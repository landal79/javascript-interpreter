package org.landal.utility;

/**
 * Questa classe rappresenta il generico nodo di un qualsiasi tipo di struttura
 * dati dinamica. Ogni nodo ha un campo dato e un riferimento al nodo successivo,
 * gestito diversamente a senconda del tipo di struttura dati.
 * @author  Alex Landini
 */
public class Nodo implements INodo{
    
    /**
    * Campo che contiene il valore del nodo della coda.
    */
    protected Object val;

    /**
    * Campo che contiene il riferimento al nodo successivo della coda.
    */
    protected INodo next;

    /**
     * Costruttore del nodo senza argomenti.
     */
    public Nodo (){
            val = null;
            next = null;
            }

    /**
     * Costruttore del nodo nel caso gli vengano passati un valore e il 
     * riferimento al nodo successivo.
     * @param   val valore che il nodo deve contenere
     * @param   n   riferimento al nodo successivo.
     */
    public Nodo (Object val, INodo n){
            this.val=val;
            this.next=n;
    }

    /**
    * Costruttore per un nodo che non ha nodi successivi.
    * @param val    valore che il nodo deve contenere.
    */
    public Nodo (Object val){
            this.val=val;
            this.next=null;
    }

    /**
     * Restuisce il valore contenuto nel nodo.
     * @return  Object  oggetto presente nel nodo.
     */
    public Object getVal(){return val;}

     /**
     * Imposta il valore del nodo.
     * @param   o   oggetto che diventar� il valore contenuto nel nodo.
     */
    public void setVal(Object o){
            this.val=o;
    }

    /**
     * Restituisce un riferimento al nodo successivo.
     * @return  INodo   riferimento al prossimo nodo.
     */
    public INodo getNext(){return next;}

    /**
     * Imposta il riferimento al prossimo nodo.
     * @param   n   riferimeto al prossimo nodo.
     */
    public void setNext(INodo n){
            next=n;
    }

    public String toString(){

            if (next != null) return val.toString()+"  |  "+next.toString();
                    else return val.toString()+"  ";

    }
    
    
//fine toString

    
}//Nodo
