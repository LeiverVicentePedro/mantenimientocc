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
public class Usuario implements Serializable{
    
    private int idUsuario;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String clave;
    private int nivel;
    private Oficina_solicitante idOficina;
    private String rfc;
    private Profesion id_profesion;
    private String tipoBT;
    private Boolean estatus;
    private String concatenar;

    
    public String getConcatenar() {
        return concatenar;
    }

    public void setConcatenar() {
        
        this.concatenar = getNombre()+" "+getApellidoPaterno()+" "+getApellidoMaterno();
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Oficina_solicitante getIdOficina() {
        return idOficina;
    }

    public void setIdOficina(Oficina_solicitante idOficina) {
        this.idOficina = idOficina;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public Profesion getId_profesion() {
        return id_profesion;
    }

    public void setId_profesion(Profesion id_profesion) {
        this.id_profesion = id_profesion;
    }

    public String getTipoBT() {
        return tipoBT;
    }

    public void setTipoBT(String tipoBT) {
        this.tipoBT = tipoBT;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.idUsuario;
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
        final Usuario other = (Usuario) obj;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        return true;
    }
   

   
   
     
}
