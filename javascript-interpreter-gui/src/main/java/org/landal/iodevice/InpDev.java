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
 * Questa classe rappresenta un dispositivo di input e fornisce
 * tutte le operazioni necessarie per agire su di esso.
 * @author  Alex Landini
 * @version 1.0
 */
public class InpDev extends InputDev{
	
	private static final long serialVersionUID = 1L;
    
    /**
     * Etichetta che indica il nome dell'area
     */
    private JLabel inpLabel;
       
    /**
     * Area di output.
     */
    private JTextArea inputArea;
    
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
            
            inputArea.setText("");
            
        }//actionPerformed        
        
    }//ButtonHandler
    
    /** 
     * Costruttore di InpDev
     */
    public InpDev() {
        
       configura();
        
    }//InpDev
    
   /**
    * Questo metodo serve a configurare il dispositivo di ingresso.
    */
    protected void configura(){
                    
        /*
         * creo il dispositivo di ingresso
         */
        
        setLayout( new BorderLayout() );
        add("North", inpLabel = new JLabel("Dispositivo di input"));
        add("Center", new JScrollPane(inputArea = new JTextArea(10,20)));
        //definisco il colore di contorno dell'area di input
        inputArea.setBorder(new LineBorder(new Color(00,00,00)));
        add("South", clearButton = new JButton("Cancella Input"));
        //definisco il colore di contorno del pannello di input
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        
        /*
         * creazione e registratore dell'ascoltatore privato dell'evento per inButton
         */
        ButtonHandler bh = new ButtonHandler();
        clearButton.addActionListener(bh);
        
    }//configura
    
    /**
     * Questo metodo legge il contenuto del dispositivo di input restituendolo 
     * sotto forma di stringa.
     * @return  rappresenta la stringa letta sul dispositivo di input.
     */
    public String read(){
        
        return inputArea.getText();
        
    }//read
    
   /**
    * Questo metodo permette di inserire un messaggio indicativo per richiedere dati.
    * @param    msg     rappresenta il messaggio da inserire
    */
    public void emit (String msg){
        
        inpLabel.setText (msg);
        
    }//emit
    
    
}//InpDev
