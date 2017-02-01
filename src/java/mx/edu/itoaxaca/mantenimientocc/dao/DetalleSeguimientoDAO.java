/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.dao;


import java.io.FileInputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mx.edu.itoaxaca.mantenimientocc.conexion.Conexion;
import mx.edu.itoaxaca.mantenimientocc.modelo.DetalleSeguimiento;
import mx.edu.itoaxaca.mantenimientocc.modelo.Seguimiento;



/**
 *
 * @author Jerusalen
 */
public class DetalleSeguimientoDAO extends Conexion {

    public void registrarDetalleSeguimiento(DetalleSeguimiento detalleseguimiento) throws Exception {

        try {
            this.Conectar();
            PreparedStatement inserta = this.getConexion().prepareStatement("INSERT INTO detalle_seguimiento (estado, descripcion,  imagen, id_seguimiento, fecha ) values(?,?,?,?,?)");
            inserta.setString(1, detalleseguimiento.getEstado());
            inserta.setString(2, detalleseguimiento.getDescripcion());
            inserta.setBinaryStream(3, detalleseguimiento.getImagen().getInputstream());//para valor a imagen
            inserta.setInt(4, detalleseguimiento.getId_seguimiento().getIdseguimiento());
            inserta.setDate(5, (Date) detalleseguimiento.getFecha());

            inserta.executeUpdate();

        } catch (Exception e) {
            System.out.println("error en DetalleSeguimiento DAO -->RegistrarDetalleSeguimiento" + "/n" + e);
        } finally {
            this.Cerrar();
        }
    }
    /*insercion para cuando no hay refacciones unicamente*/
     public void registrarDetalleSeguimientoEnOrdenInterna(DetalleSeguimiento detalleseguimiento,FileInputStream imagen) throws Exception {

        try {
            this.Conectar();
            PreparedStatement inserta = this.getConexion().prepareStatement("INSERT INTO detalle_seguimiento (estado, descripcion,  imagen, id_seguimiento, fecha ) values(?,?,?,?,?)");
            inserta.setString(1, detalleseguimiento.getEstado());
            inserta.setString(2, detalleseguimiento.getDescripcion());
            inserta.setBinaryStream(3, imagen);//para valor a imagen
            inserta.setInt(4, detalleseguimiento.getId_seguimiento().getIdseguimiento());
            inserta.setDate(5, (Date) detalleseguimiento.getFecha());

            inserta.executeUpdate();

        } catch (Exception e) {
            System.out.println("error en DetalleSeguimiento DAO -->RegistrarDetalleSeguimientoEnOrdenInterna" + "/n" + e);
        } finally {
            this.Cerrar();
        }
    }
    /*fon del metodo para cuando no hay refaccion*/
        public List<DetalleSeguimiento> listarDetalleSeguimiento() throws Exception{//uso unico para la vista Area
     List<DetalleSeguimiento> lista;
        ResultSet resultadoset;
     try{
         this.Conectar();
         PreparedStatement consulta=this.getConexion().prepareCall("SELECT iddetalle_seguimiento, estado, descripcion,imagen, id_seguimiento, fecha FROM detalle_seguimiento where estado like '%final%'");
         resultadoset= consulta.executeQuery();
         lista =new ArrayList();
         
         while(resultadoset.next()){
            
             DetalleSeguimiento detalleSeguimiento=new DetalleSeguimiento();
             detalleSeguimiento.setIddetalle_seguimiento(resultadoset.getInt("iddetalle_seguimiento"));
             detalleSeguimiento.setEstado(resultadoset.getString("estado"));
             detalleSeguimiento.setDescripcion(resultadoset.getString("descripcion"));
             detalleSeguimiento.setId_seguimiento(new SeguimientoDAO().identificadorDetalleSeguimientoID(resultadoset.getInt("id_seguimiento")));
             detalleSeguimiento.setFecha(resultadoset.getDate("fecha"));
             
             lista.add(detalleSeguimiento);
         }
     }
     catch(Exception e){
         throw e;
     }
     finally{
         this.Cerrar();
     }
     return lista;
    }

   
    public List<DetalleSeguimiento> listardetalle(Seguimiento seguimiento) throws Exception{
        ArrayList<DetalleSeguimiento> detalles = new ArrayList<DetalleSeguimiento>();
        
        ResultSet resultado = null;
        try{
            this.Conectar();
            PreparedStatement Consulta = this.getConexion().prepareCall("SELECT * FROM detalle_seguimiento where id_seguimiento=?");
            Consulta.setInt(1, seguimiento.getIdseguimiento());
            resultado = Consulta.executeQuery();
            
            while(resultado.next()){
                DetalleSeguimiento seguimientoDetalle = new DetalleSeguimiento();
                seguimientoDetalle.setIddetalle_seguimiento(resultado.getInt("iddetalle_seguimiento"));
                seguimientoDetalle.setEstado(resultado.getString("estado"));
                seguimientoDetalle.setDescripcion(resultado.getString("descripcion"));
                seguimientoDetalle.setImagenDowload(resultado.getBytes("imagen"));
                seguimientoDetalle.setId_seguimiento(seguimiento);
                seguimientoDetalle.setFecha(resultado.getDate("fecha"));
                detalles.add(seguimientoDetalle);
            }
        }catch(SQLException ex){
            System.out.println("Error en DAO -> lista "+ex);
        }finally{
            this.Cerrar();
        }
        return detalles;
    }

}
