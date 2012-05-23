package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la generica categoria sintattica istruzione 'break'.
 * @author  Landini Alex
 * @version 1.0
 */
 public class BreakIstr extends SaltoIstr{

    /**
     * Costruttore di BreakIstr.
     */
    public BreakIstr(){};

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
        return "break;";
      }
	
 }