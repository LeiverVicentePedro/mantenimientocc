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
import mx.edu.itoaxaca.mantenimientocc.dao.Asigna_solicitudDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Asigna_solicitud;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;

/**
 *
 * @author Jerusalen
 */
@ManagedBean
@ViewScoped
public class Asigna_solicitudBEAN implements Serializable{
    

    private Asigna_solicitud asigna_Solicitud=new Asigna_solicitud();
    

    public Asigna_solicitud getAsigna_Solicitud() {
        return asigna_Solicitud;
    }

    public void setAsigna_Solicitud(Asigna_solicitud asigna_Solicitud) {
        this.asigna_Solicitud = asigna_Solicitud;
    }
    
    
    
     public void registrarAsignaSolicitu() throws Exception {
        Asigna_solicitudDAO asignaSolicitudDao;
        
            try {
            FacesContext contexto = FacesContext.getCurrentInstance(); //paraq entrar ql dom del navegador
            Usuario usuarioVive = (Usuario) contexto.getExternalContext().getSessionMap().get("usuario");//llamo a  la etiqueta usuario que es un objeto que ya debe
            //existir dentro del navegador
            asignaSolicitudDao = new Asigna_solicitudDAO();
            
           
            
            asigna_Solicitud.setId_usuario_personal_jefe(usuarioVive);
            asigna_Solicitud.setFecha(new java.sql.Date(new java.util.Date().getTime()));//fecha sistema
            asignaSolicitudDao.registrarAsignarSolicitud(asigna_Solicitud);
           // this.limpiarAsignaSolicitud();
    
            System.out.println("fecha del sistema " + asigna_Solicitud.getFecha());
           
          //  this.limpiar();

        } catch (Exception ex) {
            System.out.println("Error en Asigna-Solicitud-BEAN -> generarAsignacionSolicitud " + ex);
            throw ex;
        }
    }
     
     public void limpiarAsignaSolicitud(){
        this.asigna_Solicitud.setId_solicitud(null);
        this.asigna_Solicitud.setId_usuario_personal(null);
       
    }
    
}
