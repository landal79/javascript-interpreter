package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categoria sintattica istruzione 'if'.
 * @author  Landini Alex
 * @version 1.0
 */
  public class IfIstr extends ControlIstr{

 	/**
 	* Questa � l'istruzione che viene eseguita se la condizione non � verificata.
        * Il Campo � opzionale, ci potrebbe essere un'istruzione if, senza else.
 	*/
 	protected Istr _else;

 	/**
 	* Costruttore nel caso non ci sia l'alternativa else.
        * @param    e   rappresenta la condizione.
        * @param    i   rappresenta il corpo del ramo then dell'if.
 	*/
 	public IfIstr (Exp e, Istr th){
			super(e,th);
			this._else=null;
	}//IfIstr

 	/**
         * Costruttore di IfIstr nel caso ci sia l'alternativa else.
         * @param   e   rappresenta la condizione.
         * @param   i   rappresenta il corpo del ramo then dell'if.
         * @param   el  rappresenta il corpo del ramo else dell'if.
         */
        public IfIstr (Exp e, Istr th, Istr el){
		super(e,th);
		this._else=el;
	}//IfIstr
        
        /**
         * Questo metodo consente di reperire la condizione dell'if.
         * @return  la condizione � una espessione.
         */
        public Exp getCond(){ return super.getCond();}

	/**
         * Consente di reperire il corpo del ramo then dell'if.
         * @return  Istr    il corpo dell'istruzione di controllo � ancora un'istruzione-
         */
        public Istr getCorpo(){ return super.getCorpo(); }

	/**
         * Questo metodo ritorna il corpo dell'else.
         * @return  Istr    rappresenta il corpo dell'else
         */
        public Istr getElse(){return _else;}

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
		if (_else != null) return "if ( "+getCond().toString()+" ) "+getCorpo().toString()+" else "+ _else.toString();
			else return "if ( "+getCond().toString()+" ) "+getCorpo().toString();
	}//toString


 }




