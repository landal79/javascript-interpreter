package org.landal.visitor;

/**
 * Questa eccezione viene generata nel caso si tenti di leggere da
 * uno stack di attivazione vuoto.
 * @author  Alex Landini
 */
public class EmptyStackException extends java.lang.Exception {
    
    /**
     * Crea una nuova istanza di <i>EmptyStackException</i> senza specificare il messaggio di dettaglio.
     */
    public EmptyStackException() {
    }
    
    
    /**
     * Crea una nuova istanza di <i>EmptyStackException</i> con un messaggio di errore.
     * @param msg   il messagio che specifica il particolare errore.
     */
    public EmptyStackException(String msg) {
        super(msg);
    }
}
