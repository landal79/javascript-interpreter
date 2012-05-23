package org.landal.token;

/**
 * Questa classe rappresenta la categoria lessicale 'function'.
 * @author  Landini Alex
 * @version 1.0
 */
public class FunctionToken extends KeywordToken implements IFunctionToken{
    
    /**
     * Costruttore di FunctinToken.
     */
    public FunctionToken(){}
    
     /**
     * Questo metodo mi restituisce una stringa che identifica il tipo di token.
     * @return  String   identificatore del tipo di token.
     */
    public String getID(){ return "function";}
    
     /**
     * Fornisce una rappresentazione esterna del token.
     */
    public String toString (){
        return "function";
    }
    
}
