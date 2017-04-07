/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mx.edu.itoaxaca.mantenimientocc.conexion.Conexion;
import mx.edu.itoaxaca.mantenimientocc.modelo.Niveles_internet;

/**
 *
 * @author Jerusalen
 */
public class Niveles_internetDAO extends Conexion {
    
    public void registrarNivelesInternet(Niveles_internet niveles_Internetregistra) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement
        ("INSERT INTO niveles_internet (id_catalogo_niveles,fecha,tipo_solicitud,tipo_equipo,solicita,correo_solicita,justificacion,estatus_autoriza)"
                + " values(?,?,?,?,?,?,?,?)");
            consulta.setInt(1,niveles_Internetregistra.getId_catalogo_niveles().getIdcatalogo_niveles());
            consulta.setDate(2, (Date) niveles_Internetregistra.getFecha());
            consulta.setString(3,niveles_Internetregistra.getTipo_solicitud());
            consulta.setString(4,niveles_Internetregistra.getTipo_equipo());
            consulta.setInt(5,niveles_Internetregistra.getSolicita().getIdUsuario());
            consulta.setString(6,niveles_Internetregistra.getCorreo_solicita());
            consulta.setString(7, niveles_Internetregistra.getJustificacion());
            consulta.setBoolean(8, false);
            consulta.executeUpdate();
        }
        catch(Exception e){
        System.out.println("error en niveles_InternetDAO -->RegistrarNiveles_Internet"+"\n"+e);
       // System.out.println("error en DAO"); 
        }
     finally{
           this.Cerrar();
        }
    }
    
    public List<Niveles_internet> listarNiveles_internet() throws Exception {
        List<Niveles_internet> lista;
        ResultSet resultadoList;
        try {
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareCall("SELECT * FROM niveles_internet WHERE estatus_autoriza=false");
            resultadoList = consulta.executeQuery();
            lista = new ArrayList();
            while (resultadoList.next()) {
                Niveles_internet niveles = new Niveles_internet();

                niveles.setIdniveles_internet(resultadoList.getInt("idniveles_internet"));
                 niveles.setId_catalogo_niveles(new Catalogo_nivelesDAO().buscarIdCatalogo_niveles(resultadoList.getInt("id_catalogo_niveles")));
                 niveles.setModelo_equipo(resultadoList.getString("modelo_equipo"));
                 niveles.setMac(resultadoList.getString("mac")); 
                 niveles.setSo(resultadoList.getString("so"));
                niveles.setFecha(resultadoList.getDate("fecha"));
                niveles.setEdificio(resultadoList.getString("edificio"));
                niveles.setNivel_edificio(resultadoList.getString("nivel_edificio"));
                niveles.setTipo_solicitud(resultadoList.getString("tipo_solicitud"));
                niveles.setVlan(resultadoList.getInt("vlan"));
                niveles.setConexion(resultadoList.getString("conexion"));
                niveles.setTipo_equipo(resultadoList.getString("tipo_equipo"));
                niveles.setSolicita(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoList.getInt("solicita")));
                niveles.setAutoriza(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoList.getInt("autoriza")));
                niveles.setCorreo_solicita(resultadoList.getString("correo_solicita"));
                niveles.setCorreo_autoriza(resultadoList.getString("correo_autoriza"));
                niveles.setConfiguro(resultadoList.getString("configuro"));
                niveles.setIp(resultadoList.getString("ip"));
                niveles.setJustificacion(resultadoList.getString("justificacion"));
                niveles.setEstatus_autoriza(resultadoList.getBoolean("estatus_autoriza"));
                niveles.setPuerto(resultadoList.getInt("puerto"));
                lista.add(niveles);
            }

        } catch (Exception e) {
            System.out.println("error en orden_internaDao metodo Listar" + e);
            throw e;

        } finally {
            this.Cerrar();
        }
        return lista;
    }
    
     public Niveles_internet elegirDatoNivel(Niveles_internet nivelesInternet) throws Exception{
        Niveles_internet nivelesdos=null;
        ResultSet resultadoset;
        
        try{
            this.Conectar();
             PreparedStatement consulta= this.getConexion().prepareStatement("SELECT * FROM niveles_internet WHERE idniveles_internet=?");
            consulta.setInt(1, nivelesInternet.getIdniveles_internet());
            resultadoset = consulta.executeQuery();
            while(resultadoset.next())
            {
              nivelesdos= new Niveles_internet();
             nivelesdos.setIdniveles_internet(resultadoset.getInt("idniveles_internet"));
                 nivelesdos.setId_catalogo_niveles(new Catalogo_nivelesDAO().buscarIdCatalogo_niveles(resultadoset.getInt("id_catalogo_niveles")));
                 nivelesdos.setModelo_equipo(resultadoset.getString("modelo_equipo"));
                 nivelesdos.setMac(resultadoset.getString("mac")); 
                 nivelesdos.setSo(resultadoset.getString("so"));
                nivelesdos.setFecha(resultadoset.getDate("fecha"));
                nivelesdos.setEdificio(resultadoset.getString("edificio"));
                nivelesdos.setNivel_edificio(resultadoset.getString("nivel_edificio"));
                nivelesdos.setTipo_solicitud(resultadoset.getString("tipo_solicitud"));
                nivelesdos.setVlan(resultadoset.getInt("vlan"));
                nivelesdos.setConexion(resultadoset.getString("conexion"));
                nivelesdos.setTipo_equipo(resultadoset.getString("tipo_equipo"));
                nivelesdos.setSolicita(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoset.getInt("solicita")));
                nivelesdos.setAutoriza(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoset.getInt("autoriza")));
                nivelesdos.setCorreo_solicita(resultadoset.getString("correo_solicita"));
                nivelesdos.setCorreo_autoriza(resultadoset.getString("correo_autoriza"));
                nivelesdos.setConfiguro(resultadoset.getString("configuro"));
                nivelesdos.setIp(resultadoset.getString("ip"));
                nivelesdos.setJustificacion(resultadoset.getString("justificacion"));
                nivelesdos.setEstatus_autoriza(resultadoset.getBoolean("estatus_autoriza"));
                 nivelesdos.setPuerto(resultadoset.getInt("puerto"));
            }
        }
        catch(Exception e){
            System.out.println("Error en Niveles_internetDAO -> elegirDatosNiveles "+e);
           throw e; 
        }
        finally{
           this.Cerrar();
        }
        return nivelesdos;
    }
    
    public void modificarNiveles (Niveles_internet nivelesModificar) throws Exception{
          
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("UPDATE niveles_internet SET id_catalogo_niveles=?,modelo_equipo=?,mac=?,so=?,fecha=?,edificio=?,nivel_edificio=?,tipo_solicitud=?,vlan=?,conexion=?,tipo_equipo=?,solicita=?,autoriza=?,correo_solicita=?,correo_autoriza=?,configuro=?,ip=?,justificacion=?,estatus_autoriza=?,puerto=? WHERE idniveles_internet=?");
            consulta.setInt(1,nivelesModificar.getId_catalogo_niveles().getIdcatalogo_niveles());
            consulta.setString(2,nivelesModificar.getModelo_equipo());
            consulta.setString(3,nivelesModificar.getMac());
            consulta.setString(4,nivelesModificar.getSo());
            consulta.setDate(5, (Date) nivelesModificar.getFecha());
            consulta.setString(6,nivelesModificar.getEdificio());
            consulta.setString(7,nivelesModificar.getNivel_edificio());
            consulta.setString(8,nivelesModificar.getTipo_solicitud());
            consulta.setInt(9,nivelesModificar.getVlan());
            consulta.setString(10,nivelesModificar.getConexion());
            consulta.setString(11,nivelesModificar.getTipo_equipo());
            consulta.setInt(12,nivelesModificar.getSolicita().getIdUsuario());
            consulta.setInt(13,nivelesModificar.getAutoriza().getIdUsuario());
            consulta.setString(14,nivelesModificar.getCorreo_solicita());
            consulta.setString(15,nivelesModificar.getCorreo_autoriza());
            consulta.setString(16,nivelesModificar.getConfiguro());
            consulta.setString(17,nivelesModificar.getIp());
            consulta.setString(18, nivelesModificar.getJustificacion());
            consulta.setBoolean(19, nivelesModificar.getEstatus_autoriza());
            consulta.setInt(20,nivelesModificar.getPuerto());
            consulta.setInt(21,nivelesModificar.getIdniveles_internet());
           
            consulta.executeUpdate();
        }
        catch(Exception e){
          System.out.println("error en NiVELESDao metodo Modificar"+e);
        }
        finally{
           this.Cerrar();
        }
    }  
    
     public List<Niveles_internet> listarNiveles_internetAdministradorRegistra() throws Exception {
        List<Niveles_internet> lista;
        ResultSet resultadoList;
        try {
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareCall("SELECT * FROM niveles_internet WHERE estatus_autoriza=true and (mac IS NULL or mac='')");
            resultadoList = consulta.executeQuery();
            lista = new ArrayList();
            while (resultadoList.next()) {
                Niveles_internet niveles = new Niveles_internet();

                niveles.setIdniveles_internet(resultadoList.getInt("idniveles_internet"));
                 niveles.setId_catalogo_niveles(new Catalogo_nivelesDAO().buscarIdCatalogo_niveles(resultadoList.getInt("id_catalogo_niveles")));
                 niveles.setModelo_equipo(resultadoList.getString("modelo_equipo"));
                 niveles.setMac(resultadoList.getString("mac")); 
                 niveles.setSo(resultadoList.getString("so"));
                niveles.setFecha(resultadoList.getDate("fecha"));
                niveles.setEdificio(resultadoList.getString("edificio"));
                niveles.setNivel_edificio(resultadoList.getString("nivel_edificio"));
                niveles.setTipo_solicitud(resultadoList.getString("tipo_solicitud"));
                niveles.setVlan(resultadoList.getInt("vlan"));
                niveles.setConexion(resultadoList.getString("conexion"));
                niveles.setTipo_equipo(resultadoList.getString("tipo_equipo"));
                niveles.setSolicita(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoList.getInt("solicita")));
                niveles.setAutoriza(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoList.getInt("autoriza")));
                niveles.setCorreo_solicita(resultadoList.getString("correo_solicita"));
                niveles.setCorreo_autoriza(resultadoList.getString("correo_autoriza"));
                niveles.setConfiguro(resultadoList.getString("configuro"));
                niveles.setIp(resultadoList.getString("ip"));
                niveles.setJustificacion(resultadoList.getString("justificacion"));
                niveles.setEstatus_autoriza(resultadoList.getBoolean("estatus_autoriza"));
                niveles.setPuerto(resultadoList.getInt("puerto"));
                lista.add(niveles);
            }

        } catch (Exception e) {
            System.out.println("error en orden_internaDao metodo Listar" + e);
            throw e;

        } finally {
            this.Cerrar();
        }
        return lista;
    }
    
     public List<Niveles_internet> listarNiveles_internetAdministradorModifica() throws Exception {
        List<Niveles_internet> lista;
        ResultSet resultadoList;
        try {
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareCall("SELECT * FROM niveles_internet WHERE estatus_autoriza=true and (mac is not null OR mac<>'')");
            resultadoList = consulta.executeQuery();
            lista = new ArrayList();
            while (resultadoList.next()) {
                Niveles_internet niveles = new Niveles_internet();

                niveles.setIdniveles_internet(resultadoList.getInt("idniveles_internet"));
                 niveles.setId_catalogo_niveles(new Catalogo_nivelesDAO().buscarIdCatalogo_niveles(resultadoList.getInt("id_catalogo_niveles")));
                 niveles.setModelo_equipo(resultadoList.getString("modelo_equipo"));
                 niveles.setMac(resultadoList.getString("mac")); 
                 niveles.setSo(resultadoList.getString("so"));
                niveles.setFecha(resultadoList.getDate("fecha"));
                niveles.setEdificio(resultadoList.getString("edificio"));
                niveles.setNivel_edificio(resultadoList.getString("nivel_edificio"));
                niveles.setTipo_solicitud(resultadoList.getString("tipo_solicitud"));
                niveles.setVlan(resultadoList.getInt("vlan"));
                niveles.setConexion(resultadoList.getString("conexion"));
                niveles.setTipo_equipo(resultadoList.getString("tipo_equipo"));
                niveles.setSolicita(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoList.getInt("solicita")));
                niveles.setAutoriza(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoList.getInt("autoriza")));
                niveles.setCorreo_solicita(resultadoList.getString("correo_solicita"));
                niveles.setCorreo_autoriza(resultadoList.getString("correo_autoriza"));
                niveles.setConfiguro(resultadoList.getString("configuro"));
                niveles.setIp(resultadoList.getString("ip"));
                niveles.setJustificacion(resultadoList.getString("justificacion"));
                niveles.setEstatus_autoriza(resultadoList.getBoolean("estatus_autoriza"));
                niveles.setPuerto(resultadoList.getInt("puerto"));
                lista.add(niveles);
            }

        } catch (Exception e) {
            System.out.println("error en orden_internaDao metodo Listar" + e);
            throw e;

        } finally {
            this.Cerrar();
        }
        return lista;
    }
}
