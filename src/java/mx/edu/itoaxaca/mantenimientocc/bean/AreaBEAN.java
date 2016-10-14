
package mx.edu.itoaxaca.mantenimientocc.bean;


import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mx.edu.itoaxaca.mantenimientocc.dao.AreaDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Area;

@ManagedBean
@ViewScoped
public class AreaBEAN {
 
    private Area area = new Area();
    private List<Area> listaArea;

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
    
    public void registrarArea() throws Exception{
        AreaDAO areadao;
            try{
                areadao= new AreaDAO();
                areadao.registrar(area);
        
            }
            catch(Exception e)
            {
                throw e;
            }
    }   

    public List<Area> getListaArea() {
        return listaArea;
    }

    public void setListaArea(List<Area> listaArea) {
        this.listaArea = listaArea;
    }
    
    public void listarArea() throws Exception{
        AreaDAO areadao;
        try{
            areadao=new AreaDAO();
            listaArea = areadao.listar();
        }
        catch(Exception e){
            throw e;
        }
    }
    
    public void elegirDatoArea(Area areaElegirDato) throws Exception{
        AreaDAO areadao;
        Area areaTemporal;
        try{
            areadao= new AreaDAO();
            areaTemporal=areadao.elegirDato(areaElegirDato);
            
            if(areaTemporal != null){
                this.area = areaTemporal;
            }
            }
        catch (Exception e){
            throw e;
        }
        
    }
    
    public void modificarArea() throws Exception{
        AreaDAO areadao;
            try{
                areadao= new AreaDAO();
                areadao.modificarArea(area);
                this.listarArea();
            }
            catch(Exception e)
            {
                throw e;
            }
    } 
}