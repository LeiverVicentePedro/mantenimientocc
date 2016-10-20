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
import mx.edu.itoaxaca.mantenimientocc.modelo.Departamento;
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
        }
        return listaOficinaSolicitantePorDepartamento;
    }
}
