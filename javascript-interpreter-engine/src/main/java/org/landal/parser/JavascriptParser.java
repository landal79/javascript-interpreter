package org.landal.parser;

import org.landal.apt.AccessArrayExp;
import org.landal.apt.AccessArrayValExp;
import org.landal.apt.AccessPropertyExp;
import org.landal.apt.AccessPropertyValExp;
import org.landal.apt.AndExp;
import org.landal.apt.AssignExp;
import org.landal.apt.BloccoIstr;
import org.landal.apt.BooleanoExp;
import org.landal.apt.BreakIstr;
import org.landal.apt.CaseIstr;
import org.landal.apt.ContinueIstr;
import org.landal.apt.CostrIstr;
import org.landal.apt.DefArrayElExp;
import org.landal.apt.DefCostrIstr;
import org.landal.apt.DefFunExp;
import org.landal.apt.DefFunIstr;
import org.landal.apt.DefPropObjectExp;
import org.landal.apt.DefVarExplIstr;
import org.landal.apt.DefaultIstr;
import org.landal.apt.DivExp;
import org.landal.apt.DoIstr;
import org.landal.apt.EmptyIstr;
import org.landal.apt.EqualExp;
import org.landal.apt.Exp;
import org.landal.apt.ExpCond;
import org.landal.apt.ExpSeq;
import org.landal.apt.ExpVar;
import org.landal.apt.ForInIstr;
import org.landal.apt.ForIstr;
import org.landal.apt.FunctionCallExp;
import org.landal.apt.FunctionCallValExp;
import org.landal.apt.GreatEqExp;
import org.landal.apt.GreaterExp;
import org.landal.apt.IApt;
import org.landal.apt.IdentExp;
import org.landal.apt.IdentValExp;
import org.landal.apt.IfIstr;
import org.landal.apt.Istr;
import org.landal.apt.ListaArgExp;
import org.landal.apt.ListaDefIstr;
import org.landal.apt.ListaElExp;
import org.landal.apt.ListaIstr;
import org.landal.apt.ListaParamIstr;
import org.landal.apt.ListaPropExp;
import org.landal.apt.LowEqExp;
import org.landal.apt.LowerExp;
import org.landal.apt.MinusExp;
import org.landal.apt.ModExp;
import org.landal.apt.MulExp;
import org.landal.apt.NewExp;
import org.landal.apt.NotEqualExp;
import org.landal.apt.NotExp;
import org.landal.apt.NullExp;
import org.landal.apt.NumExp;
import org.landal.apt.OrExp;
import org.landal.apt.PlusExp;
import org.landal.apt.PostDecExp;
import org.landal.apt.PostIncExp;
import org.landal.apt.PreDecExp;
import org.landal.apt.PreIncExp;
import org.landal.apt.PropExp;
import org.landal.apt.PropValExp;
import org.landal.apt.ReturnIstr;
import org.landal.apt.StringaExp;
import org.landal.apt.SwitchIstr;
import org.landal.apt.ThisIstr;
import org.landal.apt.UnMinusExp;
import org.landal.apt.UnPlusExp;
import org.landal.apt.WhileIstr;
import org.landal.lexer.ILexer;
import org.landal.lexer.LexerException;
import org.landal.token.IToken;

/**
 * Questa classe rappresenta un parser per Javascript. implementa l'interfaccia
 * IParser, quindi costituisce la realizzazione di un parser che costruisce un
 * albero di derivazione.
 * 
 * @author Alex Landini
 * @version 1.0
 */
public class JavascriptParser implements IParser {

	/**
	 * Questo campo contiene il curTokenen corrente.
	 */
	protected IToken curToken = null;

	/**
	 * Lexer che sta usando il parser.
	 */
	protected ILexer scan;

	/**
	 * Strumenti per analisi LL, servono per leggere la coda.
	 */
	protected LLAnalisys lettore;

	/**
	 * Variabile che contiene l'Apt.
	 */
	protected IApt apt;

	/**
	 * Questo campo contiene i messaggi di errore stato del Parser.
	 */
	protected String stato = null;

	/**
	 * Costruttore senza parametri.
	 */
	public JavascriptParser() {
		scan = null;
	}

	/**
	 * Costruttore che riceve in ingresso il lexer da usare.
	 * 
	 * @param lex
	 *            riferimento al lexer.
	 */
	public JavascriptParser(ILexer lex) {
		// costruisco il lexer per scansione LL
		scan = new LexerLL(lex);
		// assegno a lettore scanner in quanto implementa entrame le interfacce
		lettore = (LLAnalisys) scan;
	}

	/**
	 * Questo metodo effettua l'analisi sintattica della frase. di ingresso.
	 */
	public void parse() {

		try {
			// verifico se viene invocato il parser senza che sia stato
			// impostato il lexer
			if (scan == null)
				stato = "Errore: non � stato impostato il lexer";
			// verifico che non sia in corso il parsing di pi� frasi
			if (curToken == null) {
				// imposto il primo curToken
				// verifico che non sia gi� finita la frase
				if ((curToken = scan.nextToken()) != null) {
					// eseguo l'analisi sintattica della frase.
					apt = parseIstr();
				} else {// l'analisi � terminata
					apt = null;
				}
			}// � in corso l'analisi di pi� frasi quindi richiamo parseIstr
			else
				apt = parseIstr();

		} catch (Exception e) {
			// si � verificato un errore durante l'analisi
			stato = e.getMessage();
		}// catch

	}// parse

	/**
	 * Questo metodo restituisce un oggetto interfaccia IApt che rappresenta
	 * l'albero di derivazione costruito dal parser, se l'analisi sintattica �
	 * andata a buon fine altrimenti genera un eccezzione che indica il tipo di
	 * errore che si � verificato. Quando l'analisi � terminata restituisce
	 * null.
	 * 
	 * @return IApt restituisce l'albero di derivazione costruito dal parser.
	 * @throws ParserException
	 *             indicazione di errore durante l'analisi sintattica
	 * 
	 */
	public IApt getApt() throws ParserException {

		if (stato == null)
			return apt;
		// se stato non � null si � verificato un errore
		else
			throw new ParserException(stato);

	}// getApt

	/**
	 * Questo metodo ritorna lo stato del parser e contiene eventuali
	 * indicazioni di errore nel caso si sia verificato qualche inconveniente
	 * durante l'analisi sintattica.
	 * 
	 * @return String messaggio che rappresenta lo stato del Parser.
	 */
	public String getStato() {
		return stato;
	}

	/**
	 * Questo metodo resituisce il risultato sotto forma di stringa, cio� una
	 * rappresentazione esterna dell'albero di derivazione costruito dal parser.
	 * 
	 * @return rappresetazione esterna sotto forma di stringa dell'albero di
	 *         derivazione.
	 */
	public String getResultAsString() {
		return apt.toString();
	}// getResultAsString

