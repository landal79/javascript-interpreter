package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la generica categoria sintattica Stringa di javascript.
 * @author  Landini Alex
 * @version 1.0
 */
 public class StringaExp extends LetteraleExp{

 	/**
         * Valore che denota la stringa.
         */
        private String val;

 	/**
         * Costruttore di stringa senza argomenti.
         */
        public StringaExp(){ val = null; }

 	/**
         * Costruttore di stringa che riceve in ingresso il valore della stringa.
         * @param   val valore della stringa.
         */
        public StringaExp(String val){this.val = val;}

 	/**
         * Restituisce il valore della stringa.
         * @return  String  valore della stringa
         */
        public String getVal(){ return val;}

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
            return val;
        }


 }