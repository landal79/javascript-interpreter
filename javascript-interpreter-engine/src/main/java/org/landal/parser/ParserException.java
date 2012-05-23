package org.landal.parser;

/**
 * Questo tipo di ecczione indica che si � verificato un errore durante l'analisi sintattica
 * @author  Alex Landini 
 * @version 1.0
 */
public class ParserException extends java.lang.Exception {
	
	private static final long serialVersionUID = 1L;
    
    /**
     * Crea una nuova istanza di ParserException senza dettagli.
     */
    public ParserException() {
    }
    
    
    /**
     * Crea una nuova istanza di ParserException con un messaggio di errore.
     * @param msg rappresenta il messaggio di dettaglio.
     */
    public ParserException(String msg) {
        super(msg);
    }
}
