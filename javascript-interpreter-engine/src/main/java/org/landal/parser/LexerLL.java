package org.landal.parser;

import org.landal.lexer.ILexer;
import org.landal.lexer.LexerException;
import org.landal.token.IToken;
import org.landal.utility.ITokenFifoList;
import org.landal.utility.ITokenIterator;
import org.landal.utility.TokenFifoList;
import org.landal.utility.TokenIterator;

/**
 * Questo classe espande le responsabilit� di un lexer, fornendo al parser le
 * funzionalit� necessarie per compiere l'analisi ricorsiva discendente con un
 * numero <i>k</i> di simboli letti, ovvero la decisione su quale produzione
 * scegliere � posticipata dopo la lettura di k simboli. L'espansione delle
 * responsabilit� viene effettuata applicando in fase di progettazione della
 * classe il pattern Decorator. La classe opera nel seguente modo quando viene
 * richiamato il metodo <i>fillCoda</i> riempe una coda interna con la frase in
 * ingresso, le successive chiamate al lexer verranno deviate sulla coda,
 * 
 * @author Alex Landini
 * @version 1.0
 */
public class LexerLL implements ILexer, LLAnalisys {

	/**
	 * Tipo di dato che serve per contenere la lista dei token per l'analisi LL.
	 */
	protected ITokenFifoList coda = null;

	/**
	 * Iterator per scandire la coda.
	 */
	protected ITokenIterator it = null;

	/**
	 * Questo campo rappresenta il lexer da cui leggere i dati ingresso.
	 */
	protected ILexer lex;

	/**
	 * Costruttore di LexerLL.
	 * 
	 * @param lex
	 *            lexer effettivamente usato per leggere l'ingresso.
	 */
	public LexerLL(ILexer lex) {

		this.lex = lex;

	}

	/**
	 * Restituisce il token successivo della frase cui un lexer concreto pu�
	 * accedere grazie ad un dispositivo di accesso a lui noto.
	 * 
	 * @return IToken ritorna un riferimento al token successivo presente nella
	 *         frase
	 * @throws LexerException
	 *             Errori generati durante la lettura
	 */
	public IToken nextToken() throws LexerException {

		// verifico se la coda � vuota
		if (coda == null) {
			// coda vuota leggo direttamente dal lexer
			return lex.nextToken();
		} else {// leggo dalla coda

			if (!(coda.isEmpty())) {
				// la coda � piena leggo dalla coda e consumo il carattere
				return coda.getFifoToken();
			} else
				return null;
			// nel caso di coda vuota ritorno null in quanto non ci sono pi�
			// token
		}// else

	}// nextToken

	/**
	 * Questo metodo serve per reinizializzare il lexer fornendogli in ingresso
	 * un nuovo dispositivo di ingresso da cui leggere.
	 * 
	 * @param inp
	 *            nuovo dispositivo di ingresso da assegnare al Lexer.
	 */
	public void init(java.io.Reader inp) {

		lex.init(inp);

	}// init

	/**
	 * Questo metodo restituisce il numero della righe di programma che si sta
	 * leggendo.
	 * 
	 * @return String numero della stringa.
	 */
	public String getLine() {

		return lex.getLine();

	}// getLine

	/**
	 * Questo metodo serve per inizializzare l'analisi LL, riempe la coda se non
	 * � ancora stata riempita e inizializza un in Iterator ogni volta che viene
	 * invocata per effettuare una nuova scansione LL.
	 * 
	 * @param first
	 *            primo token da inserire nella coda
	 * @throws LexerException
	 *             dato che riempe la coda rimpalla tutti gli errori di lettura
	 *             dal lexer.
	 */
	public void initAnalisysLL(IToken first)
			throws org.landal.lexer.LexerException {

		// verifico se la coda � stata creata
		if (coda != null) {
			// se la coda � ancora vuota la riempio
			if (coda.isEmpty())
				coda.appendToken(first);// se � vuota lo metto nella coda nella
										// coda
			else {
				// nel caso la coda non sia vuota il token passato lo devo
				// mettere in testa alla coda
				// vado all'inizio della coda
				coda.goFirst();
				// inserisco il token all'inizio della coda
				coda.insertToken(first);
			}
		}// la coda non esiste la devo creare
		else
			fillCoda(first);
		// inizializzo un iterator per scandire la coda
		it = new TokenIterator(coda);

	}// initAnalisysLL

	/**
	 * Questo metodo permette di riempire la coda nel caso non esista ancora,
	 * con la sequenza dei token che compongono la frase di ingresso al lexer,
	 * la coda dopo che la funzione � stata invocata conterra tutti i token
	 * contenenti la frase. La coda deve essere gestita dalla classe che
	 * implementa l'interfaccia, usata per eseguire l'analisi LL(k).
	 * 
	 * @param first
	 *            primo token da inserire nella coda;
	 * @throws LexerException
	 *             dato che riempe la coda rimpalla tutti gli errori di lettura
	 *             dal lexer.
	 */
	protected void fillCoda(IToken first) throws LexerException {

		// creo la coda
		coda = creaCoda(first);

		// leggo dal lexer per verificare che il token non sia nullo
		IToken tok = lex.nextToken();
		// riempo la coda per l'analisi LL
		while (tok != null) {
			coda.append(tok);
			tok = lex.nextToken();
		}// while

	}// fillCoda

	/**
	 * Questo metodo permette di leggere dalla coda per verificare quale
	 * produzione applicare.
	 * 
	 * @return IToken il token letto dalla coda
	 */
	public org.landal.token.IToken readCoda() {
		// uso l'iterator per leggere la coda
		// leggo dalla coda e se sono alla fine restituisco null
		if (it.hasNext())
			return it.nextToken();
		else
			return null;

	}// readCoda

	/**
	 * Questo metodo serve a creare la particolare TokenFifoList, per utilizzare
	 * la lista anche come una fifo. Questo metodo pu� essere specializzato
	 * dalle sottoclassi.
	 * 
	 * @param first
	 *            primo token da inserire nella coda.
	 * @return TokenFifoList
	 */
	protected ITokenFifoList creaCoda(IToken first) {

		return new TokenFifoList(first);

	}

}// LexerLL
