/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Asociacion;
import java.io.Serializable;

/**
 *
 * @author sponte03
 */
public class AsociacionFacede extends AbstractFacade<Asociacion> implements Serializable{

    public AsociacionFacede(Class<Asociacion> entityClass) {
        super(entityClass);
    }

}
