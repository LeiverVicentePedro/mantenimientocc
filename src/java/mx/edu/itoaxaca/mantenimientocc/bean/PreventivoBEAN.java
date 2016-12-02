/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.io.Serializable;
import mx.edu.itoaxaca.mantenimientocc.modelo.Preventivo;

/**
 *
 * @author leiver
 */
public class PreventivoBEAN implements Serializable{
    Preventivo preventivo = new Preventivo();

    public Preventivo getPreventivo() {
        return preventivo;
    }

    public void setPreventivo(Preventivo preventivo) {
        this.preventivo = preventivo;
    }
    
    public void registrarPreventivo(){
        
    }
    
}
