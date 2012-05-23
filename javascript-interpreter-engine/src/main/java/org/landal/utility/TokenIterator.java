package org.landal.utility;

import org.landal.token.IToken;

/**
 * Questa classe rappresenta un iteratore per liste di token
 * @author  Alex Landini
 */
public class TokenIterator extends ListIterator implements ITokenIterator{
    
    
    /**
     * Costruttore di TokenIterator che riceve la lista come parametro.
     * @param   list    lista di cui costruire l'iterator 
     */
    public TokenIterator(ITokenList list) {
        
        super(list);
    }
    
    /**
     * Reperisce il prossimo token.
     * @return  Token   prossimo token della lista.
     */
    public IToken nextToken(){
        
        return (IToken) super.next();
       
    }
    
}
