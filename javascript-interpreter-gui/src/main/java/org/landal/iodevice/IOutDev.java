package org.landal.iodevice;

/**
 * Questa interfaccia fornisce le principali funzionalit� di output.
 * @author  Alex Landini
 * @version 1.0
 */
public interface IOutDev {
    
    /**
     * Questo metodo serve per visualizzare una stringa sul dispositivo di output.
     * @param msg   rappresenta il messaggio da visualizzare.
     */
    public void print(String msg);
    
    /**
     * Questo metodo serve per visualizzare una stringa sul dispositivo di output,
     * andando a capo dopo averla visualizzata.
     * @param msg   rappresenta il messaggio da visualizzare.
     */
    public void println(String msg);
     
    
}
