/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.modelo;

import java.io.Serializable;

/**
 *
 * @author Jerusalen
 */
public class Catalogo_servicio_solicitado implements Serializable{
    
    private int idcatalogo_servicio_solicitado;
    private String servicio_solicitado;
    private Departamento departamento;
    private Boolean estatus;

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }
    

    
    public int getIdcatalogo_servicio_solicitado() {
        return idcatalogo_servicio_solicitado;
    }

    public void setIdcatalogo_servicio_solicitado(int idcatalogo_servicio_solicitado) {
        this.idcatalogo_servicio_solicitado = idcatalogo_servicio_solicitado;
    }

    public String getServicio_solicitado() {
        return servicio_solicitado;
    }

    public void setServicio_solicitado(String servicio_solicitado) {
        this.servicio_solicitado = servicio_solicitado;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.idcatalogo_servicio_solicitado;
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
        final Catalogo_servicio_solicitado other = (Catalogo_servicio_solicitado) obj;
        if (this.idcatalogo_servicio_solicitado != other.idcatalogo_servicio_solicitado) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
