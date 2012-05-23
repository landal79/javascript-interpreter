package org.landal.token;

/**
 * Questa classe rappresenta la categoria lessicale stringa.
 * @author  Landini Alex
 * @version 1.0
 */
public class StringaToken extends LetteraleToken implements IStringaToken{
    
    private String val;
    
    /**
     * Costruttore di StringToken che riceve il valore della stringa come parametro.
     * @param   val valore della stringa.
     */
    public StringaToken(String val){
        this.val = val;
    }
    
     /**
     * Questo metodo mi restituisce una stringa che identifica il tipo di token.
     * @return  String   identificatore del tipo di token.
     */
    public String getID(){ return "stringa";}
    
    /**
     * Questo metodo ritorna il valore String che contiene il valore del token.
     * @return  String rappresenta la stringa.
     */
    public String getVal(){
        
        return val;
        
    }//getVal
    
     /**
     * Fornisce una rappresentazione esterna del token.
     */
    public String toString (){
        return val;
    }
}
   
      
