/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.io.Serializable;
import mx.edu.itoaxaca.mantenimientocc.dao.Detalle_solicitudDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Detalle_solicitud;

/**
 *
 * @author leiver
 */
public class Detalle_solicitudBEAN implements Serializable{
    
    Detalle_solicitud detalleSolicitud = new Detalle_solicitud();

    public Detalle_solicitud getDetalleSolicitud() {
        return detalleSolicitud;
    }

    public void setDetalleSolicitud(Detalle_solicitud detalleSolicitud) {
        this.detalleSolicitud = detalleSolicitud;
    }
    
    public void registrarDetalleSolicitud() throws Exception{
        Detalle_solicitudDAO detalleSolicitudDao;
        try{
            detalleSolicitudDao = new Detalle_solicitudDAO();
            detalleSolicitudDao.registrarDetalleSolicitud(detalleSolicitud);
        }catch(Exception ex){
            System.out.println("Error en Detalle_solicitudBEAN -> registrarDetalleSolicitud "+ex);
            throw ex;
        }
    }
}
