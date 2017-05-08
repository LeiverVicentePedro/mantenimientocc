/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import mx.edu.itoaxaca.mantenimientocc.dao.ConfiguracionServicioSocialDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.ConfiguracionServicioSocial;

/**
 *
 * @author leiver
 */
@ManagedBean
@SessionScoped
public class ConfiguracionServicioSocialBEAN implements Serializable{
    
    ConfiguracionServicioSocial servicioSocial = new ConfiguracionServicioSocial();
    ConfiguracionServicioSocialDAO servicioSocialDao = new ConfiguracionServicioSocialDAO();
    
    public ConfiguracionServicioSocial getServicioSocial() {
        return servicioSocial;
    }

    public void setServicioSocial(ConfiguracionServicioSocial servicioSocial) {
        this.servicioSocial = servicioSocial;
    }
    
    public void registrarServicio(){
        try{
            
            servicioSocialDao.agregarConfiguracion(servicioSocial);
        }catch(Exception ex){
            System.out.println("Error en ConfiguracionServicioSocialBEAN -> registrarServicio "+ex);
        }
    }
    
    public void modificarServicio(){
        try{
        servicioSocialDao.modificarConfiguracion(servicioSocial);
        }catch(Exception ex){
            System.out.println("Error en ConfiguracionServicioSocialBEAN -> modificarServicio "+ex);
        }
    }
    
    
    public void opcionConfiguracion(){
       ConfiguracionServicioSocial servicioSocialTemporal = new ConfiguracionServicioSocial();
       try{
         servicioSocialTemporal = servicioSocialDao.seleccionarConfiguracion();
         if(servicioSocialTemporal.getIdconfiguracion_servicio_social()== 1){
             modificarServicio();
         }else{
             registrarServicio();
         }
       }catch(Exception ex){
           System.out.println("Error en ConfiguracionServicioSocialBEAN -> opcionConfiguracion "+ex);
       }
      
    }
    
    public void obtenConfiguracion(){
        try{
            servicioSocial = servicioSocialDao.seleccionarConfiguracion();
        }catch(Exception ex){
          System.out.println("Error en ConfiguracionServicioSocialBEAN -> opcionConfiguracion "+ex);
        }
    }
}
