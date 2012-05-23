package org.landal.token;


/**
 * Questa classe rappresenta la categoria lessicale valore booleano.
 * @author  Alex Landini
 * @version 1.0
 */
public class BooleanToken extends LetteraleToken implements IBooleanToken {

    /**
     * Qusto campo contiene il valore che assume il token
     */
    private String val;

    /**
     * Costruttore di booleanToken a cui viene passato il valore che il token
     * ha nella frase letta.
     * @param   val     valore del token.
     */
    public BooleanToken(String val){

    	this.val = val;
        
    }//BooleanToken
    
     /**
     * Questo metodo mi restituisce una stringa che identifica il tipo di token.
     * @return  String   identificatore del tipo di token.
     */
    public String getID(){ return "boolean";}
    
    /**
     * Questo metodo ritorna il valore Boolean che contiene il valore del token.
     * @return  Boolean rappresenta il valore booleano.
     */
    public Boolean getVal(){
        if (val.compareTo("true")==0) return new Boolean(true);
            else return new Boolean(false);
    }//getVal

    /**
     * Fornisce la rappresentazione del Token.
     */
    public String toString (){ return val; }

}
