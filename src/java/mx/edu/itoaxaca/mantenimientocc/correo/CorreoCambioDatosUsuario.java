/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.correo;

import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;

/**
 *
 * @author leiver
 */
public class CorreoCambioDatosUsuario extends ConexionCorreo implements Runnable{
    private Usuario usuario;

    public CorreoCambioDatosUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public void run() {
       try{
           String asunto = "Cambio de Informacion SIMAPRECO del TEC-OAX";
            String mensaje ="<center><h1>INSTITUTO TECNOL&Oacute;GICO DE OAXACA<h1></center><br/>"+ 
                    "<center><h3>SISTEMA DE MANTENIMIENTO PREVENTIVO Y/O CORRECTIVO DEL ITO<h3></center><br/>"+
                    "<center>Cambios en su Cuenta</center>"+"\n" + "<div><center><h2>Su Cuenta ha sido <b>Modificada<b> de manera satisfactoria con los siguientes datos:<h2></center></div>\n"
                    + "<br/><center>DATOS DE SU CUENTA</center><br/>"+"\n"
                    + "<center>Nombre: " +"<h3>"+usuario.getNombre()+" "+usuario.getApellidoPaterno()+" "+usuario.getApellidoMaterno()+"</h3>"+ "</center>"+"\n"
                    + "<center>Cuenta de Acceso: " +"<h3>"+usuario.getCorreo() +"</h3>"+ "</center>"+"\n"
                    + "<center>Clave de Acceso: <h3>"+usuario.getClave()+"</h3></center>"
                    + "<center>Con esta informaci&oacute;n podr&aacute; acceder a su cuenta en el sistema a trav&eacute;s del siguiente enlace:<br/></center>"
                    +"<center><b><a href="+"\"http://187.154.40.169:8080/mantenimientocc/\" style=\" color:blue;\"><b>SISTEMA DE MANTENIMIENTO PREVENTIVO Y/O CORRECTIVO DEL ITO<b/>"+"</a></b></center>"
                    + "<br/><br/>"
                    + "<center><div><p style=\"font-size: 10pt; line-height:100%;\">Tecnol&oacute;gico Nacional de M&eacute;xico / Instituto Tecnol&oacute;gico de Oaxaca<br/>"
                    + "Avenida Ing. V&iacute;ctor Bravo Ahuja<br/>"
                    + "No. 125 Esquina Calzada Tecnol&oacute;gico, C.P. 68030<br/>"
                    + "Tels.(951) 501 50 16, Correo electronico SIMAPRECO: sistema.mantenimiento@itoaxaca.edu.mx<br/>"
                    + "Derechos Reservados.</p></center>";
            
             MimeMessage crearCorreo = new MimeMessage(this.crearSesion(this.setup()));
             System.out.println("Correo confirmacikon :"+usuario.getCorreo());
            crearCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(usuario.getCorreo()));
            crearCorreo.setSubject(asunto);
            crearCorreo.setContent(mensaje,"text/html");
            
//Enviamos el Mensaje
            Transport.send(crearCorreo);
            
       }catch(Exception ex){
            System.out.println("Fallo en Correo :correoCambioDatosUsuario "+ex);
       }
    }
    
}
