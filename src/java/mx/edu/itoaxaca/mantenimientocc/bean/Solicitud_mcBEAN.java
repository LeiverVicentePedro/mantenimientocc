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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import mx.edu.itoaxaca.mantenimientocc.dao.Catalogo_servicio_solicitadoDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.Detalle_solicitudDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.Orden_internaDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.Solicitud_mcDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Catalogo_servicio_solicitado;
import mx.edu.itoaxaca.mantenimientocc.modelo.Detalle_solicitud;
import mx.edu.itoaxaca.mantenimientocc.modelo.Orden_interna;
import mx.edu.itoaxaca.mantenimientocc.modelo.Solicitud_mc;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;
import mx.edu.itoaxaca.reportes.ReporteMantenimiento;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author leiver
 */
@ManagedBean
@ViewScoped
public class Solicitud_mcBEAN implements Serializable{

    Solicitud_mc solicitudmc = new Solicitud_mc();
    
    List<Catalogo_servicio_solicitado> serviciosSeleccionados;//usado por el cheboxl
    List<Catalogo_servicio_solicitado> serviciosPorDepartamento;
    List<Catalogo_servicio_solicitado> catalogoServicio;

    
    private String folioSolicitud;
    Usuario usuarioVive;
    
    private List<Solicitud_mc> listaSolicitud;
    private List<Solicitud_mc> listaSolicitudPorDepartamento;
    private List<Solicitud_mc> listaFiltroSolicitud;
    private List<Solicitud_mc> listaSolicitudDeUsuarios;
    private List<Solicitud_mc> filtrarSolicitudIdUsuario;
    private List<Solicitud_mc> listaSolicitudCentroComputo;

    public List<Solicitud_mc> getListaSolicitudCentroComputo() {
        return listaSolicitudCentroComputo;
    }

    public void setListaSolicitudCentroComputo(List<Solicitud_mc> listaSolicitudCentroComputo) {
        this.listaSolicitudCentroComputo = listaSolicitudCentroComputo;
    }
    
    
    
    
    private List<Orden_interna> listaOrdenInterna;
    
    public List<Solicitud_mc> getListaSolicitudDeUsuarios() {
        return listaSolicitudDeUsuarios;
    }

    public void setListaSolicitudDeUsuarios(List<Solicitud_mc> listaSolicitudDeUsuarios) {
        this.listaSolicitudDeUsuarios = listaSolicitudDeUsuarios;
    }

    public List<Solicitud_mc> getFiltrarSolicitudIdUsuario() {
        return filtrarSolicitudIdUsuario;
    }

    public void setFiltrarSolicitudIdUsuario(List<Solicitud_mc> filtrarSolicitudIdUsuario) {
        this.filtrarSolicitudIdUsuario = filtrarSolicitudIdUsuario;
    }
    
    
    public List<Solicitud_mc> getListaFiltroSolicitud() {
        return listaFiltroSolicitud;
    }

    public void setListaFiltroSolicitud(List<Solicitud_mc> listaFiltroSolicitud) {
        this.listaFiltroSolicitud = listaFiltroSolicitud;
    }
    
    public List<Solicitud_mc> getListaSolicitudPorDepartamento() {
        return listaSolicitudPorDepartamento;
    }

    public void setListaSolicitudPorDepartamento(List<Solicitud_mc> listaSolicitudePorDepartamento) {
        this.listaSolicitudPorDepartamento = listaSolicitudePorDepartamento;
    }
    
    
    public String getFolioSolicitud() {
        return folioSolicitud;
    }

    public void setFolioSolicitud(String folioSolicitud) {
        this.folioSolicitud = folioSolicitud;
    }

    public Usuario getUsuarioVive() {
        return usuarioVive;
    }

    public void setUsuarioVive(Usuario usuarioVive) {
        this.usuarioVive = usuarioVive;
    }

    public List<Solicitud_mc> getListaSolicitud() {
        return listaSolicitud;
    }