	/**
	 * Questo metodo permette di impostare il lexer per la scansione, oppure di
	 * cambiare il lexer da cui il parser riceve i curTokenen.
	 * 
	 * @param lex
	 *            rappresenta un riferimento a un analizzatore lessicale.
	 */
	public void setLexer(ILexer lex) {

		// costruisco il lexer per scansione LL
		scan = new LexerLL(lex);
		// assegno a lettore scanner in quanto implementa entrame le interfacce
		lettore = (LLAnalisys) scan;
		// resetto lo stato complessivo
		apt = null;
		stato = null;
		curToken = null;

	}// setLexer

	/*
	 * ==========================================================================
	 * =========== FUNZIONI PER L'ANALISI LL(k) DELLA GRAMMATICA
	 * ==================
	 * ===================================================================
	 */

	/**
	 * Questo metodo verifica se ho un assegnamento in quanto la grammatica �
	 * LL(k).
	 * 
	 * @return boolean ritorna vero se ho un assegnamento altrimenti ritorna
	 *         false.
	 */
	protected boolean verifyAssign() throws Exception {

		// variabile locale che user� private il risultato
		boolean res;
		// variabile locale che usar� nella funzione
		IToken token = null;
		// inizializzo l'analisi LL
		try {
			// inizializzo l'analisi e gli passo il curTokene corrente
			// il curToken mi serve perch� fa parte della frase
			lettore.initAnalisysLL(curToken);
		} catch (LexerException e) {
			throw new ParserException(e.getMessage());
		}// fine catch

		// verifico se la coda non � vuota
		if ((token = lettore.readCoda()) != null) {
			token = verifyIdentificatore(token);
			// verifico se il curTokenen successivo all'identificatore � un '='
			// che mi
			// identifica l'assegnamento
			if ((token != null) && token.getID().equals("="))
				res = true;
			else
				res = false;
		}// if
		else
			res = false;// la coda � vuota
		// consumo il carattere corrente perch� tanto � gi� in curToken
		scan.nextToken();
		return res;

	}// fine verifyAssign

	/**
	 * Questo metodo verifica se ho una operazione con operatore dopo
	 * l'operando.
	 * 
	 * @return boolean ritorna vero se ho il tipo di espressione ricercata.
	 */
	protected boolean verifyExpPost() throws Exception {

		// variabile locale che user� private il risultato
		boolean res;
		// variabile locale che usar� nella funzione
		IToken token = null;
		// inizializzo l'analisi LL
		try {
			// inizializzo l'analisi e gli passo il curTokenen corrente nel
			// caso si debba ancora creare la coda in quanto mi serve
			// anche quello corrente
			lettore.initAnalisysLL(curToken);
		} catch (LexerException e) {
			throw new ParserException(e.getMessage());
		}// fine catch

		// verifico se la coda non � vuota
		if ((token = lettore.readCoda()) != null) {
			token = verifyIdentificatore(token);
			// verifico se il curTokenen successivo all'identificatore � un '='
			// che mi
			// identifica l'assegnamento
			if ((token != null)
					&& (token.getID().equals("--") || token.getID()
							.equals("++")))
				res = true;
			else
				res = false;
		}// if
		else
			res = false;// la coda � vuota

		// consumo il carattere corrente perch� tanto � gi� in curToken
		scan.nextToken();
		return res;

	}// fine verifyExpPost

	/**
	 * Questo metodo mi serve a verificare se ho un identificatore.
	 * 
	 * @param curToken
	 *            curTokenen su cui eseguire la scansione.
	 * @return IToken restituisce l'ultimo curTokenen letto.
	 */
	protected IToken verifyIdentificatore(IToken token) throws ParserException {

		// verifico se ho un identificatore attraverso la grammatica
		if ((token != null) && token.getID().equals("ident")) {
			token = lettore.readCoda();
			while (token != null) {
				if (token.getID().equals("[")) {
					token = lettore.readCoda();
					while (!(token.getID().equals("]"))) {
						token = lettore.readCoda();
						if (token == null)
							throw new ParserException(
									"Errore manca la parentesi ] che delimita l\'indice dell\'array");
					}// while
						// vado al curTokenen successivo alla ] dopo
						// l'identificatore
					token = lettore.readCoda();
				} else if (token.getID().equals(".")) {
					token = lettore.readCoda();
					// dopo il punto ho ancora un identificatore quindi
					// richiamo verifyIdentificatore
					token = verifyIdentificatore(token);
				} else if (token.getID().equals("(")) {
					lettore.readCoda();
					while (!(token.getID().equals(")"))) {
						token = lettore.readCoda();
						if (token == null)
							throw new ParserException(
									"Errore manca la parentesi ) di fine argomenti");
					}// fine while
						// leggo un nuovo curTokenen dalla coda mi posiziono
						// dopo la )
					token = lettore.readCoda();
				}
				// finito identificatore
				else
					return token;
			}// end while
			return token;
		} else
			throw new ParserException(
					"Errore: l-value non valido per assegnamento, ci si aspettava un identificatore");

	}// verifyIdentificatore

	/*
	 * ==========================================================================
	 * ============== FUNZIONI DI ANALISI
	 * ========================================
	 * ================================================
	 */

	/**
	 * Questo metodo esegue l'analisi sintattica. E' disegnata per il design for
	 * change, infatti pu� essere modificata dalle sottoclassi
	 */
	protected Istr parseIstr() throws Exception {

		// nel caso siano finite le frasi del linguaggio
		if (curToken == null)
			return null;

		// quando incontro l'istruzione vuota l'albero termina quindi non leggo
		// il curTokenen successivo
		if (curToken.getID().equals(";")) {
			curToken = scan.nextToken();
			return new EmptyIstr();
		}

		if (curToken.getID().equals("case")) {
			curToken = scan.nextToken();
			return parseCase();
		}

		if (curToken.getID().equals("default")) {
			curToken = scan.nextToken();
			return parseDefault();
		}

		if (curToken.getID().equals("{")) {
			curToken = scan.nextToken();
			return parseBlocco();
		}

		if (curToken.getID().equals("if")) {
			curToken = scan.nextToken();
			return parseIf();
		}

		if (curToken.getID().equals("switch")) {
			curToken = scan.nextToken();
			return parseSwitch();
		}

		if (curToken.getID().equals("while")) {
			curToken = scan.nextToken();
			return parseWhile();
		}

		if (curToken.getID().equals("do")) {
			curToken = scan.nextToken();
			return parseDo();
		}

		if (curToken.getID().equals("for")) {
			curToken = scan.nextToken();
			return parseFor();
		}

		if (curToken.getID().equals("continue")) {
			curToken = scan.nextToken();
			return parseContinue();
		}

		if (curToken.getID().equals("break")) {
			curToken = scan.nextToken();
			return parseBreak();
		}

		if (curToken.getID().equals("return")) {
			curToken = scan.nextToken();
			return parseReturn();
		}

		if (curToken.getID().equals("var")) {
			curToken = scan.nextToken();
			return parseVar();
		}

		if (curToken.getID().equals("function")) {
			curToken = scan.nextToken();
			return parseFunction();
		}

		return parseExpIstr();

	}// fine parseIstr

