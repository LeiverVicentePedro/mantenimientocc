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
import mx.edu.itoaxaca.mantenimientocc.modelo.Profesion;

/**
 *
 * @author Jerusalen
 */
public class ProfesionDAO extends Conexion {
    
     public void registrarProfesion(Profesion profesion) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("INSERT INTO profesion (nombre_profesion) values(?)");
          
            consulta.setString(1,profesion.getNombre_profesion());
           
            consulta.executeUpdate();
        }
        catch(Exception e){
        System.out.println("error en Profesion DAO -->Registrar Profesion"+"/n"+e);
       // System.out.println("error en DAO"); 
        }
     finally{
           this.Cerrar();
        }
    }
     
     
    public List<Profesion> listarProfesion() throws Exception{
     List<Profesion> listaProfesion;
        ResultSet resultadosetProfesion;
     try{
         this.Conectar();
         PreparedStatement consulta=this.getConexion().prepareCall("SELECT idprofesion, nombre_profesion FROM profesion");
         resultadosetProfesion= consulta.executeQuery();
         listaProfesion =new ArrayList();
         while(resultadosetProfesion.next()){
             Profesion profesionRegistro=new Profesion();
             profesionRegistro.setIdprofesion(resultadosetProfesion.getInt("idprofesion"));
             profesionRegistro.setNombre_profesion(resultadosetProfesion.getString("nombre_profesion"));
             
             
             listaProfesion.add(profesionRegistro);
         }
             
     }
     catch(Exception e){
         throw e;
         
     }
     finally{
         this.Cerrar();
     }
     return listaProfesion;
    }
    
    public Profesion elegirDatoProfesion(Profesion profesionElegir) throws Exception{
        Profesion profesionElegirdos=null;
        ResultSet resultadosetElegirProfesion;
        
        try{
            this.Conectar();
             PreparedStatement consulta= this.getConexion().prepareStatement("SELECT idprofesion, nombre_profesion FROM profesion WHERE idprofesion=?");
            consulta.setInt(1, profesionElegir.getIdprofesion());
            resultadosetElegirProfesion = consulta.executeQuery();
            while(resultadosetElegirProfesion.next())
            {
              profesionElegirdos= new Profesion();
              profesionElegirdos.setIdprofesion(resultadosetElegirProfesion.getInt("idprofesion"));
              profesionElegirdos.setNombre_profesion(resultadosetElegirProfesion.getString("nombre_profesion"));
             
            }
        }
        catch(Exception e){
           throw e; 
        }
        finally{
           this.Cerrar();
        }
        return profesionElegirdos;
    }
    
    
    public void modificarProfesion (Profesion profesionModificar) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("UPDATE profesion SET nombre_profesion=? WHERE idprofesion=?");
            consulta.setString(1, profesionModificar.getNombre_profesion());
            consulta.setInt(2,profesionModificar.getIdprofesion());
            
            consulta.executeUpdate();
        }
        catch(Exception e){
           throw e; 
        }
        finally{
           this.Cerrar();
        }
    }  
    
    public void eliminarProfesion (Profesion profesioneliminar) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("DELETE FROM profesion WHERE idprofesion=?");
            consulta.setInt(1,profesioneliminar.getIdprofesion());
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
