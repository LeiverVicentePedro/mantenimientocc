/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import mx.edu.itoaxaca.mantenimientocc.dao.Catalogo_servicio_solicitadoDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.Solicitud_MCDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Catalogo_servicio_solicitado;
import mx.edu.itoaxaca.mantenimientocc.modelo.Solicitud_MC;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;

/**
 *
 * @author leiver
 */
@ManagedBean
@ViewScoped
public class Solicitud_MCBEAN {
    
    Solicitud_MC solicitudmc = new Solicitud_MC();
    List<Catalogo_servicio_solicitado> serviciosSeleccionados;
    List<Catalogo_servicio_solicitado> serviciosPorDepartamento;
    private String folioSolicitud;
    
    public Solicitud_MC getSolicitudmc() {
        return solicitudmc;
    }

    public void setSolicitudmc(Solicitud_MC solicitudmc) {
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
    
    public void generarSolicitudMC() throws Exception{
        Solicitud_MCDAO solicitudDAO;
        try{
            FacesContext contexto = FacesContext.getCurrentInstance();
            Usuario usuarioVive = (Usuario) contexto.getExternalContext().getSessionMap().get("usuario");
            solicitudDAO = new Solicitud_MCDAO();
            generaFolioSolicitud();
            solicitudmc.setFecha(new java.sql.Date(new java.util.Date().getTime()));
            solicitudmc.setId_usuario(usuarioVive);
            solicitudmc.setFolio(folioSolicitud);
            solicitudDAO.generarSolicitudMC(solicitudmc);
        }catch(Exception ex){
            System.out.println("Error en SolicitudMCBEAN -> generarSolicitudMC "+ex);
            throw ex;
        }
    }
    
    public void listaServiciosParaSolicitudPorDepartametoServicio() throws Exception{
        Catalogo_servicio_solicitadoDAO catalogoServicio;
        
        try{
            catalogoServicio = new Catalogo_servicio_solicitadoDAO();
            serviciosPorDepartamento = catalogoServicio.listarCatalogoPorDepartamentoServico(solicitudmc.getId_departamento().getIddepartamento());
        }catch(Exception ex){
            System.out.println("Error en Solicitud_MCBEAN -> listaServiciosParaSolicitudPorDepartamentoServicio "+ex);
            throw ex;
        }
    }
    
    public void generaFolioSolicitud() throws Exception{
        Calendar fecha = new GregorianCalendar();
        Solicitud_MCDAO solicitudMC = new Solicitud_MCDAO();
        try{
        folioSolicitud = solicitudmc.getId_departamento().getClave_departamento()+String.valueOf(fecha.get(Calendar.YEAR))+"-"+solicitudMC.indiceDeSolicitud(solicitudmc.getId_departamento().getIddepartamento());
        }catch(Exception ex){
            System.out.println("Error en Solicitud_MCBEAN -> generaFolioSolicitud "+ex);
         throw ex;   
        }
    }
}