	/**
	 * Questo metodo compie la'analisi del costrutto cas.
	 */
	protected CaseIstr parseCase() throws Exception {

		Exp e = parseCond();

		if (e != null) {
			if ((curToken != null) && curToken.getID().equals(":")) {
				curToken = scan.nextToken();
				if ((curToken != null) && curToken.getID().equals("case"))
					return new CaseIstr(e, null);
				else {
					Istr i = parseIstr();
					return new CaseIstr(e, i);
				}
			} else
				throw new ParserException("Manca : nella clausola case");
		} else
			throw new ParserException(
					"Errore nella clausola case: manca l'espressione");

	}// fine parseCase

	/**
	 * Questo metodo esegue l'analisi dell'istruzione default.
	 */
	protected DefaultIstr parseDefault() throws Exception {

		if ((curToken != null) && curToken.getID().equals(":")) {
			curToken = scan.nextToken();
			Istr i = parseIstr();
			if (i != null)
				return new DefaultIstr(i);
			else
				throw new ParserException(
						"Errore nella clausola default: manca l'istruzione");
		} else
			throw new ParserException("Errore nella clausola default manca : ");

	}// fine parseDefault

	protected BloccoIstr parseBlocco() throws Exception {

		ListaIstr l = parseListaIstr();

		if (l != null) {
			if ((curToken != null) && curToken.getID().equals("}")) {
				curToken = scan.nextToken();
				return new BloccoIstr(l);
			} else
				throw new ParserException(
						"Errore nel blocco di istruzioni manca }");
		} else
			throw new ParserException(
					"Errore nel Blocco di istruzioni mancano le istruzioni");

	}// fine parseBlocco

	protected ListaIstr parseListaIstr() throws Exception {

		ListaIstr first, prev, next;

		Istr i = parseIstr();

		first = prev = new ListaIstr(i);

		while ((curToken != null) && (!(curToken.getID().equals("}")))) {
			i = parseIstr();
			prev.setSucc(next = new ListaIstr(i));
			prev = next;
		}
		return first;

	}// fine parseListaIstr

	protected IfIstr parseIf() throws Exception {

		if ((curToken != null) && curToken.getID().equals("(")) {
			curToken = scan.nextToken();
			Exp e = parseExp();
			if (e != null) {
				if ((curToken != null) && curToken.getID().equals(")")) {
					curToken = scan.nextToken();
					Istr i = parseIstr();
					if (i != null) {
						if ((curToken != null)
								&& curToken.getID().equals("else")) {
							curToken = scan.nextToken();
							if ((curToken != null)
									&& curToken.getID().equals(";"))
								throw new ParserException(
										"Errore manca l'istruzione dell'else");
							Istr i2 = parseIstr();
							if (i2 != null)
								return new IfIstr(e, i, i2);
							else
								throw new ParserException(
										"Errore Manca l'istruzione nell'else");
						} else
							return new IfIstr(e, i);
					} else
						throw new ParserException(
								"Errore Manca l'istruzione nel ramo Then");
				} else
					throw new ParserException(
							"Errore Manca la ) dopo la condizione dell'if");
			} else
				throw new ParserException("Errore Manca la condizione dell'if");
		} else
			throw new ParserException("Errore Manca ( dopo l'if");

	}// fine parseIf

	protected SwitchIstr parseSwitch() throws Exception {

		if ((curToken != null) && curToken.getID().equals("(")) {
			curToken = scan.nextToken();
			Exp e = parseExp();
			if (e != null) {
				if ((curToken != null) && curToken.getID().equals(")")) {
					curToken = scan.nextToken();
					Istr i = parseIstr();
					if (i != null)
						return (new SwitchIstr(e, i));
					else
						throw new ParserException(
								"Errore Manca il blocco di istruzioni nello Switch");
				} else
					throw new ParserException(
							"Errore Manca la ) dopo la condizione dello switch");
			} else
				throw new ParserException(
						"Errore Manca la condizione dello switch");
		} else
			throw new ParserException("Errore Manca ( dopo lo switch");

	}// fine parseSwitch

	protected WhileIstr parseWhile() throws Exception {

		if ((curToken != null) && curToken.getID().equals("(")) {
			curToken = scan.nextToken();
			Exp e = parseExp();
			if (e != null) {
				if ((curToken != null) && curToken.getID().equals(")")) {
					curToken = scan.nextToken();
					Istr i = parseIstr();
					if (i != null)
						return new WhileIstr(e, i);
					else
						throw new ParserException(
								"Errore Manca il corpo del While");
				} else
					throw new ParserException(
							"Errore Manca la ) dopo la condizione del While");
			} else
				throw new ParserException(
						"Errore Manca la condizione del While");
		} else
			throw new ParserException("Errore Manca ( dopo il While");

	}// fine parseWhile

	protected DoIstr parseDo() throws Exception {

		Istr i = parseIstr();
		if (i != null) {
			if ((curToken != null) && curToken.getID().equals("while")) {
				curToken = scan.nextToken();
				if ((curToken != null) && curToken.getID().equals("(")) {
					curToken = scan.nextToken();
					Exp e = parseExp();
					if (e != null) {
						if ((curToken != null) && curToken.getID().equals(")")) {
							curToken = scan.nextToken();
							if ((curToken != null)
									&& curToken.getID().equals(";")) {
								curToken = scan.nextToken();
								return (new DoIstr(e, i));
							} else
								throw new ParserException("Errore Manca il ;");
						} else
							throw new ParserException(
									"Errore Manca ) dopo la condizione del While");
					} else
						throw new ParserException(
								"Errore Non c'� la condizione del While");
				} else
					throw new ParserException(
							"Errore Manca ( prima dopo il While");
			} else
				throw new ParserException("Errore non c'� il While");
		} else
			throw new ParserException("Errore manca il corpo del Do-While");

	}// fine parseDo

