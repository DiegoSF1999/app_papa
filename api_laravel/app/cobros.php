<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Http\Request;

class cobros extends Model
{
    protected $table = 'cobros';
    protected $guarded = ['id'];
    protected $fillable = ['dinero', 'concepto', 'cobrado_a', 'subido_por'];


public function new(Request $request)
{
    try {
        $cobro = new self();

        $cobro->dinero = $request->dinero;
        $cobro->concepto = $request->concepto;
        $cobro->cobrado_a = $request->cobrado_a;
        $cobro->subido_por = $request->subido_por;
    
        $cobro->save();

        return 200;        
    } catch (\Throwable $th) {
        return 210;
    }

}

}