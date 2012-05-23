package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categoria sintattica corpo del costruttore.
 * Rappresenta in pratica una lista che fornisce l'istruzione corrente e il riferimento
 * alla successiva, come appunto da definizione del corpo del costruttore lista di istruzioni.
 * @author  Landini Alex
 * @version 1.0
 */
 public class CostrIstr extends Istr{

	 /**
	 * Contiene la definizione di un campo di un oggetto.
	 */
	 private ThisIstr i;

	 /**
	 * contiene un riferimento alla definizione del successivo campo.
	 */
	 private CostrIstr succ;

	 /**
	 * Costruttore nel caso non ci siano pi� istruzioni nel costruttore o ce ne sia una sola.
         * @param   i   istruzione che definisce un campo dell'oggetto costruito dal costruttore.
	 */
	 public CostrIstr (ThisIstr i){
	 		 this.i=i;
	 		 this.succ=null;
	 }

         /**
          * Costruttore a cui viene passata un'istruzione e il riferimento alla successiva.
          * @param  i   istruzione.
          * @param  c   riferimento all'istruzione successiva.
          */
	 public CostrIstr (ThisIstr i, CostrIstr c){
		 this.i=i;
		 this.succ=c;
	 }

	 /**
          * Fornisce l'istruzione.
          * @return ThisIstr    istruzione.
          */
         public ThisIstr getThisIstr(){ return i;}

	 /**
          * Fornisce un riferimento all'istruzione successica.
          * @return     CostrIstr   riferimento all'istruzione successiva.        
          */
         public CostrIstr getSucc(){ return succ;}

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
		 if (succ != null) return i.toString()+"\n"+succ.toString();
		 	else return i.toString();
	}


 }
