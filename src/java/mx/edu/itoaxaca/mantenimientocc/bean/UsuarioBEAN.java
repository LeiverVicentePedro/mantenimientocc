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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
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
import org.primefaces.context.RequestContext;
import javax.mail.Session;
import java.util.Properties;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import mx.edu.itoaxaca.mantenimientocc.correo.CorreoRegistroUsuario;
/**
 *
 * @author leiver
 */
@ManagedBean(name = "usuarioBEAN")
@ViewScoped
public class UsuarioBEAN implements Serializable{

    private Usuario objetoUsuario = new Usuario();
    private Usuario registroUsuarioNuevo = new Usuario();
    private String confirmacionContraseña;
    private String mensajeClaseUsuario = "vacio";
    private List<SelectItem> listaOficinaUsuario;
    private List<Usuario> listaUsuariosDeUnDepartamento;
    private List<Usuario> filterUsuario;
    private List<Usuario> listaParaFiltro;
    
    private List<Usuario> listarTodosLosUsuarios;
    
    private List<String> listaServicioCorreo;
    
    private String usuarioCorreoNombre;
    private String usuarioCorreoServicio;

    
    private String recuperaCorreo;
    private String accionDeBotonUsuario;
    private String mensajeContraseña;
    
    private List<Area> listaAreas;
    private List<Departamento> listaDepartamento;
    private List<Oficina_solicitante> listaOficinas;
    
    private List<Departamento> listaTemporalDepartamento;
    private List<Oficina_solicitante> listaTemporalOficina;
    private int idArea;
    private int idDepartamento;
    private int idOficina;

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public int getIdOficina() {
        return idOficina;
    }

    public void setIdOficina(int idOficina) {
        this.idOficina = idOficina;
    }

    public List<Area> getListaAreas() {
        return listaAreas;
    }

    public void setListaAreas(List<Area> listaAreas) {
        this.listaAreas = listaAreas;
    }

    public List<Departamento> getListaTemporalDepartamento() {
        return listaTemporalDepartamento;
    }

    public void setListaTemporalDepartamento(List<Departamento> listaTemporalDepartamento) {
        this.listaTemporalDepartamento = listaTemporalDepartamento;
    }

    public List<Oficina_solicitante> getListaTemporalOficina() {
        return listaTemporalOficina;
    }

    public void setListaTemporalOficina(List<Oficina_solicitante> listaTemporalOficina) {
        this.listaTemporalOficina = listaTemporalOficina;
    }

    
    public List<Usuario> getListarTodosLosUsuarios() {
        return listarTodosLosUsuarios;
    }

    public void setListarTodosLosUsuarios(List<Usuario> listarTodosLosUsuarios) {
        this.listarTodosLosUsuarios = listarTodosLosUsuarios;
    }

    
    public List<Usuario> getFilterUsuario() {
        return filterUsuario;
    }

    public void setFilterUsuario(List<Usuario> filterUsuario) {
        this.filterUsuario = filterUsuario;
    }

    public List<String> getListaServicioCorreo() {
        return listaServicioCorreo;
    }

    public void setListaServicioCorreo(List<String> listaServicioCorreo) {
        this.listaServicioCorreo = listaServicioCorreo;
    }
    


    public void registrarUsuario() throws Exception {
        UsuarioDAO usuarioDao;
        try {
            usuarioDao = new UsuarioDAO();
            objetoUsuario.setCorreo(usuarioCorreoNombre+usuarioCorreoServicio);
            usuarioDao.registrarUsuario(objetoUsuario);
        } catch (Exception e) {
            System.out.println("=========Error en UsuarioBEAN -> registrarUsuario" + e + "============");
            throw e;
        }
    }

    public void registrarNuevoUsuario(ActionEvent ec) throws Exception {
        UsuarioDAO usuarioDao;
        try {
            usuarioDao = new UsuarioDAO();
            registroUsuarioNuevo.setEstatus(true);
            registroUsuarioNuevo.setNivel(1);
            registroUsuarioNuevo.setTipoBT("Base");
            registroUsuarioNuevo.setCorreo(usuarioCorreoNombre+usuarioCorreoServicio);
            if (confirmacionContraseña.equals(registroUsuarioNuevo.getClave())) {
                usuarioDao.registrarUsuario(registroUsuarioNuevo);
                setMensajeClaseUsuario("Usuario Registrado");
                new CorreoRegistroUsuario().enviarMensaje(registroUsuarioNuevo.getCorreo(), registroUsuarioNuevo.getClave());
                System.out.println(mensajeClaseUsuario);
                 
            } else {
                setMensajeClaseUsuario("Las Contraseñas no Coinciden");
                System.out.println(mensajeClaseUsuario);
            }
            FacesMessage mensajeSalida = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", mensajeClaseUsuario);
            RequestContext.getCurrentInstance().showMessageInDialog(mensajeSalida);
        } catch (Exception e) {
            System.out.println("=========Error en UsuarioBEAN -> registrarNuevoUsuario" + e + "============");
            throw e;
        }
    }

