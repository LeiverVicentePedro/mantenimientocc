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
import mx.edu.itoaxaca.mantenimientocc.modelo.Orden_interna;
import mx.edu.itoaxaca.mantenimientocc.modelo.Solicitud_mc;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;

/**
 *
 * @author Jerusalen
 */
public class Orden_internaDAO extends Conexion {

    public void registrarOrdenInterna(Orden_interna ordenInternaregistra) throws Exception {
        try {
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("INSERT INTO orden_interna (nombre_orden,id_usuario_personal,fecha_entrada,reporte_fallo,reporte_tecnico,posible_causa,id_solicitudmc,se_recibe) values(?,?,?,?,?,?,?,?)");
            consulta.setString(1, ordenInternaregistra.getNombre_orden());

            consulta.setInt(2, ordenInternaregistra.getId_usuario_personal().getIdUsuario());
            consulta.setDate(3, (Date) ordenInternaregistra.getFecha());
            consulta.setString(4, ordenInternaregistra.getReporte_fallo());
            consulta.setString(5, ordenInternaregistra.getReporte_tecnico());
            consulta.setString(6, ordenInternaregistra.getPosible_causa());
            consulta.setInt(7, ordenInternaregistra.getIdsolicitud().getIdsolicitud_mc());
            consulta.setString(8,ordenInternaregistra.getSe_recibe());
            consulta.executeUpdate();
        } catch (Exception e) {
            System.out.println("error en OrdenInterna DAO -->RegistrarOrdenInterna" + "/n" + e);
            // System.out.println("error en DAO"); 
        } finally {
            this.Cerrar();
        }
    }

    public Orden_interna identificadorDeOrden(Solicitud_mc idSolicitud) throws Exception {//me manda a traer un objeto de solicitud que te regresa la solicitud del folio pedido
        ResultSet resultado;
        Orden_interna orden = null;
        try {
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("SELECT * FROM orden_interna WHERE id_solicitudmc=?");
            consulta.setInt(1, idSolicitud.getIdsolicitud_mc());
            resultado = consulta.executeQuery();
            if (resultado.next() == true) {
                orden = new Orden_interna();
                orden.setIdorden_interna(resultado.getInt("idorden_interna"));
                orden.setNombre_orden(resultado.getString("nombre_orden"));
                orden.setId_usuario_personal(new UsuarioDAO().consultarUsuarioPorIdEntero(resultado.getInt("id_usuario_personal")));
                orden.setFecha(resultado.getDate("fecha_entrada"));
                orden.setReporte_fallo(resultado.getString("reporte_fallo"));
                orden.setReporte_tecnico(resultado.getString("reporte_tecnico"));
                orden.setPosible_causa(resultado.getString("posible_causa"));
                orden.setIdsolicitud(new Solicitud_mcDAO().buscarDeSolicitudEntero(resultado.getInt("id_solicitudmc")));
                orden.setSe_recibe(resultado.getString("se_recibe"));
            }
            return orden;
        } catch (Exception ex) {
            throw ex;
        } finally {
            this.Cerrar();
        }
    }

    public List<Orden_interna> listarOrden_interna() throws Exception {
        List<Orden_interna> lista;
        ResultSet resultadoList;
        try {
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareCall("SELECT * FROM orden_interna");
            resultadoList = consulta.executeQuery();
            lista = new ArrayList();
            while (resultadoList.next()) {
                Orden_interna orden_interna = new Orden_interna();

                orden_interna.setIdorden_interna(resultadoList.getInt("idorden_interna"));
                orden_interna.setNombre_orden(resultadoList.getString("nombre_orden"));
                orden_interna.setId_usuario_personal(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoList.getInt("id_usuario_personal")));
                orden_interna.setFecha(resultadoList.getDate("fecha_entrada"));
                orden_interna.setReporte_fallo(resultadoList.getString("reporte_fallo"));
                orden_interna.setReporte_tecnico(resultadoList.getString("reporte_tecnico"));
                orden_interna.setPosible_causa(resultadoList.getString("posible_causa"));
                orden_interna.setIdsolicitud(new Solicitud_mcDAO().buscarDeSolicitudEntero(resultadoList.getInt("id_solicitudmc")));
                orden_interna.setSe_recibe(resultadoList.getString("se_recibe"));

                lista.add(orden_interna);
            }

        } catch (Exception e) {
            System.out.println("error en orden_internaDao metodo Listar" + e);
            throw e;

        } finally {
            this.Cerrar();
        }
        return lista;
    }

    public Orden_interna buscarIdOrden_interna(int idOrden_interna) throws Exception {
        Orden_interna orden_interna = new Orden_interna();
        ResultSet resultadoConsulta;
        try {
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareCall("SELECT * FROM orden_interna where idorden_interna=?");
            consulta.setInt(1, idOrden_interna);
            resultadoConsulta = consulta.executeQuery();
            if (resultadoConsulta.next()) {
                orden_interna.setIdorden_interna(resultadoConsulta.getInt("idorden_interna"));
                orden_interna.setNombre_orden(resultadoConsulta.getString("nombre_orden"));
                orden_interna.setId_usuario_personal(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoConsulta.getInt("id_usuario_personal")));
                orden_interna.setFecha(resultadoConsulta.getDate("fecha_entrada"));
                orden_interna.setReporte_fallo(resultadoConsulta.getString("reporte_fallo"));
                orden_interna.setReporte_tecnico(resultadoConsulta.getString("reporte_tecnico"));
                orden_interna.setPosible_causa(resultadoConsulta.getString("posible_causa"));
                orden_interna.setIdsolicitud(new Solicitud_mcDAO().buscarDeSolicitudEntero(resultadoConsulta.getInt("id_solicitudmc")));
                orden_interna.setSe_recibe(resultadoConsulta.getString("se_recibe"));

            }
            resultadoConsulta.close();

        } catch (Exception ex) {
            System.out.println("Error en Orden_internaDAO -> buscarIdOrden_Interna " + ex);
            throw ex;
        } finally {
            this.Cerrar();
        }
        return orden_interna;
    }

}
