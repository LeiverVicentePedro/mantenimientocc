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



@ManagedBean
@ViewScoped
public class DepartamentoBEAN {
    
  private Departamento departamento= new Departamento();
  private List<Departamento> listadepartamento;

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    
    public void registrarDepartamento() throws Exception{
        DepartamentoDAO departamentoDao;
            try{
                departamentoDao= new DepartamentoDAO();
                departamentoDao.registrarDepartamento(departamento);
            }
            catch(Exception e)
            {
                System.out.println("error en BEAN");
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
            throw e;
        }
    }
    
    
}
