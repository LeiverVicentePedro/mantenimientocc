/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.modelo;

import java.util.Date;
import java.io.Serializable;
/**
 *
 * @author leiver
 */
public class Preventivo implements Serializable{
    private int idPreventivo;
    private Periodo_semestral id_periodo;
    private String año;
    private Date fecha_elaboracion;
    private Usuario id_usuario_personal;
    private String folio;

    public int getIdPreventivo() {
        return idPreventivo;
    }

    public void setIdPreventivo(int idPreventivo) {
        this.idPreventivo = idPreventivo;
    }

    public Periodo_semestral getId_periodo() {
        return id_periodo;
    }

    public void setId_periodo(Periodo_semestral id_periodo) {
        this.id_periodo = id_periodo;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public Date getFecha_elaboracion() {
        return fecha_elaboracion;
    }

    public void setFecha_elaboracion(Date fecha_elaboracion) {
        this.fecha_elaboracion = fecha_elaboracion;
    }

    public Usuario getId_usuario_personal() {
        return id_usuario_personal;
    }

    public void setId_usuario_personal(Usuario id_usuario_personal) {
        this.id_usuario_personal = id_usuario_personal;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.idPreventivo;
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
        final Preventivo other = (Preventivo) obj;
        if (this.idPreventivo != other.idPreventivo) {
            return false;
        }
        return true;
    }
    
    
}
