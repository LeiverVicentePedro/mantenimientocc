/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mx.edu.itoaxaca.mantenimientocc.dao.ColaboracionDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Asigna_solicitud;
import mx.edu.itoaxaca.mantenimientocc.modelo.Colaboracion;

/**
 *
 * @author leiver
 */

@ManagedBean
@ViewScoped
public class ColaboracionBEAN implements Serializable{
    
    Colaboracion colaborador = new Colaboracion();
    List<Colaboracion> misColaboradores = new ArrayList();
    List<Colaboracion> misColaboraciones = new ArrayList();
    
    public Colaboracion getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaboracion colaborador) {
        this.colaborador = colaborador;
    }

    public List<Colaboracion> getMisColaboradores() {
        return misColaboradores;
    }

    public void setMisColaboradores(List<Colaboracion> misColaboradores) {
        this.misColaboradores = misColaboradores;
    }

    public List<Colaboracion> getMisColaboraciones() {
        return misColaboraciones;
    }

    public void setMisColaboraciones(List<Colaboracion> misColaboraciones) {
        this.misColaboraciones = misColaboraciones;
    }

    
    public void agregarColaborador(){
        try{
            ColaboracionDAO colaboracionDao = new ColaboracionDAO();
            colaboracionDao.AgregarColaborador(colaborador);
        }catch(Exception ex){
            System.out.println("Error en ColaboracionBEAN -> agregarColaborador "+ex);
        }
    }
    
    
}
