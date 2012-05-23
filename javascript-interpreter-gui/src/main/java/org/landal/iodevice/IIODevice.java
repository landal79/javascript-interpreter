package org.landal.iodevice;

/**
 * Questa interfaccia fornisce le principali funzioni per I/O
 * @author  Alex Landini
 */
public interface IIODevice extends IOutDev, IInpDev{
    
    /**
     * Questo metodo serve per creare un nuovo pulsante di nome <i>buttonName</i> 
     * e associargli il comando passato come riferimento in <i>cmd</i>. 
     * @param buttonName    nome del bottone.
     * @param cmd   comando da associare al bottone.
     */
    public void addCmdButton( String buttonName, ICommand cmd );
    
}
