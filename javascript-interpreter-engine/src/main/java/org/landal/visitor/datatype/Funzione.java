package org.landal.visitor.datatype;

import org.landal.apt.ListaIstr;
import org.landal.utility.IIterator;

/**
 * Questa classe rappresenta l'astrazione di una funzione Javascript. 
 * @author  Landini Alex
 * @version 1.0
 */

public class Funzione extends ComplexType{

	/**
	* Questo campo contiene l'insieme dei parametri della funzione, che in javascript
	*s ono solo degli identificatori; questo mi servir� per confrontare con quanti parametri
	* verr� chiamata effettivamente la funzione
	*/
	private ArgList parametri;

	/**
	* Questo campo contiene il riferimento alla parte di apt che rappresenta il corpo 
        * della funzione. il corpo � un pezzo di albero di derivazione, quello corrispondente 
        * alla funzione.
	*/
	private ListaIstr corpo;

	/**
	* Questo campo � un indice che mi serve, quando la funzione viene chiamata, per associare il
	* parametro di indice i, al rispettivo valore di chiamata
	*/
	private int indice;

	/**
         * Costruttore senza parametri di Funzione.
         */
        public Funzione(){
		parametri = new ArgList();
		corpo = null;
		indice = 0;
	}
        
        /**
         * Costruttore di Funzione nel caso gli venga passata la lista di argomenti
         * e il corpo.
         * @param   par     lista dei parametri
         * @param   corpo   corpo della funzione
         */
	public Funzione(ArgList par, ListaIstr corpo){

		parametri = par;

		this.corpo = corpo;

		indice = 0;

	}

	/**
	* Questo metodo restituisce il numero di parametri della funzione.
        * @return   int numero dei parametri della funzione.
	*/
	public int getNumParam(){
            
            IIterator it = parametri.createIterator();
            int i = 0;
            while (it.hasNext()) {
                it.next();
                i++;
            }
             return i;
        }//getNumParam


	/**
	* Questo metodo serve a verificare se c'� un argomento di nome id nella lista di parametri;
	* Questo metodo � utilit� per verificare se ci sono argomenti doppi. 
        * La ricerca si ferma alla prima occorrenza.
        * @param    id  nome dell'argomento da verificare.
        * @return   boolean   indica se l'argomento � presente o no.
	*/
	protected boolean searchParam(String id){
            
            IIterator it = parametri.createIterator();
           
            while (it.hasNext()) {
                if (((String) it.next()).compareTo(id) == 0) return true;              
            }
           //non ho trovato
            return false;	

	}//fine searchParam

	/**
	* Questo metodo permette di aggiungere argomenti alla lista degli argomenti.
        * @param    id   identificatore del parametro da aggiungere.         
	*/
	public void addParam(String id) throws FunzioneException{
            //prima di aggiungere il parametro verifico che non ce ne sia gi� uno con lo stesso nome.
            if(searchParam(id)) throw new FunzioneException("Errore la funzione ha due parametri uguali");
                    else parametri.append(id);

	}//fine addParametro

	/**
	* Questo metodo restituisce i nomi dei parametri della funzione; l'ordine con cui i nomi
	* dei parametri della funzione vengono restituiti viene scandito dall'indice interno alla
	* classe con 'indice';
        * @return   String  l'identificatore del parametro appena letto
	*/
	public String getParam(){
            int i=0;//indice di confronto con quello globale
            IIterator it = parametri.createIterator();
            while(it.hasNext() && (i<indice)){
                it.next();
                i++;
            }            
            if (it.hasNext()) {
                indice++;//aggiorno l'indice successivo cos� la prossima volta passo il prossimo argomento
                return (String) parametri.get();
                }
                else {
                    indice = 0;//reinizializzo l'indice globale
                    return null;
                }	

	}//fine getParametro

	 /**
	* Questo metodo restituisce il nome  dell'i-esimo parametro di chiamata della funzione.
        * @param    i   indice del parametro che si vuole reperire.
        * @return   String  il nome dello i-esimo parametro.
	*/
	public String getParam(int i){ 
            
            IIterator it = parametri.createIterator();
            int n = 0;//valore che si indica la posizione all'interno della lista
            String par = null;
            //scorro la lista fino alla i-esima posizione           
            while (it.hasNext() && (n<i)){
                par = (String)  it.next();               
                n++;
            }
            if (it.hasNext()) return par;
                else return null;//il parametro non esiste
        }//getParam

	/**
	* Questo metodo serve quando una volta che � terminata la chiamata della funzione,
	* indice viene riportato a 0, cos� alla chiamata successiva della funzione, si ricomincia
	* a passare dal primo parametro
	*/
	public void resetIndice(){ indice = 0;}

	/**
	* Questo metodo restituisce il corpo della funzione che � un pezzo di apt 
        * costruito dal parser durante l'analisi sintattica.
        * @return   ListaIstr   rappresenta il corpo della funzione
	*/
	public ListaIstr getCorpo(){ return corpo;}

	/**
	* Questo metodo permette di assegnare alla funzione un corpo, cio� il
	* codice che deve essere eseguito nel caso la funzione venga richiamata.
        * @param    corpo   corpo da assegnare alla funzione.
	*/
	public void setCorpo(ListaIstr corpo){ this.corpo = corpo;}

	/**
         * Fornisce una rappresentazione esterna della funzione.
         * @return  String  rappresentazione esterna della funzione.
         */
        public String toString(){

            if (parametri.isEmpty()) return "function (  ) {\n"+corpo.toString()+"}\n";
                else return "function ( "+parametri.toString()+" ) {\n"+corpo.toString()+"}\n";

	}//fine toString

}