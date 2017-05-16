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
public class TotalHorasEmpleado implements Serializable{
    private Usuario idUsuarioEmpleado;
    private String horasTotales;

    public Usuario getIdUsuarioEmpleado() {
        return idUsuarioEmpleado;
    }

    public void setIdUsuarioEmpleado(Usuario idUsuarioEmpleado) {
        this.idUsuarioEmpleado = idUsuarioEmpleado;
    }

    public String getHorasTotales() {
        return horasTotales;
    }

    public void setHorasTotales(String horasTotales) {
        this.horasTotales = horasTotales;
    }
    
    
}
