/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import mx.edu.itoaxaca.mantenimientocc.correo.CorreoRegistroUsuario;
import mx.edu.itoaxaca.mantenimientocc.dao.Asigna_solicitudDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.ColaboracionDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.Orden_trabajoDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.Solicitud_mcDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Asigna_solicitud;
import mx.edu.itoaxaca.mantenimientocc.modelo.Colaboracion;
import mx.edu.itoaxaca.mantenimientocc.modelo.Orden_trabajo;
import mx.edu.itoaxaca.mantenimientocc.modelo.Solicitud_mc;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Jerusalen
 */
@ManagedBean
@ViewScoped
public class Orden_trabajoBEAN implements Serializable{
    
     private Orden_trabajo orden_trabajo=new Orden_trabajo();
     private List<Orden_trabajo> filterOrdenTrabajo;
    private List<Orden_trabajo> listaOrden_trabajo;
    Solicitud_mc solicitudOT;

    public List<Orden_trabajo> getFilterOrdenTrabajo() {
        return filterOrdenTrabajo;
    }

    public void setFilterOrdenTrabajo(List<Orden_trabajo> filterOrdenTrabajo) {
        this.filterOrdenTrabajo = filterOrdenTrabajo;
    }

    
    public Solicitud_mc getSolicitudOT() {
        return solicitudOT;
    }

    public void setSolicitudOT(Solicitud_mc solicitudOT) {
        this.solicitudOT = solicitudOT;
    }
    
    public void existeSolicitud(){//esto es para usar en la vista un preRender que llamara al dato para mostrar en la pagina el folio de Solicitud en Orden De Trabajo lo mismo se hace para Orden Interna 
        solicitudOT = (Solicitud_mc) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("solicitudAsignadaOR");
    }
    
    
    
    
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

    public void setOrden_trabajo(Orden_trabajo orden_trabajo) {
        this.orden_trabajo = orden_trabajo;
    }

 //-------------------------------------------------------------------------------------
    
    //-Registrar
    
      public void registrarOrden() throws Exception {
        Orden_trabajoDAO ordenTrabajoDao;
        Solicitud_mcDAO solicitudDao;
        
        try {
            FacesContext contexto = FacesContext.getCurrentInstance(); //paraq entrar ql dom del navegador
            Usuario usuarioVive = (Usuario) contexto.getExternalContext().getSessionMap().get("usuario");//llamo a  la etiqueta usuario que es un objeto que ya debe
            
            //existir dentro del navegador
            FacesContext contextoOT = FacesContext.getCurrentInstance(); //paraq entrar ql dom del navegador
            solicitudOT = (Solicitud_mc) contextoOT.getExternalContext().getSessionMap().get("solicitudAsignadaOR");
            ///
            ordenTrabajoDao = new Orden_trabajoDAO();
            orden_trabajo.setId_usuario_personal(usuarioVive);
            orden_trabajo.setFecha_realizacion(new java.sql.Date(new java.util.Date().getTime()));//fecha sistema
            orden_trabajo.setId_solicitudmc(solicitudOT);
            ordenTrabajoDao.registrarOrdenTrabajo(orden_trabajo);
            
            FacesMessage mensajeSalida = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACION","Registro Exitoso");
             RequestContext.getCurrentInstance().showMessageInDialog(mensajeSalida);
            
             System.out.println("Datos  " + orden_trabajo.getMantenimiento_tipo()+"\n"+
                    orden_trabajo.getTipo_servicio()+"\n "+
                    orden_trabajo.getId_usuario_personal()+"\n "+
                    orden_trabajo.getFecha_realizacion()+"\n "+
                    orden_trabajo.getTrabajo_descripcion()+"\n "+
                    orden_trabajo.getId_usuario_personal_jefe()+"\n"+
                    orden_trabajo.getId_solicitudmc().getFolio());
             
             solicitudDao=new Solicitud_mcDAO();//aqui se modifica solicitud en estado_seguimiento
            Solicitud_mc solicitudEstadoSeguimiento=solicitudDao.elegirDatoSolicitudParaModificarEstado_Seguimiento(orden_trabajo.getId_solicitudmc());
            Asigna_solicitud asignacion = new Asigna_solicitudDAO().buscarSolicitudPorIdSolicitud(solicitudEstadoSeguimiento);
            List<Colaboracion> listaColabora = new ArrayList();
            System.out.println("longitud de la lista "+listaColabora.size());
            System.out.println("longitud de lista despues de arrego "+new ColaboracionDAO().buscarColaboracionPorAsignacionSolicitud(asignacion).isEmpty());
            if(new ColaboracionDAO().buscarColaboracionPorAsignacionSolicitud(asignacion).isEmpty() == false){
                listaColabora = new ColaboracionDAO().buscarColaboracionPorAsignacionSolicitud(asignacion);
                for(Colaboracion colaborador : listaColabora){
                    new ColaboracionDAO().modificarEstatusColaboracion(colaborador);
                }
            }
            
            solicitudDao.modificarSolicitudSeguimiento(solicitudEstadoSeguimiento);
            new CorreoRegistroUsuario().enviarMensajeOrdenTrabajo(orden_trabajo);
          //  exportarOrdenTrabajo(orden_trabajo);
           
            this.limpiarOrdenTrabajo();
            
            
            
             } catch (Exception ex) {
            System.out.println("Error en Orden-TrabajoBEAN -> generarOrden-trabajo " + ex);
            throw ex;
        }
    
      }
      
