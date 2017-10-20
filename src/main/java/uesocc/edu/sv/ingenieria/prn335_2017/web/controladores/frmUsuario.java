package uesocc.edu.sv.ingenieria.prn335_2017.web.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import uesocc.edu.sv.ingenieria.prn335_2017.datos.acceso.UsuarioFacadeLocal;
import uesocc.edu.sv.ingenieria.prn335_2017.datos.definiciones.Usuario;

/**
 *
 * @author cesarlinares
 */
@Named(value = "frmUsuario")
@ViewScoped
public class frmUsuario implements Serializable {

    @EJB
    //nuevo proyecto modificado
    private UsuarioFacadeLocal UsuarioFLocal;
    private LazyDataModel<Usuario> modelodedatos;
    private Usuario RegistroUsuarios;
    private boolean buttonAdd = true;
    private boolean buttons = false;
    private boolean selections = false;

    @PostConstruct
    public void init() {
        RegistroUsuarios = new Usuario();
        try {
            this.modelodedatos = new LazyDataModel<Usuario>() {
                @Override
                public Object getRowKey(Usuario usuariokey) {
                    if (usuariokey != null) {
                        return usuariokey.getIdUsuario();
                    } else {
                        return null;
                    }

                }

                @Override
                public Usuario getRowData(String rowUsuariokey) {
                    if (rowUsuariokey != null && !rowUsuariokey.isEmpty() && this.getWrappedData() != null) {
                        try {
                            Integer buscado = new Integer(rowUsuariokey);
                            for (Usuario registros : (List<Usuario>) getWrappedData()) {
                                if (registros.getIdUsuario().compareTo(buscado) == 0) {
                                    return registros;
                                }

                            }
                        } catch (Exception e) {
                        }

                    }
                    return null;

                }

                @Override
                public List<Usuario> load(int MinpageSize, int MaxpageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                    List<Usuario> listasmostradas = new ArrayList();
                    try {
                        if (UsuarioFLocal != null) {
                            this.setRowCount(UsuarioFLocal.count());
                            listasmostradas = UsuarioFLocal.findRange(MinpageSize, MaxpageSize);

                        }
                    } catch (Exception e) {
                        Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                    }
                    return listasmostradas;

                }

            };

        }  catch (Exception e) {
                        Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            
        }

    }
    
    /*
     metodo cancelar 
     */
   public void Cancelar(){
       this.RegistroUsuarios= new Usuario();
       this.buttonAdd=true;
       this.buttons=false;
   }
   /**
     * METODO PARA GUARDAR EL REGISTRO 
     */
    public void Guardar() {
        try {
            if (this.RegistroUsuarios != null && this.UsuarioFLocal != null) {
               if (this.UsuarioFLocal.create(RegistroUsuarios)) {
                    System.out.println("registro agregado Satisfactoriamente");
                    init();
                }
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    /**
     * METODO PARA ELIMINAR EL REGISTRO 
     */
    public void Eliminar() {
        try {

            if (this.RegistroUsuarios != null && this.UsuarioFLocal != null) {
                if (this.UsuarioFLocal.remove(RegistroUsuarios)) {
                    this.RegistroUsuarios = new Usuario();
                    init();
                }
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    
    /**
     * METODO QUE SE UTILIZA PARA GUARDAR EL REGISTRO QUE EDITAMOS 
     */
    public void Modificar() {
        try {
            if (this.RegistroUsuarios != null && this.UsuarioFLocal != null) {
                if (this.UsuarioFLocal.edit(RegistroUsuarios)) {
                    init();
                    
                }
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    /**
     * ESTE METODO SE USA PARA CAMBIAR LA SELECCION DE LOS BOTONES
     * (ACTIVA O DESACTIVA LOS BOTONES -CREAR- -ELIMINAR- -EDITAR-)
     */
    public void cambiarSeleccion() {
                this.buttons = true;
                this.buttonAdd = false;
                  
    }
    
    // getters y setters
    public UsuarioFacadeLocal getUsuarioFLocal() {
        return UsuarioFLocal;
    }

    public void setUsuarioFLocal(UsuarioFacadeLocal UsuarioFLocal) {
        this.UsuarioFLocal = UsuarioFLocal;
    }

    public LazyDataModel<Usuario> getModelodedatos() {
        return modelodedatos;
    }

    public void setModelodedatos(LazyDataModel<Usuario> modelodedatos) {
        this.modelodedatos = modelodedatos;
    }

    public Usuario getRegistroUsuarios() {
        return RegistroUsuarios;
    }

    public void setRegistroUsuarios(Usuario RegistroUsuarios) {
        this.RegistroUsuarios = RegistroUsuarios;
    }

    public boolean isButtonAdd() {
        return buttonAdd;
    }

    public void setButtonAdd(boolean buttonAdd) {
        this.buttonAdd = buttonAdd;
    }

    public boolean isButtons() {
        return buttons;
    }

    public void setButtons(boolean buttons) {
        this.buttons = buttons;
    }

    public boolean isSelections() {
        return selections;
    }

    public void setSelections(boolean selections) {
        this.selections = selections;
    }

    public UsuarioFacadeLocal getUsuarioEJB() {
        return UsuarioEJB;
    }

    public void setUsuarioEJB(UsuarioFacadeLocal UsuarioEJB) {
        this.UsuarioEJB = UsuarioEJB;
    }
   
    
    
    
    

    private UsuarioFacadeLocal UsuarioEJB;
    private Usuario U;
    private List<Usuario> usuarios;

    /*@PostConstruct
    public void init() {
        usuarios = UsuarioEJB.findAll();
        U = new Usuario();
        
        
        U.setIdUsuario(null);
        U.setNombres("");
        U.setApellidos("");  
        U.setFechaNacimiento(U.getFechaNacimiento());
        U.setActivo(false);
        U.setComentarios("");
        U.setUsername("");
        U.setPassword("");
        
     
    }

     
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    
    public Usuario getU() {
        return U;
    }

    
    public void setU(Usuario U) {
        this.U = U;
    }

    
    public String guardar() {
        if (U != null) {
            System.out.println("Ingreso de datos fue exitoso");
            UsuarioEJB.create(U);
            usuarios = UsuarioEJB.findAll();
        } else {
            System.out.println("No se han podido ingresar los datos ");
        }
        U = new Usuario();
        return "c_guardar";
    }

    
    public String limpiar() {
        System.out.println("ENTRO AL LIMPIADOR");
        U = new Usuario();
        U.setIdUsuario(null);
        U.setNombres("");
        U.setApellidos("");
        U.setFechaNacimiento(U.getFechaNacimiento());
        U.setActivo(false);

        return "continue trabajando";
    }

    public frmUsuario() {

    }
*/
}
