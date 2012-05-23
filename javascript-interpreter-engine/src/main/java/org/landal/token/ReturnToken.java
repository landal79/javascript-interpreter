package org.landal.token;

/**
 * Questa classe rappresenta la categoria lessicale 'return'.
 * @author  Landini Alex
 * @version 1.0
 */
public class ReturnToken extends KeywordToken implements IReturnToken{
    
    /**
     * Costruttore di ReturnToken.
     */
    public ReturnToken(){}
    
     /**
     * Questo metodo mi restituisce una stringa che identifica il tipo di token.
     * @return  String   identificatore del tipo di token.
     */
    public String getID(){ return "return";}
    
     /**
     * Fornisce una rappresentazione esterna del token.
     */
    public String toString (){
        return "return";
    }
    
}
