package mx.edu.itoaxaca.reportes;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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

    public void crearReportePreventivo(Preventivo preventivo, List<DetallePreventivo> detallePreventivo) throws JRException, IOException {
        String[] numero = {"num1", "num2", "num3", "num4", "num5", "num6"};
        String[] servicios = {"servicio1", "servicio2", "servicio3", "servicio4", "servicio5", "servicio6"};

        String[][] descripcionServicio = {{"tipo1P", "s1P", "enep1", "febp1", "marp1", "abrp1", "mayp1", "junp1", "julp1", "agop1", "sepp1", "octp1", "novp1", "dicp1"},
        {"tipo1R", "s1R", "ener1", "febr1", "marr1", "abrr1", "mayr1", "junr1", "julr1", "agor1", "sepr1", "octr1", "novr1", "dicr1"},
        {"tipo1O", "s1O", "eneo1", "febo1", "maro1", "abro1", "mayo1", "juno1", "julo1", "agoo1", "sepo1", "octo1", "novo1", "dico1"},
        {"tipo2P", "s2P", "enep2", "febp2", "marp2", "abrp2", "mayp2", "junp2", "julp2", "agop2", "sepp2", "octp2", "novp2", "dicp2"},
        {"tipo2R", "s2R", "ener2", "febr2", "marr2", "abrr2", "mayr2", "junr2", "julr2", "agor2", "sepr2", "octr2", "novr2", "dicr2"},
        {"tipo2O", "s2O", "eneo2", "febo2", "maro2", "abro2", "mayo2", "juno2", "julo2", "agoo2", "sepo2", "octo2", "novo2", "dico2"},
        {"tipo3P", "s3P", "enep3", "febp3", "marp3", "abrp3", "mayp3", "junp3", "julp3", "agop3", "sepp3", "octp3", "novp3", "dicp3"},
        {"tipo3R", "s3R", "ener3", "febr3", "marr3", "abrr3", "mayr3", "junr3", "julr3", "agor3", "sepr3", "octr3", "novr3", "dicr3"},
        {"tipo3O", "s3O", "eneo3", "febo3", "maro3", "abro3", "mayo3", "juno3", "julo3", "agoo3", "sepo3", "octo3", "novo3", "dico3"},
        {"tipo4P", "s4P", "enep4", "febp4", "marp4", "abrp4", "mayp4", "junp4", "julp4", "agop4", "sepp4", "octp4", "novp4", "dicp4"},
        {"tipo4R", "s4R", "ener4", "febr4", "marr4", "abrr4", "mayr4", "junr4", "julr4", "agor4", "sepr4", "octr4", "novr4", "dicr4"},
        {"tipo4O", "s4O", "eneo4", "febo4", "maro4", "abro4", "mayo4", "juno4", "julo4", "agoo4", "sepo4", "octo4", "novo4", "dico4"},
        {"tipo5P", "s5P", "enep5", "febp5", "marp5", "abrp5", "mayp5", "junp5", "julp5", "agop5", "sepp5", "octp5", "novp5", "dicp5"},
        {"tipo5R", "s5R", "ener5", "febr5", "marr5", "abrr5", "mayr5", "junr5", "julr5", "agor5", "sepr5", "octr5", "novr5", "dicr5"},
        {"tipo5O", "s5O", "eneo5", "febo5", "maro5", "abro5", "mayo5", "juno5", "julo5", "agoo5", "sepo5", "octo5", "novo5", "dico5"},
        {"tipo6P", "s6P", "enep6", "febp6", "marp6", "abrp6", "mayp6", "junp6", "julp6", "agop6", "sepp6", "octp6", "novp6", "dicp6"},
        {"tipo6R", "s6R", "ener6", "febr6", "marr6", "abrr6", "mayr6", "junr6", "julr6", "agor6", "sepr6", "octr6", "novr6", "dicr6"},
        {"tipo6O", "s6O", "eneo6", "febo6", "maro6", "abro6", "mayo6", "juno6", "julo6", "agoo6", "sepo6", "octo6", "novo6", "dico6"}};

        int tamañoLista = detallePreventivo.size();
        int contador = 2;//contador para no se aun ?
        try {
            Map<String, Object> parametros = new HashMap<String, Object>();

            parametros.put("semestre", preventivo.getId_periodo().getPeriodo());
            parametros.put("año", preventivo.getAño());
            parametros.put("fechaElaboracion", String.valueOf(preventivo.getFecha_elaboracion()));
            parametros.put("elaboro", preventivo.getId_usuario_personal().getConcatenar());

            for (int i = 0; i < servicios.length; i++) {

                if (i < tamañoLista) {
                    DetallePreventivo detalle = detallePreventivo.get(i);
                    parametros.put(servicios[i], detalle.getServicio());
                    //System.out.println("numero "+detalle.getNumero_servicio());
                    parametros.put(numero[i], String.valueOf(detalle.getNumero_servicio()));
                } else {
                    parametros.put(servicios[i], " ");
                    parametros.put(numero[i], " ");
                }

            }

            for (int i = 2; i < 18; i = i + 3) {//corresponde a filas
                for (int j = 0; j < 14; j++) {//correspnde a columnas

                    DetallePreventivo detalle = null;
                    if ((i - contador) < tamañoLista) {
                        detalle = detallePreventivo.get(i - contador);

                        if (detalle.getNumero_servicio() == 1) {
                            if (detalle.getEstado().equalsIgnoreCase("programada")) {
                                if (detalle.getTipo_servicio().equalsIgnoreCase("interno")) {
                                    parametros.put(descripcionServicio[i - 2][0], "X");
                                    parametros.put(descripcionServicio[i - 2][1], "I");
                                } else {
                                    parametros.put(descripcionServicio[i - 2][0], "X");
                                    parametros.put(descripcionServicio[i - 2][1], "E");
                                }

                                if (detalle.getFecha().getMonth() + 2 == j) {
                                    parametros.put(descripcionServicio[i - 2][j], String.valueOf(detalle.getFecha().getDate()));
                                    parametros.put(descripcionServicio[i - 1][j], " ");
                                    parametros.put(descripcionServicio[i][j], " ");

                                } else {
                                    parametros.put(descripcionServicio[i - 2][j], " ");
                                    parametros.put(descripcionServicio[i - 1][j], " ");
                                    parametros.put(descripcionServicio[i][j], " ");

                                }

                            }
                            if (detalle.getEstado().equalsIgnoreCase("realizada")) {
                                if (detalle.getTipo_servicio().equalsIgnoreCase("interno")) {
                                    parametros.put(descripcionServicio[i - 1][0], "X");
                                    parametros.put(descripcionServicio[i - 1][1], "I");
                                } else {
                                    parametros.put(descripcionServicio[i - 1][0], "X");
                                    parametros.put(descripcionServicio[i - 1][1], "E");
                                }
                                if (detalle.getFecha().getMonth() + 2 == j) {
                                    parametros.put(descripcionServicio[i - 1][j], String.valueOf(detalle.getFecha().getDate()));
                                    parametros.put(descripcionServicio[i - 2][j], " ");
                                    parametros.put(descripcionServicio[i][j], " ");
                                } else {
                                    parametros.put(descripcionServicio[i - 2][j], " ");
                                    parametros.put(descripcionServicio[i - 1][j], " ");
                                    parametros.put(descripcionServicio[i][j], " ");
                                }
                            }
                            if (detalle.getEstado().equalsIgnoreCase("reprogramada")) {
                                if (detalle.getTipo_servicio().equalsIgnoreCase("interno")) {
                                    parametros.put(descripcionServicio[i][0], "X");
                                    parametros.put(descripcionServicio[i][1], "I");
                                } else {
                                    parametros.put(descripcionServicio[i][0], "X");
                                    parametros.put(descripcionServicio[i][1], "E");
                                }
                                if (detalle.getFecha().getMonth() + 2 == j) {
                                    parametros.put(descripcionServicio[i][j], String.valueOf(detalle.getFecha().getDate()));
                                    parametros.put(descripcionServicio[i - 2][j], " ");
                                    parametros.put(descripcionServicio[i - 1][j], " ");
                                } else {
                                    parametros.put(descripcionServicio[i - 2][j], " ");
                                    parametros.put(descripcionServicio[i - 1][j], " ");
                                    parametros.put(descripcionServicio[i][j], " ");
                                }
                            }

                        }
                        if (detalle.getNumero_servicio() == 2) {
                            if (detalle.getEstado().equalsIgnoreCase("programada")) {
                                if (detalle.getTipo_servicio().equalsIgnoreCase("interno")) {
                                    parametros.put(descripcionServicio[i - 2][0], "X");
                                    parametros.put(descripcionServicio[i - 2][1], "I");
                                } else {
                                    parametros.put(descripcionServicio[i - 2][0], "X");
                                    parametros.put(descripcionServicio[i - 2][1], "E");
                                }

                                if (detalle.getFecha().getMonth() + 2 == j) {
                                    parametros.put(descripcionServicio[i - 2][j], String.valueOf(detalle.getFecha().getDate()));
                                    parametros.put(descripcionServicio[i - 1][j], " ");
                                    parametros.put(descripcionServicio[i][j], " ");

                                } else {
                                    parametros.put(descripcionServicio[i - 2][j], " ");
                                    parametros.put(descripcionServicio[i - 1][j], " ");
                                    parametros.put(descripcionServicio[i][j], " ");

                                }

                            }
                            if (detalle.getEstado().equalsIgnoreCase("realizada")) {
                                if (detalle.getTipo_servicio().equalsIgnoreCase("interno")) {
                                    parametros.put(descripcionServicio[i - 1][0], "X");
                                    parametros.put(descripcionServicio[i - 1][1], "I");
                                } else {
                                    parametros.put(descripcionServicio[i - 1][0], "X");
                                    parametros.put(descripcionServicio[i - 1][1], "E");
                                }
                                if (detalle.getFecha().getMonth() + 2 == j) {
                                    parametros.put(descripcionServicio[i - 1][j], String.valueOf(detalle.getFecha().getDate()));
                                    parametros.put(descripcionServicio[i - 2][j], " ");
                                    parametros.put(descripcionServicio[i][j], " ");
                                } else {
                                    parametros.put(descripcionServicio[i - 2][j], " ");
                                    parametros.put(descripcionServicio[i - 1][j], " ");
                                    parametros.put(descripcionServicio[i][j], " ");
                                }
                            }
                            if (detalle.getEstado().equalsIgnoreCase("reprogramada")) {
                                if (detalle.getTipo_servicio().equalsIgnoreCase("interno")) {
                                    parametros.put(descripcionServicio[i][0], "X");
                                    parametros.put(descripcionServicio[i][1], "I");
                                } else {
                                    parametros.put(descripcionServicio[i][0], "X");
                                    parametros.put(descripcionServicio[i][1], "E");
                                }
                                if (detalle.getFecha().getMonth() + 2 == j) {
                                    parametros.put(descripcionServicio[i][j], String.valueOf(detalle.getFecha().getDate()));
                                    parametros.put(descripcionServicio[i - 2][j], " ");
                                    parametros.put(descripcionServicio[i - 1][j], " ");
                                } else {
                                    parametros.put(descripcionServicio[i - 2][j], " ");
                                    parametros.put(descripcionServicio[i - 1][j], " ");
                                    parametros.put(descripcionServicio[i][j], " ");
                                }
                            }
                        }
                        if (detalle.getNumero_servicio() == 3) {
                            if (detalle.getEstado().equalsIgnoreCase("programada")) {
                                if (detalle.getTipo_servicio().equalsIgnoreCase("interno")) {
                                    parametros.put(descripcionServicio[i - 2][0], "X");
                                    parametros.put(descripcionServicio[i - 2][1], "I");
                                } else {
                                    parametros.put(descripcionServicio[i - 2][0], "X");
                                    parametros.put(descripcionServicio[i - 2][1], "E");
                                }

                                if (detalle.getFecha().getMonth() + 2 == j) {
                                    parametros.put(descripcionServicio[i - 2][j], String.valueOf(detalle.getFecha().getDate()));
                                    parametros.put(descripcionServicio[i - 1][j], " ");
                                    parametros.put(descripcionServicio[i][j], " ");

                                } else {
                                    parametros.put(descripcionServicio[i - 2][j], " ");
                                    parametros.put(descripcionServicio[i - 1][j], " ");
                                    parametros.put(descripcionServicio[i][j], " ");

                                }

                            }
                            if (detalle.getEstado().equalsIgnoreCase("realizada")) {
                                if (detalle.getTipo_servicio().equalsIgnoreCase("interno")) {
                                    parametros.put(descripcionServicio[i - 1][0], "X");
                                    parametros.put(descripcionServicio[i - 1][1], "I");
                                } else {
                                    parametros.put(descripcionServicio[i - 1][0], "X");
                                    parametros.put(descripcionServicio[i - 1][1], "E");
                                }
                                if (detalle.getFecha().getMonth() + 2 == j) {
                                    parametros.put(descripcionServicio[i - 1][j], String.valueOf(detalle.getFecha().getDate()));
                                    parametros.put(descripcionServicio[i - 2][j], " ");
                                    parametros.put(descripcionServicio[i][j], " ");
                                } else {
                                    parametros.put(descripcionServicio[i - 2][j], " ");
                                    parametros.put(descripcionServicio[i - 1][j], " ");
                                    parametros.put(descripcionServicio[i][j], " ");
                                }
                            }
                            if (detalle.getEstado().equalsIgnoreCase("reprogramada")) {
                                if (detalle.getTipo_servicio().equalsIgnoreCase("interno")) {
                                    parametros.put(descripcionServicio[i][0], "X");
                                    parametros.put(descripcionServicio[i][1], "I");
                                } else {
                                    parametros.put(descripcionServicio[i][0], "X");
                                    parametros.put(descripcionServicio[i][1], "E");
                                }
                                if (detalle.getFecha().getMonth() + 2 == j) {
                                    parametros.put(descripcionServicio[i][j], String.valueOf(detalle.getFecha().getDate()));
                                    parametros.put(descripcionServicio[i - 2][j], " ");
                                    parametros.put(descripcionServicio[i - 1][j], " ");
                                } else {
                                    parametros.put(descripcionServicio[i - 2][j], " ");
                                    parametros.put(descripcionServicio[i - 1][j], " ");
                                    parametros.put(descripcionServicio[i][j], " ");
                                }
                            }
                        }
                        if (detalle.getNumero_servicio() == 4) {
                            if (detalle.getEstado().equalsIgnoreCase("programada")) {
                                if (detalle.getTipo_servicio().equalsIgnoreCase("interno")) {
                                    parametros.put(descripcionServicio[i - 2][0], "X");
                                    parametros.put(descripcionServicio[i - 2][1], "I");
                                } else {
                                    parametros.put(descripcionServicio[i - 2][0], "X");
                                    parametros.put(descripcionServicio[i - 2][1], "E");
                                }

                                if (detalle.getFecha().getMonth() + 2 == j) {
                                    parametros.put(descripcionServicio[i - 2][j], String.valueOf(detalle.getFecha().getDate()));
                                    parametros.put(descripcionServicio[i - 1][j], " ");
                                    parametros.put(descripcionServicio[i][j], " ");

                                } else {
                                    parametros.put(descripcionServicio[i - 2][j], " ");
                                    parametros.put(descripcionServicio[i - 1][j], " ");
                                    parametros.put(descripcionServicio[i][j], " ");

                                }

                            }
                            if (detalle.getEstado().equalsIgnoreCase("realizada")) {
                                if (detalle.getTipo_servicio().equalsIgnoreCase("interno")) {
                                    parametros.put(descripcionServicio[i - 1][0], "X");
                                    parametros.put(descripcionServicio[i - 1][1], "I");
                                } else {
                                    parametros.put(descripcionServicio[i - 1][0], "X");
                                    parametros.put(descripcionServicio[i - 1][1], "E");
                                }
                                if (detalle.getFecha().getMonth() + 2 == j) {
                                    parametros.put(descripcionServicio[i - 1][j], String.valueOf(detalle.getFecha().getDate()));
                                    parametros.put(descripcionServicio[i - 2][j], " ");
                                    parametros.put(descripcionServicio[i][j], " ");
                                } else {
                                    parametros.put(descripcionServicio[i - 2][j], " ");
                                    parametros.put(descripcionServicio[i - 1][j], " ");
                                    parametros.put(descripcionServicio[i][j], " ");
                                }
                            }
                            if (detalle.getEstado().equalsIgnoreCase("reprogramada")) {
                                if (detalle.getTipo_servicio().equalsIgnoreCase("interno")) {
                                    parametros.put(descripcionServicio[i][0], "X");
                                    parametros.put(descripcionServicio[i][1], "I");
                                } else {
                                    parametros.put(descripcionServicio[i][0], "X");
                                    parametros.put(descripcionServicio[i][1], "E");
                                }
                                if (detalle.getFecha().getMonth() + 2 == j) {
                                    parametros.put(descripcionServicio[i][j], String.valueOf(detalle.getFecha().getDate()));
                                    parametros.put(descripcionServicio[i - 2][j], " ");
                                    parametros.put(descripcionServicio[i - 1][j], " ");
                                } else {
                                    parametros.put(descripcionServicio[i - 2][j], " ");
                                    parametros.put(descripcionServicio[i - 1][j], " ");
                                    parametros.put(descripcionServicio[i][j], " ");
                                }
                            }
                        }
                        if (detalle.getNumero_servicio() == 5) {
                            if (detalle.getEstado().equalsIgnoreCase("programada")) {
                                if (detalle.getTipo_servicio().equalsIgnoreCase("interno")) {
                                    parametros.put(descripcionServicio[i - 2][0], "X");
                                    parametros.put(descripcionServicio[i - 2][1], "I");
                                } else {
                                    parametros.put(descripcionServicio[i - 2][0], "X");
                                    parametros.put(descripcionServicio[i - 2][1], "E");
                                }

                                if (detalle.getFecha().getMonth() + 2 == j) {
                                    parametros.put(descripcionServicio[i - 2][j], String.valueOf(detalle.getFecha().getDate()));
                                    parametros.put(descripcionServicio[i - 1][j], " ");
                                    parametros.put(descripcionServicio[i][j], " ");

                                } else {
                                    parametros.put(descripcionServicio[i - 2][j], " ");
                                    parametros.put(descripcionServicio[i - 1][j], " ");
                                    parametros.put(descripcionServicio[i][j], " ");

                                }

                            }
                            if (detalle.getEstado().equalsIgnoreCase("realizada")) {
                                if (detalle.getTipo_servicio().equalsIgnoreCase("interno")) {
                                    parametros.put(descripcionServicio[i - 1][0], "X");
                                    parametros.put(descripcionServicio[i - 1][1], "I");
                                } else {
                                    parametros.put(descripcionServicio[i - 1][0], "X");
                                    parametros.put(descripcionServicio[i - 1][1], "E");
                                }
                                if (detalle.getFecha().getMonth() + 2 == j) {
                                    parametros.put(descripcionServicio[i - 1][j], String.valueOf(detalle.getFecha().getDate()));
                                    parametros.put(descripcionServicio[i - 2][j], " ");
                                    parametros.put(descripcionServicio[i][j], " ");
                                } else {
                                    parametros.put(descripcionServicio[i - 2][j], " ");
                                    parametros.put(descripcionServicio[i - 1][j], " ");
                                    parametros.put(descripcionServicio[i][j], " ");
                                }
                            }
                            if (detalle.getEstado().equalsIgnoreCase("reprogramada")) {
                                if (detalle.getTipo_servicio().equalsIgnoreCase("interno")) {
                                    parametros.put(descripcionServicio[i][0], "X");
                                    parametros.put(descripcionServicio[i][1], "I");
                                } else {
                                    parametros.put(descripcionServicio[i][0], "X");
                                    parametros.put(descripcionServicio[i][1], "E");
                                }
                                if (detalle.getFecha().getMonth() + 2 == j) {
                                    parametros.put(descripcionServicio[i][j], String.valueOf(detalle.getFecha().getDate()));
                                    parametros.put(descripcionServicio[i - 2][j], " ");
                                    parametros.put(descripcionServicio[i - 1][j], " ");
                                } else {
                                    parametros.put(descripcionServicio[i - 2][j], " ");
                                    parametros.put(descripcionServicio[i - 1][j], " ");
                                    parametros.put(descripcionServicio[i][j], " ");
                                }
                            }
                        }
                        if (detalle.getNumero_servicio() == 6) {
                            if (detalle.getEstado().equalsIgnoreCase("programada")) {
                                if (detalle.getTipo_servicio().equalsIgnoreCase("interno")) {
                                    parametros.put(descripcionServicio[i - 2][0], "X");
                                    parametros.put(descripcionServicio[i - 2][1], "I");
                                } else {
                                    parametros.put(descripcionServicio[i - 2][0], "X");
                                    parametros.put(descripcionServicio[i - 2][1], "E");
                                }

                                if (detalle.getFecha().getMonth() + 2 == j) {
                                    parametros.put(descripcionServicio[i - 2][j], String.valueOf(detalle.getFecha().getDate()));
                                    parametros.put(descripcionServicio[i - 1][j], " ");
                                    parametros.put(descripcionServicio[i][j], " ");

                                } else {
                                    parametros.put(descripcionServicio[i - 2][j], " ");
                                    parametros.put(descripcionServicio[i - 1][j], " ");
                                    parametros.put(descripcionServicio[i][j], " ");

                                }

                            }
                            if (detalle.getEstado().equalsIgnoreCase("realizada")) {
                                if (detalle.getTipo_servicio().equalsIgnoreCase("interno")) {
                                    parametros.put(descripcionServicio[i - 1][0], "X");
                                    parametros.put(descripcionServicio[i - 1][1], "I");
                                } else {
                                    parametros.put(descripcionServicio[i - 1][0], "X");
                                    parametros.put(descripcionServicio[i - 1][1], "E");
                                }
                                if (detalle.getFecha().getMonth() + 2 == j) {
                                    parametros.put(descripcionServicio[i - 1][j], String.valueOf(detalle.getFecha().getDate()));
                                    parametros.put(descripcionServicio[i - 2][j], " ");
                                    parametros.put(descripcionServicio[i][j], " ");
                                } else {
                                    parametros.put(descripcionServicio[i - 2][j], " ");
                                    parametros.put(descripcionServicio[i - 1][j], " ");
                                    parametros.put(descripcionServicio[i][j], " ");
                                }
                            }
                            if (detalle.getEstado().equalsIgnoreCase("reprogramada")) {
                                if (detalle.getTipo_servicio().equalsIgnoreCase("interno")) {
                                    parametros.put(descripcionServicio[i][0], "X");
                                    parametros.put(descripcionServicio[i][1], "I");
                                } else {
                                    parametros.put(descripcionServicio[i][0], "X");
                                    parametros.put(descripcionServicio[i][1], "E");
                                }
                                if (detalle.getFecha().getMonth() + 2 == j) {
                                    parametros.put(descripcionServicio[i][j], String.valueOf(detalle.getFecha().getDate()));
                                    parametros.put(descripcionServicio[i - 2][j], " ");
                                    parametros.put(descripcionServicio[i - 1][j], " ");
                                } else {
                                    parametros.put(descripcionServicio[i - 2][j], " ");
                                    parametros.put(descripcionServicio[i - 1][j], " ");
                                    parametros.put(descripcionServicio[i][j], " ");
                                }
                            }
                        }
                    }
                    if (detalle == null) {
                        parametros.put(descripcionServicio[i - 2][j], " ");
                        parametros.put(descripcionServicio[i - 1][j], " ");
                        parametros.put(descripcionServicio[i][j], " ");
                    }

                }
                contador = contador + 2;
            }

            File archivo = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/FormatoMantenimientoPreventivo.jasper"));
            JasperPrint imprimirArchivo = JasperFillManager.fillReport(archivo.getPath(), parametros, new JREmptyDataSource());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            respuesta.addHeader("Content-Disposition", "attachment; filename=\"Mantenimiento_Preventivo.pdf\";");
            ServletOutputStream stream = respuesta.getOutputStream();

            JasperExportManager.exportReportToPdfStream(imprimirArchivo, stream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception ex) {
            System.out.println("Error en ReportePreventivo -> crearReportePreventivo " + ex);
        }
    }

}
