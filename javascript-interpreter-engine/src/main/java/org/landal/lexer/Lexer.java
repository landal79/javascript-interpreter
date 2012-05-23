package org.landal.lexer;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.landal.token.BooleanToken;
import org.landal.token.IToken;
import org.landal.token.ITokenFactory;

/**
 * Questa classe rappresenta un analizzatore lessicale (lexer), esso produce in
 * uscita la sequenza di token che sono contenute nella frase. Nel caso di
 * errore nella lettura dei token il lexer ritorna una eccezzione di nome
 * LexerException.
 * 
 * @author Landini Alex
 * @version 1.0
 */
public class Lexer implements ILexer {

	/**
	 * Questo campo contiene una tabella associativa per le parole chiave del
	 * linguaggio. La tabella associativa � rappresentata da una mappa che
	 * associa un valore ad una chiave, in questo modo avendo una chiave �
	 * possibile ottenere quanto contenuto nel rispettivo campo.
	 */
	protected Map<String, IToken> kw = new HashMap<String, IToken>();

	/**
	 * Questo campo contiene una tabella associativa per le i simboli del
	 * linguaggio del linguaggio. La tabella associativa � rappresentata da una
	 * mappa che associa un valore ad una chiave, in questo modo avendo una
	 * chiave � possibile ottenere quanto contenuto nel rispettivo campo.
	 */
	protected Map<String, IToken> op = new HashMap<String, IToken>();

	/**
	 * Sfrutto il pattern Abstract Factory per creare i token, quindi memorizzo
	 * il riferimento al costruttore dei vari token.
	 */
	protected ITokenFactory costr;

	/**
	 * Rappresenta l'astrazione del dispositivo di input dal quale si andranno a
	 * leggere le frasi. Il tipo � la classe astratta Reader che fa parte delle
	 * librerie standard di Java.
	 */
	protected Reader inp;

	/**
	 * Questo campo rappresenta il numero di riga che si sta leggendo.
	 */
	protected int numLine = 1;

	/*
	 * ====================================================== 
	 * Costruttori
	 * ======================================================
	 */

	/**
	 * Costruttore senza argomenti, esso crea le strutture dati necessarie per
	 * il riconoscimento lessicale, ma non inizializza il Lexer, in questo caso
	 * sar� necessario richiamare <i>init ( Reader inp )</i> per assegnare un
	 * dispositivo di input al lexer.
	 * 
	 * @param tf
	 *            factory per istanziare specifici token.
	 */
	public Lexer(ITokenFactory tf) {

		// assegno il costruttore dei token
		this.costr = tf;

		this.setup();

	}// Lexer senza argomenti

	/**
	 * Costruttore di Lexer, esso crea le strutture dati necessarie per il
	 * riconoscimento lessicale, inoltre viene anche inizializzato inserendo il
	 * riferimento al dispositivo di ingesso da cui leggere la frase.
	 * 
	 * @param input
	 *            riferimento al dispositivo di ingresso
	 * @param tf
	 *            token factory per creare i token
	 */
	public Lexer(Reader inp, ITokenFactory tf) {

		// assegno il Reader passato come parametro
		this.inp = inp;

		// assegno il costruttore dei token
		this.costr = tf;

		this.setup();

	}// Costruttore Lexer con argomenti

