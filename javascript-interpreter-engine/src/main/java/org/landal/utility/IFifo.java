package org.landal.utility;

/**
 * Questa interfaccia definisce il tipo fifo (coda), dalla quale si pu�
 * inserire solo in coda e prelevare elementi dalla testa.
 * @author  Alex Landini
 */
public interface IFifo {
            
	/**
         * Questo metodo restuisce il primo elemento in testa alla fifo 
         * eleminandolo dalla fifo.
         * @return  Object  elemento in testa alla lista
         */
        public Object getVal();
       
	/**
         * Questo metodo inserisce un oggetto in coda alla fifo.
         * @return  o   oggetto da inserire.
         */
        public void insertVal(Object o);

	/**
         * Query per verificare se la coda � vuota.
         * @return  boolean valore che indica lo stato della coda
         */
        public boolean isEmpty();
    
}
