package org.landal.iodevice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

/**
 * Questa classe rappresenta il dispositivo di output e fornisce tutte  le
 * funzionalit� necessarie per operare su di esso.
 * @author  Alex Landini
 * @version 1.0
 */
public class OutDev extends OutputDev{
	
	private static final long serialVersionUID = 1L;
   
    /**
     * Etichetta che indica il nome dell'area
     */
    private JLabel outLabel;
       
    /**
     * Area di output.
     */
    private JTextArea outputArea;
    
    /**
     * Bottone per cancellare il contenuto del dispositivo di ingresso
     */
    private JButton clearButton; 
    
    /**
     * Definisco una inner-class che funge da ascoltatore privato dell'input device
     * per ricevere l'evento del bottone dell'input device; alla pressione del bottone 
     * verr� cancellata la finestra di input.
     */
    class ButtonHandler implements ActionListener{
        
        /**
         * Reazione di risposta all'evento generato dal pulsante
         * di attivazione del pulsante.
         * @param e rappresenta l'evento scatenante
         */
         public void actionPerformed(ActionEvent e) {
            
            outputArea.setText("");
            
        }//actionPerformed     
         
    }//ButtonHandler
    
    /** 
     * Costruttore di  OutDev 
     */
    public OutDev() {
        configura();
    }//OutDev
    
    /**
     * Questo metodo serve per configurare il dispositivo di output
     */
    protected void configura(){
        
        JScrollPane scroll;
        
        /*
         * creo il dispositivo di output.
         */
       
        setLayout( new BorderLayout() );
        add("North", outLabel = new JLabel("Output") );
        add("Center", scroll = new JScrollPane(outputArea = new JTextArea(10,30)));
        //inserisce il bordo dell'area di output
        outputArea.setBorder(new LineBorder(new Color(00,00,00)));
        add("South",clearButton = new JButton("Cancella Output"));
        //inserisce il bordo del dispositivo di output
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        
        /*
         * creazione e registratore dell'ascoltatore privato dell'evento per inButton
         */
        ButtonHandler bh = new ButtonHandler();
        clearButton.addActionListener(bh);
        
    }
    
     /**
     * Questo metodo serve per visualizzare una stringa sul dispositivo di output.
     * @param msg   rappresenta il messaggio da visualizzare.
     */
    public void print(String msg) {
        outputArea.append(msg);
    }
    
    /**
     * Questo metodo serve per visualizzare una stringa sul dispositivo di output,
     * andando a capo dopo averla visualizzata.
     * @param msg   rappresenta il messaggio da visualizzare.
     */
    public void println(String msg) {
        outputArea.append(msg+"\n");
    }
    
}
