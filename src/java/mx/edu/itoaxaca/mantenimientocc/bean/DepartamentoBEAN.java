/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;


import java.util.List;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mx.edu.itoaxaca.mantenimientocc.dao.DepartamentoDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Area;
import mx.edu.itoaxaca.mantenimientocc.modelo.Departamento;



@ManagedBean
@ViewScoped
public class DepartamentoBEAN {
    
  private Departamento departamento= new Departamento();
  private List<Departamento> listadepartamento;
  private String accion;//esta variable es para usarla en un switch y poder escoger si es opcion registrar o modificar

  
  
  
  
   //metodo get y set de variable accion
    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
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
    
   //Metodo ´para listar 
    
    public void listarDepartamento() throws Exception{
        DepartamentoDAO departamentodao;
        try{
            departamentodao=new DepartamentoDAO();
            listadepartamento = departamentodao.listarDepartamento();
        }
        catch(Exception e){
            System.out.println("error en Departamento BEAN -->ListarDepartamentoBEAN"+e);
        }
    }
    
    //metodo elegir dato de departamento 
    public void elegirDatoDepartamento(Departamento departamentoElegirDato) throws Exception{
        DepartamentoDAO departamentodao;
        Departamento departamentoTemporal;
        try{
            departamentodao= new DepartamentoDAO();
            departamentoTemporal=departamentodao.elegirDatoDepartamento(departamentoElegirDato);
            
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
               // this.listarDepartamento();
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
                //this.limpiarDepartamento();
                break;
            case "Modificar":
                this.modificarDepartamento();
               // this.limpiarDepartamento();
                break;
        }
    }
     
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
    
    
}
