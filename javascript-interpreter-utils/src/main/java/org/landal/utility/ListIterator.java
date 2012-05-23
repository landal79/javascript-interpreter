package org.landal.utility;

/**
 * Questa classe rappresenta un Iterator per la lista
 * @author  Alex Landini
 */
public class ListIterator implements IIterator{
    
    /**
     * Riferimento alla lista.
     */
    protected IList list;
    
    /**
     * Costruttore di ListIterator che riceve la lista come parametro.
     * @param   list    lista di cui costruire l'iterator 
     */
    public ListIterator(IList list) {
        this.list = list;
        //quando creo l'iterator mi pongo all'inizio della lista
        list.goFirst();
    }
    
     /**
     * Reperisce il prossimo l'elemento.
     * @return  Object prossimo elemento della lista.
     */
    public Object next(){
        //recupero l'oggetto dalla posizione corrente
        Object o = list.get();
        //avanzo nella lista
        list.goNext();
        //ritorno l'oggetto corrente
        return o;
        
    }//next
    
    /**
     * Query per verificare se la lista ha ancora degli elementi.
     * @return boolena  valore che indica se la lista ha ancora degli elementi.
     */
    public boolean hasNext(){
        //verifico se la lista non � vuota
        if (list.get() != null) return true;
            else return false;               
    }//hasNext
    
    /**
     * Rimuove l'elemento nellla posizione corrente.
     */
     public void remove(){
         
         list.remove();
         
     }
    
}