	protected Istr parseFor() throws Exception {

		if ((curToken != null) && curToken.getID().equals("(")) {
			curToken = scan.nextToken();
			if ((curToken != null) && curToken.getID().equals("var")) {// ciclo
																		// for-in
				curToken = scan.nextToken();
				DefVarExplIstr iniz = parseVarIn();
				if (iniz != null) {
					if ((curToken != null) && curToken.getID().equals("in")) {
						curToken = scan.nextToken();
						if ((curToken != null)
								&& curToken.getID().equals("ident")) {
							ExpVar id = (ExpVar) parseExpVarRight();
							if ((curToken != null)
									&& curToken.getID().equals(")")) {
								curToken = scan.nextToken();
								Istr i = parseIstr();// corpo del ciclo
								if (i != null) {
									return new ForInIstr(iniz, id, i);
								} else
									throw new ParserException(
											"Errore Manca il corpo del ciclo for");
							} else
								throw new ParserException(
										"Errore Manca la ) dopo il ciclo for");
						} else
							throw new ParserException(
									"Errore nel for-in manca l'identificatore dell'oggetto");
					} else
						throw new ParserException(
								"Errore nel ciclo for-in manca la parola chiave in");
				} else
					throw new ParserException(
							"Errore nel ciclo for-in manca l'istruzione di inizializzazione");
			} else {// ciclo for
				Istr iniz = parseExp();
				if ((curToken != null) && curToken.getID().equals(";")) {
					curToken = scan.nextToken();
					Exp cond = parseExp();
					if ((curToken != null) && curToken.getID().equals(";")) {
						curToken = scan.nextToken();
						Exp inc = parseExp();
						if ((curToken != null) && curToken.getID().equals(")")) {
							curToken = scan.nextToken();
							Istr corpo = parseIstr();
							if (corpo != null)
								return (new ForIstr(iniz, cond, inc, corpo));
							else
								throw new ParserException(
										"Errore manca il corpo nel ciclo for");
						} else
							throw new ParserException(
									"Errore manca la ) nel ciclo for");
					} else
						throw new ParserException(
								"Errore manca il ; dopo la condizione nel ciclo for");
				} else
					throw new ParserException(
							"Errore nel ciclo for manca il ; dopo l'inizializzazione");
			}// fine else
		} else
			throw new ParserException("Errore manca la ( dopo il for");

	}// fine parseFor

	protected ContinueIstr parseContinue() throws Exception {

		if ((curToken != null) && curToken.getID().equals(";")) {
			curToken = scan.nextToken();
			return (new ContinueIstr());
		} else
			throw new ParserException("Errore Manca il ; dopo il continue");

	}// fine parseContinue

	protected BreakIstr parseBreak() throws Exception {

		if ((curToken != null) && curToken.getID().equals(";")) {
			curToken = scan.nextToken();
			return (new BreakIstr());
		} else
			throw new ParserException("Errore Manca il ; dopo il break");

	}// fine parseBreak

	protected ReturnIstr parseReturn() throws Exception {

		Exp e = null;// valore di ritorno
		// verifico se ho un ; significa che il return non ha una espressione di
		// ritorno
		if ((curToken != null) && (!(curToken.getID().equals(";"))))
			e = parseExp();// eseguo il parsing dell'exp se c'�
		if ((curToken != null) && curToken.getID().equals(";")) {
			curToken = scan.nextToken();
			if (e != null)
				return new ReturnIstr(e);
			else
				return new ReturnIstr();
		} else
			throw new ParserException(
					"Errore manca il ; nell\'istruzione return");

	}// fine parseReturn

	protected DefVarExplIstr parseVar() throws Exception {

		ListaDefIstr l = parseListaDef();

		if (l != null) {
			if ((curToken != null) && curToken.getID().equals(";")) {// trovato
																		// ;
																		// fine
																		// della
																		// lista
																		// di
																		// dichiarazioni

				curToken = scan.nextToken();

				return new DefVarExplIstr(l);
			} else
				throw new ParserException("Errore Manca ; nell'istruzione var");
		} else
			throw new ParserException(
					"Errore in parseDefVarExpl manca la lista di definizioni");

	}// fine parseVar

	protected DefVarExplIstr parseVarIn() throws Exception {

		ListaDefIstr l = parseListaDef();

		if (l != null)
			return new DefVarExplIstr(l);
		else
			throw new ParserException(
					"Errore in parseDefVarExpl manca la lista di definizioni");

	}// fine parseVar

	protected ListaDefIstr parseListaDef() throws Exception {

		if ((curToken != null) && curToken.getID().equals("ident")) {
			IdentExp id = new IdentExp(curToken.toString());
			curToken = scan.nextToken();
			if ((curToken != null) && curToken.getID().equals("=")) {
				curToken = scan.nextToken();
				Exp e = parseAssign();
				if (e != null) {
					if ((curToken != null) && curToken.getID().equals(",")) {
						curToken = scan.nextToken();
						ListaDefIstr l = parseListaDef();
						if (l != null)
							return new ListaDefIstr(id, e, l);
						else
							throw new ParserException(
									"Errore Manca la lista delle istruzioni in parseListaDef");
					} else
						return new ListaDefIstr(id, e);
				} else
					throw new ParserException(
							"Errore manca l\'espressione dopo l\'uguale");
			} else if ((curToken != null) && curToken.getID().equals(",")) {
				curToken = scan.nextToken();
				ListaDefIstr l = parseListaDef();
				if (l != null)
					return new ListaDefIstr(id, l);
				else
					throw new ParserException(
							"Errore Manca la lista delle istruzioni in parseListaDef");
			} else
				return new ListaDefIstr(id);
		} else
			throw new ParserException(
					"Errore Manca l'identificatore della variabile");

	}// fine parseListaDef

	protected Istr parseFunction() throws Exception {

		ListaParamIstr lParam = null;

		if ((curToken != null) && curToken.getID().equals("ident")) {// nome
																		// funzione
			IdentExp id = new IdentExp(curToken.toString());
			curToken = scan.nextToken();
			if ((curToken != null) && curToken.getID().equals("(")) {// parentesi
																		// (
				curToken = scan.nextToken();
				lParam = parseListaParam();// lista parametri
				if ((curToken != null) && curToken.getID().equals(")")) {// parentesi
																			// )
					curToken = scan.nextToken();
					if ((curToken != null) && curToken.getID().equals("{")) {// parentesi
																				// {
						curToken = scan.nextToken();
						if ((curToken != null)
								&& curToken.getID().equals("this")) {// eseguo
																		// il
																		// parsing
																		// di un
																		// costruttore
							curToken = scan.nextToken();
							CostrIstr costr = parseCostr();
							if (costr != null) {
								if ((curToken != null)
										&& curToken.getID().equals("}")) {// parentesi
																			// }
									curToken = scan.nextToken();
									if (lParam != null)
										return (new DefCostrIstr(id, lParam,
												costr));
									else
										return (new DefCostrIstr(id, costr));
								} else
									throw new ParserException(
											"Errore Manca la } dopo la definizione del costruttore");
							} else
								throw new ParserException(
										"Errore manca la sequenza di istruzioni del costruttore");
						} else {// eseguo il parsing di una funzione
							ListaIstr corpo = parseListaIstr();
							if (corpo != null) {
								if ((curToken != null)
										&& curToken.getID().equals("}")) {
									curToken = scan.nextToken();
									if (lParam != null)
										return new DefFunIstr(id, lParam, corpo);
									else
										return new DefFunIstr(id, corpo);
								} else
									throw new ParserException(
											"Errore manca la } alla fine del corpo della funzione");
							} else
								throw new ParserException(
										"Errore manca il corpo della funzione");
						}// fine else
					} else
						throw new ParserException(
								"Errore manca la { dell'inizio corpo funzione");
				} else
					throw new ParserException(
							"Errore manca la ) dopo la lista di parametri");
			} else
				throw new ParserException(
						"Errore manca la ( dopo l'identificatore del nome della funzione");
		} else
			throw new ParserException(
					"Errore manca l'identificatore della funzione");

	}// fine parseFunction

