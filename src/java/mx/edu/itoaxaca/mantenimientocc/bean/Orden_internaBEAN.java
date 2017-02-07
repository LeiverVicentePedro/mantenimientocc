/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import mx.edu.itoaxaca.mantenimientocc.dao.DetalleSeguimientoDAO;

import mx.edu.itoaxaca.mantenimientocc.dao.Orden_internaDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.Refaccion_empleadaDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.Relacion_orden_equipoDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.Relacion_orden_refaccionDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.SeguimientoDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.DetalleSeguimiento;
import mx.edu.itoaxaca.mantenimientocc.modelo.Equipo;
import mx.edu.itoaxaca.mantenimientocc.modelo.Orden_interna;
import mx.edu.itoaxaca.mantenimientocc.modelo.Refaccion_empleada;
import mx.edu.itoaxaca.mantenimientocc.modelo.Relacion_orden_equipo;
import mx.edu.itoaxaca.mantenimientocc.modelo.Relacion_orden_refaccion;
import mx.edu.itoaxaca.mantenimientocc.modelo.Seguimiento;
import mx.edu.itoaxaca.mantenimientocc.modelo.Solicitud_mc;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultUploadedFile;
import org.primefaces.model.UploadedFile;


/**
 *
 * @author Jerusalen
 */
@ManagedBean
@ViewScoped
public class Orden_internaBEAN implements Serializable{
    
    private Orden_interna orden_interna = new Orden_interna();
    private Refaccion_empleada refaccion = new Refaccion_empleada();
    
    private List<Equipo> listaEquipo = new ArrayList();
    private List<Refaccion_empleada> listaRefaccion= new ArrayList();
    private List<Orden_interna> listaOrden_interna;
    private List<Orden_interna> listaOrden_internaUsuario;
    private List<Orden_interna> filterOrden;
    private List<Orden_interna> selecEquipoRefaccion;
    private List<Orden_interna> seleccionOrdenInterna;
    Usuario usuarioVive;
    
     Solicitud_mc folioDesdeAsignacion;

    public List<Orden_interna> getSeleccionOrdenInterna() {
        return seleccionOrdenInterna;
    }

    public void setSeleccionOrdenInterna(List<Orden_interna> seleccionOrdenInterna) {
        this.seleccionOrdenInterna = seleccionOrdenInterna;
    }

     
    public List<Orden_interna> getListaOrden_internaUsuario() {
        return listaOrden_internaUsuario;
    }

    public void setListaOrden_internaUsuario(List<Orden_interna> listaOrden_internaUsuario) {
        this.listaOrden_internaUsuario = listaOrden_internaUsuario;
    }
   
    public Usuario getUsuarioVive() {
        return usuarioVive;
    }

    public void setUsuarioVive(Usuario usuarioVive) {
        this.usuarioVive = usuarioVive;
    }
    
    public Solicitud_mc getFolioDesdeAsignacion() {
        return folioDesdeAsignacion;
    }

    public void setFolioDesdeAsignacion(Solicitud_mc solicitud_mc) {
       this.folioDesdeAsignacion = solicitud_mc;
    }
     public void existeSolicitud(){
        folioDesdeAsignacion = (Solicitud_mc) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("solicitudAsignadaOR");
    }
     
    
    public List<Orden_interna> getSelecEquipoRefaccion() {
        return selecEquipoRefaccion;
    }

    public void setSelecEquipoRefaccion(List<Orden_interna> selecEquipoRefaccion) {
        this.selecEquipoRefaccion = selecEquipoRefaccion;
    }
    
    

    public List<Orden_interna> getFilterOrden() {
        return filterOrden;
    }

    public void setFilterOrden(List<Orden_interna> filterOrden) {
        this.filterOrden = filterOrden;
    }
    
    
    
    public Orden_interna getOrden_interna() {
        return orden_interna;
    }

    public void setOrden_interna(Orden_interna orden_interna) {
        this.orden_interna = orden_interna;
    }

    public List<Orden_interna> getListaOrden_interna() {
        return listaOrden_interna;
    }

