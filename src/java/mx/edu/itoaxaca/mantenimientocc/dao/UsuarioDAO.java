/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mx.edu.itoaxaca.mantenimientocc.conexion.Conexion;
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
            PreparedStatement inserta = this.getConexion().prepareStatement("INSERT INTO usuario (nombre, apellido_materno, apellido_paterno,correo, clave, nivel,id_oficina, rfc, id_profesion, tipo_bt, estatus)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            inserta.setString(1,usuarioRegistrar.getNombre());
            inserta.setString(2, usuarioRegistrar.getApellidoMaterno());
            inserta.setString(3, usuarioRegistrar.getApellidoPaterno());
            inserta.setString(4, usuarioRegistrar.getCorreo());
            inserta.setString(5, usuarioRegistrar.getClave());
            inserta.setInt(6, usuarioRegistrar.getNivel());
            inserta.setInt(7, usuarioRegistrar.getIdOficina().getIdOficinaSolicitante());
            inserta.setString(8, usuarioRegistrar.getRfc());
            inserta.setInt(9, usuarioRegistrar.getId_profesion().getIdprofesion());
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
        Usuario usuarioUnico = new Usuario();
        try{
            this.Conectar();
            PreparedStatement consultar = this.getConexion().prepareStatement("SELECT * FROM usuario WHERE correo=?");
            consultar.setString(1,correo);
            ResultSet resultado = consultar.executeQuery();
            if(resultado.next()==true){
            usuarioUnico.setIdUsuario(resultado.getInt("idusuario"));
            usuarioUnico.setNombre(resultado.getString("nombre"));
            usuarioUnico.setApellidoPaterno(resultado.getString("apellido_paterno"));
            usuarioUnico.setApellidoMaterno(resultado.getString("apellido_materno"));
            usuarioUnico.setCorreo(resultado.getString("correo"));
            usuarioUnico.setClave(resultado.getString("clave"));
            usuarioUnico.setNivel(resultado.getInt("nivel"));
            usuarioUnico.setIdOficina(new Oficina_solicitanteDAO().buscarOficina(resultado.getInt("id_oficina")));
            usuarioUnico.setRfc(resultado.getString("rfc"));
            usuarioUnico.setId_profesion(new ProfesionDAO().elegirDatoProfesionPorIdProfesion(resultado.getInt("id_profesion")));
            usuarioUnico.setTipoBT(resultado.getString("tipo_bt"));
            usuarioUnico.setEstatus(resultado.getBoolean("estatus"));
            }
            resultado.close();
            return usuarioUnico;
        }catch(Exception ex){
            System.out.println("Error en UsuarioDAO -> consultaUsuario");
            throw ex;
        }finally{
           this.Cerrar();
        } 
    }
    
    public Usuario consultarUsuarioPorId(Usuario usuario) throws Exception
    {
        Usuario usuarioUnico = new Usuario();
        try{
            this.Conectar();
            PreparedStatement consultar = this.getConexion().prepareStatement("SELECT * FROM usuario WHERE idusuario=?");
            consultar.setInt(1,usuario.getIdUsuario());
            ResultSet resultado = consultar.executeQuery();
            if(resultado.next()==true){
            usuarioUnico.setIdUsuario(resultado.getInt("idusuario"));
            usuarioUnico.setNombre(resultado.getString("nombre"));
            usuarioUnico.setApellidoPaterno(resultado.getString("apellido_paterno"));
            usuarioUnico.setApellidoMaterno(resultado.getString("apellido_materno"));
            usuarioUnico.setCorreo(resultado.getString("correo"));
            usuarioUnico.setClave(resultado.getString("clave"));
            usuarioUnico.setNivel(resultado.getInt("nivel"));
            usuarioUnico.setIdOficina(new Oficina_solicitanteDAO().buscarOficina(resultado.getInt("id_oficina")));
            usuarioUnico.setRfc(resultado.getString("rfc"));
            usuarioUnico.setId_profesion(new ProfesionDAO().elegirDatoProfesionPorIdProfesion(resultado.getInt("id_profesion")));
            usuarioUnico.setTipoBT(resultado.getString("tipo_bt"));
            usuarioUnico.setEstatus(resultado.getBoolean("estatus"));
            
            
            }
            resultado.close();
            return usuarioUnico;
        }catch(Exception ex){
            System.out.println("Error en UsuarioDAO -> consultaUsuarioPorId "+ex);
            throw ex;
        }finally{
           this.Cerrar();
        } 
    }
    
    public List<Usuario> listaUsuarioDepartamento(Usuario usuario) throws Exception
    {
        List<Usuario> listaUsuarioDepartamento = null;
        
        ResultSet resultado;
        System.out.println("usuario: "+usuario.getIdOficina().getIdOficinaSolicitante());
       try{
           this.Conectar();
           PreparedStatement consulta = this.getConexion().prepareStatement("SELECT * FROM usuario WHERE id_oficina=?");
           consulta.setInt(1,usuario.getIdOficina().getIdOficinaSolicitante());
           resultado = consulta.executeQuery();
           listaUsuarioDepartamento = new ArrayList();
           while(resultado.next()){
               Usuario usuarioParaLista = new Usuario();
             usuarioParaLista.setIdUsuario(resultado.getInt("idusuario"));
            usuarioParaLista.setNombre(resultado.getString("nombre"));
            usuarioParaLista.setApellidoPaterno(resultado.getString("apellido_paterno"));
            usuarioParaLista.setApellidoMaterno(resultado.getString("apellido_materno"));
            usuarioParaLista.setCorreo(resultado.getString("correo"));
            usuarioParaLista.setClave(resultado.getString("clave"));
            usuarioParaLista.setNivel(resultado.getInt("nivel"));
            usuarioParaLista.setIdOficina(new Oficina_solicitanteDAO().buscarOficina(resultado.getInt("id_oficina")));
            usuarioParaLista.setRfc(resultado.getString("rfc"));
            usuarioParaLista.setId_profesion(new ProfesionDAO().elegirDatoProfesionPorIdProfesion(resultado.getInt("id_profesion")));
            usuarioParaLista.setTipoBT(resultado.getString("tipo_bt"));
            usuarioParaLista.setEstatus(resultado.getBoolean("estatus"));
            
            listaUsuarioDepartamento.add(usuarioParaLista);
            
           }
           resultado.close();
           
           
       }catch(Exception ex){
           System.out.println("Eror en UsuarioDAO -> listaUsuarioDepartamento "+ex);
       }finally{
           this.Cerrar();
       }
       return listaUsuarioDepartamento;
    }
    
    public void modificarUsuario(Usuario usuario) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("UPDATE usuario SET  rfc=?, nombre=?, apellido_paterno=?, apellido_materno=?, correo=?, clave=?, nivel=?, id_oficina=?, profesion=?, tipo_bt=?, estatus=? WHERE idusuario=?");
            consulta.setString(1, usuario.getRfc());
            consulta.setString(2, usuario.getNombre());
            consulta.setString(3, usuario.getApellidoPaterno());
            consulta.setString(4,usuario.getApellidoMaterno());
            consulta.setString(5, usuario.getCorreo());
            consulta.setString(6, usuario.getClave());
            consulta.setInt(7, usuario.getNivel());
            consulta.setInt(8, usuario.getIdOficina().getIdOficinaSolicitante());
            consulta.setInt(9, usuario.getId_profesion().getIdprofesion());
            consulta.setString(10, usuario.getTipoBT());
            consulta.setBoolean(11, usuario.getEstatus());
            consulta.setInt(12, usuario.getIdUsuario());
            consulta.executeUpdate();
        }catch(Exception ex){
            System.out.println("Error en UsuarioDAO -> modificarUsuario "+ex);
        }finally{
            this.Cerrar();
        }
    }
    
    public Usuario consultarUsuarioPorIdEntero(int idUsuario) throws Exception
    {
        Usuario usuarioUnico = new Usuario();
        try{
            this.Conectar();
            PreparedStatement consultar = this.getConexion().prepareStatement("SELECT * FROM usuario WHERE idusuario=?");
            consultar.setInt(1,idUsuario);
            ResultSet resultado = consultar.executeQuery();
            if(resultado.next()==true){
            usuarioUnico.setIdUsuario(resultado.getInt("idusuario"));
            usuarioUnico.setNombre(resultado.getString("nombre"));
            usuarioUnico.setApellidoPaterno(resultado.getString("apellido_paterno"));
            usuarioUnico.setApellidoMaterno(resultado.getString("apellido_materno"));
            usuarioUnico.setCorreo(resultado.getString("correo"));
            usuarioUnico.setClave(resultado.getString("clave"));
            usuarioUnico.setNivel(resultado.getInt("nivel"));
            usuarioUnico.setIdOficina(new Oficina_solicitanteDAO().buscarOficina(resultado.getInt("id_oficina")));
            usuarioUnico.setRfc(resultado.getString("rfc"));
            usuarioUnico.setId_profesion(new ProfesionDAO().elegirDatoProfesionPorIdProfesion(resultado.getInt("id_profesion")));
            usuarioUnico.setTipoBT(resultado.getString("tipo_bt"));
            usuarioUnico.setEstatus(resultado.getBoolean("estatus"));
            
            }
            resultado.close();
            return usuarioUnico;
        }catch(Exception ex){
            System.out.println("Error en UsuarioDAO -> consultaUsuarioPorIdEntero "+ex);
            throw ex;
        }finally{
           this.Cerrar();
        } 
    }
    
    public List<Usuario> listaUsuario() throws Exception
    {
        List<Usuario> listaUsuario;
        ResultSet resultado;
       try{
           this.Conectar();
           PreparedStatement consulta = this.getConexion().prepareStatement("SELECT * FROM usuario" );
           resultado = consulta.executeQuery();
           listaUsuario = new ArrayList();
           while(resultado.next()){
               Usuario usuarioParaLista = new Usuario();
            usuarioParaLista.setIdUsuario(resultado.getInt("idusuario"));
            usuarioParaLista.setNombre(resultado.getString("nombre"));
            usuarioParaLista.setApellidoPaterno(resultado.getString("apellido_paterno"));
            usuarioParaLista.setApellidoMaterno(resultado.getString("apellido_materno"));
            usuarioParaLista.setCorreo(resultado.getString("correo"));
            usuarioParaLista.setClave(resultado.getString("clave"));
            usuarioParaLista.setNivel(resultado.getInt("nivel"));
            usuarioParaLista.setIdOficina(new Oficina_solicitanteDAO().buscarOficina(resultado.getInt("id_oficina")));
            usuarioParaLista.setRfc(resultado.getString("rfc"));
            usuarioParaLista.setId_profesion(new ProfesionDAO().elegirDatoProfesionPorIdProfesion(resultado.getInt("id_profesion")));
            usuarioParaLista.setTipoBT(resultado.getString("tipo_bt"));
            usuarioParaLista.setEstatus(resultado.getBoolean("estatus"));
            usuarioParaLista.setConcatenar();
            listaUsuario.add(usuarioParaLista);
            
           }
   
       }catch(Exception ex){
           System.out.println("Eror en UsuarioDAO -> listaUsuarioDepartamento "+ex);
           throw ex;
       }finally{
           this.Cerrar();
       }
       return listaUsuario;
    }
}
