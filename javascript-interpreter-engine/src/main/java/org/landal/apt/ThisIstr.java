package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categoria sintattica che indica una istruzione
 * del costruttore che inizia con la keyword 'this'.
 * @author  Landini Alex
 * @version 1.0
 */
public class ThisIstr extends Istr{

 	/**
 	*Nome del campo dell'oggetto
 	*/
 	private IdentExp ident;

 	/**
 	* Espressione che mi fornisce il risultato da assegnare al campo dell'oggetto.
 	*/
 	private Exp val;

 	/**
         * Costruttore. riceve il nome della propriet� e l'espressione da assegnare alla 
         * propriet� dopo essere stata valutata.
         * @param   ident   nome della propriet�
         * @param   val valore da assegnare alla propriet�
         */
        public ThisIstr(IdentExp ident, Exp val){
		this.ident=ident;
		this.val=val;
	}

        /**
         * Fornisce il nome della propriet�.
         * @return IdentExp identificatore del nome della propriet�.
         */
	public IdentExp getNomeProp(){return ident;}

	/**
         * Fornisce il valore da assegnare alla propriet�.
         * @return Exp  valore da assegnare alla propriet�.
         */
        public Exp getPropVal(){return val;}

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
        public String toString (){
		return "this."+ident.toString()+" = "+val.toString()+" ;";
	}


 }
