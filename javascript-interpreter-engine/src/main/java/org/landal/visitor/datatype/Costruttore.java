package org.landal.visitor.datatype;

import org.landal.apt.CostrIstr;
import org.landal.apt.Istr;
import org.landal.utility.IIterator;

/**
 * Questa classe rappresenta il tipo di dato costruttore di javascript.
 * @author  Landini Alex
 * @version 1.0
 */ 
public class Costruttore extends ComplexType{

 	/**
	* Questo campo contiene l'insieme dei parametri del costruttore, che in javascript
	* sono solo degli identificatori, cio� delle stringhe; questo mi servir� per confrontare con quanti parametri
	* verr� chiamata effettivamente il costruttore
	*/
	private ArgList parametri;

	/**
	* Questo campo contiene il riferimento alla parte di apt che rappresenta il corpo
        * del costruttore.
	*/
	private CostrIstr corpo;

	/**
	* Questo campo contiene un indice, usato,  quando viene invocato il costruttore, 
	* per restituire uno ad uno i nomi dei parametri.
	*/
	private int indice;

	/**
         * Costruttore senza argomenti.
         */
        public Costruttore (){
            parametri = new ArgList();
            corpo = null;
            indice =0;
	}

        /**
         * Costruttore di Costruttore nel caso gli venga passata la lista di argomenti
         * e il corpo.
         * @param   par     lista dei parametri
         * @param   corpo   corpo del costruttore
         */
	public Costruttore (ArgList v, CostrIstr i){
            parametri = v;
            corpo = i;
            indice = 0;
	}

	/**
	* Questo metodo restituisce il numero di parametri del costruttore.
        * @return   int numero dei parametri della costruttore.
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
	public void addParam(String id) throws CostruttoreException{
            //prima di aggiungere il parametro verifico che non ce ne sia gi� uno con lo stesso nome.
            if(searchParam(id)) throw new CostruttoreException("Errore la funzione ha due parametri uguali");
                    else parametri.append(id);

	}//fine addParam

	/**
	* Questo metodo restituisce i nomi dei parametri del costruttore; l'ordine con cui i nomi
	* dei parametri del costruttore vengono restituiti viene scandito dall'indice interno alla
	* classe 'indice';
        * @return   String  l'identificatore del parametro appena letto
	*/
	public String getParam(){
            int i =0;//indice di confronto con quello globale
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
	* Questo metodo restituisce il nome  dell'i-esimo parametro di chiamata del  ostruttore.
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
	*questo metodo serve quando una volta che � terminata la chiamata del costruttore
	*indice viene riportato a 0, cos� alla chiamata successiva del costruttore, si ricomincia
	*a passare dal primo parametro
	*/
	public void resetIndice(){ indice = 0;}

	/**
	* Questo metodo restituisce il corpo del costruttore che � un pezzo di apt 
        * costruito dal parser durante l'analisi sintattica.
        * @return   Istr   rappresenta il corpo del costruttore
	*/
	public Istr getCorpo(){ return corpo;}

	/**
	* Questo metodo permette di assegnare al costruttore il corpo, cio� il
	* codice che deve essere eseguito nel caso il costruttore venga richiamato.
        * @param    corpo   corpo del costruttore.
	*/
	public void setCorpo(CostrIstr corpo){ this.corpo = corpo;}

	/**
         * Fornisce una rappresentazione esterna del Costruttore.
         * @return  String  rappresentazione esterna del costruttore.
         */
        public String toString(){

            if (parametri.isEmpty()) return "function (  ) {\n"+corpo.toString()+"}\n";
                    else return "function ( "+parametri.toString()+" ) {\n"+corpo.toString()+"}\n";

	}//fine toString

 }