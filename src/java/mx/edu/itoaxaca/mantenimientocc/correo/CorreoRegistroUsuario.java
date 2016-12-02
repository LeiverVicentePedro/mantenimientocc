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

/**
 *
 * @author leiver
 */
public class CorreoRegistroUsuario extends ConexionCorreo{
    
        public void enviarMensaje(String correo, String clave) {
        try {
            this.setup();
            String asunto = "Registro en el Sistema SIMAPRECO del TEC-OAX";
            String mensaje = "Gracias por registrarse\n" + "Su Cuenta a sido creada de manera satisfactoria con los siguentes datos:\n"
                    + "DATOS PARA ACCEDER A SU CUENTA\n"
                    + "Su cuenta de Acceso: " + correo + "\n"
                    + "Su clave de acceso: " + clave + "\n"
                    + "Con esta informacion podra acceder a su cuenta en el sistema";

            Message crearCorreo = new MimeMessage(this.crearSesion());
            crearCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(correo));
            crearCorreo.setSubject(asunto);
            crearCorreo.setText(mensaje);
//Enviamos el Mensaje
            Transport.send(crearCorreo);
        } catch (Exception ex) {
            System.out.println("Error en UsuarioBEAN -> enviarMensaje " + ex);
        }
    }

    public void enviarMensajeRecuperacion(String correo, String clave) {
        try {
            setup();
            String asunto = "Recuperacion de Cuenta del Sistema SIMAPRECO del TEC-OAX";
            String mensaje = "Datos pertenecientes a la cuenta del Sistema SIMAPRECO del INSTITUTO TECNOLÓGICO DE OAXACA\n"
                    + "DATOS DE SU CUENTA\n"
                    + "Su cuenta de Acceso: " + correo + "\n"
                    + "Su clave de acceso: " + clave + "\n"
                    + "Con esta informacion podra acceder a su cuenta en el sistema";

            Message crearCorreo = new MimeMessage(this.crearSesion());
            crearCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(correo));
            crearCorreo.setSubject(asunto);
            crearCorreo.setText(mensaje);
//Enviamos el Mensaje
            Transport.send(crearCorreo);
        } catch (Exception ex) {
            System.out.println("Error en UsuarioBEAN -> enviarMensaje " + ex);
        }
    }
}
