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
import mx.edu.itoaxaca.mantenimientocc.dao.Asigna_solicitudDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.SeguimientoDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.Solicitud_mcDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Asigna_solicitud;
import mx.edu.itoaxaca.mantenimientocc.modelo.Seguimiento;
import mx.edu.itoaxaca.mantenimientocc.modelo.Solicitud_mc;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;

/**
 *
 * @author Jerusalen
 */
@ManagedBean
@ViewScoped
public class Asigna_solicitudBEAN implements Serializable{
    

    private Asigna_solicitud asigna_Solicitud=new Asigna_solicitud();
    private List<Asigna_solicitud> listarAsignacionSolicitudes;
    private List<Asigna_solicitud> filterAsignar;
    private List<Asigna_solicitud> listaAsinacionesDeUsuarios;
  

    public List<Asigna_solicitud> getListaAsinacionesDeUsuarios() {
        return listaAsinacionesDeUsuarios;
    }

    public void setListaAsinacionesDeUsuarios(List<Asigna_solicitud> listaAsinacionesDeUsuarios) {
        this.listaAsinacionesDeUsuarios = listaAsinacionesDeUsuarios;
    }
    

    
    public List<Asigna_solicitud> getFilterAsignar() {
        return filterAsignar;
    }

    public void setFilterAsignar(List<Asigna_solicitud> filterAsignar) {
        this.filterAsignar = filterAsignar;
    }
    
    
    public List<Asigna_solicitud> getListarAsignacionSolicitudes() {
        return listarAsignacionSolicitudes;
    }

    public void setListarAsignacionSolicitudes(List<Asigna_solicitud> listarAsignacionSolicitudes) {
        this.listarAsignacionSolicitudes = listarAsignacionSolicitudes;
    }
    
    

    public Asigna_solicitud getAsigna_Solicitud() {
        return asigna_Solicitud;
    }

    public void setAsigna_Solicitud(Asigna_solicitud asigna_Solicitud) {
        this.asigna_Solicitud = asigna_Solicitud;
    }
    
    
    
     public void registrarAsignaSolicitu() throws Exception {
        Asigna_solicitudDAO asignaSolicitudDao;
        SeguimientoDAO seguimientodao;
            try {
            FacesContext contexto = FacesContext.getCurrentInstance(); //paraq entrar ql dom del navegador
            Usuario usuarioVive = (Usuario) contexto.getExternalContext().getSessionMap().get("usuario");//llamo a  la etiqueta usuario que es un objeto que ya debe
            //existir dentro del navegador
            asignaSolicitudDao = new Asigna_solicitudDAO();
            
           
            
            asigna_Solicitud.setId_usuario_personal_jefe(usuarioVive);
            asigna_Solicitud.setFecha(new java.sql.Date(new java.util.Date().getTime()));//fecha sistema
            asignaSolicitudDao.registrarAsignarSolicitud(asigna_Solicitud);
            /*seccion donde se pone el estatus de la solicitud en falso para su asignacion*/
            Solicitud_mc solicitudAsignada = asigna_Solicitud.getId_solicitud();
            new Solicitud_mcDAO().modificarSolicitudMC(solicitudAsignada);
            /*finaliza la seccion donde se pone falso la solicitud despues de asignarlo*/
            /* Aqui comienza para modificar el SEGUIMIENTO Y PODER REGISTRAR EL PERSONAL QUE TIENE A CARGO LA SOLICITUD 
            Y EL ESTADO DE ASIGNACION EN MODO TRUE*/
            seguimientodao = new SeguimientoDAO();//el seguimientodao es para poder tener acceso a la clase SeguimientoDao y mandar a llamar al metodo elegirDatoSeguimiento
           Seguimiento solicitudSeguimientoTemporal = seguimientodao.elegirDatoSeguimiento(solicitudAsignada);//Aqui es para buscar el seguimiento con la solicitud asignada
           
            solicitudSeguimientoTemporal.setId_usuario_personal(asigna_Solicitud.getId_usuario_personal()); //se manda el valor del personal extrallendolo desde el get
            solicitudSeguimientoTemporal.setEstado_asignacion(true);
            seguimientodao.modificarSeguimiento(solicitudSeguimientoTemporal);//se manda a llamar el metodo modificar que se encuentra en el SeguimientoDao
            
            this.limpiarAsignaSolicitud();
            System.out.println("fecha del sistema " + asigna_Solicitud.getFecha());
           
          //  this.limpiar();

        } catch (Exception ex) {
            System.out.println("Error en Asigna-Solicitud-BEAN -> generarAsignacionSolicitud " + ex);
            throw ex;
        }
    }
     
     
     //Metodo ´para listar 
    
    public void listarAsignaciones() throws Exception{
       Asigna_solicitudDAO asigna_solicituddao;
        try{
            asigna_solicituddao=new Asigna_solicitudDAO();
            listarAsignacionSolicitudes = asigna_solicituddao.listarAsignarSolicitud();
        }
        catch(Exception e){
            System.out.println("error en Listar AsignacionBEAN --> listarAsignacion BEAN"+e);
        }
    }
    
     
     public void limpiarAsignaSolicitud(){
        this.asigna_Solicitud.setId_solicitud(null);
        this.asigna_Solicitud.setId_usuario_personal(null);
       
    }
     
      public void listaAsignacionesDeUsuarios() {
        Asigna_solicitudDAO asignaSolicituddao;
        
        try{
                asignaSolicituddao = new Asigna_solicitudDAO();
               FacesContext contexto = FacesContext.getCurrentInstance(); //paraq entrar ql dom del navegador
            Usuario usuarioVive = (Usuario) contexto.getExternalContext().getSessionMap().get("usuario");//llamo a  la etiqueta usuario que es un objeto que ya debe
           
                
            listaAsinacionesDeUsuarios = asignaSolicituddao.buscarAsignacionPorIdUsuario(usuarioVive.getIdUsuario());
        }catch(Exception e){
            System.out.println("Error en SolicitudesCC BEAN -> listaSolicitudesCC "+e);
        }
    }
     
   public void eligeIdAsignaSolicitud(Asigna_solicitud asignaSolicitud) throws Exception{ //
       
               FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("solicitudAsignada",asignaSolicitud.getId_solicitud());
               System.out.println(asignaSolicitud.getId_solicitud().getFolio());
             
               
    
    }
     
   public void eligeIdAsignaSolicitudOTrabajo(Solicitud_mc ordenTrabajoFolio){//
       
               FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("solicitudAsignadaOR",ordenTrabajoFolio);
               System.out.println(ordenTrabajoFolio.getFolio());
               
    
    }
   
   public void eligeDeAsignaIdSolicitudSeguimiento(Solicitud_mc seguimientoFolio) throws Exception{//para agregar a DetalleSeguimiento el id_solicitud
           FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("solicitudSeguimiento",seguimientoFolio);//continua en SeguimientoBEAN
              
              System.out.println(seguimientoFolio.getFolio());
   
          
   }   
              
 
}
