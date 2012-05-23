package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categoria sintattica lista di istruzioni, essa �
 * realizzata come una lista che ha un elemento e un riferimento al successivo
 * @author  Landini Alex
 * @version 1.0
 */
 public class ListaIstr extends Istr{

 	/**
 	*Questo campo contiene l'istruzione.
 	*/
 	private Istr i;

 	/**
 	* Questo campo contiene un riferimento all'istruzione successiva.
 	*/
 	private ListaIstr succ;

 	/**
 	* Costruttore serve nel caso sia l'unica o l'ultima istruzione.
        * @param    i   istruzione corrente.
 	*/
 	public ListaIstr(Istr i){
			this.i=i;
			this.succ=null;
	}

 	/**
         * Costruttore nel caso ci siano pi� istruzioni.
         * @param   i   istruzione corrente.
         * @param   l   riferimento all'istruzione successiva.
         */
        public ListaIstr(Istr i, ListaIstr l){
		this.i=i;
		this.succ=l;
	}

	/**
         * Ritorna l'istruzione corrente.
         * @return  Istr    istruzione corrente.
         */
        public Istr getIstr(){return i;}

	/**
         * Ritorna l'istruzione successiva.
         * @return ListaIstr    riferimento all'istruzione successiva.
         */
        public ListaIstr getSucc(){return succ;}

	/**
         * Permette di impostare l'istruzione successiva.
         * @param   l   riferimento all'istruzione successiva.
         */
        public void setSucc(ListaIstr l){ succ=l;}
        
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
		if(succ != null) return i.toString()+succ.toString();
			else return i.toString();
	}

 }
