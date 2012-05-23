package org.landal.token;

/**
 * Questa classe rappresenta la categoria lessicale 'do'.
 * @author  Landini Alex
 * @version 1.0
 */
public class DoToken extends KeywordToken implements IDoToken{
    
    /**
     * Costruttore di DoToken
     */
    public DoToken(){}
    
     /**
     * Questo metodo mi restituisce una stringa che identifica il tipo di token.
     * @return  String   identificatore del tipo di token.
     */
    public String getID(){ return "do";}
    
     /**
     * Fornisce una rappresentazione esterna del token.
     */
    public String toString (){
        return "do";
    }
    
}
