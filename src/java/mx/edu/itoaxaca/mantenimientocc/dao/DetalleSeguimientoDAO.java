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
import mx.edu.itoaxaca.mantenimientocc.modelo.DetalleSeguimiento;
import mx.edu.itoaxaca.mantenimientocc.modelo.Seguimiento;
import org.primefaces.model.UploadedFile;


/**
 *
 * @author Jerusalen
 */
public class DetalleSeguimientoDAO extends Conexion{
    
    public void registrarDetalleSeguimiento(DetalleSeguimiento detalleseguimiento) throws Exception  {

        try {
            this.Conectar();
            PreparedStatement inserta = this.getConexion().prepareStatement("INSERT INTO detalle_seguimiento (estado, descripcion, imagen, id_seguimiento, fecha ) values(?,?,?,?,?)");
            inserta.setString(1, detalleseguimiento.getEstado());
            inserta.setString(2, detalleseguimiento.getDescripcion());
            inserta.setBinaryStream(3,detalleseguimiento.getImagen().getInputstream());//para valor a imagen
            inserta.setInt(4, detalleseguimiento.getId_seguimiento().getIdseguimiento());
            inserta.setDate(5, (Date) detalleseguimiento.getFecha());
         
            inserta.executeUpdate();
          
        } 
        catch(Exception e){
          System.out.println("error en DetalleSeguimiento DAO -->RegistrarDetalleSeguimiento"+"/n"+e);
        }
        finally{
           this.Cerrar();
        }
    }
    
    public List<DetalleSeguimiento> listarSeguimiento(Seguimiento seguimiento) throws Exception{
        List<DetalleSeguimiento> listaDetalle = new ArrayList();
        try{
            ResultSet resultado;
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("SELECT * FROM detalle_seguimiento WHERE id_seguimiento=?");
            consulta.setInt(1,seguimiento.getIdseguimiento());
            resultado = consulta.executeQuery();
            
            while(resultado.next()){
                DetalleSeguimiento detalle = new DetalleSeguimiento();
                detalle.setIddetalle_seguimiento(resultado.getInt("iddetalle_seguimiento"));
                detalle.setEstado(resultado.getString("estado"));
                detalle.setDescripcion(resultado.getString("descripcion"));
                detalle.setImagen((UploadedFile) resultado.getBlob("imagen"));
                detalle.setId_seguimiento(seguimiento);
                detalle.setFecha(resultado.getDate("fecha"));
                listaDetalle.add(detalle);
            }
            return listaDetalle;
        }catch(Exception ex){
            System.out.println("Error en DetalleSeguimientoDAO -> listarSeguimiento "+ex);
            throw ex;
        }
    }
    
}
