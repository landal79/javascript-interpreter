package org.landal.token;

/**
 * Questa classe rappresena la categoria lessicale 'break' 
 * che � una parola chiave.
 * @author  Landini Alex
 * @version 1.0
 */

public class BreakToken extends KeywordToken implements IBreakToken {
    
    /**
     *Costruttore di BreakToken.
     */
    public BreakToken(){}
    
     /**
     * Questo metodo mi restituisce una stringa che identifica il tipo di token.
     * @return  String   identificatore del tipo di token.
     */
    public String getID(){ return "break";}
    
    /**
     * Fornisce la rappresentazione del Token.
     */
    public String toString (){ return "break"; }
    
}