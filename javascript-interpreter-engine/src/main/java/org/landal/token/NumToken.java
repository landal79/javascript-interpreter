package org.landal.token;

/**
 * Questa classe rappresenta la categoria lessicale numero reale.
 * @author  Landini Alex
 * @version 1.0
 */

public class NumToken extends LetteraleToken implements INumToken{

    /**
     * rappresenta il numero in forma di stringa.
     */
    private String val;
    
    /**
     * Costruttore di NumToken che riceve il numero in forma di stringa come argomento.
     * @param val    reppresenta il numero reale-
     */
    public NumToken(String val){    
        this.val = val;      
    }
    
     /**
     * Questo metodo mi restituisce una stringa che identifica il tipo di token.
     * @return  String   identificatore del tipo di token.
     */
    public String getID(){ return "num";}
    
   /**
     * Questo metodo ritorna il valore Double che contiene il token.
     * @return  Double rappresenta il valore reale.
     */
    public Double getVal(){
        
        return new Double(val);
        
    }//getVal
    
     /**
     * Fornisce una rappresentazione esterna del token.
     */
    public String toString (){
        return val;
    }

}