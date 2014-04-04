/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Direccion;
import java.io.Serializable;

/**
 *
 * @author sponte03
 */
public class DireccionFacede extends AbstractFacade<Direccion> implements  Serializable{

    public DireccionFacede(Class<Direccion> entityClass) {
        super(entityClass);
    }
    
}
