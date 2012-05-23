package org.landal.token;

/**
 * Questa interfaccia definisce il tipo token, fornendo una serie di
 * funzionalit� necessarie per il parser.
 * @author  Alex Landini
 */
public interface IToken {
    
    /**
     * Questo metodo mi restituisce una stringa che identifica il tipo di token.
     * @return  String   identificatore del tipo di token.
     */
    public String getID();
    
    /**
     * 
     * Questo metodo fornisce una rappresentazione esterna del Token; i token
     * sono memorizzati in forma di stringa, in modo che la loro rappresentazione
     * sia uniforme fra vari componenti che ne possono fare diverso uso, 
     * essi verranno convertiti nel valore che semanticamente rappresentano solo quando
     * se ne avr� la necessita dal modulo che ne avr� necessit�.
     */
    public String toString();
    
    
}
