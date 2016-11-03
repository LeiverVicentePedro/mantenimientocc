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
import mx.edu.itoaxaca.mantenimientocc.modelo.Oficina_solicitante;

/**
 *
 * @author leiver
 */
public class Oficina_solicitanteDAO extends Conexion{
    
    public void registrarDepartamento(Oficina_solicitante oficinaregistra) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("INSERT INTO oficinas_solicitantes (nombre_oficina, id_departamento,extencion,estatus) values(?,?,?,?)");
            consulta.setString(1, oficinaregistra.getNombreOficina());
            consulta.setInt(2,oficinaregistra.getIdDepartamento().getIddepartamento());
            consulta.setInt(3,oficinaregistra.getExtencion());
            consulta.setBoolean(4,oficinaregistra.getEstatus());
            
            consulta.executeUpdate();
        }
        catch(Exception e){
           System.out.println("error en DAO"); 
        }
        finally{
           this.Cerrar();
        }
    }
    
    public List<Oficina_solicitante> buscarOficinaSolicitantePorIdDepartamento(int idDepartamento) throws Exception{
        List listaOficinaSolicitantePorDepartamento =null;
        ResultSet resultadoConsulta;
        try{
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareCall("SELECT * FROM oficinas_solicitantes where id_departamento=?");
            consulta.setInt(1, idDepartamento);
            resultadoConsulta = consulta.executeQuery();
            listaOficinaSolicitantePorDepartamento = new ArrayList();
            while(resultadoConsulta.next())
            {
                Oficina_solicitante oficina = new Oficina_solicitante();
                oficina.setIdOficinaSolicitante(resultadoConsulta.getInt("idoficinas"));
                oficina.setNombreOficina(resultadoConsulta.getString("nombre_oficina"));
                oficina.setIdDepartamento(new DepartamentoDAO().buscarIdDepartamento(resultadoConsulta.getInt("id_departamento")));
                oficina.setExtencion(resultadoConsulta.getInt("extencion"));
                oficina.setEstatus(resultadoConsulta.getBoolean("estatus"));
                listaOficinaSolicitantePorDepartamento.add(oficina);
            }
            resultadoConsulta.close();
            
        }catch(Exception ex){
            System.out.println("Error en Oficina_solicitanteDAO->listaOficinaSolicitante "+ex);
            throw ex;
        }finally{
            this.Cerrar();
        }
        return listaOficinaSolicitantePorDepartamento;
    }
    
    public Oficina_solicitante buscarOficinaPorIdOficina(int idOficina) throws Exception{
        Oficina_solicitante oficina =null;
        ResultSet resultadoConsulta;
        try{
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareCall("SELECT * FROM oficinas_solicitantes where idoficinas=?");
            consulta.setInt(1, idOficina);
             resultadoConsulta = consulta.executeQuery();
            if(resultadoConsulta.next()==true){
                oficina = new Oficina_solicitante();
            oficina.setIdOficinaSolicitante(resultadoConsulta.getInt("idoficinas"));
            oficina.setExtencion(resultadoConsulta.getInt("extencion"));
            oficina.setNombreOficina(resultadoConsulta.getString("nombre_oficina"));
            oficina.setEstatus(resultadoConsulta.getBoolean("estatus"));
            oficina.setIdDepartamento(new DepartamentoDAO().buscarIdDepartamento(resultadoConsulta.getInt("id_departamento")));
            consulta.close();
            }
            else{
                System.out.println("Error en objeto vacio");
            }
            
            return oficina;
        }catch(Exception ex){
            System.out.println("Error en Oficina_solicitanteDAO -> buscarOficinaPorIdOficina "+ex);
            throw ex;
        }finally{
            this.Cerrar();
        }
        
    }
}
