/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import mx.edu.itoaxaca.mantenimientocc.conexion.Conexion;
import mx.edu.itoaxaca.mantenimientocc.modelo.Empleado_periodo;
import mx.edu.itoaxaca.mantenimientocc.modelo.Periodo_semestral;

/**
 *
 * @author Jerusalen
 */
public class Empleado_periodoDAO extends Conexion {
    
    public void registrarEmpleado_periodo(Empleado_periodo empleado_periodo) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("INSERT INTO empleado_periodo (id_periodo,año,id_usuario_personal) values(?,?,?)");
          
            consulta.setInt(1,empleado_periodo.getId_periodo().getIdperiodo_semestral());
            consulta.setString(2,empleado_periodo.getAño());
            consulta.setInt(3,empleado_periodo.getId_usuario_personal().getIdUsuario());
           
            consulta.executeUpdate();
        }
        catch(Exception e){
        System.out.println("error en Empleado_Periodo DAO -->RegistrarEMPLEADO_PERIODO"+"\n"+e);
       // System.out.println("error en DAO"); 
        }
     finally{
           this.Cerrar();
        }
    }
    
    
    public Empleado_periodo elegirDatoEP(Empleado_periodo empleadoPeriodo) throws Exception{//creado para lo de modicar periodo
        Empleado_periodo EPdos=null;
        ResultSet resultadoset;
        
        try{
            this.Conectar();
             PreparedStatement consulta= this.getConexion().prepareStatement("SELECT idempleado_periodo, id_periodo,año, id_usuario_personal FROM empleado_periodo WHERE id_periodo=?");
            consulta.setInt(1, empleadoPeriodo.getId_periodo().getIdperiodo_semestral());
            resultadoset = consulta.executeQuery();
            while(resultadoset.next())
            {
              EPdos= new Empleado_periodo();
              EPdos.setIdempleado_periodo(resultadoset.getInt("idempleado_periodo"));
              EPdos.setId_periodo(new Periodo_semestralDAO().buscarIdPeriodo(resultadoset.getInt("id_periodo")));
              EPdos.setAño(resultadoset.getString("año"));
              EPdos.setId_usuario_personal(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoset.getInt("id_usuario_personal")));
            }
        }
        catch(Exception e){
           throw e; 
        }
        finally{
           this.Cerrar();
        }
        return EPdos;
    }
    
    public void modificarEmpleadoPeriodo (Empleado_periodo empleadoPeriodomodificar) throws Exception{//modificar periodo dentro de usuario pero aun no funciona
          
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("UPDATE empleado_periodo SET idempleado_periodo=?, id_periodo=?, año=?, id_usuario_personal=? WHERE id_periodo=?");
            consulta.setInt(1, empleadoPeriodomodificar.getId_periodo().getIdperiodo_semestral());
            consulta.setString(2, empleadoPeriodomodificar.getAño());
            consulta.setInt(3,empleadoPeriodomodificar.getId_usuario_personal().getIdUsuario());
            
            consulta.executeUpdate();
        }
        catch(Exception e){
          System.out.println("error en Empleado_periodoDao metodo Modificar"+e);
        }
        finally{
           this.Cerrar();
        }
    }  
    
    
    
    
    
    
}
