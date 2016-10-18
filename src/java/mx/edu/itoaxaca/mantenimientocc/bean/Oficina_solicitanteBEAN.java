/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mx.edu.itoaxaca.mantenimientocc.dao.Oficina_solicitanteDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Oficina_solicitante;


@ManagedBean
@ViewScoped
public class Oficina_solicitanteBEAN {
    
    private Oficina_solicitante oficina= new Oficina_solicitante();

    public Oficina_solicitante getOficina() {
        return oficina;
    }

    public void setOficina(Oficina_solicitante oficina) {
        this.oficina = oficina;
    }
           
    //Modulo Registrar
    
    public void registrarOficina() throws Exception{
        Oficina_solicitanteDAO oficinaDao;
            try{
                oficinaDao= new Oficina_solicitanteDAO();
                oficinaDao.registrarDepartamento(oficina);
            }
            catch(Exception e)
            {
                System.out.println("error en BEAN");
            }
    }   
    
    
    
}
