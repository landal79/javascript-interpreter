package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categoria sintattatica definizione esplicita di 
 * variabile tramite 'var'.
 * @author  Landini Alex
 * @version 1.0
 */
 public class DefVarExplIstr extends Istr{

 	/**
 	* Questo campo contiene un riferimento alla lista di parametri.
 	*/
 	private ListaDefIstr l;

 	/**
         * Costruttore a cui � necessario passare la lista dei parametri.
         * @param   l   rappresenta la lista dei parameri
         */
        public DefVarExplIstr (ListaDefIstr l){
		this.l=l;
	}

	/**
         * Restituisce la lista dei parametri.
         * @return  ListaDefIstr    lista dei parametri.
         */
        public ListaDefIstr getListaDef(){return l;}

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

		return "var "+l.toString()+" ;\n";
	}
        
        /**
         * Questo metodo stampa l'istruzione di dichiarazione senza il ';' finale;
         * @return  String  ritorna la stringa della rappresentazione esterna.
         */
        public String toString2(){

		return "var "+l.toString();
	}

 }