/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import mx.edu.itoaxaca.mantenimientocc.dao.Refaccion_empleadaDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Refaccion_empleada;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
/**
 *
 * @author Jerusalen
 */


@ManagedBean
@ViewScoped
public class Refaccion_empleadaBEAN implements Serializable{
    
    private Refaccion_empleada refaccion= new Refaccion_empleada();
    private List<Refaccion_empleada> listaRefaccion;
    private List<Refaccion_empleada> filterRefaccion;
    private String accion;

    public Refaccion_empleada getRefaccion() {
        return refaccion;
    }

    public void setRefaccion(Refaccion_empleada refaccion) {
        this.refaccion = refaccion;
    }

    public List<Refaccion_empleada> getListaRefaccion() {
        return listaRefaccion;
    }

    public void setListaRefaccion(List<Refaccion_empleada> listaRefaccion) {
        this.listaRefaccion = listaRefaccion;
    }

    public List<Refaccion_empleada> getFilterRefaccion() {
        return filterRefaccion;
    }

    public void setFilterRefaccion(List<Refaccion_empleada> filterRefaccion) {
        this.filterRefaccion = filterRefaccion;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

   /////////Eventos para poder usar CheckBox en la Tabla
      public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Selected", ((Refaccion_empleada) event.getObject()).getDescripcion());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Unselected", ((Refaccion_empleada) event.getObject()).getDescripcion());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    ///////////////////////////////////////////////////////////7
   
    
     //metodo Registrar equipo
    public void registrarRefaccion() throws Exception{
        Refaccion_empleadaDAO refaccionDao;
        
            try{
                
               refaccionDao= new Refaccion_empleadaDAO();
                refaccionDao.registrarRefaccion(refaccion);
                this.listarRefaccion();
            }
            
            catch(Exception e)
            {
                System.out.println("error en Refaccion BEAN -->RegistrarRefaccionBEAN"+e);
            }
    }
    
     //metodo elegir dato de equipó 
    public void elegirDatoRefaccion(Refaccion_empleada refaccionElegirDato) throws Exception{
        Refaccion_empleadaDAO refacciondao;
        Refaccion_empleada refaccionTemporal;
        try{
            refacciondao= new Refaccion_empleadaDAO();
            refaccionTemporal = new Refaccion_empleada();
            refaccionTemporal = refacciondao.elegirDatoRefaccion(refaccionElegirDato);
            
            if(refaccionTemporal != null){
                this.refaccion = refaccionTemporal;
                this.accion="Modificar";
            }
            }
        catch (Exception e){
            throw e;
        }
        
    }
    
     public void modificarRefaccion() throws Exception{
        Refaccion_empleadaDAO refacciondao;
            try{
                refacciondao= new Refaccion_empleadaDAO();
                refacciondao.modificarRefaccion(refaccion);
               this.listarRefaccion();
            }
            catch(Exception e)
            {
                System.out.println("error en refaccionBEAN metodo -->modificar"+e);
            }
    } 
     
     //metodo operar para elegir la opcion de registrar o modificar
     
     public void operarRefaccion() throws Exception{
        switch(accion)
        {
            case "Registrar":
                this.registrarRefaccion();
               this.limpiarRefaccion();
                break;
            case "Modificar":
                this.modificarRefaccion();
               this.limpiarRefaccion();
                break;
        }
    }
     
     public void eliminarRefaccion(Refaccion_empleada refaccionEliminar) throws Exception{
       Refaccion_empleadaDAO refacciondao;
            try{
                refacciondao= new Refaccion_empleadaDAO();
                refacciondao.eliminarRefaccion(refaccionEliminar);
                this.listarRefaccion();
            }
            catch(Exception e)
            {
                throw e;
            }
    }
     
     public void limpiarRefaccion(){
        this.refaccion.setDescripcion("");
        this.refaccion.setNumero_serie("");
        this.refaccion.setPrecio(0.00);
        this.refaccion.setCantidad(0);
    }
     
      //Metodo ´para listar 
    
    public void listarRefaccion() throws Exception{
        Refaccion_empleadaDAO refacciondao;
        try{
            refacciondao=new Refaccion_empleadaDAO();
            listaRefaccion = refacciondao.listarRefaccion();
        }
        catch(Exception e){
            System.out.println("error en RefaccionBEAN --> listarRefaccionBEAN"+e);
        }
    }

    
    
}

