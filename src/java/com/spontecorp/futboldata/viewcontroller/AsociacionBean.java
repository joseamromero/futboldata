/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Asociacion;
import com.spontecorp.futboldata.entity.Direccion;
import com.spontecorp.futboldata.entity.Email;
import com.spontecorp.futboldata.entity.Pais;
import com.spontecorp.futboldata.entity.Telefono;
import com.spontecorp.futboldata.jpacontroller.AsociacionFacade;
import com.spontecorp.futboldata.jpacontroller.CiudadFacade;
import com.spontecorp.futboldata.jpacontroller.DireccionFacade;
import com.spontecorp.futboldata.jpacontroller.EmailFacade;
import com.spontecorp.futboldata.jpacontroller.PaisFacade;
import com.spontecorp.futboldata.jpacontroller.TelefonoFacade;
import com.spontecorp.futboldata.utilities.Util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sponte03
 */
@Named("asociacionBean")
@SessionScoped
public class AsociacionBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Asociacion asociacion = null;
    private Pais pais;
    private Direccion direccion;
    private Telefono telefono;
    private Email email;

    private List<Email> emails = null;
    private List<Telefono> telefonos = null;
    private List<Telefono> telefonoEliminar = null;
    private List<Email> emailEliminar = null;
    private transient DataModel itemsTelefono = null;
    private transient DataModel itemsAsociacion = null;
    private SelectItem[] ciudades;

    private final AsociacionFacade controllerAsociacion;
    private final transient EntityManagerFactory emf = Util.getEmf();
    private final DireccionFacade controllerDireccion;
    private final TelefonoFacade controllerTelefono;
    private final EmailFacade controllerEmail;

    private final CiudadFacade controllerCiudad;
    private final PaisFacade controllerPais;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AsociacionBean.class);

    /**
     * Creates a new instance of LocalidadBean
     */
    public AsociacionBean() {
        controllerPais = new PaisFacade();
        controllerCiudad = new CiudadFacade();
        controllerEmail = new EmailFacade();
        controllerTelefono = new TelefonoFacade();
        controllerAsociacion = new AsociacionFacade();
        controllerDireccion = new DireccionFacade();

    }

    /**
     * permite la edición de los campos en la vista Asociación, devuelve un
     * String con la facelet de respuesta
     *
     * @return un String que retorna a la lista
     */
    public String edit() {
        try {
            if (controllerAsociacion.find(asociacion.getId()) == null) {
                Util.addErrorMessage("Asociacion no existente");
                return prepareList();
            } else {
                for (Telefono telefonoEditar : telefonos) {
                    if (controllerTelefono.findTelefono(telefonoEditar.getTelefono()) != null) {
                        controllerTelefono.edit(telefonoEditar);
                    } else {
                        telefonoEditar.setDireccionId(asociacion.getDireccionId());
                        controllerTelefono.create(telefonoEditar);
                    }
                }

                for (Email emailEditar : emails) {
                    if (controllerEmail.findEmail(emailEditar.getEmail()) != null) {
                        controllerEmail.edit(emailEditar);
                    } else {
                        emailEditar.setDireccionId(asociacion.getDireccionId());
                        controllerEmail.create(emailEditar);

                    }
                }

                for (Email emailEli : emailEliminar) {
                    controllerEmail.remove(emailEli);
                }
                for (Telefono telefonoEli : telefonoEliminar) {
                    controllerTelefono.remove(telefonoEli);
                }

                controllerDireccion.edit(asociacion.getDireccionId());
                controllerAsociacion.edit(asociacion);
                Util.addSuccessMessage("Asociacion editado con éxito");

                telefonos = null;
                emails = null;
                telefonoEliminar = null;
                emailEliminar = null;
                return prepareList();
            }
        } catch (Exception e) {
            Util.addErrorMessage(e, "Error al editar la asociacion");
            return null;
        }
    }

    public SelectItem[] getPaisAvailable() {
        return Util.getSelectItems(controllerPais.listaPaisxNombre());
    }

    public void ciudadAvailable() {
        ciudades = Util.getSelectItems(controllerCiudad.findCiudadxPais(pais));
    }

    public void ciudadAvailable(Pais pais) {
        ciudades = Util.getSelectItems(controllerCiudad.findCiudadxPais(pais));
    }

    public SelectItem[] getCiudades() {
        return ciudades;
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
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

    public List<Telefono> getTelefonos(Direccion direccion) {
        telefonos = controllerDireccion.findListTelefonoxDireaccion(direccion);
        return telefonos;

    }

    public List<Email> getEmails(Direccion direccion) {
        emails = controllerDireccion.findListEmailxDireaccion(direccion);
        return emails;

    }

    public String gotoAsociacionPage() {
        recreateModel();
        return "/admin/asociacion/asociacion/list.xhtml";
    }

    private void recreateModel() {
        itemsTelefono = null;
        asociacion = null;
        ciudades = null;
        telefonos = null;
        email = null;
    }

    private void recreateModelAsociacion() {
        itemsAsociacion = null;
    }

    public void setItemsAsociacion(DataModel items) {
        this.itemsAsociacion = items;
    }

    public String create() {
        try {
            if (controllerAsociacion.findAsociacion(asociacion.getNombre()) != null) {
                Util.addErrorMessage("Asociacion ya existente, coloque otra");
                return null;
            } else {

                controllerDireccion.create(direccion);
                for (Email item : emails) {
                    item.setDireccionId(direccion);
                    controllerEmail.create(item);
                }
                for (Telefono item2 : telefonos) {
                    item2.setDireccionId(direccion);
                    controllerTelefono.create(item2);
                }
                direccion.setEmailCollection(emails);
                direccion.setTelefonoCollection(telefonos);

                asociacion.setDireccionId(direccion);

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

    public void setSelectedAsociacion(Asociacion asociacion) {
        this.asociacion = asociacion;

    }

    public String prepareList() {
        asociacion = null;
        recreateModelAsociacion();
        return "/admin/asociacion/asociacion/list.xhtml";
    }

    public String returnAdminPage() {
        return "/admin/adminPage";
    }

    public String cancelOption() {
        asociacion = null;
        pais = null;
        direccion = null;
        telefono = null;
        email = null;
        emails = null;
        telefonos = null;
        return prepareList();
    }

    public String prepareCreate() {
        asociacion = new Asociacion();
        direccion = new Direccion();
        telefono = new Telefono();
        telefonos = new ArrayList<>();
        emails = new ArrayList<>();
        email = new Email();
        pais = new Pais();
        ciudades = null;
        return "/admin/asociacion/asociacion/create.xhtml";
    }

    public void cargarTelefono() {
        telefonos.add(telefono);
        telefono = new Telefono();

    }

    public void cargarEmail() {
        emails.add(email);
        email = new Email();
    }

    public void cargarTelefonoEdit() {
        telefonos.add(telefono);
       telefono = new Telefono();
    }

    public void cargarEmailEdit() {
        emails.add(email);
        email = new Email();
    }

    public void eliminarTelefono(Telefono telefono) {

        logger.debug("El numero telfono: " + telefono.getTelefono(), AsociacionBean.class);
        if (telefonos.remove(telefono)) {
            telefonoEliminar.add(telefono);
            for (Telefono tlf : telefonoEliminar) {
                logger.debug("Va a eliminar a: " + tlf.toString());
            }
        } else {
            logger.debug("No lo agrego a la lista de eliminar Telefono");
        }
    }

    public void eliminarEmail(Email email) {

        if (emails.remove(email)) {
            emailEliminar.add(email);
            for (Email eml : emailEliminar) {
                logger.debug("Va a eliminar a: " + eml.toString());
            }
        } else {
            logger.debug("No lo agrego a la lista de eliminar Telefono");
        }
    }

    public String prepareEdit() {
        email = new Email();
        telefono = new Telefono();
        telefonoEliminar = new ArrayList<>();
        emailEliminar = new ArrayList<>();
        asociacion = (Asociacion) getItemsAsociacion().getRowData();
        ciudadAvailable(asociacion.getDireccionId().getCiudadId().getPaisId());
        telefonos = getTelefonos(asociacion.getDireccionId());
        emails = getEmails(asociacion.getDireccionId());
        return "/admin/asociacion/asociacion/edit.xhtml";
    }

//    public void persist(Object object) {
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.persist(object);
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
//            em.getTransaction().rollback();
//        } finally {
//            em.close();
//        }
//    }

    public Telefono getTelefono() {
        return telefono;
    }

    public void setTelefono(Telefono telefono) {
        if(this.telefono == null){
            this.telefono = new Telefono();
        }
        this.telefono = telefono;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public DataModel getItemsTelefono() {
        return itemsTelefono;
    }

    public void setItemsTelefono(DataModel itemsTelefono) {
        this.itemsTelefono = itemsTelefono;
    }

    public void cargarItemTelefono() {
        telefono = (Telefono) getItemsTelefono().getRowData();
    }

}
