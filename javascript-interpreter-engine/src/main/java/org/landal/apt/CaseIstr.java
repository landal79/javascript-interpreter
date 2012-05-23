package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categoria sintattica istruzione 'case'.
 * Pu� stare solo dentro a un costrutto 'switch'.
 * @author  Landini Alex
 * @version 1.0
 */
 public class CaseIstr extends Istr{

 	/**
 	* Etichetta che distingue i casi deve essere una espressione costante.
 	*/
 	private Exp label;
        
 	/**
 	* Istruzione che viene eseguita in corrispondenza della selezione della relativa etichetta.
 	*/
 	private Istr i;

 	/**
         * Costruttore gli viene passata l'etichetta del case e l'istruzione associata all'etichetta.
         * @param label etichetta del case.
         * @param   i   istruzione da eseguire in corrispondenza del case.
         */
        public CaseIstr (Exp label, Istr i){
 		this.label=label;
 		this.i=i;
 	}

 	/**
         * Fornisce l'etichetta del case.
         * @return  Exp espressione del case.
         */
        public Exp getLabel(){return label;}

 	/**
         * Fornisce l'istruzione associata al case.
         * @return Istr istruzione del case.
         */
        public Istr getIstr(){return i;}

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

		if (i != null) return "case " + label.toString() +" : "+ i.toString();
			else return "case" + label.toString()+" : ";
        }

 }