	protected ListaParamIstr parseListaParam() throws Exception {

		if ((curToken != null) && curToken.getID().equals("ident")) {
			IdentExp id = new IdentExp(curToken.toString());
			curToken = scan.nextToken();
			if ((curToken != null) && curToken.getID().equals(",")) {
				curToken = scan.nextToken();
				ListaParamIstr succ = parseListaParam();
				if (succ != null)
					return (new ListaParamIstr(id, succ));
				else
					throw new ParserException("Errore manca il parametro");
			} else
				return (new ListaParamIstr(id));
		} else
			return null;// non ci sono parametri

	}// fine parseListaParam

	protected CostrIstr parseCostr() throws Exception {

		ThisIstr i = parseThis();

		if ((curToken != null) && curToken.getID().equals("this")) {// se c'è
																	// un'altra
																	// istruzione
																	// this
			curToken = scan.nextToken();
			CostrIstr succ = parseCostr();
			return new CostrIstr(i, succ);
		} else
			return new CostrIstr(i);

	}// fine parseCostr

	protected ThisIstr parseThis() throws Exception {

		if ((curToken != null) && curToken.getID().equals(".")) {// leggo il .
			curToken = scan.nextToken();
			if ((curToken != null) && curToken.getID().equals("ident")) {// leggo
																			// l'identificatore
																			// del
																			// nome
																			// della
																			// proprietà
				IdentExp id = new IdentExp(curToken.toString());
				curToken = scan.nextToken();
				if ((curToken != null) && curToken.getID().equals("=")) {// leggo
																			// l'uguale
					curToken = scan.nextToken();
					Exp e = parseAssign();
					if (e != null) {
						if ((curToken != null) && curToken.getID().equals(";")) {// leggo
																					// i
																					// :
																					// di
																					// fine
																					// espressione
							curToken = scan.nextToken();
							return (new ThisIstr(id, e));
						} else
							throw new ParserException(
									"Errore manca il ; a fine istruzione");
					} else
						throw new ParserException(
								"Errore manca l'espressione che determina il valore della propriet�");
				} else
					throw new ParserException(
							"Errore manca l'uguale nell'assegnazione della propriet�");
			} else
				throw new ParserException(
						"Errore non c'� il nome di un identificatore per la propriet� dell'oggetto");
		} else
			throw new ParserException("Errore manca il . dopo this");

	}// fine parseThis

	protected Exp parseExpIstr() throws Exception {

		Exp e = parseExp();

		if (e != null) {
			if ((curToken != null) && curToken.getID().equals(";")) {
				curToken = scan.nextToken();
				return e;
			} else
				throw new ParserException("Errore manca il ;");
		} else
			throw new ParserException("Errore Manca l'espressione");

	}// fine parseExpIstr

	protected Exp parseExp() throws Exception {

		Exp e = parseAssign();
		if (e != null) {
			while ((curToken != null) && curToken.getID().equals(",")) {
				curToken = scan.nextToken();
				Exp next = parseAssign();
				if (next != null)
					e = new ExpSeq(e, next);
				else
					throw new ParserException(
							"Errore Manca l'istruzione dopo la ,");
			}// fine While
			return e;
		} else
			throw new ParserException(
					"Errore manca l\'espressione di assegnamento");

	}// fine parseExp

	protected Exp parseAssign() throws Exception {

		// controllo se ho un identificatore
		if ((curToken != null) && curToken.getID().equals("ident")) {// c'� un
																		// identificatore
																		// parsing
																		// LL(k)
			if (verifyAssign()) {// verifico se ho un assegnamento
				// ho un assegnamento
				Exp id = parseExpVarLeft();
				if (id != null) {
					if ((curToken != null) && curToken.getID().equals("=")) {
						curToken = scan.nextToken();
						Exp val = parseAssign();
						if (val != null)
							return new AssignExp(id, val);
						else
							throw new ParserException(
									"Errore manca il valore da assegnare");
					} else
						throw new ParserException(
								"Errore manca l\'uguale dopo dell\'assegnamento");
				} else
					throw new ParserException(
							"Errore manca l\'identificatore nell\'espressione di assegnamento");
			} else {// non ho un assegnamento
				return parseCond();
			}
		}// non ho un identificatore
		else {// caso in cui non ho un identificatore

			if ((curToken != null) && curToken.getID().equals("null")) {// ho un
																		// null
				curToken = scan.nextToken();
				return new NullExp();
			}

			if ((curToken != null) && curToken.getID().equals("new")) {// costruzione
																		// attraverso
																		// new

				curToken = scan.nextToken();
				return parseNew();
			}

			if ((curToken != null) && curToken.getID().equals("{")) {// letterale
																		// oggetto
				curToken = scan.nextToken();
				return parseObj();
			}

			if ((curToken != null) && curToken.getID().equals("[")) {// letterale
																		// array
				curToken = scan.nextToken();
				return parseArray();
			}

			if ((curToken != null) && curToken.getID().equals("function")) {// espressione
																			// funzione
				curToken = scan.nextToken();
				return parseFunExp();
			}

			return parseCond();

		}// fine else

	}// fine parseAssign

	protected NewExp parseNew() throws Exception {

		if ((curToken != null) && curToken.getID().equals("ident")) {// identificatore
																		// costruttore
			IdentValExp id = new IdentValExp(curToken.toString());
			curToken = scan.nextToken();
			if ((curToken != null) && curToken.getID().equals("(")) {// parentesi
																		// (
				curToken = scan.nextToken();
				ListaArgExp lArg = parseListaArg();// leggo la lista di
													// argomenti se esiste
				if ((curToken != null) && curToken.getID().equals(")")) {// parentesi
																			// )
					curToken = scan.nextToken();
					if (lArg != null)
						return (new NewExp(id, lArg));
					else
						return (new NewExp(id));
				} else
					throw new ParserException("Errore Manca la )");
			} else
				throw new ParserException(
						"Errore Manca la ( dopo il nome del costruttore");
		} else
			throw new ParserException("Errore Manca il nome del costruttore");

	}// fine parseNew

