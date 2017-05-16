/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.modelo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author leiver
 */
public class VistaHorasEmpleados implements Serializable{
    
    private HorasEmpleado idHorasEmpleado;
    private Date fecha;
    private int idDetalleHorasEmpleado;
    private String hora_entrada;
    private String hora_salida;
    private String acumulado;

    public HorasEmpleado getIdHorasEmpleado() {
        return idHorasEmpleado;
    }

    public void setIdHorasEmpleado(HorasEmpleado idHorasEmpleado) {
        this.idHorasEmpleado = idHorasEmpleado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdDetalleHorasEmpleado() {
        return idDetalleHorasEmpleado;
    }

    public void setIdDetalleHorasEmpleado(int idDetalleHorasEmpleado) {
        this.idDetalleHorasEmpleado = idDetalleHorasEmpleado;
    }

    public String getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(String hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    public String getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    public String getAcumulado() {
        return acumulado;
    }

    public void setAcumulado(String acumulado) {
        this.acumulado = acumulado;
    }
    
    
    
}
