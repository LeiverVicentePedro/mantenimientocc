/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.modelo;

import java.util.Date;

/**
 *
 * @author Jerusalen
 */
public class Orden_interna {
    
    private int idorden_interna;
    private Solicitud_mc idsolicitud;
    private String nombre_orden;
   
    private Usuario id_usuario_personal;
    private Date fecha;
    private String reporte_fallo;
    private String reporte_tecnico;
    private String posible_causa;

    public int getIdorden_interna() {
        return idorden_interna;
    }

    public void setIdorden_interna(int idorden_interna) {
        this.idorden_interna = idorden_interna;
    }

    public Solicitud_mc getIdsolicitud() {
        return idsolicitud;
    }

    public void setIdsolicitud(Solicitud_mc idsolicitud) {
        this.idsolicitud = idsolicitud;
    }

   

  
    

    public String getNombre_orden() {
        return nombre_orden;
    }

    public void setNombre_orden(String nombre_orden) {
        this.nombre_orden = nombre_orden;
    }

  

    public Usuario getId_usuario_personal() {
        return id_usuario_personal;
    }

    public void setId_usuario_personal(Usuario id_usuario_personal) {
        this.id_usuario_personal = id_usuario_personal;
    }

    
    
   
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getReporte_fallo() {
        return reporte_fallo;
    }

    public void setReporte_fallo(String reporte_fallo) {
        this.reporte_fallo = reporte_fallo;
    }

    public String getReporte_tecnico() {
        return reporte_tecnico;
    }

    public void setReporte_tecnico(String reporte_tecnico) {
        this.reporte_tecnico = reporte_tecnico;
    }

    public String getPosible_causa() {
        return posible_causa;
    }

    public void setPosible_causa(String posible_causa) {
        this.posible_causa = posible_causa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.idorden_interna;
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
        final Orden_interna other = (Orden_interna) obj;
        if (this.idorden_interna != other.idorden_interna) {
            return false;
        }
        return true;
    }
    
    
    
}
