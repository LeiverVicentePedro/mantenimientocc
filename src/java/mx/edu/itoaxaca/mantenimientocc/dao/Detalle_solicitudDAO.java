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
import mx.edu.itoaxaca.mantenimientocc.modelo.Detalle_solicitud;
import mx.edu.itoaxaca.mantenimientocc.modelo.Solicitud_mc;

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
    
    public List<Detalle_solicitud> listaDetalleDeUnaSolicitud(Solicitud_mc solicitud)throws Exception{
        List<Detalle_solicitud> lista = new ArrayList();
        try{
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("select * from detalle_solicitud where id_solicitud_mc=?");
            consulta.setInt(1, solicitud.getIdsolicitud_mc());
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                Detalle_solicitud detalle = new Detalle_solicitud();
                detalle.setId_solicitud_mc(solicitud);
                detalle.setId_catalogo_servicio_solicitado(new Catalogo_servicio_solicitadoDAO().elegirDatoCatalogoPorId(resultado.getInt("id_catalogo_servicio_solicitado")));
                lista.add(detalle);
            }
            
        }catch(Exception ex){
            System.out.println("Error en Detalle_solicitudDAO -> listaDetalleDeUnaSoliciutd: "+ex);
        }finally{
            this.Cerrar();
        }
        return lista;
    }
}
