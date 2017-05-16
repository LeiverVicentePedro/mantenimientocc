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
import mx.edu.itoaxaca.mantenimientocc.modelo.Solicitud_mc;

/**
 *
 * @author leiver
 */
public class CorreoInternet extends ConexionCorreo implements Runnable{
    private String correo;
    private Solicitud_mc solicitud;

    public CorreoInternet(String correo) {
        this.correo = correo;
       
    }
    
    
    @Override
    public void run() {
        try {
            setup();
             String asunto = "Nivel de Internet Rechazado - SIMAPRECO del TEC-OAX";
            String mensaje = "<center><h1>INSTITUTO TECNOL&Oacute;GICO DE OAXACA<h1></center><br/>"+
                    "<center><h3>SISTEMA DE MANTENIMIENTO PREVENTIVO Y/O CORRECTIVO DEL ITO<h3></center><br/>"
                    + "<div><center><h2>Solicitud de Niveles de Internet Rechazada:<h2></center></div>\n"
                    + "<br/><center><h3>Gracias Por usar SIMAPRECO<h3></center><br/>"+"\n"
                    + "<center><p>Usted a realizado una solicitud de Niveles de Internet el cual a sido rechazado."
                    + "<center><p>favor de comunicarse con su jefe inmediato para mas informaci&ocute;n</p></center>"+"\n"
                    + "<br/><br/><center><h6 style=\" color:red; font-size:10px;\">* Este correo es de car&aacute;cter Informativo no es relevante y los datos contenidos son proporcionados por el sistema.<h6></center>"
                    + "<center>Puede Acceder al sistema a trav&eacute;s del siguiente enlace:<br/></center>"
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
            System.out.println("Error en CorreoRegistroUsuario -> enviarMensajeSolicitud " + ex);
        }
    }
    
}



