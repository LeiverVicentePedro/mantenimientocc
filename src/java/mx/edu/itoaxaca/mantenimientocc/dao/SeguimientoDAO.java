/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.dao;

import java.sql.PreparedStatement;
import mx.edu.itoaxaca.mantenimientocc.conexion.Conexion;
import mx.edu.itoaxaca.mantenimientocc.modelo.Seguimiento;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mx.edu.itoaxaca.mantenimientocc.modelo.Solicitud_mc;

/**
 *
 * @author Jerusalen
 */
public class SeguimientoDAO extends Conexion{
    
    public void registrarSeguimiento(Seguimiento seguimiento) throws Exception  {

        try {
            this.Conectar(); //en la consulta se le quita el campo id_usuario_personal ya que no se registrara en la solicitud
            PreparedStatement inserta = this.getConexion().prepareStatement("INSERT INTO seguimiento (fecha,id_solicitud, estado_solicitud, estado_asignacion) values(?,?,?,?)");
            inserta.setDate(1, (Date) seguimiento.getFecha());
            inserta.setInt(2, seguimiento.getId_solicitud().getIdsolicitud_mc());
            inserta.setBoolean(3, seguimiento.getEstado_solicitud());
            inserta.setBoolean(4, seguimiento.getEstado_asignacion());
            inserta.executeUpdate();
          
        } 
        catch(Exception e){
          System.out.println("error en Seguimiento DAO -->RegistrarSeguimiento"+"/n"+e);
        }
        finally{
           this.Cerrar();
        }
    }
    
