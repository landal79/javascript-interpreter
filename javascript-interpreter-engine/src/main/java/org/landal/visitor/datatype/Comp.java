package org.landal.visitor.datatype;

/**
 * Questa classe rappresenta la generica componente con un indice e un valore.
 * @author  Landini Alex
 * @version 1.0
 */

 public class Comp implements IComp{

 	/**
         * Indice dell componente.
         */
        private int indice;

 	/**
         * valore della componente.
         */
        private Object val;

 	/**
         * Costruttore senza parametri.
         */
        public Comp(){
 		indice = 0;
 		val = null;
 	}

        /**
         * Costruttore nel caso si inserisca un componente solo con indice.
         * @param  i    indice.
         */
 	public Comp(int i){
		indice = i;
		val = null;
	}

        /**
         * Costruttore di una componente.
         * @param   i   indice della componente.
         * @param   o   valore della componente.
         */
 	public Comp(int i, Object o){
		indice = i;
		val = o;
        }

	/**
         * Restituisce l'indice della componente.
         * @return  int     indice della componente.
         */
        public int getIndice(){ return indice;}

	/**
         * imposta l'indice della componente.
         * @param   i   indice del componente.
         */
        public void setIndice(int i){ indice = i;}

	/**
         * Reperisce il valore della componente.
         * @return  Object   valore della componente.
         */
        public Object getVal(){ return val;}

	/**
         * Imposta il valore della componente.
         * @param   o   oggetto da inserire come valore della componente.
         */
        public void setVal(Object o){ val = o;}

	/**
         * Fornisce una rappresentazione esterna della componente.
         * @return  String  rappresentazione esterna della componente.
         */
        public String toString(){ return indice+":"+val.toString();}

 }