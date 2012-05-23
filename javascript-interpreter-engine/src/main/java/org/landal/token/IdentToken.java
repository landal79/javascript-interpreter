package org.landal.token;

/**
 * Questa classe rappresenta la categoria lessicale identificatore.
 * @author  Landini Alex
 * @version 1.0
 */
public class IdentToken extends LetteraleToken implements IIdentToken{
    
    /**
     * Rappresenta il nome dell'identificatore.
     */
    private String val;
    
    /**
     * Costruttore di IdentToken che riceve il nome dell'identificatore.
     * @param val   nome dell'identificatore
     */
    public IdentToken(String val){
        this.val = val;
    }
    
     /**
     * Questo metodo mi restituisce una stringa che identifica il tipo di token.
     * @return  String   identificatore del tipo di token.
     */
    public String getID(){ return "ident";}
    
   /**
     * Questo metodo ritorna il nome dell'identificatore che contiene il token.
     * @return  String rappresenta il nome dell'identificatore.
     */
    public String getVal(){
        return val;
    }
    
     /**
     * Fornisce una rappresentazione esterna del token.
     */
    public String toString (){
        return val;
    }
    
}
       
