/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.dao;

import java.sql.PreparedStatement;
import mx.edu.itoaxaca.mantenimientocc.conexion.Conexion;
import mx.edu.itoaxaca.mantenimientocc.modelo.Empleado_periodo;

/**
 *
 * @author Jerusalen
 */
public class Empleado_periodoDAO extends Conexion {
    
    public void registrarEmpleado_periodo(Empleado_periodo empleado_periodo) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("INSERT INTO empleado_periodo (id_periodo,año,id_usuario_personal) values(?,?,?)");
          
            consulta.setInt(1,empleado_periodo.getId_periodo().getIdperiodo_semestral());
            consulta.setString(2,empleado_periodo.getAño());
            consulta.setInt(3,empleado_periodo.getId_usuario_personal().getIdUsuario());
           
            consulta.executeUpdate();
        }
        catch(Exception e){
        System.out.println("error en Empleado_Periodo DAO -->RegistrarEMPLEADO_PERIODO"+"\n"+e);
       // System.out.println("error en DAO"); 
        }
     finally{
           this.Cerrar();
        }
    }
    
    
    
    
    
}