    public void setListaOrden_interna(List<Orden_interna> listaOrden_interna) {
        this.listaOrden_interna = listaOrden_interna;
    }

    public List<Equipo> getListaEquipo() {
        return listaEquipo;
    }

    public void setListaEquipo(List<Equipo> listaEquipo) {
        this.listaEquipo = listaEquipo;
    }

    public List<Refaccion_empleada> getListaRefaccion() {
        return listaRefaccion;
    }

    public void setListaRefaccion(List<Refaccion_empleada> listaRefaccion) {
        this.listaRefaccion = listaRefaccion;
    }

    public Refaccion_empleada getRefaccion() {
        return refaccion;
    }

    public void setRefaccion(Refaccion_empleada refaccion) {
        this.refaccion = refaccion;
    }
    
    
    
    ////-----REGISTRAR----
    public void registrarOrden() throws Exception {
        Orden_internaDAO ordenInternaDao;
        
        
        Relacion_orden_equipoDAO relacion_orden_equipoDAO;
        Relacion_orden_refaccionDAO relacion_orden_refaccionDAO;
        try {
            FacesContext contexto = FacesContext.getCurrentInstance(); //paraq entrar ql dom del navegador
            Usuario usuarioVive = (Usuario) contexto.getExternalContext().getSessionMap().get("usuario");//llamo a  la etiqueta usuario que es un objeto que ya debe
            //existir dentro del navegador
            
            ///
            ordenInternaDao = new Orden_internaDAO();
            relacion_orden_equipoDAO = new Relacion_orden_equipoDAO();
            relacion_orden_refaccionDAO =new Relacion_orden_refaccionDAO();
            
           
            
            orden_interna.setId_usuario_personal(usuarioVive);
            orden_interna.setFecha(new java.sql.Date(new java.util.Date().getTime()));//fecha sistema
            orden_interna.setIdsolicitud(folioDesdeAsignacion);
            ordenInternaDao.registrarOrdenInterna(orden_interna);
            
            System.out.println("Lista de Equipo "+listaEquipo.size());
            
            Orden_interna ordenTemporal = ordenInternaDao.identificadorDeOrden(orden_interna.getIdsolicitud());
            
            for (int i = 0; i < listaEquipo.size(); i++) {
                Relacion_orden_equipo detalleOrdenEquipo = new Relacion_orden_equipo();

                detalleOrdenEquipo.setIdOrden_interna(ordenTemporal);
                detalleOrdenEquipo.setIdEquipo(listaEquipo.get(i));
                relacion_orden_equipoDAO.registrarDetalleOrdenEquipo(detalleOrdenEquipo);

            }
             for (int i = 0; i < listaRefaccion.size(); i++) {
               Relacion_orden_refaccion detalleOrdenRefaccion = new Relacion_orden_refaccion();

               detalleOrdenRefaccion.setIdOrdenRefaccion(ordenTemporal);
                detalleOrdenRefaccion.setIdRefaccion(listaRefaccion.get(i));
                relacion_orden_refaccionDAO.registrarDetalleOrdenRefaccion(detalleOrdenRefaccion);

            }
                System.out.println("Lista de Refaccion "+listaRefaccion.size());                      
           /* for (int i = 0; i < listaRefaccion.size(); i++) {
                Relacion_orden_refaccion detalleOrdenRefaccion = new Relacion_orden_refaccion();
                new Refaccion_empleadaDAO().registrarRefaccion(listaRefaccion.get(i));
                listaRefaccion.set(i,new Refaccion_empleadaDAO().BuscaRefaccionEmpledaPorObjeto(listaRefaccion.get(i)));
                detalleOrdenRefaccion.setIdOrdenRefaccion(ordenTemporal);
                detalleOrdenRefaccion.setIdRefaccion(listaRefaccion.get(i));
                relacion_orden_refaccionDAO.registrarDetalleOrdenRefaccion(detalleOrdenRefaccion);
               
            }*/
            
            System.out.println("fecha del sistema " + orden_interna.getFecha());
            Seguimiento segimiento = new SeguimientoDAO().elegirDatoSeguimiento(folioDesdeAsignacion);
            
            /*subir evidencia a la base de datos de que falta herramientas
            *creando los objetos nesesarios*/
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\resources");
            File imagen = new File(path+"\\"+"images\\"+"aviso.png");
            System.out.println("ruta imagen:-> "+path+"\\"+"images\\"+"aviso.png");
            FileInputStream parseoImagen = new FileInputStream(imagen);
            if(!orden_interna.getRefaccion_faltante().isEmpty()){
            DetalleSeguimiento detalleseguimiento = new DetalleSeguimiento();
            detalleseguimiento.setId_seguimiento(segimiento);
            detalleseguimiento.setEstado("proceso");
            detalleseguimiento.setFecha(new java.sql.Date(new java.util.Date().getTime()));
            detalleseguimiento.setDescripcion(orden_interna.getRefaccion_faltante());
          
            new DetalleSeguimientoDAO().registrarDetalleSeguimientoEnOrdenInterna(detalleseguimiento,parseoImagen);
            }
           
            exportarPDFOrdenInterna(usuarioVive);
             this.limpiarOrdenInterna();

        } catch (Exception ex) {
            System.out.println("Error en Orden-BEAN -> generarOrden " + ex);
            throw ex;
        }
    }
    
