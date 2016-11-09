/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mx.edu.itoaxaca.mantenimientocc.dao.Solicitud_MCDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Catalogo_servicio_solicitado;
import mx.edu.itoaxaca.mantenimientocc.modelo.Solicitud_MC;

/**
 *
 * @author leiver
 */
@ManagedBean
@ViewScoped
public class Solicitud_MCBEAN {
    
    Solicitud_MC solicitudmc = new Solicitud_MC();
    List<Catalogo_servicio_solicitado> serviciosSeleccionados;
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
    
    public void generarSolicitudMC() throws Exception{
        Solicitud_MCDAO solicitudDAO;
        try{
            solicitudDAO = new Solicitud_MCDAO();
            solicitudDAO.generarSolicitudMC(solicitudmc);
        }catch(Exception ex){
            System.out.println("Error en SolicitudMCBEAN -> generarSolicitudMC "+ex);
            throw ex;
        }
    }
}
