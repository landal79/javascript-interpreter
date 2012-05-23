package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categoria sintattica lista degli elementi di un
 * Array, viene realizzata come una lista che contiene un elemento e un riferimento
 * al successivo.
 * @author  Landini Alex
 * @version 1.0
 */
 public class ListaElExp extends Exp{

	 /**
	 * Valore dell'i-esimo elemento dell'array.
	 */
	 private Exp el;

	 /**
	 * Riferimento all'elemento successivo.
	 */
	 private ListaElExp succ;

	 /**
	 * Costruttore nel caso l'elemento sia l'unico elemento o l'ultimo elemento dell'array.
         * @param   l   elemenento dell'array.
	 */
	 public ListaElExp (Exp l){
	 		 el=l;
	 		 succ=null;
	 }
         
         /**
          * Costruttore nel caso ci siano pi� elementi nell'array.
          * @param  l   elemento dell'array.
          * @param  list    riferimento all'elemento successivo.
          */
	 public ListaElExp (Exp l, ListaElExp list){
		 el=l;
		 succ=list;
	 }

	 /**
          * Restituisce l'elemento dell'array.
          * @return Exp elemento dell'array.
          */
         public Exp getElem(){return el;}

	 /**
          * Ritorna il riferimento all'elemento successivo.
          * @return ListaElExp  riferimento all'elemento successivo.
          */
         public ListaElExp getSucc(){return succ;}

	 /**
          * Imposta il riferimento all'elemento successivo.
          * @param  l   riferimento all'elemento successivo.
          */
         public void setSucc(ListaElExp l){ succ=l;}

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
		 if (succ != null) return el.toString()+" , "+succ.toString();
			else return el.toString();
	 }


 }