	/**
	 * Questo metodo imposta le tabelle del Lexer
	 * 
	 */
	protected void setup() {

		// creo la hashTable per le parole chiave
		kw.put("break", costr.createBreakToken());
		kw.put("case", costr.createCaseToken());
		kw.put("continue", costr.createContinueToken());
		kw.put("default", costr.createDefaultToken());
		kw.put("do", costr.createDoToken());
		kw.put("else", costr.createElseToken());
		kw.put("for", costr.createForToken());
		kw.put("function", costr.createFunctionToken());
		kw.put("if", costr.createIfToken());
		kw.put("in", costr.createInToken());
		kw.put("new", costr.createNewToken());
		kw.put("null", costr.createNullToken());
		kw.put("return", costr.createReturnToken());
		kw.put("switch", costr.createSwitchToken());
		kw.put("this", costr.createThisToken());
		kw.put("var", costr.createVarToken());
		kw.put("while", costr.createWhileToken());

		// creo la hashtable per gli operatori
		op.put("+", costr.createPlusToken());
		op.put("-", costr.createMinusToken());
		op.put("*", costr.createMulToken());
		op.put("/", costr.createDivToken());
		op.put("%", costr.createModToken());
		op.put(">", costr.createGreaterToken());
		op.put("<", costr.createLowerToken());
		op.put(">=", costr.createGreatEqToken());
		op.put("<=", costr.createLowEqToken());
		op.put("[", costr.createOpenParQToken());
		op.put("]", costr.createCloseParQToken());
		op.put("++", costr.createIncToken());
		op.put("--", costr.createDecToken());
		op.put("{", costr.createOpenParGToken());
		op.put("}", costr.createCloseParGToken());
		op.put("?", costr.createQMarkToken());
		op.put(")", costr.createCloseParTToken());
		op.put("(", costr.createOpenParTToken());
		op.put(":", costr.createColonToken());
		op.put("!", costr.createEMarkToken());
		op.put(".", costr.createDotToken());
		op.put("==", costr.createEqualToken());
		op.put("!=", costr.createNotEqualToken());
		op.put(";", costr.createSemicolonToken());
		op.put("&&", costr.createAndToken());
		op.put("||", costr.createOrToken());
		op.put("=", costr.createUgualeToken());
		op.put(",", costr.createCommaToken());
	}

	/*
	 * ====================================================== Interfaccia ILexer
	 * ======================================================
	 */

	/**
	 * Questo metodo scorre lo stream in ingresso fornendo la sequenza di token
	 * da cui � composto.
	 * 
	 * @return IToken restituisce il token correntemente letto.
	 * @throws LexerException
	 *             si � verificato un qualsiasi tipo di errore durante l'analisi
	 *             lessicale.
	 */
	public IToken nextToken() throws LexerException {

		return scanToken();

	}// nextToken

	/**
	 * Questo metodo compie l'effettiva scansione dello stream in ingresso alla
	 * ricerca dei Token, pu� essere specializzato dalle sottoclassi per
	 * modificare l'analisi lessicale.
	 * 
	 * @return Token ritorna il token appena letto.
	 * @throws LexerException
	 *             si � verificato un errore durante l'analisi lessicale
	 */
	protected IToken scanToken() throws LexerException {

		char ch;// questa variabile mi serve per il carattere letto che viene
				// convertito da intero
		int n; // questa variabile locale mi serve per leggere l'intero che
				// deriva dallo stream

		try {
			// leggo il prossimo carattere
			n = readItem();
			// si � verificato un errore di lettura dallo stream di input

			if (n == -1)
				return null;// stream di ingresso finito restituisco null
			ch = (char) n;
			// verifico se ho un simbolo nel carattere corrente
			if (op.containsKey("" + ch) || (ch == '&') || (ch == '|'))
				return scanOp(ch);
			// verifico se ho ' o " che mi indicano l'inizio di una stringa
			else if ((ch == '\"') || (ch == '\''))
				return scanStringa(ch);
			// verifico se ho un numero in ingresso
			else if (Character.isDigit(ch))
				return scanNum(n);
			// verifico se ho un carattere in ingresso, allora posso avere un
			// identificatore o una keyword
			else if (Character.isLetter(ch) || (ch == '$') || (ch == '_'))
				return scanParola(ch);
			else
				throw new LexerException(
						"In ingresso c'� un oggetto non riconosciuto");

		} catch (LexerException e) {
			throw new LexerException("Errore: riga " + getLine() + " :"
					+ e.getMessage());
		} catch (IOException e1) {
			throw new LexerException("Errore: " + e1.getMessage());
		}

	}// fine nextToken

