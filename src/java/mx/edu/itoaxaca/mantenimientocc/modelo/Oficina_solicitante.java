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
public class Oficina_solicitante implements Serializable {
    
    private int idOficinaSolicitante;
    private String nombreOficina;
    private Departamento departamento;
    private int Extencion;
    private Boolean estatus;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.idOficinaSolicitante;
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
        final Oficina_solicitante other = (Oficina_solicitante) obj;
        if (this.idOficinaSolicitante != other.idOficinaSolicitante) {
            return false;
        }
        return true;
    }
    
    
    

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

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    
    public int getExtencion() {
        return Extencion;
    }

    public void setExtencion(int Extencion) {
        this.Extencion = Extencion;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }

   

    
}
