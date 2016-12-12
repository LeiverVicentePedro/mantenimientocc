/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.modelo;

import java.util.Date;

/**
 *
 * @author leiver
 */
public class DetallePreventivo {
    private int idDetalle_preventivo;
    private int numero_servicio;
    private String servicio;
    private String tipo_servicio;
    private Date fecha_programada;
    private Date fecha_realizada;
    private Date fecha_reprogramada;
    private Preventivo id_preventivo;

    public int getIdDetalle_preventivo() {
        return idDetalle_preventivo;
    }

    public void setIdDetalle_preventivo(int idDetalle_preventivo) {
        this.idDetalle_preventivo = idDetalle_preventivo;
    }

    public int getNumero_servicio() {
        return numero_servicio;
    }

    public void setNumero_servicio(int numero_servicio) {
        this.numero_servicio = numero_servicio;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getTipo_servicio() {
        return tipo_servicio;
    }

    public void setTipo_servicio(String tipo_servicio) {
        this.tipo_servicio = tipo_servicio;
    }

    public Date getFecha_programada() {
        return fecha_programada;
    }

    public void setFecha_programada(Date fecha_programada) {
        this.fecha_programada = fecha_programada;
    }

    public Date getFecha_realizada() {
        return fecha_realizada;
    }

    public void setFecha_realizada(Date fecha_realizada) {
        this.fecha_realizada = fecha_realizada;
    }

    public Date getFecha_reprogramada() {
        return fecha_reprogramada;
    }

    public void setFecha_reprogramada(Date fecha_reprogramada) {
        this.fecha_reprogramada = fecha_reprogramada;
    }

    public Preventivo getId_preventivo() {
        return id_preventivo;
    }

    public void setId_preventivo(Preventivo id_preventivo) {
        this.id_preventivo = id_preventivo;
    }
}
