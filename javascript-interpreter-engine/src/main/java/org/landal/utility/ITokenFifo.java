package org.landal.utility;

import org.landal.token.IToken;

/**
 * Questa interfaccia definisce il tipo fifo (coda) per il tipo di oggetti Token, dalla quale si pu�
 * inserire solo in coda e prelevare elementi dalla testa.
 * @author  Alex Landini
 * @version 1.0
 */
public interface ITokenFifo extends IFifo{
            
	/**
         * Questo metodo restuisce il primo token in testa alla fifo 
         * eleminandolo dalla fifo.
         * @return  Token  elemento in testa alla lista
         */
        public IToken getFifoToken();
       
	/**
         * Questo metodo inserisce un token in coda alla fifo.
         * @return  o   oggetto da inserire.
         */
        public void insertFifoToken(IToken o);

}
