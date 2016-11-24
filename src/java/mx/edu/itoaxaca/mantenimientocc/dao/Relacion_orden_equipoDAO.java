/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.dao;

import java.sql.PreparedStatement;
import mx.edu.itoaxaca.mantenimientocc.conexion.Conexion;
import mx.edu.itoaxaca.mantenimientocc.modelo.Relacion_orden_equipo;

/**
 *
 * @author Jerusalen
 */
public class Relacion_orden_equipoDAO extends Conexion{

    public void registrarDetalleOrdenEquipo(Relacion_orden_equipo detalleOrdenEquipo) throws Exception{
        try{
            this.Conectar();
            PreparedStatement insertar = this.getConexion().prepareStatement("INSERT INTO relacion_orden_equipo (id_equipo, id_orden_interna) VALUES(?,?)");
            insertar.setInt(1, detalleOrdenEquipo.getIdEquipo().getIdequipo());
            insertar.setInt(2, detalleOrdenEquipo.getIdOrden_interna().getIdorden_interna());
            insertar.executeUpdate();
        }catch(Exception ex){
            System.out.println("Error en Detalle_Orden_equipoDAO -> registrarDetalleOrdenEquipo "+ex);
            throw ex;
        }finally{
            this.Cerrar();
        }
    }
    
}

