/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import mx.edu.itoaxaca.mantenimientocc.conexion.Conexion;
import mx.edu.itoaxaca.mantenimientocc.modelo.DetallePreventivo;

/**
 *
 * @author leiver
 */
public class DetallePreventivoDAO extends Conexion{
    
    public void registrarDetalle(DetallePreventivo detalle) throws Exception{
        try{
            this.Conectar();
            PreparedStatement registrar = this.getConexion().prepareStatement("INSERT INTO detalle_preventivo(numero_servicio,servicio, tipo_servicio, fecha_programada, fecha_realizada, fecha_reprogramada, id_preventivo) VALUES(?,?,?,?,?,?,?)");
            registrar.setInt(1, detalle.getNumero_servicio());
            registrar.setString(2, detalle.getServicio());
            registrar.setString(3, detalle.getTipo_servicio());
            registrar.setDate(4, new java.sql.Date(detalle.getFecha_programada().getTime()));
            registrar.setDate(5, new java.sql.Date(detalle.getFecha_realizada().getTime()));
            registrar.setDate(6, new java.sql.Date(detalle.getFecha_reprogramada().getTime()));
            registrar.setInt(7, detalle.getId_preventivo().getIdPreventivo());
            
            registrar.executeUpdate();
        }catch(Exception ex){
            System.out.println("Error en DetallePreventivoDAO -> registrarDetalle "+ex);
            throw ex;
        }finally{
            this.Cerrar();
        }
    }
}
