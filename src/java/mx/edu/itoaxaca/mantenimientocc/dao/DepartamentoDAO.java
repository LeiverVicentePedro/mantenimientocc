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
import mx.edu.itoaxaca.mantenimientocc.modelo.Area;
import mx.edu.itoaxaca.mantenimientocc.modelo.Departamento;



/**
 *
 * @author Jerusalen
 */
public class DepartamentoDAO extends Conexion{
    
    public void registrarDepartamento(Departamento departamentoregistra) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("INSERT INTO departamento (clave_departamento,nombre_departamento,id_area,estatus) values(?,?,?,?)");
            consulta.setString(1, departamentoregistra.getClave_departamento());
            consulta.setString(2,departamentoregistra.getNombre_departamento());
            consulta.setInt(3,departamentoregistra.getArea().getIdarea());
            consulta.setBoolean(4,departamentoregistra.getEstatus());
            consulta.executeUpdate();
        }
        catch(Exception e){
        System.out.println("error en Departamento DAO -->RegistrarDEPTO"+"/n"+e);
       // System.out.println("error en DAO"); 
        }
     finally{
           this.Cerrar();
        }
    }
    
    
     public List<Departamento> listarDepartamento() throws Exception{
     List<Departamento> lista;
        ResultSet resultadoset;
     try{
         this.Conectar();
         PreparedStatement consulta=this.getConexion().prepareCall("SELECT iddepartamento, clave_departamento, nombre_departamento, id_area, estatus FROM departamento");
         resultadoset= consulta.executeQuery();
         lista =new ArrayList();
         while(resultadoset.next()){
             Departamento departamento=new Departamento();
             departamento.setIddepartamento(resultadoset.getInt("iddepartamento"));
             departamento.setClave_departamento(resultadoset.getString("clave_departamento"));
             departamento.setNombre_departamento(resultadoset.getString("nombre_departamento"));
            departamento.setArea(new AreaDAO().buscarIdArea(resultadoset.getInt("id_area")));

             departamento.setEstatus(resultadoset.getBoolean("estatus"));
             
             lista.add(departamento);
         }
             
     }
     catch(Exception e){
         System.out.println("error en departamentoDao metodo Listar"+e);
         throw e;
         
     }
     finally{
         this.Cerrar();
     }
     return lista;
    }
     
     
     public Departamento elegirDatoDepartamento(Departamento departamento) throws Exception{
        Departamento departamentodos=null;
        ResultSet resultadosetdepartamento;
        try{
            this.Conectar();
             PreparedStatement consulta= this.getConexion().prepareStatement("SELECT iddepartamento, clave_departamento, nombre_departamento,id_area,estatus FROM departamento WHERE iddepartamento=?");
            consulta.setInt(1, departamento.getIddepartamento());
            resultadosetdepartamento = consulta.executeQuery();
            while(resultadosetdepartamento.next())
            {
              departamentodos= new Departamento();
              
             departamentodos.setIddepartamento(resultadosetdepartamento.getInt("iddepartamento"));
              departamentodos.setClave_departamento(resultadosetdepartamento.getString("clave_departamento"));
              departamentodos.setNombre_departamento(resultadosetdepartamento.getString("nombre_departamento"));
              departamentodos.setArea(new AreaDAO().buscarIdArea(resultadosetdepartamento.getInt("id_area")));
              departamentodos.setEstatus(resultadosetdepartamento.getBoolean("estatus"));
            }
        }
        catch(Exception e){
           System.out.println("error en departamentoDao metodo ElegirDato"+e);
        }
        finally{
           this.Cerrar();
        }
        return departamentodos;
    }
     
     
    //metodo modificar departamento
     
     public void modificarDepartamento (Departamento departamentomodificar) throws Exception{
          
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("UPDATE departamento SET clave_departamento=?, nombre_departamento=?, id_area=?, estatus=? WHERE iddepartamento=?");
            consulta.setInt(1, departamentomodificar.getIddepartamento());
            consulta.setString(2, departamentomodificar.getClave_departamento());
            consulta.setString(3, departamentomodificar.getNombre_departamento());
            consulta.setInt(4,departamentomodificar.getArea().getIdarea());
            consulta.setBoolean(5,departamentomodificar.getEstatus());
            consulta.executeUpdate();
        }
        catch(Exception e){
          System.out.println("error en departamentoDao metodo Modificar"+e);
        }
        finally{
           this.Cerrar();
        }
    }  
     
   //metodo eliminaf
      public void eliminarDepartamento (Departamento departamentoeliminar) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("DELETE FROM departamento WHERE id_area=?");
            consulta.setInt(1,departamentoeliminar.getArea().getIdarea());
            consulta.executeUpdate();
        }
        catch(Exception e){
           throw e; 
        }
        finally{
           this.Cerrar();
        }
    }   
    
    
     public Departamento buscarIdDepartamento(int idDepartamento) throws Exception{
         Departamento departamento = new Departamento();
         ResultSet resultadoConsulta;
         try{
             this.Conectar();
             PreparedStatement consulta = this.getConexion().prepareCall("SELECT * FROM departamento where iddepartamento=?");
             consulta.setInt(1, idDepartamento);
             resultadoConsulta = consulta.executeQuery();
              if(resultadoConsulta.next()){
             departamento.setIddepartamento(resultadoConsulta.getInt("iddepartamento"));
             departamento.setClave_departamento(resultadoConsulta.getString("clave_departamento"));
             departamento.setNombre_departamento(resultadoConsulta.getString("nombre_departamento"));
             departamento.setArea(new AreaDAO().buscarIdArea(resultadoConsulta.getInt("id_area")));
             departamento.setEstatus(resultadoConsulta.getBoolean("estatus"));
              }
              resultadoConsulta.close();
              
         }catch(Exception ex){
             System.out.println("Error en DepartamentoDAO -> buscarIdDepartamento "+ex);
             throw ex;
         }finally{
             this.Cerrar();
         }
         return departamento;
     }
    
     public List<Departamento> buscarDepartamentoPorIdArea(int idArea) throws Exception{
         
         List<Departamento> listaDepartamentoDeUnArea = null;
         ResultSet resultadoConsulta;
         try{
             this.Conectar();
             PreparedStatement consulta = this.getConexion().prepareCall("SELECT * FROM departamento where id_area=?");
             consulta.setInt(1, idArea);
             resultadoConsulta = consulta.executeQuery();
             listaDepartamentoDeUnArea = new ArrayList();
             while(resultadoConsulta.next()){
             Departamento departamento = new Departamento();
             departamento.setIddepartamento(resultadoConsulta.getInt("iddepartamento"));
             departamento.setClave_departamento(resultadoConsulta.getString("clave_departamento"));
             departamento.setNombre_departamento(resultadoConsulta.getString("nombre_departamento"));
             departamento.setArea(new AreaDAO().buscarIdArea(resultadoConsulta.getInt("id_area")));
             departamento.setEstatus(resultadoConsulta.getBoolean("estatus"));
             
             listaDepartamentoDeUnArea.add(departamento);
              }
              resultadoConsulta.close();
              
         }catch(Exception ex){
             System.out.println("Error en DepartamentoDAO -> buscarDepartamentoPorIdArea "+ex);
             throw ex;
         }finally{
             this.Cerrar();
         }
         return listaDepartamentoDeUnArea;
     }
}
