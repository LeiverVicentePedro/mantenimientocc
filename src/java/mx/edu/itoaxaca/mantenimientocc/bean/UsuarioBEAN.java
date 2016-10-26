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
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author leiver
 */
@ManagedBean(name = "usuarioBEAN")
@ViewScoped
public class UsuarioBEAN {

    private Usuario objetoUsuario = new Usuario();
    private Usuario registroUsuarioNuevo = new Usuario();
    private String confirmacionContraseña;
    private String mensajeClaseUsuario = "vacio";
    private List<SelectItem> listaOficinaUsuario;
    static String usuarioCorreo, contraseñaCorreo;
    static Properties propiedades;
    static Session sesion;

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

    public void registrarNuevoUsuario(ActionEvent ec) throws Exception {
        UsuarioDAO usuarioDao;
        try {
            usuarioDao = new UsuarioDAO();
            registroUsuarioNuevo.setEstatus(true);
            registroUsuarioNuevo.setNivel(1);
            registroUsuarioNuevo.setTipoBT("Base");

            if (confirmacionContraseña.equals(registroUsuarioNuevo.getClave())) {
                usuarioDao.registrarUsuario(registroUsuarioNuevo);
                setMensajeClaseUsuario("Usuario Registrado");
                enviarMensaje(registroUsuarioNuevo.getCorreo(),registroUsuarioNuevo.getClave());
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

    /* creacion de metodos para envio de mensajeria por correo*/
    private void enviarMensaje(String correo,String clave) {
        try {
            setup();
            String asunto="Registro en el Sistema SIMAPRECO del TEC-OAX";
            String mensaje="Gracias por registrarse/n"+"Su Cuenta a sido creada de manera satisfactoria a con los siguentes datos:\n"
                    +"DATOS PARA ACCEDER A SU CUENTA\n"+
                    correo+"\n"+
                    clave+"\n"+
                    "Con esta informacion podra acceder a su cuenta en el sistema";

            Message crearCorreo = new MimeMessage(sesion);
            crearCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(correo));
            crearCorreo.setSubject(asunto);
            crearCorreo.setText(mensaje);
//Enviamos el Mensaje
            Transport.send(crearCorreo);
        } catch (Exception ex) {
            System.out.println("Error en UsuarioBEAN -> enviarMensaje "+ex);
        }
    }

    private static void setup() throws MessagingException {
        //datos de conexion
        usuarioCorreo = "vpleiver@gmail.om";
        contraseñaCorreo = "elviamaria";
        //propiedades de la conexion
        propiedades = new Properties();
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.port", "587");

        //creamos la sesion
        sesion = crearSesion();
    }

    private static Session crearSesion() {
        Session session = Session.getInstance(propiedades,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuarioCorreo, contraseñaCorreo);
            }
        });
        return session;
    }
}
