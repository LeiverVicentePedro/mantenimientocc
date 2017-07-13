/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import mx.edu.itoaxaca.mantenimientocc.dao.DetalleSeguimientoDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.SeguimientoDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.Solicitud_mcDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.DetalleSeguimiento;
import mx.edu.itoaxaca.mantenimientocc.modelo.Seguimiento;
import mx.edu.itoaxaca.mantenimientocc.modelo.Solicitud_mc;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;

/**
 *
 * @author leiver
 */
@ManagedBean
@SessionScoped
public class SegiomientoMantenimientoUsuarioBEAN implements Serializable {

 
    private Seguimiento seguimientoEncontrado = new Seguimiento();
    private String noExisteSolicitud;
    private String vista;
    private List<Solicitud_mc> listaSolicitudActivas = new ArrayList();
    private List<DetalleSeguimiento> listaDetalle ;
    private List<DetalleSeguimiento> listaDetalleProceso;
    private List<DetalleSeguimiento> listaDetalleFinal;
    private List<Solicitud_mc> listaSolicitudActivasfilter;

    public List<Solicitud_mc> getListaSolicitudActivasfilter() {
        return listaSolicitudActivasfilter;
    }

    public void setListaSolicitudActivasfilter(List<Solicitud_mc> listaSolicitudActivasfilter) {
        this.listaSolicitudActivasfilter = listaSolicitudActivasfilter;
    }

    public List<Solicitud_mc> getListaSolicitudActivas() {
        return listaSolicitudActivas;
    }

    public void setListaSolicitudActivas(List<Solicitud_mc> listaSolicitudActivas) {
        this.listaSolicitudActivas = listaSolicitudActivas;
    }

    public List<DetalleSeguimiento> getListaDetalle() {
        return listaDetalle;
    }

    public void setListaDetalle(List<DetalleSeguimiento> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }

    public List<DetalleSeguimiento> getListaDetalleProceso() {
        return listaDetalleProceso;
    }

    public void setListaDetalleProceso(List<DetalleSeguimiento> listaDetalleProceso) {
        this.listaDetalleProceso = listaDetalleProceso;
    }

    public List<DetalleSeguimiento> getListaDetalleFinal() {
        return listaDetalleFinal;
    }

    public void setListaDetalleFinal(List<DetalleSeguimiento> listaDetalleFinal) {
        this.listaDetalleFinal = listaDetalleFinal;
    }

    public String getVista() {
        return vista;
    }

    public void setVista(String vista) {
        this.vista = vista;
    }

    
    public Seguimiento getSeguimientoEncontrado() {
        return seguimientoEncontrado;
    }

    public void setSeguimientoEncontrado(Seguimiento seguimientoEncontrado) {
        this.seguimientoEncontrado = seguimientoEncontrado;
    }

    public String getNoExisteSolicitud() {
        return noExisteSolicitud;
    }

    public void setNoExisteSolicitud(String noExisteSolicitud) {
        this.noExisteSolicitud = noExisteSolicitud;
    }

    public String redirige() {//para redirigir
        return vista;
    }

