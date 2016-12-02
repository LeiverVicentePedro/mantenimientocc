/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.correo;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

/**
 *
 * @author leiver
 */
public class ConexionCorreo {
    private String usuarioCorreo;
    private String contraseñaCorreo;
    private Properties propiedades;
    private Session sesion;
    
    public  void setup() throws MessagingException {
        //datos de conexion
        usuarioCorreo = "soprte.mantenimiento@itoaxaca.edu.mx";
        contraseñaCorreo = "tecdeoaxaca";
        //propiedades de la conexion
        propiedades = new Properties();
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.port", "587");

        //creamos la sesion
        sesion = crearSesion();
    }

    public  Session crearSesion() {
        Session session = Session.getInstance(propiedades,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuarioCorreo, contraseñaCorreo);
            }
        });
        return session;
    }
}
