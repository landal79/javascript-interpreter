package org.landal.utility;

/**
 * Questa interfaccia definisce il tipo generico lista di elementi, 
 * non ordinata. L'interfaccia fornisce le funzionalit� necessarie 
 * per operare sulle liste non ordinate.
 * @author  Alex Landini
 */
public interface IList {
    
    /**
     * Inserisce l'elemento nella posizione corrente.
     * @param   o   oggetto da inserire.
     */
    public void insert (Object o);
    
    /**
     * Rimuove l'oggetto nella posizione corrente.
     */
    public void remove ();
    
    /**
     * Reperisce l'elemento nella posizione corretente.
     * @return  Object elemento nella posizione corrente.
     */
    public Object get();
    
    /**
     * Appende l'oggetto passato come parametro alla fine della lista.
     * @param   o   oggetto da inserire.
     */
    public void append(Object o);
    
    /**
     * Questo metodo riposiziona il puntatore che scorre la lista al primo nodo.
     */
    public void goFirst();
    
    /**
     * Questo metodo serve per scorrere in avanti la lista.
     */
    public void goNext();
    
    /**
     * Questo metodo verifica se la lista � vuota.
     * @return  boolean valore che indica se lo stato della lista.
     */
    public boolean isEmpty ();
    
    /**
     * Crea un oggetto iterator per scorrere la lista.
     * @return IIterator    riferimento all'Iterator della lista.
     */
    public IIterator createIterator();
    
}
