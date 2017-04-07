/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import mx.edu.itoaxaca.mantenimientocc.dao.HorasEmpleadoDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.HorasEmpleado;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;

/**
 *
 * @author leiver
 */
@ManagedBean
@SessionScoped
public class HorasEmpleadoBEAN implements Serializable {

    HorasEmpleado horas = new HorasEmpleado();
    HorasEmpleadoDAO horasEmpleadoDao = new HorasEmpleadoDAO();
    List<HorasEmpleado> ListaHorasEmpleado = new ArrayList();
    Usuario usuario = new Usuario();
    private String horasAcumuladas;

    public HorasEmpleado getHoras() {
        return horas;
    }

    public void setHoras(HorasEmpleado horas) {
        this.horas = horas;
    }

    public List<HorasEmpleado> getListaHorasEmpleado() {
        return ListaHorasEmpleado;
    }

    public void setListaHorasEmpleado(List<HorasEmpleado> ListaHorasEmpleado) {
        this.ListaHorasEmpleado = ListaHorasEmpleado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getHorasAcumuladas() {
        return horasAcumuladas;
    }

    public void setHorasAcumuladas(String horasAcumuladas) {
        this.horasAcumuladas = horasAcumuladas;
    }

    public void registraHoras() {
        try {
            //if(/*comprobar ip del equipo*/){
              //if(/*si el usuario ya registro y tiene un o en estatus */){
                  
             // }else{
                  /*el registro es nuevo*/
            FacesContext contexto = FacesContext.getCurrentInstance(); //paraq entrar ql dom del navegador
            Usuario usuarioVive = (Usuario) contexto.getExternalContext().getSessionMap().get("usuario");//llamo a  la etiqueta usuario que es un objeto que ya debe
            horas.setEstatus(false);
            horas.setFecha(new java.sql.Date(new java.util.Date().getTime()));//fecha sistema.
            horas.setHora_entrada(new SimpleDateFormat("HH:mm:ss").format(new java.sql.Date(new java.util.Date().getTime())));
            horas.setHora_salida(new SimpleDateFormat("HH:mm:ss").format(new java.sql.Date(new java.util.Date().getTime())));
            horas.setId_usuario_empleado(usuarioVive);
            horasEmpleadoDao.insertarHorasEmpleado(horas);
            //  }  
           // }
            
            /*para el registro de horas
            se debe consular si ya existe el registro si ya esta con fecha y esttus es 0 entonces es modificacion 
            pero si no tiene fecha ni estatus es insercion.
            comprobar que la ip del equipo sea el correcto para registrar si no evitar el registro.*/
        } catch (Exception ex) {
            System.out.println("Error en HorasEmpleadoBEAN -> registrarHoras " + ex);
        }
    }

    public void listarHorasEmpleado() {
        try {
            ListaHorasEmpleado = horasEmpleadoDao.listaHorasEmpleado(usuario);
            obtenerHorasAcumuladas();
        } catch (Exception ex) {
            System.out.println("Error en HorasEmpleadoBEAN -> listarHorasEmpleado " + ex);
        }
    }

    public void obtenerHorasAcumuladas() throws ParseException {
        int minutos = 0;
        int hora = 0;
        
        for (HorasEmpleado horas : ListaHorasEmpleado) {
            StringTokenizer st = new StringTokenizer(horas.getHorasEmpleado(), ":", false);
            int contador = 0;
            while (st.hasMoreTokens()) {
                if (contador % 2 == 0) {
                    
                    hora += Integer.parseInt(st.nextToken());
                    
                } else {
                    
                    minutos += Integer.parseInt(st.nextToken());
                     
                }
                contador++;
            }
            
        }
        horasAcumuladas = (hora + (minutos / 60)) + ":" + (minutos % 60);
    }

}