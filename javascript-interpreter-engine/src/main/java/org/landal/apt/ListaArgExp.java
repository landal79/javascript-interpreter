package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categoria sintattica lista degli argomenti reali quando
 * si chiama una funzione o un costruttore.
 * Viene modellata come una lista che contiene un argomento e il riferimento al successivo.
 * @author  Landini Alex
 * @version 1.0
 */
 public class ListaArgExp extends Exp{

 	/**
 	* Questo campo contiene un'espressione che restituir� il valore che verr� 
        * passato come paramtro.
 	*/
 	private Exp argVal;

 	/**
 	* Questo campo contiene un riferimento al parametro successivo.
 	*/
 	private ListaArgExp succ;

 	/**
 	* Costruttore nel caso ci sia un solo argomento o sia l'ultimo argomento.
        * @param    e   espressione da assegnare al parametro.
 	*/
 	public ListaArgExp(Exp e){
			argVal=e;
			succ=null;
	}

 	/**
         * Costruttore con pi� argomenti.
         * @param   e   espressione da assegnare all'argomento.
         * @param   l   riferimento al parametro successivo.
         */
        public ListaArgExp(Exp e, ListaArgExp l){
		argVal=e;
		succ=l;
	}

	/**
         * ritorna l'espressione da assegnare al parametro.
         * @return  Exp espressione da assegnare al parametro.
         */
        public Exp getArgVal(){return argVal;}

	/**
         * Ritorna un riferimento al parametro successivo.
         * @return ListaArgExp  riferimento al parametro successivo.
         */
        public ListaArgExp getSucc(){return succ;}

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
		if (succ != null) return argVal.toString()+" , "+succ.toString();
			else return argVal.toString();
	}

 }
