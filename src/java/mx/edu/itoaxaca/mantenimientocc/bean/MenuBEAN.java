package mx.edu.itoaxaca.mantenimientocc.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import mx.edu.itoaxaca.mantenimientocc.dao.MenuDAO;
import mx.edu.itoaxaca.mantenimientocc.modelo.Menu;
import mx.edu.itoaxaca.mantenimientocc.modelo.Usuario;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author leiver
 */
@ManagedBean
@SessionScoped
public class MenuBEAN implements Serializable {

    private MenuModel menuPrincipal;
    private List<Menu> listaMenu;

    @PostConstruct
    public void crearMenu() {
        MenuDAO menuDao = new MenuDAO();
        menuPrincipal = new DefaultMenuModel();
        //obtenemos que usuario esta activo
        Usuario usuarioActivo = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        try {
            listaMenu = menuDao.listarMenuPorUsuario(usuarioActivo.getNivel());//lista con los elementos del menu
            if (usuarioActivo.getIdOficina().getDepartamento().getClave_departamento().equalsIgnoreCase("DCC")) {
                for (Menu menu : listaMenu) {//primer for que generara el menu principal
                    if (menu.getId_elemento_menu().getTipo_menu().equalsIgnoreCase("submenu") && menu.getId_elemento_menu().getCodigo_submenu() == 0) {//condicion que comprueba si es un submenu principal
                        DefaultSubMenu subMenuPrincipal = new DefaultSubMenu(menu.getId_elemento_menu().getNombre());
                        subMenuPrincipal.setIcon(menu.getId_elemento_menu().getIcono());
                        subMenuPrincipal.setStyle("margin-right: 1em;");

                        for (Menu menuInterno : listaMenu) {
                            if (menuInterno.getId_elemento_menu().getTipo_menu().equalsIgnoreCase("submenu") && menuInterno.getId_elemento_menu().getCodigo_submenu() != 0) {

                                if (menuInterno.getId_elemento_menu().getCodigo_submenu() == menu.getId_elemento_menu().getIdelemento_menu()) {
                                    DefaultSubMenu subMenuSecundario = new DefaultSubMenu(menuInterno.getId_elemento_menu().getNombre());
                                    subMenuSecundario.setIcon(menuInterno.getId_elemento_menu().getIcono());

                                    for (Menu subMenuInterno : listaMenu) {
                                        if (subMenuInterno.getId_elemento_menu().getTipo_menu().equalsIgnoreCase("submenu") && subMenuInterno.getId_elemento_menu().getCodigo_submenu() != 0) {
                                            if (subMenuInterno.getId_elemento_menu().getCodigo_submenu() == menuInterno.getId_elemento_menu().getIdelemento_menu()) {
                                                DefaultSubMenu subMenuTerceario = new DefaultSubMenu(subMenuInterno.getId_elemento_menu().getNombre());
                                                subMenuTerceario.setIcon(subMenuInterno.getId_elemento_menu().getIcono());

                                                for (Menu subSubMenuInterno : listaMenu) {
                                                    if (subSubMenuInterno.getId_elemento_menu().getTipo_menu().equalsIgnoreCase("submenu") && subSubMenuInterno.getId_elemento_menu().getCodigo_submenu() != 0) {
                                                        if (subSubMenuInterno.getId_elemento_menu().getCodigo_submenu() == subMenuInterno.getId_elemento_menu().getIdelemento_menu()) {
                                                            DefaultSubMenu subMenuCuarto = new DefaultSubMenu(subSubMenuInterno.getId_elemento_menu().getNombre());
                                                            subMenuCuarto.setIcon(subSubMenuInterno.getId_elemento_menu().getIcono());
                                                            for (Menu subSubSubMenu : listaMenu) {
                                                                if (subSubSubMenu.getId_elemento_menu().getTipo_menu().equalsIgnoreCase("submenu") && subSubSubMenu.getId_elemento_menu().getCodigo_submenu() != 0) {
                                                                    if (subSubSubMenu.getId_elemento_menu().getCodigo_submenu() == subSubMenuInterno.getId_elemento_menu().getIdelemento_menu()) {
                                                                        DefaultSubMenu subMenuQuinto = new DefaultSubMenu(subSubSubMenu.getId_elemento_menu().getNombre());
                                                                        subMenuQuinto.setIcon(subSubSubMenu.getId_elemento_menu().getIcono());
                                                                        //aca se puede agregar mas submenus
                                                                        subMenuCuarto.addElement(subMenuQuinto);
                                                                    }
                                                                } else if (subSubSubMenu.getId_elemento_menu().getCodigo_submenu() == subSubMenuInterno.getId_elemento_menu().getIdelemento_menu()) {
                                                                    DefaultMenuItem itemSubMenu = new DefaultMenuItem(subSubSubMenu.getId_elemento_menu().getNombre());
                                                                    itemSubMenu.setIcon(subSubSubMenu.getId_elemento_menu().getIcono());
                                                                    itemSubMenu.setCommand(subSubSubMenu.getId_elemento_menu().getVista());
                                                                    subMenuCuarto.addElement(itemSubMenu);
                                                                }
                                                            }
                                                            subMenuTerceario.addElement(subMenuCuarto);
                                                        }
                                                    } else if (subSubMenuInterno.getId_elemento_menu().getCodigo_submenu() == subMenuInterno.getId_elemento_menu().getIdelemento_menu()) {
                                                        DefaultMenuItem itemSubMenu = new DefaultMenuItem(subSubMenuInterno.getId_elemento_menu().getNombre());
                                                        itemSubMenu.setIcon(subSubMenuInterno.getId_elemento_menu().getIcono());
                                                        itemSubMenu.setCommand(subSubMenuInterno.getId_elemento_menu().getVista());
                                                        subMenuTerceario.addElement(itemSubMenu);
                                                    }
                                                }
                                                subMenuSecundario.addElement(subMenuTerceario);
                                            }
                                        } else if (subMenuInterno.getId_elemento_menu().getCodigo_submenu() == menuInterno.getId_elemento_menu().getIdelemento_menu()) {
                                            DefaultMenuItem itemSubMenu = new DefaultMenuItem(subMenuInterno.getId_elemento_menu().getNombre());
                                            itemSubMenu.setIcon(subMenuInterno.getId_elemento_menu().getIcono());
                                            itemSubMenu.setCommand(subMenuInterno.getId_elemento_menu().getVista());
                                            subMenuSecundario.addElement(itemSubMenu);
                                        }
                                    }
                                    subMenuPrincipal.addElement(subMenuSecundario);
                                }

                            } else if (menuInterno.getId_elemento_menu().getCodigo_submenu() == menu.getId_elemento_menu().getIdelemento_menu()) {
                                DefaultMenuItem itemSubMenu = new DefaultMenuItem(menuInterno.getId_elemento_menu().getNombre());
                                itemSubMenu.setIcon(menuInterno.getId_elemento_menu().getIcono());
                                itemSubMenu.setCommand(menuInterno.getId_elemento_menu().getVista());
                                subMenuPrincipal.addElement(itemSubMenu);
                            }
                        }
                        menuPrincipal.addElement(subMenuPrincipal);
                    } else if (menu.getId_elemento_menu().getCodigo_submenu() == 0) {
                        DefaultMenuItem itemPrincipal = new DefaultMenuItem(menu.getId_elemento_menu().getNombre());
                        itemPrincipal.setIcon(menu.getId_elemento_menu().getIcono());
                        itemPrincipal.setCommand(menu.getId_elemento_menu().getVista());
                        itemPrincipal.setStyle("margin-right: 1em;");
                        menuPrincipal.addElement(itemPrincipal);
                        //listaMenu.remove(menu);
                    }

                }

            } else if (!usuarioActivo.getIdOficina().getDepartamento().getClave_departamento().equalsIgnoreCase("cc")) {
                for (Menu menu : listaMenu) {//primer for que generara el menu principal
                    if (menu.getId_elemento_menu().getTipo_menu().equalsIgnoreCase("submenu") && menu.getId_elemento_menu().getCodigo_submenu() == 0 && menu.getId_elemento_menu().getDepartamento()==null){//condicion que comprueba si es un submenu principal
                        DefaultSubMenu subMenuPrincipal = new DefaultSubMenu(menu.getId_elemento_menu().getNombre());
                        subMenuPrincipal.setIcon(menu.getId_elemento_menu().getIcono());
                        subMenuPrincipal.setStyle("margin-right: 1em;");

                        for (Menu menuInterno : listaMenu) {
                            if (menuInterno.getId_elemento_menu().getTipo_menu().equalsIgnoreCase("submenu") && menuInterno.getId_elemento_menu().getCodigo_submenu() != 0 && menuInterno.getId_elemento_menu().getDepartamento()==null) {

                                if (menuInterno.getId_elemento_menu().getCodigo_submenu() == menu.getId_elemento_menu().getIdelemento_menu()) {
                                    DefaultSubMenu subMenuSecundario = new DefaultSubMenu(menuInterno.getId_elemento_menu().getNombre());
                                    subMenuSecundario.setIcon(menuInterno.getId_elemento_menu().getIcono());

                                    for (Menu subMenuInterno : listaMenu) {
                                        if (subMenuInterno.getId_elemento_menu().getTipo_menu().equalsIgnoreCase("submenu") && subMenuInterno.getId_elemento_menu().getCodigo_submenu() != 0 && subMenuInterno.getId_elemento_menu().getDepartamento() == null) {
                                            if (subMenuInterno.getId_elemento_menu().getCodigo_submenu() == menuInterno.getId_elemento_menu().getIdelemento_menu()) {
                                                DefaultSubMenu subMenuTerceario = new DefaultSubMenu(subMenuInterno.getId_elemento_menu().getNombre());
                                                subMenuTerceario.setIcon(subMenuInterno.getId_elemento_menu().getIcono());

                                                for (Menu subSubMenuInterno : listaMenu) {
                                                    if (subSubMenuInterno.getId_elemento_menu().getTipo_menu().equalsIgnoreCase("submenu") && subSubMenuInterno.getId_elemento_menu().getCodigo_submenu() != 0 && subSubMenuInterno.getId_elemento_menu().getDepartamento() == null) {
                                                        if (subSubMenuInterno.getId_elemento_menu().getCodigo_submenu() == subMenuInterno.getId_elemento_menu().getIdelemento_menu()) {
                                                            DefaultSubMenu subMenuCuarto = new DefaultSubMenu(subSubMenuInterno.getId_elemento_menu().getNombre());
                                                            subMenuCuarto.setIcon(subSubMenuInterno.getId_elemento_menu().getIcono());
                                                            for (Menu subSubSubMenu : listaMenu) {
                                                                if (subSubSubMenu.getId_elemento_menu().getTipo_menu().equalsIgnoreCase("submenu") && subSubSubMenu.getId_elemento_menu().getCodigo_submenu() != 0 && subSubSubMenu.getId_elemento_menu().getDepartamento() == null) {
                                                                    if (subSubSubMenu.getId_elemento_menu().getCodigo_submenu() == subSubMenuInterno.getId_elemento_menu().getIdelemento_menu()) {
                                                                        DefaultSubMenu subMenuQuinto = new DefaultSubMenu(subSubSubMenu.getId_elemento_menu().getNombre());
                                                                        subMenuQuinto.setIcon(subSubSubMenu.getId_elemento_menu().getIcono());
                                                                        //aca se puede agregar mas submenus
                                                                        subMenuCuarto.addElement(subMenuQuinto);
                                                                    }
                                                                } else if (subSubSubMenu.getId_elemento_menu().getCodigo_submenu() == subSubMenuInterno.getId_elemento_menu().getIdelemento_menu() && subSubSubMenu.getId_elemento_menu().getDepartamento() == null) {
                                                                    DefaultMenuItem itemSubMenu = new DefaultMenuItem(subSubSubMenu.getId_elemento_menu().getNombre());
                                                                    itemSubMenu.setIcon(subSubSubMenu.getId_elemento_menu().getIcono());
                                                                    itemSubMenu.setCommand(subSubSubMenu.getId_elemento_menu().getVista());
                                                                    subMenuCuarto.addElement(itemSubMenu);
                                                                }
                                                            }
                                                            subMenuTerceario.addElement(subMenuCuarto);
                                                        }
                                                    } else if (subSubMenuInterno.getId_elemento_menu().getCodigo_submenu() == subMenuInterno.getId_elemento_menu().getIdelemento_menu() && subSubMenuInterno.getId_elemento_menu().getDepartamento() == null) {
                                                        DefaultMenuItem itemSubMenu = new DefaultMenuItem(subSubMenuInterno.getId_elemento_menu().getNombre());
                                                        itemSubMenu.setIcon(subSubMenuInterno.getId_elemento_menu().getIcono());
                                                        itemSubMenu.setCommand(subSubMenuInterno.getId_elemento_menu().getVista());
                                                        subMenuTerceario.addElement(itemSubMenu);
                                                    }
                                                }
                                                subMenuSecundario.addElement(subMenuTerceario);
                                            }
                                        } else if (subMenuInterno.getId_elemento_menu().getCodigo_submenu() == menuInterno.getId_elemento_menu().getIdelemento_menu() && subMenuInterno.getId_elemento_menu().getDepartamento() == null) {
                                            DefaultMenuItem itemSubMenu = new DefaultMenuItem(subMenuInterno.getId_elemento_menu().getNombre());
                                            itemSubMenu.setIcon(subMenuInterno.getId_elemento_menu().getIcono());
                                            itemSubMenu.setCommand(subMenuInterno.getId_elemento_menu().getVista());
                                            subMenuSecundario.addElement(itemSubMenu);
                                        }
                                    }
                                    subMenuPrincipal.addElement(subMenuSecundario);
                                }

                            } else if (menuInterno.getId_elemento_menu().getCodigo_submenu() == menu.getId_elemento_menu().getIdelemento_menu() && menuInterno.getId_elemento_menu().getDepartamento() == null) {
                                DefaultMenuItem itemSubMenu = new DefaultMenuItem(menuInterno.getId_elemento_menu().getNombre());
                                itemSubMenu.setIcon(menuInterno.getId_elemento_menu().getIcono());
                                itemSubMenu.setCommand(menuInterno.getId_elemento_menu().getVista());
                                subMenuPrincipal.addElement(itemSubMenu);
                            }
                        }
                        menuPrincipal.addElement(subMenuPrincipal);
                    } else if (menu.getId_elemento_menu().getCodigo_submenu() == 0 && menu.getId_elemento_menu().getDepartamento() == null) {
                        DefaultMenuItem itemPrincipal = new DefaultMenuItem(menu.getId_elemento_menu().getNombre());
                        itemPrincipal.setIcon(menu.getId_elemento_menu().getIcono());
                        itemPrincipal.setCommand(menu.getId_elemento_menu().getVista());
                        itemPrincipal.setStyle("margin-right: 1em;");
                        menuPrincipal.addElement(itemPrincipal);
                        //listaMenu.remove(menu);
                    }

                }
            }
        } catch (Exception ex) {
            System.out.println("Error en MenuBEAN -> crearMenu " + ex);

        }
    }

    public MenuModel getMenuPrincipal() {
        return menuPrincipal;
    }

    public void setMenuPrincipal(MenuModel menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
    }
    
    public void cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
}
