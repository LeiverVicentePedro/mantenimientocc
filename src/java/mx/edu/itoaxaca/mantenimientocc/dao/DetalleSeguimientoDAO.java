/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.dao;

import java.sql.PreparedStatement;

import mx.edu.itoaxaca.mantenimientocc.conexion.Conexion;
import mx.edu.itoaxaca.mantenimientocc.modelo.DetalleSeguimiento;


/**
 *
 * @author Jerusalen
 */
public class DetalleSeguimientoDAO extends Conexion{
    
    public void registrarDetalleSeguimiento(DetalleSeguimiento detalleseguimiento) throws Exception  {

        try {
            this.Conectar();
            PreparedStatement inserta = this.getConexion().prepareStatement("INSERT INTO detalle_seguimiento (numero_seguimiento, descripcion, imagen, id_seguimiento ) values(?,?,?,?)");
            inserta.setInt(1, detalleseguimiento.getNumero_seguimiento());
            inserta.setString(2, detalleseguimiento.getDescripcion());
            inserta.setBinaryStream(3,detalleseguimiento.getImagen().getInputstream());//para valor a imagen
            inserta.setInt(4, detalleseguimiento.getId_seguimiento().getIdseguimiento());
         
            inserta.executeUpdate();
          
        } 
        catch(Exception e){
          System.out.println("error en DetalleSeguimiento DAO -->RegistrarDetalleSeguimiento"+"/n"+e);
        }
        finally{
           this.Cerrar();
        }
    }
    
    
}
