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
import mx.edu.itoaxaca.mantenimientocc.dao.Elemento_menuDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Elemento_menu;

/**
 *
 * @author leiver
 */
@ManagedBean
@ViewScoped
public class Elemento_menuBEAN implements Serializable{
   
    Elemento_menu objetoMenu = new Elemento_menu();

    public Elemento_menu getObjetoMenu() {
        return objetoMenu;
    }

    public void setObjetoMenu(Elemento_menu objetoMenu) {
        this.objetoMenu = objetoMenu;
    }
    
    public void listarMenu(){
        Elemento_menuDAO menuDao;
        List<Elemento_menu> listaMenu;
        try{
            
        }catch(Exception ex){
            System.out.println("Error en Elemento_MenuBEAN -> listarMenu "+ex);
            throw ex;
        }
    }
}
