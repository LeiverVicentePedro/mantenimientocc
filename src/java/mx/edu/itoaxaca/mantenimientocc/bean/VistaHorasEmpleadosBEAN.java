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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import mx.edu.itoaxaca.mantenimientocc.dao.DetalleHorasEmpleadoDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.VistaHorasEmpleadosDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.DetalleHorasEmpleado;
import mx.edu.itoaxaca.mantenimientocc.modelo.TotalHorasEmpleado;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;
import mx.edu.itoaxaca.mantenimientocc.modelo.VistaHorasEmpleados;
import org.primefaces.context.RequestContext;

/**
 *
 * @author leiver
 */
@ManagedBean
@SessionScoped
public class VistaHorasEmpleadosBEAN implements Serializable{
    
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
}
