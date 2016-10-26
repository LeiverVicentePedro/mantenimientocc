/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import mx.edu.itoaxaca.mantenimientocc.conexion.Conexion;
import mx.edu.itoaxaca.mantenimientocc.modelo.Oficina_solicitante;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;

/**
 *
 * @author leiver
 */
public class UsuarioDAO extends Conexion{
    
    public void registrarUsuario(Usuario usuarioRegistrar) throws Exception
    {
        try{
            this.Conectar();
            PreparedStatement inserta = this.getConexion().prepareStatement("INSERT INTO usuario (nombre, apellido_materno, apellido_paterno,correo, clave, nivel,id_oficina, rfc, profesion, tipo_bt, estatus)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            inserta.setString(1,usuarioRegistrar.getNombre());
            inserta.setString(2, usuarioRegistrar.getApellidoMaterno());
            inserta.setString(3, usuarioRegistrar.getApellidoPaterno());
            inserta.setString(4, usuarioRegistrar.getCorreo());
            inserta.setString(5, usuarioRegistrar.getClave());
            inserta.setInt(6, usuarioRegistrar.getNivel());
            inserta.setInt(7, usuarioRegistrar.getIdOficina().getIdOficinaSolicitante());
            inserta.setString(8, usuarioRegistrar.getRfc());
            inserta.setString(9, usuarioRegistrar.getProfesion());
            inserta.setString(10, usuarioRegistrar.getTipoBT());
            inserta.setBoolean(11, usuarioRegistrar.getEstatus());
            
            inserta.executeUpdate();
            
        }catch(Exception ex){
            System.out.println("Error en UsuarioDAO -> RegistrarUsuario "+ex);
            throw ex;
        }finally{
            this.Cerrar();
        }
    }
    
    public Usuario consultarUsuario(String  correo) throws Exception
    {
        Usuario usuarioUnico = null;
        try{
            this.Conectar();
            PreparedStatement consultar = this.getConexion().prepareStatement("SELECT * FROM usuario WHERE correo=?");
            consultar.setString(1,correo);
            ResultSet resultado = consultar.executeQuery();
            
            usuarioUnico.setIdUsuario(resultado.getInt("idUsuario"));
            usuarioUnico.setNombre(resultado.getString("nombre"));
            usuarioUnico.setApellidoPaterno(resultado.getString("apellido_paterno"));
            usuarioUnico.setApellidoMaterno(resultado.getString("apellido_materno"));
            usuarioUnico.setCorreo(resultado.getString("correo"));
            usuarioUnico.setClave(resultado.getString("clave"));
            usuarioUnico.setNivel(resultado.getInt("nivel"));
            usuarioUnico.setIdOficina((Oficina_solicitante) resultado.getObject("id_oficina"));
            usuarioUnico.setRfc(resultado.getString("rfc"));
            usuarioUnico.setProfesion(resultado.getString("profesion"));
            usuarioUnico.setTipoBT(resultado.getString("tipo_bt"));
            usuarioUnico.setEstatus(resultado.getBoolean("estatus"));
            resultado.close();
            
            return usuarioUnico;
        }catch(Exception ex){
            throw ex;
        }finally{
           this.Cerrar();
        }
      
    }  
}
