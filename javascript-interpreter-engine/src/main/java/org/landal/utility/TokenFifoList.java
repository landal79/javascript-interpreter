package org.landal.utility;

import org.landal.token.IToken;

/**
 * Questa classe specializza FifoList per il particolare tipo di
 * dato token.
 * @author  Alex Landini
 */
public class TokenFifoList extends FifoList implements ITokenFifoList{
    
    /**
     * Costruttore di una fifolist senza argomenti.
     */
    public TokenFifoList() {
        
       super();
    }
    
   /**
    * Costruttore nel caso venga inserito il primo oggetto.
    * @param    o   primo oggetto da inserire.
    */
    public TokenFifoList (IToken o){
        
        super(o);
        
    }    
    
    //FUNZIONI PER GESTIRE LA LISTA
    
    /**
     * Inserisceil token nella posizione corrente.
     * @param   o   oggetto da inserire.
     */
    public void insertToken (IToken o){
        
        super.insert(o);        
    }
    
    /**
     * Rimuove il token nella posizione corrente.
     */
    public void remove (){
        
        super.remove();
        
    }
    
    /**
     * Reperisce il token nella posizione corretente.
     * @return  Token  token nella posizione corrente.
     */
    public IToken getToken(){
       
        return (IToken) super.get();
        
    }
       
    /**
     * Appende il token passato come parametro alla fine della lista.
     * @param   o   oggetto da inserire.
     */
    public void appendToken(IToken o){
        super.append(o);
    }
    
    /**
     * Crea un oggetto iterator per scorrere la lista.
     * @return ITokenIterator    riferimento all'Iterator della lista.
     */
    public ITokenIterator createTokenIterator(){
      
        return (ITokenIterator) super.creaIterator();
    }    
    
    /**
     * Questo metodo pu� essere specializzato dalle sottoclassi per creare un
     * iterator particolare.
     * @return  IIterator   oggetto iterator
     */
    protected IIterator creaIterator(){
        return new TokenIterator(this);
    }
    
    // FUNZIONI PER GESTIRE LE FIFO
    /**
     * Questo metodo restuisce il primo token in testa alla fifo 
     * eleminandolo dalla fifo.
     * @return  Token  elemento in testa alla lista
     */
    public IToken getFifoToken(){
        
        return (IToken) super.getVal(); 
    }

    /**
     * Questo metodo inserisce un token in coda alla fifo.
     * @return  o   oggetto da inserire.
     */
    public void insertFifoToken(IToken o){
        super.insertVal(o);
    }

    
}
