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
            PreparedStatement inserta = this.getConexion().prepareStatement("INSERT INTO usuario (nombre, apellido_materno, apellido_paterno,correo, clave, nivel,id_oficina, rfc, id_profesion, tipo_bt, estatus, fecha_nacimiento)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
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
            inserta.setDate(12, new java.sql.Date(usuarioRegistrar.getFecha_nacimiento().getTime()));
            
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
            usuarioUnico.setFecha_nacimiento(resultado.getDate("fecha_nacimiento"));
            usuarioUnico.setConcatenar();
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
    
    public Usuario consultarUsuarioPorRFC(String rfc) throws Exception{
        Usuario usuario = new Usuario();
        ResultSet resultado;
        try{
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("SELECT * FROM usuario WHERE rfc=?");
            consulta.setString(1,rfc);
            resultado = consulta.executeQuery();
            if(resultado.next()==true){
                usuario.setIdUsuario(resultado.getInt("idusuario"));
            usuario.setNombre(resultado.getString("nombre"));
            usuario.setApellidoPaterno(resultado.getString("apellido_paterno"));
            usuario.setApellidoMaterno(resultado.getString("apellido_materno"));
            usuario.setCorreo(resultado.getString("correo"));
            usuario.setClave(resultado.getString("clave"));
            usuario.setNivel(resultado.getInt("nivel"));
            usuario.setIdOficina(new Oficina_solicitanteDAO().buscarOficina(resultado.getInt("id_oficina")));
            usuario.setRfc(resultado.getString("rfc"));
            usuario.setId_profesion(new ProfesionDAO().elegirDatoProfesionPorIdProfesion(resultado.getInt("id_profesion")));
            usuario.setTipoBT(resultado.getString("tipo_bt"));
            usuario.setEstatus(resultado.getBoolean("estatus"));
            usuario.setFecha_nacimiento(resultado.getDate("fecha_nacimiento"));
            usuario.setConcatenar();
            }
            
        }catch(Exception ex){
            System.out.println("Error en UsuarioDAO -> consultarUsuarioPorRFC "+ex);
        }finally{
            this.Cerrar();
        }
        return usuario;
    }
    public Usuario consultarUsuarioPorId(Usuario usuario) throws Exception //Se reutiliza para buscar el id del Usuario que se agregara en la tabla empleado_periodo
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
            usuarioUnico.setFecha_nacimiento(resultado.getDate("fecha_nacimiento"));
            usuarioUnico.setConcatenar();
            
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
    
    public List<Usuario> listaUsuarioDepartamento(Usuario usuario) throws Exception//usada unicamente para la vista usuario
    {
        List<Usuario> listaUsuarioDepartamento = null;
        
        ResultSet resultado;
        System.out.println("usuario: "+usuario.getIdOficina().getIdOficinaSolicitante());
       try{
           this.Conectar();
           PreparedStatement consulta = this.getConexion().prepareStatement("SELECT * FROM usuario WHERE id_oficina=? AND nivel not like '1%'");
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
            usuarioParaLista.setFecha_nacimiento(resultado.getDate("fecha_nacimiento"));
            usuarioParaLista.setConcatenar();
            
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
    
    public List<Usuario> listaUsuarioDepartamentoEnAsignacion(Usuario usuario) throws Exception//usada en la asignacion vista
    {
        List<Usuario> listaUsuarioDepartamento = null;
        
        ResultSet resultado;
        System.out.println("usuario: "+usuario.getIdOficina().getIdOficinaSolicitante());
       try{
           this.Conectar();
           PreparedStatement consulta = this.getConexion().prepareStatement("SELECT * FROM usuario WHERE (id_oficina=? and estatus = true) and not nivel like '%1%'");
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
            usuarioParaLista.setFecha_nacimiento(resultado.getDate("fecha_nacimiento"));
            usuarioParaLista.setConcatenar();
            
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
            PreparedStatement consulta = this.getConexion().prepareStatement("UPDATE usuario SET  rfc=?, nombre=?, apellido_paterno=?, apellido_materno=?, correo=?, clave=?, nivel=?, id_oficina=?, id_profesion=?, tipo_bt=?, estatus=?, fecha_nacimiento=? WHERE idusuario=?");
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
            consulta.setDate(12, new java.sql.Date(usuario.getFecha_nacimiento().getTime()));
            consulta.setInt(13, usuario.getIdUsuario());
            consulta.executeUpdate();
        }catch(Exception ex){
            System.out.println("Error en UsuarioDAO -> modificarUsuario "+ex);
        }finally{
            this.Cerrar();
        }
    }
     public void modificarUsuarioRFC(Usuario usuario) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("UPDATE usuario SET  rfc=UPPER(rfc) WHERE idusuario=?");
            consulta.setString(1, usuario.getRfc());
            consulta.executeUpdate();
        }catch(Exception ex){
            System.out.println("Error en UsuarioRFCDAO -> rfcUsuario "+ex);
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
            usuarioUnico.setFecha_nacimiento(resultado.getDate("fecha_nacimiento"));
            
            usuarioUnico.setConcatenar();
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
    
   /* public List<Usuario> listaUsuario() throws Exception
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
    */
    //para clasificar los usuarios de nivel tres
    public List<Usuario> listaUsuarioNivelTres(Usuario usuarioNivelTres) throws Exception
    {
        List<Usuario> listaUsuario;
        ResultSet resultado;
       try{
           this.Conectar();
           PreparedStatement consulta = this.getConexion().prepareStatement("SELECT * FROM usuario where nivel=3 and id_oficina=?" );
           consulta.setInt(1,usuarioNivelTres.getIdOficina().getIdOficinaSolicitante());
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
            usuarioParaLista.setFecha_nacimiento(resultado.getDate("fecha_nacimiento"));
            usuarioParaLista.setConcatenar();
            listaUsuario.add(usuarioParaLista);
            
           }
   
       }catch(Exception ex){
           System.out.println("Eror en UsuarioDAO -> listaUsuarioNivelTres "+ex);
           throw ex;
       }finally{
           this.Cerrar();
       }
       return listaUsuario;
    }
    
     public List<Usuario> listaUsuarioNivelUno() throws Exception
    {
        List<Usuario> listaUsuario;
        ResultSet resultado;
       try{
           this.Conectar();
           PreparedStatement consulta = this.getConexion().prepareStatement("SELECT * FROM usuario where nivel like '1%'" );
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
            usuarioParaLista.setFecha_nacimiento(resultado.getDate("fecha_nacimiento"));
            usuarioParaLista.setConcatenar();
            listaUsuario.add(usuarioParaLista);
            
           }
   
       }catch(Exception ex){
           System.out.println("Eror en UsuarioDAO -> listaUsuarioNivelTres "+ex);
           throw ex;
       }finally{
           this.Cerrar();
       }
       return listaUsuario;
    }
    
    
    public Usuario elegirDatoUsuarioBaja(Usuario usuario) throws Exception{
        Usuario usuariodos=null;
        ResultSet resultadoset;
        
        try{
            this.Conectar();
             PreparedStatement consulta= this.getConexion().prepareStatement("SELECT * FROM usuario WHERE idusuario=?");
            consulta.setInt(1, usuario.getIdUsuario());
            resultadoset = consulta.executeQuery();
            while(resultadoset.next())
            {
              usuariodos= new Usuario();
            usuariodos.setIdUsuario(resultadoset.getInt("idusuario"));
            usuariodos.setNombre(resultadoset.getString("nombre"));
            usuariodos.setApellidoPaterno(resultadoset.getString("apellido_paterno"));
            usuariodos.setApellidoMaterno(resultadoset.getString("apellido_materno"));
            usuariodos.setCorreo(resultadoset.getString("correo"));
            usuariodos.setClave(resultadoset.getString("clave"));
            usuariodos.setNivel(resultadoset.getInt("nivel"));
            usuariodos.setIdOficina(new Oficina_solicitanteDAO().buscarOficina(resultadoset.getInt("id_oficina")));
            usuariodos.setRfc(resultadoset.getString("rfc"));
            usuariodos.setId_profesion(new ProfesionDAO().elegirDatoProfesionPorIdProfesion(resultadoset.getInt("id_profesion")));
            usuariodos.setTipoBT(resultadoset.getString("tipo_bt"));
            usuariodos.setEstatus(resultadoset.getBoolean("estatus"));
            usuariodos.setFecha_nacimiento(resultadoset.getDate("fecha_nacimiento"));
            usuariodos.setConcatenar();
            }
        }
        catch(Exception e){
           throw e; 
        }
        finally{
           this.Cerrar();
        }
        return usuariodos;
    }
}
