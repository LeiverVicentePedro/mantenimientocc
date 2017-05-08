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
public class DetalleHorasEmpleado implements Serializable{
    
    private HorasEmpleado idHorasEmpleado;
    private String horaEntrada;
    private String horaSalida;

    public HorasEmpleado getIdHorasEmpleado() {
        return idHorasEmpleado;
    }

    public void setIdHorasEmpleado(HorasEmpleado idHorasEmpleado) {
        this.idHorasEmpleado = idHorasEmpleado;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

}
