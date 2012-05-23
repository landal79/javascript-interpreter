package org.landal.token;

/**
 * Questa classe rappresenta la categoria lessicale 'for'.
 * @author  Landini Alex
 * @version 1.0
 */
public class ForToken extends KeywordToken implements IForToken{
    
    /**
     * Costruttore di ForToken.
     */
    public ForToken(){}
    
     /**
     * Questo metodo mi restituisce una stringa che identifica il tipo di token.
     * @return  String   identificatore del tipo di token.
     */
    public String getID(){ return "for";}
    
     /**
     * Fornisce una rappresentazione esterna del token.
     */
    public String toString (){
        return "for";
    }
    
}
