/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.modelo;

/**
 *
 * @author leiver
 */
public class ModeloEmpleadoReporte {
    private Usuario usuario;
    private int inicio;
    private int proceso;
    private int finales;
    private int total;

    public ModeloEmpleadoReporte() {
    }

    public ModeloEmpleadoReporte(Usuario usuario, int inicio, int proceso, int finales, int total) {
        this.usuario = usuario;
        this.inicio = inicio;
        this.proceso = proceso;
        this.finales = finales;
        this.total = total;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getInicio() {
        return inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public int getProceso() {
        return proceso;
    }

    public void setProceso(int proceso) {
        this.proceso = proceso;
    }

    public int getFinales() {
        return finales;
    }

    public void setFinales(int finales) {
        this.finales = finales;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
    
}
