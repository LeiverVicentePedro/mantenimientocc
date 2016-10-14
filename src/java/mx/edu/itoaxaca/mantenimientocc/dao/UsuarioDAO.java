/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import mx.edu.itoaxaca.mantenimientocc.conexion.Conexion;
import mx.edu.itoaxaca.mantenimientocc.modelo.Area;
import mx.edu.itoaxaca.mantenimientocc.modelo.Departamento;
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
            PreparedStatement inserta = this.getConexion().prepareStatement("INSERT INTO usuario (nombre, apellido_materno, apellido_paterno,correo, clave, id_area, nivel,id_departamento, id_oficina)"
                    + "VALUES(?,?,?,?,?,?,?,?,?)");
            inserta.setString(1,usuarioRegistrar.getNombre());
            inserta.setString(2, usuarioRegistrar.getApellidoMaterno());
            inserta.setString(3, usuarioRegistrar.getApellidoPaterno());
            inserta.setString(4, usuarioRegistrar.getCorreo());
            inserta.setString(5, usuarioRegistrar.getClave());
            inserta.setInt(6, usuarioRegistrar.getIdArea().getIdarea());
            inserta.setInt(7, usuarioRegistrar.getNivel());
            inserta.setInt(8, usuarioRegistrar.getIdDepartamento().getIdDepartamento());
            inserta.setInt(9, usuarioRegistrar.getIdOficina().getIdOficinaSolicitante());
            
            inserta.executeUpdate();
            
        }catch(Exception ex){
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
            ResultSet resultado = consultar.executeQuery();
            
            usuarioUnico.setIdUsuario(resultado.getInt("idUsuario"));
            usuarioUnico.setNombre(resultado.getString("nombre"));
            usuarioUnico.setApellidoPaterno(resultado.getString("apellido_paterno"));
            usuarioUnico.setApellidoMaterno(resultado.getString("apellido_materno"));
            usuarioUnico.setCorreo(resultado.getString("correo"));
            usuarioUnico.setClave(resultado.getString("clave"));
            usuarioUnico.setIdArea((Area) resultado.getObject("id_area"));
            usuarioUnico.setIdDepartamento((Departamento) resultado.getObject("id_departamento"));
            usuarioUnico.setIdOficina((Oficina_solicitante) resultado.getObject("id_oficina"));
            usuarioUnico.setNivel(resultado.getInt("nivel"));
            
            resultado.close();
            
            return usuarioUnico;
        }catch(Exception ex){
            throw ex;
        }finally{
           this.Cerrar();
        }
      
    }
    
     
}
