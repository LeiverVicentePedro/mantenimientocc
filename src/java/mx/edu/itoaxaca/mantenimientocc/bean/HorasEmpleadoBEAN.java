/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.io.Serializable;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import mx.edu.itoaxaca.mantenimientocc.dao.ConfiguracionServicioSocialDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.DetalleHorasEmpleadoDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.HorasEmpleadoDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.TotalHorasEmpleadoDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.ConfiguracionServicioSocial;
import mx.edu.itoaxaca.mantenimientocc.modelo.DetalleHorasEmpleado;
import mx.edu.itoaxaca.mantenimientocc.modelo.HorasEmpleado;
import mx.edu.itoaxaca.mantenimientocc.modelo.TotalHorasEmpleado;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;
import org.primefaces.context.RequestContext;

/**
 *
 * @author leiver
 */
@ManagedBean
@SessionScoped
public class HorasEmpleadoBEAN implements Serializable {

    HorasEmpleado horas = new HorasEmpleado();
    DetalleHorasEmpleado detalleHoras = new DetalleHorasEmpleado();

    public HorasEmpleado getHoras() {
        return horas;
    }

    public void setHoras(HorasEmpleado horas) {
        this.horas = horas;
    }

    public DetalleHorasEmpleado getDetalleHoras() {
        return detalleHoras;
    }

    public void setDetalleHoras(DetalleHorasEmpleado detalleHoras) {
        this.detalleHoras = detalleHoras;
    }

