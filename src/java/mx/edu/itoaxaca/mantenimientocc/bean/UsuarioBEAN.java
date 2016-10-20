/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
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

    public void registrarUsuario() throws Exception {
        UsuarioDAO usuarioDao;
        try {
            usuarioDao = new UsuarioDAO();
            usuarioDao.registrarUsuario(objetoUsuario);
        } catch (Exception e) {
            System.out.println("=========Error en UsuarioBEAN -> registrarUsuario" + e + "============");
            throw e;
        }
    }
    
    @PostConstruct
    public void llenaListaOficinaUsuario(){
        listaOficinaUsuario = new ArrayList<SelectItem>();
       Departamento departamento;
        Area area;
         Oficina_solicitante oficina;
        try {
            List<Area> areaDAO = new AreaDAO().listarArea();
            Iterator recorrerAreaDAO = areaDAO.listIterator();
            
            while (recorrerAreaDAO.hasNext()) {
                
                area = new Area();
                area = (Area) recorrerAreaDAO.next();
                SelectItemGroup grupoArea = new SelectItemGroup(area.getNombre_area());
                int idArea = area.getIdarea();

                List<Departamento> departamentoDAO = new DepartamentoDAO().buscarDepartamentoPorIdArea(idArea);
                Iterator recorrerListaDepartamentoDAO = departamentoDAO.listIterator();
                int contadorDepartamento = 0;
                SelectItem arregloItemDepartamento[] = new SelectItem[departamentoDAO.size()];
                while (recorrerListaDepartamentoDAO.hasNext()) {

                    departamento = new Departamento();
                    departamento = (Departamento) recorrerListaDepartamentoDAO.next();
                    SelectItemGroup grupoDepartamento = new SelectItemGroup(departamento.getNombre_departamento());
                    int idDepartamento = departamento.getIddepartamento();

                    List<Oficina_solicitante> oficinaSolicitanteDAO = new Oficina_solicitanteDAO().buscarOficinaSolicitantePorIdDepartamento(idDepartamento);
                    Iterator recorrerListaOficinaSolicitanteDAO = oficinaSolicitanteDAO.listIterator();
                    int contadorOficina = 0;

                    SelectItem arregloItemOficina[] = new SelectItem[oficinaSolicitanteDAO.size()];
                    while (recorrerListaOficinaSolicitanteDAO.hasNext()) {
                       
                         oficina = new Oficina_solicitante();
                        oficina = (Oficina_solicitante) recorrerListaOficinaSolicitanteDAO.next();
                        SelectItem itemOficina = new SelectItem(oficina, oficina.getNombreOficina());
                        
                        arregloItemOficina[contadorOficina] = itemOficina;
                        contadorOficina++;
                    }
                    grupoDepartamento.setSelectItems(arregloItemOficina);
                    arregloItemDepartamento[contadorDepartamento] = grupoDepartamento;
                    contadorDepartamento++;
                }
                grupoArea.setSelectItems(arregloItemDepartamento);
                
                listaOficinaUsuario.add(grupoArea);
            }
            
        } catch (Exception e) {
           System.out.println("Error en UsuarioBEAN -> llenarListaOficinaUsuario "+e); 
          
        }
    }
    
    public List<SelectItem> getListaOficinaUsuario() {
        return listaOficinaUsuario;
    }    
}
