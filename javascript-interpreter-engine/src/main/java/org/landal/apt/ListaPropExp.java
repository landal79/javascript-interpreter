package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categoria sintattica lista delle propriet� di un 
 * oggetto quando viene definito con un espressione. a = {d:4,y:7}.
 * Viene modellata come una lista che contiene un elemento e un riferimento al
 * successivo.
 * @author  Landini Alex
 * @version 1.0
 */
 public class ListaPropExp extends Exp{

 	/**
 	* Campo che contiene l'identificatore della propriet�.
 	*/
 	private IdentExp nomeProp;

 	/**
 	* Questo campo contiene il valore della propriet�.
 	*/
 	private Exp propVal;

 	/**
 	* Questo campo contiene un riferimento alla definizione della propriet� successiva
 	*/
 	private ListaPropExp succ;

 	/**
 	* Costruttore nel caso sia l'unica o l'ultima propriet� definita.
        * @param    id  identificatore della propriet�
        * @param    l   valore della propriet�
 	*/
 	public ListaPropExp (IdentExp id, Exp l){
			this.nomeProp=id;
			this.propVal=l;
			this.succ=null;
	}

 	/**
 	* Costruttore nel caso ci siano pi� propriet� definite.
        * @param    id  identificatore della propriet�
        * @param    l   valore della propriet�
        * @param    next   riferimento all'elemento successivo.
 	*/
        public ListaPropExp (IdentExp id, Exp l, ListaPropExp next){
		this.nomeProp=id;
		this.propVal=l;
		this.succ=next;
	}

	/**
         * Restituisce il nome della propriet�.
         * @return  IdentExp    nome della propriet�.
         */
        public IdentExp getNomeProp(){return nomeProp;}

	/**
         * Restituisce il valore della propriet�.
         * @return  Exp valore della propriet�
         */
        public Exp getPropVal(){return propVal;}

	/**
         * Ritorna un riferimento alla propriet� successiva.
         * @return  ListaPropExp    riferimento alla propriet� successiva.
         */
        public ListaPropExp getNextProp(){return succ;}

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
		if (succ != null) return nomeProp.toString()+" : "+propVal.toString()+" , "+succ.toString();
			else return nomeProp.toString()+" : "+propVal.toString();
	}

	
 }