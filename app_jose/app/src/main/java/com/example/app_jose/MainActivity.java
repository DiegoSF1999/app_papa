package com.example.app_jose;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner SpinnerTipos;

    EditText EditTextDinero;
    EditText EditTextConcepto;
    EditText EditTextCobroPago;

    TextView TextViewCobroPago;

    Button Boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.diegosanchezbp-dev.com/varios/laravel/apipagos/public/index.php/api/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        final MiApi api = retrofit.create(MiApi.class);


        SpinnerTipos = (Spinner) findViewById(R.id.SpinnerTipos);

        EditTextDinero = (EditText) findViewById(R.id.editTextDinero);
        EditTextConcepto = (EditText) findViewById(R.id.editTextConcepto);
        EditTextCobroPago = (EditText) findViewById(R.id.editTextCobroPago);

        TextViewCobroPago = (TextView) findViewById(R.id.textViewCobroPago);

        Boton = (Button) findViewById(R.id.button);

        ArrayAdapter adapter = ArrayAdapter.createFromResource( this, R.array.tipos , android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        SpinnerTipos.setAdapter(adapter);
        SpinnerTipos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {

                        TextViewCobroPago.setText("Cobrado a:");

                } else {
                    TextViewCobroPago.setText("Pagado a:");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            Chequear();

            if (Boton.isEnabled() == false){

                if (SpinnerTipos.getSelectedItemPosition() == 0)
                {

                    Call<PetiCobro> llamada = api.enviarCobro(Double.parseDouble(String.valueOf(EditTextDinero.getText())), String.valueOf(EditTextConcepto.getText()), String.valueOf(EditTextCobroPago.getText()), "Isi");
                llamada.enqueue(new Callback<PetiCobro>() {
                    @Override
                    public void onResponse(Call<PetiCobro> call, Response<PetiCobro> response) {
                        Boton.setText("Se ha enviado correctamente");
                    }

                    @Override
                    public void onFailure(Call<PetiCobro> call, Throwable t) {
                        Boton.setText("Revisa la conexion");
                    }
                });


                } else {
                    Call<PetiPago> llamada = api.enviarPago(Double.parseDouble(String.valueOf(EditTextDinero.getText())), String.valueOf(EditTextConcepto.getText()), String.valueOf(EditTextCobroPago.getText()), "Isi");
                    llamada.enqueue(new Callback<PetiPago>() {
                        @Override
                        public void onResponse(Call<PetiPago> call, Response<PetiPago> response) {
                           Boton.setText("Se ha enviado correctamente");
                        }

                        @Override
                        public void onFailure(Call<PetiPago> call, Throwable t) {
                            Boton.setText("Revisa la conexion");
                        }
                    });
                }

            }

            }
        });

    }

public void Chequear()
{
    Boton.setEnabled(false);

    String dinero = String.valueOf(EditTextDinero.getText());
    String concepto = String.valueOf(EditTextConcepto.getText());
    String cobradopagadoa = String.valueOf(EditTextCobroPago.getText());

    Boolean check = true;

    Integer punto = dinero.indexOf(".");
    Integer longitud = dinero.length();
    int resta = longitud - punto;

    if (dinero.equals(""))
    {
        Toast.makeText(getApplicationContext(),"introduce el dinero", Toast.LENGTH_SHORT).show();
        check = false;

    } else if (punto != -1) {

        if (resta == 1)
        {
            Toast.makeText(getApplicationContext(),"quita el punto del dinero", Toast.LENGTH_SHORT).show();
        } else if (resta == 2 || resta == 3){

        } else {
            Toast.makeText(getApplicationContext(),"comprueba los centimos ", Toast.LENGTH_SHORT).show();
            check = false;
        }

    }

    if (concepto.equals("") || concepto.equals(" "))
    {
        check = false;
        Toast.makeText(getApplicationContext(),"escribe un concepto", Toast.LENGTH_SHORT).show();
    } else if (cobradopagadoa.equals("") || cobradopagadoa.equals(" "))
    {
        check = false;
        Toast.makeText(getApplicationContext(),"escribe a quien has pagado o cobrado", Toast.LENGTH_SHORT).show();
    }
    //final de la combrobacion

    if (check == false){
        Boton.setEnabled(true);
    }
}

public void EnviarDatos()
{

}


}
