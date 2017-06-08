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
import java.io.Serializable;
/**
 *
 * @author Jerusalen
 */
@ManagedBean
@ViewScoped
public class Catalogo_servicio_solicitadoBEAN  implements Serializable{
    
    private Catalogo_servicio_solicitado catalogo= new Catalogo_servicio_solicitado();
    private List<Catalogo_servicio_solicitado> catalogoLista;
    private List<Catalogo_servicio_solicitado> listaSolicitudCatalogo;
    private List<Catalogo_servicio_solicitado> filterCatalogo;
    private List<Catalogo_servicio_solicitado> seleccionCatalogo;
    private String accion;

    public List<Catalogo_servicio_solicitado> getSeleccionCatalogo() {
        return seleccionCatalogo;
    }

    public void setSeleccionCatalogo(List<Catalogo_servicio_solicitado> seleccionCatalogo) {
        this.seleccionCatalogo = seleccionCatalogo;
    }

    
    public Catalogo_servicio_solicitado getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo_servicio_solicitado catalogo) {
        this.catalogo = catalogo;
    }

    public List<Catalogo_servicio_solicitado> getFilterCatalogo() {
        return filterCatalogo;
    }

    public void setFilterCatalogo(List<Catalogo_servicio_solicitado> filterCatalogo) {
        this.filterCatalogo = filterCatalogo;
    }

    
    
    //-------
    
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
    //------
    
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
    
     /* se Mantiene comentado debido que se remplazo por el metodo dar de baja ya que en la base de datos no se puede romper la relacion entre datos.
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
            
            
    } */
    
     public void elegirDatoCatalogoBaja(Catalogo_servicio_solicitado catalogoElegirDato) throws Exception{//esto es para dar de baja primero se elige el dato y despues se pone en inactivo
        Catalogo_servicio_solicitadoDAO catalogodao;
        Catalogo_servicio_solicitado catalogoTemporal;
        try{
            catalogodao= new Catalogo_servicio_solicitadoDAO();
            catalogoTemporal=catalogodao.elegirDatoCatalogo(catalogoElegirDato);
            
            if(catalogoTemporal != null){
                this.catalogo = catalogoTemporal;
            }
            this.bajaOficina();//se manda a llamar al metodo dar de baja para q se modifique el estatus por INACTIVO
            this.listarCatalogo();//para actualizar la tabla y se vea reflejado el cambio de estatus
            }
        catch (Exception e){
            throw e;
        }
        
    }
     public void bajaOficina() throws Exception{
        Catalogo_servicio_solicitadoDAO catalogodao;
            try{
                catalogodao= new Catalogo_servicio_solicitadoDAO();
                catalogo.setEstatus(false);
                catalogodao.modificarCatalogo(catalogo);
            }
            catch(Exception e)
            {
                throw e;
            }
    } 
 
    
}
