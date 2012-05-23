package org.landal.apt;

import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categoria sintattica propriet� di un oggetto
 * come l-value.
 * @author  Landini Alex
 * @version 1.0
 */
 public class PropExp extends IdentExp{

 	/**
         * Costruttore di PropExp
         * @param   name    nome della propriet�.
         */
       public PropExp (String name){ super(name);}

        /**
         * Questo metodo permette a un visitor di valutare la categoria sintattica
         * attraverso la tecnica del double-dispatch.
         * @param   v   rappresenta il visitor che eseguir� la valutazione della classe
         * @throws  VisitorException    nel caso durante la valutazione dell'albero si generi qualche errore.
         */
         public void accept (org.landal.visitor.IVisitor v) throws org.landal.visitor.VisitorException{ v.visit(this); } 

 }