    /*metodo que busca la lista de solicitudes realizad por un usuario*/
    public void listarSolicitudesSeguimientoactivasPorUsuario() {
        try {
            Usuario existeUsuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            listaSolicitudActivas = new Solicitud_mcDAO().listarSolicitudPorDepartamentoUsuarioEnSeguimiento(existeUsuario);
        } catch (Exception ex) {
            System.out.println("Error en SeguimientoMantenimientoUsuario -> listarSolicitudesSeguimiento " + ex);
        }
    }
    /*Metodo que busca y llena el objeto de Seguimiento si este tiene algo o no*///para nivel UNO
    public void redirigeVistaSiExisteSolicitud(Solicitud_mc buscarSolicitud) throws Exception {
        try {
            // solicitudABuscar = new Solicitud_mcDAO().identificadorDeSolicitud(solicitudABuscar.getFolio());
            if (new SeguimientoDAO().elegirDatoSeguimiento(buscarSolicitud) != null) {
                seguimientoEncontrado = new SeguimientoDAO().elegirDatoSeguimiento(buscarSolicitud);
                setNoExisteSolicitud("ACTIVO");
                if (buscarSolicitud.getEstado_seguimiento() == false) {
                    setNoExisteSolicitud("TERMINADO");
                }
                alNavegadorSiExiste();
            }
            if (new SeguimientoDAO().elegirDatoSeguimiento(buscarSolicitud) == null) {
                setNoExisteSolicitud("NO EXISTE");
                System.out.println("seguimiento " + seguimientoEncontrado.getIdseguimiento());
                System.out.println(noExisteSolicitud);
                alNavegadorNoExiste();
            }

        } catch (Exception ex) {
            System.out.println("Error en SeguimientoMantenimientoUsuarioBEAN -> redirigeVistaSiExisteSolicitud " + ex);
            throw ex;
        }
    }
    
   
   //para nivel 2 y 3
    public void alNavegadorSiExiste() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("existe", noExisteSolicitud);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("seguimiento", seguimientoEncontrado);
    }

    public void alNavegadorNoExiste() {
        Solicitud_mc mc = new Solicitud_mc();
        mc.setFolio("######-##");
        seguimientoEncontrado.setId_solicitud(mc);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("existe", noExisteSolicitud);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("seguimiento", seguimientoEncontrado);
    }

    public void recuperaNavegador() throws Exception {
        try {
            seguimientoEncontrado = (Seguimiento) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("seguimiento");
            noExisteSolicitud = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("existe");

        } catch (Exception ex) {
            System.out.println("Error en SEguimientoMantenimientoUsuarioBEAN -> recuperaNavegador " + ex);
        }
    }

    public void setListaDetalleImagenes() throws IOException, Exception {
        List<DetalleSeguimiento> listaSD = new ArrayList();
        listaSD = getListaDetalleImagenes();
        listaDetalle = new ArrayList();
        listaDetalleProceso = new ArrayList();
        listaDetalleFinal = new ArrayList();
        for (DetalleSeguimiento seguimiento : listaSD) {
            if (seguimiento.getEstado().equalsIgnoreCase("Inicial")) {
                listaDetalle.add(seguimiento);
            }
            if (seguimiento.getEstado().equalsIgnoreCase("Proceso")) {
                listaDetalleProceso.add(seguimiento);
            }
            if (seguimiento.getEstado().equalsIgnoreCase("Final")) {
                listaDetalleFinal.add(seguimiento);
            }
        }
    }

    public List<DetalleSeguimiento> getListaDetalleImagenes() throws SQLException, IOException, Exception {
        DetalleSeguimientoDAO detalleDAO = new DetalleSeguimientoDAO();
        List<DetalleSeguimiento> listaDetalle = detalleDAO.listardetalle(seguimientoEncontrado);
        //List<String> imagen= new ArrayList<String>();
       
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources");
        //String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        System.out.println("ruta " + path);

        for (int contador = 0; contador < listaDetalle.size(); contador++) {
            FileOutputStream salida = new FileOutputStream(path+"/"+listaDetalle.get(contador).getEstado()+contador+".jpg");
            //FileOutputStream salida = new FileOutputStream(path + "" + listaDetalle.get(contador).getEstado() + contador + ".jpg");
            System.out.println("con Imagen " + path + "/" + listaDetalle.get(contador).getEstado() + contador + ".jpg");
            salida.write(listaDetalle.get(contador).getImagenDowload());
            salida.close();
            listaDetalle.get(contador).setRuta(listaDetalle.get(contador).getEstado() + contador + ".jpg");

        }
        return listaDetalle;
    }

    static public String getRealPath(ServletContext servletContext, String resourcePath) {
        String result = "";
        result = servletContext.getRealPath(resourcePath);
        if (result == null) {
// resources en un .war (JBoss, WebLogic)
            java.net.URL url;
            try {
                url = servletContext.getResource(resourcePath);
                result = url.getPath();
            } catch (MalformedURLException e) {
                System.out.println("No se ha podido recuperar el path real de: " + resourcePath);
            }
        }
        return result;
    }

}
