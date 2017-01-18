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
import mx.edu.itoaxaca.mantenimientocc.dao.DetalleSeguimientoDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.SeguimientoDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.DetalleSeguimiento;
import mx.edu.itoaxaca.mantenimientocc.modelo.Seguimiento;
import mx.edu.itoaxaca.mantenimientocc.modelo.Solicitud_mc;


/**
 *
 * @author Jerusalen
 */
@ManagedBean
@ViewScoped
public class DetalleSeguimientoBEAN implements Serializable{
    
    private DetalleSeguimiento detalleSeguimiento=new DetalleSeguimiento();
   Seguimiento seguimiento;
   Solicitud_mc solicitud;

    public Solicitud_mc getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud_mc solicitud) {
        this.solicitud = solicitud;
    }
   public void existeSolicitud(){
        solicitud = (Solicitud_mc) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("solicitudAsignadaOR");
    }
   

    public Seguimiento getSeguimiento() {
        return seguimiento;
    }

    public void setSeguimiento(Seguimiento seguimiento) {
        this.seguimiento = seguimiento;
    }
   
   
    public DetalleSeguimiento getDetalleSeguimiento() {
        return detalleSeguimiento;
    }

    public void setDetalleSeguimiento(DetalleSeguimiento detalleSeguimiento) {
        this.detalleSeguimiento = detalleSeguimiento;
    }
    
    
    
    public void registrarDetalleSeguimiento() throws Exception{
        DetalleSeguimientoDAO detalleSeguimientodao;
        SeguimientoDAO seguimientodao;
            try{
                detalleSeguimientodao= new DetalleSeguimientoDAO();
                FacesContext contextoOT = FacesContext.getCurrentInstance(); //paraq entrar ql dom del navegador
            solicitud = (Solicitud_mc) contextoOT.getExternalContext().getSessionMap().get("solicitudAsignadaOR");
                seguimientodao = new SeguimientoDAO();//el seguimientodao es para poder tener acceso a la clase SeguimientoDao y mandar a llamar al metodo elegirDatoSeguimiento
           Seguimiento solicitudSeguimientoTemporal = seguimientodao.elegirDatoSeguimiento(solicitud);
              
                detalleSeguimiento.setId_seguimiento(solicitudSeguimientoTemporal);
                detalleSeguimiento.setFecha(new java.sql.Date(new java.util.Date().getTime()));//fecha sistema
                detalleSeguimientodao.registrarDetalleSeguimiento(detalleSeguimiento);
                this.limpiarDetalleSeguimiento();
                
            }
            catch(Exception e)
            {
                System.out.println("error en DetalleSeguimiento BEAN -->RegistrarDetalleSeguimiento"+e);
            }
    }
    
    public void limpiarDetalleSeguimiento()
    {
        this.detalleSeguimiento.setEstado("Seleccionar");
        this.detalleSeguimiento.setDescripcion(" ");
        
    }
    
}
