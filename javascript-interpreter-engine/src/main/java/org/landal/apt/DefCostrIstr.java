package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categoria sintattica definizione di costruttore, attraverso
 * la parole chiave 'function', con la differenza che quest'ultimo pu� essere richiamato
 * solo attraverso l'operatore 'new'.
 * @author  Landini Alex
 * @version 1.0
 */
 public class DefCostrIstr extends Istr{


 	/**
	* Questo campo contiene l'identificatore che rappresenta il nome del Costruttore.
	*/
	private IdentExp nomeCostr;

	/**
	* Questo campo contiene un riferimento alla lista dei parametri.
	*/
	private ListaParamIstr lParam;


	/**
	* Questo campo contiene un riferimento al corpo del costruttore.
	*/
	private CostrIstr corpoCostr;

	/**
	* Costruttore nel caso non si abbiano i parametri parametri.
        * @param    nomeCostr   nome del costruttore.
        * @param    corpo   corpo del costruttore
	*/
	public DefCostrIstr (IdentExp nomeCostr, CostrIstr corpo){
				this.nomeCostr=nomeCostr;
				this.lParam=null;
				this.corpoCostr=corpo;
	}

        /**
	* Costruttore nel caso si abbiano i parametri parametri.
        * @param    nomeCostr   nome del costruttore.
        * @param    param   lista dei parametri.
        * @param    corpo   corpo del costruttore
	*/
	public DefCostrIstr (IdentExp nomeCostr, ListaParamIstr param, CostrIstr corpo){
			this.nomeCostr=nomeCostr;
			this.lParam=param;
			this.corpoCostr=corpo;
	}

	/**
         * Fornisce il nome del costruttore.
         * @return  IdentExp    nome del costruttore.  
         */
        public IdentExp getNomeCostr(){return nomeCostr;}

	/**
         * Fornisce la lista dei parametri.
         * @return ListaParamIstr   lista dei parametri.
         */
        public ListaParamIstr getListaParam(){return lParam;}

	/**
         * Fornisce il corpo del costruttore.
         * @return  CostrIstr   il corpo del costruttore.
         */
        public CostrIstr getCorpoCostr(){return corpoCostr;}

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
		if (lParam != null) return "function "+nomeCostr.toString()+" ( "+lParam.toString()+" ) \n {\n"+corpoCostr.toString()+"}\n";
			else return "function "+nomeCostr.toString()+" (  ) {"+corpoCostr.toString()+"}\n";
	}

	
 }

