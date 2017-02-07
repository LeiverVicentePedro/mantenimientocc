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
public class SeguimientoCliente implements Serializable{
    private int idsolicitud_mc;
    private String folio;
    private Date fechaSolicitud;
    private Boolean estado_seguimiento;
    private int idseguimiento;
    private Boolean estado_asignacion;
    private int iddetalle_seguimiento;
    private String estado_detalle_Seguimiento;

    public int getIdsolicitud_mc() {
        return idsolicitud_mc;
    }

    public void setIdsolicitud_mc(int idsolicitud_mc) {
        this.idsolicitud_mc = idsolicitud_mc;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Boolean getEstado_seguimiento() {
        return estado_seguimiento;
    }

    public void setEstado_seguimiento(Boolean estado_seguimiento) {
        this.estado_seguimiento = estado_seguimiento;
    }

    public int getIdseguimiento() {
        return idseguimiento;
    }

    public void setIdseguimiento(int idseguimiento) {
        this.idseguimiento = idseguimiento;
    }

    public Boolean getEstado_asignacion() {
        return estado_asignacion;
    }

    public void setEstado_asignacion(Boolean estado_asignacion) {
        this.estado_asignacion = estado_asignacion;
    }

    public int getIddetalle_seguimiento() {
        return iddetalle_seguimiento;
    }

    public void setIddetalle_seguimiento(int iddetalle_seguimiento) {
        this.iddetalle_seguimiento = iddetalle_seguimiento;
    }

    public String getEstado_detalle_Seguimiento() {
        return estado_detalle_Seguimiento;
    }

    public void setEstado_detalle_Seguimiento(String estado_detalle_Seguimiento) {
        this.estado_detalle_Seguimiento = estado_detalle_Seguimiento;
    }
    
    
    
    
    
}
