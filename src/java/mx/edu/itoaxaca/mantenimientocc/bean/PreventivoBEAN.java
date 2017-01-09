/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import mx.edu.itoaxaca.mantenimientocc.dao.PreventivoDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.DetallePreventivo;
import mx.edu.itoaxaca.mantenimientocc.modelo.Preventivo;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;
import java.util.List;
import mx.edu.itoaxaca.mantenimientocc.dao.DetallePreventivoDAO;
import mx.edu.itoaxaca.reportes.ReportePreventivo;
/**
 *
 * @author leiver
 */
@ManagedBean
@ViewScoped
public class PreventivoBEAN implements Serializable{
    Preventivo preventivo = new Preventivo();
    DetallePreventivo detalle1 = new DetallePreventivo();
    DetallePreventivo detalle2 = new DetallePreventivo();
    DetallePreventivo detalle3 = new DetallePreventivo();
    DetallePreventivo detalle4 = new DetallePreventivo();
    DetallePreventivo detalle5 = new DetallePreventivo();
    DetallePreventivo detalle6 = new DetallePreventivo();
    
    private List<Preventivo> listaPreventivo;
    private List<DetallePreventivo> listaDetalle;
    private List<DetallePreventivo> listaParaReporteDetallePreventivo;//lista que se utilizara en el reporte
    private Date fecha1;
    private Date fecha2;
    private Date fecha3;
    private Date fecha4;
    private Date fecha5;
    private Date fecha6;
    
    public Date getFecha1() {
        return fecha1;
    }

    public void setFecha1(Date fecha1) {
        this.fecha1 = fecha1;
    }

    public Date getFecha2() {
        return fecha2;
    }

    public void setFecha2(Date fecha2) {
        this.fecha2 = fecha2;
    }

    public Date getFecha3() {
        return fecha3;
    }

    public void setFecha3(Date fecha3) {
        this.fecha3 = fecha3;
    }

    public Date getFecha4() {
        return fecha4;
    }

    public void setFecha4(Date fecha4) {
        this.fecha4 = fecha4;
    }

    public Date getFecha5() {
        return fecha5;
    }

    public void setFecha5(Date fecha5) {
        this.fecha5 = fecha5;
    }

    public Date getFecha6() {
        return fecha6;
    }

    public void setFecha6(Date fecha6) {
        this.fecha6 = fecha6;
    }
    
    public Preventivo getPreventivo() {
        return preventivo;
    }

    public void setPreventivo(Preventivo preventivo) {
        this.preventivo = preventivo;
    }

    public DetallePreventivo getDetalle1() {
        return detalle1;
    }

    public void setDetalle1(DetallePreventivo detalle1) {
        this.detalle1 = detalle1;
    }

    public DetallePreventivo getDetalle2() {
        return detalle2;
    }

    public void setDetalle2(DetallePreventivo detalle2) {
        this.detalle2 = detalle2;
    }

    public DetallePreventivo getDetalle3() {
        return detalle3;
    }

    public void setDetalle3(DetallePreventivo detalle3) {
        this.detalle3 = detalle3;
    }

    public DetallePreventivo getDetalle4() {
        return detalle4;
    }

    public void setDetalle4(DetallePreventivo detalle4) {
        this.detalle4 = detalle4;
    }

    public DetallePreventivo getDetalle5() {
        return detalle5;
    }

    public void setDetalle5(DetallePreventivo detalle5) {
        this.detalle5 = detalle5;
    }

    public DetallePreventivo getDetalle6() {
        return detalle6;
    }

    public void setDetalle6(DetallePreventivo detalle6) {
        this.detalle6 = detalle6;
    }

    public List<Preventivo> getListaPreventivo() {
        return listaPreventivo;
    }

    public void setListaPreventivo(List<Preventivo> listaPreventivo) {
        this.listaPreventivo = listaPreventivo;
    }

    public List<DetallePreventivo> getListaDetalle() {
        return listaDetalle;
    }

    public void setListaDetalle(List<DetallePreventivo> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }
    