    //---------para checkBox TABLA
    public void check(SelectEvent event) {
            System.out.println("in check-Equipo");
        }
   
    
    
    //-----
    
//Metodo ´para listar 
    
    public void listarOrden_interna() throws Exception{
       Orden_internaDAO orden_internadao;
        try{
            orden_internadao=new Orden_internaDAO();
            listaOrden_interna = orden_internadao.listarOrden_interna();
        }
        catch(Exception e){
            System.out.println("error en orden_internaBEAN --> listarordenInternaBEAN"+e);
        }
    }
     public void listarOrdenInternaPorUsuario(){
        Orden_internaDAO ordenInterna;
        
        try{
            ordenInterna = new Orden_internaDAO();
           // Orden_internaDAO ordenInterna = new Orden_internaDAO();
            FacesContext contexto = FacesContext.getCurrentInstance(); //paraq entrar ql dom del navegador
            usuarioVive = (Usuario) contexto.getExternalContext().getSessionMap().get("usuario");//llamo a  la etiqueta usuario que es un objeto que ya debe
            listaOrden_internaUsuario =ordenInterna.listarOrdenInternaPorDepartamentoUsuario(usuarioVive);
           
                
            
        }catch(Exception ex){
            System.out.println("Error en Solicitud_mc -> listarSolicitudPorDepartamento "+ex);
        }
    }
    