	/**
	 * Questo metodo crea i token corrispondenti agli operatori e ai simboli.
	 * 
	 * @param ch
	 *            rappresenta il carattere correntemente in ingresso.
	 * @return IToken ritorna il toke del simbolo
	 * @throws LexerException
	 *             significa che � stato letto un simbolo sconosciuto
	 */
	protected IToken scanOp(char ch) throws LexerException {

		int n; // intero che mi serve per leggere dallo stream
		// mi serve per contenere il carattere successivo
		// nel caso devo verificare se esistono simboli o
		// operatori di due caratteri

		try {

			inp.mark(1);
			// marca il punto corrente
			if ((n = inp.read()) > -1) {
				// leggo il prossimo carattere
				switch (ch) {
				case '+':
					if (n == '+')
						return op.get("++");
				case '-':
					if (n == '-')
						return op.get("--");
				case '<':
					if (n == '=')
						return op.get("<=");
				case '>':
					if (n == '=')
						return op.get(">=");
				case '=':
					if (n == '=')
						return op.get("==");
				case '!':
					if (n == '=')
						return op.get("!=");
				case '&':
					if (n == '&')
						return op.get("&&");
				case '|':
					if (n == '|')
						return op.get("||");
				}// fine switch
			}// fine if

			if (inp.markSupported())
				inp.reset();
			// ritorno al punto marcato nel reader
			// il simbolo � composto da un solo carattere
			if (op.containsKey("" + ch))
				return op.get("" + ch);
			// il simbolo non esiste
			else
				throw new LexerException("Simbolo non riconosciuto:" + ch);

		} catch (Exception e) {
			// si � verificato un errore
			throw new LexerException("Errore durante lettura simbolo");
		}

	}// fine scanOp

	/**
	 * Questo metodo esegue la scansione delle stringhe, che in javascript
	 * possono essere contenute fra ' oppure ", e se iniziano con ' e
	 * all'interno troviamo un carattere
	 * " questo fa parte della stringa, vale anche per ".
	 * 
	 * @param ch
	 *            carattere correntemente letto
	 * @return ritorna il token contenente la stringa letta.
	 * @throws LexerException
	 *             Si � verificato un errore durante la scansione della stringa.
	 */
	protected IToken scanStringa(char ch) throws LexerException {

		// ch ora contiene il simbolo che indica con cosa � iniziata la stringa
		String str = "";// variabile locale mi serve per contenere la stringa
		int c; // carattere correntemente in input

		try {

			// controllo se lo stream non � vuoto
			if ((c = (char) inp.read()) > -1) {
				// con un ciclo arrivo fino alla fine della stringa
				while (c != ch) {
					str = str + ((char) c);
					// verifico che la stringa non sia gi� finita prima di
					// trovare il terminale
					if ((c = inp.read()) == -1)
						throw new LexerException(
								"Manca il carattere terminale della stringa: "
										+ ch);
				}// fine while
					// la lettura della stringa � andata a buon fine quindi
					// restituisce il token StringaToken
				return costr.createStringaToken(str);
			}// l'input � vuoto la stringa � sbagliata
			else
				throw new LexerException(
						"Manca il carattere terminale della stringa: " + ch);

		} catch (IOException e) {// errore durante la lettura della stringa
			throw new LexerException(
					"Si � verificato un errore durante la lettura della stringa");
		}// fine catch

	}// fine scanStringa

