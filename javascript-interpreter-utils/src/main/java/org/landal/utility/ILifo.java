package org.landal.utility;

/**
 * Questa interfaccia rappresenta l'astrazione della struttura dati Lifo. 
 * @author  Alex Landini
 */
public interface ILifo {
    
    /**
     * Controlla se la lifo � vuota.
     * @return  boolean     valore booleano che indica se la lifo � piena o vuota
     */
    public boolean isEmpty();
    
    /**
     * Inserisce un oggetto al top della lifo.
     * @param o oggetto da inserire.
     */
    public void push(Object o);
    
    /**
     * Prende il primo oggetto in cima alla lifo.
     * @return  Object  primo oggetto in cima alla lifo.
     */
    public Object pop();        
    
}
