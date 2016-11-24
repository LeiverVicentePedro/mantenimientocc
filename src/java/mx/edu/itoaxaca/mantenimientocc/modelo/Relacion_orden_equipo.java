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
public class Relacion_orden_equipo {
    
    private Orden_interna idOrden_interna;
    private Equipo idEquipo;

    public Orden_interna getIdOrden_interna() {
        return idOrden_interna;
    }

    public void setIdOrden_interna(Orden_interna idOrden_interna) {
        this.idOrden_interna = idOrden_interna;
    }

    public Equipo getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Equipo idEquipo) {
        this.idEquipo = idEquipo;
    }
    
    
    
    
}