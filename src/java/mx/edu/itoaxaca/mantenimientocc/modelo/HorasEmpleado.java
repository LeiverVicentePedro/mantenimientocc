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
public class HorasEmpleado implements Serializable{
    
    private int idhoras_empleado;
    private Usuario id_usuario_empleado;
    private String hora_entrada;
    private String hora_salida;
    private String horasEmpleado;
    private Date fecha;
    private Boolean estatus;

    //---------------------------------------Geter and seter---------------------------------------------------------
    public int getIdhoras_empleado() {
        return idhoras_empleado;
    }

    public void setIdhoras_empleado(int idhoras_empleado) {
        this.idhoras_empleado = idhoras_empleado;
    }

    public Usuario getId_usuario_empleado() {
        return id_usuario_empleado;
    }

    public void setId_usuario_empleado(Usuario id_usuario_empleado) {
        this.id_usuario_empleado = id_usuario_empleado;
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

    public String getHorasEmpleado() {
        return horasEmpleado;
    }

    public void setHorasEmpleado(String horasEmpleado) {
        this.horasEmpleado = horasEmpleado;
    }

   
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }
    
    
}
