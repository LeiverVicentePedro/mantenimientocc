/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import mx.edu.itoaxaca.mantenimientocc.dao.Solicitud_mcDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.DetallePorMesSolicitud;
import mx.edu.itoaxaca.mantenimientocc.modelo.Oficina_solicitante;
import mx.edu.itoaxaca.mantenimientocc.modelo.SolicitudPorDepartamento;
import mx.edu.itoaxaca.mantenimientocc.modelo.Solicitud_mc;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;


 /*
 * @author leiver
 */
@ManagedBean(name="estadisticaBEAN")
@ViewScoped
public class EstadisticoBEAN {
    
    private List<DetallePorMesSolicitud> listaSolicitudes;
    private List<SolicitudPorDepartamento> listaPorDepartamento;
    
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
    
    
    
    
    
    
    
}
