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
public class Departamento {
    
    private int idDepartamento;
    private String claveDepartamento;
    private String nombreDepartamento;
    private Area idArea;

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDeparrtameto(int idDeparrtameto) {
        this.idDepartamento = idDeparrtameto;
    }

    public String getClaveDepartamento() {
        return claveDepartamento;
    }

    public void setClaveDepartamento(String claveDepartamento) {
        this.claveDepartamento = claveDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }
    private boolean estatus;
}
