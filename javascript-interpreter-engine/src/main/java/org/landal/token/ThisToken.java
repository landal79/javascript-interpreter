package org.landal.token;

/**
 * Questa classe rappresenta la categoria lessicale 'this'.
 * @author  Landini Alex
 * @version 1.0
 */
public class ThisToken extends KeywordToken implements IThisToken{
    
    /**
     * Costruttore di ThisToken.
     */
    public ThisToken(){
    }
    
     /**
     * Questo metodo mi restituisce una stringa che identifica il tipo di token.
     * @return  String   identificatore del tipo di token.
     */
    public String getID(){ return "this";}
    
     /**
     * Fornisce una rappresentazione esterna del token.
     */
    public String toString (){
        return "this";
    }
    
}