	/**
	 * Questo metodo compie la scansione di un numero reale. Restituendo in caso
	 * di succeso il token che rappresenta il numero appena letto.
	 * 
	 * @param n
	 *            rappresenta il carattere appena letto.
	 * @return IToken ritorna il token che rappresenta il numero reale.
	 * @throws LexerExceptin
	 *             si � verificata un errore durante la lettura di un numero.
	 */
	protected IToken scanNum(int n) throws LexerException {

		String num = new String();// conterr� il numero sotto forma di stringa

		try {
			inp.mark(1);
			// con un ciclo leggo finch� ho un numero
			while (n >= '0' && n <= '9') {
				num = num + (char) n;
				// leggo il carattere successivo
				inp.mark(1);
				n = inp.read();
			}// fine while
				// se ho un numero reale posso avere un '.'
			if (n == '.') {
				num = num + (char) n;
				// leggo il carattere succesivo al '.'
				n = inp.read();
				// controllo se ho ancora dei numeri altrimenti ho un errore
				if (n >= '0' && n <= '9') {
					while (n >= '0' && n <= '9') {
						num = num + (char) n;
						// leggo il carattere successivo
						inp.mark(1);
						n = inp.read();
					}// while
					inp.reset();
					return costr.createNumToken(num);
				}// errore nella lettura del numero
				else
					throw new LexerException(
							"Errore durante la lettura di numero reale;");
			} else {// ritorno al carattere precedente
				inp.reset();
				return costr.createNumToken(num);
			}

		} catch (IOException e) {
			// errore durante la lettura di un numero
			throw new LexerException("Errore durante la lettura di un numero;");
		}// catch

	}// fine scanNum

	/**
	 * Questo metodo si occupa di compiere la scansione di identificatori,
	 * parole chiave e Boolean.
	 * 
	 * @param ch
	 *            rappresenta il carattere corrente
	 * @return IToken ritorna il token letto in caso di successo
	 * @throws LexerException
	 *             si � verificato un errore.
	 */
	protected IToken scanParola(char ch) throws LexerException {

		String ident = "";// rappresenta l'identificatore letto
		int n;// rappresenta il carattere corrente

		try {
			inp.mark(1);
			// con un ciclo prendo i caratteri che rappresentano
			// l'identificatore
			while (Character.isLetterOrDigit(ch) || (ch == '$') || (ch == '_')) {
				ident = ident + ch;
				inp.mark(1);
				if ((n = inp.read()) < -1)
					break;// fine dello stream
				ch = (char) n;
			}// while
			inp.reset();
			// verifico se ho un parola chiave
			if (kw.containsKey(ident))
				return kw.get(ident);// � una parola chiave
			// verifico se ho un boolean
			else if (ident.compareTo("false") == 0)
				return new BooleanToken("false");
			else if (ident.compareTo("true") == 0)
				return new BooleanToken("true");
			// allora ho un identificatore
			else
				return costr.createIdentToken(ident);
		} catch (Exception e) {
			throw new LexerException("Errore durante la lettura dallo stream");
		}

	}// fine scanParola

	/**
	 * Questo metodo serve a leggere i caratteri eliminando eventuali spazi,
	 * tabulazioni, nuova linea, etc.
	 * 
	 * @return int ritorna il carattere letto sotto forma di intero.
	 * @throws Exception
	 *             si � verificato un errore durante la lettura.
	 */
	protected int readItem() throws IOException {

		int n;// carattere letto

		do {// il ciclo elimina gli spazi bianchi

			n = inp.read();
			// verifico se ho un new line cos� aggiorno il numero di linea
			if (n == '\n') {
				numLine++;
				n = inp.read();
			}// if
				// se lo stream finisce restituisco null
			if (n == -1)
				return n;

		} while (Character.isWhitespace((char) n));

		return n;

	}// readItem

	/**
	 * Questo metodo serve per reinizializzare il lexer fornendogli in ingresso
	 * un nuovo dispositivo di ingresso da cui leggere.
	 * 
	 * @param inp
	 *            nuovo dispositivo di ingresso da assegnare al Lexer.
	 */
	public void init(Reader inp) {
		this.inp = inp;
		numLine = 1;
	}// init

	/**
	 * Questo metodo restituisce il numero della righe di programma che si sta
	 * leggendo.
	 * 
	 * @return String numero della stringa.
	 */
	public String getLine() {
		return "" + numLine;
	}// getLine

}// fine Lexer