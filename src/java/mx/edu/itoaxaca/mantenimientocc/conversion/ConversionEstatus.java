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

@FacesConverter ("conversionEstatus")
public class ConversionEstatus implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
    
     return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
          String estatus="";
        if(value!=null)
        {
          
            estatus=String.valueOf(value);
          switch(estatus){
                case "true":
                    estatus="ACTIVO";
                    break;
                case "false":
                    estatus="INACTIVO";
                    break;
            }
            
            
        }
        return estatus;
 
    }
    
}
