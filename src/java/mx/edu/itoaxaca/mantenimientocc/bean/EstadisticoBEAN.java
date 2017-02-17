/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import mx.edu.itoaxaca.mantenimientocc.dao.SeguimientoDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.Solicitud_mcDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.DetallePorMesSolicitud;
import mx.edu.itoaxaca.mantenimientocc.modelo.ModeloEmpleadoReporte;
import mx.edu.itoaxaca.mantenimientocc.modelo.Oficina_solicitante;
import mx.edu.itoaxaca.mantenimientocc.modelo.SolicitudPorDepartamento;
import mx.edu.itoaxaca.mantenimientocc.modelo.Solicitud_mc;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


 /*
 * @author leiver
 */
@ManagedBean(name="estadisticaBEAN")
@ViewScoped
public class EstadisticoBEAN {
    
    private List<DetallePorMesSolicitud> listaSolicitudes;
    private List<SolicitudPorDepartamento> listaPorDepartamento;

    private List<ModeloEmpleadoReporte> listaEmpleados;

    private List<SolicitudPorDepartamento> filterestadistico;

    public List<SolicitudPorDepartamento> getFilterestadistico() {
        return filterestadistico;
    }

    public void setFilterestadistico(List<SolicitudPorDepartamento> filterestadistico) {
        this.filterestadistico = filterestadistico;
    }
    
    

    private SolicitudPorDepartamento seleccionado;
    
    public void generarLista(){
        try{
            Usuario usuarioActual = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            
            listaPorDepartamento = new ArrayList();
           listaPorDepartamento = new Solicitud_mcDAO().listaEstadisticoSolicitud(usuarioActual);
           
                
        }catch(Exception ex){
            System.out.println("Error en EstadisticaBEAN -> generarLista "+ex);
        }
    }
    
    public void generaDetalle(SolicitudPorDepartamento solicitud){
        try{
            listaSolicitudes = new ArrayList();
            listaSolicitudes = new Solicitud_mcDAO().detalleMes(solicitud);
        }catch(Exception ex){
            System.out.println("Error en EstadisticaBEAN -> geenerarDetalle "+ex);
        }
    }
    
    public void generarReporteEmpleado(){
        try{
        Usuario usuarioActual = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        listaEmpleados = new ArrayList();
        listaEmpleados = new SeguimientoDAO().obtenerReporteEmpleado(usuarioActual);
        }catch(Exception ex){
            System.out.println("Error en EstadisticaBEAN -> geenerarreporteEmpleado "+ex);
        }  
    }
    
    
    
    
    
    public List<DetallePorMesSolicitud> getListaSolicitudes() {
        return listaSolicitudes;
    }

    public void setListaSolicitudes(List<DetallePorMesSolicitud> listaSolicitudes) {
        this.listaSolicitudes = listaSolicitudes;
    }

    public List<SolicitudPorDepartamento> getListaPorDepartamento() {
        return listaPorDepartamento;
    }

    public void setListaPorDepartamento(List<SolicitudPorDepartamento> listaPorDepartamento) {
        this.listaPorDepartamento = listaPorDepartamento;
    }

    public SolicitudPorDepartamento getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(SolicitudPorDepartamento seleccionado) {
        this.seleccionado = seleccionado;
    }

    public List<ModeloEmpleadoReporte> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<ModeloEmpleadoReporte> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }
    
    
    
    public void reportePDF(){
        try{
            File archivo = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reporteEstadisticoDepartamento.jasper"));
        JasperPrint imprimirArchivo = JasperFillManager.fillReport(archivo.getPath(),null , new JRBeanCollectionDataSource(listaPorDepartamento));

        HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        respuesta.addHeader("Content-Disposition", "attachment; filename=\"Reporte_Departamento.pdf\";");
        ServletOutputStream stream = respuesta.getOutputStream();

        JasperExportManager.exportReportToPdfStream(imprimirArchivo, stream);

        FacesContext.getCurrentInstance().responseComplete();
        }catch(Exception ex){
            System.out.println("Error de reporte "+ex);
        }
    }
    
    
    
    
    
    
}
