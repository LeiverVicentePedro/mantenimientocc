/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.dao;

import java.sql.PreparedStatement;
import mx.edu.itoaxaca.mantenimientocc.conexion.Conexion;
import mx.edu.itoaxaca.mantenimientocc.modelo.Preventivo;

/**
 *
 * @author leiver
 */
public class PreventivoDAO extends Conexion{
    
    public void registrarPreventivo(Preventivo objetoPreventivo) throws Exception{
        try{
            this.Conectar();
            PreparedStatement insertar = this.getConexion().prepareStatement("INSERT INTO preventivo (id_periodo, año, fecha_elaboracion, fecha_aprobacion, id_usuario_personal, aprobo) VALUES(?,?,?,?,?,?)");
            insertar.setInt(1, objetoPreventivo.getId_periodo().getIdperiodo_semestral());
            insertar.setString(2, objetoPreventivo.getAño());
            insertar.setDate(3, objetoPreventivo.getFecha_elaboracion());
            insertar.setDate(4, objetoPreventivo.getFecha_aprobacion());
            insertar.setInt(5, objetoPreventivo.getId_usuario_personal().getIdUsuario());
            insertar.setString(6, objetoPreventivo.getAprobo());
            insertar.executeUpdate();
            
        }catch(Exception ex){
            System.out.println("Error en PreventivoDAO -> RegistrarPreventivo "+ex);
        }finally{
            this.Cerrar();
        }
    }
    
    public void modificarPreventivo(Preventivo objetoPreventivo) throws Exception{
        try{
            this.Conectar();
            PreparedStatement modificar = this.getConexion().prepareStatement("UPDATE preventivo set id_periodo=?, año=?, fecha_elaboracion=?, fecha_aprobacion=?, id_usuario_persona=?, aprobo=? where idpreventivo=?");
            modificar.setInt(1, objetoPreventivo.getId_periodo().getIdperiodo_semestral());
            modificar.setString(2, objetoPreventivo.getAño());
            modificar.setDate(3, objetoPreventivo.getFecha_elaboracion());
            modificar.setDate(4, objetoPreventivo.getFecha_aprobacion());
            modificar.setInt(5, objetoPreventivo.getId_usuario_personal().getIdUsuario());
            modificar.setString(6, objetoPreventivo.getAprobo());
            modificar.setInt(7, objetoPreventivo.getIdPreventivo());
            modificar.executeUpdate();
        }catch(Exception ex){
            System.out.println("Error en PreventivoDAO -> modificarPreventivo");
        }finally{
            this.Cerrar();
        }
    }
}
