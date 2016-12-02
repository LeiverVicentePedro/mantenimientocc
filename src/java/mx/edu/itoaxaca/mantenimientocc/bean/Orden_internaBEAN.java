/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import mx.edu.itoaxaca.mantenimientocc.dao.Orden_internaDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.Relacion_orden_equipoDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.Relacion_orden_refaccionDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Equipo;
import mx.edu.itoaxaca.mantenimientocc.modelo.Orden_interna;
import mx.edu.itoaxaca.mantenimientocc.modelo.Refaccion_empleada;
import mx.edu.itoaxaca.mantenimientocc.modelo.Relacion_orden_equipo;
import mx.edu.itoaxaca.mantenimientocc.modelo.Relacion_orden_refaccion;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.primefaces.event.SelectEvent;


/**
 *
 * @author Jerusalen
 */
@ManagedBean
@ViewScoped
public class Orden_internaBEAN implements Serializable{
    
    private Orden_interna orden_interna=new Orden_interna();
    
    private List<Equipo> listaEquipo;
    private List<Refaccion_empleada> listaRefaccion;
    private List<Orden_interna> listaOrden_interna;
    
    
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
    
    
    
    
    ////-----REGISTRAR----
    public void registrarOrden() throws Exception {
        Orden_internaDAO ordenInternaDao;
        
        Relacion_orden_equipoDAO relacion_orden_equipoDAO;
        Relacion_orden_refaccionDAO relacion_orden_refaccionDAO;
        try {
            FacesContext contexto = FacesContext.getCurrentInstance(); //paraq entrar ql dom del navegador
            Usuario usuarioVive = (Usuario) contexto.getExternalContext().getSessionMap().get("usuario");//llamo a  la etiqueta usuario que es un objeto que ya debe
            //existir dentro del navegador
            ordenInternaDao = new Orden_internaDAO();
            relacion_orden_equipoDAO = new Relacion_orden_equipoDAO();
            relacion_orden_refaccionDAO =new Relacion_orden_refaccionDAO();
            
           
            
            orden_interna.setId_usuario_personal(usuarioVive);
            orden_interna.setFecha(new java.sql.Date(new java.util.Date().getTime()));//fecha sistema
            ordenInternaDao.registrarOrdenInterna(orden_interna);
            
            System.out.println("Lista de Equipo "+listaEquipo.size());
            
            Orden_interna ordenTemporal = ordenInternaDao.identificadorDeOrden(orden_interna.getIdsolicitud());
            
            for (int i = 0; i < listaEquipo.size(); i++) {
                Relacion_orden_equipo detalleOrdenEquipo = new Relacion_orden_equipo();

                detalleOrdenEquipo.setIdOrden_interna(ordenTemporal);
                detalleOrdenEquipo.setIdEquipo(listaEquipo.get(i));
                relacion_orden_equipoDAO.registrarDetalleOrdenEquipo(detalleOrdenEquipo);

            }
            
            
                System.out.println("Lista de Refaccion "+listaRefaccion.size());                      
            for (int i = 0; i < listaRefaccion.size(); i++) {
                Relacion_orden_refaccion detalleOrdenRefaccion = new Relacion_orden_refaccion();

                detalleOrdenRefaccion.setIdOrdenRefaccion(ordenTemporal);
                detalleOrdenRefaccion.setIdRefaccion(listaRefaccion.get(i));
                relacion_orden_refaccionDAO.registrarDetalleOrdenRefaccion(detalleOrdenRefaccion);

            }
            
            System.out.println("fecha del sistema " + orden_interna.getFecha());
            exportarPDFOrdenInterna(usuarioVive);

        } catch (Exception ex) {
            System.out.println("Error en Orden-BEAN -> generarOrden " + ex);
            throw ex;
        }
    }
    
    //---------para checkBox TABLA
    public void check(SelectEvent event) {
            System.out.println("in check-Equipo");
        }
    public void checkDos(SelectEvent event) {
            System.out.println("in check-Refaccion");
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
        
        parametros.put("folio", orden_interna.getIdsolicitud().getFolio());
        if(orden_interna.getNombre_orden().equalsIgnoreCase("orden de mantenimiento")){
            parametros.put("cordinacion","COORDINACIÓN DE MANTENIMIENTO DE EQUIPO DE CÓMPUTO");
        }else{
            parametros.put("cordinacion","COORDINACIÓN DE TELECOMUNICACIONES");
        }
        parametros.put("orden", orden_interna.getNombre_orden());
        parametros.put("nombreUsiario",nombreUsuario);
        parametros.put("area",orden_interna.getIdsolicitud().getId_usuario().getIdOficina().getDepartamento().getNombre_departamento());
        parametros.put("departamento",orden_interna.getIdsolicitud().getId_usuario().getIdOficina().getNombreOficina());
        parametros.put("extencion",orden_interna.getIdsolicitud().getId_usuario().getIdOficina().getExtencion());
        parametros.put("fecha",fechaOrden);
        
        for(Equipo elementoEquipo : listaEquipo){
          tipo += elementoEquipo.getTipo()+"\n";
          marca+= elementoEquipo.getMarca()+"\n";
          modelo += elementoEquipo.getModelo()+"\n";
          numeroSerie += elementoEquipo.getNumero_serie()+"\n";
          folioInventario += elementoEquipo.getFolio_inventario()+"\n";
        }
        
        parametros.put("tipo", tipo);
        parametros.put("marca", marca);
        parametros.put("modelo", modelo);
        parametros.put("numSerie", numeroSerie);
        parametros.put("folioInventario",folioInventario);
        parametros.put("reporteFalla",orden_interna.getReporte_fallo());
        parametros.put("reporteTecnico",orden_interna.getReporte_tecnico());
        parametros.put("causaFalla", orden_interna.getPosible_causa());
        parametros.put("realizo",usuarioActivo.getNombre()+" "+usuarioActivo.getApellidoPaterno()+" "+usuarioActivo.getApellidoMaterno());
        parametros.put("fechaRealizo",fecha);
        
        for(Refaccion_empleada refaccion : listaRefaccion){
         cantidad += refaccion.getCantidad()+"\n";
         numeroPartes += refaccion.getNumero_serie()+"\n";
         descripcion += refaccion.getDescripcion()+"\n";
         precio += refaccion.getPrecio()+"\n";
        }
        
        parametros.put("cantidad", cantidad);
        parametros.put("numParte", numeroPartes);
        parametros.put("descripcion", descripcion);
        parametros.put("precio", precio);
        
        File archivo = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/FormatoOrdenInterna.jasper"));
        JasperPrint imprimirArchivo = JasperFillManager.fillReport(archivo.getPath(), parametros, new JREmptyDataSource());

        HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        respuesta.addHeader("Content-Disposition", "attachment; filename=\"Orden_interna.pdf\";");
        ServletOutputStream stream = respuesta.getOutputStream();

        JasperExportManager.exportReportToPdfStream(imprimirArchivo, stream);

        FacesContext.getCurrentInstance().responseComplete();
    
}
}