/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.dao;

import java.sql.PreparedStatement;
import mx.edu.itoaxaca.mantenimientocc.conexion.Conexion;
import mx.edu.itoaxaca.mantenimientocc.modelo.Detalle_solicitud;

/**
 *
 * @author leiver
 */
public class Detalle_solicitudDAO extends Conexion{

    public void registrarDetalleSolicitud(Detalle_solicitud detalleSolicitud) throws Exception{
        try{
            this.Conectar();
            PreparedStatement insertar = this.getConexion().prepareStatement("INSERT INTO detalle_solicitud (id_solicitud_mc, id_catalogo_servicio_solicitado) VALUES(?,?)");
            insertar.setInt(1, detalleSolicitud.getId_solicitud_mc().getIdsolicitud_mc());
            insertar.setInt(2, detalleSolicitud.getId_catalogo_servicio_solicitado().getIdcatalogo_servicio_solicitado());
            insertar.executeUpdate();
        }catch(Exception ex){
            System.out.println("Error en Detalle_solicitudDAO -> registrarDetalleSolicitud "+ex);
            throw ex;
        }finally{
            this.Cerrar();
        }
    }
}
