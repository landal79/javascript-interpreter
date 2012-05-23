package org.landal.iodevice;

import java.awt.event.WindowListener;

/**
 * Questa classe eredita da Frame e crea una finestra, che imposta un listener
 * per gli eventi della finestra.
 * @author  Alex Landini
 * @version 1.0
 */
public class FrameClosing extends Frame{
	
	private static final long serialVersionUID = 1L;
    
    /**
     * Variabile che denota un listener per gli eventi della finestra
     */
    protected WindowListener fh = null;
    
    /**
     * Costruttore che delega la costruzione al
     * costruttore della superclasse.
     * il costruttore della superclasse costruisce il frame.
     * e  lo configura usando il metodo locale.
     * In questo modo verr� impostato un ascoltatore di default della finestra
     * che chiuder� l'applicazione all'interno della finestra alla chiusura 
     * @param   s   nome della finestra.
     */
    public FrameClosing(String s) {
        
        super(s);
        
        configura();
        
    }//FrameClosing
    
     /**
     * Costruttore che delega la costruzione al
     * costruttore della superclasse.
     * il costruttore della superclasse costruisce il frame
     * e lo configura usando il metodo locale.
     * Il secondo argomento rappresenta un listener che serve a impostare le
     * azioni da compiere quando accadono eventi che riguradano la finestra
     * @param s    nome da dare alla finestra. 
     * @param lis  ascoltatore degli eventi generati dalla finestra.
     */
    public FrameClosing(String s, WindowListener lis) {
        
        super(s);
        
        fh = lis;
        
        configura();
        
    }//FrameClosing
        
    /**
     *Specializzazione della configurazione che definisce un
     *listener per l'evento
     */
    protected void configura(){
        
       super.configura();
       //se non � stato impostato nessun ascoltatore degli eventi ne verr� impostato uno di default 
       if (fh == null) fh = new FrameHandler();
            addWindowListener( fh );
    }//configura
    
}
