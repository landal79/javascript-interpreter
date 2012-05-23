package org.landal.token;

/**
 * Questa classe rappresenta la categora lessicale 'null'.
 * @author  Landini Alex
 * @version 1.0
 */
public class NullToken extends KeywordToken implements INullToken{
    
    /**
     * Costruttore di NullToken.
     */
    public NullToken(){}
    
     /**
     * Questo metodo mi restituisce una stringa che identifica il tipo di token.
     * @return  String   identificatore del tipo di token.
     */
    public String getID(){ return "null";}
    
     /**
     * Fornisce una rappresentazione esterna del token.
     */
    public String toString (){
        return "null";
    }
    
}
