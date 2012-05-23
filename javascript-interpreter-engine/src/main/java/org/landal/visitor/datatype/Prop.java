package org.landal.visitor.datatype;

/**
 * Questa classe rappresenta il tipo propriet� ha un campo nome propriet�
 * e uno per il valore della propriet�.
 * @author  Landini Alex
 * @version 1.0
 */

 public class Prop implements IProp{

 	/**
         * Nome della propriet�.
         */
        private String nome;

 	/**
         * Valore della propriet�.
         */
        private Object val;

 	/**
         * Costruttore di Prop senza parametri.
         */
        public Prop (){
 		nome = null;
 		val = null;
 	}

        /**
         * Costruttore di prop nel caso si abbia un propriet� senza valore.
         * @param   name    nome della propriet�.
         */
 	public Prop (String name){
 		nome = name;
 		val = null;
 	}

 	/**
         * Costruttore di Prop.
         * @param name    nome della propriet�.
         * @param   o   oggetto da inserire come valore.
         */
        public Prop (String name, Object o){

		nome = name;

		val = o;
	}

	/**
         * Restituisce il nome della propriet�.
         * @return  String  nome della propriet�.
         */
        public String getNome(){ return nome;}

	/**
         * Imposta il nome della propriet�.
         * @param   name  nome della propriet�.
         */
        public void setNome(String name){ nome = name;}

	/**
         * Restituisce il valore della propriet�.
         * @return Object   valore della propriet�.
         */
        public Object getVal(){ return val;}

	/**
         * Imposta il valore della propriet�.
         * @param   o   oggetto da inserire come valore della propriet�.
         */
        public void setVal(Object o){ val = o;}

        /**
         * Rappresentazione esterna della propriet�.
         * @return  String  rappresentazione esterna.
         */
        public String toString(){ return nome+":"+val.toString();}

 }