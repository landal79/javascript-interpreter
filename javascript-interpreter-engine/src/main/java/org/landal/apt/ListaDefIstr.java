package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categoria sintattica lista di definizioni, che
 * viene dichiarata attraverso la keyword 'var'.
 * In Pratica � strutturata come una lista che fornisce una variabile e il riferimento
 * alla variabile successiva.
 * @author  Landini Alex
 * @version 1.0
 */

 public class ListaDefIstr extends Istr{

 	/**
 	* Nome della variabile.
 	*/
 	private IdentExp nomeVar;

 	/**
 	* Espressione che restituisce il valore della variabile.
 	*/
 	private Exp val;

 	/**
 	* In questo campo � contenuto un riferimento alla definizione successiva.
 	*/
 	private ListaDefIstr succ;

	/**
	* Costruttore nel caso sia l'unica definizione o l'ultima definizione e 
        * non venga assegnato alcun parametro alla variabile.
        * @param    i   nome della variabile.
	*/
	public ListaDefIstr(IdentExp i){
				this.nomeVar=i;
				this.val=null;
				this.succ=null;
	}

	/**
	* Costruttore nel caso sia l'unica definizione o l'ultima definizione.
        * @param    i   nome della variabile.
        * @param    e   espressione da assegnare alla variabile.
	*/
	public ListaDefIstr(IdentExp i, Exp e){
			this.nomeVar=i;
			this.val=e;
			this.succ=null;
	}

 	/**
 	* Costruttore nel caso non venga assegnato alcun parametro alla variabile e ci siano
        * altre variabili.
        * @param    i   nome della variabile.
        * @param    l   riferimento alla variabile successiva.         
 	*/
 	public ListaDefIstr(IdentExp i, ListaDefIstr l){
			this.nomeVar=i;
			this.val=null;
			this.succ=l;
	}

 	/**
         * Costruttore che riceve il nome della variabile, il valore che deve assumere e
         *  il riferimento alla variabile successiva.
         * @param   i   nome della variabile.
         * @param   e   espressione da assegnare alla variabile
         * @param   l   riferimento alla variabile successiva.
         */
        public ListaDefIstr(IdentExp i, Exp e, ListaDefIstr l){
		this.nomeVar=i;
		this.val=e;
		this.succ=l;
	}

	/**
         * Fornisce il nome della variabile.
         * @return  IdentExp    nome della variabile
         */
        public IdentExp getNomeVar() {return nomeVar;}

	/**
         * Fornisce l'espressione da assegnare alla variabile.
         * @return Exp  espressione da assegnare alla variabile.
         */
        public Exp getVal(){return val;}

	/**
         * Fornisce il riferimento alla variabile successiva.
         * @return  ListaDefIstr    riferimento alla variabile successiva.
         */
        public ListaDefIstr getSucc(){return succ;}

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

            if (succ != null) {
                    if (val != null) return nomeVar.toString()+" = "+val.toString()+ " , "+succ.toString();
                            else return nomeVar.toString()+" = undefined  , "+succ.toString();
                }
                else {
                        if (val != null) return nomeVar.toString()+" = "+val.toString();
                        else return nomeVar.toString()+" = undefined ";

                }
	}//toString

 }
