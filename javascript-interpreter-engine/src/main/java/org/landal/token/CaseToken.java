package org.landal.token;


/**
 * Questa classe rappresenta la categoria lessicale 'case', che una parola chiave.
 * @author  Landini Alex
 * @version 1.0
 */
public class CaseToken extends KeywordToken implements ICaseToken {
    
    /**
     * Costruttore di CaseToken.
     */
    public CaseToken() { }
    
     /**
     * Questo metodo mi restituisce una stringa che identifica il tipo di token.
     * @return  String   identificatore del tipo di token.
     */
    public String getID(){ return "case";}
    
    /**
     * Fornisce una rappresentazione esterna del token.
     */
    public String toString ( ){ return "case"; }
    
}
