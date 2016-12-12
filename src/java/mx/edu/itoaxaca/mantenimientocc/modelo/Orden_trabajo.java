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
public class Orden_trabajo implements Serializable{
    
    
    private int idorden_trabajo;
    private String mantenimiento_tipo;
    private String tipo_servicio;
    private Usuario id_usuario_personal;
    private Date fecha_realizacion;
    private String trabajo_descripcion;
    private Usuario id_usuario_personal_jefe;
    private Solicitud_mc id_solicitudmc;

    public int getIdorden_trabajo() {
        return idorden_trabajo;
    }

    public void setIdorden_trabajo(int idorden_trabajo) {
        this.idorden_trabajo = idorden_trabajo;
    }
    
    
    public String getMantenimiento_tipo() {
        return mantenimiento_tipo;
    }

    public void setMantenimiento_tipo(String mantenimiento_tipo) {
        this.mantenimiento_tipo = mantenimiento_tipo;
    }

    public String getTipo_servicio() {
        return tipo_servicio;
    }

    public void setTipo_servicio(String tipo_servicio) {
        this.tipo_servicio = tipo_servicio;
    }

    public Usuario getId_usuario_personal() {
        return id_usuario_personal;
    }

    public void setId_usuario_personal(Usuario id_usuario_personal) {
        this.id_usuario_personal = id_usuario_personal;
    }

    public Date getFecha_realizacion() {
        return fecha_realizacion;
    }

    public void setFecha_realizacion(Date fecha_realizacion) {
        this.fecha_realizacion = fecha_realizacion;
    }

    public String getTrabajo_descripcion() {
        return trabajo_descripcion;
    }

    public void setTrabajo_descripcion(String trabajo_descripcion) {
        this.trabajo_descripcion = trabajo_descripcion;
    }

    public Usuario getId_usuario_personal_jefe() {
        return id_usuario_personal_jefe;
    }

    public void setId_usuario_personal_jefe(Usuario id_usuario_personal_jefe) {
        this.id_usuario_personal_jefe = id_usuario_personal_jefe;
    }

    public Solicitud_mc getId_solicitudmc() {
        return id_solicitudmc;
    }

    public void setId_solicitudmc(Solicitud_mc id_solicitudmc) {
        this.id_solicitudmc = id_solicitudmc;
    }
    
    
    
    
}
