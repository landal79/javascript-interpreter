package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categoria sintattica valore numero di javascript.
 * @author  Landini Alex
 * @version 1.0
 */
 public class NumExp extends LetteraleExp{

 	/**
         * Valore che denota il numero.
         */
        private double val;

        /**
         * Costruttore che riceve un numero e lo memorizza.
         * @param   val valore del numero
         */
 	public NumExp(double val){ this.val = val;}
        
         /**
         * Costruttore che riceve una Stringa e lo memorizza.
         * @param   val valore del numero
         */
 	public NumExp(String val){ this.val = Double.parseDouble(val);}

 	/**
         * ritorna il valore memorizzato.
         * @return  Double  ritorna un oggetto Double contenente il valore
         */
        public double getVal(){ return val;}

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