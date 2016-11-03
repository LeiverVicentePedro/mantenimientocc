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

    public Usuario accesoUsuario(String correo, String clave) throws Exception {
        Usuario usuario = new Usuario();
        try {
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("SELECT * FROM usuario WHERE correo=? and clave=?");
            consulta.setString(1, correo);
            consulta.setString(2, clave);
            ResultSet resultado = consulta.executeQuery();
            System.out.println("impresion resultset Row "+resultado.getRow()+"");
            if (resultado!=null) {
                resultado.next();
                usuario.setIdUsuario(resultado.getInt("idusuario"));
                usuario.setNombre(resultado.getString("nombre"));
                usuario.setApellidoPaterno(resultado.getString("apellido_paterno"));
                usuario.setApellidoMaterno(resultado.getString("apellido_materno"));
                usuario.setRfc(resultado.getString("rfc"));
                usuario.setProfesion(resultado.getString("profesion"));
                usuario.setTipoBT(resultado.getString("tipo_bt"));
                usuario.setCorreo(resultado.getString("correo"));
                usuario.setClave(resultado.getString("clave"));
                usuario.setEstatus(resultado.getBoolean("estatus"));
                usuario.setNivel(resultado.getInt("nivel"));
                usuario.setIdOficina(new Oficina_solicitanteDAO().buscarOficina(resultado.getInt("id_oficina")));
            }else{
                System.out.println("usuario en else "+ usuario);
                return usuario;
            }
        } catch (Exception ex) {
            System.out.println("Error en AccesoDAO -> accesoUsuario " + ex);
            throw ex;
        } finally {
            this.Cerrar();
        }
        System.out.println("usuario en si encontro "+ usuario);
        return usuario;
    }
}
