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
import mx.edu.itoaxaca.mantenimientocc.modelo.Preventivo;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;

/**
 *
 * @author leiver
 */
public class PreventivoDAO extends Conexion {

    public void registrarPreventivo(Preventivo objetoPreventivo) throws Exception {
        try {
            this.Conectar();
            PreparedStatement insertar = this.getConexion().prepareStatement("INSERT INTO preventivo (id_periodo, año, fecha_elaboracion, id_usuario_personal, folio, aprobo) VALUES(?,?,?,?,?,?)");
            insertar.setInt(1, objetoPreventivo.getId_periodo().getIdperiodo_semestral());
            insertar.setString(2, objetoPreventivo.getAño());
            insertar.setDate(3, (java.sql.Date) objetoPreventivo.getFecha_elaboracion());
            insertar.setInt(4, objetoPreventivo.getId_usuario_personal().getIdUsuario());
            insertar.setString(5, objetoPreventivo.getFolio());
            insertar.setString(6, objetoPreventivo.getAprobo());
            insertar.executeUpdate();

        } catch (Exception ex) {
            System.out.println("Error en PreventivoDAO -> RegistrarPreventivo " + ex);
        } finally {
            this.Cerrar();
        }
    }

    public void modificarPreventivo(Preventivo objetoPreventivo) throws Exception {
        try {
            this.Conectar();
            PreparedStatement modificar = this.getConexion().prepareStatement("UPDATE preventivo set id_periodo=?, año=?, fecha_elaboracion=?, id_usuario_persona=?, folio=?, aprobo=? where idpreventivo=?");
            modificar.setInt(1, objetoPreventivo.getId_periodo().getIdperiodo_semestral());
            modificar.setString(2, objetoPreventivo.getAño());
            modificar.setDate(3, (java.sql.Date) objetoPreventivo.getFecha_elaboracion());
            modificar.setInt(4, objetoPreventivo.getId_usuario_personal().getIdUsuario());
            modificar.setString(5, objetoPreventivo.getFolio());
            modificar.setString(6, objetoPreventivo.getAprobo());
            modificar.setInt(7, objetoPreventivo.getIdPreventivo());
            modificar.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Error en PreventivoDAO -> modificarPreventivo");
        } finally {
            this.Cerrar();
        }
    }

    public Preventivo buscarPreventivo(Preventivo prev) throws Exception {
        try {
            this.Conectar();
            Preventivo preventivo = new Preventivo();
            ResultSet resultado;
            PreparedStatement consultar = this.getConexion().prepareStatement("SELECT * FROM preventivo WHERE folio=?");
            consultar.setString(1, prev.getFolio());
            resultado = consultar.executeQuery();
            if (resultado.next() == true) {
                preventivo.setIdPreventivo(resultado.getInt("idpreventivo"));
                preventivo.setId_periodo(new Periodo_semestralDAO().elegirDatoPeriodoPorId(resultado.getInt("id_periodo")));
                preventivo.setAño(resultado.getString("año"));
                preventivo.setFecha_elaboracion(resultado.getDate("fecha_elaboracion"));
                preventivo.setId_usuario_personal(new UsuarioDAO().consultarUsuarioPorIdEntero(resultado.getInt("id_usuario_personal")));
                preventivo.setFolio(resultado.getString("folio"));   
                preventivo.setAprobo(resultado.getString("aprobo"));

            }
            return preventivo;
        } catch (Exception ex) {
            System.out.println("Error en PreventivoDAO -> buscarPreventivo " + ex);
            throw ex;
        } finally {
            this.Cerrar();
        }
    }

    public int ultimoIdInsertado() throws Exception {
        try {
            int valor = 0;
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("Select max(idpreventivo) as valor from preventivo");
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next() == true) {
                valor = resultado.getInt("valor");
            }

            return valor;
        } catch (Exception ex) {
            System.out.println("Error en PreventivoDAO -> ultimoIdInsertado " + ex);
            throw ex;
        } finally {
            this.Cerrar();
        }
    }

    public List<Preventivo> listarPreventivo(Usuario usuario) throws Exception {
        try {
            List<Preventivo> listaPreventivo = new ArrayList();
            this.Conectar();
            ResultSet resultado;
            PreparedStatement consulta = this.getConexion().prepareStatement("SELECT * FROM preventivo WHERE id_usuario_personal=?");
            consulta.setInt(1, usuario.getIdUsuario());
            resultado = consulta.executeQuery();
            while (resultado.next()) {
                Preventivo preventivo = new Preventivo();
                preventivo.setIdPreventivo(resultado.getInt("idpreventivo"));
                preventivo.setId_periodo(new Periodo_semestralDAO().elegirDatoPeriodoPorId(resultado.getInt("id_periodo")));
                preventivo.setAño(resultado.getString("año"));
                preventivo.setFecha_elaboracion(resultado.getDate("fecha_elaboracion"));
                preventivo.setId_usuario_personal(new UsuarioDAO().consultarUsuarioPorIdEntero(resultado.getInt("id_usuario_personal")));
                preventivo.setFolio(resultado.getString("folio"));
                preventivo.setAprobo(resultado.getString("aprobo"));
                
                listaPreventivo.add(preventivo);
            }
            return listaPreventivo;
        } catch (Exception ex) {
            System.out.println("Error en PreventivoDAO -> listarPreventivo " + ex);
            throw ex;
        } finally {
            this.Cerrar();
        }
    }
}
