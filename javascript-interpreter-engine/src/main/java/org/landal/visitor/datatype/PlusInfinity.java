package org.landal.visitor.datatype;

/**
 * Questa classe rappresenta la costante: + infinito.
 * @author  Landini Alex
 * @version 1.0
 */
 public class PlusInfinity extends CostantType{

        /**
         * Costruttore di PlusInfinity.
         */
 	public PlusInfinity(){};

 	/**
         * Questo metodo mi fornisce una rappresentazione esterna sotto forma
         * del generico tipo di dato.
         * @return  String      rappresentazione esterna.
         */
        public String toString(){ return "+Infinity"; }

 }
