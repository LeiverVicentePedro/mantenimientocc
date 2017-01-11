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
import mx.edu.itoaxaca.mantenimientocc.modelo.Refaccion_empleada;


/**
 *
 * @author Jerusalen
 */
public class Refaccion_empleadaDAO extends Conexion{
    
     public void registrarRefaccion(Refaccion_empleada refaccionregistra) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("INSERT INTO refaccion_empleada (descripcion,numero_serie,precio,cantidad) values(?,?,?,?)");
            consulta.setString(1, refaccionregistra.getDescripcion());
            consulta.setString(2,refaccionregistra.getNumero_serie());
            consulta.setDouble(3,refaccionregistra.getPrecio());
            consulta.setInt(4,refaccionregistra.getCantidad());
            consulta.executeUpdate();
            
        }
        catch(Exception e){
            System.out.println("Error en DAO Refaccion "+ e);
        }
        finally{
            this.Cerrar();
        }
        
    }
    
    
     public List<Refaccion_empleada> listarRefaccion() throws Exception{
     List<Refaccion_empleada> lista;
        ResultSet resultadoset;
     try{
         this.Conectar();
         PreparedStatement consulta=this.getConexion().prepareCall("SELECT idrefaccion_empleada, descripcion, numero_serie, precio, cantidad FROM refaccion_empleada");
         resultadoset= consulta.executeQuery();
         lista =new ArrayList();
         while(resultadoset.next()){
             Refaccion_empleada refaccion=new Refaccion_empleada();
             refaccion.setId_refaccion_empleada(resultadoset.getInt("idrefaccion_empleada"));
             refaccion.setDescripcion(resultadoset.getString("descripcion"));
             refaccion.setNumero_serie(resultadoset.getString("numero_serie"));
             refaccion.setPrecio(resultadoset.getDouble("precio"));
             refaccion.setCantidad(resultadoset.getInt("cantidad"));
             
             
             
             lista.add(refaccion);
         }
             
     }
     catch(Exception e){
         System.out.println("error en Refaccion_empledaDao metodo Listar"+e);
         throw e;
         
     }
     finally{
         this.Cerrar();
     }
     return lista;
    }
     
     
     public Refaccion_empleada elegirDatoRefaccion(Refaccion_empleada refaccion) throws Exception{
        Refaccion_empleada refacciondos=null;
        ResultSet resultadosetrefaccion;
        try{
            this.Conectar();
             PreparedStatement consulta= this.getConexion().prepareStatement("SELECT * FROM refaccion_empleada WHERE idrefaccion_empleada=?");
            consulta.setInt(1, refaccion.getId_refaccion_empleada());
            resultadosetrefaccion = consulta.executeQuery();
            while(resultadosetrefaccion.next())
            {
              refacciondos= new Refaccion_empleada();
              
             refacciondos.setId_refaccion_empleada(resultadosetrefaccion.getInt("idrefaccion_empleada"));
              refacciondos.setDescripcion(resultadosetrefaccion.getString("descripcion"));
              refacciondos.setNumero_serie(resultadosetrefaccion.getString("numero_serie"));
              refacciondos.setPrecio(resultadosetrefaccion.getDouble("precio"));
              refacciondos.setCantidad(resultadosetrefaccion.getInt("cantidad"));
            
            }
        }
        catch(Exception e){
           System.out.println("error en refaccionDao metodo ElegirDato"+e);
        }
        finally{
           this.Cerrar();
        }
        return refacciondos;
    }
    //buscar una refaccion por todos los datos que contiene
     public Refaccion_empleada BuscaRefaccionEmpledaPorObjeto(Refaccion_empleada refaccion)throws Exception{
        Refaccion_empleada refacciondos=null;
        ResultSet resultadosetrefaccion;
        try{
            this.Conectar();
             PreparedStatement consulta= this.getConexion().prepareStatement("SELECT * FROM refaccion_empleada WHERE descripcion=? and numero_serie=? and precio=? and cantidad=?");
            consulta.setString(1, refaccion.getDescripcion());
            consulta.setString(2,refaccion.getNumero_serie());
            consulta.setDouble(3, refaccion.getPrecio());
            consulta.setInt(4,refaccion.getCantidad());
            resultadosetrefaccion = consulta.executeQuery();
            while(resultadosetrefaccion.next())
            {
              refacciondos= new Refaccion_empleada();
              
             refacciondos.setId_refaccion_empleada(resultadosetrefaccion.getInt("idrefaccion_empleada"));
              refacciondos.setDescripcion(resultadosetrefaccion.getString("descripcion"));
              refacciondos.setNumero_serie(resultadosetrefaccion.getString("numero_serie"));
              refacciondos.setPrecio(resultadosetrefaccion.getDouble("precio"));
              refacciondos.setCantidad(resultadosetrefaccion.getInt("cantidad"));
            
            }
        }
        catch(Exception e){
           System.out.println("error en Refaccion_EmpleadaDao -> BuscaRefaccionEmpleadaPorObjeto"+e);
        }
        finally{
           this.Cerrar();
        }
        return refacciondos; 
     }
     
    //metodo modificar equipo
     
     public void modificarRefaccion (Refaccion_empleada refaccionmodificar) throws Exception{
          
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("UPDATE refaccion_empleada SET descripcion=?, numero_serie=?, precio=?, cantidad=? WHERE idrefaccion_empleada=?");
             consulta.setString(1, refaccionmodificar.getDescripcion());
            consulta.setString(2,refaccionmodificar.getNumero_serie());
            consulta.setDouble(3,refaccionmodificar.getPrecio());
            consulta.setInt(4,refaccionmodificar.getCantidad());
            consulta.setInt(5, refaccionmodificar.getId_refaccion_empleada());
            consulta.executeUpdate();
        }
        catch(Exception e){
          System.out.println("error en RefaccionDao metodo Modificar"+e);
        }
        finally{
           this.Cerrar();
        }
    }  
     
   //metodo eliminaf
      public void eliminarRefaccion (Refaccion_empleada refaccioneliminar) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("DELETE FROM refaccion_empleada WHERE idrefaccion_empleada=?");
            consulta.setInt(1,refaccioneliminar.getId_refaccion_empleada());
            consulta.executeUpdate();
        }
        catch(Exception e){
           throw e; 
        }
        finally{
           this.Cerrar();
        }
    }   
      
    public Refaccion_empleada buscarIdRefaccion(int idRefaccion) throws Exception{
         Refaccion_empleada refaccion = new Refaccion_empleada();
         ResultSet resultadoConsulta;
         try{
             this.Conectar();
             PreparedStatement consulta = this.getConexion().prepareCall("SELECT * FROM refaccion_empleada where idrefaccion_empleada=?");
             consulta.setInt(1, idRefaccion);
             resultadoConsulta = consulta.executeQuery();
              if(resultadoConsulta.next()){
             refaccion.setId_refaccion_empleada(resultadoConsulta.getInt("idrefaccion_empleada"));
              refaccion.setDescripcion(resultadoConsulta.getString("descripcion"));
              refaccion.setNumero_serie(resultadoConsulta.getString("numero_serie"));
              refaccion.setPrecio(resultadoConsulta.getDouble("precio"));
              refaccion.setCantidad(resultadoConsulta.getInt("cantidad"));
              }
              resultadoConsulta.close();
              
         }catch(Exception ex){
             System.out.println("Error en RefaccionDAO -> buscarIdRefaccion "+ex);
             throw ex;
         }finally{
             this.Cerrar();
         }
         return refaccion;
     }
    
    
    
    
    
    
}
