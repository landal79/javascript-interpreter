package org.landal.visitor.datatype;

/**
 * Questa classe rappresenta il valore costante nessun valore: NaN.
 * @author  Landini Alex
 * @version 1.0
 */
 public class NaN extends CostantType{
           
        /** 
	 *Costruttore di NaN.
         */
 	public NaN(){}

        /**
         * Questo metodo mi fornisce una rappresentazione esterna sotto forma
         * del generico tipo di dato.
         * @return  String      rappresentazione esterna.
         */
 	public String toString(){ return "NaN"; }

 }
