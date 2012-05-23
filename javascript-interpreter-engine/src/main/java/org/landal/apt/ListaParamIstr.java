package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categoria sintattica lista dei parametri di una 
 * funzione o di un costruttore.
 * @author  Landini Alex
 * @version 1.0
 */
 public class ListaParamIstr extends Istr{

 	/**
 	* Questo campo contiene il parametro passato.
 	*/
 	private IdentExp i;

 	/**
 	*questo campo contiene un riferimento al paramentro successivo.
 	*/
 	private ListaParamIstr l;

	/**
	* Costruttore nel caso ci sia un solo parametro o sia l'ultimo parametro della lista
        * di parametri.
        * @param    i   nome del parametro
	*/
	public ListaParamIstr (IdentExp i){
			this.i=i;
			this.l=null;
	}

 	/** 
         * Costruttore che riceve un parametro e un riferimento al successivo.
         * @param i nome del parametro.
         * @param   l   riferimento al parametro successivo.
         */
        public ListaParamIstr (IdentExp i, ListaParamIstr l){
		this.i=i;
		this.l=l;
	}

	/**
         * Fornisce il nome del parametro.
         * @return  IdentExp    identificatore del parametro.
         */
        public IdentExp getIdent(){return i;}

	/**
         * Fornisce un riferimento al parametro successivo.
         * @return  ListaParamIstr  lista dei parametri.
         */
        public ListaParamIstr getSuccParam(){return l;}

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
		if (l != null) return i.toString()+" , "+l.toString();
			else return i.toString();
		}

 }
