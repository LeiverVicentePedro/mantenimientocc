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
             departamento.setEstatus(resultadoset.getBoolean("estatus"));
             
             lista.add(departamento);
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
