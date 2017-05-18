/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import mx.edu.itoaxaca.mantenimientocc.dao.AccesoDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;
import mx.edu.itoaxaca.mantenimientocc.validacion.ValidacionMantenimientoRFC;
import org.primefaces.context.RequestContext;

/**
 *
 * @author leiver
 */
@ManagedBean(name = "accesoBEAN")
@ViewScoped
public class AccesoBEAN implements Serializable{

    private String mensajeAcceso;
    private String redireccion;
    private String correo;
    private String clave;
    private Usuario usuarioBean = new Usuario();
    private String nombreCompleto;
    
    public String getRedireccion() {
        return redireccion;
    }

    public void setRedireccion(String redireccion) {
        this.redireccion = redireccion;
    }

    public String URL() {
        return redireccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getMensajeAcceso() {
        return mensajeAcceso;
    }

    public void setMensajeAcceso(String mensajeAcceso) {
        this.mensajeAcceso = mensajeAcceso;
    }

    public Usuario getUsuarioBean() {
        return usuarioBean;
    }

    public void setUsuarioBean(Usuario usuarioBean) {
        this.usuarioBean = usuarioBean;
    }

    public void accederSistema() throws Exception {
        AccesoDAO accesodao;
        try {
            accesodao = new AccesoDAO();
            System.out.println("valores de correo=" + correo + " valore de clave=" + clave);
            System.out.println("objeto de recepcion " + accesodao.accesoUsuario(correo, clave) + "");
            if (accesodao.accesoUsuario(correo, clave) != null) {
                usuarioBean = accesodao.accesoUsuario(correo, clave);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuarioBean);
                setMensajeAcceso("Bienvenido");
                if(usuarioBean.getNivel()==0){
                setRedireccion("principalNivelUno.xhtml");
                }
                if(usuarioBean.getNivel()==1){
                setRedireccion("principalNivelUno.xhtml");
                }
                if(usuarioBean.getNivel()==2){
                setRedireccion("principalNivelDos.xhtml");
                }
                 if(usuarioBean.getNivel()==3){
                setRedireccion("principal.xhtml");
                }
               // new ValidacionMantenimientoRFC().comparaRFC(usuarioBean.getNombre(), usuarioBean.getApellidoPaterno(), usuarioBean.getApellidoMaterno(), usuarioBean.getFecha_nacimiento());
            } else {
                setMensajeAcceso("Credenciales incorrectos");
                FacesMessage mensajeSalida = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", mensajeAcceso);
                RequestContext.getCurrentInstance().showMessageInDialog(mensajeSalida);
                setRedireccion("index.xhtml");

            }
        } catch (Exception ex) {
            System.out.println("Error en AccesoBEAN -> accederSistema " + ex);
            throw ex;
        }

    }

    public void setNombreCompleto() {
       nombreCompleto = usuarioBean.getNombre() + " " + usuarioBean.getApellidoPaterno() + " " + usuarioBean.getApellidoMaterno();
        System.out.println(nombreCompleto);
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void exite() {
        try {
            FacesContext contexto = FacesContext.getCurrentInstance();
            Usuario usuarioVive = (Usuario) contexto.getExternalContext().getSessionMap().get("usuario");
            if (usuarioVive == null) {
                contexto.getExternalContext().redirect("index.xhtml");
            } else {
              //  nombreCompleto = usuarioVive.getNombre() + " " + usuarioVive.getApellidoPaterno() + " " + usuarioVive.getApellidoMaterno();
                 nombreCompleto = " " + usuarioVive.getNombre() ;
            }
        } catch (Exception ex) {

        }
    }

    
}
