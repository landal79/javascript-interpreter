package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;


/**
 * Questa classe rappresenta la categoria sintattica istruzione 'continue'.
 * @author  Landini Alex
 * @version 1.0
 */
 public class ContinueIstr extends SaltoIstr{

 	/**
         * Costruttore di ContinueIstr.
         */
        public ContinueIstr(){ }

 	
         /**
          * Questo metodo consente di ottenere una rappresentazione esterna della categoria sintattica.
          * @return String  viene restituita la rappresetazione esterna sotto forma di stringa.
          */
        public String toString(){ return "continue;\n"; }

     /**
     * Questo metodo permette a un visitor di valutare la categoria sintattica
     * attraverso la tecnica del double-dispatch.
     * @param   v   rappresenta il visitor che eseguir� la valutazione della classe
     * @throws  VisitorException    nel caso durante la valutazione dell'albero si generi qualche errore.
     */
        public void accept ( IVisitor v ) throws VisitorException{ v.visit(this); }
 }