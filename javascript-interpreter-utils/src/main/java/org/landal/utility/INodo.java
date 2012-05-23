package org.landal.utility;

/**
 * Questa interccia rappresenta il generico nodo di un qualsiasi tipo di lista.
 * @author  Alex Landini
 */
public interface INodo {
    
    /**
     * Restuisce il valore contenuto nel nodo.
     * @return  Object  oggetto presente nel nodo.
     */
    public Object getVal();
    
    /**
     * Imposta il valore del nodo.
     * @param   o   oggetto che diventar� il valore contenuto nel nodo.
     */
    public void setVal(Object o);
    
    /**
     * Restituisce un riferimento al nodo successivo.
     * @return  INodo   riferimento al prossimo nodo.
     */
    public INodo getNext();
    
    /**
     * Imposta il riferimento al prossimo nodo.
     * @param   n   riferimeto al prossimo nodo.
     */
    public void setNext(INodo n);
    
}
