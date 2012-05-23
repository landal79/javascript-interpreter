package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categoria sintattica espressione di disuguaglianza '!='.
 * @author  Landini Alex
 * @version 1.0
 */
 public class NotEqualExp extends BinOpExp{

 	/**
         * Costruttore.
         * @param   l   operando di sinistra.
         * @param   r   operando di destra.
         */
         public NotEqualExp (Exp l, Exp r){ super(l,r);}
         
        /**
         * Questo metodo permette a un visitor di valutare la categoria sintattica
         * attraverso la tecnica del double-dispatch.
         * @param   v   rappresenta il visitor che eseguir� la valutazione della classe
         * @throws  VisitorException    nel caso durante la valutazione dell'albero si generi qualche errore.
         */
        public void accept (IVisitor v) throws org.landal.visitor.VisitorException{ v.visit(this); } 
        
        /**
          * Operazione di stampa da specializzare per dalle sottoclassi.
          * @return String  l'operatore dell'operazione.
          */
 	public String myOp(){ return "!=";}

 }