/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.modelo;

/**
 *
 * @author Jerusalen
 */
import java.io.Serializable;
import java.util.Date;


public class Seguimiento implements Serializable {
    
    private int idseguimiento;
    private Date fecha;
    private Usuario id_usuario_personal;//se tomara apartir del usuario Vive
    private Solicitud_mc id_solicitud;// se tomara de la lista de asignaciones
    private Boolean estado_solicitud;
    private Boolean estado_asignacion;
    private Boolean estado_equipo_revisado;
    private Boolean estado_progreso;
    private Boolean estado_termino;
    private Usuario id_usuario_solicitante;

    public int getIdseguimiento() {
        return idseguimiento;
    }

    public void setIdseguimiento(int idseguimiento) {
        this.idseguimiento = idseguimiento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getId_usuario_personal() {
        return id_usuario_personal;
    }

    public void setId_usuario_personal(Usuario id_usuario_personal) {
        this.id_usuario_personal = id_usuario_personal;
    }

    public Solicitud_mc getId_solicitud() {
        return id_solicitud;
    }

    public void setId_solicitud(Solicitud_mc id_solicitud) {
        this.id_solicitud = id_solicitud;
    }

    public Boolean getEstado_solicitud() {
        return estado_solicitud;
    }

    public void setEstado_solicitud(Boolean estado_solicitud) {
        this.estado_solicitud = estado_solicitud;
    }

    public Boolean getEstado_asignacion() {
        return estado_asignacion;
    }

    public void setEstado_asignacion(Boolean estado_asignacion) {
        this.estado_asignacion = estado_asignacion;
    }

    public Boolean getEstado_equipo_revisado() {
        return estado_equipo_revisado;
    }

    public void setEstado_equipo_revisado(Boolean estado_equipo_revisado) {
        this.estado_equipo_revisado = estado_equipo_revisado;
    }

    public Boolean getEstado_progreso() {
        return estado_progreso;
    }

    public void setEstado_progreso(Boolean estado_progreso) {
        this.estado_progreso = estado_progreso;
    }

    public Boolean getEstado_termino() {
        return estado_termino;
    }

    public void setEstado_termino(Boolean estado_termino) {
        this.estado_termino = estado_termino;
    }

    public Usuario getId_usuario_solicitante() {
        return id_usuario_solicitante;
    }

    public void setId_usuario_solicitante(Usuario id_usuario_solicitante) {
        this.id_usuario_solicitante = id_usuario_solicitante;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Seguimiento other = (Seguimiento) obj;
        if (this.idseguimiento != other.idseguimiento) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
}
