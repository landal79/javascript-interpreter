package org.landal.iodevice;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;


/**
 * Questa classe crea un insieme di bottoni con un nome ciascuno e fornisce 
 * i metodi necessari per associare il particolare comando ad ogni bottone.
 * @author  Alex Landini
 * @version 1.0
 */
public class CmdButton extends CommandButton{
	
	private static final long serialVersionUID = 1L;
    
    /**
     * Rappresenta il numero di pulsanti.
     */
    private int nButton = 0;        
    
    /**
     * Questa classe funge da ascoltatore degli eventi generati dai bottoni,
     * essa � configurabile, infatti il suo comportamento varia a seconda 
     * del comando che gli viene associata.
     */
    class ButtonListener implements ActionListener {
        
        /**
         * Rappresenta il comando da eseguire.
         */
        private ICommand cmd;
        
        /**
         * Rappresenta il dispositivo di input da cui prelevare l'input.
         */
        private IInpDev in;
        
        /**
         * Rappresenta il dispositivo di output su cui porre il risultato
         */
        private IOutDev out;
        
        /**
         * Costruttore di ButtonListener che riceve in ingresso l'azione da eseguire
         * nel caso venga premuto il pulsante del quale � ascoltatore.
         * @param   cmd comando da eseguire alla pressione del bottone.
         * @param in    dispositivo di input da cui prelevare l'input.
         * @param out   dispositivo di output su cui porre l'uscita.
         */
        public ButtonListener(ICommand cmd, IInpDev in, IOutDev out){
            
            this.cmd = cmd;
            this.in = in;
            this.out = out;
            
        }//ButtonListener
        
        /**
         * Questo metodo rappresenta l'azione da compiere quando viene premuto il
         * pulsante associato all'evento.
         * @param e evento ricevuto.
         */
        public void actionPerformed(ActionEvent e) {
            
            out.println( cmd.execute( in.read() ) );
             
        }//actionPerformed
        
    }//ButtonListener
    
    
    /**
     * Costruttore di CmdButton.
     * Richiama il metodo di configurazione.
     */
    public CmdButton() {
      
          configura();
        
    }//CmdButton
    
   /**
    * Questo metodo permette di configurare il pannello dei bottoni.
    */
    protected void configura(){
                       
        //stabilisco il Layout
        setLayout( new GridLayout( 1, nButton ) );
        
        //imposto i bordi al pannello
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(00,00,00)), "Operazioni"));                
        
    }//configura
    
    /**
     * Questo metodo serve per creare un nuovo pulsante di nome <i>buttonName</i> 
     * e associargli il comando passato come riferimento in <i>cmd</i>. 
     * @param buttonName    nome del bottone.
     * @param cmd   comando da associare al bottone.
     * @param in    dispositivo di input da cui prelevare l'input.
     * @param out   dispositivo di output su cui porre l'uscita.
     */
    public void addCmdButton( String buttonName, ICommand cmd, IInpDev in, IOutDev out ){
        //creo il bottone
        JButton cmdButton = new JButton(buttonName);
        
        //creo il listener per il pulsante
        ButtonListener bl = new ButtonListener(cmd,in,out);
        cmdButton.addActionListener(bl);
        
        //aggiungo il bottone al pannello       
        ((GridLayout) this.getLayout()).setColumns(++nButton);
        this.add(cmdButton);
        
        this.validate();               
        
    }//addCmdButton
    
    
}
