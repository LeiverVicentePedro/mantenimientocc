/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.formatos;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import mx.edu.itoaxaca.mantenimientocc.modelo.Catalogo_servicio_solicitado;
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
@RequestScoped
public class FormatoSolicitudMantenimiento {

    private List<Catalogo_servicio_solicitado> catalogoSeleccioando = new ArrayList<Catalogo_servicio_solicitado>();
    Usuario usuarioActivo = new Usuario();
    Solicitud_mc solicitud = new Solicitud_mc();
    
    public FormatoSolicitudMantenimiento(){
        
    }
    public FormatoSolicitudMantenimiento(Usuario usuarioSolicitante, Solicitud_mc solicitudRealizada, List<Catalogo_servicio_solicitado> listaEntrada) {
        
        this.setCatalogoSeleccioando(listaEntrada);
        this.setUsuarioActivo(usuarioSolicitante);
        this.setSolicitud(solicitudRealizada);
    }
    
    public List<Catalogo_servicio_solicitado> getCatalogoSeleccioando() {
        return catalogoSeleccioando;
    }

    public void setCatalogoSeleccioando(List<Catalogo_servicio_solicitado> catalogoSeleccioando) {
        this.catalogoSeleccioando = catalogoSeleccioando;
    }

    public Usuario getUsuarioActivo() {
        return usuarioActivo;
    }

    public void setUsuarioActivo(Usuario usuarioActivo) {
        this.usuarioActivo = usuarioActivo;
    }

    public Solicitud_mc getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud_mc solicitud) {
        this.solicitud = solicitud;
    }
    
    public void exportarPDF() throws JRException, IOException{
        Map<String, Object> parametros= new HashMap<String, Object>();
        
        parametros.put("recursosMaterialesServicio", "x");
        parametros.put("centroComputo", "x");
        parametros.put("mantenimientoEquipo", "x");
        parametros.put("folio", solicitud.getFolio());
        parametros.put("areaSolicitante",solicitud.getId_usuario().getIdOficina().getNombreOficina());
        parametros.put("nombreSolicitante", solicitud.getId_usuario().getId_profesion().getNombre_profesion()+". "+solicitud.getId_usuario().getNombre()+" "+solicitud.getId_usuario().getApellidoPaterno()+" "+solicitud.getId_usuario().getApellidoMaterno());
        String fecha = String.valueOf(solicitud.getFecha());
        parametros.put("fechaElaboracion",fecha);
        parametros.put("otroProblema",solicitud.getOtroProblema());
        
        File archivo = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/FormatoSolicitudMantenimiento.jasper"));
        JasperPrint imprimirArchivo = JasperFillManager.fillReport(archivo.getPath(),parametros,new JRBeanCollectionDataSource(this.getCatalogoSeleccioando()));
        
        HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        respuesta.addHeader("Content-disposition","attachment; filename=Solicitud_Mantenimiento.pdf");
        ServletOutputStream stream = respuesta.getOutputStream();
        
        JasperExportManager.exportReportToPdfStream(imprimirArchivo, stream);
        
        FacesContext.getCurrentInstance().responseComplete();
    }
}
