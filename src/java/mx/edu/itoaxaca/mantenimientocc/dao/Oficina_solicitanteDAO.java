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
import mx.edu.itoaxaca.mantenimientocc.modelo.Oficina_solicitante;

/**
 *
 * @author leiver
 */
public class Oficina_solicitanteDAO extends Conexion{
    
    public void registrarDepartamento(Oficina_solicitante oficinaregistra) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("INSERT INTO oficinas_solicitantes (nombre_oficina, id_departamento,extencion,estatus) values(?,?,?,?)");
            consulta.setString(1, oficinaregistra.getNombreOficina());
            consulta.setInt(2,oficinaregistra.getDepartamento().getIddepartamento());
            consulta.setInt(3,oficinaregistra.getExtencion());
            consulta.setBoolean(4,oficinaregistra.getEstatus());
            
            consulta.executeUpdate();
        }
        catch(Exception e){
           System.out.println("error en DAO"); 
        }
        finally{
           this.Cerrar();
        }
    }
    
  public List<Oficina_solicitante> listarOficina() throws Exception{
     List<Oficina_solicitante> listaOfi;
        ResultSet resultadoset;
     try{
         this.Conectar();
         PreparedStatement consulta=this.getConexion().prepareCall("SELECT idoficinas, nombre_oficina, id_departamento,extencion, estatus FROM oficinas_solicitantes");
         resultadoset= consulta.executeQuery();
         listaOfi =new ArrayList();
         while(resultadoset.next()){
             Oficina_solicitante oficina=new Oficina_solicitante();
             oficina.setIdOficinaSolicitante(resultadoset.getInt("idoficinas"));
             oficina.setNombreOficina(resultadoset.getString("nombre_oficina"));
             oficina.setDepartamento(new DepartamentoDAO().buscarIdDepartamento(resultadoset.getInt("id_departamento")));
             oficina.setExtencion(resultadoset.getInt("extencion"));
             oficina.setEstatus(resultadoset.getBoolean("estatus"));
             
             listaOfi.add(oficina);
         }
             
     }
     catch(Exception e){
         System.out.println("error en departamentoDao metodo Listar"+e);
         throw e;
         
     }
     finally{
         this.Cerrar();
     }
     return listaOfi;
    }
  
  public Oficina_solicitante elegirDatoOficina(Oficina_solicitante oficina) throws Exception{
        Oficina_solicitante oficinaDos=null;
        ResultSet resultadosetoficina;
        try{
            this.Conectar();
             PreparedStatement consulta= this.getConexion().prepareStatement("SELECT idoficinas, nombre_oficina, id_departamento, extencion, estatus FROM oficinas_solicitantes WHERE idoficinas=?");
            consulta.setInt(1, oficina.getIdOficinaSolicitante());
            resultadosetoficina = consulta.executeQuery();
            while(resultadosetoficina.next())
            {
              oficinaDos= new Oficina_solicitante();
              
             oficinaDos.setIdOficinaSolicitante(resultadosetoficina.getInt("idoficinas"));
              oficinaDos.setNombreOficina(resultadosetoficina.getString("nombre_oficina"));
              oficinaDos.setDepartamento(new DepartamentoDAO().buscarIdDepartamento(resultadosetoficina.getInt("id_departamento")));
             oficinaDos.setExtencion(resultadosetoficina.getInt("Extencion"));
              oficinaDos.setEstatus(resultadosetoficina.getBoolean("estatus"));
            }
        }
        catch(Exception e){
           System.out.println("error en oficinaDao metodo ElegirDato"+e);
        }
        finally{
           this.Cerrar();
        }
        return oficinaDos;
    }
     
     
    //metodo modificar departamento
     
     public void modificarOficina (Oficina_solicitante oficinamodificar) throws Exception{
          
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("UPDATE oficinas_solicitantes SET nombre_oficina=?, id_departamento=?, extencion=?, estatus=? WHERE idoficinas=?");
            consulta.setString(1, oficinamodificar.getNombreOficina());
            consulta.setInt(2,oficinamodificar.getDepartamento().getIddepartamento());
            consulta.setInt(3,oficinamodificar.getExtencion());
            consulta.setBoolean(4,oficinamodificar.getEstatus());
            consulta.setInt(5, oficinamodificar.getIdOficinaSolicitante());
            consulta.executeUpdate();
        }
        catch(Exception e){
          System.out.println("error en OficinaDao metodo Modificar"+e);
        }
        finally{
           this.Cerrar();
        }
    }  
     
   //metodo eliminaf
      public void eliminarOficina (Oficina_solicitante oficinaeliminar) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("DELETE FROM oficinas_solicitantes WHERE idoficinas=?");
            consulta.setInt(1,oficinaeliminar.getIdOficinaSolicitante());
            consulta.executeUpdate();
        }
        catch(Exception e){
           throw e; 
        }
        finally{
           this.Cerrar();
        }
    } 

public Oficina_solicitante buscarOficina(int idoficina) throws Exception{
        Oficina_solicitante oficina = null;
        try{
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareCall("SELECT * FROM oficinas_solicitantes where idoficinas=?");
            consulta.setInt(1, idoficina);
            ResultSet resultadoConsulta = consulta.executeQuery();
            if(resultadoConsulta.next()==true){
                oficina = new Oficina_solicitante();
            oficina.setIdOficinaSolicitante(resultadoConsulta.getInt("idoficinas"));
            oficina.setNombreOficina(resultadoConsulta.getString("nombre_oficina"));
            oficina.setDepartamento(new DepartamentoDAO().buscarIdDepartamento(resultadoConsulta.getInt("id_departamento")));
            oficina.setExtencion(resultadoConsulta.getInt("extencion"));
            oficina.setEstatus(resultadoConsulta.getBoolean("estatus"));
            }else{
                System.out.println("el objeto esta vacio");
            }
            resultadoConsulta.close();
             return oficina;
        }catch(Exception ex){
            System.out.println("Error en Oficina_solicitanteDAO -> buscarOficinaPorIdOficina "+ex);
            throw ex;
        }finally{
            this.Cerrar();
        }
        
    }

public List<Oficina_solicitante> buscarOficinaSolicitantePorIdDepartamento(int idDepartamento) throws Exception{
         
         List<Oficina_solicitante> listaOficinaDeUnDepartamento = null;
         ResultSet resultadoConsulta;
         try{
             this.Conectar();
             PreparedStatement consulta = this.getConexion().prepareCall("SELECT * FROM oficinas_solicitantes where id_departamento=?");
             consulta.setInt(1, idDepartamento);
             resultadoConsulta = consulta.executeQuery();
             listaOficinaDeUnDepartamento = new ArrayList();
             while(resultadoConsulta.next()){
             Oficina_solicitante oficina = new Oficina_solicitante();
             oficina.setIdOficinaSolicitante(resultadoConsulta.getInt("idoficinas"));
             oficina.setNombreOficina(resultadoConsulta.getString("nombre_oficina"));
             oficina.setDepartamento(new DepartamentoDAO().buscarIdDepartamento(resultadoConsulta.getInt("id_departamento")));
             oficina.setExtencion(resultadoConsulta.getInt("extencion"));
             oficina.setEstatus(resultadoConsulta.getBoolean("estatus"));
             
             listaOficinaDeUnDepartamento.add(oficina);
              }
              resultadoConsulta.close();
              
         }catch(Exception ex){
             System.out.println("Error en DepartamentoDAO -> buscarDepartamentoPorIdArea "+ex);
             throw ex;
         }finally{
             this.Cerrar();
         }
         return listaOficinaDeUnDepartamento;
     }

    
    
  
}
