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
import mx.edu.itoaxaca.mantenimientocc.modelo.Periodo_semestral;

/**
 *
 * @author leiver
 */
public class Periodo_semestralDAO extends Conexion{
    
    public void registrarPeriodo(Periodo_semestral periodo) throws Exception{
        try{
            this.Conectar();
            PreparedStatement insertar = this.getConexion().prepareStatement("INSERT INTO periodo_semestral (periodo) VALUES(?)");
            insertar.setString(1, periodo.getPeriodo());
            insertar.executeUpdate();
        }catch(Exception ex){
            System.out.println("Error en Periodo_semestralDAO -> insetarPeriodo "+ex);
            throw ex;
        }finally{
            this.Cerrar();
        }
    }
    
    public List<Periodo_semestral> listarPeriodo() throws Exception{
        List<Periodo_semestral> listaPeriodo;
        ResultSet resultado;
        try{
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("SELECT * FROM periodo_semestral;");
            resultado = consulta.executeQuery();
            listaPeriodo = new ArrayList();
            while(resultado.next()){
                Periodo_semestral periodo = new Periodo_semestral();
                periodo.setIdperiodo_semestral(resultado.getInt("idperiodo_semestral"));
                periodo.setPeriodo(resultado.getString("periodo"));
                listaPeriodo.add(periodo);
            }
            return listaPeriodo;
        }catch(Exception ex){
            System.out.println("error en Periodo_semestralDAO -> listarPeriodos "+ex);
            throw ex;
        }finally{
            this.Cerrar();
        }
    }
    
    public Periodo_semestral elegirDatoPeriodo(Periodo_semestral periodoElegir) throws Exception{
        Periodo_semestral periodoElegirdos=null;
        ResultSet resultadosetElegirPeriodo;
        
        try{
            this.Conectar();
             PreparedStatement consulta= this.getConexion().prepareStatement("SELECT * FROM periodo_semestral WHERE idperiodo_semestral=?");
            consulta.setInt(1, periodoElegir.getIdperiodo_semestral());
            resultadosetElegirPeriodo = consulta.executeQuery();
            while(resultadosetElegirPeriodo.next())
            {
              periodoElegirdos= new Periodo_semestral();
              periodoElegirdos.setIdperiodo_semestral(resultadosetElegirPeriodo.getInt("idperiodo_semestral"));
              periodoElegirdos.setPeriodo(resultadosetElegirPeriodo.getString("periodo"));
              
            }
        }
        catch(Exception e){
           throw e; 
        }
        finally{
           this.Cerrar();
        }
        return periodoElegirdos;
    }
    
    public void modificarPeriodo(Periodo_semestral periodoModificar) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("UPDATE periodo_semestral SET periodo=? WHERE idperiodo_semestral=?");
            consulta.setString(1, periodoModificar.getPeriodo());
            consulta.setInt(2, periodoModificar.getIdperiodo_semestral());
            consulta.executeUpdate();
            
        }
        catch(Exception e){
         throw e;
        }
        finally{
            this.Cerrar();
        }
        
    }
    
    public void eliminarPeriodo(Periodo_semestral periodoEliminar) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("DELETE FROM periodo_semestral WHERE idperiodo_semestral=?");
            consulta.setInt(1,periodoEliminar.getIdperiodo_semestral());
            consulta.executeUpdate();
            
            
        }
        catch(Exception e){
            throw e;
        }
        finally{
            this.Cerrar();
        }
        
    }
    
  
    
    
}
