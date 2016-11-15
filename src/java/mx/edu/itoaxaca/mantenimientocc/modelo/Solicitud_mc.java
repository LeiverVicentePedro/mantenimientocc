/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.modelo;

import java.util.Date;

/**
 *
 * @author leiver
 */
public class Solicitud_mc {
 
    private int idsolicitud_mc;
    private Usuario id_usuario;
    private String folio;
    private Date fecha;
    private String otroProblema;
    private Departamento id_departamento;
    
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

    
    
}
