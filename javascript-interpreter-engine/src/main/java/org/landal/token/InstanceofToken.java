package org.landal.token;

/**
 * Questa classe rappresenta la categoria lessicale 'instanceof'.
 * @author  Landini Alex
 * @version 1.0
 */
public class InstanceofToken extends KeywordToken implements IInstanceofToken{
    
    /**
     * Costruttore di Instanceof.
     */
    public InstanceofToken(){}
    
     /**
     * Questo metodo mi restituisce una stringa che identifica il tipo di token.
     * @return  String   identificatore del tipo di token.
     */
    public String getID(){ return "instanceof";}
    
    /**
     * Fornisce una rappresentazione esterna del token.
     */
    public String toString (){
        return "instanceof";
    }
    
}
