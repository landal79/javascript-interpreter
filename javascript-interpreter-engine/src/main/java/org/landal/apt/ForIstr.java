package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categoria sintattica ciclo 'for'.
 * @author  Landini Alex
 * @version 1.0
 */
 public class ForIstr extends ControlIstr{

 	/**
 	* Questo campo contiene l'inizializzazione del contatore di ciclo.
 	*/
 	private Istr iniz;

 	/**
 	* Questo campo contiene l'espressione di incremento del contatore del ciclo.
 	*/
 	private Exp inc;

	/**
	* Costruttore per un for di cui ne viene fornito soltanto il corpo.
        * @param     corpo  corpo del ciclo for.
	*/
	public ForIstr (Istr corpo){
			super(null, corpo);
			this.iniz=null;
			this.inc=null;
	}

 	/**
         * Costruttore nel caso siano presenti inizializzazione, condizione, incremento 
         * e corpo del ciclo for.
         * @param   iniz    istruzione di inizializzazione.
         * @param   cond    espressione che rappresenta la condizione
         * @param   inc     espressione di incremento dell'indice
         * @param   corpo   istruzione che rappresenta il corpo del ciclo
         */
        public ForIstr (Istr iniz, Exp cond, Exp inc, Istr corpo){
		super(cond, corpo);
		this.iniz=iniz;
		this.inc=inc;
	}

	/**
         * Questo metodo restituisce l'istruzione di inizializzazione.
         * @return  Istr    istruzione di inizializzazione
         */
        public Istr getIniz(){return iniz;}

	/**
         * Ritorna l'espressione di incremento.
         * @return  Exp espressione di incremento.
         */
        public Exp getInc(){return inc;}

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
                String  str = "for ( ";
                
                if (iniz != null) str = str + iniz.toString() +" ; ";
                    else str = str +"; ";
                
                if (getCond() != null) str  = str + getCond().toString() +" ; ";
                    else str = str + "; ";
                
                if (inc != null) str = str + inc.toString() +" ) ";
                    else str = str + ") ";
                
                if (getCorpo() != null) str = str + getCorpo().toString()+ " ;";
                    else str = str +";";
                
		return str;
	}	


 }