    //--------------------------------------------------------------------------------------
    //----------------metodo que nos genera el pdf para la Orden Interna--------------------
    public void exportarPDFOrdenInterna(Usuario usuarioActivo) throws JRException, IOException{
        Map<String, Object> parametros = new HashMap<String, Object>();
        
        String nombreUsuario = orden_interna.getIdsolicitud().getId_usuario().getId_profesion().getNombre_profesion()+". "+
                orden_interna.getIdsolicitud().getId_usuario().getNombre()+" "+
                orden_interna.getIdsolicitud().getId_usuario().getApellidoPaterno()+" "+
                orden_interna.getIdsolicitud().getId_usuario().getApellidoMaterno();
        
        String fecha = String.valueOf(orden_interna.getFecha());
        String fechaOrden = String.valueOf(orden_interna.getIdsolicitud().getFecha());
        
        String tipo = "";
        String marca = "";
        String modelo = "";
        String numeroSerie = "";
        String folioInventario = "";
        
        String cantidad = "";
        String numeroPartes = "";
        String descripcion = "";
        String precio = "";
        
        parametros.put("folio", orden_interna.getIdsolicitud().getFolio().toUpperCase());
        if(orden_interna.getNombre_orden().equalsIgnoreCase("orden de mantenimiento")){
            parametros.put("cordinacion","COORDINACIÓN DE MANTENIMIENTO DE EQUIPO DE CÓMPUTO");
        }else{
            parametros.put("cordinacion","COORDINACIÓN DE TELECOMUNICACIONES");
        }
         
        parametros.put("orden", orden_interna.getNombre_orden().toUpperCase());
        parametros.put("nombreUsiario",nombreUsuario.toUpperCase());
        parametros.put("area",orden_interna.getIdsolicitud().getId_usuario().getIdOficina().getDepartamento().getNombre_departamento().toUpperCase());
        parametros.put("departamento",orden_interna.getIdsolicitud().getId_usuario().getIdOficina().getNombreOficina().toUpperCase());
        parametros.put("extension",String.valueOf(orden_interna.getIdsolicitud().getId_usuario().getIdOficina().getExtencion()));
        parametros.put("fecha",fechaOrden);
        
        
        for(Equipo elementoEquipo : listaEquipo){
          tipo += elementoEquipo.getTipo()+"\n";
          marca+= elementoEquipo.getMarca()+"\n";
          modelo += elementoEquipo.getModelo()+"\n";
          numeroSerie += elementoEquipo.getNumero_serie()+"\n";
          folioInventario += elementoEquipo.getFolio_inventario()+"\n";
        }
        
        parametros.put("tipo", tipo.toUpperCase());
        parametros.put("marca", marca.toUpperCase());
        parametros.put("modelo", modelo.toUpperCase());
        parametros.put("numSerie", numeroSerie.toUpperCase());
        parametros.put("folioInventario",folioInventario);
        parametros.put("reporteFalla",orden_interna.getReporte_fallo().toUpperCase());
        parametros.put("reporteTecnico",orden_interna.getReporte_tecnico().toUpperCase());
        parametros.put("causaFalla", orden_interna.getPosible_causa().toUpperCase());
        parametros.put("realizo",usuarioActivo.getNombre().toUpperCase()+" "+usuarioActivo.getApellidoPaterno().toUpperCase()+" "+usuarioActivo.getApellidoMaterno().toUpperCase());
        parametros.put("fechaRealizo",fecha);
        parametros.put("seRecibe",orden_interna.getSe_recibe().toUpperCase());
        
        for(Refaccion_empleada refaccion : listaRefaccion){
         cantidad += refaccion.getCantidad()+"\n";
         numeroPartes += refaccion.getNumero_serie()+"\n";
         descripcion += refaccion.getDescripcion()+"\n";
         precio += refaccion.getPrecio()+"\n";
        }
        
        parametros.put("cantidad", cantidad);
        parametros.put("numParte", numeroPartes.toUpperCase());
        parametros.put("descripcion", descripcion.toUpperCase());
        parametros.put("precio", precio);
        parametros.put("refaccion_faltante",orden_interna.getRefaccion_faltante());
        File archivo = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/FormatoOrdenInterna.jasper"));
        JasperPrint imprimirArchivo = JasperFillManager.fillReport(archivo.getPath(), parametros, new JREmptyDataSource());

        HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        respuesta.addHeader("Content-Disposition", "attachment; filename=\"Orden_interna.pdf\";");
        ServletOutputStream stream = respuesta.getOutputStream();

        JasperExportManager.exportReportToPdfStream(imprimirArchivo, stream);

        FacesContext.getCurrentInstance().responseComplete();
    
}
    
    public void limpiarOrdenInterna(){
        this.orden_interna.setIdsolicitud(null);
        this.orden_interna.setNombre_orden("ORDEN DE MANTENIMIENTO");
        this.orden_interna.setReporte_fallo("");
        this.orden_interna.setReporte_tecnico("");
        this.orden_interna.setPosible_causa("");
        this.orden_interna.setSe_recibe("");
    }
    
    /*Metodo que llena la lista para las refacciones empleadas*/
   /* public void agregaRefaccion(){
        System.out.println(refaccion.getDescripcion()+"objeto");
        
        listaRefaccion.add(new Refaccion_empleada(refaccion.getDescripcion(),refaccion.getNumero_serie(),refaccion.getPrecio(),refaccion.getCantidad()));
        refaccion.setCantidad(0);
        refaccion.setNumero_serie(null);
        refaccion.setDescripcion(null);
        refaccion.setPrecio(0.0);
        
    }*/
    
    public void limpiaListaRefaccion(){
        listaRefaccion.clear();
    }
}
