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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import mx.edu.itoaxaca.mantenimientocc.dao.TotalHorasEmpleadoDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.TotalHorasEmpleado;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;

/**
 *
 * @author leiver
 */
@ManagedBean
@SessionScoped

public class TotalHorasEmpleadoBEAN implements Serializable{
    TotalHorasEmpleado misHoras = new TotalHorasEmpleado();
    List<TotalHorasEmpleado> listaHorasEmpleado = new ArrayList();

    public TotalHorasEmpleado getMisHoras() {
        return misHoras;
    }

    public void setMisHoras(TotalHorasEmpleado misHoras) {
        this.misHoras = misHoras;
    }
    
    public List<TotalHorasEmpleado> getListaHorasEmpleado() {
        return listaHorasEmpleado;
    }

    public void setListaHorasEmpleado(List<TotalHorasEmpleado> listaHorasEmpleado) {
        this.listaHorasEmpleado = listaHorasEmpleado;
    }
    
    
    public void listarEmpleadosHoras(){
        try{
            listaHorasEmpleado = new TotalHorasEmpleadoDAO().listarHorasEmpleado();
        }catch(Exception ex){
            System.out.println("Error en TotalHorasEmpleadoBEAN -> listarEmpleadoHoras "+ex);
        }
    }
    
    
    public void misHorasAcumuladas(){
        Usuario usuarioVive = new Usuario();
        usuarioVive = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        try{
        if(new TotalHorasEmpleadoDAO().totalMisHoras(usuarioVive).getHorasTotales()!=null){
            misHoras=new TotalHorasEmpleadoDAO().totalMisHoras(usuarioVive);
        }else{
            misHoras.setHorasTotales("00:00:00");
        }
        }catch(Exception ex){
            System.out.println("Error en TotalHorasEmpleadoBEAN -> misHorasAcumuladas "+ex);
        }
    }
}
