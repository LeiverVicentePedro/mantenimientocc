/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import mx.edu.itoaxaca.mantenimientocc.dao.AreaDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.DepartamentoDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.Oficina_solicitanteDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.UsuarioDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Area;
import mx.edu.itoaxaca.mantenimientocc.modelo.Departamento;
import mx.edu.itoaxaca.mantenimientocc.modelo.Oficina_solicitante;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;

/**
 *
 * @author leiver
 */
@ManagedBean
@ViewScoped
public class UsuarioBEAN {
    
   private Usuario objetoUsuario = new Usuario();
   private List<SelectItem> listaOficinaUsuario; 
   
    public Usuario getObjetoUsuario() {
        return objetoUsuario;
    }

    public void setObjetoUsuario(Usuario objetoUsuario) {
        this.objetoUsuario = objetoUsuario;
    }
   
   public void registrarUsuario() throws Exception{
       UsuarioDAO usuarioDao;
       try{
          usuarioDao = new UsuarioDAO(); 
          usuarioDao.registrarUsuario(objetoUsuario);
       }catch(Exception e){
           System.out.println("=========Error en UsuarioBEAN -> registrarUsuario"+e+ "============");
           throw e;
       }
   }
   
   public void llenaListaOficinaUsuario() throws Exception{
       listaOficinaUsuario = new ArrayList<SelectItem>();
       Area area = new Area();
       Departamento departamento = new Departamento();
       SelectItemGroup grupoArea;
       SelectItemGroup grupoDepartamento;
       try{
       List<Area> areaDAO = new AreaDAO().listarArea();
       Iterator recorrerAreaDAO = areaDAO.listIterator();
       while(recorrerAreaDAO.hasNext()){
            grupoArea = new SelectItemGroup();
             area = (Area) recorrerAreaDAO.next();
            
            int idArea = area.getIdarea();
            
            List<Departamento> departamentoDAO = new DepartamentoDAO().listarDepartamento();
            Iterator recorrerListaDepartamentoDAO = departamentoDAO.listIterator();
            while(recorrerListaDepartamentoDAO.hasNext()){
                 grupoDepartamento = new SelectItemGroup();
                 departamento = (Departamento) recorrerListaDepartamentoDAO.next();
                 
                 int idDepartamento = departamento.getIddepartamento();
                 
               //  List<Oficina_solicitante> oficinaSolicitanteDAO = new Oficina_solicitanteDAO();
                }
       }
       }catch(Exception e){
               throw e;
               }
   }
   
   
}
