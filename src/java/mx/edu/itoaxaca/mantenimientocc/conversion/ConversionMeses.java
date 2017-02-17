/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.conversion;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author leiver
 */
@FacesConverter ("conversionMeses")
public class ConversionMeses implements Converter{
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
    
     return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
          String mes="";
          int mesEntero;
        if(value!=null)
        {
          
            mesEntero=Integer.parseInt(String.valueOf(value));
          switch(mesEntero){
                case 1:
                    mes="ENERO";
                    break;
                case 2:
                    mes="FEBRERO";
                    break;
                    
                case 3:
                    mes="";
                    break;
                    
                case 4:
                    mes="ABRIL";
                    break;
                
                case 5:
                    mes="MAYO";
                    break;
                    
                case 6:
                    mes="JUNIO";
                    break;
                
                case 7:
                    mes="JULIO";
                    break;
                    
                case 8:
                    mes="AGOSTO";
                    break;
                  
               case 9:
                    mes="SEPTIEMBRE";
                    break;
                    
                case 10:
                    mes="OCTUBRE";
                    break;
                  
                case 11:
                    mes="NOVIEMBRE";
                    break;
                    
                case 12:
                    mes="DICIEMBRE";
                    break;
            
          }
            
            
        }
        return mes;
        
        
        
        
    }
    
}