    public void setListaSolicitud(List<Solicitud_mc> listaSolicitud) {
        this.listaSolicitud = listaSolicitud;
    }
    
    
    
    public Solicitud_mc getSolicitudmc() {
        return solicitudmc;
    }

    public void setSolicitudmc(Solicitud_mc solicitudmc) {
        this.solicitudmc = solicitudmc;
    }

    public List<Catalogo_servicio_solicitado> getServiciosSeleccionados() {
        return serviciosSeleccionados;
    }

    public void setServiciosSeleccionados(List<Catalogo_servicio_solicitado> serviciosSeleccionados) {
        this.serviciosSeleccionados = serviciosSeleccionados;
    }

    public List<Catalogo_servicio_solicitado> getServiciosPorDepartamento() {
        return serviciosPorDepartamento;
    }

    public void setServiciosPorDepartamento(List<Catalogo_servicio_solicitado> serviciosPorDepartamento) {
        this.serviciosPorDepartamento = serviciosPorDepartamento;
    }

//    public List<Catalogo_servicio_solicitado> getListaMuestraSeleccionados() {
//        return listaMuestraSeleccionados;
//    }
//
//    public void setListaMuestraSeleccionados(List<Catalogo_servicio_solicitado> listaMuestraSeleccionados) {
//        this.listaMuestraSeleccionados = listaMuestraSeleccionados;
//    }
    
   

    
    public void registrarSolicitudMC() throws Exception {
        Solicitud_mcDAO solicitudDao;
        Detalle_solicitudDAO detalleSolicitudDao;
        try {
            FacesContext contexto = FacesContext.getCurrentInstance(); //paraq entrar ql dom del navegador
            usuarioVive = (Usuario) contexto.getExternalContext().getSessionMap().get("usuario");//llamo a  la etiqueta usuario que es un objeto que ya debe
            //existir dentro del navegador
            solicitudDao = new Solicitud_mcDAO();
            detalleSolicitudDao = new Detalle_solicitudDAO();
            generaFolioSolicitud();
            solicitudmc.setFecha(new java.sql.Date(new java.util.Date().getTime()));//fecha sistema
            solicitudmc.setId_usuario(usuarioVive);
            solicitudmc.setFolio(folioSolicitud);
            solicitudDao.registrarSolicitudMC(solicitudmc);
            
            Solicitud_mc solicitudTemporal = solicitudDao.identificadorDeSolicitud(folioSolicitud);
            
            for (int i = 0; i < serviciosSeleccionados.size(); i++) {
                Detalle_solicitud detalleSolicitud = new Detalle_solicitud();

                detalleSolicitud.setId_solicitud_mc(solicitudTemporal);
                detalleSolicitud.setId_catalogo_servicio_solicitado(serviciosSeleccionados.get(i));
                detalleSolicitudDao.registrarDetalleSolicitud(detalleSolicitud);

            }
           
            new ReporteMantenimiento().exportarPDFSolicitud(solicitudmc, serviciosSeleccionados);//metodo para exportar pdf desde otra clase
            
            System.out.println("fecha del sistema " + solicitudmc.getFecha());
            this.limpiarSolicitud();

        } catch (Exception ex) {
            System.out.println("Error en SolicitudMCBEAN -> generarSolicitudMC " + ex);
            throw ex;
        }
    }

    public void listaServiciosParaSolicitudPorDepartametoServicio(){
       serviciosPorDepartamento = new ArrayList();
        if(solicitudmc.getId_departamento()!=null){
           for(Catalogo_servicio_solicitado servicio : catalogoServicio){
              if(servicio.getDepartamento().getIddepartamento()==solicitudmc.getId_departamento().getIddepartamento()){
                  serviciosPorDepartamento.add(servicio);
              } 
           }
       }else{
           serviciosSeleccionados.clear();
       }
    }