    public void registrarPreventivo(){
        PreventivoDAO preventivoDao = new PreventivoDAO();
        Usuario usuarioVive = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        try{
            listaParaReporteDetallePreventivo = new ArrayList();//se crea lista para detalles que se nesesita en el reporte
            
            preventivo.setAño(String.valueOf(new java.util.Date().getYear()+1900));
            preventivo.setId_usuario_personal(usuarioVive);
            preventivo.setFolio("mp-"+(new java.util.Date().getYear()+1900)+"/"+(preventivoDao.ultimoIdInsertado()+1));
            preventivo.setFecha_elaboracion(new java.sql.Date(preventivo.getFecha_elaboracion().getTime()));
            System.out.println("valores "+preventivo);
            preventivoDao.registrarPreventivo(preventivo);
            
            preventivo = new PreventivoDAO().buscarPreventivo(preventivo);//busca al objeto de preventivo y lo asigna al mismo objeto
            
            if(!detalle1.getServicio().isEmpty()){
                detalle1.setNumero_servicio(1);
                detalle1.setFecha(fecha1);
                detalle1.setId_preventivo(preventivo);
                new DetallePreventivoBEAN().registrarDetallePreventivo(detalle1);
                listaParaReporteDetallePreventivo.add(detalle1);
            }
             if(!detalle2.getServicio().isEmpty()){
                 detalle2.setNumero_servicio(2);
                detalle2.setFecha(fecha2);
                detalle2.setId_preventivo(preventivo);
                new DetallePreventivoBEAN().registrarDetallePreventivo(detalle2);
                listaParaReporteDetallePreventivo.add(detalle2);
            }
             if(!detalle3.getServicio().isEmpty()){
                 detalle3.setNumero_servicio(3);
                detalle3.setFecha(fecha3);
                detalle3.setId_preventivo(preventivo);
                new DetallePreventivoBEAN().registrarDetallePreventivo(detalle3);
                listaParaReporteDetallePreventivo.add(detalle3);
            }
             if(!detalle4.getServicio().isEmpty()){
                 detalle4.setNumero_servicio(4);
                detalle4.setFecha(fecha4);
                detalle4.setId_preventivo(preventivo);
                new DetallePreventivoBEAN().registrarDetallePreventivo(detalle4);
                listaParaReporteDetallePreventivo.add(detalle4);
            }
             if(!detalle5.getServicio().isEmpty()){
                 detalle5.setNumero_servicio(5);
                detalle5.setFecha(fecha5);
                detalle5.setId_preventivo(preventivo);
                new DetallePreventivoBEAN().registrarDetallePreventivo(detalle5);
                listaParaReporteDetallePreventivo.add(detalle5);
            }
             if(!detalle6.getServicio().isEmpty()){
                 detalle6.setNumero_servicio(6);
                detalle6.setFecha(fecha6);
                detalle6.setId_preventivo(preventivo);
                new DetallePreventivoBEAN().registrarDetallePreventivo(detalle6);
                listaParaReporteDetallePreventivo.add(detalle6);
            }
            new ReportePreventivo().crearReportePreventivo(preventivo, listaParaReporteDetallePreventivo);//se llama al metodo que creara el reporte.
            
        }catch(Exception ex){
            System.out.println("Error en PreventivoBEAN -> registrarPreventivo "+ ex);
        }
    }
    
    public void listarPreventivo() throws Exception{
        try{
            PreventivoDAO preventivoDao = new PreventivoDAO();
            listaPreventivo = new ArrayList();
            Usuario usuario  = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            listaPreventivo = preventivoDao.listarPreventivo(usuario);
            
        }catch(Exception ex){
            System.out.println("Error en PreventivoBEAN -> listarPReventivo "+ex);
            throw ex;
        }
    }
    
    
    public void listarDetallePreventivo(){
        listaDetalle = new ArrayList();
        try{
        listaDetalle = new DetallePreventivoDAO().listarDetallePreventivo(preventivo);    
        }catch(Exception ex){
            System.out.println("Error en PreventivoBEAN -> listarDetallePreventivo "+ex);
        }
        
    }
}
