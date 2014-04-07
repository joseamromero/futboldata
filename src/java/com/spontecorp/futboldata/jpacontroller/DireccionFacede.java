/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.jpacontroller;


import com.spontecorp.futboldata.entity.Direccion;
import com.spontecorp.futboldata.entity.Email;
import com.spontecorp.futboldata.entity.Telefono;
import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sponte03
 */
public class DireccionFacede extends AbstractFacade<Direccion> implements  Serializable{
        
        private TelefonoFacade controllerTelefono =new TelefonoFacade (Telefono.class);
       
         private TelefonoFacade controllerEmail;
        private static final Logger logger = LoggerFactory.getLogger(Direccion.class);
        
        public List<Telefono> findListTelefonoxDireaccion(Direccion direccion) {

        return controllerTelefono.findListTelefonoxDireaccion(direccion);
    }
        
        
    public List<Email> findListEmailxDireaccion(Direccion direccion) {
        List<Email> email = null;
        try {
            EntityManager emTelefono = controllerEmail.getEntityManager();
            String query = "SELECT e FROM Email e WHERE  e.emailId = :direccion";
            Query q = emTelefono.createQuery(query, Email.class);
            q.setParameter("direccion", direccion);
            email = (List<Email>) q.getResultList();
        } catch (Exception e) {
            logger.debug("Error encontrando los Correos: " + e.getLocalizedMessage(), e);
        }
        return email;
    }
        
    public DireccionFacede(Class<Direccion> entityClass) {
        super(entityClass);
    }
    
}
