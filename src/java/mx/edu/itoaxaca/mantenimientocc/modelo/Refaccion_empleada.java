/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.modelo;

import java.io.Serializable;

/**
 *
 * @author Jerusalen
 */
public class Refaccion_empleada implements Serializable{
    
    private int id_refaccion_empleada;
    private String descripcion;
    private String numero_serie;
    private Double precio;
    private int cantidad;

    public Refaccion_empleada(String descripcion, String numero_serie, Double precio, int cantidad) {//actualmente solo usado en orden interna
        this.descripcion = descripcion;
        this.numero_serie = numero_serie;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public Refaccion_empleada() {
        //constructor vacio para los metodos comunes cuando se crea objetos sin nesesidad de pasar parametros
    }
    
    
    
    
    public int getId_refaccion_empleada() {
        return id_refaccion_empleada;
    }

    public void setId_refaccion_empleada(int id_refaccion_empleada) {
        this.id_refaccion_empleada = id_refaccion_empleada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNumero_serie() {
        return numero_serie;
    }

    public void setNumero_serie(String numero_serie) {
        this.numero_serie = numero_serie;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

  
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.id_refaccion_empleada;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Refaccion_empleada other = (Refaccion_empleada) obj;
        if (this.id_refaccion_empleada != other.id_refaccion_empleada) {
            return false;
        }
        return true;
    }
    
    
    
    
}
