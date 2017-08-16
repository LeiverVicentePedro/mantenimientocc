/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itoaxaca.mantenimientocc.apkdowload;

import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author leiver
 */
@ManagedBean
public class DescargarApk {
     
    private StreamedContent file;
     
    public DescargarApk() {        
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/apk/mantenimientocc.apk");
        file = new DefaultStreamedContent(stream, "application/vnd.android.package-archive", "mantenimientocc.apk");
    }
 
    public StreamedContent getFile() {
        return file;
    }
}
