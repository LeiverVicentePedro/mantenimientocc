/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.modelo;

import java.io.Serializable;

/**
 *
 * @author leiver
 */
public class ConfiguracionServicioSocial implements Serializable{
    
    private int idconfiguracion_servicio_social;
    private int horas_servicio;
    private String ip_checador;

    public int getIdconfiguracion_servicio_social() {
        return idconfiguracion_servicio_social;
    }

    public void setIdconfiguracion_servicio_social(int idconfiguracion_servicio_social) {
        this.idconfiguracion_servicio_social = idconfiguracion_servicio_social;
    }

    public int getHoras_servicio() {
        return horas_servicio;
    }

    public void setHoras_servicio(int horas_servicio) {
        this.horas_servicio = horas_servicio;
    }

    public String getIp_checador() {
        return ip_checador;
    }

    public void setIp_checador(String ip_checador) {
        this.ip_checador = ip_checador;
    }
         
}
