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
    private Date fecha;


    //---------------------------------------Getter and setter---------------------------------------------------------
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
   
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
    
}
