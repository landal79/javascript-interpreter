package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categoria sintattica chiamata di funzione come l-value.
 * @author  Landini Alex
 * @version 1.0
 */
 public class FunctionCallExp extends Exp{

 	/**
 	* Questo campo contiene il nome o ci� che costituir� il nome della funzione da chiamare.
 	*/
 	protected ExpVar nomeFun;

 	/**
 	* Questo campo contiene un riferimento alla lista degli argomenti
 	*/
 	protected ListaArgExp lArg;

 	/**
 	* Costruttore nel caso non vi siano argomenti per la funzione.
        * @param    name   nome della funzione.
 	*/
 	public FunctionCallExp (ExpVar name){
			nomeFun=name;
			lArg=null;
	}

 	/**
         * Costruttore nel caso la funzione ha dei parametri.
         * @param   name    nome della funzione.
         * @param   l   rifermento alla lista dei parametri.
         */
        public FunctionCallExp (ExpVar name, ListaArgExp l){
		nomeFun=name;
		lArg=l;
	}

	/**
         * Ritorna il nome della funzione.
         * @return  ExpVar  nome della funzione.
         */
        public ExpVar getNomeFun(){return nomeFun;}

	/**
         * Ritorna la lista degli argomenti.
         * @return ListaArgExp  riferimento alla lista degli argomenti
         */
        public ListaArgExp getListaArg(){return lArg;}

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
		if (lArg != null) return nomeFun.toString()+"( "+lArg.toString()+" )";
			else return nomeFun.toString()+"()";
	}


 }
