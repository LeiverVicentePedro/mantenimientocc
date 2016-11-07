/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import mx.edu.itoaxaca.mantenimientocc.conexion.Conexion;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;

/**
 *
 * @author leiver
 */
public class AccesoDAO extends Conexion {
    
    public Usuario accesoUsuario(String correo, String clave) throws Exception{
        Usuario usuario = null;
        ResultSet resultado;
        try{
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareCall("SELECT * FROM usuario WHERE correo=? and clave=?");
            consulta.setString(1, correo);
            consulta.setString(2, clave);
             resultado= consulta.executeQuery();
            if(resultado.next()==true){
                usuario = new Usuario();
                usuario.setIdUsuario(resultado.getInt("idusuario"));
                usuario.setNombre(resultado.getString("nombre"));
                usuario.setApellidoPaterno(resultado.getString("apellido_paterno"));
                usuario.setApellidoMaterno(resultado.getString("apellido_materno"));
                usuario.setProfesion(resultado.getString("profesion"));
                usuario.setRfc(resultado.getString("rfc"));
                usuario.setTipoBT(resultado.getString("tipo_bt"));
                usuario.setNivel(resultado.getInt("nivel"));
                usuario.setCorreo(resultado.getString("correo"));
                usuario.setClave(resultado.getString("clave"));
                usuario.setEstatus(resultado.getBoolean("estatus"));
                usuario.setIdOficina(new Oficina_solicitanteDAO().buscarOficina(resultado.getInt("id_oficina")));
                resultado.close();
            }else{
                System.out.println("Esta vacio el objeto ");
            }
            
            return usuario;
        }catch(Exception ex){
            System.out.println("Error en AccesoDAO -> accesoUsuario "+ex);
            throw ex;
        }finally{
            this.Cerrar();
        }
        
    } 
}
