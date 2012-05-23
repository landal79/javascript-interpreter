package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categoria lessicale di accesso alle propriet�
 *  di un oggetto come l-value: b.m = 65.
 * @author  Landini Alex
 * @version 1.0
 */
 public class AccessPropertyExp extends ExpVar{

 	/**
 	* Questo campo contiene la parte sinistra di una dot notation.
 	*/
 	protected ExpVar left;

 	/**
 	* Questo campo contiene la parte destra di una dot notation, 
        * cio� l'identificatore di una propriet�.
 	*/
 	protected PropExp right;

 	/**
         * Costruttore di AccessPropertyExp
         * @param   l   nome dell'oggetto.
         * @param   r   nome della propriet�
         */
        public AccessPropertyExp(ExpVar l, PropExp r){
		left=l;
		right=r;
	}

	/**
         * Ritorna il valore a sinistra della dot-notation, cio� il nome dell'oggetto.
         * @return  nome dell'oggetto.
         */
        public ExpVar getLeft(){return left;}

	/**
         * Ritorna il valore a destra della dot-notation, cio� il nome della propriet�.
         * @return PropExp  nome della propriet�.
         */
        public PropExp getRight(){return right;}

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
		return left.toString()+"."+right.toString();
	}

 }