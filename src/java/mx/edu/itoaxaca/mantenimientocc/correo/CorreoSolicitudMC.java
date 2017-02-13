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
public class CorreoSolicitudMC extends ConexionCorreo implements Runnable{
    private String correo;
    private Solicitud_mc solicitud;

    public CorreoSolicitudMC(String correo, Solicitud_mc solicitud) {
        this.correo = correo;
        this.solicitud = solicitud;
    }
    
    
    @Override
    public void run() {
        try {
            setup();
            String asunto = "Solicitud de Mantenimieto Realizada al Sistema SIMAPRECO del TEC-OAX";
            String mensaje = "<center><h1>INSTITUTO TECNOL&Oacute;GICO DE OAXACA<h1></center><br/>"
                    + "<div><center><h2>Solicitud de Mantenimiento Realizada:<h2></center></div>\n"
                    + "<br/><center><h3>Gracias Por usar SIMAPRECO<h3></center><br/>"+"\n"
                    + "<center><p>A realizado una solicitud de mantenimiento con la siguente informacion basica:</p></center>"+"\n"
                    + "<center>Numero de Folio: \t <b>"+solicitud.getFolio()+"</b>\tAl Departamento:<b>"+solicitud.getId_departamento().getNombre_departamento()+"</b> </center>"+"\n"
                    + "<br/><br/><center><h6 style=\" color:red; font-size:10px;\">* Este correo es de caracter Informativo no es relevante y los datos contenidos son proporcionados por el sistema.<h6></center>"
                    + "<center>Puede Acceder  al sistema a travez del siguiente enlace:<br/></center>"
                    +"<center><b><a href="+"\"http://simapreco.j.layershift.co.uk/\" style=\" color:blue;\"><b>SISTEMA DE MANTENIMIENTO PREVENTIVO Y/O CORRECTIVO DEL ITO<b/>"+"</a></b></center>"
                    + "<br/><br/>"
                    + "<center><div><p style=\"font-size: 10pt; line-height:100%;\">Tecnol&oacute;gico Nacional de M&eacute;xico / Instituto Tecnol&oacute;gico de Oaxaca<br/>"
                    + "Avenida Ing. V&iacute;ctor Bravo Ahuja<br/>"
                    + "No. 125 Esquina Calzada Tecnol&oacute;gico, C.P. 68030<br/>"
                    + "Tels.(951) 501 50 16, Correo electronico SIMAPRECO: sistema.mantenimiento@itoaxaca.edu.mx<br/>"
                    + "Derechos Reservados.</p></center>";

            Message crearCorreo = new MimeMessage(this.crearSesion(this.setup()));
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
