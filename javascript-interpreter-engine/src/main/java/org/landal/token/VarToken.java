package org.landal.token;

/**
 * Questa classe rappresenta la categoria lessicale 'var.
 * @author  Landini Alex
 * @version 1.0
 */
public class VarToken extends KeywordToken implements IVarToken{
    
    /**
     * Costruttore di VarToken.
     */
    public VarToken(){}
    
     /**
     * Questo metodo mi restituisce una stringa che identifica il tipo di token.
     * @return  String   identificatore del tipo di token.
     */
    public String getID(){ return "var";}
    
     /**
     * Fornisce una rappresentazione esterna del token.
     */
    public String toString (){
        return "var";
    }
    
}
