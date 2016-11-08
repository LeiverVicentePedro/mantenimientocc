/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mx.edu.itoaxaca.mantenimientocc.dao.Solicitud_MCDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Solicitud_MC;

/**
 *
 * @author leiver
 */
@ManagedBean
@ViewScoped
public class Solicitud_MCBEAN {
    
    Solicitud_MC solicitudmc = new Solicitud_MC();
    
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
