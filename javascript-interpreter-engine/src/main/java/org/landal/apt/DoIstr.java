package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categoria sintattica ciclo 'do'.
 * @author  Landini Alex
 * @version 1.0
 */
 public class DoIstr extends ControlIstr{
     
        /**
         * Costruttore di DoIstr. ci devono essere sia la condizione sia il corpo.
         * @param   e   espressione del ciclo.
         * @param   i   corpo del ciclo.
         */
	 public DoIstr (Exp e, Istr i){
	 	super(e,i);
	 }

	 /**
         * Questo metodo permette a un visitor di valutare la categoria sintattica
         * attraverso la tecnica del double-dispatch.
         * @param   v   rappresenta il visitor che eseguir� la valutazione della classe
         * @throws  VisitorException    nel caso durante la valutazione dell'albero si generi qualche errore.
         */
        public void accept (IVisitor v) throws org.landal.visitor.VisitorException{ v.visit(this); } 
     
        /**
         * Questo metodo consente di ottenere una rappresentazione esterna della categoria sintattica.
         * @return String  viene restituita la rappresetazione esterna sotto forma di stringa.
         */
         public String toString(){
            return "do \n"+corpo.toString()+"\n while ( "+condizione.toString()+" )";
	 }


 }
