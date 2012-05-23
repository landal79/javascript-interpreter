package org.landal.iodevice;

/**
 * Questa interfaccia fornisce 
 * tutte le operazioni necessarie per l'input.
 * @author  Alex Landini
 * @version 1.0
 */
public interface IInpDev {
    
     /**
     * Questo metodo legge il contenuto del dispositivo di input restituendolo 
     * sotto forma di stringa.
     * @return  rappresenta la stringa letta sul dispositivo di input.
     */
    public String read();
    
   /**
    * Questo metodo permette di inserire un messaggio indicativo per richiedere dati.
    * @param    msg     rappresenta il messaggio da inserire
    */
    public void emit (String msg);
    
}
