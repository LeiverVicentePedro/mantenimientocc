/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.modelo;

/**
 *
 * @author leiver
 */
public class Detalle_solicitud {
    private Solicitud_mc id_solicitud_mc;
    private Catalogo_servicio_solicitado id_catalogo_servicio_solicitado;

    public Solicitud_mc getId_solicitud_mc() {
        return id_solicitud_mc;
    }

    public void setId_solicitud_mc(Solicitud_mc id_solicitud_mc) {
        this.id_solicitud_mc = id_solicitud_mc;
    }

    public Catalogo_servicio_solicitado getId_catalogo_servicio_solicitado() {
        return id_catalogo_servicio_solicitado;
    }

    public void setId_catalogo_servicio_solicitado(Catalogo_servicio_solicitado id_catalogo_servicio_solicitado) {
        this.id_catalogo_servicio_solicitado = id_catalogo_servicio_solicitado;
    }
    
    
}
