package org.landal.iodevice;

/**
 * Questa interfaccia definisce definisce un generico comando che pu� essere 
 * eseguito premendo un pulsante dell'interfaccia, oppure segliendo un voce da 
 * un menu. Le classi concrete che la implementano devono fornire l'implementazione
 * del metodo <i>execute</i> che fornisce l'azione effettiva da eseguire.
 * @author  Alex Landini
 * @version 1.0
 */
public interface ICommand {
    
    /**
     * Questo metodo riceve in ingresso una stringa e pone il risultato 
     * dell'elaborazione in una stringa.
     * @param input  stringa di input.
     * @return  risultato dell'elaborazione.
     */
    public String execute (String input);
    
}
