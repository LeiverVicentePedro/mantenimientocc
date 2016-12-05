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
import mx.edu.itoaxaca.mantenimientocc.modelo.Solicitud_mc;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;

/**
 *
 * @author leiver
 */
public class Solicitud_mcDAO extends Conexion {

    public boolean registrarSolicitudMC(Solicitud_mc solicitudmc) throws Exception {

        try {
            this.Conectar();
            PreparedStatement inserta = this.getConexion().prepareStatement("INSERT INTO solicitud_mc(id_usuario, folio, fecha, id_departamento, otro_problema) VALUES(?,?,?,?,?)");
            inserta.setInt(1, solicitudmc.getId_usuario().getIdUsuario());
            inserta.setString(2, solicitudmc.getFolio());
            inserta.setDate(3, (Date) solicitudmc.getFecha());
            inserta.setInt(4, solicitudmc.getId_departamento().getIddepartamento());
            inserta.setString(5, solicitudmc.getOtroProblema());
            inserta.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Error en Solicitud_MCDAO -> generarSolicitudMC " + ex);
            throw ex;
        } finally {
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
            }
            return solicitud;
        } catch (Exception ex) {
            throw ex;
        } finally {
            this.Cerrar();
        }
    }

    public Solicitud_mc buscarDeSolicitudEntero(int idSolicitud_mc) throws Exception {
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
    
    
    public List<Solicitud_mc> listarSoicitudPorDepartamentoUsuario(Usuario usuario) throws Exception{
        List<Solicitud_mc> lista;
        ResultSet resultadoList;
        try {
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareCall("SELECT * FROM solicitud_mc where id_departamento=?");
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
     //Esto es para hacer la seleccion de solicitud que sea solo por centro de computo   
    public List<Solicitud_mc> listarSolicitudesCentroComputo() throws Exception{
         List<Solicitud_mc> listaSolicitudesCC=null;
         ResultSet resultado;
         try{
             this.Conectar();
             PreparedStatement consulta = this.getConexion().prepareStatement("Select * from solicitud_mc where id_departamento=2 ");
             resultado = consulta.executeQuery();
             listaSolicitudesCC = new ArrayList();
             while(resultado.next()){
                 Solicitud_mc solicitudesCC= new Solicitud_mc();
                 solicitudesCC.setIdsolicitud_mc(resultado.getInt("idsolicitud_mc"));
             solicitudesCC.setId_usuario(new UsuarioDAO().consultarUsuarioPorIdEntero(resultado.getInt("id_usuario")));
             solicitudesCC  .setFolio(resultado.getString("folio"));
             solicitudesCC.setFecha(resultado.getDate("fecha"));
             solicitudesCC.setOtroProblema(resultado.getString("otro_problema"));
             solicitudesCC.setId_departamento(new DepartamentoDAO().buscarIdDepartamento(resultado.getInt("id_departamento")));
                 listaSolicitudesCC.add(solicitudesCC);
            }
             
            return listaSolicitudesCC; 
         }catch(Exception ex){
             System.out.println("Error en SolicitudDAO -> lista SOLICITUD "+ex);
             throw ex;
         }finally{
             this.Cerrar();
         }
     }
        
}
