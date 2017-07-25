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
import mx.edu.itoaxaca.mantenimientocc.modelo.Orden_trabajo;
import mx.edu.itoaxaca.mantenimientocc.modelo.Solicitud_mc;

/**
 *
 * @author Jerusalen
 */
public class Orden_trabajoDAO extends Conexion {
    
     public void registrarOrdenTrabajo(Orden_trabajo ordenTrabajoregistra) throws Exception {
        try {
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("INSERT INTO orden_trabajo (mantenimiento_tipo,tipo_servicio,id_usuario_personal,fecha_realizacion,trabajo_descripcion,id_usuario_personal_jefe,id_solicitudmc) values(?,?,?,?,?,?,?)");
            consulta.setString(1, ordenTrabajoregistra.getMantenimiento_tipo());
            consulta.setString(2,ordenTrabajoregistra.getTipo_servicio());
            consulta.setInt(3, ordenTrabajoregistra.getId_usuario_personal().getIdUsuario());
            consulta.setDate(4, (Date) ordenTrabajoregistra.getFecha_realizacion());
            consulta.setString(5, ordenTrabajoregistra.getTrabajo_descripcion());
            consulta.setInt(6, ordenTrabajoregistra.getId_usuario_personal_jefe().getIdUsuario());
            consulta.setInt(7, ordenTrabajoregistra.getId_solicitudmc().getIdsolicitud_mc());
            consulta.executeUpdate();
        } catch (Exception e) {
            System.out.println("error en OrdenTrabajo DAO -->RegistrarOrdenTrabajo" + "/n" + e);
            // System.out.println("error en DAO"); 
        } finally {
            this.Cerrar();
        }
    }
     
      public Orden_trabajo identificadorDeOrdenTrabajo(Solicitud_mc idSolicitud) throws Exception {//me manda a traer un objeto de solicitud que te regresa la solicitud del folio pedido
        ResultSet resultado;
        Orden_trabajo orden = null;
        try {
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("SELECT * FROM orden_trabajo WHERE id_solicitudmc=?");
            consulta.setInt(1, idSolicitud.getIdsolicitud_mc());
            resultado = consulta.executeQuery();
            if (resultado.next() == true) {
                orden = new Orden_trabajo();
                orden.setIdorden_trabajo(resultado.getInt("idorden_trabajo"));
                orden.setMantenimiento_tipo(resultado.getString("mantenimiento_tipo"));
                orden.setTipo_servicio(resultado.getString("tipo_servicio"));
                orden.setId_usuario_personal(new UsuarioDAO().consultarUsuarioPorIdEntero(resultado.getInt("id_usuario_personal")));
                orden.setFecha_realizacion(resultado.getDate("fecha_realizacion"));
                orden.setTrabajo_descripcion(resultado.getString("trabajo_descripcion"));
                orden.setId_usuario_personal_jefe(new UsuarioDAO().consultarUsuarioPorIdEntero(resultado.getInt("id_usuario_personal")));
                orden.setId_solicitudmc(new Solicitud_mcDAO().buscarDeSolicitudEntero(resultado.getInt("id_solicitudmc")));
                
            }
            return orden;
        } catch (Exception ex) {
            throw ex;
        } finally {
            this.Cerrar();
        }
    }
      
    public List<Orden_trabajo> listarOrden_trabajo() throws Exception {
        List<Orden_trabajo> lista;
        ResultSet resultadoList;
        try {
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareCall("SELECT * FROM orden_trabajo");
            resultadoList = consulta.executeQuery();
            lista = new ArrayList();
            while (resultadoList.next()) {
                Orden_trabajo orden_trabajo = new Orden_trabajo();

                orden_trabajo.setIdorden_trabajo(resultadoList.getInt("idorden_trabajo"));
                orden_trabajo.setMantenimiento_tipo(resultadoList.getString("mantenimiento_tipo"));
                orden_trabajo.setTipo_servicio(resultadoList.getString("tipo_servicio"));
                orden_trabajo.setId_usuario_personal(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoList.getInt("id_usuario_personal")));
                orden_trabajo.setFecha_realizacion(resultadoList.getDate("fecha_realizacion"));
                orden_trabajo.setTrabajo_descripcion(resultadoList.getString("trabajo_descripcion"));
                orden_trabajo.setId_usuario_personal_jefe(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoList.getInt("id_usuario_personal_jefe")));
                orden_trabajo.setId_solicitudmc(new Solicitud_mcDAO().buscarDeSolicitudEntero(resultadoList.getInt("id_solicitudmc")));

                lista.add(orden_trabajo);
            }

        } catch (Exception e) {
            System.out.println("error en orden_trabajoDao metodo Listar" + e);
            throw e;

        } finally {
            this.Cerrar();
        }
        return lista;
    }
    
}
