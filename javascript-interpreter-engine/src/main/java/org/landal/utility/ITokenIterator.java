package org.landal.utility;

import org.landal.token.IToken;

/**
 * Questa interfaccia estende Iterator e la specializza per liste di token.
 * @author  Alex Landini
 * @version 1.0
 */
public interface ITokenIterator extends IIterator{
    
    /**
     * Reperisce il prossimo token.
     * @return  Token   prossimo token della lista.
     */
    public IToken nextToken();
    
}
