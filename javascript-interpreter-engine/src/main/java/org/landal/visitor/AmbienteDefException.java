package org.landal.visitor;

/**
 * Questa � un'eccezzione generata dall'ambiente di definizione.
 * @author  Landini Alex
 * @version 1.0
 */

 public class AmbienteDefException extends VisitorException{

     /**
     * Crea una nuova istanza di <i>AmbienteDefException</i> senza specificare il messaggio di dettaglio.
     */
     public AmbienteDefException(){}

    /**
     * Crea una nuova istanza di <i>AmbienteDefException</i> con un messaggio di errore.
     * @param msg   il messagio che specifica il particolare errore.
     */
     public AmbienteDefException(String s){super(s);}

 }
