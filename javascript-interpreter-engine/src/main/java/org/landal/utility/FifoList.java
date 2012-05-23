/*
 * FifoList.java
 *
 * Created on 11 dicembre 2004, 21.09
 */

package org.landal.utility;

/**
 * Questa classe implementa l'ADT Lista che pu� essere usata anche come una Fifo,
 * in quanto eredita da entrambi i tipi di dati;
 * @author  Alex Landini
 */
public class FifoList extends List implements IFifoList{
    
    /**
     * Costruttore di una fifolist senza argomenti.
     */
    public FifoList() {
        
       super();
    }
    
   /**
    * Costruttore nel caso venga inserito il primo oggetto.
    * @param    o   primo oggetto da inserire.
    */
    public FifoList (Object o){
        
        super(o);
        
    }    
    
    /**
     * Questo metodo restuisce il primo elemento in testa alla fifo 
     * eleminandolo dalla fifo. Se la Fifo � vuota ritorna Null.
     * @return  Object  elemento in testa alla lista
     */
    public Object getVal(){
        
        Object o = null;
        //reperisco il dato se esiste
        if (first != null){
            o = first.getVal();                        
            //al primo nodo metto il successivo del primo
            first = first.getNext();       
            //ritorno il dato letto
            return o;
        }//la fifo � vuota
        else return null;
        
    }//getVal

    /**
     * Questo metodo inserisce un oggetto in coda alla fifo.
     * @return  o   oggetto da inserire.
     */
    public void insertVal(Object o){
        //verifico se la lista non � vuota
       if (first != null) super.append(o);
           else first = act = new Nodo(o); 
        
    }//insertVal
    
}
