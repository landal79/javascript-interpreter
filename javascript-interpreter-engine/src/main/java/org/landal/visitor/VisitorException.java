/*
 * VisitorException.java
 *
 * Created on 7 dicembre 2004, 19.51
 */

package org.landal.visitor;

/**
 * Questo tipo di eccezione indica che si � verificato un errore durante la valutazione della frase.
 * @author  Alex Landini
 * @version 1.0
 */
public class VisitorException extends java.lang.Exception {
    
    /**
     * Crea una nuova istanza di <i>VisitorExceptio</i> senza specificare il messaggio di dettaglio.
     */
    public VisitorException() {
    }
    
    
    /**
     * Crea una nuova istanza di <i>VisitorException</i> con un messaggio di errore.
     * @param msg   il messagio che specifica il particolare errore.
     */
    public VisitorException(String msg) {
        super(msg);
    }
}
