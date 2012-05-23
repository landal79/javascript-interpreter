package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categoria espressione condizionnale: a<0 ? 1 : 0.
 * @author  Landini Alex
 * @version 1.0
 */
 public class ExpCond extends BinOpExp{

 	/**
 	*terzo operando dell'operazione
 	*/
 	private Exp oper3;
        
        /**
         * Costruttore.
         * @param   cond    condizione.
         * @param   vero    espressione da eseguire se condizione vera
         * @param   falso   espressione da eseguire se condizione false
         */
 	public ExpCond (Exp cond, Exp vero, Exp falso){
 		super(cond, vero);
 		oper3=falso;
 		}

 	/**
         * Ritorna il terzo operando dell'istruzione.
         * @return Exp terzo operando dell'istruzione.
         */
        public Exp getOper3(){return oper3;}

        protected String myOp(){return "? :";}

 	/**
         * Ritorna la rappresentazione esterna dell'espressione condizionale.
         * @return  String  rappresentazione esterna dell'espressione condizionale.
         */
        public String toString(){
 		return oper1.toString()+" ? "+oper2.toString()+" : "+oper3.toString();
 	}
        
        /**
         * Questo metodo permette a un visitor di valutare la categoria sintattica
         * attraverso la tecnica del double-dispatch.
         * @param   v   rappresenta il visitor che eseguir� la valutazione della classe
         * @throws  VisitorException    nel caso durante la valutazione dell'albero si generi qualche errore.
         */
        public void accept (IVisitor v) throws org.landal.visitor.VisitorException{ v.visit(this); } 
        
        
 }