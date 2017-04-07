/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.modelo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Jerusalen
 */
public class Niveles_internet implements Serializable{
    
    private int idniveles_internet;
    Catalogo_niveles id_catalogo_niveles;
    private String modelo_equipo;
    private String mac;
    private String so;
    private Date fecha;
    private String edificio;
    private String nivel_edificio;
    private String tipo_solicitud;
    private int vlan;
    private String conexion;
    private String tipo_equipo;
    Usuario solicita;
    Usuario autoriza;
    private String correo_solicita; 
    private String correo_autoriza;
    private String configuro;
    private String ip;
    private String justificacion;
    private Boolean estatus_autoriza;
    private int puerto;

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }
    

    
    public Boolean getEstatus_autoriza() {
        return estatus_autoriza;
    }

    public void setEstatus_autoriza(Boolean estatus_autoriza) {
        this.estatus_autoriza = estatus_autoriza;
    }
    
    public int getIdniveles_internet() {
        return idniveles_internet;
    }

    public void setIdniveles_internet(int idniveles_internet) {
        this.idniveles_internet = idniveles_internet;
    }

    public String getModelo_equipo() {
        return modelo_equipo;
    }

    public void setModelo_equipo(String modelo_equipo) {
        this.modelo_equipo = modelo_equipo;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getSo() {
        return so;
    }

    public void setSo(String so) {
        this.so = so;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public String getNivel_edificio() {
        return nivel_edificio;
    }

    public void setNivel_edificio(String nivel_edificio) {
        this.nivel_edificio = nivel_edificio;
    }

    public String getTipo_solicitud() {
        return tipo_solicitud;
    }

    public void setTipo_solicitud(String tipo_solicitud) {
        this.tipo_solicitud = tipo_solicitud;
    }

    public int getVlan() {
        return vlan;
    }

    public void setVlan(int vlan) {
        this.vlan = vlan;
    }

    public String getConexion() {
        return conexion;
    }

    public void setConexion(String conexion) {
        this.conexion = conexion;
    }

    public String getTipo_equipo() {
        return tipo_equipo;
    }

    public void setTipo_equipo(String tipo_equipo) {
        this.tipo_equipo = tipo_equipo;
    }

    public Usuario getSolicita() {
        return solicita;
    }

    public void setSolicita(Usuario solicita) {
        this.solicita = solicita;
    }

    public Usuario getAutoriza() {
        return autoriza;
    }

    public void setAutoriza(Usuario autoriza) {
        this.autoriza = autoriza;
    }

  
    public String getCorreo_solicita() {
        return correo_solicita;
    }

    public void setCorreo_solicita(String correo_solicita) {
        this.correo_solicita = correo_solicita;
    }

    public String getCorreo_autoriza() {
        return correo_autoriza;
    }

    public void setCorreo_autoriza(String correo_autoriza) {
        this.correo_autoriza = correo_autoriza;
    }

    public String getConfiguro() {
        return configuro;
    }

    public void setConfiguro(String configuro) {
        this.configuro = configuro;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Catalogo_niveles getId_catalogo_niveles() {
        return id_catalogo_niveles;
    }

    public void setId_catalogo_niveles(Catalogo_niveles id_catalogo_niveles) {
        this.id_catalogo_niveles = id_catalogo_niveles;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.idniveles_internet;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Niveles_internet other = (Niveles_internet) obj;
        if (this.idniveles_internet != other.idniveles_internet) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