	protected DefPropObjectExp parseObj() throws Exception {

		ListaPropExp l = parseListaProp();

		if (l != null) {
			if ((curToken != null) && curToken.getID().equals("}")) {
				curToken = scan.nextToken();
				return new DefPropObjectExp(l);
			} else
				throw new ParserException(
						"Errore Manca la } alla fine delle propriet� dell'oggetto");
		} else
			throw new ParserException(
					"Errore la lista delle propriet� dell'oggetto");

	}// fine parseObj

	protected ListaPropExp parseListaProp() throws Exception {

		if ((curToken != null) && curToken.getID().equals("ident")) {// nome
																		// della
																		// propriet�
																		// dell'oggetto
			IdentExp id = new IdentExp(curToken.toString());
			curToken = scan.nextToken();
			if ((curToken != null) && curToken.getID().equals(":")) {// leggo :
				curToken = scan.nextToken();
				Exp elemento = parseElemento();// scansione valore propriet�
												// oggetto
				if (elemento != null) {
					if ((curToken != null) && curToken.getID().equals(",")) {// verifico
																				// se
																				// esistono
																				// altre
																				// propriet�
						curToken = scan.nextToken();
						ListaPropExp succ = parseListaProp();// scansione della
																// propriet�
						if (succ != null)
							return (new ListaPropExp(id, elemento, succ));
						else
							throw new ParserException(
									"Errore Manca la propriet� dopo \',\'");
					} else
						return new ListaPropExp(id, elemento);
				} else
					throw new ParserException(
							"Errore manca il valore da assegnare alla propriet�");
			} else
				throw new ParserException(
						"Errore mancano i \':\' prima della definizione della propriet�");
		} else
			throw new ParserException(
					"Errore manca l\'identificatore della propriet�");

	}// fine parseListaProp

	protected DefArrayElExp parseArray() throws Exception {

		ListaElExp l = parseListaEl();

		if (l != null) {
			if ((curToken != null) && curToken.getID().equals("]")) {
				curToken = scan.nextToken();
				return new DefArrayElExp(l);
			} else
				throw new ParserException(
						"Errore Manca la ] alla fine della lista degli elementi");
		} else
			throw new ParserException("Errore Manca la lista degli elementi");

	}// fine parseArray

	protected ListaElExp parseListaEl() throws Exception {

		Exp el = parseElemento();// scansione del valore dell'elemento

		if (el != null) {
			if ((curToken != null) && curToken.getID().equals(",")) {// verifico
																		// se
																		// c'�
																		// un
																		// altro
																		// elemento
				curToken = scan.nextToken();
				ListaElExp succ = parseListaEl();
				if (succ != null)
					return new ListaElExp(el, succ);
				else
					throw new ParserException(
							"Errore manca l'elemento dell'array");
			} else
				return new ListaElExp(el);// l'elemento non ha successori
		} else
			throw new ParserException("Errore manca l'elemento dell'array");

	}// fine parseListaEl

	protected Exp parseElemento() throws Exception {

		if ((curToken != null) && curToken.getID().equals("stringa")) {// Stringa
			IToken tok = curToken;
			curToken = scan.nextToken();
			return new StringaExp(tok.toString());
		}

		if ((curToken != null) && curToken.getID().equals("num")) {// Numero
			IToken tok = curToken;
			curToken = scan.nextToken();
			return new NumExp(tok.toString());
		}

		if ((curToken != null) && curToken.getID().equals("boolean")) {// Booleano
			IToken tok = curToken;
			curToken = scan.nextToken();
			return new BooleanoExp(tok.toString());
		}

		if ((curToken != null) && curToken.getID().equals("{")) {// Oggetto
			curToken = scan.nextToken();
			return parseObj();
		}

		if ((curToken != null) && curToken.getID().equals("[")) {// Array
			curToken = scan.nextToken();
			return parseArray();
		}

		return parseAssign();// potrebbe essere un'espressione

	}// fine parseElemento

	protected DefFunExp parseFunExp() throws Exception {

		if ((curToken != null) && curToken.getID().equals("(")) {// parentesi (
			curToken = scan.nextToken();
			ListaParamIstr l = parseListaParam();// scansione lista parametri
			if ((curToken != null) && curToken.getID().equals(")")) {// parentesi
																		// )
				curToken = scan.nextToken();
				if ((curToken != null) && curToken.getID().equals("{")) {// parentesi
																			// {
					curToken = scan.nextToken();
					ListaIstr li = parseListaIstr();// scansione lista
													// istruzioni
					if (li != null) {
						if ((curToken != null) && curToken.getID().equals("}")) {// parentesi
																					// }
							curToken = scan.nextToken();
							if (l != null)
								return new DefFunExp(l, li);
							else
								return new DefFunExp(li);
						} else
							throw new ParserException(
									"Errore manca la \'}\' alla fine del blocco di istruzioni della funzione");
					} else
						throw new ParserException(
								"Errore manca la lista delle istruzioni della funzione");
				} else
					throw new ParserException(
							"Errore nella costruzione della funzione manca la\')\'");
			} else
				throw new ParserException("Errore Manca la Parentesi \'{\'");
		} else
			throw new ParserException("Errore Manca la \'(\'");

	}// fine parseFunExp

	protected Exp parseCond() throws Exception {

		Exp e = parseOr();

		if (e != null) {
			if ((curToken != null) && curToken.getID().equals("?")) {
				curToken = scan.nextToken();
				Exp e1 = parseExp();
				if (e1 != null) {
					if ((curToken != null) && curToken.getID().equals(":")) {
						curToken = scan.nextToken();
						Exp e2 = parseExp();
						if (e2 != null)
							return new ExpCond(e, e1, e2);
						else
							throw new ParserException(
									"Errore:neel\'operatore ? manca l\'espressione nel ramo false");
					} else
						throw new ParserException(
								"Errore manca il \':\' nell\'espressione condizionale");
				} else
					throw new ParserException(
							"Errore nell\'operatore ? manca l\'espressione del ramo true");
			}
			return e;// nel caso non si abbia un'espressione condizionale
		} else
			throw new ParserException("Errore manca l'espressione");

	}// fine parseCond

