package org.landal.utility;

/**
 * Questa classe rappresenta una possibile implementazione del tipo lifo, 
 * ovvero di uno stack.
 * @author  Alex Landini
 */
public class Lifo extends List implements ILifo{
    
    /** 
     * Costruttore della lifo.
     */
    public Lifo() {
        super ();
    }        
    
    /**
     * Costruttore nel caso venga passato il primo oggetto da inserire.
     * @param   o   primo oggetto da inserire.
     */
    public Lifo (Object o){
        super(o);
    }
    
    /**
     * Inserisce un oggetto al top della lifo.
     * @param o oggetto da inserire.
     */
    public void push(Object o){
        //mi posiziono all'inizio della lista
        super.goFirst();
        //lo inserisco come primo elemento
        super.insert(o);
    }
    
    /**
     * Prende il primo oggetto in cima alla lifo.
     * @return  Object  primo oggetto in cima alla lifo.
     */
    public Object pop(){
        //mi posiziono all'inizio della lista
        super.goFirst();                
        //reperisco l'oggetto in cima alla lista
        Object o = super.get();
        //elimino l'oggetto in cima alla lista
        super.remove();
        //resituisco l'oggetto
        return o;
    }
        
            
}//Lifo
