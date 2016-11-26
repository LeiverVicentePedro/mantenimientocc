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

import mx.edu.itoaxaca.mantenimientocc.dao.Orden_internaDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.Relacion_orden_equipoDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.Relacion_orden_refaccionDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Equipo;
import mx.edu.itoaxaca.mantenimientocc.modelo.Orden_interna;
import mx.edu.itoaxaca.mantenimientocc.modelo.Refaccion_empleada;
import mx.edu.itoaxaca.mantenimientocc.modelo.Relacion_orden_equipo;
import mx.edu.itoaxaca.mantenimientocc.modelo.Relacion_orden_refaccion;
import mx.edu.itoaxaca.mantenimientocc.modelo.Solicitud_mc;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;
import org.primefaces.event.SelectEvent;


/**
 *
 * @author Jerusalen
 */
@ManagedBean
@ViewScoped
public class Orden_internaBEAN implements Serializable{
    
    private Orden_interna orden_interna=new Orden_interna();
    
    private List<Equipo> listaEquipo = new ArrayList();
    private List<Refaccion_empleada> listaRefaccion= new ArrayList();
    private List<Orden_interna> listaOrden_interna;
    
    
    
    Usuario usuarioVive;
    
    public Usuario getUsuarioVive() {
        return usuarioVive;
    }

    public void setUsuarioVive(Usuario usuarioVive) {
        this.usuarioVive = usuarioVive;
    }

 
    public Orden_interna getOrden_interna() {
        return orden_interna;
    }

    public void setOrden_interna(Orden_interna orden_interna) {
        this.orden_interna = orden_interna;
    }

    public List<Orden_interna> getListaOrden_interna() {
        return listaOrden_interna;
    }

    public void setListaOrden_interna(List<Orden_interna> listaOrden_interna) {
        this.listaOrden_interna = listaOrden_interna;
    }

    public List<Equipo> getListaEquipo() {
        return listaEquipo;
    }

    public void setListaEquipo(List<Equipo> listaEquipo) {
        this.listaEquipo = listaEquipo;
    }

    public List<Refaccion_empleada> getListaRefaccion() {
        return listaRefaccion;
    }

    public void setListaRefaccion(List<Refaccion_empleada> listaRefaccion) {
        this.listaRefaccion = listaRefaccion;
    }
    
    
    
    ////-----REGISTRAR----
    public void registrarOrden() throws Exception {
        Orden_internaDAO ordenInternaDao;
        
        
        Relacion_orden_equipoDAO relacion_orden_equipoDAO;
        Relacion_orden_refaccionDAO relacion_orden_refaccionDAO;
        try {
            FacesContext contexto = FacesContext.getCurrentInstance(); //paraq entrar ql dom del navegador
            usuarioVive = (Usuario) contexto.getExternalContext().getSessionMap().get("usuario");//llamo a  la etiqueta usuario que es un objeto que ya debe
            //existir dentro del navegador
            ordenInternaDao = new Orden_internaDAO();
            relacion_orden_equipoDAO = new Relacion_orden_equipoDAO();
            relacion_orden_refaccionDAO =new Relacion_orden_refaccionDAO();
            
           
            
            orden_interna.setId_usuario_personal(usuarioVive);
            orden_interna.setFecha(new java.sql.Date(new java.util.Date().getTime()));//fecha sistema
            ordenInternaDao.registrarOrdenInterna(orden_interna);
            
            System.out.println("Lista de Equipo "+listaEquipo.size());
            
            Orden_interna ordenTemporal = ordenInternaDao.identificadorDeOrden(orden_interna.getIdsolicitud());
            
            for (int i = 0; i < listaEquipo.size(); i++) {
                Relacion_orden_equipo detalleOrdenEquipo = new Relacion_orden_equipo();

                detalleOrdenEquipo.setIdOrden_interna(ordenTemporal);
                detalleOrdenEquipo.setIdEquipo(listaEquipo.get(i));
                relacion_orden_equipoDAO.registrarDetalleOrdenEquipo(detalleOrdenEquipo);

            }
            
            
                System.out.println("Lista de Refaccion "+listaRefaccion.size());                      
            for (int i = 0; i < listaRefaccion.size(); i++) {
                Relacion_orden_refaccion detalleOrdenRefaccion = new Relacion_orden_refaccion();

                detalleOrdenRefaccion.setIdOrdenRefaccion(ordenTemporal);
                detalleOrdenRefaccion.setIdRefaccion(listaRefaccion.get(i));
                relacion_orden_refaccionDAO.registrarDetalleOrdenRefaccion(detalleOrdenRefaccion);

            }
            
            System.out.println("fecha del sistema " + orden_interna.getFecha());
            

        } catch (Exception ex) {
            System.out.println("Error en Orden-BEAN -> generarOrden " + ex);
            throw ex;
        }
    }
    
    //---------para checkBox TABLA
    public void check(SelectEvent event) {
            System.out.println("in check-Equipo");
        }
    public void checkDos(SelectEvent event) {
            System.out.println("in check-Refaccion");
        }
    
    
    //-----
    
//Metodo ´para listar 
    
    public void listarOrden_interna() throws Exception{
       Orden_internaDAO orden_internadao;
        try{
            orden_internadao=new Orden_internaDAO();
            listaOrden_interna = orden_internadao.listarOrden_interna();
        }
        catch(Exception e){
            System.out.println("error en orden_internaBEAN --> listarordenInternaBEAN"+e);
        }
    }
    
     public void limpiarOrdenInterna() {
        orden_interna.setIdsolicitud(null);
        orden_interna.setNombre_orden("");
        orden_interna.setReporte_fallo("");
        orden_interna.setReporte_tecnico("");
        orden_interna.setPosible_causa("");
    }
    
    
    
    
    
}
