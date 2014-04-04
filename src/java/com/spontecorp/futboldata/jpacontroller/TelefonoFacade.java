/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Telefono;
import java.io.Serializable;

/**
 *
 * @author sponte03
 */
public class TelefonoFacade extends AbstractFacade<Telefono> implements Serializable{

    public TelefonoFacade(Class<Telefono> entityClass) {
        super(entityClass);
    }
    
}
