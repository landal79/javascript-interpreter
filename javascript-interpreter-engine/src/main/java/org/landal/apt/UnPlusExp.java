package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categoria sintattica pi� unario: + a. 
 * @author  Landini Alex
 * @version 1.0
 */

 public class UnPlusExp extends UnOpExp{
        
        /**
         * Costruttore.
         * @param   e   operando.
         */
	 public UnPlusExp (Exp e){
		 super(e);
	 }

	  /**
         * Questo metodo permette a un visitor di valutare la categoria sintattica
         * attraverso la tecnica del double-dispatch.
         * @param   v   rappresenta il visitor che eseguir� la valutazione della classe
         * @throws  VisitorException    nel caso durante la valutazione dell'albero si generi qualche errore.
         */
        public void accept (IVisitor v) throws org.landal.visitor.VisitorException{ v.visit(this); } 
     
        /** 
         * resituisce l'operatore.
         * @return  String  la rappresentazione dell'operando.
         */
        public String myOp() { return "+";}
         
 }