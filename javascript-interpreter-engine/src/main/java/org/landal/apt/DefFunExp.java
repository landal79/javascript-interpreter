package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categoria sintattica definizione di funzione come 
 * espressione esempio: a = fun (a,g){ return a*g;};
 * @author  Landini Alex
 * @version 1.0
 */
 public class DefFunExp extends Exp{

 	/**
 	* Questo campo contiene un riferimento alla lista dei parametri.
 	*/
 	private ListaParamIstr lParam;

 	/**
 	* Questo campo contiene un riferimento alla lista delle istruzioni.
 	*/
 	private ListaIstr lIstr;

 	/**
 	* Costruttore nel caso la funzione non abbia parametri.
        * @param    li  riferimento alla lista delle istruzioni.
 	*/
 	public DefFunExp(ListaIstr li){
			lParam=null;
			lIstr=li;
	}

 	/**
         * Costruttore nel caso ci siano i parametri.
         * @param   lp  lista dei parametri
         * @param   li  lista delle istruzioni
         */
        public DefFunExp(ListaParamIstr lp, ListaIstr li){
		lParam=lp;
		lIstr=li;
	}

	/**
         * Ritorna un riferimento alla lista dei parametri.
         * @return  ListaParamIstr  riferimento alla lista dei parametri.
         */
        public ListaParamIstr getListaParam(){return lParam;}

	/**
         * Ritorna un riferimento alla lista delle istruzioni.
         * @return  ListaIstr   riferimento lista delle istruzioni.
         */
        public ListaIstr getCorpoFun() {return lIstr;}

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
		return "funcion ( "+lParam.toString()+" )\n{\n"+lIstr.toString()+"} \n";
	}


 }
