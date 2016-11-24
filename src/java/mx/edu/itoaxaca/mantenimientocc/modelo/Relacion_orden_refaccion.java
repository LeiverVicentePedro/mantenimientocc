/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.modelo;

/**
 *
 * @author Jerusalen
 */
public class Relacion_orden_refaccion {
    
    private Orden_interna idOrdenRefaccion;
    private Refaccion_empleada idRefaccion;

    public Orden_interna getIdOrdenRefaccion() {
        return idOrdenRefaccion;
    }

    public void setIdOrdenRefaccion(Orden_interna idOrdenRefaccion) {
        this.idOrdenRefaccion = idOrdenRefaccion;
    }

    public Refaccion_empleada getIdRefaccion() {
        return idRefaccion;
    }

    public void setIdRefaccion(Refaccion_empleada idRefaccion) {
        this.idRefaccion = idRefaccion;
    }
    
    
    
}
