package org.landal.apt;

import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categoria sintattica accesso alle propriet� di 
 * un oggetto come r-value: m = b.o;
 * @author  Landini Alex
 * @version 1.0
 */
  public class AccessPropertyValExp extends AccessPropertyExp{

        /**
         * Costruttore di AccessPropertyValExp
         * @param   l   nome dell'oggetto 
         * @param   r   nome della propriet�
         */
      public AccessPropertyValExp(ExpVar l, PropValExp r){
		super(l,r);
	}
      
       /**
         * Questo metodo permette a un visitor di valutare la categoria sintattica
         * attraverso la tecnica del double-dispatch.
         * @param   v   rappresenta il visitor che eseguir� la valutazione della classe
         * @throws  VisitorException    nel caso durante la valutazione dell'albero si generi qualche errore.
         */
         public void accept (org.landal.visitor.IVisitor v) throws org.landal.visitor.VisitorException{ v.visit(this); } 

         
 }