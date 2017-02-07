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
    private List<Equipo> listaEquipoOrdenIn;
    private List<Equipo> filterEquipo;
    private List<Equipo> seleccionEquipo;

    public List<Equipo> getSeleccionEquipo() {
        return seleccionEquipo;
    }

    public void setSeleccionEquipo(List<Equipo> seleccionEquipo) {
        this.seleccionEquipo = seleccionEquipo;
    }
    
    
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

    public List<Equipo> getListaEquipoOrdenIn() {
        return listaEquipoOrdenIn;
    }

    public void setListaEquipoOrdenIn(List<Equipo> listaEquipoOrdenIn) {
        this.listaEquipoOrdenIn = listaEquipoOrdenIn;
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
     //se modifica por metodo baja
    /* public void eliminarEquipo(Equipo equipoEliminar) throws Exception{
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
    }*/
      public void elegirDatoEquipoBaja(Equipo equipoElegirDato) throws Exception{//esto es para dar de baja primero se elige el dato y despues se pone en inactivo
        EquipoDAO equipodao;
        Equipo equipoTemporal;
        try{
            equipodao= new EquipoDAO();
            equipoTemporal=equipodao.elegirDatoEquipo(equipoElegirDato);
            
            if(equipoTemporal != null){
                this.equipo = equipoTemporal;
            }
            this.bajaEquipo();//se manda a llamar al metodo dar de baja para q se modifique el estatus por INACTIVO
            this.listarEquipo();//para actualizar la tabla y se vea reflejado el cambio de estatus
            }
        catch (Exception e){
            throw e;
        }
        
    }
      
      public void bajaEquipo() throws Exception{
        EquipoDAO equipodao;
            try{
                equipodao= new EquipoDAO();
                equipo.setEstatus(false);
                equipodao.modificarEquipo(equipo);
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
    public void listarEquipoOrdenInterna() throws Exception{
        EquipoDAO equipodao;
        try{
            equipodao=new EquipoDAO();
            listaEquipoOrdenIn = equipodao.listarEquipoOrIn();
        }
        catch(Exception e){
            System.out.println("error en EquipoBEAN --> listarEquipoBEAN"+e);
        }
    }

    
    
}
