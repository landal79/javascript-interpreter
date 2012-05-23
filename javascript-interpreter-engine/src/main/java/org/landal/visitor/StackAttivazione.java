package org.landal.visitor;

import org.landal.utility.ILifo;
import org.landal.utility.Lifo;

/**
 * Questa classe rappresenta una implementazione dello stack di attivazione
 * per la chiamata di funzioni. Fornisce tutte le funzionalit� per
 * gestire lo stack di attivazione.
 * @author  Landini Alex
 * @version 1.0
 */
 public class StackAttivazione {
	
        /**
         * Lo stack di attivazione gestisce una Lifo locale.
         */
        protected ILifo stack = null;
        
        /**
         * Riferimento il primo record in cima allo stack.
         * Serve per ricercare variabili nella catena degli ambiti.
         */
        protected RecordAttivazione top = null;

	/**
         * Costruttore dello StackAttivazione senza argomenti.
         */
        public StackAttivazione (){ 
          stack = new Lifo();
          top = null;
        }

	/**
         * Costruttore dello stack di attivazione, che riceve in ingresso
         * il primo record.
         * @param   r   primo record di attivazione da inserire nello stack.
         */
        public StackAttivazione (RecordAttivazione r){ 
            //metto il primo record in top
            top = r;
            stack = new Lifo();
        }

	/**
         * Preleva il primo record di attivazione in cima alla pila.
         * @return  RecordAttivazione   record di attivazione in cima allo stack
         * @throws  EmptyStackException   nel caso lo stack sia vuoto viene generata un'eccezione.         
         */
        public void pop() throws EmptyStackException{          
            //verifico che lo stack non sia vuoto 
            //se non � vuoto quello nuovo estratto viene memorizzato 
            //in top in quanto record in cima allo stack
            if (( top = (RecordAttivazione) stack.pop() ) == null)
                throw new EmptyStackException("Errore Stack vuoto");
                
	}//fine pop

	/**
         * Inserisce nello stack un record di attivazione.
         * @param   r   record di attivazione da inserire nello stack.
         */
        public void push (RecordAttivazione r){
            //metto in top quello appena inserito               
            top =r;
            //inserisco top nello stack
            stack.push(top);
	}//fine push

	/**
         * Restituisce un riferimento al top dello stack.
         * @return  RecordAttivazione primo record in cima allo stack di attivazione
         */
        public RecordAttivazione getTop() throws EmptyStackException{
            //verifico se top � vuoto
            if (top == null) throw new EmptyStackException ("Errore Stack vuoto");
            return top;
            
        }//getTop

	/**
         * Query per verificare se lo stack � vuoto.
         * @return  boolean   stato dello stack di attivazione.
         */
        public boolean isEmpty(){
            //verifico se top non � null
            if (top == null) return true;
                else return false;
        }

	/**
 	 * Rappresentazione esterna dello stack.
         * @return  String  rappresentazione esterna dello stack.
         */
        public String toString(){ 
            if (top!=null){
                if (stack.isEmpty() != false)return top.toString()+" "+stack.toString();
                    else return top.toString();
            }//lo stack � vuoto
            else return "Empty Stack";
        }

 }
