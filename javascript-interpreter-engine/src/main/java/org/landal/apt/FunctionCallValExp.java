package org.landal.apt;

import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categoria sintattica chiamata di funzione come
 * r-value.
 * @author  Landini Alex
 * @version 1.0
 */
public class FunctionCallValExp extends FunctionCallExp{

 	/**
 	* Costruttore nel caso non vi siano argomenti per la funzione.
        * @param    name   nome della funzione.
 	*/
 	public FunctionCallValExp (ExpVar name){
			super (name);
	}

 	/**
         * Costruttore nel caso la funzione ha dei parametri.
         * @param   name    nome della funzione.
         * @param   l   rifermento alla lista dei parametri.
         */
        public FunctionCallValExp (ExpVar name, ListaArgExp l){
		super (name,l);
	}

         /**
         * Questo metodo permette a un visitor di valutare la categoria sintattica
         * attraverso la tecnica del double-dispatch.
         * @param   v   rappresenta il visitor che eseguir� la valutazione della classe
         * @throws  VisitorException    nel caso durante la valutazione dell'albero si generi qualche errore.
         */
         public void accept (org.landal.visitor.IVisitor v) throws org.landal.visitor.VisitorException{ v.visit(this); } 

}
