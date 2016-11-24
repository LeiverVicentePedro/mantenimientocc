/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.io.Serializable;
import mx.edu.itoaxaca.mantenimientocc.dao.Relacion_orden_refaccionDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Relacion_orden_refaccion;

/**
 *
 * @author Jerusalen
 */
public class Relacion_orden_refaccionBEAN implements Serializable{
    
    Relacion_orden_refaccion detalleOrdenRefaccion = new Relacion_orden_refaccion();

  
    
    public void registrarDetalleOrdenRefaccion() throws Exception{
        Relacion_orden_refaccionDAO detalleOrdenRefaccionDao;
        try{
            detalleOrdenRefaccionDao = new Relacion_orden_refaccionDAO();
            detalleOrdenRefaccionDao.registrarDetalleOrdenRefaccion(detalleOrdenRefaccion);
        }catch(Exception ex){
            System.out.println("Error en Detalle_orden_RefaccionBEAN -> registrar DetalleOrdenRefaccion "+ex);
            throw ex;
        }
    }
}
