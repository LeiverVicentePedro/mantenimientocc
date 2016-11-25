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
public class Menu {
    private int idmenu;
    private int nivel_usuario;
    private Elemento_menu id_elemento_menu;

    public int getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(int idmenu) {
        this.idmenu = idmenu;
    }

    public int getNivel_usuario() {
        return nivel_usuario;
    }

    public void setNivel_usuario(int nivel_usuario) {
        this.nivel_usuario = nivel_usuario;
    }

    public Elemento_menu getId_elemento_menu() {
        return id_elemento_menu;
    }

    public void setId_elemento_menu(Elemento_menu id_elemento_menu) {
        this.id_elemento_menu = id_elemento_menu;
    }
    
}
