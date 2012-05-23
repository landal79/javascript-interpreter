package org.landal.token;

/**
 * Questa classe rappresenta la categoria lessicale 'switch'.
 * @author  Landini Alex
 * @version 1.0
 */

public class SwitchToken extends KeywordToken implements ISwitchToken{
    
    /**
     * Costruttore di SwitchToken.
     */
    public SwitchToken(){}
    
     /**
     * Questo metodo mi restituisce una stringa che identifica il tipo di token.
     * @return  String   identificatore del tipo di token.
     */
    public String getID(){ return "switch";}
    
    /**
     * Fornisce una rappresentazione esterna del token.
     */
    public String toString (){
        return "switch";
    }
    
}
