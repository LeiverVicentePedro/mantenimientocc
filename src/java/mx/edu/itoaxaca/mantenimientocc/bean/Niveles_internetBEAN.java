/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.bean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import mx.edu.itoaxaca.mantenimientocc.dao.Catalogo_nivelesDAO;
import mx.edu.itoaxaca.mantenimientocc.dao.Niveles_internetDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Catalogo_niveles;
import mx.edu.itoaxaca.mantenimientocc.modelo.Niveles_internet;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Jerusalen
 */
@ManagedBean
@ViewScoped
public class Niveles_internetBEAN implements Serializable {
    Niveles_internet nivelesInternet= new Niveles_internet();
    Catalogo_niveles catalogo_niveles = new Catalogo_niveles();
    private List<Niveles_internet> listaniveles= new ArrayList();
    private List<Niveles_internet> listanivelesAdministrador= new ArrayList();
    private List<Niveles_internet> listanivelesAdministradorModifica= new ArrayList();
    private List<Niveles_internet> seleccionJustificacion=new ArrayList();
    private List<Niveles_internet> filterNiveles_internet;
    private List<Niveles_internet> seleccionNiveles_internet;
    
    private String redireccion;
    private String mensaje;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    

    public String getRedireccion() {
        return redireccion;
    }

    public void setRedireccion(String redireccion) {
        this.redireccion = redireccion;
    }
    

    
    
    public List<Niveles_internet> getFilterNiveles_internet() {
        return filterNiveles_internet;
    }

    public void setFilterNiveles_internet(List<Niveles_internet> filterNiveles_internet) {
        this.filterNiveles_internet = filterNiveles_internet;
    }

    public List<Niveles_internet> getSeleccionNiveles_internet() {
        return seleccionNiveles_internet;
    }

    public void setSeleccionNiveles_internet(List<Niveles_internet> seleccionNiveles_internet) {
        this.seleccionNiveles_internet = seleccionNiveles_internet;
    }
    
    

    
    public List<Niveles_internet> getSeleccionJustificacion() {
        return seleccionJustificacion;
    }

    public void setSeleccionJustificacion(List<Niveles_internet> seleccionJustificacion) {
        this.seleccionJustificacion = seleccionJustificacion;
    }

    public List<Niveles_internet> getListanivelesAdministrador() {
        return listanivelesAdministrador;
    }

    public void setListanivelesAdministrador(List<Niveles_internet> listanivelesAdministrador) {
        this.listanivelesAdministrador = listanivelesAdministrador;
    }

    public List<Niveles_internet> getListanivelesAdministradorModifica() {
        return listanivelesAdministradorModifica;
    }

    public void setListanivelesAdministradorModifica(List<Niveles_internet> listanivelesAdministradorModifica) {
        this.listanivelesAdministradorModifica = listanivelesAdministradorModifica;
    }
    
    
    
    
    private Boolean nivelCuatro;

    public Boolean getNivelCuatro() {
        return nivelCuatro;
    }

    public void setNivelCuatro(SelectEvent event) {
        if(catalogo_niveles.getIdcatalogo_niveles()==3)
        {
            this.nivelCuatro=true;
        }
        else
            this.nivelCuatro=false;
    }

    public Catalogo_niveles getCatalogo_niveles() {
        return catalogo_niveles;
    }

    public void setCatalogo_niveles(Catalogo_niveles catalogo_niveles) {
        this.catalogo_niveles = catalogo_niveles;
    }
 
    
    public List<Niveles_internet> getListaniveles() {
        return listaniveles;
    }

    public void setListaniveles(List<Niveles_internet> listaniveles) {
        this.listaniveles = listaniveles;
    }

    
    public Niveles_internet getNivelesInternet() {
        return nivelesInternet;
    }

    public void setNivelesInternet(Niveles_internet nivelesInternet) {
        this.nivelesInternet = nivelesInternet;
    }
    
