package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categoria sintattica valore booleano di javascript.
 * @author  Landini Alex
 * @version 1.0
 */
 public class BooleanoExp extends LetteraleExp{

 	/**
         * Denota il valore del boolean.
         */
        private boolean val;
        
        /**
         * Costruttore del Boolean con in ingresso un boolean.
         * @param  val  rappresenta il valore.
         */
 	public BooleanoExp(boolean val){this.val = val;}
        
        /**
         * Costruttore del Boolean con in ingresso una stringa.
         * @param  val  rappresenta il valore del boolean.
         */
 	public BooleanoExp(String val){
        
            if(val.compareTo("true") == 0) this.val = true;
                else this.val=false;
        }

 	/**
         * Resituisce il valore del boolean.
         * @return  boolean valore del booleano.
         */
        public boolean getVal(){return val;}

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
        public String toString(){return ""+val;}

 }