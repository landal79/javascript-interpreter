package org.landal.apt;

/**
 * Questa classe rappresenta la generica categoria sintattica operazione con
 * due operandi.
 * @author  Landini Alex
 * @version 1.0
 */

 public abstract class BinOpExp extends UnOpExp{

	 /**
	 * secondo operando dell'operazione binaria.
	 */
	 protected Exp oper2;

	 /** 
          * Costruttore di BinOpExp.
          * @param  l   primo operando
          * @param  r   secondo operando
          */
         protected BinOpExp (Exp l, Exp r){
		 super(l);
		 oper2=r;
	 }

         /**
          * Restiuisce il secondo operando.
          * return  Exp valore del secondo operando.
          */
         public Exp getOper2(){return oper2;}

        /**
         * Questo metodo consente di ottenere una rappresentazione esterna della categoria sintattica.
         * @return String  viene restituita la rappresetazione esterna sotto forma di stringa.
         */
        public String toString(){
	 	return oper1.toString()+" "+myOp()+" "+oper2.toString();
	 }

 }