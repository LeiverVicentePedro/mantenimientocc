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
import mx.edu.itoaxaca.mantenimientocc.modelo.Catalogo_servicio_solicitado;

/**
 *
 * @author Jerusalen
 */
public class Catalogo_servicio_solicitadoDAO extends Conexion {
    
     public void registrarCatalogo_servicio_solicitado(Catalogo_servicio_solicitado catalogo_servicio_solicitado) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("INSERT INTO catalogo_servicio_solicitado (servicio_solicitado) values(?)");
            consulta.setInt(1, catalogo_servicio_solicitado.getIdcatalogo_servicio_solicitado());
            consulta.setString(2,catalogo_servicio_solicitado.getServicio_solicitado());
           
            consulta.executeUpdate();
        }
        catch(Exception e){
        System.out.println("error en Catalogo_Servicio_Solicitado DAO -->RegistrarCatalogo"+"/n"+e);
       // System.out.println("error en DAO"); 
        }
     finally{
           this.Cerrar();
        }
    }
     
     
    public List<Catalogo_servicio_solicitado> listarCatalogo() throws Exception{
     List<Catalogo_servicio_solicitado> listaCatalogo;
        ResultSet resultadosetCatalogo;
     try{
         this.Conectar();
         PreparedStatement consulta=this.getConexion().prepareCall("SELECT idcatalogo_servicio_solicitado, servicio_solicitado FROM catalogo_servicio_solicitado");
         resultadosetCatalogo= consulta.executeQuery();
         listaCatalogo =new ArrayList();
         while(resultadosetCatalogo.next()){
             Catalogo_servicio_solicitado catalogo=new Catalogo_servicio_solicitado();
             catalogo.setIdcatalogo_servicio_solicitado(resultadosetCatalogo.getInt("idcatalogo_servicio_solicitado"));
             catalogo.setServicio_solicitado(resultadosetCatalogo.getString("servicio_solicitado"));
             
             
             listaCatalogo.add(catalogo);
         }
             
     }
     catch(Exception e){
         throw e;
         
     }
     finally{
         this.Cerrar();
     }
     return listaCatalogo;
    }
    
    public Catalogo_servicio_solicitado elegirDatoCatalogo(Catalogo_servicio_solicitado catalogoElegir) throws Exception{
        Catalogo_servicio_solicitado catalogoElegirdos=null;
        ResultSet resultadosetElegirCatalogo;
        
        try{
            this.Conectar();
             PreparedStatement consulta= this.getConexion().prepareStatement("SELECT idcatalogo_servicio_solicitado, servicio_solicitado FROM catalogo_servicio_solicitado WHERE idcatalogo_servicio_solicitado=?");
            consulta.setInt(1, catalogoElegir.getIdcatalogo_servicio_solicitado());
            resultadosetElegirCatalogo = consulta.executeQuery();
            while(resultadosetElegirCatalogo.next())
            {
              catalogoElegirdos= new Catalogo_servicio_solicitado();
              catalogoElegirdos.setIdcatalogo_servicio_solicitado(resultadosetElegirCatalogo.getInt("idcatalogo_servicio_solicitado"));
              catalogoElegirdos.setServicio_solicitado(resultadosetElegirCatalogo.getString("servicio_solicitado"));
             
            }
        }
        catch(Exception e){
           throw e; 
        }
        finally{
           this.Cerrar();
        }
        return catalogoElegirdos;
    }
    
    
    public void modificarCatalogo (Catalogo_servicio_solicitado catalogomodificar) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("UPDATE catalogo_servicio_solicitado SET servicio_solicitado=? WHERE idcatalogo_servicio_solicitado=?");
            consulta.setString(1, catalogomodificar.getServicio_solicitado());
            consulta.setInt(2,catalogomodificar.getIdcatalogo_servicio_solicitado());
            
            consulta.executeUpdate();
        }
        catch(Exception e){
           throw e; 
        }
        finally{
           this.Cerrar();
        }
    }  
    
    public void eliminarCatalogo (Catalogo_servicio_solicitado catalogoeliminar) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("DELETE FROM catalogo_servicio_solicitado WHERE idcatalogo_servicio_solicitado=?");
            consulta.setInt(1,catalogoeliminar.getIdcatalogo_servicio_solicitado());
            consulta.executeUpdate();
        }
        catch(Exception e){
           throw e; 
        }
        finally{
           this.Cerrar();
        }
    }   
    
    
    
}
