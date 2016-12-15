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
import mx.edu.itoaxaca.mantenimientocc.modelo.DetallePreventivo;
import mx.edu.itoaxaca.mantenimientocc.modelo.Preventivo;

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
            registrar.setDate(3, new java.sql.Date(detalle.getFecha_programada().getTime()));
            registrar.setDate(4, new java.sql.Date(detalle.getFecha_realizada().getTime()));
            registrar.setDate(5, new java.sql.Date(detalle.getFecha_reprogramada().getTime()));
            registrar.setInt(6, detalle.getId_preventivo().getIdPreventivo());
            
            registrar.executeUpdate();
        }catch(Exception ex){
            System.out.println("Error en DetallePreventivoDAO -> registrarDetalle "+ex);
            throw ex;
        }finally{
            this.Cerrar();
        }   
    }
    
    public List<DetallePreventivo> listarDetallePreventivo(Preventivo preventivo) throws Exception{
            try{
                List<DetallePreventivo> listaDetalle = new ArrayList();
                ResultSet resultado;
                this.Conectar();
                PreparedStatement consulta = this.getConexion().prepareStatement("SELECT * FROM detalle_preventivo WHERE id_preventivo=?");
                consulta.setInt(1, preventivo.getIdPreventivo());
                resultado = consulta.executeQuery();
                
                while(resultado.next()){
                    DetallePreventivo detalle = new DetallePreventivo();
                    detalle.setIdDetalle_preventivo(resultado.getInt("iddetalle_preventivo"));
                    detalle.setNumero_servicio(resultado.getInt("numero_servicio"));
                    detalle.setServicio(resultado.getString("servicio"));
                    detalle.setFecha_programada(resultado.getDate("fecha_programada"));
                    detalle.setFecha_realizada(resultado.getDate("fecha_realizada"));
                    detalle.setFecha_reprogramada(resultado.getDate("fecha_reprogramada"));
                    detalle.setId_preventivo(preventivo);
                    listaDetalle.add(detalle);
                }
                return listaDetalle;
                
            }catch(Exception ex){
                System.out.println("Error en DetallePreventivoDAO -> listarDetallePreventivo "+ex);
                throw ex;
            }finally{
                this.Cerrar();
            }
        }
}
