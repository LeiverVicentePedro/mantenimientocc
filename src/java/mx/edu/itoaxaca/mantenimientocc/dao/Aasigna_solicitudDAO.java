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
import mx.edu.itoaxaca.mantenimientocc.modelo.Aasigna_solicitud;

/**
 *
 * @author Jerusalen
 */
public class Aasigna_solicitudDAO extends Conexion{
     public void registrarAsignarSolicitud(Aasigna_solicitud asignarSolicitudregistra) throws Exception {
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
     
    public List<Aasigna_solicitud> listarAsignarSolicitud() throws Exception {
        List<Aasigna_solicitud> lista;
        ResultSet resultadoList;
        try {
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareCall("SELECT * FROM asigna_solicitud");
            resultadoList = consulta.executeQuery();
            lista = new ArrayList();
            while (resultadoList.next()) {
                Aasigna_solicitud asignar_Solicitud = new Aasigna_solicitud();

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
    }
    
}
