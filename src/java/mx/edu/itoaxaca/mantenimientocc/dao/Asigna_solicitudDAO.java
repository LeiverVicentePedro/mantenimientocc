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
import mx.edu.itoaxaca.mantenimientocc.modelo.Asigna_solicitud;

/**
 *
 * @author Jerusalen
 */
public class Asigna_solicitudDAO extends Conexion{
     public void registrarAsignarSolicitud(Asigna_solicitud asignarSolicitudregistra) throws Exception {
        try {
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("INSERT INTO asigna_solicitud (id_usuario_personal,id_solicitud,id_usuario_personal_jefe,fecha_asignada) values(?,?,?,?)");
            consulta.setInt(1, asignarSolicitudregistra.getId_usuario_personal().getIdUsuario());
            consulta.setInt(2, asignarSolicitudregistra.getId_solicitud().getIdsolicitud_mc());
            consulta.setInt(3, asignarSolicitudregistra.getId_usuario_personal_jefe().getIdUsuario());
            consulta.setDate(4, (Date) asignarSolicitudregistra.getFecha());
           
            
            consulta.executeUpdate();
        } catch (Exception e) {
            System.out.println("error en AsignarSOLICITUD    DAO -->RegistrarAsignarSolicitar" + "/n" + e);
            // System.out.println("error en DAO"); 
        } finally {
            this.Cerrar();
        }
    }
     //esta se sustituira por otra para mostrar cuales de las asignaciones ya fueron terminadas y cuales no
  /*  public List<Asigna_solicitud> listarAsignarSolicitud() throws Exception {
        List<Asigna_solicitud> lista;
        ResultSet resultadoList;
        try {
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareCall("SELECT * FROM asigna_solicitud");
            resultadoList = consulta.executeQuery();
            lista = new ArrayList();
            while (resultadoList.next()) {
                Asigna_solicitud asignar_Solicitud = new Asigna_solicitud();

                asignar_Solicitud.setIdasigna_solicitud(resultadoList.getInt("idasigna_solicitud"));
                asignar_Solicitud.setId_usuario_personal(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoList.getInt("id_usuario_personal")));
                asignar_Solicitud.setId_solicitud(new Solicitud_mcDAO().buscarDeSolicitudEntero(resultadoList.getInt("id_solicitud")));
                asignar_Solicitud.setId_usuario_personal_jefe(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoList.getInt("id_usuario_personal_jefe")));
                asignar_Solicitud.setFecha(resultadoList.getDate("fecha_asignada"));
                
                lista.add(asignar_Solicitud);
            }

        } catch (Exception e) {
            System.out.println("error en AsignaSolicitudDao metodo Listar" + e);
            throw e;

        } finally {
            this.Cerrar();
        }
        return lista;
    }*/
    //aqui se mostrara una lista donde se muestre las activas y las terminadas
   public List<Asigna_solicitud> listarTodasAsignaciones() throws Exception {
        List<Asigna_solicitud> lista;
        ResultSet resultadoList;
        try {
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareCall("SELECT * FROM asigna_solicitud inner join solicitud_mc on id_solicitud=idsolicitud_mc order by estado_seguimiento desc");
            resultadoList = consulta.executeQuery();
            lista = new ArrayList();
            while (resultadoList.next()) {
                Asigna_solicitud asignar_Solicitud = new Asigna_solicitud();

                asignar_Solicitud.setIdasigna_solicitud(resultadoList.getInt("idasigna_solicitud"));
                asignar_Solicitud.setId_usuario_personal(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoList.getInt("id_usuario_personal")));
                asignar_Solicitud.setId_solicitud(new Solicitud_mcDAO().buscarDeSolicitudEntero(resultadoList.getInt("id_solicitud")));
                asignar_Solicitud.setId_usuario_personal_jefe(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoList.getInt("id_usuario_personal_jefe")));
                asignar_Solicitud.setFecha(resultadoList.getDate("fecha_asignada"));
                
                lista.add(asignar_Solicitud);
            }

        } catch (Exception e) {
            System.out.println("error en ListarTodasLasAsignaciones metodo Listar" + e);
            throw e;

        } finally {
            this.Cerrar();
        }
        return lista;
    }
    
    
    
         public List<Asigna_solicitud> buscarAsignacionPorIdUsuario(int idUsuario) throws Exception{
         
         List<Asigna_solicitud> listaAsignasionDeUsuario = null;
         ResultSet resultadoConsulta;
         try{
             this.Conectar();
             PreparedStatement consulta = this.getConexion().prepareCall("SELECT * FROM asigna_solicitud inner join solicitud_mc on id_solicitud=idsolicitud_mc where id_usuario_personal=? and estado_seguimiento=true");
             consulta.setInt(1, idUsuario);
             resultadoConsulta = consulta.executeQuery();
             listaAsignasionDeUsuario = new ArrayList();
             while(resultadoConsulta.next()){
             Asigna_solicitud asignaSolicitud = new Asigna_solicitud();
             asignaSolicitud.setIdasigna_solicitud(resultadoConsulta.getInt("idasigna_solicitud"));
             asignaSolicitud.setId_usuario_personal(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoConsulta.getInt("id_usuario_personal")));
             asignaSolicitud.setId_solicitud(new Solicitud_mcDAO().buscarDeSolicitudEntero(resultadoConsulta.getInt("id_solicitud")));
             asignaSolicitud.setId_usuario_personal_jefe(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoConsulta.getInt("id_usuario_personal_jefe")));
             asignaSolicitud.setFecha(resultadoConsulta.getDate("fecha_asignada"));
             
             listaAsignasionDeUsuario.add(asignaSolicitud);
              }
              resultadoConsulta.close();
              
         }catch(Exception ex){
             System.out.println("Error en Lista Asignacion por UsuarioDAO -> buscar Asignacion por Usuario "+ex);
             throw ex;
         }finally{
             this.Cerrar();
         }
         return listaAsignasionDeUsuario;
     }
         
    public List<Asigna_solicitud> buscarAsignacionTerminadasPorIdUsuario(int idUsuario) throws Exception{
         
         List<Asigna_solicitud> listaAsignasionDeUsuario = null;
         ResultSet resultadoConsulta;
         try{
             this.Conectar();
             PreparedStatement consulta = this.getConexion().prepareCall("SELECT * FROM asigna_solicitud inner join solicitud_mc on id_solicitud=idsolicitud_mc where id_usuario_personal=? and estado_seguimiento=false");
             consulta.setInt(1, idUsuario);
             resultadoConsulta = consulta.executeQuery();
             listaAsignasionDeUsuario = new ArrayList();
             while(resultadoConsulta.next()){
             Asigna_solicitud asignaSolicitud = new Asigna_solicitud();
             asignaSolicitud.setIdasigna_solicitud(resultadoConsulta.getInt("idasigna_solicitud"));
             asignaSolicitud.setId_usuario_personal(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoConsulta.getInt("id_usuario_personal")));
             asignaSolicitud.setId_solicitud(new Solicitud_mcDAO().buscarDeSolicitudEntero(resultadoConsulta.getInt("id_solicitud")));
             asignaSolicitud.setId_usuario_personal_jefe(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoConsulta.getInt("id_usuario_personal_jefe")));
             asignaSolicitud.setFecha(resultadoConsulta.getDate("fecha_asignada"));
             
             listaAsignasionDeUsuario.add(asignaSolicitud);
              }
              resultadoConsulta.close();
              
         }catch(Exception ex){
             System.out.println("Error en Lista Asignacion por UsuarioDAO -> buscar Asignacion por Usuario "+ex);
             throw ex;
         }finally{
             this.Cerrar();
         }
         return listaAsignasionDeUsuario;
     } 
         
    
      
}
