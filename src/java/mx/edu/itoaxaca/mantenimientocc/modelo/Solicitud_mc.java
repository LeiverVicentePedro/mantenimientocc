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
 * @author leiver
 */
public class Solicitud_mc implements Serializable{
 
    private int idsolicitud_mc;
    private Usuario id_usuario;
    private String folio;
    private Date fecha;
    private String otroProblema;
    private Departamento id_departamento;
    private String asignacion;
    private Boolean estatus;
    private Boolean estado_seguimiento;
    private String icono;//variable para ir a asignar seguimienrto
    private String vista;//ruta pra ir a asignar seguimiento
    
    public int getIdsolicitud_mc() {
        return idsolicitud_mc;
    }

    public void setIdsolicitud_mc(int idsolicitud_mc) {
        this.idsolicitud_mc = idsolicitud_mc;
    }

    public Usuario getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getOtroProblema() {
        return otroProblema;
    }

    public void setOtroProblema(String otroProblema) {
        this.otroProblema = otroProblema;
    }

    public Departamento getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(Departamento id_departamento) {
        this.id_departamento = id_departamento;
    }

    public String getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(String asignacion) {
        this.asignacion = asignacion;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }

    public Boolean getEstado_seguimiento() {
        return estado_seguimiento;
    }

    public void setEstado_seguimiento(Boolean estado_seguimiento) {
        this.estado_seguimiento = estado_seguimiento;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getVista() {
        return vista;
    }

    public void setVista(String vista) {
        this.vista = vista;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.idsolicitud_mc;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Solicitud_mc other = (Solicitud_mc) obj;
        if (this.idsolicitud_mc != other.idsolicitud_mc) {
            return false;
        }
        return true;
    }
    
    

    
    
}
