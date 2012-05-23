package org.landal.token;

/**
 * Rappresenta la categoria lessicale stringa.
 * @author  Landini Alex
 * @version 1.0
 */
public interface IStringaToken extends ILetteraleToken {
        
    /**
     * Questo metodo ritorna il valore String che contiene il valore del token.
     * @return  String rappresenta la stringa.
     */
    public String getVal();     
    
}
   
      
