package org.landal.utility;

import org.landal.token.IToken;

/**
 * Questa interfaccia specializza la lista pre contenere elementi token.
 * @author  Alex
 */
public interface ITokenList extends IList{
    
    /**
     * Inserisceil token nella posizione corrente.
     * @param   o   oggetto da inserire.
     */
    public void insertToken (IToken o);
    
    /**
     * Reperisce il token nella posizione corretente.
     * @return  Token  token nella posizione corrente.
     */
    public IToken getToken();
    
    /**
     * Appende il token passato come parametro alla fine della lista.
     * @param   o   oggetto da inserire.
     */
    public void appendToken(IToken o);
    
    /**
     * Crea un oggetto iterator per scorrere la lista.
     * @return ITokenIterator    riferimento all'Iterator della lista.
     */
    public ITokenIterator createTokenIterator();
    
}
