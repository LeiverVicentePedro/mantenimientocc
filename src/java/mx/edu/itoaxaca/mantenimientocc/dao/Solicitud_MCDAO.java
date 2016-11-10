/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import mx.edu.itoaxaca.mantenimientocc.conexion.Conexion;
import mx.edu.itoaxaca.mantenimientocc.modelo.Solicitud_MC;

/**
 *
 * @author leiver
 */
public class Solicitud_MCDAO extends Conexion{
    
    public void generarSolicitudMC(Solicitud_MC solicitudmc) throws Exception{
        try{
            this.Conectar();
            PreparedStatement inserta = this.getConexion().prepareStatement("INSERT INTO solicitud_mc(id_usuario, folio, fecha, id_departamento, otro_problema) VALUES(?,?,?,?,?)");
            inserta.setInt(1, solicitudmc.getId_usuario().getIdUsuario());
            inserta.setString(2, solicitudmc.getFolio());
            inserta.setDate(3, (Date) solicitudmc.getFecha());
            inserta.setInt(4,solicitudmc.getId_departamento().getIddepartamento());
            inserta.setString(5, solicitudmc.getOtroProblema());
            inserta.executeUpdate();
        }catch(Exception ex){
            System.out.println("Error en Solicitud_MCDAO -> generarSolicitudMC "+ex);
            throw ex;
        }finally{
            this.Cerrar();
        }
    }
    
    public int indiceDeSolicitud(int idDepartamento) throws Exception{
        ResultSet resultado;
        int numeroParaFolio=0;
        try{
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("SELECT COUNT(idsolicitud_mc) AS numSolicitud FROM solicitud_mc WHERE id_departamento=?");
            consulta.setInt(1, idDepartamento);
            resultado = consulta.executeQuery();
            if(resultado.next()==true){
                numeroParaFolio = resultado.getInt("numSolicitud");
            }
        }catch(Exception ex){
            System.out.println("Error en Solicitud_MCDAO -> indiceDeSolicitud "+ex);
            throw ex;
        }finally{
            this.Cerrar();
        }
        return numeroParaFolio+1;
    }
    
}
