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
public class DetallePorMesSolicitud {
    
    private int mes;
    private int inicial;
    private int proceso;
    private int terminado;
    private int total;

    public DetallePorMesSolicitud() {
    }

    public DetallePorMesSolicitud(int mes, int inicial, int proceso, int terminado, int total) {
        this.mes = mes;
        this.inicial = inicial;
        this.proceso = proceso;
        this.terminado = terminado;
        this.total = total;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getInicial() {
        return inicial;
    }

    public void setInicial(int inicial) {
        this.inicial = inicial;
    }

    public int getProceso() {
        return proceso;
    }

    public void setProceso(int proceso) {
        this.proceso = proceso;
    }

    public int getTerminado() {
        return terminado;
    }

    public void setTerminado(int terminado) {
        this.terminado = terminado;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
    
}
