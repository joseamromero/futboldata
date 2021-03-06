/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Categoria;
import com.spontecorp.futboldata.jpacontroller.CategoriaFacade;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import java.util.logging.Level;
import javax.enterprise.context.SessionScoped;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
@Named("categoriaBean")
@SessionScoped
public class CategoriaBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Categoria categoria;
    private DataModel items = null;
    private final CategoriaFacade controllerCategoria;   
    
    private static final Logger logger = LoggerFactory.getLogger(CategoriaBean.class);

    public CategoriaBean() {
        controllerCategoria = new CategoriaFacade();
    }    

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public Categoria getSelected(){
        if(categoria == null){
            categoria = new Categoria();
        }
        return categoria;
    }
    
    public DataModel getItems() {
        if (items == null) {
            items = new ListDataModel(controllerCategoria.findAll());
        }
        return items;
    }
    
    public void recreateModel(){
        items = null;
        categoria = null;
    }
    public String gotoCategoriasPage() {
        return "list";
    }
    
    public String prepareCreate(){
        categoria = new Categoria();
        return "create";
    }
    
    public String prepareEdit(){
        categoria = (Categoria) getItems().getRowData();
        return "edit";
    }
    
    public String prepareList() {
        return "list";
    }
    
    public String returnAdminPage() {
        return "adminPage";
    }
    
    public String create(){
        try {
            if(controllerCategoria.findCategoria(categoria.getNombre()) != null){
                Util.addErrorMessage("Categoria ya existente, coloque otro");
                return null;
            } else {
                controllerCategoria.create(categoria);
                Util.addSuccessMessage("Categoría creada con éxito");
                recreateModel();
                return prepareCreate();
            }
        } catch (Exception e) {
            Util.addErrorMessage(e, "Error al crear la categoría");
            return null;
        }
    }
    
    public String edit(){
        try {
            if (controllerCategoria.findCategoria(categoria.getNombre()) == null) {
                Util.addErrorMessage("Categoria no existente, hay un error");
                return null;
            } else {
                controllerCategoria.edit(categoria);
                Util.addSuccessMessage("Categoría editada con éxito");
                return prepareCreate();
            }
        } catch (Exception e) {
            Util.addErrorMessage(e, "Error al editar la categoría");
            return null;
        }
    }
    
//    public void persist(Object object) {
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.persist(object);
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            java.util.logging.Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
//            em.getTransaction().rollback();
//        } finally {
//            em.close();
//        }
//    }
}
