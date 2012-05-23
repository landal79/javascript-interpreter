package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categoria lessicale istruzione 'return'.
 * @author  Landini Alex
 * @version 1.0
 */
 public class ReturnIstr extends SaltoIstr{

 	/**
 	* Espressione eventualmente ritornata nel caso return abbia argomenti.
        * Non � detto che sia presente
 	*/
 	private Exp retVal = null;

 	/**
         * Costruttore di ReturnIstr, quando l'istruzione non ha valore di ritorno.
         */
        public ReturnIstr(){ }

 	/**
         * Costruttore di ReturnIstr, quando l'istruzione ritorna un valore.
         * @param   e   valore eventualmente ritornato dall'istruzione return.
         */
        public ReturnIstr(Exp e){
		this.retVal = e;
	}

	/**
         * Questo metodo restitusce l'espressione che l'istruzione return deve restituire.
         * @return  Exp rappresenta l'istruzione che il return deve ritornare.
         */
        public Exp getRetVal(){return retVal;}

 	/**
         * Fornisce la rappresentazione esterna di ReturnIStr.
         * @return  rappresentazione esterna di ReturnIstr.
         */
        public String toString(){
		if(retVal != null) return "return "+retVal.toString()+";\n";
			else return "return;\n";
	}//toString
/**
         * Questo metodo permette a un visitor di valutare la categoria sintattica
         * attraverso la tecnica del double-dispatch.
         * @param   v   rappresenta il visitor che eseguir� la valutazione della classe
         * @throws  VisitorException    nel caso durante la valutazione dell'albero si generi qualche errore.
         */
         public void accept ( IVisitor v ) throws VisitorException{ v.visit(this); }
         
 }//ReturnIstr