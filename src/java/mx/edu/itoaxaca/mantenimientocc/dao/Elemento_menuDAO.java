
package mx.edu.itoaxaca.mantenimientocc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mx.edu.itoaxaca.mantenimientocc.conexion.Conexion;
import mx.edu.itoaxaca.mantenimientocc.modelo.Elemento_menu;

/**
 *
 * @author leiver
 */
public class Elemento_menuDAO extends Conexion{

    public Elemento_menu buscarMenuPorIdElemento_menu(int idElemento_menu) throws Exception{
        Elemento_menu objetoElementoMenu = new Elemento_menu();
        ResultSet resultado;
        try{
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("SELECT * FROM elemento_menu WHERE idelemento_menu=?");
            consulta.setInt(1, idElemento_menu);
            resultado = consulta.executeQuery();
            if(resultado.next()==true){
                objetoElementoMenu.setIdelemento_menu(resultado.getInt("idelemento_menu"));
                objetoElementoMenu.setNombre(resultado.getString("nombre"));
                objetoElementoMenu.setTipo_menu(resultado.getString("tipo_menu"));
                objetoElementoMenu.setCodigo_submenu(resultado.getInt("codigo_submenu"));
                objetoElementoMenu.setDepartamento(resultado.getString("departamento"));
                objetoElementoMenu.setEstado(resultado.getBoolean("estado"));
                objetoElementoMenu.setIcono(resultado.getString("icono"));
                objetoElementoMenu.setVista(resultado.getString("vista"));
            }
            return objetoElementoMenu;
        }catch(Exception ex){
            System.out.println("Error en Elemento_MenuDAO -> buscarMenuPorIdmenu "+ex);
            throw ex;
        }finally{
            this.Cerrar();
        }
    }
}
