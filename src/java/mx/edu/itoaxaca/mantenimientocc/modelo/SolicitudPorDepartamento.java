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
public class SolicitudPorDepartamento {
    private Departamento departamento;
    private int inicial;
    private int proceso;
    private int finales;
    private int total;

    public SolicitudPorDepartamento() {
    }

    public SolicitudPorDepartamento(Departamento departamento, int inicial, int proceso, int finales, int total) {
        this.departamento = departamento;
        this.inicial = inicial;
        this.proceso = proceso;
        this.finales = finales;
        this.total = total;
    }

    
    
    
    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
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
