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
public class Catalogo_niveles implements Serializable{
    private int idcatalogo_niveles;
    private int nivel;
    private String descripcion;

    public int getIdcatalogo_niveles() {
        return idcatalogo_niveles;
    }

    public void setIdcatalogo_niveles(int idcatalogo_niveles) {
        this.idcatalogo_niveles = idcatalogo_niveles;
    }

  

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.idcatalogo_niveles;
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
        final Catalogo_niveles other = (Catalogo_niveles) obj;
        if (this.idcatalogo_niveles != other.idcatalogo_niveles) {
            return false;
        }
        return true;
    }
    
    
}
