package org.landal.token;

/**
 * Rappresenta la categoria lessicale numero reale.
 * @author  Landini Alex
 * @version 1.0
 */

public interface INumToken extends ILetteraleToken {
    
   /**
     * Questo metodo ritorna il valore Double che contiene il token.
     * @return  Double rappresenta il valore reale.
     */
    public Double getVal();     

}