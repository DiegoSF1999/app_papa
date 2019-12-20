package com.example.app_jose;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MiApi {

    @FormUrlEncoded
    @POST("cobros")
    Call<PetiCobro>enviarCobro(@Field("dinero") Double dinero, @Field("concepto") String concepto, @Field("cobrado_a") String cobrado_a,@Field("subido_por") String subido_por);

    @FormUrlEncoded
    @POST("pagos")
    Call<PetiPago>enviarPago(@Field("dinero") Double dinero, @Field("concepto") String concepto, @Field("pagado_a") String pagado_a,@Field("subido_por") String subido_por);

}
