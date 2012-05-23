package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categoria sintattica definizione di funzione
 * esplicitamente attraverso la parola chiave 'function'.
 * @author  Landini Alex
 * @version 1.0
 */

 public class DefFunIstr extends Istr{

 	/**
 	* Questo campo contiene l'identificatore che rappresenta il nome della funzione.
 	*/
 	private IdentExp nomeFun;

 	/**
 	* Questo campo contiene un riferimento alla lista dei parametri.
 	*/
 	private ListaParamIstr lParam;

 	/**
 	* Questo campo contiene un riferimento al corpo della funzione.
 	*/
 	private ListaIstr corpoFun;

	/**
	* Costruttore nel caso la funzione non abbia parametri. 
        * @param    corpo   nome della funzione
        * @param    listaIstr   corpo della funzione
	*/
	public DefFunIstr (IdentExp corpo, ListaIstr listaIstr){
			this.nomeFun=corpo;
			this.lParam=null;
			this.corpoFun=listaIstr;
	}

 	/**
	* Costruttore di DefFunIstr. 
        * @param    i   nome della funzione
        * @param    listaParam  lista dei paramtri
        * @param    listaIstr   corpo della funzione
	*/
        public DefFunIstr (IdentExp corpo, ListaParamIstr listaParam, ListaIstr listaIstr){
		this.nomeFun=corpo;
		this.lParam=listaParam;
		this.corpoFun=listaIstr;
	}

	/**
         * Restituisce il nome della funzione.
         * @return  IdentExp    nome della funzione.
         */
        public IdentExp getNomeFun() {return nomeFun;}

	/**
         * Restituisce la lista dei parametri.
         * @return   ListaParamIstr  lista dei parametri.
         */
        public ListaParamIstr getListaParam(){return lParam;}

	/**
         * Restituisce il corpo della funzione.
         * @return  ListaIstr   istruzione della funzione.
         */
        public ListaIstr getCorpoFun() {return corpoFun;}

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
		
		if (lParam != null) return "function "+nomeFun.toString()+" ( "+lParam.toString()+" ) \n {\n"+corpoFun.toString()+"}\n";
			else return "function "+nomeFun.toString()+" ( ) {\n"+corpoFun.toString()+"}\n";
	}


 }
