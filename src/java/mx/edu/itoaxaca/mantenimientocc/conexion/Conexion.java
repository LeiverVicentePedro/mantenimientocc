/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author leiver
 */
public class Conexion {
    
    private Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
    
    public void Conectar() throws Exception{
        try{
      Class.forName("com.mysql.jdbc.Driver");
      conexion = DriverManager.getConnection("jdbc:mysql://sql37.hostinger.mx:3306/u135348475_mant?user=u135348475_root&password=administrador");
      //conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mantenimientobd?user=root&password=admin");
      
    }catch(Exception ex){
        System.out.println("Error de Conexion" +ex+" ==============================");
        throw ex;
    }
    }
    
    public void Cerrar() throws Exception{
        try{
        if(conexion != null){
            if(conexion.isClosed()==false){
                conexion.close();
            }
        }
        }catch(Exception ex){
            throw ex;
        }
    }
}
