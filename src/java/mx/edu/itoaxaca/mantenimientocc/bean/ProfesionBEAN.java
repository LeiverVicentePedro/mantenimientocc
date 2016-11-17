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
import mx.edu.itoaxaca.mantenimientocc.dao.ProfesionDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Profesion;

/**
 *
 * @author Jerusalen
 */
@ManagedBean
@ViewScoped
public class ProfesionBEAN implements Serializable{
    
    private Profesion profesion= new Profesion();
    private List<Profesion> profesionLista;
    private List<Profesion> filterProfesion;
    private String accion;
    

    public Profesion getProfesion() {
        return profesion;
    }

    public void setProfesion(Profesion profesion) {
        this.profesion = profesion;
    }

    public List<Profesion> getFilterProfesion() {
        return filterProfesion;
    }

    public void setFilterProfesion(List<Profesion> filterProfesion) {
        this.filterProfesion = filterProfesion;
    }
    
    
    

    public List<Profesion> getProfesionLista() {
        return profesionLista;
    }

    public void setProfesionLista(List<Profesion> profesionLista) {
        this.profesionLista = profesionLista;
    }
   
    
    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiarProfesion();
        this.accion = accion;
    }
    
    public void operarProfesion() throws Exception{
        switch(accion)
        {
            case "Registrar":
                 this.registrarProfesion();
                 this.listarProfesion();
                break;
            case "Modificar":
                this.modificarProfesion();
                this.listarProfesion();
                break;
        }
    }
    
    public void limpiarProfesion(){
        this.profesion.setNombre_profesion("");
      }
     //--Metodos para Registrar y Modificar
    
    private void registrarProfesion() throws Exception{
        
        ProfesionDAO profesiondao;
            try{
                profesiondao= new ProfesionDAO();
                profesiondao.registrarProfesion(profesion);
                this.listarProfesion();
        
            }
            catch(Exception e)
            {
                System.out.println("error en Profesion BEAN -->RegistrarPROFESION"+e);
            }
            
    }   
    
      private void modificarProfesion() throws Exception{
        ProfesionDAO profesion_dao;
            try{
                profesion_dao= new ProfesionDAO();
                profesion_dao.modificarProfesion(profesion);
                this.listarProfesion();
            }
            catch(Exception e)
            {
                throw e;
            }
    } 
      
      
    public void listarProfesion() throws Exception{
        ProfesionDAO profesiondao;
        try{
            profesiondao=new ProfesionDAO();
            profesionLista = profesiondao.listarProfesion();
        }
        catch(Exception e){
            throw e;
        }
    }
    
    public void elegirDatoProfesion(Profesion profesionElegirDato) throws Exception{
        ProfesionDAO profesiondao;
        Profesion profesionTemporal;
        try{
            profesiondao= new ProfesionDAO();
            profesionTemporal=profesiondao.elegirDatoProfesion(profesionElegirDato);
            
            if(profesionTemporal != null){
                this.profesion = profesionTemporal;
                this.accion="Modificar";
            }
            }
        catch (Exception e){
            throw e;
        }
        
    }
    
     
    public void eliminarProfesion(Profesion profesionEliminar) throws Exception{
        ProfesionDAO profesiondao;
            try{
                profesiondao= new ProfesionDAO();
                profesiondao.eliminarProfesion(profesionEliminar);
                this.listarProfesion();
            }
            catch(Exception e)
            {
                throw e;
            }
            
            
    } 
    
  
    
}