    public void registrarNivelesInternet() throws Exception{
        Niveles_internetDAO nivelesDao;
        
        Catalogo_nivelesDAO catalogoNivelesdao;
            try{
            FacesContext contexto = FacesContext.getCurrentInstance(); //paraq entrar ql dom del navegador
            Usuario usuarioVive = (Usuario) contexto.getExternalContext().getSessionMap().get("usuario");//llamo a  la etiqueta usuario que es un objeto que ya debe
            
                
               nivelesDao= new Niveles_internetDAO();
               catalogoNivelesdao=new Catalogo_nivelesDAO();
               nivelesInternet.setSolicita(usuarioVive);
              nivelesInternet.setFecha(new java.sql.Date(new java.util.Date().getTime()));//fecha sistema
              nivelesInternet.setId_catalogo_niveles(catalogo_niveles);
              nivelesInternet.setCorreo_solicita(usuarioVive.getCorreo());
               System.out.println("ver "+ nivelesInternet.getId_catalogo_niveles().getNivel());
                nivelesDao.registrarNivelesInternet(nivelesInternet);
              
                setMensaje("Registrado");
                setRedireccion("index.xhtml");
                FacesMessage mensajeSalida = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", mensaje);
                RequestContext.getCurrentInstance().showMessageInDialog(mensajeSalida);
                
                
               
            }
            
            catch(Exception e)
            {
                System.out.println("error en Niveles de Internet BEAN -->RegistrarNivelesInternetBEAN"+e);
            }
    }   
    public void limpiarNivelInternet(){
        this.nivelesInternet.setJustificacion("");
        this.nivelesInternet.setTipo_solicitud("");
        this.nivelesInternet.setTipo_equipo("");
       
    }
    public void listarNiveles_internet() throws Exception{
       Niveles_internetDAO niveles_internetdao;
        try{
            niveles_internetdao=new Niveles_internetDAO();
            listaniveles = niveles_internetdao.listarNiveles_internet();
        }
        catch(Exception e){
            System.out.println("error en niveles_internetBEAN --> listarniveles_InternetBEAN"+e);
        }
    }
    public void listarNiveles_internetAministrador() throws Exception{
       Niveles_internetDAO niveles_internetdao;
        try{
            niveles_internetdao=new Niveles_internetDAO();
            listanivelesAdministrador = niveles_internetdao.listarNiveles_internetAdministradorRegistra();
        }
        catch(Exception e){
            System.out.println("error en niveles_internetBEAN --> listarniveles_InternetAdministradorBEAN"+e);
        }
    }
     public void listarNiveles_internetAministradorModifica() throws Exception{
       Niveles_internetDAO niveles_internetdao;
        try{
            niveles_internetdao=new Niveles_internetDAO();
            listanivelesAdministradorModifica = niveles_internetdao.listarNiveles_internetAdministradorModifica();
        }
        catch(Exception e){
            System.out.println("error en niveles_internetBEAN --> listarniveles_InternetAdministradorBEAN"+e);
        }
    }
    
     public void elegirDatoNivelesInternet(Niveles_internet nivelesElegirDato) throws Exception{//esto es para dar de baja primero se elige el dato y despues se pone en inactivo
        Niveles_internetDAO nivelesdao;
        Niveles_internet nivelesTemporal;
        try{
            nivelesdao= new Niveles_internetDAO();
            nivelesTemporal=nivelesdao.elegirDatoNivel(nivelesElegirDato);
            
            if(nivelesTemporal != null){
                this.nivelesInternet = nivelesTemporal;
            }
            this.autoriza();//se manda a llamar al metodo dar de baja para q se modifique el estatus por INACTIVO
            this.listarNiveles_internet();//para actualizar la tabla y se vea reflejado el cambio de estatus
            }
        catch (Exception e){
            throw e;
        }
        
    }
     public void autoriza() throws Exception{
        Niveles_internetDAO nivelesdao;
            try{
                nivelesdao= new Niveles_internetDAO();
                FacesContext contexto = FacesContext.getCurrentInstance(); //paraq entrar ql dom del navegador
                 Usuario usuarioVive = (Usuario) contexto.getExternalContext().getSessionMap().get("usuario");//llamo a  la etiqueta usuario que es un objeto que ya debe
                 nivelesInternet.setAutoriza(usuarioVive);
                 nivelesInternet.setCorreo_autoriza(usuarioVive.getCorreo());
                nivelesInternet.setEstatus_autoriza(true);
                nivelesdao.modificarNiveles(nivelesInternet);
            }
            catch(Exception e)
            {
                throw e;
            }
    } 
     
