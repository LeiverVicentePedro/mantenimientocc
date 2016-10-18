
package mx.edu.itoaxaca.mantenimientocc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mx.edu.itoaxaca.mantenimientocc.conexion.Conexion;
import mx.edu.itoaxaca.mantenimientocc.modelo.Area;


public class AreaDAO extends Conexion {
    
    public void registrarArea (Area arearegistra) throws Exception{
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
    
    public List<Area> listarArea() throws Exception{
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
    
    public Area elegirDatoArea(Area area) throws Exception{
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
            
            consulta.executeUpdate();
        }
        catch(Exception e){
           throw e; 
        }
        finally{
           this.Cerrar();
        }
    }  
    
    public void eliminarArea (Area areaeliminar) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("DELETE FROM area WHERE idarea=?");
            consulta.setInt(1,areaeliminar.getIdarea());
            consulta.executeUpdate();
        }
        catch(Exception e){
           throw e; 
        }
        finally{
           this.Cerrar();
        }
    }   
    
    public Area buscarIdArea(int idarea) throws Exception{
        Area areabusca= new Area();
        ResultSet resultadosetbusca;
      try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareCall("SELECT * FROM area WHERE idarea=?");
            consulta.setInt(1,idarea);
            resultadosetbusca=consulta.executeQuery();
            if(resultadosetbusca.next()){
            areabusca.setIdarea(resultadosetbusca.getInt("idarea"));
            areabusca.setNombre_area(resultadosetbusca.getString("nombre_area"));
            areabusca.setEstatus(resultadosetbusca.getBoolean("estatus"));
            }
            resultadosetbusca.close();
            
        }
        catch(Exception e){
            System.out.println("error en AreaDAO->buscarIdArea "+e);
           throw e; 
        }
        finally{
           this.Cerrar();
        }  
        return areabusca;
    }
    
}

