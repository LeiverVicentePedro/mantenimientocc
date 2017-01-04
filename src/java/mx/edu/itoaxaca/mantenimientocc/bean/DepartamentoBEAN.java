/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;


import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mx.edu.itoaxaca.mantenimientocc.dao.DepartamentoDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Departamento;
import java.io.Serializable;


@ManagedBean
@ViewScoped
public class DepartamentoBEAN implements Serializable{
    
  private Departamento departamento= new Departamento();
  private List<Departamento> listadepartamento;//departamentos en  general
  private List<Departamento> listadepartamentoServicio;//esta lista es para clasificar los departamentos prestadores de servicio de mantenimiento
  private List<Departamento> filterDepartamento;//esta lista es para una busqueda general es una lista vacia
  private String accion;//esta variable es para usarla en un switch y poder escoger si es opcion registrar o modificar

    public List<Departamento> getFilterDepartamento() {
        return filterDepartamento;
    }

    public void setFilterDepartamento(List<Departamento> filterDepartamento) {
        this.filterDepartamento = filterDepartamento;
    }

  
   
  
   //metodo get y set de variable accion
    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
         this.limpiarDepartamento();
        this.accion = accion;
    }

    public List<Departamento> getListadepartamentoServicio() {
        return listadepartamentoServicio;
    }

    public void setListadepartamentoServicio(List<Departamento> listadepartamentoServicio) {
        this.listadepartamentoServicio = listadepartamentoServicio;
    }
    
   //set y get de departamento
    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    //metodo Registrar departamento
    public void registrarDepartamento() throws Exception{
        DepartamentoDAO departamentoDao;
        
            try{
                
               departamentoDao= new DepartamentoDAO();
                departamentoDao.registrarDepartamento(departamento);
                this.listarDepartamento();
            }
            
            catch(Exception e)
            {
                System.out.println("error en Departamento BEAN -->RegistrarDepartamentoBEAN"+e);
            }
    }   

    public List<Departamento> getListadepartamento() {
        return listadepartamento;
    }

    public void setListadepartamento(List<Departamento> listadepartamento) {
        this.listadepartamento = listadepartamento;
    }
    
   
    
    //metodo elegir dato de departamento 
    public void elegirDatoDepartamento(Departamento departamentoElegirDato) throws Exception{
        DepartamentoDAO departamentodao;
        Departamento departamentoTemporal;
        try{
            departamentodao= new DepartamentoDAO();
            departamentoTemporal = new Departamento();
            departamentoTemporal = departamentodao.elegirDatoDepartamento(departamentoElegirDato);
            
            if(departamentoTemporal != null){
                this.departamento = departamentoTemporal;
                this.accion="Modificar";
            }
            }
        catch (Exception e){
            throw e;
        }
        
    }
    
     public void modificarDepartamento() throws Exception{
        DepartamentoDAO departamentodao;
            try{
                departamentodao= new DepartamentoDAO();
                departamentodao.modificarDepartamento(departamento);
               this.listarDepartamento();
            }
            catch(Exception e)
            {
                System.out.println("error en departamentoBEAN metodo -->modificar"+e);
            }
    } 
     
     //metodo operar para elegir la opcion de registrar o modificar
     
     public void operarDepartamento() throws Exception{
        switch(accion)
        {
            case "Registrar":
                this.registrarDepartamento();
               this.limpiarDepartamento();
                break;
            case "Modificar":
                this.modificarDepartamento();
               this.limpiarDepartamento();
                break;
        }
    }
     /*Este se sustituira por el metodo dar de baja en donde solo cambia el estatus
     public void eliminarDepartamento(Departamento departamentoEliminar) throws Exception{
        DepartamentoDAO departamentodao;
            try{
                departamentodao= new DepartamentoDAO();
                departamentodao.eliminarDepartamento(departamentoEliminar);
                this.listarDepartamento();
            }
            catch(Exception e)
            {
                throw e;
            }
    }
     */
     public void elegirDatoDepartamentoBaja(Departamento departamentoElegirDato) throws Exception{
     //esto es para dar de baja primero se elige el dato y despues se pone en inactivo
        DepartamentoDAO departamentodao;
        Departamento departamentoTemporal;
        try{
            departamentodao= new DepartamentoDAO();
            departamentoTemporal=departamentodao.elegirDatoDepartamento(departamentoElegirDato);
            
            if(departamentoTemporal != null){
                this.departamento = departamentoTemporal;
            }
            this.bajaDepartamento();//se manda a llamar al metodo dar de baja para q se modifique el estatus por INACTIVO
            this.listarDepartamento();//para actualizar la tabla y se vea reflejado el cambio de estatus
            }
        catch (Exception e){
            throw e;
        }
        
    }
     
     public void bajaDepartamento() throws Exception{
        DepartamentoDAO departamentodao;
            try{
                departamentodao= new DepartamentoDAO();
                departamento.setEstatus(false);
                departamentodao.modificarDepartamento(departamento);
            }
            catch(Exception e)
            {
                throw e;
            }
    } 
     
     public void limpiarDepartamento(){
        this.departamento.setClave_departamento("");
        this.departamento.setNombre_departamento("");
        this.departamento.setArea(null);
        this.departamento.setEstatus(Boolean.TRUE);
    }
     
      //Metodo ´para listar 
    
    public void listarDepartamento() throws Exception{
        DepartamentoDAO departamentodao;
        try{
            departamentodao=new DepartamentoDAO();
            listadepartamento = departamentodao.listarDepartamento();
        }
        catch(Exception e){
            System.out.println("error en DepartamentoBEAN --> listarDepartamentoBEAN"+e);
        }
    }
    
    public void listaDepartamentoServicio(){
        DepartamentoDAO departamentodao;
        try{
            departamentodao = new DepartamentoDAO();
            listadepartamentoServicio = departamentodao.listarDepartamentoServicios();
        }catch(Exception e){
            System.out.println("Error en DepartamentoBEAN -> listaDepartamentoServicio "+e);
        }
    }
}
