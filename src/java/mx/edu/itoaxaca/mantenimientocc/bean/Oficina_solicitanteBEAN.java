/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mx.edu.itoaxaca.mantenimientocc.dao.Oficina_solicitanteDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Oficina_solicitante;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class Oficina_solicitanteBEAN implements Serializable{
    
    private Oficina_solicitante oficina= new Oficina_solicitante();
    private List<Oficina_solicitante> listaOficina;
    private List<Oficina_solicitante> filterOficina;
    private List<Oficina_solicitante> seleccionOficina;
    private String accion;

    public List<Oficina_solicitante> getSeleccionOficina() {
        return seleccionOficina;
    }

    public void setSeleccionOficina(List<Oficina_solicitante> seleccionOficina) {
        this.seleccionOficina = seleccionOficina;
    }
    
    
    public List<Oficina_solicitante> getFilterOficina() {
        return filterOficina;
    }

    //----------------------------------
    public void setFilterOficina(List<Oficina_solicitante> filterOficina) {
        this.filterOficina = filterOficina;
    }

    //------------------------------------Get,Set ----Oficina
    public Oficina_solicitante getOficina() {
        return oficina;
    }

    public void setOficina(Oficina_solicitante oficina) {
        this.oficina = oficina;
    }
    //------------------------------------Get,Set ---Lista

    public List<Oficina_solicitante> getListaOficina() {
        return listaOficina;
    }

    public void setListaOficina(List<Oficina_solicitante> listaOficina) {
        this.listaOficina = listaOficina;
    }
    //--------------------------------------Get,Set accion

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiarOficina();
        this.accion = accion;
    }
    
    
    //-----------------------------------
    //Modulo Registrar
    
    public void registrarOficina() throws Exception{
        Oficina_solicitanteDAO oficinaDao;
            try{
                oficinaDao= new Oficina_solicitanteDAO();
                oficinaDao.registrarDepartamento(oficina);
                this.listarOficina();
            }
            catch(Exception e)
            {
                System.out.println("error en BEAN");
            }
    } 
    //-----------------------------------------Metodo Listar
     public void listarOficina() throws Exception{
        Oficina_solicitanteDAO oficinadao;
        try{
            oficinadao=new Oficina_solicitanteDAO();
            listaOficina = oficinadao.listarOficina();
        }
        catch(Exception e){
            System.out.println("error en Departamento BEAN -->ListarDepartamentoBEAN"+e);
        }
    }
     
      //metodo elegir dato de departamento 
    public void elegirDatoOficina(Oficina_solicitante oficinaElegirDato) throws Exception{
        Oficina_solicitanteDAO oficinadao;
        Oficina_solicitante oficinaTemporal;
        try{
            oficinadao= new Oficina_solicitanteDAO();
            oficinaTemporal = new Oficina_solicitante();
            oficinaTemporal = oficinadao.elegirDatoOficina(oficinaElegirDato);
            
            if(oficinaTemporal != null){
                this.oficina = oficinaTemporal;
                this.accion="Modificar";
            }
            }
        catch (Exception e){
            throw e;
        }
        
    }
    
     public void modificarOficina() throws Exception{
        Oficina_solicitanteDAO oficinadao;
            try{
                oficinadao= new Oficina_solicitanteDAO();
                oficinadao.modificarOficina(oficina);
               this.listarOficina();
            }
            catch(Exception e)
            {
                System.out.println("error en oficinaBEAN metodo -->modificar"+e);
            }
    } 
     
     //metodo operar para elegir la opcion de registrar o modificar
     
     public void operarOficina() throws Exception{
        switch(accion)
        {
            case "Registrar":
                this.registrarOficina();
               this.limpiarOficina();
                break;
            case "Modificar":
                this.modificarOficina();
               this.limpiarOficina();
                break;
        }
    }
     /*Se remplasara por dar de baja ya que no se puede eliminar de la base de datos
     public void eliminarOficina(Oficina_solicitante oficinaEliminar) throws Exception{
        Oficina_solicitanteDAO oficinadao;
            try{
                oficinadao= new Oficina_solicitanteDAO();
                oficinadao.eliminarOficina(oficinaEliminar);
                this.listarOficina();
            }
            catch(Exception e)
            {
                throw e;
            }
    }*/
      public void elegirDatoOficinaBaja(Oficina_solicitante oficinaElegirDato) throws Exception{//esto es para dar de baja primero se elige el dato y despues se pone en inactivo
        Oficina_solicitanteDAO oficinadao;
        Oficina_solicitante oficinaTemporal;
        try{
            oficinadao= new Oficina_solicitanteDAO();
            oficinaTemporal=oficinadao.elegirDatoOficina(oficinaElegirDato);
            
            if(oficinaTemporal != null){
                this.oficina = oficinaTemporal;
            }
            this.bajaOficina();//se manda a llamar al metodo dar de baja para q se modifique el estatus por INACTIVO
            this.listarOficina();//para actualizar la tabla y se vea reflejado el cambio de estatus
            }
        catch (Exception e){
            throw e;
        }
        
    }
     public void bajaOficina() throws Exception{
        Oficina_solicitanteDAO oficinadao;
            try{
                oficinadao= new Oficina_solicitanteDAO();
                oficina.setEstatus(false);
                oficinadao.modificarOficina(oficina);
            }
            catch(Exception e)
            {
                throw e;
            }
    } 
     
     public void limpiarOficina(){
        this.oficina.setNombreOficina("");
        this.oficina.setExtencion(0);
        this.oficina.setDepartamento(null);
        this.oficina.setEstatus(Boolean.TRUE);
    }
    
    
    
}

