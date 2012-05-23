package org.landal.parser;

import org.landal.lexer.LexerException;
import org.landal.token.IToken;

/**
 * Questa interfaccia fornisce i metodi necessari per compiere l'analisi LL(k),
 * applicando l'analisi ricorsiva discendente. La classe che implementa questa
 * interfaccia deve gestire una coda di token che conterr� la sequenza di token
 * di cui � composta la frase in ingresso al lexer.
 * 
 * @author Alex
 */
public interface LLAnalisys {

	/**
	 * Questo metodo serve per inizializzare l'analisi LL, riempe la coda se non
	 * � ancora stata riempita e inizializza un in Iterator ogni volta che viene
	 * invocata per effettuare una nuova scansione LL.
	 * 
	 * @param first
	 *            � il primo token che deve essere inserito nella coda
	 * @throws LexerException
	 *             dato che riempe la coda rimpalla tutti gli errori di lettura
	 *             dal lexer.
	 */
	public void initAnalisysLL(IToken first)
			throws org.landal.lexer.LexerException;

	/**
	 * Questo metodo permette di leggere dalla coda per verificare quale
	 * produzione applicare.
	 * 
	 * @return IToken il token letto dalla coda
	 */
	public IToken readCoda();

}
