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
        ("INSERT INTO niveles_internet (id_catalogo_niveles,fecha,tipo_solicitud,tipo_equipo,solicita,justificacion)"
                + " values(?,?,?,?,?,?)");
            consulta.setInt(1,niveles_Internetregistra.getId_catalogo_niveles().getIdcatalogo_niveles());
            consulta.setDate(2, (Date) niveles_Internetregistra.getFecha());
            consulta.setString(3,niveles_Internetregistra.getTipo_solicitud());
            consulta.setString(4,niveles_Internetregistra.getTipo_equipo());
            consulta.setInt(5,niveles_Internetregistra.getSolicita().getIdUsuario());
            consulta.setString(6, niveles_Internetregistra.getJustificacion());
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
            PreparedStatement consulta = this.getConexion().prepareCall("SELECT * FROM niveles_internet");
            resultadoList = consulta.executeQuery();
            lista = new ArrayList();
            while (resultadoList.next()) {
                Niveles_internet niveles = new Niveles_internet();

                niveles.setIdniveles_internet(resultadoList.getInt("idniveles_internet"));
                niveles.setFolio(resultadoList.getString("folio"));
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
                niveles.setVisto_bueno(new UsuarioDAO().consultarUsuarioPorIdEntero(resultadoList.getInt("visto_bueno")));
                niveles.setCorreo_solicita(resultadoList.getString("correo_solicita"));
                niveles.setCorreo_autoriza(resultadoList.getString("correo_autoriza"));
                niveles.setConfiguro(resultadoList.getString("configuro"));
                niveles.setIp(resultadoList.getString("ip"));
                niveles.setJustificacion(resultadoList.getString("justificacion"));
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
    
    public void modificarNiveles (Niveles_internet nivelesModificar) throws Exception{
          
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("UPDATE niveles_internet SET folio=?,id_catalogo_niveles=?,modelo_equipo=?,mac=?,so=?,fecha=?,edificio=?,nivel_edificio=?,tipo_solicitud=?,vlan=?,conexion=?,tipo_equipo=?,solicita=?,autoriza=?,visto_bueno=?,correo_solicita=?,correo_autoriza=?,configuro=?,ip=?,justificacion WHERE idniveles_internet=?");
             consulta.setString(1, nivelesModificar.getFolio());
            consulta.setInt(2,nivelesModificar.getId_catalogo_niveles().getIdcatalogo_niveles());
            consulta.setString(3,nivelesModificar.getModelo_equipo());
            consulta.setString(4,nivelesModificar.getMac());
            consulta.setString(5,nivelesModificar.getSo());
            consulta.setDate(6, (Date) nivelesModificar.getFecha());
            consulta.setString(7,nivelesModificar.getEdificio());
            consulta.setString(8,nivelesModificar.getNivel_edificio());
            consulta.setString(9,nivelesModificar.getTipo_solicitud());
            consulta.setInt(10,nivelesModificar.getVlan());
            consulta.setString(11,nivelesModificar.getConexion());
            consulta.setString(12,nivelesModificar.getTipo_equipo());
            consulta.setInt(13,nivelesModificar.getSolicita().getIdUsuario());
            consulta.setInt(14,nivelesModificar.getAutoriza().getIdUsuario());
            consulta.setInt(15,nivelesModificar.getVisto_bueno().getIdUsuario());
            consulta.setString(16,nivelesModificar.getCorreo_solicita());
            consulta.setString(17,nivelesModificar.getCorreo_autoriza());
            consulta.setString(18,nivelesModificar.getConfiguro());
            consulta.setString(19,nivelesModificar.getIp());
            consulta.setString(20, nivelesModificar.getJustificacion());
           
            consulta.executeUpdate();
        }
        catch(Exception e){
          System.out.println("error en EquipoDao metodo Modificar"+e);
        }
        finally{
           this.Cerrar();
        }
    }  
    
}
