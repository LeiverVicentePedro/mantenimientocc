/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.dao;

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
        }catch(Exception ex){
            
        }finally{
            this.Cerrar();
        }
    }
}
