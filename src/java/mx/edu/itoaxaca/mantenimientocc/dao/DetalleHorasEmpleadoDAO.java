/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import mx.edu.itoaxaca.mantenimientocc.conexion.Conexion;
import mx.edu.itoaxaca.mantenimientocc.modelo.DetalleHorasEmpleado;
import mx.edu.itoaxaca.mantenimientocc.modelo.HorasEmpleado;

/**
 *
 * @author leiver
 */
public class DetalleHorasEmpleadoDAO extends Conexion{
    /*este DAO sera utilizado dentro del BEAN de HORASEMPLEADO*/
    public void registrarDetalleHorasEmpleado(DetalleHorasEmpleado detalle) throws Exception{
        try{
            this.Conectar();
            PreparedStatement registra = this.getConexion().prepareStatement("INSERT INTO detalle_horas_empleado(id_horas_empleado,hora_entrada) VALUES(?,?)");
            registra.setInt(1, detalle.getIdHorasEmpleado().getIdhoras_empleado());
            registra.setString(2, detalle.getHoraEntrada());
            registra.executeUpdate();
            
        }catch(Exception ex){
            System.out.println("Error en DetalleHorasEmpleadoDAO -> registrarDetalleHorssEmpleado "+ex);
        }finally{
           this.Cerrar();
        }
    }
   
    public DetalleHorasEmpleado buscaDetalleHorasEmpleado(HorasEmpleado horas) throws Exception{
        DetalleHorasEmpleado detalle = new DetalleHorasEmpleado();

        try{
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("SELECT * FROM detalle_horas_empleado WHERE id_horas_empleado=? AND hora_salida is null");
            consulta.setInt(1,horas.getIdhoras_empleado());
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                detalle.setIdHorasEmpleado(new HorasEmpleadoDAO().buscarHoraEmpleadoPorId(resultado.getInt("id_horas_empleado")));
                detalle.setHoraEntrada(resultado.getString("hora_entrada"));
                detalle.setHoraSalida(resultado.getString("hora_salida"));
            }
        }catch(Exception ex){
            System.out.println("Error en DetalleHorasEmpleadoDAO -> buscarDetalleHorasEmpleado "+ex);
        }finally{
            this.Cerrar();
        }
        return detalle;
    }
    
    public void actualizarDetalleHorasEmpleado(DetalleHorasEmpleado detalleHoras) throws Exception{
        try{
            this.Conectar();
            PreparedStatement modifica = this.getConexion().prepareStatement("UPDATE detalle_horas_empleado SET hora_salida=? WHERE id_horas_empleado=? and hora_salida is null");
            modifica.setString(1, detalleHoras.getHoraSalida());
            modifica.setInt(2, detalleHoras.getIdHorasEmpleado().getIdhoras_empleado());
            modifica.executeUpdate();
        }catch(Exception ex){
            System.out.println("error en DetalleHorasEmpleadoDAO -> modificarHorasEmpleado "+ex);
        }finally{
            this.Cerrar();
        }
    }
}
