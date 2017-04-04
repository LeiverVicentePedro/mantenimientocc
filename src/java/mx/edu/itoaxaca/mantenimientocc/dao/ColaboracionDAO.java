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
import mx.edu.itoaxaca.mantenimientocc.modelo.Asigna_solicitud;
import mx.edu.itoaxaca.mantenimientocc.modelo.Colaboracion;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;

/**
 *
 * @author leiver
 */
public class ColaboracionDAO extends Conexion{
    
   public void AgregarColaborador(Colaboracion colaborador) throws Exception{
       try{
           this.Conectar();
           PreparedStatement inserta = this.getConexion().prepareStatement("INSERT INTO colaboracion (id_asigna_solicitud,id_usuario,estatus) VALUES(?,?,?)");
           inserta.setInt(1, colaborador.getIdAsignaSolicitud().getIdasigna_solicitud());
           inserta.setInt(2, colaborador.getIdUsuario().getIdUsuario());
           inserta.setBoolean(3, true);
           
           inserta.executeUpdate();
       }catch(Exception ex){
           System.out.println("Error en ColaboracionDAO -> AgregarColaborador "+ex);
           
       }finally{
           this.Cerrar();
       }
   }
   
   public List<Colaboracion> buscarColaboracionPorAsignacionSolicitud(Asigna_solicitud asignaSolicitud) throws Exception{
       List<Colaboracion> colabora = new ArrayList();
       try{
           this.Conectar();
           PreparedStatement consulta = this.getConexion().prepareStatement("SELECT * FROM colaboracion WHERE id_asigna_solicitud=?");
           consulta.setInt(1, asignaSolicitud.getIdasigna_solicitud());
           ResultSet resultado = consulta.executeQuery();
           while(resultado.next()){
               Colaboracion colaboracion = new Colaboracion();
               colaboracion.setIdColaboracion(resultado.getInt("idcolaboracion"));
               colaboracion.setIdAsignaSolicitud(asignaSolicitud);
               colaboracion.setIdUsuario(new UsuarioDAO().consultarUsuarioPorIdEntero(resultado.getInt("id_usuario")));
               colaboracion.setEstatus(resultado.getBoolean("estatus"));
               colabora.add(colaboracion);
           }
       }catch(Exception ex){
           System.out.println("Error en ColaboracionDAO -> buscarColaboracionPorAsignacionSolicitud "+ex);
       }finally{
           this.Cerrar();
       }
       return colabora;
   }
   
   public void modificarEstatusColaboracion(Colaboracion colaborador) throws Exception{
       try{
           this.Conectar();
           PreparedStatement actualiza = this.getConexion().prepareStatement("UPDATE colaboracion SET id_asigna_solicitud= ?,id_usuario= ? ,estatus= ? WHERE idcolaboracion= ?");
           actualiza.setInt(1, colaborador.getIdAsignaSolicitud().getIdasigna_solicitud());
           actualiza.setInt(2, colaborador.getIdUsuario().getIdUsuario());
           actualiza.setBoolean(3, false);
           actualiza.setInt(4, colaborador.getIdColaboracion());
           
           actualiza.executeUpdate();
       }catch(Exception ex){
           System.out.println("error en ColaboracionDAO -> modificarEstatusColaboracion "+ex);
       }finally{
           this.Cerrar();
       }
   }
   public List<Colaboracion> listarColaboradoresDeLider(Asigna_solicitud idAsignaSolicitud) throws Exception{
       
       List<Colaboracion> misColaboradores = new ArrayList();
       try{
           
           this.Conectar();
           ResultSet resultado;
           PreparedStatement consulta = this.getConexion().prepareStatement("SELECT * FROM colaboracion WHERE id_asigna_solicitud=? and estatus=true");
           consulta.setInt(1, idAsignaSolicitud.getIdasigna_solicitud());
           resultado = consulta.executeQuery();
           while(resultado.next()){
            Colaboracion colaborador = new Colaboracion();
            colaborador.setIdColaboracion(resultado.getInt("idcolaboracion"));
            colaborador.setIdAsignaSolicitud(new Asigna_solicitudDAO().buscarSolicitudPorIdAsignacion(resultado.getInt("id_asigna_solicitud")));
            colaborador.setIdUsuario(new UsuarioDAO().consultarUsuarioPorIdEntero(resultado.getInt("id_usuario")));
            colaborador.setEstatus(resultado.getBoolean("estatus"));
            misColaboradores.add(colaborador);
           }
       }catch(Exception ex){
           System.out.println("Error en ColboracionDAO -> listaColaboradoresDeLider "+ ex);
       }finally{
           this.Cerrar();
       }
       return misColaboradores;
   }
   
   public List<Colaboracion> listarColaboraciones(Usuario usuario) throws Exception{
       List<Colaboracion> misColaboraciones = new ArrayList();
       try{
           this.Conectar();
           PreparedStatement consulta = this.getConexion().prepareStatement("SELECT * FROM colaboracion WHERE id_usuario = ? and estatus=true");
           consulta.setInt(1, usuario.getIdUsuario());
           ResultSet resultado = consulta.executeQuery();
           while(resultado.next()){
            Colaboracion colaborador = new Colaboracion();
            colaborador.setIdColaboracion(resultado.getInt("idcolaboracion"));
            colaborador.setIdAsignaSolicitud(new Asigna_solicitudDAO().buscarSolicitudPorIdAsignacion(resultado.getInt("id_asigna_solicitud")));
            colaborador.setIdUsuario(new UsuarioDAO().consultarUsuarioPorIdEntero(resultado.getInt("id_usuario")));
            colaborador.setEstatus(resultado.getBoolean("estatus"));
            misColaboraciones.add(colaborador);
           }
       }catch(Exception ex){
          System.out.println("Error en ColaboracionDAO -> listarColaboraciones "+ex);
       }finally{
           this.Cerrar();
       }
       return misColaboraciones;
   }
}
