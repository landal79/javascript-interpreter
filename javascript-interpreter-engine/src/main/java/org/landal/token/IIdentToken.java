package org.landal.token;

/**
 * Rappresenta la categoria lessicale identificatore.
 * @author  Landini Alex
 * @version 1.0
 */
public interface IIdentToken extends ILetteraleToken{        
    
   /**
     * Questo metodo ritorna il nome dell'identificatore che contiene il token.
     * @return  String rappresenta il nome dell'identificatore.
     */
    public String getVal();
         
}
       
