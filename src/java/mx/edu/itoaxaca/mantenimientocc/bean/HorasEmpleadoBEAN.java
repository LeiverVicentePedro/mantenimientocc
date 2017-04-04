/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
            horasEmpleadoDao.insertarHorasEmpleado(horas);
        } catch (Exception ex) {
            System.out.println("Error en HorasEmpleadoBEAN -> registrarHoras " + ex);
        }
    }

    public void listarHorasEmpleado() {
        try {
            ListaHorasEmpleado = horasEmpleadoDao.listaHorasEmpleado(usuario);
        } catch (Exception ex) {
            System.out.println("Error en HorasEmpleadoBEAN -> listarHorasEmpleado " + ex);
        }
    }

    public void obtenerHorasAcumuladas() {
        /*Date hora1 = new SimpleDateFormat().parse("19/05/2006 05:00:00");
        Date hora2 = new SimpleDateFormat().parse("19/05/2006 08:30:30");
        long lantes = hora1.getTime();
        long lahora = hora2.getTime();
        long diferencia = (lahora - lantes);
        System.out.println(new java.text.SimpleDateFormat("HH:mm:ss").format(new Date(diferencia)));*/
        for(HorasEmpleado horas : ListaHorasEmpleado){
            //poner las horas que lleva para acumularlos en una sola variable y despues imprimirlas.
           
        }
        
    }
}
