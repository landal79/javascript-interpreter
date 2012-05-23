package org.landal.iodevice;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Questa classe rappresenta l'ascoltatore dell'evento di chiusura
 * della finestra. 
 * @author  Alex Landini
 * @version 1.0
 */
public class FrameHandler extends WindowAdapter{
    
    /**
     * Gestore dell'evento di chiusura.
     */
    public void windowClosing( WindowEvent e){
        
        System.exit(0);
        
    }//windowClosing
    
}//FrameHandler
