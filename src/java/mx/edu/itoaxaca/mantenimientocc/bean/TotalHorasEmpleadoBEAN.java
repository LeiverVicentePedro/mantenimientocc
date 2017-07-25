/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import mx.edu.itoaxaca.mantenimientocc.dao.DetalleHorasEmpleadoDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.TotalHorasEmpleadoDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.VistaHorasEmpleadosDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.TotalHorasEmpleado;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;
import mx.edu.itoaxaca.mantenimientocc.modelo.VistaHorasEmpleados;
import org.primefaces.context.RequestContext;

/**
 *
 * @author leiver
 */
@ManagedBean
@ViewScoped

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
    
   //metodos y variables de la clase VistaHorasEmpleadosBEAN
     private TotalHorasEmpleado vistaEmpleados = new TotalHorasEmpleado();
    private List<VistaHorasEmpleados> listaHorasEmpleados = new ArrayList();
    private List<VistaHorasEmpleados> listaMisHoras = new ArrayList();
    
    public List<VistaHorasEmpleados> getListaHorasEmpleados() {
        return listaHorasEmpleados;
    }

    public void setListaHorasEmpleados(List<VistaHorasEmpleados> listaHorasEmpleados) {
        this.listaHorasEmpleados = listaHorasEmpleados;
    }

    public TotalHorasEmpleado getVistaEmpleados() {
        return vistaEmpleados;
    }

    public void setVistaEmpleados(TotalHorasEmpleado vistaEmpleados) {
        limpiarHorasEmpleado();
        this.vistaEmpleados = vistaEmpleados;
    }

    public List<VistaHorasEmpleados> getListaMisHoras() {
        return listaMisHoras;
    }

    public void setListaMisHoras(List<VistaHorasEmpleados> listaMisHoras) {
        this.listaMisHoras = listaMisHoras;
    }
    
    
    //para que el administrador pueda controlar las horas
    public void listarHorasEmpleados(){
        try{
            listaHorasEmpleados = new VistaHorasEmpleadosDAO().listarHorasEmplado(vistaEmpleados);
        }catch(Exception ex){
            System.out.println("Error en VistaHorasEmpleadosBEAN -> listasHorasEmpleados "+ex);
        }
    }
    //para que uno mismo pueda ver sus horas
    public void listarHorasEmpleadosPorUsuario(){
        try{
            Usuario usuarioVive = new Usuario();
            usuarioVive = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            listaMisHoras = new VistaHorasEmpleadosDAO().listarParaUnEmpleado(usuarioVive);
        }catch(Exception ex){
            System.out.println("Error en VistaHorasEmpleadosBEAN -> listasHorasEmpleadosPorUsuario "+ex);
        }
    }
    
    public void elimiarHoras(VistaHorasEmpleados vista){
        try{
            new DetalleHorasEmpleadoDAO().eliminarHoras(vista);
            listarHorasEmpleados();
            FacesMessage mensajeSalida = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Horas Eliminadas");
                    RequestContext.getCurrentInstance().showMessageInDialog(mensajeSalida);
        }catch(Exception ex){
            System.out.println("Error en VistaHorasEmpleadoBEAN -> eliminarHoras "+ex);
        }
    }
    
     public void limpiarHorasEmpleado(){
        vistaEmpleados.setHorasTotales("");
        vistaEmpleados.setIdUsuarioEmpleado(null);
    }
}
