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
           System.out.println("error en DAO"); 
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

            // departamento.setArea((Area) resultadoset.getObject("idarea"));

             departamento.setArea(new AreaDAO().buscarIdArea(resultadoset.getInt("id_area")));

             departamento.setEstatus(resultadoset.getBoolean("estatus"));
             
             lista.add(departamento);
         }
             
     }
     catch(Exception e){
         System.out.println("error en departamentoDao "+e);
         throw e;
         
     }
     finally{
         this.Cerrar();
     }
     return lista;
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