       /**
     * ***********************************************************************
     */
    /*
    *metodo que genera el pdf para la Orden De Trabajo
    ***************************************************************************/
      
      
      public void exportarOrdenTrabajo(Orden_trabajo orden_trabajo) throws JRException, IOException{
          Map<String,Object> parametros = new HashMap<String,Object>();
          String nombreUsuario = orden_trabajo.getId_solicitudmc().getId_usuario().getId_profesion().getNombre_profesion()+" "+
                orden_trabajo.getId_solicitudmc().getId_usuario().getNombre()+" "+
                orden_trabajo.getId_solicitudmc().getId_usuario().getApellidoPaterno()+" "+
                orden_trabajo.getId_solicitudmc().getId_usuario().getApellidoMaterno();
          
        parametros.put("folio", orden_trabajo.getId_solicitudmc().getFolio().toUpperCase());
        if(orden_trabajo.getMantenimiento_tipo().equalsIgnoreCase("Interno")){
            parametros.put("manInterno","X");
            parametros.put("manExterno"," ");
        }else{
            parametros.put("manExterno","X");
            parametros.put("manInterno"," ");
        }
        parametros.put("tipoServicio",orden_trabajo.getTipo_servicio().toUpperCase());
        parametros.put("asignado", orden_trabajo.getId_usuario_personal().getConcatenar().toUpperCase());
        String fechaRealizacion = String.valueOf(orden_trabajo.getFecha_realizacion());
        parametros.put("fecha", fechaRealizacion);
        parametros.put("trabajo_realizado",orden_trabajo.getTrabajo_descripcion().toUpperCase());
        parametros.put("verificadoYliberado", nombreUsuario.toUpperCase());          
        parametros.put("aprobadoPor", orden_trabajo.getId_usuario_personal_jefe().getId_profesion().getNombre_profesion().toUpperCase() + " " + orden_trabajo.getId_usuario_personal_jefe().getNombre().toUpperCase() + " " + orden_trabajo.getId_usuario_personal_jefe().getApellidoPaterno().toUpperCase() + " " + orden_trabajo.getId_usuario_personal_jefe().getApellidoMaterno().toUpperCase());
        parametros.put("fechaLiberado"," ");
        parametros.put("fechaAprobado"," ");
               
           
           File archivo = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/FormatoOrdenTrabajo.jasper"));
           JasperPrint imprimirArchivo = JasperFillManager.fillReport(archivo.getPath(), parametros, new JREmptyDataSource());
           
           HttpServletResponse respuestaArchivo = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        respuestaArchivo.addHeader("Content-Disposition", "attachment; filename=\"Orden_Trabajo.pdf\";");
        ServletOutputStream stream = respuestaArchivo.getOutputStream();

        JasperExportManager.exportReportToPdfStream(imprimirArchivo, stream);

        FacesContext.getCurrentInstance().responseComplete();
        

      }
      
      //**********************************************************************************************
       public void limpiarOrdenTrabajo() {
        orden_trabajo.setMantenimiento_tipo("");
        orden_trabajo.setTipo_servicio("");
        orden_trabajo.setTrabajo_descripcion("");
        /*
        orden_trabajo.setId_usuario_personal_jefe(null);
        orden_trabajo.setId_solicitudmc(null);*/
        
        
    } 

//Metodo ´para listar 
    
    public void listarOrden_trabajo() throws Exception{
       Orden_trabajoDAO orden_trabajodao;
        try{
            orden_trabajodao=new Orden_trabajoDAO();
            listaOrden_trabajo = orden_trabajodao.listarOrden_trabajo();
        }
        catch(Exception e){
            System.out.println("error en orden_trabajoBEAN --> listarorden TrabajoBEAN"+e);
        }
    }       
    
    
}
