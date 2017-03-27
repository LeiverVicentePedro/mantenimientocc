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

/**
 *
 * @author Jerusalen
 */
@ManagedBean
@ViewScoped
public class Niveles_internetBEAN implements Serializable {
    private Niveles_internet nivelesInternet= new Niveles_internet();
    Catalogo_niveles catalogo_niveles = new Catalogo_niveles();
    private List<Niveles_internet> listaniveles= new ArrayList();
    
    private Boolean nivelCuatro;

    public Boolean getNivelCuatro() {
        return nivelCuatro;
    }

    public void setNivelCuatro(Boolean nivelCuatro) {
        this.nivelCuatro = nivelCuatro;
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
}
