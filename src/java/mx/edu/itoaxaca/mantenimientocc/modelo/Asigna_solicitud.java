/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.modelo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Jerusalen
 */
public class Asigna_solicitud implements Serializable{
    
    private int idasigna_solicitud;
    private Usuario id_usuario_personal;
    private Solicitud_mc id_solicitud;
    private Usuario id_usuario_personal_jefe;
    private Date fecha;

    public int getIdasigna_solicitud() {
        return idasigna_solicitud;
    }

    public void setIdasigna_solicitud(int idasigna_solicitud) {
        this.idasigna_solicitud = idasigna_solicitud;
    }

    public Usuario getId_usuario_personal() {
        return id_usuario_personal;
    }

    public void setId_usuario_personal(Usuario id_usuario_personal) {
        this.id_usuario_personal = id_usuario_personal;
    }

    public Solicitud_mc getId_solicitud() {
        return id_solicitud;
    }

    public void setId_solicitud(Solicitud_mc id_solicitud) {
        this.id_solicitud = id_solicitud;
    }

    public Usuario getId_usuario_personal_jefe() {
        return id_usuario_personal_jefe;
    }

    public void setId_usuario_personal_jefe(Usuario id_usuario_personal_jefe) {
        this.id_usuario_personal_jefe = id_usuario_personal_jefe;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    
}
