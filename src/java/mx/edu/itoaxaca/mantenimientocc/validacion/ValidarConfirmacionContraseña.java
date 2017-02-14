/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.validacion;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author leiver
 */
@FacesValidator("validaContraseña")
public class ValidarConfirmacionContraseña implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent component, Object value)
        throws ValidatorException{
            String attribute = (String) component.getAttributes().get("contraseñaUsuario");
            if (!value.equals(attribute)) {
                FacesMessage message = new FacesMessage();
                message.setSummary("La contraseña no coincide");
                message.setSeverity(FacesMessage.SEVERITY_WARN);
                throw new ValidatorException(message);
            }
        }

    
}
