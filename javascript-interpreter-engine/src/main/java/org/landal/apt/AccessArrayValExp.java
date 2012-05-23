package org.landal.apt;

import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta l'accesso all'array come r-value: m = a[7];
 * @author  Landini Alex
 * @version 1.0
 */

 public class AccessArrayValExp extends AccessArrayExp{

    /** 
     * Costruttore di AccessArrayValExp.
     * @param   e   nome dell'array.
     * @param   i   indice di accesso.
     */
     public AccessArrayValExp (ExpVar e, Exp i){super(e,i);}
     
    /**
     * Questo metodo permette a un visitor di valutare la categoria sintattica
     * attraverso la tecnica del double-dispatch.
     * @param   v   rappresenta il visitor che eseguir� la valutazione della classe
     * @throws  VisitorException    nel caso durante la valutazione dell'albero si generi qualche errore.
     */
     public void accept (org.landal.visitor.IVisitor v) throws org.landal.visitor.VisitorException{ v.visit(this); } 

 }