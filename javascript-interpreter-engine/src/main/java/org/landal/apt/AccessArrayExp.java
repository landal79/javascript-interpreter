package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la generica categoria sintattica accesso agli elementi
 * di un array come l-value: a[i] = 4;
 * @author  Landini Alex
 * @version 1.0
 */
 public class AccessArrayExp extends ExpVar{

 	/**
 	* Questo campo contiene l'identificatore del nome dell'array, oppure pu� contenere
        * a sua volta un riferimento a ExpVar, ottenendo cos� un nome complesso di variabile.
 	*/
 	protected ExpVar var;

 	/**
 	* Questo campo contiene un'espressione che conterr� il valore dell'indice di 
        * array da ricercare.
 	*/
 	protected Exp indice;

 	/**
         * Costruttore di AccessArrayExp
         * @param   e   identificatore dell'array
         * @param   i   indice di accesso all'array.
         */
        public AccessArrayExp (ExpVar e, Exp i){
		var=e;
		indice=i;
	}

	/**
         * Ritorna il nome dell'array.
         * @return ExpVar   nome dell'array.
         */
        public ExpVar getNomeArray(){return var;}

	/**
         * Ritorna l'indice di accesso.
         * @return  Exp indice di accesso.
         */
        public Exp getIndice(){return indice;}

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
		return var.toString()+"[ "+indice.toString()+" ]";
	}

 }
