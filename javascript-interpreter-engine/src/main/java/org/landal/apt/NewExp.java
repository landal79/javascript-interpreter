package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta l'espressione di costruzione di oggetti denotata
 * dalla keyword 'new', esempio: a = new fun(5,6,7);
 * @author  Landini Alex
 * @version 1.0
 */
 public class NewExp extends Exp{

 	/**
 	* Questo campo contiene l'identificatore del nome del costruttore dell'oggetto.
 	*/
 	private IdentExp nomeCostr;

 	/**
 	* Questo campo contiene un riferimento alla lista dei parametri del costruttore.
 	*/
 	private ListaArgExp lArg;

 	/**
 	* Costruttore nel caso non ci siano paramatri.
        * @param    id  identificatore della funzione.
        * 
 	*/
 	public NewExp (IdentExp id){
			nomeCostr=id;
			lArg=null;
	}

 	/**
         * Costruttore nel caso ci siano i parametri.
         * @param   id  identificatore della funzione.
         * @param   l   riferimento alla lista degli argomenti.
         */
        public NewExp (IdentExp id, ListaArgExp l){
		nomeCostr=id;
		lArg=l;
	}

	/**
         * Restituisce il nome del costruttore.
         * @return IdentExp nome del costruttore.
         */
        public IdentExp getNomeCostr(){return nomeCostr;}

	/**
         * restituisce un riferimento alla lista degli argomenti.
         * @return  ListaArgExp riferimento alla lista degli argomenti.
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
		if (lArg != null) return "new "+nomeCostr.toString()+"( "+lArg.toString()+" )";
			else return "new "+nomeCostr.toString()+"()";
	}

	
 }

