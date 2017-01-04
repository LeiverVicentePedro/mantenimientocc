/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mx.edu.itoaxaca.mantenimientocc.dao.Periodo_semestralDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Periodo_semestral;

/**
 *
 * @author Jerusalen
 */
@ManagedBean
@ViewScoped
public class Periodo_semestralBEAN implements Serializable{
    private Periodo_semestral periodo=new Periodo_semestral();
    private List<Periodo_semestral> listaPeriodo;
    private List<Periodo_semestral> filterPeriodo;
    private String accion;

    public Periodo_semestral getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo_semestral periodo) {
        this.periodo = periodo;
    }

    public List<Periodo_semestral> getFilterPeriodo() {
        return filterPeriodo;
    }

    public void setFilterPeriodo(List<Periodo_semestral> filterPeriodo) {
        this.filterPeriodo = filterPeriodo;
    }
    

    

    public List<Periodo_semestral> getListaPeriodo() {
        return listaPeriodo;
    }

    public void setListaPeriodo(List<Periodo_semestral> listaPeriodo) {
        this.listaPeriodo = listaPeriodo;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiarPeriodo();
        this.accion = accion;
    }
    
    ///Metodos
    
    public void operarPeriodo() throws Exception{
        switch(accion)
        {
            case "Registrar":
                 this.registrarPeriodo();
                 this.listarPeriodo();
                break;
            case "Modificar":
                this.modificarPeriodo();
                this.listarPeriodo();
                break;
        }
    }
    
    public void limpiarPeriodo(){
        this.periodo.setPeriodo("");
      }
     //--Metodos para Registrar y Modificar
    
    private void registrarPeriodo() throws Exception{
        
        Periodo_semestralDAO periodoDao;
            try{
                periodoDao= new Periodo_semestralDAO();
                periodoDao.registrarPeriodo(periodo);
                this.listarPeriodo();
        
            }
            catch(Exception e)
            {
                System.out.println("error en Periodo BEAN -->Registrarperiodo"+e);
            }
            
    }   
    
      private void modificarPeriodo() throws Exception{
        Periodo_semestralDAO periododao;
            try{
                periododao= new Periodo_semestralDAO();
                periododao.modificarPeriodo(periodo);
                this.listarPeriodo();
            }
            catch(Exception e)
            {
                throw e;
            }
    } 
      
      
    public void listarPeriodo() throws Exception{
        Periodo_semestralDAO periododao;
        try{
            periododao=new Periodo_semestralDAO();
            listaPeriodo = periododao.listarPeriodo();
        }
        catch(Exception e){
            throw e;
        }
    }
    
    public void elegirDatoPeriodo(Periodo_semestral periodoElegirDato) throws Exception{
        Periodo_semestralDAO periododao;
        Periodo_semestral periodoTemporal;
        try{
            periododao= new Periodo_semestralDAO();
            periodoTemporal=periododao.elegirDatoPeriodo(periodoElegirDato);
            
            if(periodoTemporal != null){
                this.periodo = periodoTemporal;
                this.accion="Modificar";
            }
            }
        catch (Exception e){
            throw e;
        }
        
    }
    
     
    public void eliminarPeriodo(Periodo_semestral periodoEliminar) throws Exception{
       Periodo_semestralDAO periododao;
            try{
                periododao= new Periodo_semestralDAO();
                periododao.eliminarPeriodo(periodoEliminar);
                this.listarPeriodo();
            }
            catch(Exception e)
            {
                throw e;
            }
            
            
    } 
    
     
    
    
    
}
