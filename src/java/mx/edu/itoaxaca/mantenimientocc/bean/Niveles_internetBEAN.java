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
import javax.faces.context.FacesContext;
import mx.edu.itoaxaca.mantenimientocc.dao.Catalogo_nivelesDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.Niveles_internetDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Catalogo_niveles;
import mx.edu.itoaxaca.mantenimientocc.modelo.Niveles_internet;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Jerusalen
 */
@ManagedBean
@ViewScoped
public class Niveles_internetBEAN implements Serializable {
    Niveles_internet nivelesInternet= new Niveles_internet();
    Catalogo_niveles catalogo_niveles = new Catalogo_niveles();
    private List<Niveles_internet> listaniveles= new ArrayList();
    private List<Niveles_internet> listanivelesAdministrador= new ArrayList();
    private List<Niveles_internet> listanivelesAdministradorModifica= new ArrayList();
    private List<Niveles_internet> seleccionJustificacion=new ArrayList();
    private List<Niveles_internet> filterNiveles_internet;
    private List<Niveles_internet> seleccionNiveles_internet;

    public List<Niveles_internet> getFilterNiveles_internet() {
        return filterNiveles_internet;
    }

    public void setFilterNiveles_internet(List<Niveles_internet> filterNiveles_internet) {
        this.filterNiveles_internet = filterNiveles_internet;
    }

    public List<Niveles_internet> getSeleccionNiveles_internet() {
        return seleccionNiveles_internet;
    }

    public void setSeleccionNiveles_internet(List<Niveles_internet> seleccionNiveles_internet) {
        this.seleccionNiveles_internet = seleccionNiveles_internet;
    }
    
    

    
    public List<Niveles_internet> getSeleccionJustificacion() {
        return seleccionJustificacion;
    }

    public void setSeleccionJustificacion(List<Niveles_internet> seleccionJustificacion) {
        this.seleccionJustificacion = seleccionJustificacion;
    }

    public List<Niveles_internet> getListanivelesAdministrador() {
        return listanivelesAdministrador;
    }

    public void setListanivelesAdministrador(List<Niveles_internet> listanivelesAdministrador) {
        this.listanivelesAdministrador = listanivelesAdministrador;
    }

    public List<Niveles_internet> getListanivelesAdministradorModifica() {
        return listanivelesAdministradorModifica;
    }

    public void setListanivelesAdministradorModifica(List<Niveles_internet> listanivelesAdministradorModifica) {
        this.listanivelesAdministradorModifica = listanivelesAdministradorModifica;
    }
    
    
    
    
    private Boolean nivelCuatro;

    public Boolean getNivelCuatro() {
        return nivelCuatro;
    }

    public void setNivelCuatro(SelectEvent event) {
        if(catalogo_niveles.getIdcatalogo_niveles()==3)
        {
            this.nivelCuatro=true;
        }
        else
            this.nivelCuatro=false;
    }

    public Catalogo_niveles getCatalogo_niveles() {
        return catalogo_niveles;
    }

    public void setCatalogo_niveles(Catalogo_niveles catalogo_niveles) {
        this.catalogo_niveles = catalogo_niveles;
    }
 
    
    public List<Niveles_internet> getListaniveles() {
        return listaniveles;
    }

    public void setListaniveles(List<Niveles_internet> listaniveles) {
        this.listaniveles = listaniveles;
    }

    
    public Niveles_internet getNivelesInternet() {
        return nivelesInternet;
    }

    public void setNivelesInternet(Niveles_internet nivelesInternet) {
        this.nivelesInternet = nivelesInternet;
    }
    
    public void registrarNivelesInternet() throws Exception{
        Niveles_internetDAO nivelesDao;
        
        Catalogo_nivelesDAO catalogoNivelesdao;
            try{
            FacesContext contexto = FacesContext.getCurrentInstance(); //paraq entrar ql dom del navegador
            Usuario usuarioVive = (Usuario) contexto.getExternalContext().getSessionMap().get("usuario");//llamo a  la etiqueta usuario que es un objeto que ya debe
            
                
               nivelesDao= new Niveles_internetDAO();
               catalogoNivelesdao=new Catalogo_nivelesDAO();
               nivelesInternet.setSolicita(usuarioVive);
              nivelesInternet.setFecha(new java.sql.Date(new java.util.Date().getTime()));//fecha sistema
              nivelesInternet.setId_catalogo_niveles(catalogo_niveles);
              nivelesInternet.setCorreo_solicita(usuarioVive.getCorreo());
               System.out.println("ver "+ nivelesInternet.getId_catalogo_niveles().getNivel());
                nivelesDao.registrarNivelesInternet(nivelesInternet);
               // this.listarDepartamento();
            }
            
            catch(Exception e)
            {
                System.out.println("error en Niveles de Internet BEAN -->RegistrarNivelesInternetBEAN"+e);
            }
    }   
    public void listarNiveles_internet() throws Exception{
       Niveles_internetDAO niveles_internetdao;
        try{
            niveles_internetdao=new Niveles_internetDAO();
            listaniveles = niveles_internetdao.listarNiveles_internet();
        }
        catch(Exception e){
            System.out.println("error en niveles_internetBEAN --> listarniveles_InternetBEAN"+e);
        }
    }
    public void listarNiveles_internetAministrador() throws Exception{
       Niveles_internetDAO niveles_internetdao;
        try{
            niveles_internetdao=new Niveles_internetDAO();
            listanivelesAdministrador = niveles_internetdao.listarNiveles_internetAdministradorRegistra();
        }
        catch(Exception e){
            System.out.println("error en niveles_internetBEAN --> listarniveles_InternetAdministradorBEAN"+e);
        }
    }
     public void listarNiveles_internetAministradorModifica() throws Exception{
       Niveles_internetDAO niveles_internetdao;
        try{
            niveles_internetdao=new Niveles_internetDAO();
            listanivelesAdministradorModifica = niveles_internetdao.listarNiveles_internetAdministradorModifica();
        }
        catch(Exception e){
            System.out.println("error en niveles_internetBEAN --> listarniveles_InternetAdministradorBEAN"+e);
        }
    }
    
