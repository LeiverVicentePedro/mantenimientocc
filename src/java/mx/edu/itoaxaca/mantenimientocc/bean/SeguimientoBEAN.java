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
import mx.edu.itoaxaca.mantenimientocc.dao.SeguimientoDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Seguimiento;
import mx.edu.itoaxaca.mantenimientocc.modelo.Solicitud_mc;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;

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

    public List<Seguimiento> getListarSeguimiento() {
        return listarSeguimiento;
    }

    public void setListarSeguimiento(List<Seguimiento> listarSeguimiento) {
        this.listarSeguimiento = listarSeguimiento;
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
  
  public void existeSolicitud(){//esto es para usar en la vista un preRender que llamara al dato para mostrar en la pagina el folio de Solicitud en Orden De Trabajo lo mismo se hace para Orden Interna 
        solicitudSeguimiento = (Solicitud_mc) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("solicitudSeguimiento");
       
    }
  
  //metodo Registrar
  
   public void registrarSeguimiento() throws Exception {
        SeguimientoDAO seguimientoDao;
        
        try {
            FacesContext contexto = FacesContext.getCurrentInstance(); //paraq entrar ql dom del navegador
            Usuario usuarioVive = (Usuario) contexto.getExternalContext().getSessionMap().get("usuario");//llamo a  la etiqueta usuario que es un objeto que ya debe
            
            //existir dentro del navegador
            FacesContext contextoOT = FacesContext.getCurrentInstance(); //paraq entrar ql dom del navegador
            solicitudSeguimiento = (Solicitud_mc) contextoOT.getExternalContext().getSessionMap().get("solicitudSeguimiento");
            ///
            seguimientoDao = new SeguimientoDAO();
            
            seguimiento.setFecha(new java.sql.Date(new java.util.Date().getTime()));//fecha sistema
            seguimiento.setId_usuario_personal(usuarioVive);
            seguimiento.setId_solicitud(solicitudSeguimiento);
            seguimiento.setEstado_solicitud(true);
            seguimiento.setEstado_asignacion(true);
            seguimiento.setId_usuario_solicitante(solicitudSeguimiento.getId_usuario());
            seguimientoDao.registrarSeguimiento(seguimiento);
            
             System.out.println("Datos  " + seguimiento.getId_usuario_personal()+"\n"+
                    seguimiento.getId_solicitud()+"\n "+
                    seguimiento.getId_usuario_solicitante());
             } 
        catch (Exception ex) {
            System.out.println("Error en SeguimientoBEAN -> Registrar-Seguimiento " + ex);
            throw ex;
        }
    
      }

   
   
   public void modificarSeguimiento() {
        SeguimientoDAO seguimientodao;
        try {
            seguimientodao = new SeguimientoDAO();
            seguimientodao.modificarSeguimiento(seguimiento);
        } catch (Exception ex) {

        }
    }
   
    public void limpiar() {
        this.seguimiento.setId_solicitud(null);
        
        
        
    }
   
  
    
}
