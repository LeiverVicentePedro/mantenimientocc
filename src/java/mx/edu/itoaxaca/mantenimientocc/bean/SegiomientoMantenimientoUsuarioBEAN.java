/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import mx.edu.itoaxaca.mantenimientocc.dao.SeguimientoDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.Solicitud_mcDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Seguimiento;
import mx.edu.itoaxaca.mantenimientocc.modelo.Solicitud_mc;

/**
 *
 * @author leiver
 */
@ManagedBean
@ViewScoped
public class SegiomientoMantenimientoUsuarioBEAN implements Serializable{
    
    private Solicitud_mc solicitudABuscar = new Solicitud_mc();
    private Seguimiento seguimientoEncontrado = new Seguimiento();
    private String noExisteSolicitud;
    private String vista;

    public String getVista() {
        return vista;
    }

    public void setVista(String vista) {
        this.vista = vista;
    }
    
    public Solicitud_mc getSolicitudABuscar() {
        return solicitudABuscar;
    }

    public void setSolicitudABuscar(Solicitud_mc solicitudABuscar) {
        this.solicitudABuscar = solicitudABuscar;
    }
    
    public Seguimiento getSeguimientoEncontrado() {
        return seguimientoEncontrado;
    }

    public void setSeguimientoEncontrado(Seguimiento seguimientoEncontrado) {
        this.seguimientoEncontrado = seguimientoEncontrado;
    }

    public String getNoExisteSolicitud() {
        return noExisteSolicitud;
    }

    public void setNoExisteSolicitud(String noExisteSolicitud) {
        this.noExisteSolicitud = noExisteSolicitud;
    }
    public String redirige(){//para redirigir
        return vista;
    }
    /*Metodo que busca y llena el objeto de Seguimiento si este tiene algo o no*/
    public void redirigeVistaSiExisteSolicitud() throws Exception{
        try{
         solicitudABuscar = new Solicitud_mcDAO().identificadorDeSolicitud(solicitudABuscar.getFolio());
         if( new SeguimientoDAO().elegirDatoSeguimiento(solicitudABuscar)!=null){
             seguimientoEncontrado = new SeguimientoDAO().elegirDatoSeguimiento(solicitudABuscar);
             setNoExisteSolicitud("ACTIVO");
             alNavegadorSiExiste();
         }if(new SeguimientoDAO().elegirDatoSeguimiento(solicitudABuscar)==null){
             setNoExisteSolicitud("NO EXISTE");
             System.out.println("seguimiento "+seguimientoEncontrado.getIdseguimiento());
             System.out.println(noExisteSolicitud);
             alNavegadorNoExiste();
         }
        }catch(Exception ex){
            System.out.println("Error en SeguimientoMantenimientoUsuarioBEAN -> redirigeVistaSiExisteSolicitud "+ex);
            throw ex;
        }
    }
    
    public void alNavegadorSiExiste(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("existe", noExisteSolicitud);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("seguimiento", seguimientoEncontrado);
    }
    
    public void alNavegadorNoExiste(){
        Solicitud_mc mc = new Solicitud_mc();
        mc.setFolio("######-##");
        seguimientoEncontrado.setId_solicitud(mc);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("existe", noExisteSolicitud);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("seguimiento", seguimientoEncontrado);
    }
    
    public void recuperaNavegador(){
        seguimientoEncontrado = (Seguimiento) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("seguimiento");
        noExisteSolicitud = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("existe");
    }
    
}
