/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.dao;

import java.sql.PreparedStatement;
import mx.edu.itoaxaca.mantenimientocc.conexion.Conexion;
import mx.edu.itoaxaca.mantenimientocc.modelo.Departamento;


/**
 *
 * @author Jerusalen
 */
public class DepartamentoDAO extends Conexion{
    
    public void registrarDepartamento(Departamento departamentoregistra) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("INSERT INTO departamento (clave_departamento,nombre_departamento,id_area,estatus) values(?,?,?,?)");
            consulta.setString(1, departamentoregistra.getClaveDepartamento());
            consulta.setString(2,departamentoregistra.getNombreDepartamento());
            consulta.setInt(3,departamentoregistra.getArea().getIdarea());
            consulta.setBoolean(4,departamentoregistra.getEstatus());
            
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
