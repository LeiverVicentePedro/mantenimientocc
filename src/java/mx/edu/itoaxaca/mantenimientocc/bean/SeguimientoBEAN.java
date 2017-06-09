/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import mx.edu.itoaxaca.mantenimientocc.dao.SeguimientoDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Seguimiento;
import mx.edu.itoaxaca.mantenimientocc.modelo.Solicitud_mc;

/**
 *
 * @author Jerusalen
 */
@ManagedBean
@ViewScoped
public class SeguimientoBEAN implements Serializable{
    
  private Seguimiento seguimiento= new Seguimiento();
  
  Solicitud_mc solicitudSeguimiento;
  private List<Seguimiento> listarSeguimiento;
  private List<Seguimiento> filterSeguimiento;


    public List<Seguimiento> getListarSeguimiento() {
        return listarSeguimiento;
    }

    public void setListarSeguimiento(List<Seguimiento> listarSeguimiento) {
        this.listarSeguimiento = listarSeguimiento;
    }

    public List<Seguimiento> getFilterSeguimiento() {
        return filterSeguimiento;
    }

    public void setFilterSeguimiento(List<Seguimiento> filterSeguimiento) {
        this.filterSeguimiento = filterSeguimiento;
    }
 
    //Objeto de la clase seguimiento
    public Seguimiento getSeguimiento() {
        return seguimiento;
    }

    public void setSeguimiento(Seguimiento seguimiento) {
        this.seguimiento = seguimiento;
    }
    
   ///////////esto es get y set de objeto Solictud_mc
    public Solicitud_mc getSolicitudSeguimiento() {
        return solicitudSeguimiento;
    }

    public void setSolicitudSeguimiento(Solicitud_mc solicitudSeguimiento) {
        this.solicitudSeguimiento = solicitudSeguimiento;
    }
  
  public void existeSolicitud(){//esto es para usar en la vista un preRender que llamara al dato para mostrar en la pagina el folio de Solicitud enDetalle de Seguimiento 
        solicitudSeguimiento = (Solicitud_mc) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("solicitudSeguimiento");
        System.out.println("aqui llamo solicituden Seguimiento");
       
    }
  
    public void listarSeguimiento() throws Exception{
        SeguimientoDAO seguimientodao;
        try{
            
            seguimientodao=new SeguimientoDAO();
            listarSeguimiento = seguimientodao.listarSeguimiento();
        }
        catch(Exception e){
            throw e;
        }
    }
   
  
    
}
