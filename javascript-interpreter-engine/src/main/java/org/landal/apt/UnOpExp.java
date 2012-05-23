package org.landal.apt;

/**
 * Questa classe rappresenta la categoria sintattica operazioni con un operando.
 * @author  Landini Alex
 * @version 1.0
 */

 public abstract class UnOpExp extends Exp{

	 /**
	 *operando dell'operazione
	 */
	 protected Exp oper1;
         
         /**
          * Costruttore.
          * @param  e   primo operando.
          */
	 protected UnOpExp (Exp e){
		 oper1=e;
	 }
         
         /**
          * Restituisce il primo operando.
          * @return Exp valore del primo operando.
          */
	 public Exp getOper1(){return oper1;}
         
         /**
          * Operazione di stampa da specializzare per dalle sottoclassi.
          * @return String  l'operatore dell'operazione.
          */
         protected abstract String myOp ();
     
        /**
         * Questo metodo consente di ottenere una rappresentazione esterna della categoria sintattica.
         * @return String  viene restituita la rappresetazione esterna sotto forma di stringa.
         */
         public String toString(){
             return myOp( )+" "+oper1.toString();
         }

 }