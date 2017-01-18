/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.dao;

import java.io.InputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import mx.edu.itoaxaca.mantenimientocc.conexion.Conexion;
import mx.edu.itoaxaca.mantenimientocc.modelo.DetalleSeguimiento;
import mx.edu.itoaxaca.mantenimientocc.modelo.Seguimiento;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DefaultUploadedFile;
import org.primefaces.model.UploadedFile;


/**
 *
 * @author Jerusalen
 */
public class DetalleSeguimientoDAO extends Conexion{
    
    public void registrarDetalleSeguimiento(DetalleSeguimiento detalleseguimiento) throws Exception  {

        try {
            this.Conectar();
            PreparedStatement inserta = this.getConexion().prepareStatement("INSERT INTO detalle_seguimiento (estado, descripcion,  imagen, id_seguimiento, fecha ) values(?,?,?,?,?)");
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
    
        public List<DetalleSeguimiento> listarDetalleSeguimiento() throws Exception{//uso unico para la vista Area
     List<DetalleSeguimiento> lista;
        ResultSet resultadoset;
     try{
         this.Conectar();
         PreparedStatement consulta=this.getConexion().prepareCall("SELECT iddetalle_seguimiento, estado, descripcion,imagen, id_seguimiento, fecha FROM detalle_seguimiento");
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
    
    
    
}
