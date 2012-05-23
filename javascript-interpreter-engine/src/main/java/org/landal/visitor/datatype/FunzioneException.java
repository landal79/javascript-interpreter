package org.landal.visitor.datatype;

import org.landal.visitor.VisitorException;

/**
 * Eccezione della funzione
 * @author  Landini Alex
 * @version 1.0
 */
 public class FunzioneException extends VisitorException{

        /**
         * Crea una nuova istanza di <i>FunzioneException</i> senza specificare il messaggio di dettaglio.
         */
 	public FunzioneException (){}

        /**
         * Crea una nuova istanza di <i>FunzioneException</i> con un messaggio di errore.
         * @param msg   il messagio che specifica il particolare errore.
         */
 	public FunzioneException(String s){super(s);}

 }
