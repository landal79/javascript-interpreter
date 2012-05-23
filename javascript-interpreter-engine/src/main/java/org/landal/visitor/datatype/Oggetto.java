package org.landal.visitor.datatype;

import org.landal.utility.IIterator;

/**
 * Questa classe rappresenta il tipo oggetto javascript, come sappiammo un oggetto
 * � costituito da un insieme di propriet� che possono variare dinamicamente, ossia
 * in qualsiasi momento � possibile aggiungerne.
 * @author  Landini Alex
 * @version 1.0
 */
public class Oggetto extends ComplexType{

	/**
	* Questo campo contiene un vettore dinamico che contiene una la lista delle
        * delle propriet� dell'oggetto. Le propriet� sono oggetto di tipo Prop. 
	*/
	private IPropList listaProp;

	/**
	* Questa stringa memorizza l'ultima propriet� letta, mi serve come supporto
        * per cicli for/in in cui si scorrono i nomi delle propriet� una a una. 
        */
	private String last = null;

	/**
         * Costruttore dell'oggetto, crea il vettore che dovr� contenere 
         * le propriet� dell'oggetto e inizializza l'indice a 0.
         */
        public Oggetto(){
            listaProp = new PropList();
	}//Oggetto


	/**
         * Aggiunge una propriet� senza valore all'oggetto.
         * @param   id  identificatore della propriet�
         */
        
        public void addProp(String id){

		listaProp.addProp(new Prop(id));

	}//fine addProp

	/**
         * Aggiunge una propriet� con valore all'oggetto.
         * @param   id  identificatore della propriet�.
         * @param   value   valore della propriet�
         */
        public void addProp(String id, Object value){

		listaProp.addProp(new Prop(id,value));

	}//fine addProp

	/**
         * Reperisce una propriet� dall'oggetto di cui si conosce il nome
         * @param   name    nome della propriet�.
         * @return  IProp  ritorna  il valore della propriet�
         */
        public IProp getProp(String name){

            IProp p;//variabile locale che rappresenta la propriet�
                       
            IIterator it = listaProp.createIterator();
            
            while (it.hasNext()){
                p = (IProp) it.next();
               if (p.getNome().compareTo(name) == 0) return p;
            }//while
 
            return null;// la propriet� non esiste non esiste

	}//fine getProp

	/**
	* Questo metodo mi restituisce ad uno ad uno tutti i nome delle propriet� dell'oggetto.
        * Serve come supporto per eseguire cicli for/in.
        * @return   String  nome della propriet� attualmente letta.
	*/
	public String getNomeProp(){
                 System.out.println("getNomeProp");
                IProp p;
                //uso un iterator per scorrere la lista
               IIterator it = listaProp.createIterator();
                //con un ciclo mi posizione all'ultima propriet� letta
               if (last != null){//se last � null � il primo ciclo quindi non vado alla fine 
                   while(it.hasNext()){
                       p = (IProp) it.next();
                       if (p.getNome().compareTo(last) == 0) break;//esco dal ciclo                   
                     }
                 }//prima iterazione del ciclo for/in                 
                    System.out.println("sono andato all'ultima propriet� letta");
                   //leggo la prossima propriet�
               if (it.hasNext()){
                    p = (IProp) it.next();
                    last = p.getNome();//memorizzo l'ultima propriet� letta
                    return p.getNome();
                }//ho gi� letto tutte le propriet�               
               else {
                   last = null;//finito il ciclo for/in resetto last
                   return null;// la propriet� non esiste non esiste
                   }

	}//fine getProp

	/**
	* Questo metodo modifica la propriet� di un oggetto, 
        * se tale propriet� non esiste la crea aggiungendola in fondo alla lista �
        * delle propriet�.
        * @param    id   identificatore del nome della propriet�
        * @param    val   valore da assegnare come nuovo valore.
	*/
	public void modifyProp (String id, Object val){

           IProp p;

           IIterator it = listaProp.createIterator();

            while (it.hasNext()){
                p = (IProp) it.next();                
                if (p.getNome().compareTo(id) == 0) {
                    p.setVal(val);                   
                    return;
                }
            }//while
            //la propriet� non esiste allora la aggiungo
            p = new Prop(id,val);
            listaProp.addProp(p);

           
	}//fine modifyProp

	/**
         * Questo metodo mi dice se una propriet� di cui conosco il nome esiste.
         * @param   name    nome della propriet� da cercare.
         * @return  boolean     risultato dell'interrogazione.
         */
        public boolean verifyProp (String name){

            IProp p;//variabile locale che rappresenta la propriet�
                       
            IIterator it = listaProp.createIterator();
            
            while (it.hasNext()){
                p = (IProp) it.next();
               if (p.getNome().compareTo(name) == 0) return true;
            }//while
 
            return false ;// la propriet� non esiste non esiste

	}//fine verifyProp

	/**
         * Fornisce una rappresentazione esterna dell'oggetto.
         * @return  String  rappresentazione esterna dell'oggetto
         */
        public String toString(){ return listaProp.toString();}


}
