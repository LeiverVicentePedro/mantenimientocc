/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import mx.edu.itoaxaca.mantenimientocc.conexion.Conexion;
import mx.edu.itoaxaca.mantenimientocc.modelo.ConfiguracionServicioSocial;

/**
 *
 * @author leiver
 */
public class ConfiguracionServicioSocialDAO extends Conexion{
    
    public void agregarConfiguracion(ConfiguracionServicioSocial servicio) throws Exception{
        try{
            this.Conectar();
            PreparedStatement inserta = this.getConexion().prepareStatement("INSERT INTO configuracion_servicio_social(horas_servicio, ip_checador) VALUES(?,?)");
            inserta.setInt(1, servicio.getHoras_servicio());
            inserta.setString(2,servicio.getIp_checador());
            inserta.executeUpdate();
        }catch(Exception ex){
            System.out.println("Error en ConfiguracionservicioSocialDAO -> agregarConfiguracion "+ex);
        }finally{
            this.Cerrar();
        }
    }
    
    public void modificarConfiguracion(ConfiguracionServicioSocial servicio) throws Exception{
        try{
            this.Conectar();
            PreparedStatement actualiza = this.getConexion().prepareStatement("UPDATE configuracion_servicio_social SET horas_servicio=?, ip_checador=? WHERE idconfiguracion_servicio_social=1");
            actualiza.setInt(1, servicio.getHoras_servicio());
            actualiza.setString(2, servicio.getIp_checador());
            actualiza.executeUpdate();
        }catch(Exception ex){
            System.out.println("Error en ConfiguracionServicioSocialDAO -> modificarConfiguracion "+ex);
        }finally{
            this.Cerrar();
        }
    }
    
    public ConfiguracionServicioSocial seleccionarConfiguracion() throws Exception{
        ConfiguracionServicioSocial servicioSocial = new ConfiguracionServicioSocial();
        try{
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("SELECT * FROM configuracion_servicio_social WHERE idconfiguracion_servicio_social=1");
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                servicioSocial.setIdconfiguracion_servicio_social(resultado.getInt("idconfiguracion_servicio_social"));
                servicioSocial.setHoras_servicio(resultado.getInt("horas_servicio"));
                servicioSocial.setIp_checador(resultado.getString("ip_checador"));
            }
        }catch(Exception ex){
            System.out.println("Error en ConfiguracionServicioSocialDAO -> seleccionaConfiguracion "+ex);
        }finally{
            this.Cerrar();
        }
        return servicioSocial;
    }
}
