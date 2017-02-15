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
import mx.edu.itoaxaca.mantenimientocc.modelo.Orden_trabajo;
import mx.edu.itoaxaca.mantenimientocc.modelo.Solicitud_mc;

/**
 *
 * @author leiver
 */
public class CorreoRegistroUsuario extends ConexionCorreo{
    
        public void enviarMensaje(String correo, String clave) {
        try {
            
            
             String asunto = "Registro SIMAPRECO del TEC-OAX";
            String mensaje ="<center><h1>INSTITUTO TECNOL&Oacute;GICO DE OAXACA<h1></center><br/>"+ 
                    "<center><h3>SISTEMA DE MANTENIMIENTO PREVENTIVO Y/O CORRECTIVO DEL ITO<h3></center><br/>"+
                    "<center>Gracias por registrarse</center>"+"\n" + "<div><center><h2>Su Cuenta ha sido creada de manera satisfactoria con los siguientes datos:<h2></center></div>\n"
                    + "<br/><center>DATOS PARA ACCEDER A SU CUENTA</center><br/>"+"\n"
                    + "<center>Cuenta de Acceso: " +"<h3>"+ correo +"</h3>"+ "</center>"+"\n"
                    + "<center>Clave de Acceso: " +"<h3>"+ clave +"</h3>"+ "</center>"+"\n"
                    + "<center>Con esta informaci&oacute;n podr&aacute; acceder a su cuenta en el sistema a trav&eacute;s del siguiente enlace:<br/></center>"
                    +"<center><b><a href="+"\"http://187.154.40.169:8080/mantenimientocc/\" style=\" color:blue;\"><b>SISTEMA DE MANTENIMIENTO PREVENTIVO Y/O CORRECTIVO DEL ITO<b/>"+"</a></b></center>"
                    + "<br/><br/>"
                    + "<center><div><p style=\"font-size: 10pt; line-height:100%;\">Tecnol&oacute;gico Nacional de M&eacute;xico / Instituto Tecnol&oacute;gico de Oaxaca<br/>"
                    + "Avenida Ing. V&iacute;ctor Bravo Ahuja<br/>"
                    + "No. 125 Esquina Calzada Tecnol&oacute;gico, C.P. 68030<br/>"
                    + "Tels.(951) 501 50 16, Correo electronico SIMAPRECO: sistema.mantenimiento@itoaxaca.edu.mx<br/>"
                    + "Derechos Reservados.</p></center>";
            
                     
            MimeMessage crearCorreo = new MimeMessage(this.crearSesion(this.setup()));
            crearCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(correo));
            crearCorreo.setSubject(asunto);
            crearCorreo.setContent(mensaje,"text/html");
            
//Enviamos el Mensaje
            Transport.send(crearCorreo);
        } catch (Exception ex) {
            System.out.println("Error en CorreoRegistroUsuario -> enviarMensaje " + ex);
        }
    }

    public void enviarMensajeRecuperacion(String correo, String clave) {
        try {
            setup();
            String asunto = "Recuperación de Cuenta del Sistema SIMAPRECO del TEC-OAX";
            String mensaje = "<center><h1>INSTITUTO TECNOL&Oacute;GICO DE OAXACA<h1></center><br/>"+
                    "<center><h3>SISTEMA DE MANTENIMIENTO PREVENTIVO Y/O CORRECTIVO DEL ITO<h3></center><br/>"
                    + "<div><center><h2>A Solicitado la Recuperaci&oacute;n de sus Datos:<h2></center></div>\n"
                    + "<br/><center>DATOS PARA ACCEDER A SU CUENTA</center><br/>"+"\n"
                    + "<center>Cuenta de Acceso: " +"<h3>"+ correo +"</h3>"+ "</center>"+"\n"
                    + "<center>Clave de Acceso: " +"<h3>"+ clave +"</h3>"+ "</center>"+"\n"
                    + "<center>Con esta informaci&oacute;n podr&aacute; acceder a su cuenta en el sistema a trav&eacute;s del siguiente enlace:<br/></center>"
                    +"<center><b><a href="+"\"http://187.154.40.169:8080/mantenimientocc/\" style=\" color:blue;\"><b>SISTEMA DE MANTENIMIENTO PREVENTIVO Y/O CORRECTIVO DEL ITO<b/>"+"</a></b></center>"
                    + "<br/><br/>"
                    + "<center><div><p style=\"font-size: 10pt; line-height:100%;\">Tecnol&oacute;gico Nacional de M&eacute;xico / Instituto Tecnol&oacute;gico de Oaxaca<br/>"
                    + "Avenida Ing. V&iacute;ctor Bravo Ahuja<br/>"
                    + "No. 125 Esquina Calzada Tecnol&oacute;gico, C.P. 68030<br/>"
                    + "Tels.(951) 501 50 16, Correo electr&oacute;nico SIMAPRECO: sistema.mantenimiento@itoaxaca.edu.mx<br/>"
                    + "Derechos Reservados.</p></center>";

            MimeMessage crearCorreo = new MimeMessage(this.crearSesion(this.setup()));
            crearCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(correo));
            crearCorreo.setSubject(asunto);
            crearCorreo.setContent(mensaje,"text/html");
//Enviamos el Mensaje
            Transport.send(crearCorreo);
        } catch (Exception ex) {
            System.out.println("Error en CorreoRegistroUsuario -> enviarMensajeRecuperacion " + ex);
        }
    }
    
    
     public void enviarMensajeOrdenTrabajo(Orden_trabajo orden) {
        
         try {
            setup();
            String asunto = "Final de Proceso de Solicitud al Sistema SIMAPRECO del TEC-OAX";
            String mensaje = "<center><h1>INSTITUTO TECNOL&Oacute;GICO DE OAXACA<h1></center><br/>"+
                    "<center><h3>SISTEMA DE MANTENIMIENTO PREVENTIVO Y/O CORRECTIVO DEL ITO<h3></center><br/>"
                    + "<div><center><h2>Finalizaci&oacute;n de Servicio:<h2></center></div>\n"
                    + "<br/><center><h3>Gracias Por usar SIMAPRECO<h3></center><br/>"+"\n"
                    + "<center><p>Por este Medio se le Informa que su Servicio est&aacute; Llegando a su Estado Final:</p></center>"+"\n"
                    + "<center>N&uacute;mero de Folio: \t <b>"+orden.getId_solicitudmc().getFolio()+"</b>\tAl Departamento:<b>"+orden.getId_solicitudmc().getId_departamento().getNombre_departamento()+"</b> </center>"+"\n"
                    + "<center><p>Recuerde que Puede Ver el Seguimiento de su Solicitud.</p></center>"
                    + "<br/><br/><center><h6 style=\" color:red; font-size:10px;\">* Este correo es de car&aacute;cter Informativo no es relevante y los datos contenidos son proporcionados por el sistema.<h6></center>"
                    + "<center>Puede Acceder  al sistema a trav&eacute;s del siguiente enlace:<br/></center>"
                    +"<center><b><a href="+"\"http://187.154.40.169:8080/mantenimientocc/\" style=\" color:blue;\"><b>SISTEMA DE MANTENIMIENTO PREVENTIVO Y/O CORRECTIVO DEL ITO<b/>"+"</a></b></center>"
                    + "<br/><br/>"
                    + "<center><div><p style=\"font-size: 10pt; line-height:100%;\">Tecnol&oacute;gico Nacional de M&eacute;xico / Instituto Tecnol&oacute;gico de Oaxaca<br/>"
                    + "Avenida Ing. V&iacute;ctor Bravo Ahuja<br/>"
                    + "No. 125 Esquina Calzada Tecnol&oacute;gico, C.P. 68030<br/>"
                    + "Tels.(951) 501 50 16, Correo electr&oacute;nico SIMAPRECO: sistema.mantenimiento@itoaxaca.edu.mx<br/>"
                    + "Derechos Reservados.</p></center>";

            MimeMessage crearCorreo = new MimeMessage(this.crearSesion(this.setup()));
            crearCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(orden.getId_solicitudmc().getId_usuario().getCorreo()));
            crearCorreo.setSubject(asunto);
            crearCorreo.setContent(mensaje,"text/html");
//Enviamos el Mensaje
            Transport.send(crearCorreo);
        } catch (Exception ex) {
            System.out.println("Error en CorreoRegistroUsuario -> enviarMensajeRecuperacion " + ex);
        }
    }
}
