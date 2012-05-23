package org.landal.token;

/**
 * Questa classe rappresenta la categoria lessicale di 'new'.
 * @author  Landini Alex
 * @version 1.0
 */
public class NewToken extends KeywordToken implements INewToken{
    
    /**
     * Costruttore di NewToken.
     */
    public NewToken(){}
    
     /**
     * Questo metodo mi restituisce una stringa che identifica il tipo di token.
     * @return  String   identificatore del tipo di token.
     */
    public String getID(){ return "new";}
    
     /**
     * Fornisce una rappresentazione esterna del token.
     */
    public String toString (){
        return "new";
    }
    
}
