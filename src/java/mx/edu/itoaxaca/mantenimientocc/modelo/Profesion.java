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
public class Profesion implements Serializable{
    private int idprofesion;
    private String nombre_profesion;

    public int getIdprofesion() {
        return idprofesion;
    }

    public void setIdprofesion(int idprofesion) {
        this.idprofesion = idprofesion;
    }

    public String getNombre_profesion() {
        return nombre_profesion;
    }

    public void setNombre_profesion(String nombre_profesion) {
        this.nombre_profesion = nombre_profesion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.idprofesion;
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
        final Profesion other = (Profesion) obj;
        if (this.idprofesion != other.idprofesion) {
            return false;
        }
        return true;
    }
    
    
    
    
}
