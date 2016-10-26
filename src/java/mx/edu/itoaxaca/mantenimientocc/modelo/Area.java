
package mx.edu.itoaxaca.mantenimientocc.modelo;


public class Area {
    
    private int idarea;
    private String nombre_area;
    private Boolean estatus;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.idarea;
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
        final Area other = (Area) obj;
        if (this.idarea != other.idarea) {
            return false;
        }
        return true;
    }

    /*
    @Override
    public String toString() {
        return String.format("%s[idarea=%d]",getClass().getSimpleName(),getIdarea());
    }

  */
    
    
    
    
 

    public int getIdarea() {
        return idarea;
    }

    public void setIdarea(int idarea) {
        this.idarea = idarea;
    }

    public String getNombre_area() {
        return nombre_area;
    }

    public void setNombre_area(String nombre_area) {
        this.nombre_area = nombre_area;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }
    
    
    
    
}
