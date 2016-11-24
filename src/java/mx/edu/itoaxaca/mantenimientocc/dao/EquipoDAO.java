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
import mx.edu.itoaxaca.mantenimientocc.modelo.Equipo;

/**
 *
 * @author Jerusalen
 */
public class EquipoDAO extends Conexion{
    
    public void registrarEquipo(Equipo equiporegistra) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("INSERT INTO equipo (tipo,marca,modelo,numero_serie,folio_inventario) values(?,?,?,?,?)");
            consulta.setString(1, equiporegistra.getTipo());
            consulta.setString(2,equiporegistra.getMarca());
            consulta.setString(3,equiporegistra.getModelo());
            consulta.setString(4,equiporegistra.getNumero_serie());
            consulta.setInt(5, equiporegistra.getFolio_inventario());
            consulta.executeUpdate();
            
        }
        catch(Exception e){
            System.out.println("Error en DAO equipo "+ e);
        }
        finally{
            this.Cerrar();
        }
        
    }
    
    
     public List<Equipo> listarEquipo() throws Exception{
     List<Equipo> lista;
        ResultSet resultadoset;
     try{
         this.Conectar();
         PreparedStatement consulta=this.getConexion().prepareCall("SELECT idequipo, tipo,marca,modelo,numero_serie,folio_inventario FROM equipo");
         resultadoset= consulta.executeQuery();
         lista =new ArrayList();
         while(resultadoset.next()){
             Equipo equipo=new Equipo();
             equipo.setIdequipo(resultadoset.getInt("idequipo"));
             equipo.setTipo(resultadoset.getString("tipo"));
             equipo.setMarca(resultadoset.getString("marca"));
             equipo.setModelo(resultadoset.getString("modelo"));
             equipo.setNumero_serie(resultadoset.getString("numero_serie"));
             equipo.setFolio_inventario(resultadoset.getInt("folio_inventario"));
             
             
             
             lista.add(equipo);
         }
             
     }
     catch(Exception e){
         System.out.println("error en Orden_internaDao metodo Listar"+e);
         throw e;
         
     }
     finally{
         this.Cerrar();
     }
     return lista;
    }
     
     
     public Equipo elegirDatoEquipo(Equipo equipo) throws Exception{
        Equipo equipodos=null;
        ResultSet resultadosetequipo;
        try{
            this.Conectar();
             PreparedStatement consulta= this.getConexion().prepareStatement("SELECT idequipo, tipo, marca,modelo,numero_serie,folio_inventario FROM equipo WHERE idequipo=?");
            consulta.setInt(1, equipo.getIdequipo());
            resultadosetequipo = consulta.executeQuery();
            while(resultadosetequipo.next())
            {
              equipodos= new Equipo();
              
             equipodos.setIdequipo(resultadosetequipo.getInt("idequipo"));
              equipodos.setTipo(resultadosetequipo.getString("tipo"));
              equipodos.setMarca(resultadosetequipo.getString("marca"));
              equipodos.setModelo(resultadosetequipo.getString("modelo"));
              equipodos.setNumero_serie(resultadosetequipo.getString("numero_serie"));
              equipodos.setFolio_inventario(resultadosetequipo.getInt("folio_inventario"));
            
            }
        }
        catch(Exception e){
           System.out.println("error en departamentoDao metodo ElegirDato"+e);
        }
        finally{
           this.Cerrar();
        }
        return equipodos;
    }
     
     
    //metodo modificar equipo
     
     public void modificarEquipo (Equipo equipomodificar) throws Exception{
          
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("UPDATE equipo SET tipo=?, marca=?, modelo=?, numero_serie=?, folio_inventario=? WHERE idequipo=?");
             consulta.setString(1, equipomodificar.getTipo());
            consulta.setString(2,equipomodificar.getMarca());
            consulta.setString(3,equipomodificar.getModelo());
            consulta.setString(4,equipomodificar.getNumero_serie());
            consulta.setInt(5, equipomodificar.getFolio_inventario());
            consulta.setInt(6, equipomodificar.getIdequipo());
            consulta.executeUpdate();
        }
        catch(Exception e){
          System.out.println("error en EquipoDao metodo Modificar"+e);
        }
        finally{
           this.Cerrar();
        }
    }  
     
   //metodo eliminaf
      public void eliminarEquipo (Equipo equipoeliminar) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("DELETE FROM equipo WHERE idequipo=?");
            consulta.setInt(1,equipoeliminar.getIdequipo());
            consulta.executeUpdate();
        }
        catch(Exception e){
           throw e; 
        }
        finally{
           this.Cerrar();
        }
    }   
      
    public Equipo buscarIdEquipo(int idEquipo) throws Exception{
         Equipo equipo = new Equipo();
         ResultSet resultadoConsulta;
         try{
             this.Conectar();
             PreparedStatement consulta = this.getConexion().prepareCall("SELECT * FROM equipo where idequipo=?");
             consulta.setInt(1, idEquipo);
             resultadoConsulta = consulta.executeQuery();
              if(resultadoConsulta.next()){
             equipo.setIdequipo(resultadoConsulta.getInt("idequipo"));
              equipo.setTipo(resultadoConsulta.getString("tipo"));
              equipo.setMarca(resultadoConsulta.getString("marca"));
              equipo.setModelo(resultadoConsulta.getString("modelo"));
              equipo.setNumero_serie(resultadoConsulta.getString("numero_serie"));
              equipo.setFolio_inventario(resultadoConsulta.getInt("folio_inventario"));
              }
              resultadoConsulta.close();
              
         }catch(Exception ex){
             System.out.println("Error en DepartamentoDAO -> buscarIdDepartamento "+ex);
             throw ex;
         }finally{
             this.Cerrar();
         }
         return equipo;
     }
    
    
    
    
}
