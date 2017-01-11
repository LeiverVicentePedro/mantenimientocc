/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mx.edu.itoaxaca.mantenimientocc.dao.DetalleSeguimientoDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.DetalleSeguimiento;
import mx.edu.itoaxaca.mantenimientocc.modelo.Seguimiento;

/**
 *
 * @author Jerusalen
 */
@ManagedBean
@ViewScoped
public class DetalleSeguimientoBEAN implements Serializable{
    
    private DetalleSeguimiento detalleSeguimiento=new DetalleSeguimiento();

    public DetalleSeguimiento getDetalleSeguimiento() {
        return detalleSeguimiento;
    }

    public void setDetalleSeguimiento(DetalleSeguimiento detalleSeguimiento) {
        this.detalleSeguimiento = detalleSeguimiento;
    }
    
    
    
    public void registrarDetalleSeguimiento() throws Exception{
        DetalleSeguimientoDAO detalleSeguimientodao;
            try{
                detalleSeguimientodao= new DetalleSeguimientoDAO();
               Seguimiento ejemplo= new Seguimiento();
               ejemplo.setIdseguimiento(1);
                detalleSeguimiento.setId_seguimiento(ejemplo);
                detalleSeguimientodao.registrarDetalleSeguimiento(detalleSeguimiento);
            }
            catch(Exception e)
            {
                System.out.println("error en DetalleSeguimiento BEAN -->RegistrarDetalleSeguimiento"+e);
            }
    }   
    
}