	protected Exp parseOr() throws Exception {

		Exp e1 = parseAnd();

		if (e1 != null) {
			while ((curToken != null) && curToken.getID().equals("||")) {
				curToken = scan.nextToken();
				Exp e2 = parseAnd();
				if (e2 != null)
					e1 = new OrExp(e1, e2);
				else
					throw new ParserException(
							"Errore manca il secondo operando nell\'espressione or");
			}// end While
			return e1;
		} else
			throw new ParserException("Errore manca l'espressione");

	}// fine parseOr

	protected Exp parseAnd() throws Exception {

		Exp e1 = parseUguaglianza();

		if (e1 != null) {
			while ((curToken != null) && curToken.getID().equals("&&")) {
				curToken = scan.nextToken();
				Exp e2 = parseUguaglianza();
				if (e2 != null)
					e1 = new AndExp(e1, e2);
				else
					throw new ParserException(
							"Errore manca il secondo operatore nell\'espressione and");
			}// end While
			return e1;
		} else
			throw new ParserException(
					"Errore manca l'espressione di uguaglianza");

	}// fine parseAnd

	protected Exp parseUguaglianza() throws Exception {

		Exp e1 = parseRel();

		if (e1 != null) {
			while (curToken != null) {
				if (curToken.getID().equals("==")) {
					curToken = scan.nextToken();
					Exp e2 = parseRel();
					if (e2 != null)
						e1 = new EqualExp(e1, e2);
					else
						throw new ParserException(
								"Errore manca la seconda espressione nell\'uguaglianza");
				} else if (curToken.getID().equals("!=")) {
					curToken = scan.nextToken();
					Exp e2 = parseRel();
					if (e2 != null)
						e1 = new NotEqualExp(e1, e2);
					else
						throw new ParserException(
								"Errore manca la seconda espressione nella disuguaglianza");
				} else
					return e1;
			}// end While
			return e1;
		} else
			throw new ParserException("Errore manca l'espressione");

	}// fine parseUguaglianza

	protected Exp parseRel() throws Exception {

		Exp e1 = parseAritm();

		if (e1 != null) {
			while (curToken != null) {
				if (curToken.getID().equals(">")) {
					curToken = scan.nextToken();
					Exp e2 = parseAritm();
					if (e2 != null)
						e1 = new GreaterExp(e1, e2);
					else
						throw new ParserException(
								"Errore manca la seconda espressione di >");
				} else if (curToken.getID().equals(">=")) {
					curToken = scan.nextToken();
					Exp e2 = parseAritm();
					if (e2 != null)
						e1 = new GreatEqExp(e1, e2);
					else
						throw new ParserException(
								"Errore manca la seconda espressione di >=");
				} else if (curToken.getID().equals("<")) {
					curToken = scan.nextToken();
					Exp e2 = parseAritm();
					if (e2 != null)
						e1 = new LowerExp(e1, e2);
					else
						throw new ParserException(
								"Errore manca la seconda espressione di <");
				} else if (curToken.getID().equals("<=")) {
					curToken = scan.nextToken();
					Exp e2 = parseAritm();
					if (e2 != null)
						e1 = new LowEqExp(e1, e2);
					else
						throw new ParserException(
								"Errore manca la seconda espressione di <=");
				} else
					return e1;
			}// end While
			return e1;
		} else
			throw new ParserException("Errore manca l'espressione");

	}// fine parseRel

	protected Exp parseAritm() throws Exception {

		Exp e1 = null;

		if ((curToken != null) && curToken.getID().equals("stringa")) {// caso
																		// in
																		// cui
																		// il
																		// primo
																		// operando
																		// � una
																		// stringa
			e1 = new StringaExp(curToken.toString());
			curToken = scan.nextToken();
		} else {
			e1 = parseTerm();
			if (e1 == null)
				throw new ParserException("Errore manca l'espressione");
		}// else

		while (curToken != null) {
			if (curToken.getID().equals("+")) {// leggo +
				curToken = scan.nextToken();
				Exp e2 = null;
				if (curToken.getID().equals("stringa")) {// secondo operndo
															// stringa
					e2 = new StringaExp(curToken.toString());
					curToken = scan.nextToken();
				} else {
					e2 = parseTerm();
					if (e2 == null)
						throw new ParserException(
								"Errore manca il secondo operando  dell\'espressione aritmetica");
				}
				e1 = new PlusExp(e1, e2);
			} else if (curToken.getID().equals("-")) {
				curToken = scan.nextToken();
				Exp e2 = parseTerm();
				if (e2 != null)
					e1 = new MinusExp(e1, e2);
				else
					throw new ParserException(
							"Errore manca il secondo operando  dell\'espressione aritmetica");
			} else
				return e1;
		}// end While
		return e1;

	}// fine parseAritm

	protected Exp parseTerm() throws Exception {

		Exp e1 = parseFactor();

		if (e1 != null) {
			while (curToken != null) {
				if (curToken.getID().equals("*")) {// leggo *
					curToken = scan.nextToken();
					Exp e2 = parseFactor();
					if (e2 != null)
						e1 = new MulExp(e1, e2);
					else
						throw new ParserException(
								"Errore manca la seconda espressione di *");
				} else if (curToken.getID().equals("/")) {// leggo /
					curToken = scan.nextToken();
					Exp e2 = parseFactor();
					if (e2 != null)
						e1 = new DivExp(e1, e2);
					else
						throw new ParserException(
								"Errore manca la seconda espressione di /");
				} else if (curToken.getID().equals("%")) {// leggo %
					curToken = scan.nextToken();
					Exp e2 = parseFactor();
					if (e2 != null)
						e1 = new ModExp(e1, e2);
					else
						throw new ParserException(
								"Errore manca la seconda espressione di %");
				} else
					return e1;
			}// end While
			return e1;
		} else
			throw new ParserException("Errore manca il fattore");

	}// fine parseTerm

	protected Exp parseFactor() throws Exception {

		if ((curToken != null) && curToken.getID().equals("+")) {// leggo +
			curToken = scan.nextToken();
			Exp e = parseExpPost();
			if (e != null)
				return new UnPlusExp(e);
			else
				throw new ParserException(
						"Errore manca l\'espressione dopo il +");
		} else if ((curToken != null) && curToken.getID().equals("-")) {// leggo
																		// -
			curToken = scan.nextToken();
			Exp e = parseExpPost();
			if (e != null)
				return new UnMinusExp(e);
			else
				throw new ParserException(
						"Errore manca l\'espressione dopo il -");
		} else if ((curToken != null) && curToken.getID().equals("!")) {// leggo
																		// !
			curToken = scan.nextToken();
			Exp e = parseExpPost();
			if (e != null)
				return new NotExp(e);
			else
				throw new ParserException(
						"Errore manca l\'espressione dopo il !");
		} else if ((curToken != null) && curToken.getID().equals("++")) {// leggo
																			// ++
			curToken = scan.nextToken();
			Exp e = parseExpVarLeft();
			if (e != null)
				return (new PreIncExp(e));
			else
				throw new ParserException("Errore manca l\'espressione dopo ++");
		} else if ((curToken != null) && curToken.getID().equals("--")) {// leggo
																			// --
			curToken = scan.nextToken();
			Exp e = parseExpVarLeft();
			if (e != null)
				return new PreDecExp(e);
			else
				throw new ParserException("Errore manca l\'espressione dopo --");
		} else if ((curToken != null) && curToken.getID().equals("(")) {// leggo
																		// (
			curToken = scan.nextToken();
			Exp e = parseExp();
			if (e != null) {
				if ((curToken != null) && curToken.getID().equals(")")) {// leggo
																			// )
					curToken = scan.nextToken();
					return e;
				} else
					throw new ParserException(
							"Errore manca la ) dopo l\'espressione");
			} else
				throw new ParserException(
						"Errore manca l'espressione all\'interno di ()");
		} else
			return parseExpPost();

	}// fine parseFactor