    public void recuperarCuenta() throws Exception {
        UsuarioDAO usuarioDao;
        Usuario usuariodeCuenta = new Usuario();
        try {
            usuarioDao = new UsuarioDAO();
            usuariodeCuenta = usuarioDao.consultarUsuario(recuperaCorreo);

            if (usuariodeCuenta.getCorreo().isEmpty()) {
                mensajeClaseUsuario = "El Correo no Existe Proporcione el correo con el que se registro.";
            } else {
                mensajeClaseUsuario = "La informacion a sido enviada a su Correo.";
                new CorreoRegistroUsuario().enviarMensajeRecuperacion(usuariodeCuenta.getCorreo(), usuariodeCuenta.getClave());
            }
            FacesMessage mensajeSalida = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", mensajeClaseUsuario);
            RequestContext.getCurrentInstance().showMessageInDialog(mensajeSalida);
        } catch (Exception ex) {
            System.out.println("Error en UsuarioBEAN ->recuperarCuenta " + ex);
            throw ex;
        }
    }

    @PostConstruct
    public void llenaListaOficinaUsuario() {
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
            System.out.println("Error en UsuarioBEAN -> llenarListaOficinaUsuario " + e);

        }
    }

    public Usuario getRegistroUsuarioNuevo() {
        return registroUsuarioNuevo;
    }

    public void setRegistroUsuarioNuevo(Usuario RegistroUsuarioNuevo) {
        this.registroUsuarioNuevo = RegistroUsuarioNuevo;
    }

    public String getConfirmacionContraseña() {
        return confirmacionContraseña;
    }

    public void setConfirmacionContraseña(String confirmacionContraseña) {
        this.confirmacionContraseña = confirmacionContraseña;
    }

    public List<SelectItem> getListaOficinaUsuario() {
        return listaOficinaUsuario;
    }

    public void setListaOficinaUsuario(List<SelectItem> listaOficinaUsuario) {
        this.listaOficinaUsuario = listaOficinaUsuario;
    }

    public Usuario getObjetoUsuario() {
        return objetoUsuario;
    }

    public void setObjetoUsuario(Usuario objetoUsuario) {
        this.objetoUsuario = objetoUsuario;
    }

    public String getMensajeClaseUsuario() {
        return mensajeClaseUsuario;
    }

    public void setMensajeClaseUsuario(String mensajeClaseUsuario) {
        this.mensajeClaseUsuario = mensajeClaseUsuario;
    }

    public String getRecuperaCorreo() {
        return recuperaCorreo;
    }

    public void setRecuperaCorreo(String recuperaCorreo) {
        this.recuperaCorreo = recuperaCorreo;
    }

    public List<Usuario> getListaUsuariosDeUnDepartamento() {
        return listaUsuariosDeUnDepartamento;
    }

    public void setListaUsuariosDeUnDepartamento(List<Usuario> listaUsuariosDeUnDepartamento) {
        this.listaUsuariosDeUnDepartamento = listaUsuariosDeUnDepartamento;
    }

    public List<Usuario> getListaParaFiltro() {
        return listaParaFiltro;
    }

    public void setListaParaFiltro(List<Usuario> listaParaFiltro) {
        this.listaParaFiltro = listaParaFiltro;
    }

    public String getAccionDeBotonUsuario() {
        return accionDeBotonUsuario;
    }

    public void setAccionDeBotonUsuario(String accionDeBotonUsuario) {
        this.accionDeBotonUsuario = accionDeBotonUsuario;
        this.limpiaUsuario();
    }

    public String getMensajeContraseña() {
        return mensajeContraseña;
    }

    public void setMensajeContraseña(String mensajeContraseña) {
        this.mensajeContraseña = mensajeContraseña;
    }
     
    public String getUsuarioCorreoNombre() {
        return usuarioCorreoNombre;
    }

    public void setUsuarioCorreoNombre(String usuarioCorreoNombre) {
        this.usuarioCorreoNombre = usuarioCorreoNombre;
    }

    public String getUsuarioCorreoServicio() {
        return usuarioCorreoServicio;
    }

    public void setUsuarioCorreoServicio(String usuarioCorreoServicio) {
        this.usuarioCorreoServicio = usuarioCorreoServicio;
    }
    

    public void listaUsuarioDepartameto() throws Exception {
        UsuarioDAO usuariodao;
        try {
            usuariodao = new UsuarioDAO();
            FacesContext contexto = FacesContext.getCurrentInstance();
            Usuario usuarioVive = (Usuario) contexto.getExternalContext().getSessionMap().get("usuario");
            listaUsuariosDeUnDepartamento = usuariodao.listaUsuarioDepartamento(usuarioVive);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void modificarUsuario() {
        UsuarioDAO usuariodao;
        try {
            usuariodao = new UsuarioDAO();
            usuariodao.modificarUsuario(objetoUsuario);
            this.listaUsuarioDepartameto();
        } catch (Exception ex) {

        }
    }

    public void limpiaUsuario() {
        this.objetoUsuario.setNombre("");
        this.objetoUsuario.setApellidoPaterno("");
        this.objetoUsuario.setApellidoMaterno("");
        this.objetoUsuario.setClave("");
        this.objetoUsuario.setCorreo("");
        this.objetoUsuario.setRfc("");
        this.objetoUsuario.setEstatus(Boolean.TRUE);
        this.objetoUsuario.setTipoBT("");
        this.objetoUsuario.setIdOficina(null);
        this.objetoUsuario.setNivel(0);
        this.objetoUsuario.setId_profesion(null);
        setIdArea(0);
        setIdDepartamento(0);
        listaTemporalDepartamento.clear();
        listaTemporalOficina.clear();
        setUsuarioCorreoNombre("");
        setUsuarioCorreoServicio("");
        
    }

    public void establecerAccionDeBoton() throws Exception {

        switch (accionDeBotonUsuario) {
            case "Registrar":
                this.registrarUsuario();
                this.limpiaUsuario();
                break;
            case "Modificar":
                this.modificarUsuario();
                this.limpiaUsuario();
                break;
        }
    }

    public void elegirDatoUsuario(Usuario usuarioElegido) {
        UsuarioDAO usuariodao;
        Usuario usuarioParcial;
        try {
            usuariodao = new UsuarioDAO();
            usuarioParcial = new Usuario();
            usuarioParcial = usuariodao.consultarUsuarioPorId(usuarioElegido);
            if(usuarioParcial!=null){
                this.objetoUsuario = usuarioParcial;
                setIdArea(objetoUsuario.getIdOficina().getDepartamento().getArea().getIdarea());
                setIdDepartamento(objetoUsuario.getIdOficina().getDepartamento().getIddepartamento());
                departamentoDeUnArea();
                oficinaDeUnDepartamento();
                this.accionDeBotonUsuario = "Modificar";
            }
        } catch (Exception ex) {
            System.out.println("Error en UsuarioBEAN -> elegirDatoUsuario " + ex);
        }
    }
    
    public void validaContraseña(){
        if(confirmacionContraseña.equals(registroUsuarioNuevo.getClave())){
            mensajeContraseña = " ";
        }else{
            mensajeContraseña = "clave incorrecto";
        }
    }
    public void listarUsuario() throws Exception{
       UsuarioDAO usuariodao;
        try{
            usuariodao=new UsuarioDAO();
            listarTodosLosUsuarios = usuariodao.listaUsuario();
            System.out.println(listarTodosLosUsuarios.get(0).getConcatenar());
        }
        catch(Exception e){
            System.out.println("error en orden_internaBEAN --> listarordenInternaBEAN"+e);
        }
    }
    
    public void existeRFC(){
        UsuarioDAO usuarioDao;
        Usuario usuarioExistente = new Usuario();
        try{
            usuarioDao = new UsuarioDAO();
            usuarioExistente = usuarioDao.consultarUsuarioPorRFC(registroUsuarioNuevo.getRfc());
            
            if(usuarioExistente.getRfc().isEmpty()){
                FacesMessage mensajeSalida = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "El RFC ya se Encuentra Registrado.");
                RequestContext.getCurrentInstance().showMessageInDialog(mensajeSalida);
            }
        }catch(Exception ex){
            System.out.println("Error en orden_internaBEAN -> existeRFC "+ex);
        }
    }
    
    public void llenarListasAreaDepartamentoOficina(){
        AreaDAO areaDao = new AreaDAO();
        DepartamentoDAO departamentoDao = new DepartamentoDAO();
        Oficina_solicitanteDAO oficinaDao = new Oficina_solicitanteDAO();
        try{
        listaAreas = areaDao.listarArea();
        listaDepartamento = departamentoDao.listarDepartamento();
        listaOficinas = oficinaDao.listarOficina();
        }catch(Exception ex){
            System.out.println("Error en UsuarioBEAN -> lenarListasAreaDepartamentoOficina "+ex);
        } 
    }
    
    public void departamentoDeUnArea(){
        listaTemporalDepartamento = new ArrayList();
        if(idArea !=0){
        for(Departamento departamento : listaDepartamento){
            if(departamento.getArea().getIdarea() == idArea){
                listaTemporalDepartamento.add(departamento);
            }
        }
        }else{
            listaTemporalDepartamento.clear();
            listaTemporalOficina.clear();
            setIdDepartamento(0);
        }
    }
    
    public void oficinaDeUnDepartamento(){
        listaTemporalOficina = new ArrayList();
        if(idDepartamento != 0){
            for(Oficina_solicitante oficina : listaOficinas){
                if(oficina.getDepartamento().getIddepartamento()==idDepartamento){
                    listaTemporalOficina.add(oficina);
                }
            }
        }else{
            listaTemporalOficina.clear();
        }
    }
    
    public void llenaListaServicoCorreo(){
        listaServicioCorreo = new ArrayList();
        listaServicioCorreo.add("@hotmail.com");
        listaServicioCorreo.add("@gmail.com");
        listaServicioCorreo.add("@gmail.com.mx");
        listaServicioCorreo.add("@yahoo.com.mx");
        listaServicioCorreo.add("@yahoo.com");
        listaServicioCorreo.add("@itoaxaca.edu.mx");
        listaServicioCorreo.add("@outlook.com");
    }
}
