/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.dao;

import java.sql.PreparedStatement;
import mx.edu.itoaxaca.mantenimientocc.conexion.Conexion;
import mx.edu.itoaxaca.mantenimientocc.modelo.Seguimiento;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mx.edu.itoaxaca.mantenimientocc.modelo.Solicitud_mc;

/**
 *
 * @author Jerusalen
 */
public class SeguimientoDAO extends Conexion{
    
    public void registrarSeguimiento(Seguimiento seguimiento) throws Exception  {

        try {
            this.Conectar();
            PreparedStatement inserta = this.getConexion().prepareStatement("INSERT INTO seguimiento (fecha, id_usuario_personal, id_solicitud, estado_solicitud, estado_asignacion, estado_equipo_revisado, estado_progreso, estado_termino, id_usuario_solicitante) values(?,?,?,?,?,?,?,?,?)");
            inserta.setDate(1, (Date) seguimiento.getFecha());
            inserta.setInt(2, seguimiento.getId_usuario_personal().getIdUsuario());
            inserta.setInt(3, seguimiento.getId_solicitud().getIdsolicitud_mc());
            inserta.setBoolean(4, seguimiento.getEstado_solicitud());
            inserta.setBoolean(5, seguimiento.getEstado_asignacion());
            inserta.setBoolean(6, seguimiento.getEstado_equipo_revisado());
            inserta.setBoolean(7, seguimiento.getEstado_progreso());
            inserta.setBoolean(8, seguimiento.getEstado_termino());
            inserta.setInt(9, seguimiento.getId_usuario_solicitante().getIdUsuario());
            inserta.executeUpdate();
          
        } 
        catch(Exception e){
          System.out.println("error en AREA DAO -->RegistrarAREA"+"/n"+e);
        }
        finally{
           this.Cerrar();
        }
    }
    
    public Seguimiento elegirDatoSeguimiento(Solicitud_mc seguimientoElige) throws Exception{
        Seguimiento seguimientoDos=null;
        ResultSet resultadosetseguimiento;
        try{
            this.Conectar();
             PreparedStatement consulta= this.getConexion().prepareStatement("SELECT * FROM seguimiento WHERE id_solicitud=?");
            consulta.setInt(1, seguimientoElige.getIdsolicitud_mc());
            resultadosetseguimiento = consulta.executeQuery();
            while(resultadosetseguimiento.next())
            {
              seguimientoDos= new Seguimiento();
              
             seguimientoDos.setIdseguimiento(resultadosetseguimiento.getInt("idseguimiento"));
              seguimientoDos.setFecha(resultadosetseguimiento.getDate("fecha"));
              seguimientoDos.setId_usuario_personal(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadosetseguimiento.getInt("id_usuario_personal")));
              seguimientoDos.setId_solicitud(new Solicitud_mcDAO().buscarDeSolicitudEntero(resultadosetseguimiento.getInt("id_solicitud")));
              seguimientoDos.setEstado_solicitud(resultadosetseguimiento.getBoolean("estado_solicitud"));
              seguimientoDos.setEstado_asignacion(resultadosetseguimiento.getBoolean("estado_asignacion"));
              seguimientoDos.setEstado_equipo_revisado(resultadosetseguimiento.getBoolean("estado_equipo_revisado"));
              seguimientoDos.setEstado_progreso(resultadosetseguimiento.getBoolean("estado_progreso"));
              seguimientoDos.setEstado_termino(resultadosetseguimiento.getBoolean("estado_termino"));
              seguimientoDos.setId_usuario_solicitante(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadosetseguimiento.getInt("id_usuario_solicitante")));
            }
        }
        catch(Exception e){
           System.out.println("error en SeguimientoDao metodo ElegirDato"+e);
        }
        finally{
           this.Cerrar();
        }
        return seguimientoDos;
    }
    
    public void modificarSeguimiento (Seguimiento seguimientomodificar) throws Exception{
          
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("UPDATE seguimiento SET fecha=?, id_usuario_personal=?, id_solicitud=?, estado_solicitud=?, estado_asignacion=?, estado_equipo_revisado=?, estado_progreso=?, estado_termino=?, id_usuario_solicitante=? WHERE idseguimiento=?");
           consulta.setDate(1, (Date) seguimientomodificar.getFecha());
           consulta.setInt(2, seguimientomodificar.getId_usuario_personal().getIdUsuario());
            consulta.setInt(3, seguimientomodificar.getId_solicitud().getIdsolicitud_mc());
            consulta.setBoolean(4, seguimientomodificar.getEstado_solicitud());
           consulta.setBoolean(5, seguimientomodificar.getEstado_asignacion());
           consulta.setBoolean(6, seguimientomodificar.getEstado_equipo_revisado());
           consulta.setBoolean(7, seguimientomodificar.getEstado_progreso());
           consulta.setBoolean(8, seguimientomodificar.getEstado_termino());
           consulta.setInt(9, seguimientomodificar.getId_usuario_solicitante().getIdUsuario());
           consulta.setInt(10,seguimientomodificar.getIdseguimiento());
            consulta.executeUpdate();
        }
        catch(Exception e){
          System.out.println("error en SeguimientoDao metodo Modificar"+e);
        }
        finally{
           this.Cerrar();
        }
    }  
    
    public List<Seguimiento> listarSeguimiento() throws Exception{
     List<Seguimiento> lista;
        ResultSet resultadoset;
     try{
         this.Conectar();
         PreparedStatement consulta=this.getConexion().prepareCall("SELECT * FROM seguimiento");
         resultadoset= consulta.executeQuery();
         lista =new ArrayList();
         while(resultadoset.next()){
             Seguimiento seguimiento=new Seguimiento();
              seguimiento.setIdseguimiento(resultadoset.getInt("idseguimiento"));
              seguimiento.setFecha(resultadoset.getDate("fecha"));
              seguimiento.setId_usuario_personal(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoset.getInt("id_usuario_personal")));
              seguimiento.setId_solicitud(new Solicitud_mcDAO().buscarDeSolicitudEntero(resultadoset.getInt("id_solicitud")));
              seguimiento.setEstado_solicitud(resultadoset.getBoolean("estado_solicitud"));
              seguimiento.setEstado_asignacion(resultadoset.getBoolean("estado_asignacion"));
              seguimiento.setEstado_equipo_revisado(resultadoset.getBoolean("estado_equipo_revisado"));
              seguimiento.setEstado_progreso(resultadoset.getBoolean("estado_progreso"));
              seguimiento.setEstado_termino(resultadoset.getBoolean("estado_termino"));
              seguimiento.setId_usuario_solicitante(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoset.getInt("id_usuario_solicitante")));
           
             
             lista.add(seguimiento);
         }
             
     }
     catch(Exception e){
         throw e;
         
     }
     finally{
         this.Cerrar();
     }
     return lista;
    }
   
    
}
