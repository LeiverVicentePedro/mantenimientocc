
package mx.edu.itoaxaca.mantenimientocc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mx.edu.itoaxaca.mantenimientocc.conexion.Conexion;
import mx.edu.itoaxaca.mantenimientocc.modelo.Area;


public class AreaDAO extends Conexion {
    
    public void registrar (Area arearegistra) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("INSERT INTO area (nombre_area,estatus) values(?,?)");
            consulta.setString(1, arearegistra.getNombre_area());
            consulta.setBoolean(2,arearegistra.getEstatus());
            
            consulta.executeUpdate();
        }
        catch(Exception e){
           throw e; 
        }
        finally{
           this.Cerrar();
        }
    }
    
    public List<Area> listar() throws Exception{
     List<Area> lista;
        ResultSet resultadoset;
     try{
         this.Conectar();
         PreparedStatement consulta=this.getConexion().prepareCall("SELECT idarea, nombre_area, estatus FROM area");
         resultadoset= consulta.executeQuery();
         lista =new ArrayList();
         while(resultadoset.next()){
             Area area=new Area();
             area.setIdarea(resultadoset.getInt("idarea"));
             area.setNombre_area(resultadoset.getString("nombre_area"));
             area.setEstatus(resultadoset.getBoolean("estatus"));
             
             lista.add(area);
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
    
    public Area elegirDato(Area area) throws Exception{
        Area areados=null;
        ResultSet resultadoset;
        
        try{
            this.Conectar();
             PreparedStatement consulta= this.getConexion().prepareStatement("SELECT idarea, nombre_area,estatus FROM area WHERE idarea=?");
            consulta.setInt(1, area.getIdarea());
            resultadoset = consulta.executeQuery();
            while(resultadoset.next())
            {
              areados= new Area();
              areados.setIdarea(resultadoset.getInt("idarea"));
              areados.setNombre_area(resultadoset.getString("nombre_area"));
              areados.setEstatus(resultadoset.getBoolean("estatus"));
            }
        }
        catch(Exception e){
           throw e; 
        }
        finally{
           this.Cerrar();
        }
        return areados;
    }
    
    
    public void modificarArea (Area areamodificar) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("UPDATE area SET nombre_area=?, estatus=? WHERE idarea=?");
            consulta.setString(1, areamodificar.getNombre_area());
            consulta.setBoolean(2,areamodificar.getEstatus());
            consulta.setInt(3,areamodificar.getIdarea());
            
            consulta.executeQuery();
        }
        catch(Exception e){
           throw e; 
        }
        finally{
           this.Cerrar();
        }
    }    
    
}
