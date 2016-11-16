/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.io.File;
import java.io.IOException;
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
import mx.edu.itoaxaca.mantenimientocc.dao.Solicitud_mcDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Catalogo_servicio_solicitado;
import mx.edu.itoaxaca.mantenimientocc.modelo.Detalle_solicitud;
import mx.edu.itoaxaca.mantenimientocc.modelo.Solicitud_mc;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author leiver
 */
@ManagedBean
@ViewScoped
public class Solicitud_mcBEAN {
    
    Solicitud_mc solicitudmc = new Solicitud_mc();
    List<Catalogo_servicio_solicitado> serviciosSeleccionados;
    List<Catalogo_servicio_solicitado> serviciosPorDepartamento;
    private String folioSolicitud;
    Usuario usuarioVive;
    
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
    
    public void registrarSolicitudMC() throws Exception{
        Solicitud_mcDAO solicitudDao;
        Detalle_solicitudDAO detalleSolicitudDao;
        try{
            FacesContext contexto = FacesContext.getCurrentInstance();
            usuarioVive = (Usuario) contexto.getExternalContext().getSessionMap().get("usuario");
            solicitudDao = new Solicitud_mcDAO();
            detalleSolicitudDao = new Detalle_solicitudDAO();
            generaFolioSolicitud();
            solicitudmc.setFecha(new java.sql.Date(new java.util.Date().getTime()));
            solicitudmc.setId_usuario(usuarioVive);
            solicitudmc.setFolio(folioSolicitud);
            solicitudDao.registrarSolicitudMC(solicitudmc);
          Solicitud_mc solicitudTemporal = solicitudDao.identificadorDeSolicitud(folioSolicitud);
            for(int i=0; i<serviciosSeleccionados.size(); i++){
                Detalle_solicitud detalleSolicitud = new Detalle_solicitud();
                
                detalleSolicitud.setId_solicitud_mc(solicitudTemporal);
                detalleSolicitud.setId_catalogo_servicio_solicitado(serviciosSeleccionados.get(i));
               detalleSolicitudDao.registrarDetalleSolicitud(detalleSolicitud);

            }
             exportarPDF();
            System.out.println("fecha del sistema "+solicitudmc.getFecha());
            
            
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
        Solicitud_mcDAO solicitudMC = new Solicitud_mcDAO();
        try{
        folioSolicitud = solicitudmc.getId_departamento().getClave_departamento()+String.valueOf(fecha.get(Calendar.YEAR))+"-"+solicitudMC.indiceDeSolicitud(solicitudmc.getId_departamento().getIddepartamento());
        }catch(Exception ex){
            System.out.println("Error en Solicitud_MCBEAN -> generaFolioSolicitud "+ex);
         throw ex;   
        }
    }
    
    
    
    
    /**
     * ************************************************************************/
    /*
    *metodo que genera el pdf para la solicitud 
    ***************************************************************************/
   
    public void exportarPDF() throws JRException, IOException{
        Map<String, Object> parametros= new HashMap<String, Object>();
        
        parametros.put("recursosMaterialesServicio", "x");
        parametros.put("centroComputo", "x");
        parametros.put("mantenimientoEquipo", "x");
        parametros.put("folio", solicitudmc.getFolio());
        parametros.put("areaSolicitante",solicitudmc.getId_usuario().getIdOficina().getNombreOficina());
        parametros.put("nombreSolicitante", solicitudmc.getId_usuario().getId_profesion().getNombre_profesion()+". "+solicitudmc.getId_usuario().getNombre()+" "+solicitudmc.getId_usuario().getApellidoPaterno()+" "+solicitudmc.getId_usuario().getApellidoMaterno());
        String fecha = String.valueOf(solicitudmc.getFecha());
        parametros.put("fechaElaboracion",fecha);
        parametros.put("otroProblema",solicitudmc.getOtroProblema());
        
        File archivo = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/FormatoSolicitudMantenimiento.jasper"));
        JasperPrint imprimirArchivo = JasperFillManager.fillReport(archivo.getPath(),parametros,new JRBeanCollectionDataSource(serviciosSeleccionados));
        
        HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        respuesta.addHeader("Content-Disposition","attachment; filename=\"Solicitud_Mantenimiento.pdf\";");
        ServletOutputStream stream = respuesta.getOutputStream();
        
        JasperExportManager.exportReportToPdfStream(imprimirArchivo, stream);
        
        FacesContext.getCurrentInstance().responseComplete();
    }
}