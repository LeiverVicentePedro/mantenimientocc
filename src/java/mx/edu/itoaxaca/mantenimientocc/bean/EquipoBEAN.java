/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;
import mx.edu.itoaxaca.mantenimientocc.dao.EquipoDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Equipo;

/**
 *
 * @author Jerusalen
 */
@ManagedBean
@ViewScoped
public class EquipoBEAN implements Serializable{
    
    private Equipo equipo= new Equipo();
    private List<Equipo> listaEquipo;
    private List<Equipo> filterEquipo;
 
    private String accion;

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public List<Equipo> getListaEquipo() {
        return listaEquipo;
    }

    public void setListaEquipo(List<Equipo> listaEquipo) {
        this.listaEquipo = listaEquipo;
    }

    public List<Equipo> getFilterEquipo() {
        return filterEquipo;
    }

    public void setFilterEquipo(List<Equipo> filterEquipo) {
        this.filterEquipo = filterEquipo;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

   
   
     //metodo Registrar equipo
    public void registrarEquipo() throws Exception{
        EquipoDAO equipoDao;
        
            try{
                
               equipoDao= new EquipoDAO();
                equipoDao.registrarEquipo(equipo);
                this.listarEquipo();
            }
            
            catch(Exception e)
            {
                System.out.println("error en Equipo BEAN -->RegistrarEquipoBEAN"+e);
            }
    }
    
     //metodo elegir dato de equipó 
    public void elegirDatoEquipo(Equipo equipoElegirDato) throws Exception{
        EquipoDAO equipodao;
        Equipo equipoTemporal;
        try{
            equipodao= new EquipoDAO();
            equipoTemporal = new Equipo();
            equipoTemporal = equipodao.elegirDatoEquipo(equipoElegirDato);
            
            if(equipoTemporal != null){
                this.equipo = equipoTemporal;
                this.accion="Modificar";
            }
            }
        catch (Exception e){
            throw e;
        }
        
    }
    
     public void modificarEquipo() throws Exception{
        EquipoDAO equipodao;
            try{
                equipodao= new EquipoDAO();
                equipodao.modificarEquipo(equipo);
               this.listarEquipo();
            }
            catch(Exception e)
            {
                System.out.println("error en equipoBEAN metodo -->modificar"+e);
            }
    } 
     
     //metodo operar para elegir la opcion de registrar o modificar
     
     public void operarEquipo() throws Exception{
        switch(accion)
        {
            case "Registrar":
                this.registrarEquipo();
               this.limpiarEquipo();
                break;
            case "Modificar":
                this.modificarEquipo();
               this.limpiarEquipo();
                break;
        }
    }
     
     public void eliminarEquipo(Equipo equipoEliminar) throws Exception{
        EquipoDAO equipodao;
            try{
                equipodao= new EquipoDAO();
                equipodao.eliminarEquipo(equipoEliminar);
                this.listarEquipo();
            }
            catch(Exception e)
            {
                throw e;
            }
    }
     
     public void limpiarEquipo(){
        this.equipo.setTipo("");
        this.equipo.setMarca("");
        this.equipo.setModelo("");
        this.equipo.setNumero_serie("");
        this.equipo.setFolio_inventario(0);
    }
     
      //Metodo ´para listar 
    
    public void listarEquipo() throws Exception{
        EquipoDAO equipodao;
        try{
            equipodao=new EquipoDAO();
            listaEquipo = equipodao.listarEquipo();
        }
        catch(Exception e){
            System.out.println("error en EquipoBEAN --> listarEquipoBEAN"+e);
        }
    }

    
    
}
