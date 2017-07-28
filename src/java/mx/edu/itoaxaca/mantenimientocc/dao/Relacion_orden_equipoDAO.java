/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mx.edu.itoaxaca.mantenimientocc.conexion.Conexion;
import mx.edu.itoaxaca.mantenimientocc.modelo.Equipo;
import mx.edu.itoaxaca.mantenimientocc.modelo.Orden_interna;
import mx.edu.itoaxaca.mantenimientocc.modelo.Relacion_orden_equipo;

/**
 *
 * @author Jerusalen
 */
public class Relacion_orden_equipoDAO extends Conexion{

    public void registrarDetalleOrdenEquipo(Relacion_orden_equipo detalleOrdenEquipo) throws Exception{
        try{
            this.Conectar();
            PreparedStatement insertar = this.getConexion().prepareStatement("INSERT INTO relacion_orden_equipo (id_equipo, id_orden_interna) VALUES(?,?)");
            insertar.setInt(1, detalleOrdenEquipo.getIdEquipo().getIdequipo());
            insertar.setInt(2, detalleOrdenEquipo.getIdOrden_interna().getIdorden_interna());
            insertar.executeUpdate();
        }catch(Exception ex){
            System.out.println("Error en Detalle_Orden_equipoDAO -> registrarDetalleOrdenEquipo "+ex);
            throw ex;
        }finally{
            this.Cerrar();
        }
    }
    
    public List<Relacion_orden_equipo> listaOrdenEquipo(Orden_interna orden)throws Exception{
        List<Relacion_orden_equipo> listadoOrdenEquipo = new ArrayList();
        try{
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("SELECT * FROM relacion_orden_equipo WHERE id_orden_interna=?");
            consulta.setInt(1, orden.getIdorden_interna());
           
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                Relacion_orden_equipo relacion = new Relacion_orden_equipo();
                relacion.setIdEquipo(new EquipoDAO().buscarIdEquipo(resultado.getInt("id_equipo")));
                relacion.setIdOrden_interna(orden);
                listadoOrdenEquipo.add(relacion);
            }
            
        }catch(Exception ex){
            System.out.println("Error en Relacion_orden_equipoDAO -> listaOrdenEquipo");
        }finally{
            this.Cerrar();
        }
        return listadoOrdenEquipo;
    }
    
}

