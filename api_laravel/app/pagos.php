<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Http\Request;


class pagos extends Model
{
    protected $table = 'pagos';
    protected $guarded = ['id'];
    protected $fillable = ['dinero', 'concepto', 'pagado_a', 'subido_por'];


    public function new(Request $request)
    {
        try {
            $pagos = new self();
    
            $pagos->dinero = $request->dinero;
            $pagos->concepto = $request->concepto;
            $pagos->pagado_a = $request->pagado_a;
            $pagos->subido_por = $request->subido_por;
        
            $pagos->save();
    
            return 200;        
        } catch (\Throwable $th) {
            return 210;
        }
    
    }


}
