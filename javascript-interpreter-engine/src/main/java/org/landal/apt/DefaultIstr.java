package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categoria sintattica istrutzione 'default'.
 * @author  Landini Alex
 * @version 1.0
 */
 public class DefaultIstr extends Istr{

 	/**
 	* Istruzione che viene eseguita di default in uno switch quando non � stato 
        * selezionato nessun case.
 	*/
 	private Istr i;

 	/**
         * Costruttore riceve l'istruzione da eseguire.
         * @param   i   istruzione da eseguire da parte del default.
         */
        public DefaultIstr (Istr i){
 		this.i=i;
 	}

 	/**
         * Fornisce l'istruzione del delfault.
         * @return Istr istruzione del default.
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
        public String toString (){
 		return "default : "+i.toString()+" \n";
 		}

 }