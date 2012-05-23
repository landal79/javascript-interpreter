package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categoria sintattica istruzione 'switch'.
 * @author  Landini Alex
 * @version 1.0
 */
public class SwitchIstr extends ControlIstr{

    /**
     * Costruttore di SwitchIstr.
     * @param   e   Condizione dello switch.
     * @param   i   corpo dello switch.
     */
    public SwitchIstr (Exp e, Istr i){
		super(e,i);
    }

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
               return "switch ( "+condizione.toString()+" )\n"+corpo.toString();
        }

 }

