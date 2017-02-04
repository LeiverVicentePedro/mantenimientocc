/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.validacion;

/**
 *
 * @author Jerusalen
 */
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;




@FacesValidator("validadoresRFC")
public class ValidacionMantenimientoRFC implements Validator {
    private static final String Validador="[A-Za-z,Ñ,&]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Za-z,0-9]?[A-Za-z,0-9]?[0-9,A-Za-z]?";
            
    //representacion compilada de una expresion regular
    private Pattern patron;
    //objeto que se crea apartir de un patron
    private Matcher objetoPatron;
    
    public ValidacionMantenimientoRFC(){
        patron=Pattern.compile(Validador); 
   }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        objetoPatron=patron.matcher(value.toString());
        if(!objetoPatron.matches())
        {
            FacesMessage mensaje=new FacesMessage("Valor Invalido En RFC");
            mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(mensaje);
        }
       }
    
    public boolean comparaRFC(String nombre,String apPaterno,String apMaterno,Date cumpleaños){
            String RFCSinHomoclave="";
            System.out.println(cumpleaños+"");
            char nom = nombre.charAt(0);
            String apP=apPaterno.substring(0, 2);
            char apMat = apMaterno.charAt(0);
            int año = cumpleaños.getYear();
            int mes = cumpleaños.getMonth()+1;
            int dia = cumpleaños.getDate();
            RFCSinHomoclave=apP+apMat+nom+año;
            if(mes<10)
                RFCSinHomoclave+="0"+mes;
            
            else
                RFCSinHomoclave+=mes;
            
            if(dia<10)
                RFCSinHomoclave+="0"+dia;
            
            else
                RFCSinHomoclave+=dia;
            
            System.out.println(RFCSinHomoclave);
        return false;
    }
}


