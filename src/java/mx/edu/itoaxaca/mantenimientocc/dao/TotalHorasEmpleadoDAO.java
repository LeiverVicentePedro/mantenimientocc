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

/**
 *
 * @author leiver
 */
public class TotalHorasEmpleadoDAO extends Conexion{
   
    public List<TotalHorasEmpleado> listarHorasEmpleado() throws Exception{
        List<TotalHorasEmpleado> listaHorasEmpleado = new ArrayList();
        try{
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("SELECT * FROM total_horas_empleado");
            ResultSet resultado = consulta.executeQuery();
            
            while(resultado.next()){
                /*creamos el objeto y llenamos lista*/
                TotalHorasEmpleado horasEmpleado = new TotalHorasEmpleado();
                horasEmpleado.setIdUsuarioEmpleado(new UsuarioDAO().consultarUsuarioPorIdEntero(resultado.getInt("id_usuario_empleado")));
                horasEmpleado.setHorasTotales(resultado.getString("total"));
                listaHorasEmpleado.add(horasEmpleado);
                
            }
        }catch(Exception ex){
            System.out.println("Error en TotalHorasEmpeladoDAO -> listarHorasEmpleado "+ex);
        }finally{
            this.Cerrar();
        }
        return listaHorasEmpleado;
    }
    
    public TotalHorasEmpleado totalMisHoras(Usuario usuario) throws Exception{
        TotalHorasEmpleado misHoras = new TotalHorasEmpleado();
        try{
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("select * from total_horas_empleado where id_usuario_empleado=?");
            consulta.setInt(1,usuario.getIdUsuario());
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                misHoras.setIdUsuarioEmpleado(usuario);
                misHoras.setHorasTotales(resultado.getString("total"));
            }
        }catch(Exception ex){
            System.out.println("Error en TotalHorasEmpleadoDAO ->totalMisHoras "+ex);
        }finally{
            this.Cerrar();
        }
        return misHoras;
    }
}
