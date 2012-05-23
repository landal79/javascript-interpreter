package org.landal.iodevice;

/**
 * Questa classe fornisce le funzionalit� per creare un bottone 
 * che eseguono un particolare comando.
 * @author  Alex Landini
 */
public interface ICmdButton {
    
     /**
     * Questo metodo serve per creare un nuovo pulsante di nome <i>buttonName</i> 
     * e associargli il comando passato come riferimento in <i>cmd</i>. 
     * @param buttonName    nome del bottone.
     * @param cmd   comando da associare al bottone.
     * @param in    dispositivo di input da cui prelevare l'input.
     * @param out   dispositivo di output su cui porre l'uscita.
     */
    public void addCmdButton( String buttonName, ICommand cmd, IInpDev in, IOutDev out );
    
}
