package org.landal.token;


/**
 * Rappresenta la categoria lessicale valore booleano.
 * @author  Alex Landini
 * @version 1.0
 */
public interface IBooleanToken extends ILetteraleToken {   
   
    /**
     * Questo metodo ritorna il valore Boolean che contiene il valore del token.
     * @return  Boolean rappresenta il valore booleano.
     */
    public Boolean getVal();   

}