    public void generaFolioSolicitud() throws Exception {
        Calendar fecha = new GregorianCalendar();
        Solicitud_mcDAO solicitudMC = new Solicitud_mcDAO();
        try {
            folioSolicitud = solicitudmc.getId_departamento().getClave_departamento() + String.valueOf(fecha.get(Calendar.YEAR)) + "-" + solicitudMC.indiceDeSolicitud(solicitudmc.getId_departamento().getIddepartamento());
        } catch (Exception ex) {
            System.out.println("Error en Solicitud_MCBEAN -> generaFolioSolicitud " + ex);
            throw ex;
        }
    }

    public void limpiarSolicitud() {
        solicitudmc.setId_departamento(null);
        solicitudmc.setOtroProblema("");
        serviciosSeleccionados.clear();
    }
    
    public void listarSolicituMC() throws Exception{
       Solicitud_mcDAO solicitudMCdao;
        try{
            solicitudMCdao=new Solicitud_mcDAO();
            listaSolicitud = solicitudMCdao.listarSolicitudMC();
        }
        catch(Exception e){
            System.out.println("error en Solicitud BEAN --> listarSolicitud BEAN"+e);
        }
    }
    
    public void listarSolicitudPorDepartamento(){
        Solicitud_mcDAO solicitud;
        
        try{
            solicitud = new Solicitud_mcDAO();
            Orden_internaDAO ordenInterna = new Orden_internaDAO();
            FacesContext contexto = FacesContext.getCurrentInstance(); //paraq entrar ql dom del navegador
            usuarioVive = (Usuario) contexto.getExternalContext().getSessionMap().get("usuario");//llamo a  la etiqueta usuario que es un objeto que ya debe
            listaSolicitudPorDepartamento =solicitud.listarSoicitudPorDepartamentoUsuario(usuarioVive);
            listaOrdenInterna = ordenInterna.listarOrden_interna();
            for(int i=0; i<listaSolicitudPorDepartamento.size();i++){
                listaSolicitudPorDepartamento.get(i).setAsignacion("No Asignada");
                for(int j=0;j<listaOrdenInterna.size();j++){
                    if(listaSolicitudPorDepartamento.get(i).getIdsolicitud_mc()==listaOrdenInterna.get(j).getIdsolicitud().getIdsolicitud_mc()){
                        listaSolicitudPorDepartamento.get(i).setAsignacion("Asignada");
                    }
                }
                
            }
        }catch(Exception ex){
            System.out.println("Error en Solicitud_mc -> listarSolicitudPorDepartamento "+ex);
        }
    }
     
     public void listaSolicitudDeUsuarios() {
        Solicitud_mcDAO miSolicituddao;
        
        try{
                miSolicituddao = new Solicitud_mcDAO();
               FacesContext contexto = FacesContext.getCurrentInstance(); //paraq entrar ql dom del navegador
            Usuario usuarioVive = (Usuario) contexto.getExternalContext().getSessionMap().get("usuario");//llamo a  la etiqueta usuario que es un objeto que ya debe
           
                
            listaSolicitudDeUsuarios = miSolicituddao.buscarSolucitudPorIdUsuario(usuarioVive.getIdUsuario());
        }catch(Exception e){
            System.out.println("Error en MisSolicitudes BEAN -> listaMisSolicitudes "+e);
        }
    }
     
     
    public void listaSolicitudCentroComputo(){
         Solicitud_mcDAO solicitud;
         try{
             solicitud = new Solicitud_mcDAO();
          //listaSolicitudCentroComputo = new ArrayList();
            listaSolicitudCentroComputo = solicitud.listarSolicitudPorCentroComputo(solicitudmc);
         }catch(Exception ex){
           System.out.println("Error en Solicitud_mcBEAN -> listaServicioSolicitado "+ex);  
         }
         
     }
     
     public void listaServicioSolicitado(){
         Catalogo_servicio_solicitadoDAO servicioSolicitado = new Catalogo_servicio_solicitadoDAO();
         try{
            catalogoServicio = new ArrayList();
            catalogoServicio = servicioSolicitado.listarCatalogo();
         }catch(Exception ex){
           System.out.println("Error en Solicitud_mcBEAN -> listaServicioSolicitado "+ex);  
         }
         
     }
     
    
     
}