	protected Exp parseExpPost() throws Exception {

		Exp e = null;

		if ((curToken != null) && curToken.getID().equals("num")) {// ho un
																	// numero
			IToken t = curToken;
			curToken = scan.nextToken();
			return new NumExp(t.toString());
		} else if ((curToken != null) && curToken.getID().equals("boolean")) {// ho
																				// un
																				// booleano
			IToken t = curToken;
			curToken = scan.nextToken();
			return new BooleanoExp(t.toString());
		} else if ((curToken != null) && curToken.getID().equals("ident")) {
			// verifico se ci sono pre e post decremento
			if (verifyExpPost()) {// ci sono pre e post decremento
				e = parseExpVarLeft();
				if (e != null) {
					if ((curToken != null) && curToken.getID().equals("++")) {
						curToken = scan.nextToken();
						return new PostIncExp(e);
					} else if ((curToken != null)
							&& curToken.getID().equals("--")) {
						curToken = scan.nextToken();
						return new PostDecExp(e);
					} else
						throw new ParserException(
								"Errore nel riconoscimento dell\'identificatore");
				} else
					throw new ParserException("Errore manca l\'espressione");
			} else
				return parseExpVarRight();
		} else
			return parseExpVarRight();

	}// fine parseExpPost

	protected Exp parseExpVarRight() throws Exception {

		Exp id = null;

		if ((curToken != null) && curToken.getID().equals("ident")) {
			id = new IdentValExp(curToken.toString());
			curToken = scan.nextToken();
			while (curToken != null) {
				if (curToken.getID().equals("[")) {// leggo [ accesso ad array
					curToken = scan.nextToken();
					Exp e1 = parseExp();// scansione indice array
					if (e1 != null) {
						if ((curToken != null) && curToken.getID().equals("]")) {
							id = new AccessArrayValExp((ExpVar) id, e1);
							curToken = scan.nextToken();
						} else
							throw new ParserException(
									"Errore manca la parentesi ]");
					} else
						throw new ParserException(
								"Errore manca l\'indice per la posizione dell'array");
				} else if (curToken.getID().equals(".")) {// leggo . accesso
															// propriet� oggetto
					curToken = scan.nextToken();
					if ((curToken != null) && curToken.getID().equals("ident")) {
						PropValExp e1 = new PropValExp(curToken.toString());
						curToken = scan.nextToken();
						id = new AccessPropertyValExp((ExpVar) id, e1);
					} else
						throw new ParserException(
								"Errore manca il nome della propriet� dopo il .");
				} else if (curToken.getID().equals("(")) {// leggo ( chiamata
															// funzione
					curToken = scan.nextToken();
					ListaArgExp l = parseListaArg();
					if ((curToken != null) && curToken.getID().equals(")")) {
						curToken = scan.nextToken();
						if (l != null)
							id = new FunctionCallValExp((ExpVar) id, l);
						else
							id = new FunctionCallValExp((ExpVar) id);
					} else
						throw new ParserException("Errore manca la parentesi )");
				}// non ho pi� un identificatore
				else
					return id;
			}// end while
			return id;
		} else
			return null;// non si � costruito nulla

	}// fine parseExpVarRight

	protected Exp parseExpVarLeft() throws Exception {

		Exp id = null;

		if ((curToken != null) && curToken.getID().equals("ident")) {
			id = new IdentExp(curToken.toString());
			curToken = scan.nextToken();
			while (curToken != null) {
				if (curToken.getID().equals("[")) {// leggo [ accesso ad array
					curToken = scan.nextToken();
					Exp e1 = parseExp();// scansione indice array
					if (e1 != null) {
						if ((curToken != null) && curToken.getID().equals("]")) {
							id = new AccessArrayExp((ExpVar) id, e1);
							curToken = scan.nextToken();
						} else
							throw new ParserException(
									"Errore manca la parentesi ]");
					} else
						throw new ParserException(
								"Errore manca l\'indice per la posizione dell'array");
				} else if (curToken.getID().equals(".")) {// leggo . accesso
															// propriet� oggetto
					curToken = scan.nextToken();
					if ((curToken != null) && curToken.getID().equals("ident")) {
						PropExp e1 = new PropExp(curToken.toString());
						curToken = scan.nextToken();
						id = new AccessPropertyExp((ExpVar) id, e1);
					} else
						throw new ParserException(
								"Errore manca il nome della propriet� dopo il .");
				} else if (curToken.getID().equals("(")) {// leggo ( chiamata
															// funzione
					curToken = scan.nextToken();
					ListaArgExp l = parseListaArg();
					if ((curToken != null) && curToken.getID().equals(")")) {
						curToken = scan.nextToken();
						if (l != null)
							id = new FunctionCallExp((ExpVar) id, l);
						else
							id = new FunctionCallExp((ExpVar) id);
					} else
						throw new ParserException("Errore manca la parentesi )");
				}// non ho pi� un identificatore
				else
					return id;
			}// end while
			return id;
		} else
			return null;// non si � costruito nulla

	}// fine parseExpVarLeft

	protected ListaArgExp parseListaArg() throws Exception {

		if ((curToken != null) && (!(curToken.getID().equals(")")))) {

			Exp e = parseAssign();

			if (e != null) {
				if (curToken != null) {// curToken � null
					if (curToken.getID().equals(",")) {
						curToken = scan.nextToken();
						ListaArgExp succ = parseListaArg();
						if (succ != null)
							return new ListaArgExp(e, succ);
						else
							throw new ParserException(
									"Errore manca l\'argomento");
					} else
						return new ListaArgExp(e);
				}// sono finiti i token
				else
					return new ListaArgExp(e);
			} else
				return null;// non ci sono pi� argomenti
		} else
			return null;// non ci sono argomenti

	}// fine parseListaArg

}// JavascriptParser
