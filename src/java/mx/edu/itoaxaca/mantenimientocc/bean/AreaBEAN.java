
package mx.edu.itoaxaca.mantenimientocc.bean;


import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import mx.edu.itoaxaca.mantenimientocc.dao.AreaDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Area;

@ManagedBean
@ViewScoped
public class AreaBEAN implements Serializable{
 
    private Area area = new Area();
    private List<Area> listaArea;
    private List<Area> filterArea;
    private String accion;


    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public List<Area> getFilterArea() {
        return filterArea;
    }

    public void setFilterArea(List<Area> filterArea) {
        this.filterArea = filterArea;
    }
    
    
    
    public void operarArea() throws Exception{
        switch(accion)
        {
            case "Registrar":
                this.registrarArea();
                this.limpiarArea();
                
                break;
            case "Modificar":
                this.modificarArea();
                this.limpiarArea();
               
                break;
        }
    }
    
    public void limpiarArea(){
        this.area.setNombre_area("");
        this.area.setEstatus(Boolean.TRUE);
    }
     //--Metodos para Registrar y Modificar
    
    private void registrarArea() throws Exception{
        AreaDAO areadao;
            try{
                areadao= new AreaDAO();
                areadao.registrarArea(area);
                this.listarArea();
        
            }
            catch(Exception e)
            {
                System.out.println("error en AREA BEAN -->RegistrarAREA"+e);
            }
    }   
    
      private void modificarArea() throws Exception{
        AreaDAO areadao;
            try{
                areadao= new AreaDAO();
                areadao.modificarArea(area);
                this.listarArea();
            }
            catch(Exception e)
            {
                throw e;
            }
    } 
    
    //-----Metodos get y set de listar

    public List<Area> getListaArea() {
        return listaArea;
    }

    public void setListaArea(List<Area> listaArea) {
        this.listaArea = listaArea;
    }
    
    //--metodos get y set de la variable accion

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiarArea();
        this.accion = accion;
        
        
    }
    
    //----Metodos 
    
    public void listarArea() throws Exception{
        AreaDAO areadao;
        try{
            areadao=new AreaDAO();
            listaArea = areadao.listarArea();
        }
        catch(Exception e){
            throw e;
        }
    }
    
    public void elegirDatoArea(Area areaElegirDato) throws Exception{
        AreaDAO areadao;
        Area areaTemporal;
        try{
            areadao= new AreaDAO();
            areaTemporal=areadao.elegirDatoArea(areaElegirDato);
            
            if(areaTemporal != null){
                this.area = areaTemporal;
                this.accion="Modificar";
            }
            }
        catch (Exception e){
            throw e;
        }
        
    }
    
     
    public void eliminarArea(Area areaEliminar) throws Exception{
        AreaDAO areadao;
            try{
                areadao= new AreaDAO();
                areadao.eliminarArea(areaEliminar);
                this.listarArea();
            }
            catch(Exception e)
            {
                throw e;
            }
    }
    
   
}