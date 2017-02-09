package mx.edu.itoaxaca.mantenimientocc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mx.edu.itoaxaca.mantenimientocc.conexion.Conexion;
import mx.edu.itoaxaca.mantenimientocc.modelo.Menu;

/**
 *
 * @author leiver
 */
public class MenuDAO extends Conexion{
    
    public List<Menu> listarMenuPorUsuario(int nivelUsuario) throws Exception{
        List<Menu> listaMenu;
        
        ResultSet resultado;
        try{
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("SELECT * FROM menu WHERE nivel_usuario=? order by idmenu");
            consulta.setInt(1, nivelUsuario);
            resultado = consulta.executeQuery();
            listaMenu = new ArrayList<Menu>();
            while(resultado.next()){
                Menu elementoMenu = new Menu();
                elementoMenu.setIdmenu(resultado.getInt("idmenu"));
                elementoMenu.setId_elemento_menu(new Elemento_menuDAO().buscarMenuPorIdElemento_menu(resultado.getInt("id_elemento_menu")));
                elementoMenu.setNivel_usuario(resultado.getInt("nivel_usuario"));
                listaMenu.add(elementoMenu);
            }
            return listaMenu;
        }catch(Exception ex){
            System.out.println("Error en MenuDAO -> listarMenuPorUsuario "+ex);
            throw ex;
        }finally{
            this.Cerrar();
        }
    }
}
