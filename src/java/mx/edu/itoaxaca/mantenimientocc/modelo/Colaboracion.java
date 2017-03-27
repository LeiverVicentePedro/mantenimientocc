/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.modelo;

import java.io.Serializable;

/**
 *
 * @author leiver
 */
public class Colaboracion implements Serializable{
    
    private int idColaboracion;
    private Asigna_solicitud idAsignaSolicitud;
    private Usuario idUsuario;
    private Boolean estatus;

    public int getIdColaboracion() {
        return idColaboracion;
    }

    public void setIdColaboracion(int idColaboracion) {
        this.idColaboracion = idColaboracion;
    }

    public Asigna_solicitud getIdAsignaSolicitud() {
        return idAsignaSolicitud;
    }

    public void setIdAsignaSolicitud(Asigna_solicitud idAsignaSolicitud) {
        this.idAsignaSolicitud = idAsignaSolicitud;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }
    
}
