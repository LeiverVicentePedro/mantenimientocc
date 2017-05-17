/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mx.edu.itoaxaca.mantenimientocc.dao.Catalogo_nivelesDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Catalogo_niveles;

/**
 *
 * @author Jerusalen
 */
@ManagedBean
@ViewScoped
public class Catalogo_nivelesBEAN implements Serializable {
    
    private Catalogo_niveles catalogo_niveles= new Catalogo_niveles();
    private List<Catalogo_niveles> listaCatalogo_niveles;
    private List<Catalogo_niveles> filterCatalogo_niveles;
    private List<Catalogo_niveles> seleccionCatalogo_niveleses;
    private String accion;
    
     //metodo Registrar equipo

    public Catalogo_niveles getCatalogo_niveles() {
        return catalogo_niveles;
    }

    public void setCatalogo_niveles(Catalogo_niveles catalogo_niveles) {
        this.catalogo_niveles = catalogo_niveles;
    }

    public List<Catalogo_niveles> getListaCatalogo_niveles() {
        return listaCatalogo_niveles;
    }

    public void setListaCatalogo_niveles(List<Catalogo_niveles> listaCatalogo_niveles) {
        this.listaCatalogo_niveles = listaCatalogo_niveles;
    }

    public List<Catalogo_niveles> getFilterCatalogo_niveles() {
        return filterCatalogo_niveles;
    }

    public void setFilterCatalogo_niveles(List<Catalogo_niveles> filterCatalogo_niveles) {
        this.filterCatalogo_niveles = filterCatalogo_niveles;
    }

    public List<Catalogo_niveles> getSeleccionCatalogo_niveleses() {
        return seleccionCatalogo_niveleses;
    }

    public void setSeleccionCatalogo_niveleses(List<Catalogo_niveles> seleccionCatalogo_niveleses) {
        this.seleccionCatalogo_niveleses = seleccionCatalogo_niveleses;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiarcatalogo();
        this.accion = accion;
    }
    
     public void limpiarcatalogo(){
        this.catalogo_niveles.setNivel(0);
        this.catalogo_niveles.setDescripcion("");
    }
    
    
    
    public void registrarCatalogo_niveles() throws Exception{
        Catalogo_nivelesDAO catalogo_nivelesDao;
        
            try{
                
               catalogo_nivelesDao= new Catalogo_nivelesDAO();
                catalogo_nivelesDao.registrarCatalogo_niveles(catalogo_niveles);
                this.listaCatalogo_niveles();
            }
            
            catch(Exception e)
            {
                System.out.println("error en Equipo BEAN -->RegistrarEquipoBEAN"+e);
            }
    }
    
     //metodo elegir dato de equipó 
    public void elegirCatalogo_niveles(Catalogo_niveles nivelElegirDato) throws Exception{
        Catalogo_nivelesDAO nivelesdao;
        Catalogo_niveles nivelesTemporal;
        try{
            nivelesdao= new Catalogo_nivelesDAO();
            nivelesTemporal = new Catalogo_niveles();
            nivelesTemporal = nivelesdao.elegirNiveles(nivelElegirDato);
            
            if(nivelesTemporal != null){
                this.catalogo_niveles = nivelesTemporal;
                this.accion="Modificar";
            }
            }
        catch (Exception e){
            throw e;
        }
        
    }
    
     public void modificarCatalogo_niveles() throws Exception{
        Catalogo_nivelesDAO nivelesdao;
            try{
                nivelesdao= new Catalogo_nivelesDAO();
                nivelesdao.modificarCatalogoNiveles(catalogo_niveles);
               this.listaCatalogo_niveles();
            }
            catch(Exception e)
            {
                System.out.println("error en catalogo_nivelesBEAN metodo -->modificar"+e);
            }
    } 
     
     //metodo operar para elegir la opcion de registrar o modificar
     
     public void operarNiveles() throws Exception{
        switch(accion)
        {
            case "Registrar":
                this.registrarCatalogo_niveles();
               
                break;
            case "Modificar":
                this.modificarCatalogo_niveles();
              
                break;
        }
    }
     
     
  public void listaCatalogo_niveles() throws Exception{
        Catalogo_nivelesDAO catalogoNivelesdao;
        try{
            catalogoNivelesdao=new Catalogo_nivelesDAO();
            listaCatalogo_niveles = catalogoNivelesdao.listarNiveles();
        }
        catch(Exception e){
            System.out.println("error en EquipoBEAN --> listarEquipoBEAN"+e);
        }
    }
    
    
}