    public void registrarHoraEmpleado() throws Exception {
        try {
            InetAddress ip;
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String ipAddress=getRemoteAddress(request);
            String ipNueva = ipAddress;
            //System.out.println("ip del local: "+ipNueva);
            //ip = InetAddress.getLocalHost();
            ConfiguracionServicioSocial configuracion = new ConfiguracionServicioSocial();
            configuracion = new ConfiguracionServicioSocialDAO().seleccionarConfiguracion();

            //if (ip.getHostAddress().trim().equalsIgnoreCase(configuracion.getIp_checador().trim())) {
            if (ipNueva.trim().equalsIgnoreCase(configuracion.getIp_checador().trim())) {
                FacesContext contexto = FacesContext.getCurrentInstance(); //paraq entrar ql dom del navegador
                Usuario usuarioVive = (Usuario) contexto.getExternalContext().getSessionMap().get("usuario");//llamo a  la etiqueta usuario que es un objeto que ya debe

                HorasEmpleado existeRegistroHoras = new HorasEmpleado();
                horas.setFecha(new java.sql.Date(new java.util.Date().getTime()));
                horas.setId_usuario_empleado(usuarioVive);
                existeRegistroHoras = new HorasEmpleadoDAO().buscarHoraEmpleado(horas);
                System.out.println("idhoras_empleado " + existeRegistroHoras.getIdhoras_empleado());
                if (existeRegistroHoras.getIdhoras_empleado() != 0) {

                    System.out.println("entro en if que comrpueba si existe el id del horas empleado " + existeRegistroHoras.getIdhoras_empleado());
                    //DetalleHorasEmpleado NuevoDetalleHoras = new DetalleHorasEmpleado();
                    System.out.println("Se crea el objeto para el detalle");

                    //NuevoDetalleHoras = new DetalleHorasEmpleadoDAO().buscaDetalleHorasEmpleado(existeRegistroHoras);
                    System.out.println("id de detalles horas empleado " + new DetalleHorasEmpleadoDAO().buscaDetalleHorasEmpleado(existeRegistroHoras).getHoraEntrada());
                    String horaAComprobar = null; //se agrega para comprobar que exista o no na hora registrada.
                    horaAComprobar = new DetalleHorasEmpleadoDAO().buscaDetalleHorasEmpleado(existeRegistroHoras).getHoraEntrada();//captura la hora de entrada si existe actualiza
                    if (horaAComprobar != null) {
                        //actualizar registro de detallesHorasEmpelado
                        DetalleHorasEmpleado NuevoDetalleHoras = new DetalleHorasEmpleado();
                        NuevoDetalleHoras = new DetalleHorasEmpleadoDAO().buscaDetalleHorasEmpleado(existeRegistroHoras);
                        System.out.println("Entro en actualiza hora salida");
                        NuevoDetalleHoras.setHoraSalida(new SimpleDateFormat("HH:mm:ss").format(new java.sql.Date(new java.util.Date().getTime())));
                        System.out.println("Hora de salida: "+NuevoDetalleHoras.getHoraSalida());
                        new DetalleHorasEmpleadoDAO().actualizarDetalleHorasEmpleado(NuevoDetalleHoras);
                        System.out.println("Exito se termino el registro");
                      
                        FacesMessage mensajeHorasFaltantes = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Hora de Salida Registrada");
                        RequestContext.getCurrentInstance().showMessageInDialog(mensajeHorasFaltantes);
                        /*termina seccion para calcular las horas que le faltan*/
                    }
                    if (horaAComprobar == null) {
                        //registramos detalle de hora de entrada nueva
                        System.out.println("es nuevo registro en detalles");
                        DetalleHorasEmpleado detalleHorasNueva = new DetalleHorasEmpleado();
                        detalleHorasNueva.setIdHorasEmpleado(existeRegistroHoras);
                        detalleHorasNueva.setHoraEntrada(new SimpleDateFormat("HH:mm:ss").format(new java.sql.Date(new java.util.Date().getTime())));

                        new DetalleHorasEmpleadoDAO().registrarDetalleHorasEmpleado(detalleHorasNueva);
                        FacesMessage mensajeSalida = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Hora de Entrada Registrada");
                        RequestContext.getCurrentInstance().showMessageInDialog(mensajeSalida);
                    }

                }
                if (existeRegistroHoras.getIdhoras_empleado() == 0) {
                    //horas.setFecha(new java.sql.Date(new java.util.Date().getTime()));//fecha sistema.
                    //horas.setId_usuario_empleado(usuarioVive);
                    System.out.println("es nuevo registro en horasEmpleado");
                    new HorasEmpleadoDAO().registrarHorasEmpleado(horas);

                    HorasEmpleado registroHoras = new HorasEmpleado();
                    registroHoras = new HorasEmpleadoDAO().buscarHoraEmpleado(horas);
                    detalleHoras.setIdHorasEmpleado(registroHoras);
                    detalleHoras.setHoraEntrada(new SimpleDateFormat("HH:mm:ss").format(new java.sql.Date(new java.util.Date().getTime())));

                    new DetalleHorasEmpleadoDAO().registrarDetalleHorasEmpleado(detalleHoras);

                    FacesMessage mensajeSalida = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Hora de Entrada Registrada");
                    RequestContext.getCurrentInstance().showMessageInDialog(mensajeSalida);
                }
            } else {
                FacesMessage mensajeSalida = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "No te Encuentras en Servicio");
                RequestContext.getCurrentInstance().showMessageInDialog(mensajeSalida);
                //System.out.println("ip checador " + configuracion.getIp_checador().trim() + "  ip equipo " + ip.getHostAddress().trim());
                System.out.println("ip checador " + configuracion.getIp_checador().trim() + "  ip equipo " + ipNueva);
            }

            /*para el registro de horas
            se debe consular si ya existe el registro si ya esta con fecha y esttus es 0 entonces es modificacion 
            pero si no tiene fecha ni estatus es insercion.
            comprobar que la ip del equipo sea el correcto para registrar si no evitar el registro.*/
        } catch (Exception ex) {
            System.out.println("Error en HorasEmpleadoBEAN -> registrarHoras " + ex);
        }
    }
    //pendiente la nueva agregacion para las horas de un empleado;

    public void registrarHorasPorAdministrador() {
        try {
            HorasEmpleado existeRegistroHoras = new HorasEmpleado();
            horas.setFecha(new java.sql.Date(horas.getFecha().getTime()));
            existeRegistroHoras = new HorasEmpleadoDAO().buscarHoraEmpleado(horas);
            System.out.println("idhoras_empleado " + existeRegistroHoras.getIdhoras_empleado());
            if (existeRegistroHoras.getIdhoras_empleado() != 0) {
                detalleHoras.setIdHorasEmpleado(existeRegistroHoras);
                new DetalleHorasEmpleadoDAO().registrarDetalleHorasEmpleadoCompleto(detalleHoras);
                FacesMessage mensajeSalida = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Registro Realizado");
                RequestContext.getCurrentInstance().showMessageInDialog(mensajeSalida);
                limpiarRegistros();
            } else {
                horas.setFecha(new java.sql.Date(horas.getFecha().getTime()));
                new HorasEmpleadoDAO().registrarHorasEmpleado(horas);

                HorasEmpleado registroHoras = new HorasEmpleado();
                registroHoras = new HorasEmpleadoDAO().buscarHoraEmpleado(horas);
                detalleHoras.setIdHorasEmpleado(registroHoras);

                new DetalleHorasEmpleadoDAO().registrarDetalleHorasEmpleadoCompleto(detalleHoras);
                limpiarRegistros();
                FacesMessage mensajeSalida = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Registro Realizado");
                RequestContext.getCurrentInstance().showMessageInDialog(mensajeSalida);

            }
        } catch (Exception ex) {
            System.out.println("Error en HorasEmpleadoBEAN -> registrarHorasPorAdminstrador " + ex);
        }
    }
    
    public static String getRemoteAddress(HttpServletRequest req) {
    String ipAddress = req.getHeader("X-FORWARDED-FOR");
    if (ipAddress != null) {
        ipAddress = ipAddress.replaceFirst(",.*", "");  // cares only about the first IP if there is a list
    } else {
        ipAddress = req.getRemoteAddr();
    }
    return ipAddress;
}
    public void limpiarRegistros() {
        horas.setFecha(null);
        horas.setId_usuario_empleado(null);
        detalleHoras.setHoraEntrada("");
        detalleHoras.setHoraSalida("");
        detalleHoras.setIdHorasEmpleado(null);
    }

}
