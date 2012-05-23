package org.landal.lexer;

/**
 *  Questo tipo di eccezioni indica che si � verificato un errore durante l'analisi lessicale.
 * @author  Alex Landini
 * @version 1.0
 */
public class LexerException extends java.lang.Exception {
	
	private static final long serialVersionUID = 1L;
    
    /**
     * Crea una nuova istanza di <i>LexerExceptio</i> senza specificare il messaggio di dettaglio.
     */
    public LexerException() {
    }
    
    
    /**
     * Crea una nuova istanza di <i>LexerException</i> con un messaggio di errore.
     * @param msg   il messagio che specifica il particolare errore.
     */
    public LexerException(String msg) {
        super(msg);
    }
}
