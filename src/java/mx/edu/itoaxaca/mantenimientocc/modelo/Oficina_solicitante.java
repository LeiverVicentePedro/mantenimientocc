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
public class Oficina_solicitante {
    
    private int idOficinaSolicitante;
    private String nombreOficina;
    private Departamento idDepartamento;
    private int Extencion;
    private boolean estatus;

    public int getIdOficinaSolicitante() {
        return idOficinaSolicitante;
    }

    public void setIdOficinaSolicitante(int idOficinaSolicitante) {
        this.idOficinaSolicitante = idOficinaSolicitante;
    }

    public String getNombreOficina() {
        return nombreOficina;
    }

    public void setNombreOficina(String nombreOficina) {
        this.nombreOficina = nombreOficina;
    }

    public Departamento getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Departamento idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public int getExtencion() {
        return Extencion;
    }

    public void setExtencion(int Extencion) {
        this.Extencion = Extencion;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }
}
