/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Email;
import java.io.Serializable;

/**
 *
 * @author sponte03
 */
public class EmailFacade extends AbstractFacade<Email> implements Serializable{

    public EmailFacade(Class<Email> entityClass) {
        super(entityClass);
    }
    
}
