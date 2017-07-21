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
import mx.edu.itoaxaca.mantenimientocc.modelo.Departamento;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;

/**
 *
 * @author Jerusalen
 */
public class Catalogo_servicio_solicitadoDAO extends Conexion {
    
     public void registrarCatalogo_servicio_solicitado(Catalogo_servicio_solicitado catalogo_servicio_solicitado) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("INSERT INTO catalogo_servicio_solicitado (servicio_solicitado,id_departamento,estatus) values(?,?,?)");
          
            consulta.setString(1,catalogo_servicio_solicitado.getServicio_solicitado());
            consulta.setInt(2,catalogo_servicio_solicitado.getDepartamento().getIddepartamento());
            consulta.setBoolean(3,catalogo_servicio_solicitado.getEstatus());
            consulta.executeUpdate();
        }
        catch(Exception e){
        System.out.println("error en Catalogo_Servicio_Solicitado DAO -->RegistrarCatalogo"+"\n"+e);
       // System.out.println("error en DAO"); 
        }
     finally{
           this.Cerrar();
        }
    }
     
     
    public List<Catalogo_servicio_solicitado> listarCatalogo(Departamento departamento) throws Exception{
     List<Catalogo_servicio_solicitado> listaCatalogo;
        ResultSet resultadosetCatalogo;
     try{
         this.Conectar();
         PreparedStatement consulta=this.getConexion().prepareCall("SELECT idcatalogo_servicio_solicitado, servicio_solicitado, id_departamento, estatus FROM catalogo_servicio_solicitado where id_departamento=?");
         consulta.setInt(1,departamento.getIddepartamento());
         resultadosetCatalogo= consulta.executeQuery();
         listaCatalogo =new ArrayList();
         while(resultadosetCatalogo.next()){
             Catalogo_servicio_solicitado catalogo=new Catalogo_servicio_solicitado();
             catalogo.setIdcatalogo_servicio_solicitado(resultadosetCatalogo.getInt("idcatalogo_servicio_solicitado"));
             catalogo.setServicio_solicitado(resultadosetCatalogo.getString("servicio_solicitado"));
             catalogo.setDepartamento(new DepartamentoDAO().buscarIdDepartamento(resultadosetCatalogo.getInt("id_departamento")));
             catalogo.setEstatus(resultadosetCatalogo.getBoolean("estatus"));
             
             
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
    
    //lista para solicitud mc debido a que tiene que ser de un solo departamento
    public List<Catalogo_servicio_solicitado> listarCatalogos() throws Exception{
     List<Catalogo_servicio_solicitado> listaCatalogo;
        ResultSet resultadosetCatalogo;
     try{
         this.Conectar();
         PreparedStatement consulta=this.getConexion().prepareCall("SELECT idcatalogo_servicio_solicitado, servicio_solicitado, id_departamento, estatus FROM catalogo_servicio_solicitado");
         
         resultadosetCatalogo= consulta.executeQuery();
         listaCatalogo =new ArrayList();
         while(resultadosetCatalogo.next()){
             Catalogo_servicio_solicitado catalogo=new Catalogo_servicio_solicitado();
             catalogo.setIdcatalogo_servicio_solicitado(resultadosetCatalogo.getInt("idcatalogo_servicio_solicitado"));
             catalogo.setServicio_solicitado(resultadosetCatalogo.getString("servicio_solicitado"));
             catalogo.setDepartamento(new DepartamentoDAO().buscarIdDepartamento(resultadosetCatalogo.getInt("id_departamento")));
             catalogo.setEstatus(resultadosetCatalogo.getBoolean("estatus"));
             
             
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
             PreparedStatement consulta= this.getConexion().prepareStatement("SELECT idcatalogo_servicio_solicitado, servicio_solicitado, id_departamento, estatus FROM catalogo_servicio_solicitado WHERE idcatalogo_servicio_solicitado=?");
            consulta.setInt(1, catalogoElegir.getIdcatalogo_servicio_solicitado());
            resultadosetElegirCatalogo = consulta.executeQuery();
            while(resultadosetElegirCatalogo.next())
            {
              catalogoElegirdos= new Catalogo_servicio_solicitado();
              catalogoElegirdos.setIdcatalogo_servicio_solicitado(resultadosetElegirCatalogo.getInt("idcatalogo_servicio_solicitado"));
              catalogoElegirdos.setServicio_solicitado(resultadosetElegirCatalogo.getString("servicio_solicitado"));
              catalogoElegirdos.setDepartamento(new DepartamentoDAO().buscarIdDepartamento(resultadosetElegirCatalogo.getInt("id_departamento")));
              catalogoElegirdos.setEstatus(resultadosetElegirCatalogo.getBoolean("estatus"));
             
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
            PreparedStatement consulta= this.getConexion().prepareStatement("UPDATE catalogo_servicio_solicitado SET servicio_solicitado=?, id_departamento=?, estatus=? WHERE idcatalogo_servicio_solicitado=?");
            consulta.setString(1, catalogomodificar.getServicio_solicitado());
            consulta.setInt(2,catalogomodificar.getDepartamento().getIddepartamento());
            consulta.setBoolean(3,catalogomodificar.getEstatus());
            consulta.setInt(4,catalogomodificar.getIdcatalogo_servicio_solicitado());
            
            consulta.executeUpdate();
        }
        catch(Exception e){
           throw e; 
        }
        finally{
           this.Cerrar();
        }
    }  
    /*
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
    */
    
   public List<Catalogo_servicio_solicitado> listarCatalogoPorDepartamentoServico(int idDepartamento) throws Exception{
     List<Catalogo_servicio_solicitado> listaCatalogo;
        ResultSet resultadosetCatalogo;
     try{
         this.Conectar();
         PreparedStatement consulta=this.getConexion().prepareCall("SELECT * FROM catalogo_servicio_solicitado WHERE id_departamento=?");
         consulta.setInt(1, idDepartamento);
         resultadosetCatalogo= consulta.executeQuery();
         listaCatalogo =new ArrayList();
         while(resultadosetCatalogo.next()){
             Catalogo_servicio_solicitado catalogo=new Catalogo_servicio_solicitado();
             catalogo.setIdcatalogo_servicio_solicitado(resultadosetCatalogo.getInt("idcatalogo_servicio_solicitado"));
             catalogo.setServicio_solicitado(resultadosetCatalogo.getString("servicio_solicitado"));
             catalogo.setDepartamento(new DepartamentoDAO().buscarIdDepartamento(resultadosetCatalogo.getInt("id_departamento")));
             catalogo.setEstatus(resultadosetCatalogo.getBoolean("estatus"));
             
             listaCatalogo.add(catalogo);
         }
             
     }
     catch(Exception e){
         System.out.println("Error en Catalogo_servicio_solicitadoDAO -> listarCatalogoPorDepartamentoServicio "+e);
         throw e;
         
     }
     finally{
         this.Cerrar();
     }
     return listaCatalogo;
    }
    
}
