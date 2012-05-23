package org.landal.visitor;

import java.util.Hashtable;
import java.util.Map;

/**
 * Questa classe rappresenta il record di attivazione da inserire nello stack 
 * quando viene chiamata una funzione. Nel record di attivazione sono contenute
 * le variabili definite localmente alla funzione (quindi anche le funzioni per
 * javacript), e un riferimento al record di attivazione precedente.
 * Il refirimento al record di attivazione precedente serve per compiere la ricerca
 * a ritroso attraverso i vari record di attivazione delle variabili che non sono definite
 * localemente, da ricordare che javascript applica la regola della definizione 
 * lessicale delle variabili, cio� le variabili sono visibili solo nel proprio ambiente
 * di definizione. La definizione lessicale delle variabili � stata resa possile attravero
 * l'inserimento di un campo 'ambito' nello stack che indica se la funzione che � 
 * stata richiamata � stata definita localmente alla funzione chiamante oppure se viene
 * richiamata dall'ambiente globale. Con il riferimento si � creata una catena dinamica
 * di ambito locali.
 * @author  Landini Alex
 * @version 1.0
 */
 public class RecordAttivazione {

	/**
	 * Queste costanti servono a capire qual � il contesto di definizione della funzione che �
	 * stata invocata;
	 */
 	public final int  GLOBAL = 0, LOCAL = 1;

 	/**
	 * Questa tabella contiene l'ambiente locale; nell'ambiente locale sono contenute variabili, oggetti,
	 * vettori, funzioni, costruttori, si trovano tutti insieme in quanto in javascript un identificatore
	 * pu� essere associato a qualsiasi tipo di dato e pu� cambiare tipo in ogni momento;
	 */
	private Map localVar;

 	/**
 	* Questo campo contiene un riferimento al record di attivazione precedente nello stack,
 	* per la ricerca delle variabili definite negli ambiti locali precedenti
 	*/
 	private RecordAttivazione prec;

 	/**
 	* Questo campo contiene il nome dell'ambito di definizione; se ha valore GLOBAL allora la
 	* funzione � definita nel contesto globale, se ha valore LOCAL allora la funzione �
	* definita nel contesto locale;
 	*/
 	private int ambito;

 	/**
         * Costruttore di Record attivazione: crea la tabella per le variabili globali
         * imposta a null il riferimento al record precedente.
         */
        public RecordAttivazione(){
            localVar = new Hashtable();
            prec = null;
            ambito = GLOBAL;
        }//Record di attivazione

 	/**
         * Costruttore di Record di attivazione, imposta il riferimento al record di attivazione
         * precedente, inoltre imposta il valore dell'ambito.
         * @param   prec    riferimento al record di attivazione.
         * @param   ambito  costante che indica l'ambito di definizione della funzione.
         */
        public RecordAttivazione(RecordAttivazione prec, final int ambito){
            localVar = new Hashtable();
            this.prec = prec;
            this.ambito = ambito;
	}//Rercord di attivazione

        //===================================================
	//GESTIONE VARIABILI, FUNZIONI E COSTRUTTORI LOCALI
        //==================================================

	/**
	 * Questo metodo verifica se esiste una variabile locale nella catena degli ambiti locali.
         * @param   id  identificatore della variabile.
         * @return  boolean   valore che indica se la variabile nell'ambito locale.
	 */
	public boolean searchVar(String id){            

            if (localVar.containsKey(id)) return true;
               else if ((prec != null) && (ambito == LOCAL)) return prec.searchVar(id);
                    //se l'ambito � locale allora risalgo al record precedente
                    else return false;//la variabile non � definita nelle variabili locali
            
	}//fine searchVar

	/**
	 * Questo metodo aggiunge una variabile all'ambiente locale.
         * @param   id     identificatore della variabile.
         * @parm    val    valore della variabile.        
	 */
	public void addVar(String id, Object val){		
            localVar.put(id,val);		
	}//fine addVar

	/**
	 * Questo metodo cerca nella catena degli ambiti locali la variabile passata come parametro e
         * la restituisce se esiste.
         * @param   s   stringa che indica il nome della variabile. 
	*/
	public Object getVar(String s){

            if (localVar.containsKey(s)) return localVar.get(s);
                else if ((prec != null) && (ambito == LOCAL)) return prec.getVar(s);
                    //se l'ambito � locale allora risalgo al record precedente
                    else return null;//la funzione non � definita nelle variabili locali

	}//fine getVar

	//=================================================
        // METODI PER LA RICERCA DEI VALORI DEI PARAMETRI
        //=================================================

	/**
         * Questo metodo cerca un parametro nella tabella delle variabili locali.
         * @param   id  identificatore del parametro.
         * @return  boolean     valore che indica se il parametro � stato trovato o no.         
         */
        public boolean searchParam(String id){

            if (prec != null) return prec.searchVar(id);
                else return false;
            
	}//fine searchVar


	/**
         * Questo metodo serve per reperire il valore di un parametro dalla tabella 
         * delle variabili locali. Restuisce null se la variabile non esiste.         
         * @param   s   stringa che indica il nome della variabile.
         * @return  Object  valore della variabile.
         */
        public Object getParam(String s){

            if (prec != null)  return prec.getVar(s);
                else return null;

	}//fine getVar


 }//RecordAttivazione