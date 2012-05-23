package org.landal.apt;

import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categoria sintattica propriet� di un oggetto come
 * r-value.
 * @author  Landini Alex
 * @version 1.0
 */

 public class PropValExp extends PropExp{
        
        /**
         * Costruttore di PropValExp.
         *  @param name nome della propriet�.
         */
 	public PropValExp(String name){super(name);}

         /**
         * Questo metodo permette a un visitor di valutare la categoria sintattica
         * attraverso la tecnica del double-dispatch.
         * @param   v   rappresenta il visitor che eseguir� la valutazione della classe
         * @throws  VisitorException    nel caso durante la valutazione dell'albero si generi qualche errore.
         */
         public void accept (org.landal.visitor.IVisitor v) throws org.landal.visitor.VisitorException{ v.visit(this); } 
 }