     public void elegirDatoNivelesInternet(Niveles_internet nivelesElegirDato) throws Exception{//esto es para dar de baja primero se elige el dato y despues se pone en inactivo
        Niveles_internetDAO nivelesdao;
        Niveles_internet nivelesTemporal;
        try{
            nivelesdao= new Niveles_internetDAO();
            nivelesTemporal=nivelesdao.elegirDatoNivel(nivelesElegirDato);
            
            if(nivelesTemporal != null){
                this.nivelesInternet = nivelesTemporal;
            }
            this.autoriza();//se manda a llamar al metodo dar de baja para q se modifique el estatus por INACTIVO
            this.listarNiveles_internet();//para actualizar la tabla y se vea reflejado el cambio de estatus
            }
        catch (Exception e){
            throw e;
        }
        
    }
     public void autoriza() throws Exception{
        Niveles_internetDAO nivelesdao;
            try{
                nivelesdao= new Niveles_internetDAO();
                FacesContext contexto = FacesContext.getCurrentInstance(); //paraq entrar ql dom del navegador
                 Usuario usuarioVive = (Usuario) contexto.getExternalContext().getSessionMap().get("usuario");//llamo a  la etiqueta usuario que es un objeto que ya debe
                 nivelesInternet.setAutoriza(usuarioVive);
                 nivelesInternet.setCorreo_autoriza(usuarioVive.getCorreo());
                nivelesInternet.setEstatus_autoriza(true);
                nivelesdao.modificarNiveles(nivelesInternet);
            }
            catch(Exception e)
            {
                throw e;
            }
    } 
     
    public void elegirDatoNivelesInternetAdministradorRegistra(Niveles_internet nivelesElegirDato) throws Exception{//esto es para dar de baja primero se elige el dato y despues se pone en inactivo
        Niveles_internetDAO nivelesdao;
        Niveles_internet nivelesTemporal;
        try{
            nivelesdao= new Niveles_internetDAO();
            nivelesTemporal=nivelesdao.elegirDatoNivel(nivelesElegirDato);
            
            if(nivelesTemporal != null){
                this.nivelesInternet = nivelesTemporal;
                this.modificarAdministrador();
            }
            this.listarNiveles_internetAministrador();
            }
        catch (Exception e){
            throw e;
        }
        
    }
    
    public void modificarAdministrador() throws Exception{
        Niveles_internetDAO modificaniveldao;
            try{
                modificaniveldao= new Niveles_internetDAO();
                 
                modificaniveldao.modificarNiveles(nivelesInternet);
                
               this.listarNiveles_internetAministrador();
            }
            catch(Exception e)
            {
                System.out.println("error en departamentoBEAN metodo -->modificar"+e);
            }
    } 
    
    public void elegirDatoNivelesInternetAdministradorModificar(Niveles_internet nivelesElegirDato) throws Exception{//esto es para dar de baja primero se elige el dato y despues se pone en inactivo
        Niveles_internetDAO nivelesdao;
        Niveles_internet nivelesTemporal;
        try{
            nivelesdao= new Niveles_internetDAO();
            //nivelesTemporal=nivelesdao.elegirDatoNivel(nivelesElegirDato);
            
            //if(nivelesTemporal != null){
                //this.nivelesInternet = nivelesTemporal;
                System.out.println("Dato");
           // }
            this.nivelesInternet = nivelesElegirDato;
            }
        catch (Exception e){
            System.out.println("Error en Niveles_internetBEAN -> elegirDatoNivelesInternetAdministradirModificar "+e);
            throw e;
        }
        
    }
    
    public void modificarAdministradorModificar() throws Exception{
        Niveles_internetDAO modificaniveldao;
            try{
                modificaniveldao= new Niveles_internetDAO();
                modificaniveldao.modificarNiveles(nivelesInternet);
                
               this.listarNiveles_internetAministradorModifica();
            }
            catch(Exception e)
            {
                System.out.println("error en departamentoBEAN metodo -->modificar"+e);
            }
    } 
}
