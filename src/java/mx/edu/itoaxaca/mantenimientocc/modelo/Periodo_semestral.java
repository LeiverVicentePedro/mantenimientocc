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
public class Periodo_semestral implements Serializable{
    
    private int idperiodo_semestral;
    private String periodo;

    public int getIdperiodo_semestral() {
        return idperiodo_semestral;
    }

    public void setIdperiodo_semestral(int idperiodo_semestral) {
        this.idperiodo_semestral = idperiodo_semestral;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.idperiodo_semestral;
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
        final Periodo_semestral other = (Periodo_semestral) obj;
        if (this.idperiodo_semestral != other.idperiodo_semestral) {
            return false;
        }
        return true;
    }
    
    
}
