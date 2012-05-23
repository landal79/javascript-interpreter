package org.landal.simulator;

import org.landal.apt.IApt;
import org.landal.visitor.InterpreterVisitor;

/**
 * Questa classe rappresenta l'implementazione dell'azione di interpretazione
 * di una frase di javascript. Il risultato fornito dall'interpretazione della
 * frase consiste nell'insieme delle variabili globali definite nel programma
 * con i loro rispettivi valori, nel caso sia andata a buon fine la valutazione
 * altrimenti in una opportuna indicazione di errore.
 * @author  Alex Landini
 */
public class InterpretCommand extends Command{
    
    /**
     * questo campo contiene un riferimento al visitor che eseguir� l'interpretazione.
     */
    protected InterpreterVisitor jsInt;
    
    /**
     * Costruttore del comando di interpretazione, che richiama il
     * costruttore della superclasse. 
     */
    public InterpretCommand() {      
        super();
        //creo il visitor interpreter
        jsInt = new InterpreterVisitor();
    }
 
   /**
     * Metodo che specializza il comportamento della classe padre.
     */
    protected void action(){
        //inizializzo il visitor
        jsInt.init();
        //creo un apt
        IApt apt = null;
        //inizio l'analisi sintattica
        par.parse();
        try{                  
            while ((apt = par.getApt()) != null){
                apt.accept(jsInt);
                par.parse();
            }// finita l'interpretazione
            //ricevo il risultato dell'interpretazion
            res = "TABELLA DELLE VARIABILI GLOBALI\n"+jsInt.getResAsString();
            if ( res == null ) res = "";
        }catch(Exception e){
            res = "messaggio errore: "+ e.getMessage();
        }
    }//action
   
}
