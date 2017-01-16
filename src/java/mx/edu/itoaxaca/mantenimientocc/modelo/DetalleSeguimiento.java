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
 private UploadedFile imagen;
 private Seguimiento id_seguimiento;
 private Date fecha;

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


}

   