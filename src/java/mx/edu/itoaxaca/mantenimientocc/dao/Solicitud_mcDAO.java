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
import mx.edu.itoaxaca.mantenimientocc.modelo.SeguimientoCliente;
import mx.edu.itoaxaca.mantenimientocc.modelo.Solicitud_mc;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;

/**
 *
 * @author leiver
 */
public class Solicitud_mcDAO extends Conexion {

    public boolean registrarSolicitudMC(Solicitud_mc solicitudmc) throws Exception{

        try {
            this.Conectar();
            PreparedStatement inserta = this.getConexion().prepareStatement("INSERT INTO solicitud_mc(id_usuario, folio, fecha, id_departamento, otro_problema,estatus,estado_seguimiento) VALUES(?,?,?,?,?,?,?)");
            inserta.setInt(1, solicitudmc.getId_usuario().getIdUsuario());
            inserta.setString(2, solicitudmc.getFolio());
            inserta.setDate(3, (Date) solicitudmc.getFecha());
            inserta.setInt(4, solicitudmc.getId_departamento().getIddepartamento());
            inserta.setString(5, solicitudmc.getOtroProblema());
            inserta.setBoolean(6, true);
            inserta.setBoolean(7, true);
            inserta.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Error en Solicitud_MCDAO -> generarSolicitudMC " + ex);
             return false; 
        } finally {
            this.Cerrar();
        }
            
    }
    public void modificarSolicitudMC(Solicitud_mc solicitudmc) throws Exception{//para estado de asignacion y el estado seguimiento aun no cambia
        try{
            this.Conectar();
            PreparedStatement inserta = this.getConexion().prepareStatement("UPDATE solicitud_mc SET id_usuario=?, folio=?, fecha=?, id_departamento=?, otro_problema=?, estatus=?, estado_seguimiento=? WHERE idsolicitud_mc=?");
            inserta.setInt(1, solicitudmc.getId_usuario().getIdUsuario());
            inserta.setString(2, solicitudmc.getFolio());
            inserta.setDate(3, (Date) solicitudmc.getFecha());
            inserta.setInt(4, solicitudmc.getId_departamento().getIddepartamento());
            inserta.setString(5, solicitudmc.getOtroProblema());
            inserta.setBoolean(6, false);
            inserta.setBoolean(7, true);
            inserta.setInt(8,solicitudmc.getIdsolicitud_mc());
            inserta.executeUpdate();
        }catch(Exception ex){
            System.out.println("Error en modificarSolicitudMC -> Solicitud_mcDAO");
            throw ex;
            
        }finally{
            this.Cerrar();
        }
    }
    public void modificarSolicitudSeguimiento(Solicitud_mc solicitudmc) throws Exception{//el estado seguimiento cambia porque se ha realizado en orden de trabajo
        try{
            this.Conectar();
            PreparedStatement inserta = this.getConexion().prepareStatement("UPDATE solicitud_mc SET id_usuario=?, folio=?, fecha=?, id_departamento=?, otro_problema=?, estatus=?, estado_seguimiento=? WHERE idsolicitud_mc=?");
            inserta.setInt(1, solicitudmc.getId_usuario().getIdUsuario());
            inserta.setString(2, solicitudmc.getFolio());
            inserta.setDate(3, (Date) solicitudmc.getFecha());
            inserta.setInt(4, solicitudmc.getId_departamento().getIddepartamento());
            inserta.setString(5, solicitudmc.getOtroProblema());
            inserta.setBoolean(6, false);
            inserta.setBoolean(7, false);
            inserta.setInt(8,solicitudmc.getIdsolicitud_mc());
            inserta.executeUpdate();
        }catch(Exception ex){
            System.out.println("Error en modificarSolicitudSeguimiento -> Solicitud_mcDAO");
            throw ex;
            
        }finally{
            this.Cerrar();
        }
    }
    public int indiceDeSolicitud(int idDepartamento) throws Exception {//para saber que numero de solicitud que se usa para sacar el folio
        ResultSet resultado;
        int numeroParaFolio = 0;
        try {
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("SELECT COUNT(idsolicitud_mc) AS numSolicitud FROM solicitud_mc WHERE id_departamento=?");
            consulta.setInt(1, idDepartamento);
            resultado = consulta.executeQuery();
            if (resultado.next() == true) {
                numeroParaFolio = resultado.getInt("numSolicitud");
            }
        } catch (Exception ex) {
            System.out.println("Error en Solicitud_MCDAO -> indiceDeSolicitud " + ex);
            throw ex;
        } finally {
            this.Cerrar();
        }
        return numeroParaFolio + 1;
    }

