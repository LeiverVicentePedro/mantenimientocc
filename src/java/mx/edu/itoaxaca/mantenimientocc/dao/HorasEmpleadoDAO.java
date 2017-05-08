/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import mx.edu.itoaxaca.mantenimientocc.conexion.Conexion;
import mx.edu.itoaxaca.mantenimientocc.modelo.HorasEmpleado;

/**
 *
 * @author leiver
 */
public class HorasEmpleadoDAO extends Conexion{
    
    public void registrarHorasEmpleado(HorasEmpleado horas) throws Exception{
      try{
            this.Conectar();
            PreparedStatement inserta = this.getConexion().prepareStatement("INSERT INTO horas_empleado(id_usuario_empleado,fecha) VALUES(?,?)");
            inserta.setInt(1, horas.getId_usuario_empleado().getIdUsuario());
            inserta.setDate(2, (Date) horas.getFecha());
            inserta.executeUpdate();
      }catch(Exception ex){
          System.out.println("Error en HorasEmpleadoDAO -> registrarHorasEmpleado "+ex);
      }finally{
          this.Cerrar();
      }  
    }
    
    public HorasEmpleado buscarHoraEmpleado(HorasEmpleado horas) throws Exception {
        HorasEmpleado horasEmpleado = new HorasEmpleado();
        
        try {
            this.Conectar();
            PreparedStatement consulta =this.getConexion().prepareStatement("Select * from horas_empleado where fecha=? and id_usuario_empleado=?");
            consulta.setDate(1, (Date)horas.getFecha());
            consulta.setInt(2, horas.getId_usuario_empleado().getIdUsuario());
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                horasEmpleado.setIdhoras_empleado(resultado.getInt("idhoras_empleado"));
                horasEmpleado.setId_usuario_empleado(new UsuarioDAO().consultarUsuarioPorIdEntero(resultado.getInt("id_usuario_empleado")));
                horasEmpleado.setFecha(resultado.getDate("fecha"));
            }
        } catch (Exception ex) {
            System.out.println("Error en HorasEmpleadoDAO -> buscarHoraEmpleado " + ex);
        }finally{
            this.Cerrar();
        }
        return horasEmpleado;
    }
    
    public HorasEmpleado buscarHoraEmpleadoPorId(int idHoras) throws Exception {
        HorasEmpleado horasEmpleado = new HorasEmpleado();
        try {
            this.Conectar();
            PreparedStatement consulta =this.getConexion().prepareStatement("Select * from horas_empleado where idhoras_empleado=?");
            consulta.setInt(1, idHoras);
            ResultSet resultado=consulta.executeQuery();
            while(resultado.next()){
                horasEmpleado.setIdhoras_empleado(resultado.getInt("idhoras_empleado"));
                horasEmpleado.setId_usuario_empleado(new UsuarioDAO().consultarUsuarioPorIdEntero(resultado.getInt("id_usuario_empleado")));
                horasEmpleado.setFecha(resultado.getDate("fecha"));
            }
        } catch (Exception ex) {
            System.out.println("Error en HorasEmpleadoDAO -> buscarHoraEmpleadoPorId " + ex);
        }finally{
            this.Cerrar();
        }
        return horasEmpleado;
    }
}
