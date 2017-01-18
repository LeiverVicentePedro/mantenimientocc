/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.modelo;

import java.util.Date;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Jerusalen
 */
public class DetalleSeguimiento {
 private int iddetalle_seguimiento;
 private String estado;
 private String descripcion;
 private UploadedFile imagen;//para subir la imagen unicamente
 private byte[] imagenDowload;//para bajar la imagen unicamente
 private Seguimiento id_seguimiento;
 private Date fecha;
 private String ruta;
 
    public int getIddetalle_seguimiento() {
        return iddetalle_seguimiento;
    }

    public void setIddetalle_seguimiento(int iddetalle_seguimiento) {
        this.iddetalle_seguimiento = iddetalle_seguimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getImagenDowload() {
        return imagenDowload;
    }

    public void setImagenDowload(byte[] imagenDowload) {
        this.imagenDowload = imagenDowload;
    }
    
    public UploadedFile getImagen() {
        return imagen;
    }

    public void setImagen(UploadedFile imagen) {
        this.imagen = imagen;
    }

    public Seguimiento getId_seguimiento() {
        return id_seguimiento;
    }

    public void setId_seguimiento(Seguimiento id_seguimiento) {
        this.id_seguimiento = id_seguimiento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    

}

   