    public Solicitud_mc identificadorDeSolicitud(String folio) throws Exception {//me manda a traer un objeto de solicitud que te regresa la solicitud del folio pedido
        ResultSet resultado;
        Solicitud_mc solicitud = null;
        try {
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("SELECT * FROM solicitud_mc WHERE folio=?");
            consulta.setString(1, folio);
            resultado = consulta.executeQuery();
            if (resultado.next() == true) {
                solicitud = new Solicitud_mc();
                solicitud.setIdsolicitud_mc(resultado.getInt("idsolicitud_mc"));
                solicitud.setFolio(resultado.getString("folio"));
                solicitud.setFecha(resultado.getDate("fecha"));
                solicitud.setOtroProblema(resultado.getString("otro_problema"));
                solicitud.setId_usuario(new UsuarioDAO().consultarUsuarioPorIdEntero(resultado.getInt("id_usuario")));
                solicitud.setId_departamento(new DepartamentoDAO().buscarIdDepartamento(resultado.getInt("id_departamento")));
                solicitud.setEstatus(resultado.getBoolean("estatus"));
                solicitud.setEstado_seguimiento(resultado.getBoolean("estado_seguimiento"));
            }
            return solicitud;
        } catch (Exception ex) {
            throw ex;
        } finally {
            this.Cerrar();
        }
    }

    public Solicitud_mc buscarDeSolicitudEntero(int idSolicitud_mc) throws Exception {//esto es para que en orden interna se listen en un combo todos los folios pero ya no se ocupo al final por lo que se toma ahora de mis aignaciones
        ResultSet resultado;
        Solicitud_mc solicitud = null;
        try {
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("SELECT * FROM solicitud_mc WHERE idsolicitud_mc=?");
            consulta.setInt(1, idSolicitud_mc);
            resultado = consulta.executeQuery();
            if (resultado.next() == true) {
                solicitud = new Solicitud_mc();
                solicitud.setIdsolicitud_mc(resultado.getInt("idsolicitud_mc"));
                solicitud.setFolio(resultado.getString("folio"));
                solicitud.setFecha(resultado.getDate("fecha"));
                solicitud.setOtroProblema(resultado.getString("otro_problema"));
                solicitud.setId_usuario(new UsuarioDAO().consultarUsuarioPorIdEntero(resultado.getInt("id_usuario")));
                solicitud.setId_departamento(new DepartamentoDAO().buscarIdDepartamento(resultado.getInt("id_departamento")));
                solicitud.setEstatus(resultado.getBoolean("estatus"));
                solicitud.setEstado_seguimiento(resultado.getBoolean("estado_seguimiento"));
            }
            return solicitud;
        } catch (Exception ex) {
            throw ex;
        } finally {
            this.Cerrar();
        }
    }

