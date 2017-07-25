/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.reportes;

import java.util.List;
import mx.edu.itoaxaca.mantenimientocc.modelo.Solicitud_mc;
import mx.edu.itoaxaca.mantenimientocc.modelo.Catalogo_servicio_solicitado;
import java.io.IOException;
import net.sf.jasperreports.engine.JRException;
import java.util.Map;
import java.util.HashMap;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperExportManager;
import java.io.File;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;
import mx.edu.itoaxaca.mantenimientocc.modelo.Detalle_solicitud;





/**
 *
 * @author leiver
 */
public class ReporteMantenimiento{
    
    
    public void exportarPDFSolicitud(Solicitud_mc solicitudmc, List<Detalle_solicitud> serviciosSeleccionados) throws JRException, IOException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        String servicioSolicitado = "";

        if (solicitudmc.getId_departamento().getClave_departamento().equalsIgnoreCase("DCC")) {
            parametros.put("recursosMaterialesServicio", " ");
            parametros.put("centroComputo", "x");
            parametros.put("mantenimientoEquipo", " ");
        }
        if (solicitudmc.getId_departamento().getClave_departamento().equalsIgnoreCase("DME")) {
            parametros.put("recursosMaterialesServicio", " ");
            parametros.put("centroComputo", " ");
            parametros.put("mantenimientoEquipo", "x");
        }
        if (solicitudmc.getId_departamento().getClave_departamento().equalsIgnoreCase("DRM")) {
            parametros.put("recursosMaterialesServicio", "x");
            parametros.put("centroComputo", " ");
            parametros.put("mantenimientoEquipo", " ");
        }

        parametros.put("folio", solicitudmc.getFolio().toUpperCase());
        parametros.put("areaSolicitante", solicitudmc.getId_usuario().getIdOficina().getNombreOficina().toUpperCase());
        parametros.put("nombreSolicitante", solicitudmc.getId_usuario().getId_profesion().getNombre_profesion().toUpperCase() + " " + solicitudmc.getId_usuario().getNombre().toUpperCase() + " " + solicitudmc.getId_usuario().getApellidoPaterno().toUpperCase() + " " + solicitudmc.getId_usuario().getApellidoMaterno().toUpperCase());
        String fecha = String.valueOf(solicitudmc.getFecha());
        parametros.put("fechaElaboracion", fecha);
        if (solicitudmc.getOtroProblema().equals(null)) {
            parametros.put("otroProblema", " ");
        } else {
            parametros.put("otroProblema", solicitudmc.getOtroProblema().toUpperCase());
        }
        //parametro para los problemas encontrados, servicioSolicitado
        for (Detalle_solicitud servicio : serviciosSeleccionados) {
            servicioSolicitado += servicio.getId_catalogo_servicio_solicitado().getServicio_solicitado() + "\n";
        }
        parametros.put("servicioSolicitado", servicioSolicitado.toUpperCase());

        File archivo = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/FormatoSolicitudMantenimiento.jasper"));
        JasperPrint imprimirArchivo = JasperFillManager.fillReport(archivo.getPath(), parametros, new JREmptyDataSource());

        HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        respuesta.addHeader("Content-Disposition", "attachment; filename=\"Solicitud_Mantenimiento.pdf\";");
        ServletOutputStream stream = respuesta.getOutputStream();

        JasperExportManager.exportReportToPdfStream(imprimirArchivo, stream);

        FacesContext.getCurrentInstance().responseComplete();
    }

    
}