    public void elegirDatoNivelesInternetAdministradorRegistra(Niveles_internet nivelesElegirDato) throws Exception{//esto es para dar de baja primero se elige el dato y despues se pone en inactivo
        Niveles_internetDAO nivelesdao;
        Niveles_internet nivelesTemporal;
        try{
            nivelesdao= new Niveles_internetDAO();
            nivelesTemporal=nivelesdao.elegirDatoNivel(nivelesElegirDato);
            
            if(nivelesTemporal != null){
                this.nivelesInternet = nivelesTemporal;
             //   this.modificarAdministrador();
            }
            this.listarNiveles_internetAministrador();
            }
        catch (Exception e){
           System.out.println("error en Niveles_internetBEAN -> ElegirAdministrador "+e);
        }
        
    }
    
    public void modificarAdministrador() throws Exception{
        Niveles_internetDAO modificaniveldao;
            try{
                modificaniveldao= new Niveles_internetDAO();
                 
                modificaniveldao.modificarNiveles(nivelesInternet);
                exportarNivelInternet();
              this.listarNiveles_internetAministrador();
            }
            catch(Exception e)
            {
                System.out.println("error en Niveles_internetBEAN -> modificarAdministrador "+e);
            }
    } 
    
    public void elegirDatoNivelesInternetAdministradorModificar(Niveles_internet nivelesElegirDato) throws Exception{//esto es para dar de baja primero se elige el dato y despues se pone en inactivo
        Niveles_internetDAO nivelesdao;
        Niveles_internet nivelesTemporal;
        try{
            nivelesdao= new Niveles_internetDAO();
            //nivelesTemporal=nivelesdao.elegirDatoNivel(nivelesElegirDato);
            
            //if(nivelesTemporal != null){
                //this.nivelesInternet = nivelesTemporal;
                System.out.println("Dato");
           // }
            this.nivelesInternet = nivelesElegirDato;
            }
        catch (Exception e){
            System.out.println("Error en Niveles_internetBEAN -> elegirDatoNivelesInternetAdministradirModificar "+e);
            throw e;
        }
        
    }
    
    public void modificarAdministradorModificar() throws Exception{
        Niveles_internetDAO modificaniveldao;
            try{
                modificaniveldao= new Niveles_internetDAO();
                modificaniveldao.modificarNiveles(nivelesInternet);
                
               this.listarNiveles_internetAministradorModifica();
            }
            catch(Exception e)
            {
                System.out.println("error en departamentoBEAN metodo -->modificar"+e);
            }
    } 
    
