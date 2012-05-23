package org.landal.iodevice;

import javax.swing.JFrame;

/**
 * Questa classe crea una finestra. 
 * @author  Alex Landini
 * @version 1.0
 */
public class Frame extends JFrame{
	
	private static final long serialVersionUID = 1L;
       
    /** 
     * Costruttore di Frame che invoca un metodo di configurazione locale.
     * Essendo attivo il late binding, la configurazione pu� essere specializzata
     * dalle sottoclassi che possono continuare a delegare la 
     * costruzione a questo costruttore.
     * @param name  nome da assegnare alla finestra.
     */
    public Frame(String name) {
        
        super(name);
        
        configura();
        
    }//Frame
    
    /**
     * Configurazione nel frame.
     * Questo metodo viene di norma specializzato dalle sottoclassi.
     */
    protected void configura(){
        //imposta la dimensione della finestra
        setSize(800,600);
        
    }//configura
    
}