    public List<Solicitud_mc> listarSolicitudMC() throws Exception {
        List<Solicitud_mc> lista;
        ResultSet resultadoList;
        try {
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareCall("SELECT * FROM solicitud_mc");
            resultadoList = consulta.executeQuery();
            lista = new ArrayList();
            while (resultadoList.next()) {
                Solicitud_mc solicitudMC = new Solicitud_mc();

                solicitudMC.setIdsolicitud_mc(resultadoList.getInt("idsolicitud_mc"));
                solicitudMC.setId_usuario(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoList.getInt("id_usuario")));
                solicitudMC.setFolio(resultadoList.getString("folio"));
                solicitudMC.setFecha(resultadoList.getDate("fecha"));
                solicitudMC.setOtroProblema(resultadoList.getString("otro_problema"));
                solicitudMC.setId_departamento(new DepartamentoDAO().buscarIdDepartamento(resultadoList.getInt("id_departamento")));
                solicitudMC.setEstatus(resultadoList.getBoolean("estatus"));
                solicitudMC.setEstado_seguimiento(resultadoList.getBoolean("estado_seguimiento"));

                lista.add(solicitudMC);
            }

        } catch (Exception e) {
            System.out.println("error en SolicitudMC  Dao metodo Listar" + e);
            throw e;

        } finally {
            this.Cerrar();
        }
        return lista;
    }
    
    
    public List<Solicitud_mc> listarSolicitudPorDepartamentoUsuario(Usuario usuario) throws Exception{  //segundo uso de este metodo,se reutilizo para buscar el departamento de solicitud para generar asignacion 
        List<Solicitud_mc> lista;
        ResultSet resultadoList;
        try {
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareCall("SELECT * FROM solicitud_mc where id_departamento=?  order by estatus desc");
            consulta.setInt(1, usuario.getIdOficina().getDepartamento().getIddepartamento());
            resultadoList = consulta.executeQuery();
            lista = new ArrayList();
            while (resultadoList.next()) {
                Solicitud_mc solicitudMC = new Solicitud_mc();
                solicitudMC.setIdsolicitud_mc(resultadoList.getInt("idsolicitud_mc"));
                solicitudMC.setId_usuario(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoList.getInt("id_usuario")));
                solicitudMC.setFolio(resultadoList.getString("folio"));
                solicitudMC.setFecha(resultadoList.getDate("fecha"));
                solicitudMC.setOtroProblema(resultadoList.getString("otro_problema"));
                solicitudMC.setId_departamento(new DepartamentoDAO().buscarIdDepartamento(resultadoList.getInt("id_departamento")));
                solicitudMC.setEstatus(resultadoList.getBoolean("estatus"));
                solicitudMC.setEstado_seguimiento(resultadoList.getBoolean("estado_seguimiento"));
                lista.add(solicitudMC);
            }

        } catch (Exception e) {
            System.out.println("error en Solicitud_mcDAO -> ListarSolicitudPorDepartamentoUsuario " + e);
            throw e;

        } finally {
            this.Cerrar();
        }
        return lista;
    }
    
    public List<Solicitud_mc> listarSolicitudPorDepartamentoUsuarioEnAsignacion(Usuario usuario) throws Exception{ //usado en Asignas Solicitud en asignaSolicitud
        List<Solicitud_mc> lista;
        ResultSet resultadoList;
        try {
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareCall("SELECT * FROM solicitud_mc where id_departamento=? and estatus = true");
            consulta.setInt(1, usuario.getIdOficina().getDepartamento().getIddepartamento());
            resultadoList = consulta.executeQuery();
            lista = new ArrayList();
            while (resultadoList.next()) {
                Solicitud_mc solicitudMC = new Solicitud_mc();
                solicitudMC.setIdsolicitud_mc(resultadoList.getInt("idsolicitud_mc"));
                solicitudMC.setId_usuario(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoList.getInt("id_usuario")));
                solicitudMC.setFolio(resultadoList.getString("folio"));
                solicitudMC.setFecha(resultadoList.getDate("fecha"));
                solicitudMC.setOtroProblema(resultadoList.getString("otro_problema"));
                solicitudMC.setId_departamento(new DepartamentoDAO().buscarIdDepartamento(resultadoList.getInt("id_departamento")));
                solicitudMC.setEstatus(resultadoList.getBoolean("estatus"));
                solicitudMC.setEstado_seguimiento(resultadoList.getBoolean("estado_seguimiento"));
                lista.add(solicitudMC);
            }

        } catch (Exception e) {
            System.out.println("error en Solicitud_mcDAO -> ListarSolicitudPorDepartamentoUsuario " + e);
            throw e;

        } finally {
            this.Cerrar();
        }
        return lista;
    }
    /*lista de solicitud que tienen un seguimiento activo*/
    public List<Solicitud_mc> listarSolicitudPorDepartamentoUsuarioEnSeguimiento(Usuario usuario) throws Exception{ //usado en Asignas Solicitud en asignaSolicitud
        List<Solicitud_mc> lista;
        ResultSet resultadoList;
        try {
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareCall("SELECT * FROM solicitud_mc where estado_seguimiento = true and id_usuario=?");
            consulta.setInt(1, usuario.getIdUsuario());
            resultadoList = consulta.executeQuery();
            lista = new ArrayList();
            while (resultadoList.next()) {
                Solicitud_mc solicitudMC = new Solicitud_mc();
                solicitudMC.setIdsolicitud_mc(resultadoList.getInt("idsolicitud_mc"));
                solicitudMC.setId_usuario(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoList.getInt("id_usuario")));
                solicitudMC.setFolio(resultadoList.getString("folio"));
                solicitudMC.setFecha(resultadoList.getDate("fecha"));
                solicitudMC.setOtroProblema(resultadoList.getString("otro_problema"));
                solicitudMC.setId_departamento(new DepartamentoDAO().buscarIdDepartamento(resultadoList.getInt("id_departamento")));
                solicitudMC.setEstatus(resultadoList.getBoolean("estatus"));
                solicitudMC.setEstado_seguimiento(resultadoList.getBoolean("estado_seguimiento"));
                lista.add(solicitudMC);
            }
        } catch (Exception e) {
            System.out.println("error en Solicitud_mcDAO -> ListarSolicitudPorDepartamentoUsuario " + e);
            throw e;

        } finally {
            this.Cerrar();
        }
        return lista;
    }
    /*PENDIENTE POR USAR ESTO ES POR SI AFUERZA SE NOS PIDE QUE MOSTREMOS LOS ESTADOS
    public List<SeguimientoCliente> listarSolicitudPorDepartamentoUsuarioEnSeguimiento(Usuario usuario) throws Exception{ //usado en Asignas Solicitud en asignaSolicitud
        List<SeguimientoCliente> lista;
        ResultSet resultadoList;
        try {
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareCall("SELECT * FROM solicitud_mc where estado_seguimiento = true and id_usuario=?");
            consulta.setInt(1, usuario.getIdUsuario());
            resultadoList = consulta.executeQuery();
            lista = new ArrayList();
            while (resultadoList.next()) {
                SeguimientoCliente solicitudMC = new SeguimientoCliente();
                solicitudMC.setIdsolicitud_mc(resultadoList.getInt("idsolicitud_mc"));
                solicitudMC.setId_usuario(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoList.getInt("id_usuario")));
                solicitudMC.setFolio(resultadoList.getString("folio"));
                solicitudMC.setFecha(resultadoList.getDate("fecha"));
                solicitudMC.setOtroProblema(resultadoList.getString("otro_problema"));
                solicitudMC.setId_departamento(new DepartamentoDAO().buscarIdDepartamento(resultadoList.getInt("id_departamento")));
                solicitudMC.setEstatus(resultadoList.getBoolean("estatus"));
                solicitudMC.setEstado_seguimiento(resultadoList.getBoolean("estado_seguimiento"));
                lista.add(solicitudMC);
            }
        } catch (Exception e) {
            System.out.println("error en Solicitud_mcDAO -> ListarSolicitudPorDepartamentoUsuario " + e);
            throw e;

        } finally {
            this.Cerrar();
        }
        return lista;
    }
    
    */
    
    
    public List<Solicitud_mc> buscarSolucitudPorIdUsuario(int idUsuario) throws Exception{
         List<Solicitud_mc> listaSolicitudDeUsuario = null;
         ResultSet resultadoConsulta;
         try{
             this.Conectar();
             PreparedStatement consulta = this.getConexion().prepareCall("SELECT * FROM solicitud_mc where id_usuario=?");
             consulta.setInt(1, idUsuario);
             resultadoConsulta = consulta.executeQuery();
             listaSolicitudDeUsuario = new ArrayList();
             while(resultadoConsulta.next()){
             Solicitud_mc misolicitud = new Solicitud_mc();
            misolicitud.setIdsolicitud_mc(resultadoConsulta.getInt("idsolicitud_mc"));
            misolicitud.setId_usuario(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoConsulta.getInt("id_usuario")));
            misolicitud.setFolio(resultadoConsulta.getString("folio"));
            misolicitud.setFecha(resultadoConsulta.getDate("fecha"));
            misolicitud.setOtroProblema(resultadoConsulta.getString("otro_problema"));
            misolicitud.setId_departamento(new DepartamentoDAO().buscarIdDepartamento(resultadoConsulta.getInt("id_departamento")));
            misolicitud.setEstatus(resultadoConsulta.getBoolean("estatus"));
            misolicitud.setEstado_seguimiento(resultadoConsulta.getBoolean("estado_seguimiento"));
             listaSolicitudDeUsuario.add(misolicitud);
              }
              resultadoConsulta.close();
              
         }catch(Exception ex){
             System.out.println("Error en Lista Solicitudes por UsuarioDAO -> buscar Solicitud por Usuario "+ex);
             throw ex;
         }finally{
             this.Cerrar();
         }
         return listaSolicitudDeUsuario;
     }
    
    public Solicitud_mc elegirDatoSolicitudParaModificarEstado_Seguimiento(Solicitud_mc solicitudEstadoSeguimiento) throws Exception{
        Solicitud_mc solicituddos=null;
        ResultSet resultadoset;
        
        try{
            this.Conectar();
             PreparedStatement consulta= this.getConexion().prepareStatement("SELECT * FROM solicitud_mc WHERE idsolicitud_mc=?");
            consulta.setInt(1, solicitudEstadoSeguimiento.getIdsolicitud_mc());
            resultadoset = consulta.executeQuery();
            while(resultadoset.next())
            {
              solicituddos= new Solicitud_mc();
           solicituddos.setIdsolicitud_mc(resultadoset.getInt("idsolicitud_mc"));
            solicituddos.setId_usuario(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoset.getInt("id_usuario")));
            solicituddos.setFolio(resultadoset.getString("folio"));
            solicituddos.setFecha(resultadoset.getDate("fecha"));
            solicituddos.setOtroProblema(resultadoset.getString("otro_problema"));
            solicituddos.setId_departamento(new DepartamentoDAO().buscarIdDepartamento(resultadoset.getInt("id_departamento")));
            solicituddos.setEstatus(resultadoset.getBoolean("estatus"));
           solicituddos.setEstado_seguimiento(resultadoset.getBoolean("estado_seguimiento"));
            }
        }
        catch(Exception e){
           throw e; 
        }
        finally{
           this.Cerrar();
        }
        return solicituddos;
    }
    
    //esto es para la parte de mis seguimiento se reutiliza la de mis asignaciones solo q esta mostrara si estan activas o ya no las solicitudes
    
    public List<Solicitud_mc> buscarSolucitudPorIdUsuarioParaMisSeguimientos(int idUsuario) throws Exception{
         List<Solicitud_mc> listaSolicitudDeUsuario = null;
         ResultSet resultadoConsulta;
         try{
             this.Conectar();
             PreparedStatement consulta = this.getConexion().prepareCall("SELECT * FROM solicitud_mc where id_usuario=? and estado_seguimiento=false");
             consulta.setInt(1, idUsuario);
             resultadoConsulta = consulta.executeQuery();
             listaSolicitudDeUsuario = new ArrayList();
             while(resultadoConsulta.next()){
             Solicitud_mc misolicitud = new Solicitud_mc();
            misolicitud.setIdsolicitud_mc(resultadoConsulta.getInt("idsolicitud_mc"));
            misolicitud.setId_usuario(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoConsulta.getInt("id_usuario")));
            misolicitud.setFolio(resultadoConsulta.getString("folio"));
            misolicitud.setFecha(resultadoConsulta.getDate("fecha"));
            misolicitud.setOtroProblema(resultadoConsulta.getString("otro_problema"));
            misolicitud.setId_departamento(new DepartamentoDAO().buscarIdDepartamento(resultadoConsulta.getInt("id_departamento")));
            misolicitud.setEstatus(resultadoConsulta.getBoolean("estatus"));
            misolicitud.setEstado_seguimiento(resultadoConsulta.getBoolean("estado_seguimiento"));
             listaSolicitudDeUsuario.add(misolicitud);
              }
              resultadoConsulta.close();
              
         }catch(Exception ex){
             System.out.println("Error en Lista Solicitudes por UsuarioDAO -> buscar Solicitud por Usuario "+ex);
             throw ex;
         }finally{
             this.Cerrar();
         }
         return listaSolicitudDeUsuario;
     }
      
        
}