     /**
     * ***********************************************************************
     */
    /*
    *metodo que genera el pdf para la Orden De Trabajo
    ***************************************************************************/
      
      
      public void exportarNivelInternet() throws JRException, IOException{
          Map<String,Object> parametros = new HashMap<String,Object>();
          String nombreUsuario = nivelesInternet.getSolicita().getId_profesion().getNombre_profesion()+" "+
                nivelesInternet.getSolicita().getConcatenar();
          
        String fecha = String.valueOf(nivelesInternet.getFecha());  
        parametros.put("fechasolicitud", fecha );
        parametros.put("departamento", nivelesInternet.getSolicita().getIdOficina().getDepartamento().getNombre_departamento());
        parametros.put("area", nivelesInternet.getSolicita().getIdOficina().getDepartamento().getArea().getNombre_area());

        
        if(nivelesInternet.getId_catalogo_niveles().getNivel()==2){
            parametros.put("x1","X");
            parametros.put("x2"," ");
            parametros.put("x3"," ");
        }else{
             if(nivelesInternet.getId_catalogo_niveles().getNivel()==3){
            parametros.put("x1"," ");
            parametros.put("x2","X");
            parametros.put("x3"," ");
        }
             else
             {
                if(nivelesInternet.getId_catalogo_niveles().getNivel()==4){
            parametros.put("x1"," ");
            parametros.put("x2"," ");
            parametros.put("x3","X");
        } 
             }
        }
        parametros.put("modeloequipo",nivelesInternet.getModelo_equipo());
        parametros.put("mac",nivelesInternet.getMac());
        parametros.put("so",nivelesInternet.getSo());
        String puerto= String.valueOf(nivelesInternet.getPuerto()); 
         parametros.put("puerto",puerto);
       parametros.put("edificio",nivelesInternet.getEdificio());
       parametros.put("nivel",nivelesInternet.getNivel_edificio());
      if(nivelesInternet.getTipo_solicitud().equalsIgnoreCase("Nueva")){
            parametros.put("nueva","X");
            parametros.put("cambio"," ");
            parametros.put("baja"," ");
        }else{
             if(nivelesInternet.getTipo_solicitud().equalsIgnoreCase("Cambio")){
            parametros.put("nueva"," ");
            parametros.put("cambio","X");
            parametros.put("baja"," ");
        }
             else
             {
                if(nivelesInternet.getTipo_solicitud().equalsIgnoreCase("Baja")){
            parametros.put("nueva"," ");
            parametros.put("cambio"," ");
            parametros.put("baja","X");
        } 
             }
        }
      String velan = String.valueOf(nivelesInternet.getVlan());
        parametros.put("vlan", velan); 
        if(nivelesInternet.getConexion().equalsIgnoreCase("Cableado")){
            parametros.put("cableado","X");
            parametros.put("inalambrico"," ");
        }else{
             if(nivelesInternet.getConexion().equalsIgnoreCase("Inalambrico")){
            parametros.put("cableado"," ");
            parametros.put("inalambrico","X");
        }
           
        }
        
        if(nivelesInternet.getTipo_equipo().equalsIgnoreCase("Escritorio")){
            parametros.put("escritorio","X");
            parametros.put("laptop"," ");
            parametros.put("tableta"," ");
            parametros.put("otro"," ");
        }else{
             if(nivelesInternet.getTipo_equipo().equalsIgnoreCase("Laptop")){
            parametros.put("escritorio"," ");
            parametros.put("laptop","X");
            parametros.put("tableta"," ");
            parametros.put("otro"," ");
        }
             else
             {
                if(nivelesInternet.getTipo_equipo().equalsIgnoreCase("Tableta")){
            parametros.put("escritorio"," ");
            parametros.put("laptop"," ");
            parametros.put("tableta","X");
            parametros.put("otro"," ");
        } 
                else{
                    if(!nivelesInternet.getTipo_equipo().equalsIgnoreCase("Escritorio")&&!nivelesInternet.getTipo_equipo().equalsIgnoreCase("Laptop")&&!nivelesInternet.getTipo_equipo().equalsIgnoreCase("Tableta")){
                     parametros.put("escritorio"," ");
            parametros.put("laptop"," ");
            parametros.put("tableta"," ");
            parametros.put("otro", nivelesInternet.getTipo_equipo());   
                    }
                
                        }
             }
        }
        
        
        
        parametros.put("solicita", nombreUsuario.toUpperCase());
        parametros.put("autoriza", nombreUsuario.toUpperCase());
        parametros.put("correosolicita",nivelesInternet.getCorreo_solicita());
        parametros.put("correoautoriza",nivelesInternet.getCorreo_autoriza());
        parametros.put("configuro",nivelesInternet.getConfiguro());
        parametros.put("ip",nivelesInternet.getIp());
        
               
           
           File archivo = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/niveles_internet.jasper"));
           JasperPrint imprimirArchivo = JasperFillManager.fillReport(archivo.getPath(), parametros, new JREmptyDataSource());
           
           HttpServletResponse respuestaArchivo = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        respuestaArchivo.addHeader("Content-Disposition", "attachment; filename=\"Niveles_internet.pdf\";");
        ServletOutputStream stream = respuestaArchivo.getOutputStream();

        JasperExportManager.exportReportToPdfStream(imprimirArchivo, stream);

        FacesContext.getCurrentInstance().responseComplete();
        

      }
      
      //**********************************************************************************************
}
