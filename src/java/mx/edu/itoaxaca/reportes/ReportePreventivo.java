package mx.edu.itoaxaca.reportes;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import mx.edu.itoaxaca.mantenimientocc.modelo.DetallePreventivo;
import mx.edu.itoaxaca.mantenimientocc.modelo.Preventivo;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author leiver
 */
public class ReportePreventivo {
    
    public void crearReporte(Preventivo preventivo,List<DetallePreventivo> detallePreventivo) throws JRException, IOException{
        String [] numero = {"num1", "num2", "num3", "num4", "num5", "num6"};
        String [] servicios = {"servicio1", "servicio2", "servicio3", "servicio4", "servicio5", "servicio6"};
        
        String [][] descripcionServicio = {{"tipo1P","s1P","ene1p","feb1p","mar1p","abr1p","may1p","jun1p","jul1p","ago1p","sep1p","oct1p","nov1p","dic1p"},
                                           {"tipo1R","s1R","ene1r","feb1r","mar1r","abr1r","may1r","jun1r","jul1r","ago1r","sep1r","oct1r","nov1r","dic1r"},
                                           {"tipo1O","s1O","ene1o","feb1o","mar1o","abr1o","may1o","jun1o","jul1o","ago1o","sep1o","oct1o","nov1o","dic1o"},
                                           {"tipo2P","s2P","ene2p","feb2p","mar2p","abr2p","may2p","jun2p","jul2p","ago2p","sep2p","oct2p","nov2p","dic2p"},
                                           {"tipo2R","s2R","ene2r","feb2r","mar2r","abr2r","may2r","jun2r","jul2r","ago2r","sep2r","oct2r","nov2r","dic2r"},
                                           {"tipo2O","s2O","ene2o","feb2o","mar2o","abr2o","may2o","jun2o","jul2o","ago2o","sep2o","oct2o","nov2o","dic2o"},
                                           {"tipo3P","s3P","ene3p","feb3p","mar3p","abr3p","may3p","jun3p","jul3p","ago3p","sep3p","oct3p","nov3p","dic3p"},
                                           {"tipo3R","s3R","ene3r","feb3r","mar3r","abr3r","may3r","jun3r","jul3r","ago3r","sep3r","oct3r","nov3r","dic3r"},
                                           {"tipo3O","s3O","ene3o","feb3o","mar3o","abr3o","may3o","jun3o","jul3o","ago3o","sep3o","oct3o","nov3o","dic3o"},
                                           {"tipo4P","s4P","ene4p","feb4p","mar4p","abr4p","may4p","jun4p","jul4p","ago4p","sep4p","oct4p","nov4p","dic4p"},
                                           {"tipo4R","s4R","ene4r","feb4r","mar4r","abr4r","may4r","jun4r","jul4r","ago4r","sep4r","oct4r","nov4r","dic4r"},
                                           {"tipo4O","s4O","ene4o","feb4o","mar4o","abr4o","may4o","jun4o","jul4o","ago4o","sep4o","oct4o","nov4o","dic4o"},
                                           {"tipo5P","s5P","ene5p","feb5p","mar5p","abr5p","may5p","jun5p","jul5p","ago5p","sep5p","oct5p","nov5p","dic5p"},
                                           {"tipo5R","s5R","ene5r","feb5r","mar5r","abr5r","may5r","jun5r","jul5r","ago5r","sep5r","oct5r","nov5r","dic5r"},
                                           {"tipo5O","s5O","ene5o","feb5o","mar5o","abr5o","may5o","jun5o","jul5o","ago5o","sep5o","oct5o","nov5o","dic5o"},
                                           {"tipo6P","s6P","ene6p","feb6p","mar6p","abr6p","may6p","jun6p","jul6p","ago6p","sep6p","oct6p","nov6p","dic6p"},
                                           {"tipo6R","s6R","ene6r","feb6r","mar6r","abr6r","may6r","jun6r","jul6r","ago6r","sep6r","oct6r","nov6r","dic6r"},
                                           {"tipo6O","s6O","ene6o","feb6o","mar6o","abr6o","may6o","jun6o","jul6o","ago6o","sep6o","oct6o","nov6o","dic6o"}};
        
        int tamañoLista = detallePreventivo.size();
        int contador = 0;
        Map<String, Object> parametros = new HashMap<String, Object>();
        
        parametros.put("semestre", preventivo.getId_periodo().getPeriodo());
        parametros.put("año", preventivo.getAño());
        parametros.put("fechaElaboracion", String.valueOf(preventivo.getFecha_elaboracion()));
        parametros.put("elaboro", preventivo.getId_usuario_personal().getConcatenar());
        
        for (DetallePreventivo detalle : detallePreventivo) {//corresponde a los numeros y servicios.
            parametros.put(numero[contador], String.valueOf(detalle.getNumero_servicio()));
            parametros.put(servicios[contador], detalle.getServicio());
        }
        
        for (int i = 0; i < 18; i++) {//correcponde a las filas 
            switch(i){
                
            }
        }
        
        File archivo = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/FormatoMantenimientoPreventivo.jasper"));
        JasperPrint imprimirArchivo = JasperFillManager.fillReport(archivo.getPath(), parametros, new JREmptyDataSource());

        HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        respuesta.addHeader("Content-Disposition", "attachment; filename=\"Mantenimiento_Preventivo.pdf\";");
        ServletOutputStream stream = respuesta.getOutputStream();

        JasperExportManager.exportReportToPdfStream(imprimirArchivo, stream);
        FacesContext.getCurrentInstance().responseComplete();
    }
    
    
}
