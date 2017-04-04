/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.dao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mx.edu.itoaxaca.mantenimientocc.conexion.Conexion;
import mx.edu.itoaxaca.mantenimientocc.modelo.HorasEmpleado;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;
/**
 *
 * @author leiver
 */
public class HorasEmpleadoDAO extends Conexion{
    
    public void insertarHorasEmpleado(HorasEmpleado horas)throws Exception{
        try{
        this.Conectar();
        PreparedStatement inserta = this.getConexion().prepareStatement("INSERT INTO horas_empleado(id_usuario_empleado, hora_entrada,fecha,estatus) VALUES(?,?,?,?)");
        inserta.setInt(1, horas.getId_usuario_empleado().getIdUsuario());
        inserta.setString(2, horas.getHora_entrada());
        inserta.setDate(3, (Date) horas.getFecha());
        inserta.setBoolean(4, horas.getEstatus());
        inserta.executeUpdate();
        
        }catch(Exception ex){
            System.out.println("Error en HorasEmpleadoDAO -> insertarHorasEmpleado "+ex);
        }finally{
            this.Cerrar();
        }
    }
    
    public List<HorasEmpleado> listaHorasEmpleado(Usuario usuario)throws Exception{
        List<HorasEmpleado> horasEmpleadoDia = new ArrayList();
        try{
            
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("SELECT idhoras_empleado,id_usuario_empleado,hora_entrada,hora_salida,SUBTIME(hora_salida,hora_entrada) AS horas_dia, fecha FROM horas_empleado WHERE id_usuario_empleado=?");
            consulta.setInt(1, usuario.getIdUsuario());
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                HorasEmpleado horasEmpleado = new HorasEmpleado();
                horasEmpleado.setIdhoras_empleado(resultado.getInt("idhoras_empleado"));
                horasEmpleado.setId_usuario_empleado(new UsuarioDAO().consultarUsuarioPorIdEntero(resultado.getInt("id_usuario_empleado")));
                horasEmpleado.setHora_entrada(resultado.getString("hora_entrada"));
                horasEmpleado.setHora_salida(resultado.getString("hora_salida"));
                horasEmpleado.setHorasEmpleado(resultado.getString("horas_dia"));
                horasEmpleado.setFecha(resultado.getDate("fecha"));
                horasEmpleadoDia.add(horasEmpleado);
            }
            
        }catch(Exception ex){
            System.out.println("Error en HorasEmpleadoDAO -> listaHorasEmpleado "+ex);
        }finally{
            this.Cerrar();
        }
        return horasEmpleadoDia;
    }
}
