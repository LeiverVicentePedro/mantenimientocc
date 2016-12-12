/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import mx.edu.itoaxaca.mantenimientocc.dao.PreventivoDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.DetallePreventivo;
import mx.edu.itoaxaca.mantenimientocc.modelo.Preventivo;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;

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
    
    private Date fecha1;
    private Date fecha2;
    private Date fecha3;
    private Date fecha4;
    private Date fecha5;
    private Date fecha6;
    
    private String tipoServicio1;
    private String tipoServicio2;
    private String tipoServicio3;
    private String tipoServicio4;
    private String tipoServicio5;
    private String tipoServicio6;

    public String getTipoServicio1() {
        return tipoServicio1;
    }

    public void setTipoServicio1(String tipoServicio1) {
        this.tipoServicio1 = tipoServicio1;
    }

    public String getTipoServicio2() {
        return tipoServicio2;
    }

    public void setTipoServicio2(String tipoServicio2) {
        this.tipoServicio2 = tipoServicio2;
    }

    public String getTipoServicio3() {
        return tipoServicio3;
    }

    public void setTipoServicio3(String tipoServicio3) {
        this.tipoServicio3 = tipoServicio3;
    }

    public String getTipoServicio4() {
        return tipoServicio4;
    }

    public void setTipoServicio4(String tipoServicio4) {
        this.tipoServicio4 = tipoServicio4;
    }

    public String getTipoServicio5() {
        return tipoServicio5;
    }

    public void setTipoServicio5(String tipoServicio5) {
        this.tipoServicio5 = tipoServicio5;
    }

    public String getTipoServicio6() {
        return tipoServicio6;
    }

    public void setTipoServicio6(String tipoServicio6) {
        this.tipoServicio6 = tipoServicio6;
    }
            
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
    
    public void registrarPreventivo(){
        PreventivoDAO preventivoDao = new PreventivoDAO();
        Usuario usuarioVive = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        try{
            
            preventivo.setAño(String.valueOf(new java.util.Date().getYear()+1900));
            preventivo.setId_usuario_personal(usuarioVive);
            preventivo.setFolio("mp-"+(new java.util.Date().getYear()+1900)+"/"+(preventivoDao.ultimoIdInsertado()+1));
            preventivo.setFecha_elaboracion(new java.sql.Date(preventivo.getFecha_elaboracion().getTime()));
            preventivoDao.registrarPreventivo(preventivo);
            
            preventivo = new PreventivoDAO().buscarPreventivo(preventivo);//busca al objeto de preventivo y lo asigna al mismo objeto
            
            if(!detalle1.getServicio().isEmpty()){
                detalle1.setTipo_servicio(tipoServicio1);
                detalle1.setNumero_servicio(1);
                detalle1.setFecha_programada(fecha1);
                detalle1.setFecha_realizada(new java.util.Date(0001, 01, 01));
                detalle1.setFecha_reprogramada(new java.util.Date(0001, 01, 01));
                detalle1.setId_preventivo(preventivo);
                new DetallePreventivoBEAN().registrarDetallePreventivo(detalle1);
            }
             if(!detalle2.getServicio().isEmpty()){
                detalle2.setTipo_servicio(tipoServicio2);
                 detalle1.setNumero_servicio(2);
                detalle2.setFecha_programada(fecha2);
                detalle2.setFecha_realizada(new java.util.Date(0001, 01, 01));
                detalle2.setFecha_reprogramada(new java.util.Date(0001, 01, 01));
                detalle2.setId_preventivo(preventivo);
                new DetallePreventivoBEAN().registrarDetallePreventivo(detalle2);
            }
             if(!detalle3.getServicio().isEmpty()){
                detalle3.setTipo_servicio(tipoServicio3);
                 detalle1.setNumero_servicio(3);
                detalle3.setFecha_programada(fecha3);
                detalle3.setId_preventivo(preventivo);
                new DetallePreventivoBEAN().registrarDetallePreventivo(detalle3);
            }
             if(!detalle4.getServicio().isEmpty()){
                detalle4.setTipo_servicio(tipoServicio4);
                 detalle1.setNumero_servicio(4);
                detalle4.setFecha_programada(fecha4);
                detalle4.setId_preventivo(preventivo);
                new DetallePreventivoBEAN().registrarDetallePreventivo(detalle4);
            }
             if(!detalle5.getServicio().isEmpty()){
                detalle5.setTipo_servicio(tipoServicio5);
                 detalle1.setNumero_servicio(5);
                detalle5.setFecha_programada(fecha5);
                detalle5.setId_preventivo(preventivo);
                new DetallePreventivoBEAN().registrarDetallePreventivo(detalle5);
            }
             if(!detalle6.getServicio().isEmpty()){
                detalle6.setTipo_servicio(tipoServicio6);
                 detalle1.setNumero_servicio(6);
                detalle6.setFecha_programada(fecha6);
                detalle6.setId_preventivo(preventivo);
                new DetallePreventivoBEAN().registrarDetallePreventivo(detalle6);
            }
            
        }catch(Exception ex){
            System.out.println("Error en PreventivoBEAN -> registrarPreventivo "+ ex);
        }
    }
    
}
