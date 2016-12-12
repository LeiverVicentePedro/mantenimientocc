/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mx.edu.itoaxaca.mantenimientocc.dao.DetallePreventivoDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.DetallePreventivo;

/**
 *
 * @author leiver
 */
@ManagedBean
@ViewScoped
public class DetallePreventivoBEAN {
    //DetallePreventivo detalle = new DetallePreventivo();
    
    public void registrarDetallePreventivo(DetallePreventivo detalle){
        try{
            DetallePreventivoDAO detalleDao = new DetallePreventivoDAO();
            detalleDao.registrarDetalle(detalle);
        }catch(Exception ex){
            System.out.println("Error en DetallePreventivoBEAN -> registrarDetallePreventivo "+ex);
        }
    }
}
