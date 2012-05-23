package org.landal.utility;

/**
 * Questa classe rappresenta l'implementazione del tipo lista non ordinata.
 * @author  Alex Landini
 */
public class List implements IList{
    
    /**
     * primo nodo della lista.
     */
    protected INodo first;

    /**
     * nodo attualmente puntato.
     */
    protected INodo act;
    
    /**
     * Costruttore di una lista senza argomenti.
     */
    public List() {
        
        first = act = null;
    }//List
    
   /**
    * Costruttore nel caso venga inserito il primo oggetto.
    * @param    o   primo oggetto da inserire.
    */
    public List (Object o){
        
        first = act = createNodo(o);
        
    }//List
    
    /**
     * Inserisce l'elemento nella posizione corrente.
     * Il nuovo elemento diventa l'attuale.
     * @param   o   oggetto da inserire.
     */
    public void insert (Object o){
        //verifico se la lista � vuota
        if (first == null){
            //lista vuota, quindi creo il primo nodo
            first = act = createNodo(o);                           
        }else if (act == first){
             //caso in cui sono all'inizio della lista
            first = createNodo(o,act);
            act = first;//reimposto la posizione attuale
            }
            else{//la lista non � vuota
            //creo un nuovo nodo e imposto come successivo quello attuale
            INodo n = createNodo(o, act);
            //reperisco il nodo precedente
            act = getPrec(act);
            //imposto il successivo con il nuovo nodo;
            act.setNext(n);          
            act = n;//imposto il nuovo nodo come attuale
        }
            
        
    }//insert
    
    /**
     * Rimuove l'oggetto nella posizione corrente.
     * L'elemento della posizione corrente diventa il precedente.
     */
    public void remove (){
        //verifico se la lista non � vuota
        if (first != null){  
            if (act != null){//verifico se sono a fine lista
            //verifico se sono all'inizio della lista
                if (act==first){                                        
                    //cerco il nodo precedente                    
                    first = first.getNext();//imposto come successivo dell'attuale il prossimo            
                    act = first;
                }
                else {
                    //memorizzo temporaneamente act in una variabile
                     INodo n = act;
                    //cerco il nodo precedente
                    act = getPrec(act);
                    act.setNext(n.getNext());
                    //imposto come successivo dell'attuale il successivo del vecchio attuale                               
                    }
            }//sono a fine lista non ho nulla da rimuovere
        }
        //nel caso la lista sia vuota non faccio niente
            
        
    }//remove
    
    /**
     * Reperisce l'elemento nella posizione corretente. 
     * Se sono alla fine della lista restiuisce null.
     * @return  Object elemento nella posizione corrente.
     */
    public Object get(){
        //verifico se sono alla fine della lista
        if (act == null) return null;
            else return act.getVal();
        
    }//get
    
    /**
     * Appende l'oggetto passato come parametro alla fine della lista.
     * La posizione attuale non cambia.
     * @param   o   oggetto da inserire.
     */
    public void append(Object o){
       
        //verifico se la lista � vuota
        if (first != null){                 
            //caso in cui la lista non � vuota          
            INodo n = first;        
            //vado alla fine della lista
            while (n.getNext() != null) n = n.getNext();           
            n.setNext(createNodo(o));//aggiungo il nodo in coda alla lista              
        }//lista vuota
        else first = act = createNodo(o);                
        
    }//append
    
    /**
     * Questo metodo riposiziona il puntatore che scorre la lista al primo nodo.
     */
    public void goFirst(){
        //la posizione attuale diventa l'inizio della lista
        act = first;
        
    }//goFirst
    
    /**
     * Questo metodo serve per scorrere in avanti la lista.
     */
    public void goNext(){
        //verifico se la lista � vuota
        if (first != null){
            //verifico se sono a fine lista
            if (act != null) act = act.getNext();
        }       
        
    }//goNext
    
    /**
     * Questo metodo verifica se la lista � vuota.
     * @return  boolean valore che indica se lo stato della lista.
     */
    public boolean isEmpty (){
        
        if (first == null) return true;
        else return false;
        
    }//isEmpty
    
    /**
     * Crea un oggetto iterator per scorrere la lista.
     * @return IIterator    riferimento all'Iterator della lista.
     */
    public IIterator createIterator(){
        
        return creaIterator(); 
        
    }//createIterator
    
    /**
     * Questo metodo serve per creare un nuovo nodo cos� se si vorr� cambiare il 
     * tipo di nodo create dalle sottoclassi baster� specializzarlo.
     * @param   o   valore che deve assumere il nodo.
     * @param   succ    gli viene passato il riferimento al nodo successivo.
     * @return  INodo   ritorna il nodo creato.
     */
    protected INodo createNodo(Object o, INodo succ){
        
        return new Nodo(o,succ);
        
    }//createNodo
    
    /**
     * Questo metodo serve per creare un nuovo nodo cos� se si vorr� cambiare il 
     * tipo di nodo create dalle sottoclassi baster� specializzarlo.
     * @param   o   valore che deve assumere il nodo.     
     * @return  INodo   ritorna il nodo creato.
     */
    protected INodo createNodo(Object o){
        
        return new Nodo(o);
        
    }//createNodo
    
    /**
     * Con questo metodo recupero il nodo precedente a quello passato come parametro.
     * @param   nodo  nodo di cui trovare il precedente
     * @return  INodo   nodo precedente a quello passato come parametro
     */
    protected INodo getPrec(INodo nodo){
        
        INodo n = first;//gli assegno il primo nodo della lista        
        // con un ciclo vado avanti finch� non trovo il successivo        
        while (n.getNext() != nodo) n = n.getNext();
        //restituisco il riferimento al nodo precedente
        return n;
        
    }//getPrec
    
    /**
     * Questo metodo pu� essere specializzato dalle sottoclassi per creare un
     * iterator particolare.
     * @return  IIterator   oggetto iterator
     */
    protected IIterator creaIterator(){
        return new ListIterator(this);
    }
    
    /**
     * Fornisce una rappresentazione esterna dell'oggetto.
     * @return  String  Rappresetazione esterna dell'oggetto.
     */
    public String toString(){
        
        if (first != null) return first.toString();
            else return "Empty";
        
    }//toString
    
}
