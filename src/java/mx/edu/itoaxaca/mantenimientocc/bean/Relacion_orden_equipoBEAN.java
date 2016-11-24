/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.io.Serializable;
import mx.edu.itoaxaca.mantenimientocc.dao.Relacion_orden_equipoDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Relacion_orden_equipo;

/**
 *
 * @author Jerusalen
 */
public class Relacion_orden_equipoBEAN implements Serializable{
    
    Relacion_orden_equipo detalleOrden = new Relacion_orden_equipo();

  
    
    public void registrarDetalleOrdenEquipo() throws Exception{
        Relacion_orden_equipoDAO detalleOrdenDao;
        try{
            detalleOrdenDao = new Relacion_orden_equipoDAO();
            detalleOrdenDao.registrarDetalleOrdenEquipo(detalleOrden);
        }catch(Exception ex){
            System.out.println("Error en Detalle_ordenBEAN -> registrar DetalleOrden "+ex);
            throw ex;
        }
    }
}
