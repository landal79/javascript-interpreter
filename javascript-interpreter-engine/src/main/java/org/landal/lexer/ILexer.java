package org.landal.lexer;

import org.landal.token.IToken;

/**
 * fornisce le operazioni fondamentali per eseguire l'analisi lessicale
 * di una frase
 * @author  Alex Landini
 * @ver     1.0
 *
 */
public interface ILexer {
    
    /**
     * Restituisce il token successivo della frase
     * cui un lexer concreto pu� accedere grazie 
     * ad un dispositivo di accesso a lui noto.
     * @return   IToken  ritorna un riferimento al token successivo presente nella frase
     * @throws  LexerException    errori che si sono verificati durante la lettura.
     */
    public IToken nextToken() throws LexerException;
    
    /**
     * Questo metodo serve per reinizializzare il lexer fornendogli in ingresso
     * un nuovo dispositivo di ingresso da cui leggere.
     * @param inp   nuovo dispositivo di ingresso da assegnare al Lexer.
     */
    public void init(java.io.Reader inp);
    
    /**
     * Questo metodo restituisce il numero della righe di programma che si sta leggendo.
     * @return String   numero della stringa.
     */
    public String getLine();
    
}
