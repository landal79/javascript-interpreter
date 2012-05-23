package org.landal.visitor.datatype;

import org.landal.visitor.VisitorException;

/**
 * Eccezione generata dal Costruttore.
 * @author  Landini Alex
 * @version 1.0
 */
 public class CostruttoreException extends VisitorException{

       /**
         * Crea una nuova istanza di <i>CostruttoreException</i> senza specificare il messaggio di dettaglio.
         */   
 	public CostruttoreException (){}

         /**
         * Crea una nuova istanza di <i>CostruttoreException</i> con un messaggio di errore.
         * @param msg   il messagio che specifica il particolare errore.
         */
 	public CostruttoreException(String s){super(s);}

 }
