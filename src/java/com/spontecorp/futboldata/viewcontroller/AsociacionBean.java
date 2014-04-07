/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Asociacion;
import com.spontecorp.futboldata.entity.Direccion;
import com.spontecorp.futboldata.entity.Telefono;
import com.spontecorp.futboldata.jpacontroller.AsociacionFacade;
import com.spontecorp.futboldata.jpacontroller.DireccionFacede;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author sponte03
 */
@ManagedBean(name = "asociacionBean")
@SessionScoped
public class AsociacionBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Asociacion asociacion ; 
    private List<Telefono> telefonos = null;
    private transient DataModel items = null;
    private transient DataModel itemsAsociacion = null;

  
    private final AsociacionFacade controllerAsociacion;
    private final transient EntityManagerFactory emf = Util.getEmf();
    private final DireccionFacede controllerDireccion;

    /**
     * Creates a new instance of LocalidadBean
     */
    public AsociacionBean()   {
     
        controllerAsociacion = new AsociacionFacade(Asociacion.class);
        controllerDireccion = new DireccionFacede(Direccion.class);
    }

    
    
        public String edit(){
        try {
            if(controllerAsociacion.find(asociacion.getId()) == null ){
                Util.addErrorMessage("Asociacion no existente");
                return prepareList();
            } else {
            
                controllerAsociacion.edit(asociacion);
                Util.addSuccessMessage("Asociacion editado con éxito");
                
                return prepareList();
            }
        } catch (Exception e) {
            Util.addErrorMessage(e, "Error al editar la asociacion");
            return null;
        }
    }
        
    public Asociacion getAsociacion() {
        return asociacion;
    }

    public void setAsociacion(Asociacion asociacion) {
        this.asociacion = asociacion;
    }

    public DataModel getItemsAsociacion() {

        if (itemsAsociacion == null) {
            itemsAsociacion = new ListDataModel(controllerAsociacion.findAll());     
        }
        return itemsAsociacion;

    }
    public String getTelefonos(Direccion direccion){
        System.out.print("Esta es la direaccion que trata de buscar "+direccion.getId().toString()+"***");
        telefonos = controllerDireccion.findListTelefonoxDireaccion(direccion);
        return telefonos.get(0).toString();
        
    }
    public DataModel getItems() {

        if (items == null) {
            items = new ListDataModel(controllerAsociacion.findAll());
        }
        return items;

    }

    public String gotoAsociacionPage() {
        recreateModel();
        return "/admin/asociacion/asociacion/list.xhtml";
    }

    private void recreateModel() {
        items = null;
    }

    private void recreateModelAsociacion() {
        itemsAsociacion = null;
    }

  
        public void setItemsAsociacion(DataModel items) {
        this.itemsAsociacion = items;
    }



        public String create() {
        try {
            if (controllerAsociacion.findAsociacion(asociacion.getNombre())!=null) {
                Util.addErrorMessage("Asociacion ya existente, coloque otra");
                return null;
            } else {
                
                controllerAsociacion.create(asociacion);
                Util.addSuccessMessage("Asociacion creada con éxito");
               return prepareCreate();
            }
        } catch (Exception e) {
            Util.addErrorMessage(e, "Error al crear la asociacion");
            return null;
        }
    }
        public Asociacion getSelectedAsociacion() {
        if (asociacion == null) {
            asociacion = new Asociacion();
        }
        return asociacion;
    }

        public String prepareList() {
        recreateModelAsociacion();
        return "/admin/asociacion/asociacion/list.xhtml";
    }    
    public String returnAdminPage() {
        return "/admin/adminPage";
    }

    public String prepareCreate() {
        asociacion = new Asociacion();
  
        return "/admin/asicuacion/asociacion/create.xhtml";
    }

    public String prepareEdit() {
        asociacion = (Asociacion) getItems().getRowData();
        return "/admin/asociacion/asociacion/edit.xhtml";
    }

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
