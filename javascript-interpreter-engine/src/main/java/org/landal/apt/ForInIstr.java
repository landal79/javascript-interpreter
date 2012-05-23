package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categorie lessicale ciclo for/in di javascript.
 * @author  Landini Alex
 * @version 1.0
 */
 public class ForInIstr extends Istr{

 	/**
 	* Questa istruzione restituisce una variabile che verr� 
        * usata per scorrere le varie propriet� di un oggetto con un ciclo for/in.
 	*/
 	private DefVarExplIstr var;

 	/**
	* Rappresenta l'istruzione che viene eseguita nel ciclo.
	*/
 	private Istr corpo;

 	/**
 	* Rappresenta l'identificatore dell'oggetto di cui si vogliono vedere le propriet�.
 	*/
 	private ExpVar obj;

 	/**
         * Costruttore di ForInIstr.
         * @param   is  dichiarazione della variabile per il ciclo, deve essere una istruzione con 'var'.
         * @param   obj oggetto di cui si vogliono vedere le propriet�.
         * @param   corpo   corpo del ciclo.
         */
        public ForInIstr (DefVarExplIstr is, ExpVar obj, Istr corpo){
		this.var=is;
		this.obj=obj;
		this.corpo=corpo;
	}

	/**
         * Fornisce l'istruzione di dichiarazione esplicita della variabile di ciclo.
         * @return  DefVarExplIstr  istruzione di dichiarazione della variabile di ciclo.
         */
        public DefVarExplIstr getVar(){return var;}

	/**
         * Fornisce il corpo del ciclo.
         * @param Istr  il corpo � una generica istruzione.
         */
        public Istr getCorpo(){return corpo;}

	/**
         * Fornisce l'oggetto di cui si vogliono scorrere le propriet�.
         * @return  ExpVar  rappresenta un identificatore di variabile.
         */
        public ExpVar getObj(){return obj;}

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
        public String toString (){

		return "for ( "+var.toString2()+" in "+obj.toString()+"  ) \n"+corpo.toString();
	}
	
 }