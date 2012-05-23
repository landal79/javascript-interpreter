package org.landal.token;

/**
 * Questa classe rappresenta la categoria sintattica '<'.
 * @author  Landini Alex
 * @version 1.0
 */
public class LowerToken extends SimboliToken implements ILowerToken{
    
    /**
     * Costruttore di LowerToken.
     */
    public LowerToken() {};
    
     /**
     * Questo metodo mi restituisce una stringa che identifica il tipo di token.
     * @return  String   identificatore del tipo di token.
     */
    public String getID(){ return "<";}
    
     /**
     * Fornisce una rappresentazione esterna del token.
     */
    public String toString (){
       return "<";
    }
    
}


