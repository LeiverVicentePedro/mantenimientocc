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
import javax.faces.context.FacesContext;
import mx.edu.itoaxaca.mantenimientocc.dao.Orden_trabajoDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Orden_trabajo;
import mx.edu.itoaxaca.mantenimientocc.modelo.Solicitud_mc;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;

/**
 *
 * @author Jerusalen
 */
@ManagedBean
@ViewScoped
public class Orden_trabajoBEAN implements Serializable{
    
     private Orden_trabajo orden_trabajo=new Orden_trabajo();
    private List<Orden_trabajo> listaOrden_trabajo;
    Solicitud_mc folioDesdeAsignacion;
    
    
//-------------------Getter Y Setter
    public List<Orden_trabajo> getListaOrden_trabajo() {
        return listaOrden_trabajo;
    }

    public void setListaOrden_trabajo(List<Orden_trabajo> listaOrden_trabajo) {
        this.listaOrden_trabajo = listaOrden_trabajo;
    }

    public Orden_trabajo getOrden_trabajo() {
        return orden_trabajo;
    }
    
     public void existeSolicitud(){
        folioDesdeAsignacion = (Solicitud_mc) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("solicitudAsignada");
    }

    public void setOrden_trabajo(Orden_trabajo orden_trabajo) {
        this.orden_trabajo = orden_trabajo;
    }

    public Solicitud_mc getFolioDesdeAsignacion() {
        return folioDesdeAsignacion;
    }

    public void setFolioDesdeAsignacion(Solicitud_mc folioDesdeAsignacion) {
        this.folioDesdeAsignacion = folioDesdeAsignacion;
    }

   
 //-------------------------------------------------------------------------------------
    
    //-Registrar
    
      public void registrarOrden() throws Exception {
        Orden_trabajoDAO ordenTrabajoDao;
        
        try {
            FacesContext contexto = FacesContext.getCurrentInstance(); //paraq entrar ql dom del navegador
            Usuario usuarioVive = (Usuario) contexto.getExternalContext().getSessionMap().get("usuario");//llamo a  la etiqueta usuario que es un objeto que ya debe
            //existir dentro del navegador
            
            ///
            ordenTrabajoDao = new Orden_trabajoDAO();
            orden_trabajo.setId_usuario_personal(usuarioVive);
            orden_trabajo.setFecha_realizacion(new java.sql.Date(new java.util.Date().getTime()));//fecha sistema
            orden_trabajo.setId_solicitudmc(folioDesdeAsignacion);
            ordenTrabajoDao.registrarOrdenTrabajo(orden_trabajo);
            
             } catch (Exception ex) {
            System.out.println("Error en Orden-TrabajoBEAN -> generarOrden-trabajo " + ex);
            throw ex;
        }
    
      }
    
    
}
