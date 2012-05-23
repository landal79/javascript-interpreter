package org.landal.simulator;

import org.landal.apt.IApt;

/**
 * Questa classe implementa l'operazione di analisi sintattica di una frase
 * di javascript. Se l'analisi � andata a buon fine il comando restituisce
 * la frase ricevuta in ingresso.
 * @author  Alex Landini
 */
public class SintaxCommand extends Command{
    
    /** Creates a new instance of AnalisiSintattica */
    public SintaxCommand() {
    }
        
    /**
     * questo metodo esegue l'analisi sintattica della frase di ingresso.
     */
    protected void action(){
        IApt apt = null;        
        //invoco il relativo metodo di analisi del parser
        par.parse();
        res = "Analisi sintattica completata con successo:\n";
        try{
           while ((apt = par.getApt()) != null){
               if (res != null) res = res + apt.toString();
                    else res = apt.toString();
               //analizzo la prossima frase
               par.parse();
           }//while
           //alla fine del ciclo in res ci sar� la frase ricevuta in ingresso
           if ( res == null ) res = "";          
        }catch (Exception e){
            //si � verificato un errore
            res = "messaggio errore: "+ e.getMessage();
        }//fine catch
        
    }
}
