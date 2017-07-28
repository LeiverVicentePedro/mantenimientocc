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
import mx.edu.itoaxaca.mantenimientocc.modelo.Orden_interna;
import mx.edu.itoaxaca.mantenimientocc.modelo.Relacion_orden_refaccion;

/**
 *
 * @author Jerusalen
 */
public class Relacion_orden_refaccionDAO  extends Conexion{

    public void registrarDetalleOrdenRefaccion(Relacion_orden_refaccion detalleOrdenRefaccion) throws Exception{
        try{
            this.Conectar();
            PreparedStatement insertar = this.getConexion().prepareStatement("INSERT INTO relacion_orden_refaccion (id_refaccion_empleada, id_orden_interna) VALUES(?,?)");
            insertar.setInt(1, detalleOrdenRefaccion.getIdRefaccion().getId_refaccion_empleada());
            insertar.setInt(2, detalleOrdenRefaccion.getIdOrdenRefaccion().getIdorden_interna());
            insertar.executeUpdate();
        }catch(Exception ex){
            System.out.println("Error en Detalle_Orden_equipoDAO -> registrarDetalleOrdenEquipo "+ex);
            throw ex;
        }finally{
            this.Cerrar();
        }
    }
    
    public List<Relacion_orden_refaccion> listaOrdenRefaccion(Orden_interna orden) throws Exception{
        List<Relacion_orden_refaccion> listaRefaccion = new ArrayList();
       try{
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("SELECT * FROM relacion_orden_refaccion WHERE id_orden_interna=?");
            consulta.setInt(1, orden.getIdorden_interna());
           
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                Relacion_orden_refaccion relacion = new Relacion_orden_refaccion();
                relacion.setIdRefaccion(new Refaccion_empleadaDAO().buscarIdRefaccion(resultado.getInt("id_refaccion_empleada")));
                relacion.setIdOrdenRefaccion(orden);
                listaRefaccion.add(relacion);
            }
            
        }catch(Exception ex){
            System.out.println("Error en Relacion_orden_equipoDAO -> listaOrdenEquipo");
        }finally{
            this.Cerrar();
        }
        return listaRefaccion;
    }
    
}
