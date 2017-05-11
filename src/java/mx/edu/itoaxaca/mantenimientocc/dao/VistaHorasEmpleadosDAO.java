/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mx.edu.itoaxaca.mantenimientocc.conexion.Conexion;
import mx.edu.itoaxaca.mantenimientocc.modelo.TotalHorasEmpleado;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;
import mx.edu.itoaxaca.mantenimientocc.modelo.VistaHorasEmpleados;

/**
 *
 * @author leiver
 */
public class VistaHorasEmpleadosDAO extends Conexion{
    //para el administrador para que vea las horas de un empleado en especifico
    public List<VistaHorasEmpleados> listarHorasEmplado(TotalHorasEmpleado usuario) throws Exception{
        List<VistaHorasEmpleados> listaHoras = new ArrayList();
        try{
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("SELECT \n" +
            "idhoras_empleado,\n" +
            "fecha,hora_entrada, hora_salida\n" +
            "FROM\n" +
            "horas_empleado\n" +
            "INNER JOIN\n" +
            "detalle_horas_empleado ON id_horas_empleado = idhoras_empleado where id_usuario_empleado=?");
            consulta.setInt(1,usuario.getIdUsuarioEmpleado().getIdUsuario());
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                VistaHorasEmpleados horasEmpleado = new VistaHorasEmpleados();
                horasEmpleado.setIdHorasEmpleado(new HorasEmpleadoDAO().buscarHoraEmpleadoPorId(resultado.getInt("idhoras_empleado")));
                horasEmpleado.setFecha(resultado.getDate("fecha"));
                horasEmpleado.setHora_entrada(resultado.getString("hora_entrada"));
                horasEmpleado.setHora_salida(resultado.getString("hora_salida"));
                listaHoras.add(horasEmpleado);
            }
        }catch(Exception ex){
            System.out.println("Error en VistaHorasEmpleadoDAO -> listarHorasEmpleado "+ex);
        }finally{
            this.Cerrar();
        }
        return listaHoras;
    }
    
    //para la vista donde el usuario puede ver sus propias horas
    public List<VistaHorasEmpleados> listarParaUnEmpleado(Usuario usuario) throws Exception{
        List<VistaHorasEmpleados> listaHoras = new ArrayList();
        try{
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("SELECT \n" +
            "idhoras_empleado,\n" +
            "fecha,hora_entrada, hora_salida\n" +
            "FROM\n" +
            "horas_empleado\n" +
            "INNER JOIN\n" +
            "detalle_horas_empleado ON id_horas_empleado = idhoras_empleado where id_usuario_empleado=?");
            consulta.setInt(1,usuario.getIdUsuario());
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                VistaHorasEmpleados horasEmpleado = new VistaHorasEmpleados();
                horasEmpleado.setIdHorasEmpleado(new HorasEmpleadoDAO().buscarHoraEmpleadoPorId(resultado.getInt("idhoras_empleado")));
                horasEmpleado.setFecha(resultado.getDate("fecha"));
                horasEmpleado.setHora_entrada(resultado.getString("hora_entrada"));
                horasEmpleado.setHora_salida(resultado.getString("hora_salida"));
                listaHoras.add(horasEmpleado);
            }
        }catch(Exception ex){
            System.out.println("Error en VistaHorasEmpleadoDAO -> listarParaUnEmpleado "+ex);
        }finally{
            this.Cerrar();
        }
        return listaHoras;
    }
}
