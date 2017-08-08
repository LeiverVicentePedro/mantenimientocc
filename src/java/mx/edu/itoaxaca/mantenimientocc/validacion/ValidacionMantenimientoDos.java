/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.validacion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;




@FacesValidator("validadoresLetrasNumEspacioPuntoGuion")
public class ValidacionMantenimientoDos implements Validator {
    private static final String Validador=
            "[A-Za-z0-9.-\\u00e1\\u00e9\\u00ed\\u00f3\\u00fa\\u00c1\\u00c9\\u00cd\\u00d3\\u00da\\u00f1\\u00d1]+"+"[ \\t\\n\\x0b\\r\\f]*"+
            "[A-Za-z0-9.-\\u00e1\\u00e9\\u00ed\\u00f3\\u00fa\\u00c1\\u00c9\\u00cd\\u00d3\\u00da\\u00f1\\u00d1]*"+"[ \\t\\n\\x0b\\r\\f]*"+
            "[A-Za-z0-9.-\\u00e1\\u00e9\\u00ed\\u00f3\\u00fa\\u00c1\\u00c9\\u00cd\\u00d3\\u00da\\u00f1\\u00d1]*"+"[ \\t\\n\\x0b\\r\\f]*"+
            "[A-Za-z0-9.-\\u00e1\\u00e9\\u00ed\\u00f3\\u00fa\\u00c1\\u00c9\\u00cd\\u00d3\\u00da\\u00f1\\u00d1]*"+"[ \\t\\n\\x0b\\r\\f]*"+
            "[A-Za-z0-9.-\\u00e1\\u00e9\\u00ed\\u00f3\\u00fa\\u00c1\\u00c9\\u00cd\\u00d3\\u00da\\u00f1\\u00d1]*"+"[ \\t\\n\\x0b\\r\\f]*"+
            "[A-Za-z-0-9.-\\u00e1\\u00e9\\u00ed\\u00f3\\u00fa\\u00c1\\u00c9\\u00cd\\u00d3\\u00da\\u00f1\\u00d1]*"+"[ \\t\\n\\x0b\\r\\f]*"+
            "[A-Za-z-0-9.-\\u00e1\\u00e9\\u00ed\\u00f3\\u00fa\\u00c1\\u00c9\\u00cd\\u00d3\\u00da\\u00f1\\u00d1]*"+"[ \\t\\n\\x0b\\r\\f]*"+
            "[A-Za-z-0-9.-\\u00e1\\u00e9\\u00ed\\u00f3\\u00fa\\u00c1\\u00c9\\u00cd\\u00d3\\u00da\\u00f1\\u00d1]*"+"[ \\t\\n\\x0b\\r\\f]*"+
            "[A-Za-z-0-9.-\\u00e1\\u00e9\\u00ed\\u00f3\\u00fa\\u00c1\\u00c9\\u00cd\\u00d3\\u00da\\u00f1\\u00d1]*"+"[ \\t\\n\\x0b\\r\\f]*"+
            "[A-Za-z-0-9.-\\u00e1\\u00e9\\u00ed\\u00f3\\u00fa\\u00c1\\u00c9\\u00cd\\u00d3\\u00da\\u00f1\\u00d1]*";
    
    //representacion compilada de una expresion regular
    private Pattern patron;
    //objeto que se crea apartir de un patron
    private Matcher objetoPatron;
    
    public ValidacionMantenimientoDos (){
        patron=Pattern.compile(Validador); 
   }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        objetoPatron=patron.matcher(value.toString());
        if(!objetoPatron.matches())
        {
            FacesMessage mensaje=new FacesMessage("Valor Invalido");
            mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(mensaje);
        }
       }
    
    
}

