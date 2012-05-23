package org.landal.apt;

/**
 * Questa classe astratta rappresenta la generica istruzione di controllo.
 * @author  Landini Alex
 * @version 1.0
 */
 public abstract class ControlIstr extends Istr{

 	/**
 	*questo campo rappresenta la condizione dell'istruzione di controllo.
 	*/
 	protected Exp condizione;

 	/**
 	*questo campo rappresenta l'istruzione che verr� eseguita se � verificata la condizione
 	*/
 	protected Istr corpo;
        
        /**
         * Costruttore di ControllIstr, la classe non pu� essere istanziata, ma il 
         * Costrutture pu� essere utilizzato dalle sottoclassi.
         * @param   e   espressione dell'istruzione di controllo.
         * @param   i   corpo dell'istruzione di controllo
         */
 	protected ControlIstr (Exp e, Istr i){
		this.condizione = e;
		this.corpo = i;
	}//ControlIstr

	/**
         * Questo metodo consente di reperire la condizione.
         * @return  la condizione � una espessione.
         */
        public Exp getCond(){ return condizione;}

	/**
         * Consente di reperire il corpo dell'istruzione di controllo.
         * @return  Istr    il corpo dell'istruzione di controllo � ancora un'istruzione-
         */
        public Istr getCorpo(){ return corpo;}
        
}//ControlIstr
