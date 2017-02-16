/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;



import com.javeros.anonimos.code.Rfc;
import com.javeros.anonimos.code.dto.PersonaRfcDto;
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
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.StringTokenizer;
import javax.faces.validator.ValidatorException;
import mx.edu.itoaxaca.mantenimientocc.correo.CorreoCambioDatosUsuario;
import mx.edu.itoaxaca.mantenimientocc.correo.CorreoRegistroUsuario;
import mx.edu.itoaxaca.mantenimientocc.dao.Empleado_periodoDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Empleado_periodo;
import mx.edu.itoaxaca.mantenimientocc.modelo.Periodo_semestral;
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
    private List<Empleado_periodo> listaEmpleadoPeriodo =  new ArrayList();
    private Periodo_semestral periodo=new Periodo_semestral();
    //para el objeto que necesita en modificar EmpleadoPeriodo
    private Empleado_periodo empleadoPeriodoModifica= new Empleado_periodo();
    private List<Usuario> seleccionUsuario;

    public List<Usuario> getSeleccionUsuario() {
        return seleccionUsuario;
    }

    public void setSeleccionUsuario(List<Usuario> seleccionUsuario) {
        this.seleccionUsuario = seleccionUsuario;
    }

    public Periodo_semestral getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo_semestral periodo) {
        this.periodo = periodo;
    }

    


    
    
    public List<Empleado_periodo> getListaEmpleadoPeriodo() {
        return listaEmpleadoPeriodo;
    }

    public void setListaEmpleadoPeriodo(List<Empleado_periodo> listaEmpleadoPeriodo) {
        this.listaEmpleadoPeriodo = listaEmpleadoPeriodo;
    }

 
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
    private List<Usuario> listarUsuariosNivelTresParaOrdenTrabajo;
    private List<Usuario> listarUsuarioNivelUnoParaAdministrador;

    public List<Usuario> getListarUsuarioNivelUnoParaAdministrador() {
        return listarUsuarioNivelUnoParaAdministrador;
    }

    public void setListarUsuarioNivelUnoParaAdministrador(List<Usuario> listarUsuarioNivelUnoParaAdministrador) {
        this.listarUsuarioNivelUnoParaAdministrador = listarUsuarioNivelUnoParaAdministrador;
    }
 
    public List<Usuario> getListarUsuariosNivelTresParaOrdenTrabajo() {
        return listarUsuariosNivelTresParaOrdenTrabajo;
    }

    public void setListarUsuariosNivelTresParaOrdenTrabajo(List<Usuario> listarUsuariosNivelTresParaOrdenTrabajo) {
        this.listarUsuariosNivelTresParaOrdenTrabajo = listarUsuariosNivelTresParaOrdenTrabajo;
    }
     
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
        Empleado_periodoDAO empleado_periodoDao;
        try {
            usuarioDao = new UsuarioDAO();
            
            empleado_periodoDao = new Empleado_periodoDAO();
            objetoUsuario.setCorreo(usuarioCorreoNombre+usuarioCorreoServicio);
            usuarioDao.registrarUsuario(objetoUsuario);
            

                //para agregar empleado_periodo
                Usuario usuarioEmpleado_periodo= usuarioDao.consultarUsuario(objetoUsuario.getCorreo());
                
                Empleado_periodo empleado_periodo = new Empleado_periodo();
                if(new java.sql.Date(new java.util.Date().getTime()).getMonth()<=5)
                {
                    periodo.setIdperiodo_semestral(1);
                    periodo.setPeriodo("Ene-Jun");
                }
                if(new java.sql.Date(new java.util.Date().getTime()).getMonth()>5){
                     periodo.setIdperiodo_semestral(2);
                    periodo.setPeriodo("Ago-Dic");
                }
                
                empleado_periodo.setId_periodo(periodo);
                empleado_periodo.setAño(String.valueOf(new java.sql.Date(new java.util.Date().getTime()).getYear()+1900));
                empleado_periodo.setId_usuario_personal(usuarioEmpleado_periodo);
                empleado_periodoDao.registrarEmpleado_periodo(empleado_periodo);
    
                System.out.println("usuarioPeriodo " + empleado_periodo.getId_usuario_personal());
                System.out.println("periodo " + empleado_periodo.getId_periodo().getIdperiodo_semestral());
                System.out.println("Año " + empleado_periodo.getAño());
                 this.listaUsuarioDepartameto();
            
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
            this.modificarUsuarioRFC();
            
                usuarioDao.registrarUsuario(registroUsuarioNuevo);
                setMensajeClaseUsuario("Usuario Registrado");
                new CorreoRegistroUsuario().enviarMensaje(registroUsuarioNuevo.getCorreo(), registroUsuarioNuevo.getClave());
                System.out.println(mensajeClaseUsuario);
                 
            
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

    /*@PostConstruct
    public void llenaListaOficinaUsuario() {
        listaOficinaUsuario = new ArrayList<SelectItem>();
        Departamento departamento;
        Area area;
        Oficina_solicitante oficina;
        try {
            List<Area> areaDAO = new AreaDAO().listarAreaOtrasVistas();
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
*/
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
        this.limpiaUsuario();
        this.accionDeBotonUsuario = accionDeBotonUsuario;
        
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
     public void listarUsuarioNivelUno() throws Exception{
       UsuarioDAO usuariodao;
        try{
            usuariodao=new UsuarioDAO();
            listarUsuarioNivelUnoParaAdministrador = usuariodao.listaUsuarioNivelUno();
            
        }
        catch(Exception e){
            System.out.println("error en UsuarioNivelUnoBEAN --> listarNivelUnoUusuariosBEAN"+e);
        }
    }
    
    

    public void modificarUsuario() {
        UsuarioDAO usuariodao;
        try {
            usuariodao = new UsuarioDAO();
            objetoUsuario.setCorreo(usuarioCorreoNombre+usuarioCorreoServicio);
            Thread CorreoModifica = new Thread(new CorreoCambioDatosUsuario(objetoUsuario));
            CorreoModifica.start();
            Thread.sleep(2500);
            usuariodao.modificarUsuario(objetoUsuario);
            this.listaUsuarioDepartameto();
        } catch (Exception ex) {

        }
    }
    public void modificarUsuarioRFC() {
        UsuarioDAO usuariodao;
        try {
            usuariodao = new UsuarioDAO();
            usuariodao.modificarUsuarioRFC(objetoUsuario);
        } catch (Exception ex) {

        }
    }
    @PostConstruct
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
        this.objetoUsuario.setNivel(1);
        this.objetoUsuario.setId_profesion(null);
        setIdArea(0);
        setIdDepartamento(0);
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
        String []correo = new String[3];
        try {
            usuariodao = new UsuarioDAO();
            usuarioParcial = new Usuario();
            usuarioParcial = usuariodao.consultarUsuarioPorId(usuarioElegido);
            if(usuarioParcial!=null){
                this.objetoUsuario = usuarioParcial;
                //periodo = new Empleado_periodoDAO().
                setIdArea(objetoUsuario.getIdOficina().getDepartamento().getArea().getIdarea());
                setIdDepartamento(objetoUsuario.getIdOficina().getDepartamento().getIddepartamento());
                StringTokenizer st =new StringTokenizer(objetoUsuario.getCorreo(),"@",true);
                int contador = 0;
                while(st.hasMoreElements()){
                    correo[contador]=st.nextToken();
                    contador++;   
                }
                setUsuarioCorreoNombre(correo[0]);
                setUsuarioCorreoServicio(correo[1]+correo[2]);
                departamentoDeUnArea();
                oficinaDeUnDepartamento();
                this.accionDeBotonUsuario = "Modificar";
                
            }
        } catch (Exception ex) {
            System.out.println("Error en UsuarioBEAN -> elegirDatoUsuario " + ex);
        }
    }
    
    //PARA EMPLEADO_PERIODO METODO MODIFICAR
    private void modificarEmpleadoPeriodo() throws Exception{
        Empleado_periodoDAO empleadoPeriododao;
            try{
                empleadoPeriododao= new Empleado_periodoDAO();
                empleadoPeriododao.modificarEmpleadoPeriodo(empleadoPeriodoModifica);
            }
            catch(Exception e)
            {
                throw e;
            }
    } 
     public void elegirDatoEmpleadoPeriodo(Empleado_periodo EmpPeriodoElegirDato) throws Exception{
        Empleado_periodoDAO empleadoPeriododao;
        Empleado_periodo emPeriodoTemporal;
        try{
            empleadoPeriododao= new Empleado_periodoDAO();
            emPeriodoTemporal = new Empleado_periodo();
            emPeriodoTemporal = empleadoPeriododao.elegirDatoEP(EmpPeriodoElegirDato);
            
            if(emPeriodoTemporal != null){
                this.empleadoPeriodoModifica = emPeriodoTemporal;
            }
            }
        catch (Exception e){
            throw e;
        }
        
    }
    //////////////////////
    public void validaContraseña(){
        new Runnable(){
            @Override
            public void run() {
                if(confirmacionContraseña.equals(registroUsuarioNuevo.getClave())){
            mensajeContraseña = "";
        }else{
            FacesContext.getCurrentInstance().addMessage("mensaje3", new FacesMessage("Error Contraseña No Coincide"));
        }
               
            }
            
        }.run();
        
    }
    public void listarUsuario() throws Exception{
       UsuarioDAO usuariodao;
        try{
            usuariodao=new UsuarioDAO();
             Usuario usuarioVive = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            listarTodosLosUsuarios = usuariodao.listaUsuarioDepartamentoEnAsignacion(usuarioVive);
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
        listaAreas = areaDao.listarAreaOtrasVistas();
        listaDepartamento = departamentoDao.listarDepartamentoOtrasVistas();
        listaOficinas = oficinaDao.listarOficinaOtrasVistas();
        }catch(Exception ex){
            System.out.println("Error en UsuarioBEAN -> llenarListasAreaDepartamentoOficina "+ex);
        } 
    }
    
    public void departamentoDeUnAreaRegistro(){
        //setIdArea(3);
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
    
    public void departamentoDeUnArea(){
        setIdArea(3);
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
    
    public void listarUsuarioNivelTres() throws Exception{
       UsuarioDAO usuariodao;
        try{
            usuariodao=new UsuarioDAO();
            Usuario usuarioVive = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            listarUsuariosNivelTresParaOrdenTrabajo = usuariodao.listaUsuarioNivelTres(usuarioVive);
            
        }
        catch(Exception e){
            System.out.println("error en UsuarioNivelTresBEAN --> listarNivelTresUusuariosBEAN"+e);
        }
    }
    
    //--------------------------para dar de baja a usuario Estatus de Activo A Inactivo
     public void elegirDatoUsuarioBaja(Usuario usuarioElegido) throws Exception {
        UsuarioDAO usuariodao;
        Usuario usuarioParcial;
        try {
            usuariodao = new UsuarioDAO();
             usuarioParcial = new Usuario();
            usuarioParcial = usuariodao.elegirDatoUsuarioBaja(usuarioElegido);
            if(usuarioParcial!=null){
                this.objetoUsuario = usuarioParcial; 
            }
            this.bajaUsuario();
            this.listaUsuarioDepartameto();
            
        } catch (Exception ex) {
            System.out.println("Error en UsuarioBEAN -> elegirDatoUsuarioBaja " + ex);
        }
    }
     
    public void bajaUsuario() throws Exception {
        UsuarioDAO usuariodao;
        try {
            usuariodao = new UsuarioDAO();
            objetoUsuario.setEstatus(false);
            usuariodao.modificarUsuario(objetoUsuario);
        } catch (Exception ex) {
             throw ex;

        }
    }
    /*metodos para */
    public void valida(){
        
               comparaRFC(registroUsuarioNuevo);
          
        
    }
    public void comparaRFC(Usuario obj){
             new Runnable(){
            @Override
            public void run() {
              
            
            String RFCSinHomoclave="";
            //System.out.println(cumpleaños+"");
            char nom = obj.getNombre().charAt(0);
            String apP=String.valueOf(obj.getApellidoPaterno().charAt(0));
            char vocal=0;
            int contador=1;
            while(vocal==0){
                String tmp=String.valueOf(obj.getApellidoPaterno().charAt(contador));
                
                if(tmp.equalsIgnoreCase("a")||tmp.equalsIgnoreCase("e")||tmp.equalsIgnoreCase("i")||tmp.equalsIgnoreCase("o")||tmp.equalsIgnoreCase("u"))
                { apP+=tmp;
                    vocal=obj.getApellidoPaterno().charAt(contador);
                    }
                contador++;
            }
            
            char apMat = obj.getApellidoMaterno().charAt(0);
            int año = obj.getFecha_nacimiento().getYear();
            int mes = obj.getFecha_nacimiento().getMonth()+1;
            int dia = obj.getFecha_nacimiento().getDate();
            RFCSinHomoclave=apP+apMat+nom;
            if(RFCSinHomoclave.equalsIgnoreCase("culo")||RFCSinHomoclave.equalsIgnoreCase("pene")||RFCSinHomoclave.equalsIgnoreCase("pito")){
                String tres = RFCSinHomoclave.substring(0,3);
                RFCSinHomoclave = tres;
                RFCSinHomoclave+="X";
            }
            RFCSinHomoclave +=año;
            if(mes<10)
                RFCSinHomoclave+="0"+mes;
            
            else
                RFCSinHomoclave+=mes;
            
            if(dia<10)
                RFCSinHomoclave+="0"+dia;
            
            else
                RFCSinHomoclave+=dia;
            
            System.out.println(RFCSinHomoclave.toUpperCase());
            
            if(!RFCSinHomoclave.equalsIgnoreCase(obj.getRfc().substring(0,9)))
            {  
                 FacesContext.getCurrentInstance().addMessage("mensaje2", new FacesMessage(FacesMessage.SEVERITY_WARN.toString(),"Error RFC Invalido"));
                 
            }
       }
            
        }.run();
    }
    
    public void elaboraRFC(){
            int año = registroUsuarioNuevo.getFecha_nacimiento().getYear()+1900;
            int mes = registroUsuarioNuevo.getFecha_nacimiento().getMonth()+1;
            int dia = registroUsuarioNuevo.getFecha_nacimiento().getDate();
            Rfc rfc = new Rfc();
            PersonaRfcDto persona = new PersonaRfcDto();
            persona.setNombre(registroUsuarioNuevo.getNombre());
            persona.setApPaterno(registroUsuarioNuevo.getApellidoPaterno());
            persona.setApMaterno(registroUsuarioNuevo.getApellidoMaterno());
            String mesc;
            String diac;
             if(mes<10)
                mesc="0"+mes;
            
            else
                mesc=mes+"";
            
            if(dia<10)
                diac="0"+dia;
            
            else
                diac=dia+"";
            System.out.println(año+""+mesc+""+diac);
            persona.setFecha(año+""+mesc+""+diac);
            System.out.println(rfc.generarRfc(persona));
            registroUsuarioNuevo.setRfc(rfc.generarRfc(persona));
    }
    
    public void elaboraRFCUsuarioTresDos(){
            int año = objetoUsuario.getFecha_nacimiento().getYear()+1900;
            int mes = objetoUsuario.getFecha_nacimiento().getMonth()+1;
            int dia = objetoUsuario.getFecha_nacimiento().getDate();
            Rfc rfc = new Rfc();
            PersonaRfcDto persona = new PersonaRfcDto();
            persona.setNombre(objetoUsuario.getNombre());
            persona.setApPaterno(objetoUsuario.getApellidoPaterno());
            persona.setApMaterno(objetoUsuario.getApellidoMaterno());
            String mesc;
            String diac;
             if(mes<10)
                mesc="0"+mes;
            
            else
                mesc=mes+"";
            
            if(dia<10)
                diac="0"+dia;
            
            else
                diac=dia+"";
            System.out.println(año+""+mesc+""+diac);
            persona.setFecha(año+""+mesc+""+diac);
            System.out.println(rfc.generarRfc(persona));
           objetoUsuario.setRfc(rfc.generarRfc(persona));
    }
}