    public Seguimiento elegirDatoSeguimiento(Solicitud_mc seguimientoElige) throws Exception{// esto es para la parte de en asigna para buscar el id del seguimiento
        Seguimiento seguimientoDos=null;
        ResultSet resultadosetseguimiento;
        try{
            this.Conectar();
             PreparedStatement consulta= this.getConexion().prepareStatement("SELECT * FROM seguimiento WHERE id_solicitud=?");
            consulta.setInt(1, seguimientoElige.getIdsolicitud_mc());
            resultadosetseguimiento = consulta.executeQuery();
            while(resultadosetseguimiento.next())
            {
              seguimientoDos= new Seguimiento();
              
             seguimientoDos.setIdseguimiento(resultadosetseguimiento.getInt("idseguimiento"));
              seguimientoDos.setFecha(resultadosetseguimiento.getDate("fecha"));
              seguimientoDos.setId_usuario_personal(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadosetseguimiento.getInt("id_usuario_personal")));
              seguimientoDos.setId_solicitud(new Solicitud_mcDAO().buscarDeSolicitudEntero(resultadosetseguimiento.getInt("id_solicitud")));
              seguimientoDos.setEstado_solicitud(resultadosetseguimiento.getBoolean("estado_solicitud"));
              seguimientoDos.setEstado_asignacion(resultadosetseguimiento.getBoolean("estado_asignacion"));
              }
        }
        catch(Exception e){
           System.out.println("error en SeguimientoDao metodo ElegirDato"+e);
        }
        finally{
           this.Cerrar();
        }
        return seguimientoDos;
    }
    
    public void modificarSeguimiento (Seguimiento seguimientomodificar) throws Exception{
          
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("UPDATE seguimiento SET fecha=?, id_usuario_personal=?, id_solicitud=?, estado_solicitud=?, estado_asignacion=? WHERE idseguimiento=?");
           consulta.setDate(1, (Date) seguimientomodificar.getFecha());
           consulta.setInt(2, seguimientomodificar.getId_usuario_personal().getIdUsuario());
            consulta.setInt(3, seguimientomodificar.getId_solicitud().getIdsolicitud_mc());
            consulta.setBoolean(4, seguimientomodificar.getEstado_solicitud());
           consulta.setBoolean(5, seguimientomodificar.getEstado_asignacion());
           consulta.setInt(6,seguimientomodificar.getIdseguimiento());
            consulta.executeUpdate();
        }
        catch(Exception e){
          System.out.println("error en SeguimientoDao metodo Modificar"+e);
        }
        finally{
           this.Cerrar();
        }
    }  
    
    public List<Seguimiento> listarSeguimiento() throws Exception{
     List<Seguimiento> lista;
        ResultSet resultadoset;
     try{
         this.Conectar();
         PreparedStatement consulta=this.getConexion().prepareCall("SELECT * FROM seguimiento");
         resultadoset= consulta.executeQuery();
         lista =new ArrayList();
         while(resultadoset.next()){
             Seguimiento seguimiento=new Seguimiento();
              seguimiento.setIdseguimiento(resultadoset.getInt("idseguimiento"));
              seguimiento.setFecha(resultadoset.getDate("fecha"));
              seguimiento.setId_usuario_personal(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoset.getInt("id_usuario_personal")));
              seguimiento.setId_solicitud(new Solicitud_mcDAO().buscarDeSolicitudEntero(resultadoset.getInt("id_solicitud")));
              seguimiento.setEstado_solicitud(resultadoset.getBoolean("estado_solicitud"));
              seguimiento.setEstado_asignacion(resultadoset.getBoolean("estado_asignacion"));
              
             
             lista.add(seguimiento);
         }
             
     }
     catch(Exception e){
         throw e;
         
     }
     finally{
         this.Cerrar();
     }
     return lista;
    }
    
    public Seguimiento identificadorDetalleSeguimiento(Seguimiento seguimientodetalle) throws Exception {//
        ResultSet resultadoset;
        Seguimiento seguimiento = null;
        try {
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("SELECT * FROM seguimiento WHERE id_solicitud=?");//debe ser otro dato que si conoscamos no uno q apenas se registrara (no el dato a buscar)
            consulta.setInt(1, seguimientodetalle.getId_solicitud().getIdsolicitud_mc());
            resultadoset = consulta.executeQuery();
            if (resultadoset.next() == true) {
                seguimiento = new Seguimiento();
               seguimiento.setIdseguimiento(resultadoset.getInt("idseguimiento"));
              seguimiento.setFecha(resultadoset.getDate("fecha"));
              seguimiento.setId_usuario_personal(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoset.getInt("id_usuario_personal")));
              seguimiento.setId_solicitud(new Solicitud_mcDAO().buscarDeSolicitudEntero(resultadoset.getInt("id_solicitud")));
              seguimiento.setEstado_solicitud(resultadoset.getBoolean("estado_solicitud"));
              seguimiento.setEstado_asignacion(resultadoset.getBoolean("estado_asignacion"));
           }
            return seguimiento;
        } catch (Exception ex) {
            throw ex;
        } finally {
            this.Cerrar();
        }
    }
   // esto es para el id_SEGUIMIENTO que se necesita para listar detalle_seguimiento
    public Seguimiento identificadorDetalleSeguimientoID(int seguimientodetalle) throws Exception {//
        ResultSet resultadoset;
        Seguimiento seguimiento = null;
        try {
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("SELECT * FROM seguimiento WHERE idseguimiento=?");//debe ser otro dato que si conoscamos no uno q apenas se registrara (no el dato a buscar)
            consulta.setInt(1, seguimientodetalle);
            resultadoset = consulta.executeQuery();
            if (resultadoset.next() == true) {
                seguimiento = new Seguimiento();
               seguimiento.setIdseguimiento(resultadoset.getInt("idseguimiento"));
              seguimiento.setFecha(resultadoset.getDate("fecha"));
              seguimiento.setId_usuario_personal(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoset.getInt("id_usuario_personal")));
              seguimiento.setId_solicitud(new Solicitud_mcDAO().buscarDeSolicitudEntero(resultadoset.getInt("id_solicitud")));
              seguimiento.setEstado_solicitud(resultadoset.getBoolean("estado_solicitud"));
              seguimiento.setEstado_asignacion(resultadoset.getBoolean("estado_asignacion"));
           }
            return seguimiento;
        } catch (Exception ex) {
            throw ex;
        } finally {
            this.Cerrar();
        }
    }
   
    
}
