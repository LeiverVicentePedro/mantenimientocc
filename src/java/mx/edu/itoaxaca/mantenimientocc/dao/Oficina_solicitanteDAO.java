/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.dao;

import java.sql.PreparedStatement;
import java.util.List;
import mx.edu.itoaxaca.mantenimientocc.conexion.Conexion;
import mx.edu.itoaxaca.mantenimientocc.modelo.Oficina_solicitante;

/**
 *
 * @author leiver
 */
public class Oficina_solicitanteDAO extends Conexion{
    
    public void registrarDepartamento(Oficina_solicitante oficinaregistra) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("INSERT INTO oficinas_solicitantes (nombre_oficina, id_departamento,extencion,estatus) values(?,?,?,?)");
            consulta.setString(1, oficinaregistra.getNombreOficina());
            consulta.setInt(2,oficinaregistra.getIdDepartamento().getIddepartamento());
            consulta.setInt(3,oficinaregistra.getExtencion());
            consulta.setBoolean(4,oficinaregistra.getEstatus());
            
            consulta.executeUpdate();
        }
        catch(Exception e){
           System.out.println("error en DAO"); 
        }
        finally{
           this.Cerrar();
        }
    }
}
