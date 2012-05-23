package org.landal.token;

/**
 * Questa classe rappresenta la categoria lessicale 'continue'.
 * @author  Landini Alex
 * @version 1.0
 */
public class ContinueToken extends KeywordToken implements IContinueToken{
    
    /**
     * Costruttore di ContinueToken.
     */
    public ContinueToken(){}
    
     /**
     * Questo metodo mi restituisce una stringa che identifica il tipo di token.
     * @return  String   identificatore del tipo di token.
     */
    public String getID(){ return "continue";}
    
     /**
     * Fornisce una rappresentazione esterna del token.
     */
    public String toString (){
        return "continue";
    }
    
}
