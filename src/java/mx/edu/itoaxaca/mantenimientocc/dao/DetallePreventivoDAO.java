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
            PreparedStatement registrar = this.getConexion().prepareStatement("INSERT INTO detalle_preventivo(numero_servicio,servicio, tipo_servicio, estado, fecha, id_preventivo) VALUES(?,?,?,?,?,?)");
            registrar.setInt(1, detalle.getNumero_servicio());
            registrar.setString(2, detalle.getServicio());
            registrar.setString(3,detalle.getTipo_servicio());
            registrar.setString(4, detalle.getEstado());
            registrar.setDate(5, new java.sql.Date(detalle.getFecha().getTime()));
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
                    detalle.setTipo_servicio(resultado.getString("tipo_servicio"));
                    detalle.setEstado(resultado.getString("estado"));
                    detalle.setFecha(resultado.getDate("fecha"));
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
