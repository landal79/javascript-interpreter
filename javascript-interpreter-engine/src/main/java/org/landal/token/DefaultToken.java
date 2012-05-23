package org.landal.token;

/**
 * Questa classe rappresenta la categoria lessicale 'default'.
 * @author  Landini Alex
 * @version 1.0
 */
public class DefaultToken extends KeywordToken implements IDefaultToken{
    
    /**
     * Costruttore di DefaultToken.
     */
    public DefaultToken(){}
    
     /**
     * Questo metodo mi restituisce una stringa che identifica il tipo di token.
     * @return  String   identificatore del tipo di token.
     */
    public String getID(){ return "default";}
    
     /**
     * Fornisce una rappresentazione esterna del token.
     */
    public String toString (){
        return "default";
    }
    
}
