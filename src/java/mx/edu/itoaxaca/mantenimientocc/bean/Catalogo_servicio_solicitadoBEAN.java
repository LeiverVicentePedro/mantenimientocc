/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mx.edu.itoaxaca.mantenimientocc.dao.Catalogo_servicio_solicitadoDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Catalogo_servicio_solicitado;

/**
 *
 * @author Jerusalen
 */
@ManagedBean
@ViewScoped
public class Catalogo_servicio_solicitadoBEAN {
    
    private Catalogo_servicio_solicitado catalogo= new Catalogo_servicio_solicitado();
    private List<Catalogo_servicio_solicitado> catalogoLista;
    private List<Catalogo_servicio_solicitado> listaSolicitudCatalogo;
    private String accion;

    
    
    
    
    public Catalogo_servicio_solicitado getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo_servicio_solicitado catalogo) {
        this.catalogo = catalogo;
    }

    public List<Catalogo_servicio_solicitado> getCatalogoLista() {
        return catalogoLista;
    }

    public void setCatalogoLista(List<Catalogo_servicio_solicitado> catalogoLista) {
        this.catalogoLista = catalogoLista;
    }

    public List<Catalogo_servicio_solicitado> getListaSolicitudCatalogo() {
        return listaSolicitudCatalogo;
    }

    public void setListaSolicitudCatalogo(List<Catalogo_servicio_solicitado> listaSolicitudCatalogo) {
        this.listaSolicitudCatalogo = listaSolicitudCatalogo;
    }
    
    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiarCatolo_servicio_solicitado();
        this.accion = accion;
    }
    
    public void operarCatalogo() throws Exception{
        switch(accion)
        {
            case "Registrar":
                 this.registrarCatalogo_servicio_solicitado();
                 this.listarCatalogo();
                break;
            case "Modificar":
                this.modificarCatalogo();
                this.listarCatalogo();
                break;
        }
    }
    
    public void limpiarCatolo_servicio_solicitado(){
        this.catalogo.setServicio_solicitado("");
        this.catalogo.setDepartamento(null);
      }
     //--Metodos para Registrar y Modificar
    
    private void registrarCatalogo_servicio_solicitado() throws Exception{
        
        Catalogo_servicio_solicitadoDAO catalogo_servicio_solicitadodao;
            try{
                catalogo_servicio_solicitadodao= new Catalogo_servicio_solicitadoDAO();
                catalogo_servicio_solicitadodao.registrarCatalogo_servicio_solicitado(catalogo);
                this.listarCatalogo();
        
            }
            catch(Exception e)
            {
                System.out.println("error en AREA BEAN -->RegistrarAREA"+e);
            }
            
    }   
    
      private void modificarCatalogo() throws Exception{
        Catalogo_servicio_solicitadoDAO catalogo_servicio_solicitando_dao;
            try{
                catalogo_servicio_solicitando_dao= new Catalogo_servicio_solicitadoDAO();
                catalogo_servicio_solicitando_dao.modificarCatalogo(catalogo);
                this.listarCatalogo();
            }
            catch(Exception e)
            {
                throw e;
            }
    } 
      
      
    public void listarCatalogo() throws Exception{
        Catalogo_servicio_solicitadoDAO catalogodao;
        try{
            catalogodao=new Catalogo_servicio_solicitadoDAO();
            catalogoLista = catalogodao.listarCatalogo();
        }
        catch(Exception e){
            throw e;
        }
    }
    
    public void elegirDatoCatalogo(Catalogo_servicio_solicitado catalogoElegirDato) throws Exception{
        Catalogo_servicio_solicitadoDAO catalogodao;
        Catalogo_servicio_solicitado catalogoTemporal;
        try{
            catalogodao= new Catalogo_servicio_solicitadoDAO();
            catalogoTemporal=catalogodao.elegirDatoCatalogo(catalogoElegirDato);
            
            if(catalogoTemporal != null){
                this.catalogo = catalogoTemporal;
                this.accion="Modificar";
            }
            }
        catch (Exception e){
            throw e;
        }
        
    }
    
     
    public void eliminarCatalogo(Catalogo_servicio_solicitado catalogoEliminar) throws Exception{
        Catalogo_servicio_solicitadoDAO catalogodao;
            try{
                catalogodao= new Catalogo_servicio_solicitadoDAO();
                catalogodao.eliminarCatalogo(catalogoEliminar);
                this.listarCatalogo();
            }
            catch(Exception e)
            {
                throw e;
            }
            
            
    } 
    
    public void listarCatalogoPorServicio(){
        
    }
    
    
    
    
}
