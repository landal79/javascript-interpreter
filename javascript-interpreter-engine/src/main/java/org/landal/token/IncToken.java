package org.landal.token;

/**
 * Questa classe rappresenta la categoria lessicale '++' incremento.
 * @author  Landini Alex
 * @version 1.0
 */
public class IncToken extends SimboliToken implements IIncToken{
    
    /**
     * Costruttore di IncToken.
     */
    public IncToken() {};
    
     /**
     * Questo metodo mi restituisce una stringa che identifica il tipo di token.
     * @return  String   identificatore del tipo di token.
     */
    public String getID(){ return "++";}
    
     /**
     * Fornisce una rappresentazione esterna del token.
     */
    public String toString (){
       return "++";
    }
    
}

