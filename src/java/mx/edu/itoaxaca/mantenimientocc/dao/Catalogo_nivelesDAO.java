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
import mx.edu.itoaxaca.mantenimientocc.modelo.Catalogo_niveles;
import mx.edu.itoaxaca.mantenimientocc.modelo.Equipo;

/**
 *
 * @author Jerusalen
 */
public class Catalogo_nivelesDAO extends Conexion {
    public void registrarCatalogo_niveles(Catalogo_niveles nivelregistra) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("INSERT INTO catalogo_niveles (nivel,descripcion) values(?,?)");
            consulta.setInt(1, nivelregistra.getNivel());
            consulta.setString(2,nivelregistra.getDescripcion());
            consulta.executeUpdate();
            
        }
        catch(Exception e){
            System.out.println("Error en DAO catalogo_niveles "+ e);
        }
        finally{
            this.Cerrar();
        }
        
    }
    
    
     public List<Catalogo_niveles> listarNiveles() throws Exception{//esta lista es para el listado General
     List<Catalogo_niveles> lista;
        ResultSet resultadoset;
     try{
         this.Conectar();
         PreparedStatement consulta=this.getConexion().prepareCall("SELECT idcatalogo_niveles, nivel, descripcion FROM catalogo_niveles");
         resultadoset= consulta.executeQuery();
         lista =new ArrayList();
         while(resultadoset.next()){
             Catalogo_niveles niveles=new Catalogo_niveles();
             niveles.setIdcatalogo_niveles(resultadoset.getInt("idcatalogo_niveles"));
             niveles.setNivel(resultadoset.getInt("nivel"));
             niveles.setDescripcion(resultadoset.getString("descripcion"));
           
             lista.add(niveles);
         }
             
     }
     catch(Exception e){
         System.out.println("error en Catalogo_Niveles Dao metodo Listar"+e);
         throw e;
         
     }
     finally{
         this.Cerrar();
     }
     return lista;
    }
     
     public Catalogo_niveles elegirNiveles(Catalogo_niveles niveles) throws Exception{
        Catalogo_niveles nivelesdos=null;
        ResultSet resultadoset;
        try{
            this.Conectar();
             PreparedStatement consulta= this.getConexion().prepareStatement("SELECT idcatalogo_niveles, nivel, descripcion FROM catalogo_niveles WHERE idcatalogo_niveles=?");
            consulta.setInt(1, niveles.getIdcatalogo_niveles());
            resultadoset = consulta.executeQuery();
            while(resultadoset.next())
            {
              nivelesdos= new Catalogo_niveles();
              
             nivelesdos.setIdcatalogo_niveles(resultadoset.getInt("idcatalogo_niveles"));
              nivelesdos.setNivel(resultadoset.getInt("nivel"));
              nivelesdos.setDescripcion(resultadoset.getString("descripcion"));
             
            
            }
        }
        catch(Exception e){
           System.out.println("error en catalogo_nivelesDao metodo ElegirDato"+e);
        }
        finally{
           this.Cerrar();
        }
        return nivelesdos;
    }
     
    public void modificarCatalogoNiveles (Catalogo_niveles catalogoNivelesmodificar) throws Exception{
          
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("UPDATE catalogo_niveles SET nivel=?, descripcion=? WHERE idcatalogo_niveles=?");
             consulta.setInt(1, catalogoNivelesmodificar.getNivel());
            consulta.setString(2,catalogoNivelesmodificar.getDescripcion());
            consulta.setInt(3,catalogoNivelesmodificar.getIdcatalogo_niveles());
            
            consulta.executeUpdate();
        }
        catch(Exception e){
          System.out.println("error en catologo_nivelesDao metodo Modificar"+e);
        }
        finally{
           this.Cerrar();
        }
    }
    public Catalogo_niveles buscarIdCatalogo_niveles(int idcatalogo_niveles) throws Exception{
         Catalogo_niveles niveles = new Catalogo_niveles();
         ResultSet resultadoConsulta;
         try{
             this.Conectar();
             PreparedStatement consulta = this.getConexion().prepareCall("SELECT * FROM catalogo_niveles where idcatalogo_niveles=?");
             consulta.setInt(1, idcatalogo_niveles);
             resultadoConsulta = consulta.executeQuery();
              if(resultadoConsulta.next()){
             niveles.setIdcatalogo_niveles(resultadoConsulta.getInt("idcatalogo_niveles"));
              niveles.setNivel(resultadoConsulta.getInt("nivel"));
              niveles.setDescripcion(resultadoConsulta.getString("descripcion"));
              
              }
              resultadoConsulta.close();
              
         }catch(Exception ex){
             System.out.println("Error en Catalogo_NivelesDAO -> buscarIdCatalogo_Niveles "+ex);
             throw ex;
         }finally{
             this.Cerrar();
         }
         return niveles;
     }
    
    
}
