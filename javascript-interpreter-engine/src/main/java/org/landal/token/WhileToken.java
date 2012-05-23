package org.landal.token;

/**
 * Questa classe rappresenta la categoria lessicale 'while'.
 * @author  Landini Alex
 * @version 1.0
 */
public class WhileToken extends KeywordToken implements IWhileToken{
    
    /**
     * Costruttore di WhileToken.
     */
    public WhileToken(){}
    
     /**
     * Questo metodo mi restituisce una stringa che identifica il tipo di token.
     * @return  String   identificatore del tipo di token.
     */
    public String getID(){ return "while";}
    
    /**
     * Fornisce una rappresentazione esterna del token.
     */
    public String toString (){
        return "while";
    }
    
}
