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
public class Empleado_periodo implements Serializable {
    
    private int idempleado_periodo;
    private Periodo_semestral id_periodo;
    private String año;
    private Usuario id_usuario_personal;

    public int getIdempleado_periodo() {
        return idempleado_periodo;
    }

    public void setIdempleado_periodo(int idempleado_periodo) {
        this.idempleado_periodo = idempleado_periodo;
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

    public Usuario getId_usuario_personal() {
        return id_usuario_personal;
    }

    public void setId_usuario_personal(Usuario id_usuario_personal) {
        this.id_usuario_personal = id_usuario_personal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Empleado_periodo other = (Empleado_periodo) obj;
        if (this.idempleado_periodo != other.idempleado_periodo) {
            return false;
        }
        return true;
    }
    
    
    
    
}
