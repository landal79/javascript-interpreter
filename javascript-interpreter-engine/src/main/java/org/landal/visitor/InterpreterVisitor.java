package org.landal.visitor;

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
import org.landal.apt.ExpCond;
import org.landal.apt.ExpSeq;
import org.landal.apt.ForInIstr;
import org.landal.apt.ForIstr;
import org.landal.apt.FunctionCallExp;
import org.landal.apt.FunctionCallValExp;
import org.landal.apt.GreatEqExp;
import org.landal.apt.GreaterExp;
import org.landal.apt.IdentExp;
import org.landal.apt.IdentValExp;
import org.landal.apt.IfIstr;
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
import org.landal.visitor.datatype.Comp;
import org.landal.visitor.datatype.Costruttore;
import org.landal.visitor.datatype.CostruttoreException;
import org.landal.visitor.datatype.Funzione;
import org.landal.visitor.datatype.FunzioneException;
import org.landal.visitor.datatype.IComp;
import org.landal.visitor.datatype.IProp;
import org.landal.visitor.datatype.MinusInfinity;
import org.landal.visitor.datatype.NaN;
import org.landal.visitor.datatype.Null;
import org.landal.visitor.datatype.Oggetto;
import org.landal.visitor.datatype.PlusInfinity;
import org.landal.visitor.datatype.Prop;
import org.landal.visitor.datatype.Undefined;
import org.landal.visitor.datatype.Vettore;

/**
 * Questa classe implementa una inteprete per il linguaggio Javascript. 
 * Tale classe si occupa sia dell'analisi semantica, sia di interpretare le
 * frasi del linguaggio. Per eseguire la valutazione l'interprete si appoggia
 * alla classe <i>AmbienteDef</i> che rappresenta l'astrazione di un ambiente
 * di esecuzione di un linguaggio. 
 * @author  Landini Alex
 * @version 1.0
 */
public class InterpreterVisitor implements IVisitor{     

	/**
	 *queste costanti mi indicano in che ambito mi trovo: globale = GLOBAL o locale = LOCAL
	 */
	private final int  GLOBAL = 0, LOCAL = 1;

	/**
	 *in questo campo c'� un riferimento all'ambiente globale
	 */
	private AmbienteDef environment;

	/**
	 * In questo campo c'� il risultato della valutazione delle funzioni visit.
	 * Di volta in volta conterr� i risultati parziali delle varie valutazioni.
	 */
	private Object value;

	/*
	 *===================================================
	 *         FLAG DI STATO DELL'INTERPRETE
	 *===================================================
	 *
	 * questi flag nel loro insiemi servono all'interprete per sapere in che situazione
	 * si trova e quindi comportarsi di conseguenza
	 */      

	/**
	 *questo campo memorizza il valore dell'espressione ritornato dalla condizione dello switch, che andr� confrontato
	 *in tipo e valore con le clausole case dello switch; teoricamente pu� contenere solo valori di tipo Boolean, Double
	 * o String, altri tipi per la condizione dello switch saranno considerati errati
	 */
	private Object switchExp;

	/**
	 *questo flag mi dice se � gi� stata incontrata una clausola case che verifica la condizione dello switch,
	 *in questo questo caso il flag assume il valore true, e devono essere valutate tutte le clausole case successive
	 *a meno a meno che non si incontri una istruzione break; verr� inizializzato a false;
	 */
	private boolean caseFlag;

	/**
	 *questo flag mi serve per discriminare se mi trovo in ambito globale, oppure se
	 * mi trovo in ambito locale, cio� se sto eseguendo una funzione; se vale GLOBAL allora
	 *mi trovo nell'ambito globale, se vale LOCAL allora mi trovo nell'ambito locale
	 */
	private int ambitoFlag;

	/**
	 *questo flag mi indica quando � possibile definire funzioni annidate, se � true
	 *allora si possono definire funzioni annidate, mentre se � false non si possono
	 *definire funzioni annidate; le funzioni annidate si possono definire soltanto all'interno
	 *del codice globale di primo livello e al codice delle funzioni di primo livello, in tutti gli
	 * altri casi si ha un errore;
	 */
	private boolean funAnnFlag;

	/**
	 *questo flag mi dice che  stata incontrata una istruzione break, quindi non si dovranno
	 *valutare tutte le istruzioni fino al termine del ciclo pi� interno o fino alla terminazione
	 *dello switch; se � true significa che � stata incontrata una istruzione break, mentre se � false
	 *il programma segue il normale flusso di controllo
	 */
	private boolean breakFlag;

	/**
	 *questo flag mi indica che se si incontra un'istruzione break, nel caso abbia valore true, si ha un errore, altrimenti
	 *se ha valore false si prosegue normalmente;
	 */
	private boolean noBreakFlag;

	/**
	 *questo flag mi dice che � stata incontrata una istruzione continue, se ha valore true, oppure che non � stata incontrata
	 *nessuna istruzione continue, se ha valore false, che sar� anche il suo valore iniziale; se tale flag � true, allora se si �
	 *all'interno di un ciclo si salta all'iterazione successiva;
	 */
	private boolean continueFlag;

	/**
	 *questo flag mi indica che se si incontra un'istruzione continue, nel caso abbia valore true, si ha un errore, altrimenti
	 *se ha valore false si prosegue normalmente; l'istruzione continue pu� apparire soltanto all'interno di cicli, se la si
	 *incontra in altri posizioni si ha un errore di sintassi;
	 */
	private boolean noContinueFlag;

	/**
	 *questo flag mi dice che � stata incontrata una istruzione return, se ha valore true, oppure false,
	 *se non � stata incontrata nessuna istruzione return; se ha valore false viene interroto il normale
	 *flusso di esecuzione e si ritorna al chiamante, restituendo un valore se l'istruzione return
	 *lo prevede;
	 */
	private boolean returnFlag;

	/**
	 *questo flag mi indica che se si incontra un'istruzione return, nel caso abbia valore true, si ha un errore, altrimenti
	 *se ha valore false si prosegue normalmente; l'istruzione return pu� apparire soltanto all'interno del corpo di funzione,
	 *altrimenti si ha un errore di sintassi;
	 */
	private boolean noReturnFlag;

	/**
	 *questo flag mi serve nella valutazione di IdentValExp, indica che sto cercando il nome di una funzione
	 *se � true, oppure false se non la sto cercando;
	 */
	private boolean funCallFlag;

	/**
	 * Questo flag mi serve per discriminare nel caso delle funzioni quando si tratta di parametri
	 * e quando si tratta di variabili.
	 */
	private boolean paramFlag;


	/*
	 *================================================================
	 *          COSTRUTTORI DI INTERPRETER VISITOR
	 *================================================================
	 */

	/**
	 * Costrutore del visitor interprete. Inizializza tutte le strutture necessarie 
	 * per compiere la valutazione delle frasi di Javascript.
	 */
	public InterpreterVisitor(){

		// creo l'environment globale
		environment = new AmbienteDef();

		value = null;

		switchExp = null;//non contiene nulla inizialmente

		caseFlag = false;//inizialmente falsa

		ambitoFlag = GLOBAL;//inizializzo ad ambito globale

		funAnnFlag = true;//inizializzo in modo che si possano definire funzioni annidate

		breakFlag = false;//inizializzo la variabile a false, cio� non si � ancora incontrato nessuna istruzione break

		continueFlag = false;//inizialmente falsa

		returnFlag = false;//inizialmente falsa
		// i prossimi tre flag sono impostati a true in quanto non ci possono essere le istruzioni break, continue e return
		//all'inizio dell'interpretazione di un programma, cio� nel main
		noBreakFlag = true;

		noContinueFlag = true;

		noReturnFlag = true;

		funCallFlag = false;//inizialmente non si sta chiamando nessuna funzione

		paramFlag = false;

	}

	/*
	 *====================================================================
	 *      METODI DI SUPPORTO PER LE OPERAZIONI DI VALUTAZIONE
	 *====================================================================
	 */

	/**
	 * Questo metodo privato valuta un'espressione che rappresenza una condizione restituendo
	 * true se il suo valore pu� essere considerato vero, false altrimenti; viene valutato il valore di value
	 * al momento in cui la funzione viene invocata
	 */
	private boolean valutaCond() throws VisitorException{

		if (value instanceof Boolean){

			if (((Boolean) value).booleanValue()) return true;
			else return false;

		}
		else if (value instanceof Double){

			double b = ((Double) value).doubleValue();

			if (b == 0) return false;
			else if (b>0) return true;
			else throw new VisitorException("Errore nella condizione si ha un valore negativo");

		}
		else if (value instanceof String){

			if (((String) value).compareTo("") == 0) return false;
			else return true;

		}
		else if (value instanceof Null) return false;
		else if (value instanceof NaN) return false;
		else if (value instanceof Undefined) return false;
		else throw new VisitorException("Errore nella valutazione della condizione");

	}//fine valutaCond


	/*
	 *================================================================
	 *   METOD VISIT PER LA VALUTAZIONE DELL'APT
	 *================================================================
	 */


	/**
	 * Questo metodo compie la valutazione dell'accesso a una componente di un array nel caso si abbia un l-value; in javascript
	 * � possibile accedere anche agli oggetti come array associativi, quindi la variabile contenuta in value, potr� essere di
	 * tipo Vettore oppure di tipo Oggetto;
	 * @param e rappresenta la categoria sintattica accesso alla componente dell'array come l-value
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit (AccessArrayExp e) throws VisitorException{		

		e.getNomeArray().accept(this);//analizzo il nome dell'array

		/*nel caso il nome dell'array sia un IdentExp, la cui valutazione restituisce solo il nome della variabile
		 *sar� necessario porre in value l'indirizzo della variabile;
		 */
		if (value instanceof String) {

			String v = (String) value;

			if (environment.searchVar(v)) value = environment.getVar(v);
			else throw new VisitorException("Errore l'array a cui si vuole assegnare il valore non esiste");

		}

		//nel caso sia un identificatore complesso
		if (value instanceof IProp) value = ((IProp)value).getVal();
		else if (value instanceof IComp) value = ((IComp)value).getVal();


		if (value instanceof Vettore){// la variabile � un vettore

			Vettore var = (Vettore) value;//memorizza la variabile vettore			

			e.getIndice().accept(this);//valuta l'indice dell'array che dovrebbe essere un'espressione

			if (value instanceof Double) {//value � un double quindi rappresenta l'indice di accesso al vettore

				double j =((Double) value).doubleValue();
				int i = ((Double) value).intValue();
				//verifico se l'indice � un numero intero positivo
				if (((j - i) == 0) && ( i >= 0)) {

					if (var.verifyIndice(i)) value = var.getComp(i);//la componente di indice i esiste
					else {                                                 
						//la componente non esiste, allora la creo e la aggiungo al vettore, il valore della componente � null                                                                                                             
						value = new Comp(i);                                                        
						var.addIComp((IComp) value);                                                        
					}
				}
				else throw new VisitorException("Errore l'indice di accesso all'array non � un intero");
			}
			else throw new VisitorException("Errore nella valutazione dell'indice dell'array non � un numero");
		}
		else if (value instanceof Oggetto){// si sta accedendo all'oggetto come array associativo

			Oggetto var = (Oggetto) value;//memorizza la variabile oggetto

			e.getIndice().accept(this);//valuta l'indice dell'array che dovrebbe essere un'espressione con ritorno una stringa

			if (value instanceof String) {//value � una stringa che rappresenta il nome della propriet� dell'oggetto

				String val = (String) value;

				if (var.verifyProp(val)) value = var.getProp(val);//la propriet� esiste
				else {//la propriet� non esiste, allora la creo e la aggiungio al vettore, il valore della propriet� � null

					var.addProp(val);
					value = var.getProp(val);

				}
			}
			else throw new VisitorException("Errore nella valutazione del nome della propriet�: non � una Stringa");
		}
		else throw new VisitorException("Errore la variabile a cui si sta tentando di accedere non � n� di tipo Array, n� di tipo Oggetto");

	}//fine visit AccessArrayExp

	/**
	 * Questo metodo compie la valutazione dell'accesso alla componente di un array nel caso si voglia un r-value
	 * @param e rappresenta la categoria sintattica accesso alla componente dell'array come r-value
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit (AccessArrayValExp e) throws VisitorException{

		e.getNomeArray().accept(this);//analizzo il nome dell'array

		if (value instanceof Vettore){// la variabile � un vettore

			Vettore var = (Vettore) value;//memorizza la variabile

			e.getIndice().accept(this);//valuta l'indice dell'array che dovrebbe essere un'espressione

			if (value instanceof Double) {//value � un intero quindi rappresenta l'indice di accesso al vettore

				double j = ((Double) value).doubleValue();
				int i = ((Double) value).intValue();
				//verifico se l'indice � un numero intero positivo
				if (((j - i) == 0) && ( i >= 0)) {

					if (var.verifyIndice(i)) value = ((IComp) var.getComp(i)).getVal();//la componente di indice i esiste
					else value = new Undefined();//la componente del vettore non esiste restituisco oggetto non definito

				}
				else throw new VisitorException("Errore di accesso al vettore l\'indice non � un intero");
			}
			else throw new VisitorException("Errore nella valutazione dell'indice dell\'array, non � un numero");
		}
		else if (value instanceof Oggetto){// si sta accedendo all'oggetto come array associativo

			Oggetto var = (Oggetto) value;//memorizza la variabile

			e.getIndice().accept(this);//valuta l'indice dell'array che dovrebbe essere un'espressione

			if (value instanceof String) {//value � una stringa che rappresenta il nome della propriet� dell'oggetto

				String val = (String) value;

				if (var.verifyProp(val)) value = ((IProp) var.getProp(val)).getVal();//la propriet� esiste
				else value = new Undefined();
			}
			else throw new VisitorException("Errore nella valutazione del nome della propriet� dell\'oggetto");
		}
		else throw new VisitorException("Errore nell\'accesso all'array, si sta tentando di accedere a una variabile che non � un array o un oggetto");

	}//fine visit AccessArrayValExp

	/**
	 * Questo metodo compie la valutazione dell'accesso alle propriet� di un oggetto nel caso si abbia un l-value, cio� restituisce
	 * l'indirizzo della componente dell'oggetto;
	 * @param e rappresenta la categoria sintattica accesso alla propriet� dell'oggetto come l-value
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( AccessPropertyExp e ) throws VisitorException{		

		e.getLeft().accept(this);//analizzo il nome dell'oggetto

		if (value instanceof String){ //se ho l'identificatore della variabile

			String v = (String) value;

			if (environment.searchVar(v)) value = environment.getVar(v);
			else throw new VisitorException("Errore l'oggetto a l-value non esiste");

		}
		//nel caso sia un identificatore complesso
		if (value instanceof IProp) value = ((IProp)value).getVal();
		else if (value instanceof IComp) value = ((IComp)value).getVal();

		if (value instanceof Oggetto){

			Oggetto val = (Oggetto) value;			

			e.getRight().accept(this);//valuto il nome della propriet�			

			if (value instanceof String) {				

				String v = (String) value;

				if (val.verifyProp(v)) {
					value = val.getProp(v);//la propriet� esiste					
				}
				else {					
					//la propriet� non esiste, allora la creo e la aggiungio al vettore, il valore della propriet� � null
					val.addProp(v);
					value = val.getProp(v);
				}//fine else
			}
			else throw new VisitorException("Errore nel nome della propriet� dell\'oggetto");
		}
		else throw new VisitorException("Errore la variabile non � un oggetto");

	}//fine visit AccessPropertyExp

	/**
	 * Questo metodo compie la valutazione dell'accesso alle propriet� di un oggetto nel caso di r-value, cio� si vuole il
	 * valore della propriet� dell'oggetto;
	 * @param e rappresenta la categoria sintattica accesso alla propriet� dell'oggetto come r-value
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( AccessPropertyValExp e ) throws VisitorException{

		e.getLeft().accept(this);//analizzo il nome dell'oggetto

		if (value instanceof Oggetto){

			Oggetto val = (Oggetto) value;//memorizzo l'oggetto

			e.getRight().accept(this);//valuto il nome della propriet�

			if (value instanceof String) {

				String v = (String) value;

				if (val.verifyProp(v)) value = ((IProp) val.getProp(v)).getVal();//la propriet� esiste
				else value = new Undefined();//la propriet� non esiste restituisco non definita

			}
			else throw new VisitorException("Errore nella valutazione del nome della propriet� dell'oggetto");
		}
		else throw new VisitorException("Errore la variabile non � un oggetto");

	}//fine visit AccessPropertyValExp

	/**
	 * Questo metodo esegue l'and logico fra due operandi, opera nel seguente modo: se il primo operando
	 * � uno dei valori fra: null, 0, "", undefined o false, allora restituisce uno di questi valori;
	 * altrimenti valuta il secondo operando e restituisce il valore del secondo operando
	 * @param e rappresenta la categoria sintattica 'and' logico
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( AndExp e ) throws VisitorException {

		e.getOper1().accept(this);//valuto il primo operando

		if (value instanceof Boolean){//la valutazione del primo operando ha restituito un Boolean

			boolean op = ((Boolean) value).booleanValue();

			if (op == false) value = new Boolean(false);
			else // il primo operando non � false allora restituisco il secondo
				e.getOper2().accept(this);

		}//fine caso Boolean

		else if (value instanceof String){

			String val = (String) value;

			if (!(val.compareTo("") == 0)) value = new String("");
			else e.getOper2().accept(this);

		}//fine caso String

		else if (value instanceof Double){

			if (((Double) value).doubleValue() == 0) value = new Double(0);
			else e.getOper2().accept(this);

		}//fine caso Double

		else if (value instanceof Null) value = new Null();

		else if (value instanceof Undefined) value = new Undefined();

		else if ((value instanceof Oggetto) || (value instanceof Vettore)) e.getOper2().accept(this);

		else throw new VisitorException("Errore: nell'operatore && operando non valido");

	}//fine visit AndExp

	/**
	 * Questo metodo compie la valutazione dell'operazione di assegnamento; tale metodo verifica se a sinistra ho un
	 * operando valido come l-value, in questo caso valuto r-value e lo assegno a l-value; l'operatore assegnamento
	 * restituisce come risultato r-value;
	 * @param e rappresenta la categoria sintattica 'assegnamento'.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( AssignExp e ) throws VisitorException {

		e.getOper1().accept(this);//valuto l-value, cio� il riferimento dell'ultima parte di identificatore

		if (value instanceof String){//la valutazione dell'l-value ha restituito un identificatore
			//verifico se la variabile esiste

			String val = (String) value;			

			e.getOper2().accept(this);//valuto r-value, cio� il valore della variabile			

			if (environment.searchVar(val)) {//la variabile esiste			

				environment.addVar(val,value,ambitoFlag);

			}//la variabile non esiste allora la dichiaro implicitamente aggiungendola alle variabili globali
			else environment.addVar(val,value,GLOBAL);
		}
		else if (value instanceof IComp){//sto assegnando un valore a una componente di un oggetto

			IComp comp = (IComp) value;

			e.getOper2().accept(this);//valuto r-value, cio� il valore della variabile

			comp.setVal(value);

		}
		else if (value instanceof IProp){//sto assegnando un valore a una propriet� di un oggetto				

			IProp p = (IProp) value;

			e.getOper2().accept(this);//valuto r-value, cio� il valore della variabile

			p.setVal(value);

		}
		else throw new VisitorException("Errore l-value non valido per l'assegnamento");

	}//fine visit AssignExp

	/**
	 * Questo metodo compie la valutazione del blocco di istruzioni, identificato dalle istruzioni racchiuse fra { };
	 * nel blocco non si possono definire funzioni annidate, quindi nel caso nella valutazione dell blocco di
	 * istruzioni si trover� una definizione di funzione verr� restituito un errore;
	 * @param e rappresenta la categoria sintattica blocco di istruzioni.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( BloccoIstr e ) throws VisitorException{

		boolean faf = funAnnFlag;

		funAnnFlag = false;//non si possono definire funzioni

		e.getListaIstr().accept(this);//valuto la lista delle istruzioni

		funAnnFlag = faf;//resetto il flag

	}//fine visit BloccoIstr

	/**
	 * Questo metodo compie la valutazione del tipo di dati Boolean.
	 * @param e rappresenta un valore booleano
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( BooleanoExp e ){

		Boolean b = new Boolean(e.getVal());

		value = b;

	}//fine visit BooelanoExp

	/**
	 * Questo metodo compie la valutazione dell'istruzione break; la valutazione consiste nel porre true il breakFlag,
	 * il quale indica che � stata incontrata un'istruzione break;
	 * @param e rappresenta la categoria sintattica dell'istruzione 'break'.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( BreakIstr e ) throws VisitorException{

		if (noBreakFlag) throw new VisitorException("Errore: l'istruione break non � ammessa in questo ambito");
		else breakFlag = true;

	}//fine visit BreakIstr

	/**
	 * Questo metodo compie la valutazione della clausola case;
	 * @param e rappresenta la categoria sintattica dell'istruzione 'case'.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( CaseIstr e ) throws VisitorException{

		if (switchExp != null) {//nel caso mi trovo all'interno di un costrutto switch			

			Object exp = switchExp;

			if (caseFlag) {//la clausola case che verifica la condizione � gi� stata trovata				

				if (e.getIstr() != null) e.getIstr().accept(this);//valuto l'istruzione della clausola

				caseFlag = true;//reimposto il flag a true nel caso ci sia stata una istruzione switch all'interno che abbia modificato il flag

			}

			else {//caso in cui non sia ancora stata trovata la clausola case che verifica la condizione					

				e.getLabel().accept(this);//valuto l'etichetta della clausola case

				Object label = value;//memorizzo il valore dell'etichetta

				if ((label instanceof Boolean) && (switchExp instanceof Boolean)){//controllo tipo: caso di etichette booleane

					if (((Boolean) label).booleanValue() == ((Boolean) switchExp).booleanValue()) {//verifico la condizione dello switch

						if (e.getIstr() != null)e.getIstr().accept(this);// valuto l'istruzione della clausola

						caseFlag = true;

					}
				}
				else if ((label instanceof Double) && (switchExp instanceof Double)){//controllo tipo: caso di etichette booleane

					double j = ((Double) label).doubleValue();
					int i = ((Double) label).intValue();
					//verifico se l'indice � un numero intero positivo
					if (((j - i) == 0) && ( i >= 0)) {

						if (i == (((Double) switchExp).intValue())) {//verifico la condizione dello switch

							if (e.getIstr() != null) e.getIstr().accept(this);// valuto l'istruzione della clausola

							caseFlag = true;

						}

					}
					else throw new VisitorException("Errore l\'etichetta del case non � un numero intero");

				}
				else if ((label instanceof String) && (switchExp instanceof String)){

					if (!(((String) label).compareTo((String) switchExp) == 0)) {

						if (e.getIstr() != null) e.getIstr().accept(this);//valuto l'istruzione della clausola

						caseFlag = true;

					}

				}
				else throw new VisitorException("Errore: l\'etichetta non � dello stesso tipo di quella restituita dallo switch");
			}

			switchExp = exp;

		}
		else throw new VisitorException("Errore: case non all'interno di un costrutto switch");

	}//fine visit CaseIstr

	/**
	 * Questo metodo valuta l'istruzione continue, semplicemente pone a true il corrispondente continueFlag;
	 * @param e rappresenta la categoria sintattica dell'istruzione 'continue'.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( ContinueIstr e ) throws VisitorException{

		if (noContinueFlag) throw new VisitorException("Errore: istruzione continue non � ammessa in questo ambito");
		else continueFlag = true;

	}//fine visit ContinueIstr

	/**
	 * Questo metodo compie la valutazione del corpo di un costruttore; il corpo di un costruttore � costituito da tante
	 * istruzioni che iniziano con this, che rappresenta il riferimento all'oggetto che correntemente viene creato richiamando
	 * il costruttore;
	 * @param e rappresenta la categoria sintattica corpo di un costruttore.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( CostrIstr e ) throws VisitorException{

		e.getThisIstr().accept(this);//valuto l'istruzione this che mi inizializza una propriet� di un oggetto

		if (e.getSucc() != null) e.getSucc().accept(this);//valuto l'istruzione successiva se esiste

	}//fine visit CostrIstr

	/**
	 * Questo metodo valuta la definizione di un array come letterale, esempio:<br/>
	 * c = [5,6,7,8]<br/>.
	 * @param e rappresenta la categoria sintattica definizione array.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( DefArrayElExp e ) throws VisitorException{		

		Vettore v = new Vettore();//creo il vettore vuoto

		value = v;

		e.getList().accept(this);//valuto le componenti del vettore

		value = v;

	}//fine visit DefArrayElExp

	/**
	 * Questo metodo compie la valutazione dell'istruzione 'default' di javascript.
	 * @param e rappresenta la categoria sintattica dell'istruzione 'default'.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( DefaultIstr e ) throws VisitorException{

		if (switchExp != null){//nel caso ci troviamo all'interno di un costrutto switch

			e.getIstr().accept(this);//valuto l'istruzione della clausola default

		}
		else throw new VisitorException("Errore: l'istruzione default non pu� essere usata al di fuori del costrutto switch");

	}//fine visit DefaultIstr

	/**
	 * Questo metodo compie la valutazione della definizione di un costruttore di javascript.
	 * @param e rappresenta la categoria sintattica difinizione di un costruttore.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( DefCostrIstr e ) throws VisitorException{

		if (funAnnFlag) {

			Costruttore costr = new Costruttore();//creo un'istanza di costruttore

			e.getNomeCostr().accept(this);//valuto il nome del costruttore

			String nomeCostr = (String) value;//memorizzo il nome del costruttore

			value = costr;//in value metto costr cos� posso utilizzare i suoi metodi per riempire il vector di parametri

			if (e.getListaParam() != null) e.getListaParam().accept(this);//valuto la lista dei parametri

			/*il corpo del costruttore � un pezzo di apt che verr� valutato ogni volta che il
			costruttore verr� richiamato*/
			costr.setCorpo(e.getCorpoCostr());

			environment.addVar(nomeCostr, costr, ambitoFlag);//aggiungo il costruttore nella tabella delle variabili nell'ambiente corrente

		}
		else throw new VisitorException("Errore non � possibile definire funzioni in questo ambito");


	}//fine DefCostrIstr

	/**
	 * Questo metodo compie la valutazione della definizione di una funzione come espressione di javascript.
	 * @param e rappresenta la categoria sintattica difinizione di una  funzione come espressione.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( DefFunExp e ) throws VisitorException{		

		Funzione fun = new Funzione();//creo un'istanza di costruttore

		value = fun;//in value metto costr cos� posso utilizzare i suoi metodi per riempire il vector di parametri

		if (e.getListaParam() != null) e.getListaParam().accept(this);//valuto la lista dei parametri		

		/*il corpo del costruttore � un pezzo di apt che verr� valutato ogni volta che il
		costruttore verr� richiamato*/
		fun.setCorpo(e.getCorpoFun());

		value = fun;//passo la funzione in modo che possa essere assegnata al suo nome e messa nella tabella delle variabili

	}//fine visit DefFunExp

	/**
	 * Questo metodo compie la valutazione della definizione di una funzione come istruzione di javascript.
	 * @param e rappresenta la categoria sintattica difinizione di una  funzione come istruzione.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( DefFunIstr e ) throws VisitorException {

		if (funAnnFlag) {//se il flag � attivo significa che si possono definire funzioni			

			Funzione fun = new Funzione();//creo un'istanza di costruttore

			e.getNomeFun().accept(this);//valuto il nome della funzione

			String nomeFun = (String) value;

			value = fun;//in value metto costr cos� posso utilizzare i suoi metodi per riempire il vector di parametri

			if (e.getListaParam() != null) e.getListaParam().accept(this);//valuto la lista dei parametri

			/*il corpo del costruttore � un pezzo di apt che verr� valutato ogni volta che il
			costruttore verr� richiamato*/
			fun.setCorpo(e.getCorpoFun());			

			environment.addVar(nomeFun,fun,ambitoFlag);//aggiungo la variabile alla tabella delle variabili dell'ambiente corrente

		}
		else throw new VisitorException("Errore: non si pu� definire una funzione in questo ambito");

	}//fine visit DefFunIstr

	/**
	 * Questo metodo compie la valutazione della definizione delle propriet� di un
	 * oggetto come espressione di javascript. Esempio: b = {m : 65, l : '56'};
	 * @param e rappresenta la categoria sintattica difinizione di un oggetto come espressione.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( DefPropObjectExp e ) throws VisitorException {

		Oggetto o = new Oggetto();//creo un oggetto vuoto

		value = o;//in value metto il riferimento all'oggetto appena creato

		e.getListProp().accept(this);//valuto la lista delle propriet�

		value = o;

	}//fine visit DefPropObject

	/**
	 * Questo metodo compie la valutazione della definizione esplicita di variabili attraverso
	 * la keyword 'var' di javascript.     
	 * @param e rappresenta la categoria sintattica difinizione esplicita di variabili.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( DefVarExplIstr e ) throws VisitorException{

		e.getListaDef().accept(this);//valuto la lista delle definizioni;

	}//fine visit DefVarExplIstr

	/**
	 * Questo metodo compie la valutazione dell'operatore / (divisione); la sua semantica � la seguente: gli operandi
	 * ammessi sono dei numeri, qualsiasi operando che non � un numero verr� considerato non valido; se il secondo operando
	 * � 0, allora a seconda del segno del primo operando verr� restituito + o - infinito; se si 0/0 si ha un NaN nessun valore;
	 * @param e rappresenta la categoria sintattica operazione di divisione.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( DivExp e ) throws VisitorException{

		e.getOper1().accept(this);//valuto il primo operando

		if (value instanceof Double){//il primo operando � un numero

			double op1 = ((Double) value).doubleValue();

			e.getOper2().accept(this);

			if (value instanceof Double){

				double op2 = ((Double) value).doubleValue();

				if (op2 == 0) {
					if (op1 < 0) value = new MinusInfinity();
					else if (op1 > 0) value = new PlusInfinity();
					else if (op1 == 0) value = new NaN();
				}
				else value = new Double(op1/op2);
			}
			else throw new VisitorException("Errore in / il secondo operando non � un numero");
		}
		else throw new VisitorException("Errore in / il primo operando non � un numero");

	}//fine visit DivExp

	/**
	 * Questo metodo compie la valutazione del ciclo 'do-while' di javascript.
	 * @param e rappresenta la categoria sintattica ciclo 'do-while'.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( DoIstr e ) throws VisitorException{

		boolean nbf = noBreakFlag, ncf = noContinueFlag, fag = funAnnFlag;

		noBreakFlag = noContinueFlag = funAnnFlag = false;

		do{
			e.getCorpo().accept(this);//valuti il corpo del ciclo do-while

			e.getCond().accept(this);//valuto la condizione del ciclo

			if (continueFlag) continueFlag = false;//resetto e faccio compiere un'altra iterazione

			if ((breakFlag) || (returnFlag)) break;//esco dal ciclo

		} while (valutaCond());

		if (breakFlag) breakFlag = false;//resetto il breakFlag

		noBreakFlag = nbf;

		noContinueFlag = ncf;

		funAnnFlag = fag;

	}//fine DoIstr

	/**
	 * La valutazione dell'istruzione vuota non produce alcun effetto, quindi il metodo ha il
	 * corpo vuoto.
	 * @param e rappresenta la categoria sintattica istruzione vuota.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( EmptyIstr e ){}

	/**
	 * Questo metodo compie la valutazione dell'operatore uguaglianza '=='; la sua semantica � la seguente
	 * <ul><li>se i due operandi sono dello stesso tipo, allora possono essere confrontati;</li><li> se si un numero e
	 * un boolean, il boolean viene convertito in numero (true = 1, false = 0); </li><li>se un operando � un numero
	 * e l'altro una stringa, si verifica se la stringa contiene un numero e si confrontano;</li>
	 * <li>se si ha un oggetto � possibile confrontarlo con una stringa, dopo aver convertito l'oggetto in stringa
	 * con toString() si confrontano le stringhe;</li>
	 * <li>qualsiasi altro confronto viene considerato disuguaglianza;</li></ul>
	 * @param e rappresenta la categoria sintattica espressione di uguglianza.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( EqualExp e ) throws VisitorException{

		e.getOper1().accept(this);//valuto il primo operando

		if (value instanceof Double){

			double op1 = ((Double) value).doubleValue();

			e.getOper2().accept(this);

			if (value instanceof Double){

				double op2 = ((Double) value).doubleValue();

				if (op1 == op2) value = new Boolean(true);
				else value = new Boolean(false);

			}
			else if (value instanceof Boolean){

				double op2;

				if (((Boolean) value).booleanValue() == true) op2 = 1;
				else op2 = 0;
				if (op1 == op2) value = new Boolean(true);
				else value = new Boolean(false);

			}
			else if (value instanceof String){

				try{ double op2 = Double.parseDouble((String) value);

				if (op1 == op2) value = new Boolean(true);
				else value = new Boolean(false);

				}
				catch (NumberFormatException e1){

					value = new Boolean(false);

				}

			}
			else if (value instanceof Vettore) value = new Boolean(false);

			else if (value instanceof Oggetto) value = new Boolean(false);

			else if (value instanceof Null) value = new Boolean(false);

			else if (value instanceof Undefined) value = new Boolean(false);
			else throw new VisitorException("Errore operando non valido per il confronto");

		}//fine caso Double
		else if (value instanceof Boolean){

			boolean op1 = ((Boolean) value).booleanValue();

			e.getOper2().accept(this);

			if (value instanceof Double){

				double dop1;

				if (op1 == true) dop1 = 1;
				else dop1 = 0;
				if (dop1 == ((Double) value).doubleValue()) value = new Boolean(true);
				else value = new Boolean(false);

			}
			else if (value instanceof Boolean){

				if (op1 == ((Boolean) value).booleanValue()) value = new Boolean(true);
				else value = new Boolean (false);

			}
			else if (value instanceof String) value = new Boolean(false);

			else if (value instanceof Vettore) value = new Boolean(false);

			else if (value instanceof Oggetto) value = new Boolean(false);

			else if (value instanceof Null) value = new Boolean(false);

			else if (value instanceof Undefined) value = new Boolean(false);

			else throw new VisitorException("Errore operando non valido per il confronto");

		}//fine caso Boolean
		else if (value instanceof String){

			String op1 = (String) value;

			e.getOper2().accept(this);

			if (value instanceof Double){

				try{
					double dop1 = Double.parseDouble((String) op1);

					if (dop1 == ((Double) value).doubleValue()) value = new Boolean(true);
					else value = new Boolean(false);

				}
				catch (NumberFormatException e1){

					value = new Boolean(false);

				}


			}
			else if (value instanceof Boolean) value = new Boolean(false);

			else if (value instanceof String){

				String op2 = (String) value;

				if (op1.compareTo(op2) == 0) value = new Boolean(true);
				else value = new Boolean(false);

			}

			else if (value instanceof Vettore) value = new Boolean(false);

			else if (value instanceof Oggetto) value = new Boolean(false);

			else if (value instanceof Null) value = new Boolean(false);

			else if (value instanceof Undefined) value = new Boolean(false);

			else throw new VisitorException("Errore operando non valido per il confronto");

		}//fine caso String
		else if (value instanceof Vettore){

			Vettore op1 = (Vettore) value;

			e.getOper2().accept(this);

			if (value instanceof Double) value = new Boolean(false);

			else if (value instanceof Boolean) value = new Boolean(false);

			else if (value instanceof String){

				if (op1.toString().compareTo((String) value) == 0) value = new Boolean(true);
				else value = new Boolean(false);

			}
			else if (value instanceof Vettore){

				//qui confronto le istanze dell'oggetto e vedo se sono uguali

				if (op1.toString().compareTo(((Vettore)value).toString())== 0) value = new Boolean(true);
				else value = new Boolean(false);

			}
			else if (value instanceof Oggetto) value = new Boolean(false);

			else if (value instanceof Null) value = new Boolean(false);

			else if (value instanceof Undefined) value = new Boolean(false);

			else throw new VisitorException("Errore operando non valido per il confronto");

		}//fine caso Vettore
		else if (value instanceof Oggetto){

			Oggetto op1 = (Oggetto) value;

			e.getOper2().accept(this);

			if (value instanceof Double) value = new Boolean(false);

			else if (value instanceof Boolean) value = new Boolean(false);

			else if (value instanceof String){

				if (op1.toString().compareTo((String) value) == 0) value = new Boolean(true);
				else value = new Boolean(false);

			}
			else if (value instanceof Vettore) value = new Boolean(false);

			else if (value instanceof Oggetto){

				//qui confronto le due istanze per vedere se sono uguali
				if (op1.toString().compareTo(((Oggetto)value).toString()) == 0) value = new Boolean(true);
				else value = new Boolean(false);

			}
			else if (value instanceof Null) value = new Boolean(false);

			else if (value instanceof Undefined) value = new Boolean(false);

			else throw new VisitorException("Errore operando non valido per il confronto");

		}//fine caso Oggetto
		else if (value instanceof Null){

			e.getOper2().accept(this);

			if (value instanceof Double) value = new Boolean(false);

			else if (value instanceof Boolean) value = new Boolean(false);

			else if (value instanceof String) value = new Boolean(false);

			else if (value instanceof Vettore) value = new Boolean(false);

			else if (value instanceof Oggetto) value = new Boolean(false);

			else if (value instanceof Null) value = new Boolean(true);

			else if (value instanceof Undefined) value = new Boolean(true);

			else throw new VisitorException("Errore operando non valido per il confronto");

		}//fine caso Null
		else if (value instanceof Undefined){

			e.getOper2().accept(this);

			if (value instanceof Double) value = new Boolean(false);

			else if (value instanceof Boolean) value = new Boolean(false);

			else if (value instanceof String) value = new Boolean(false);

			else if (value instanceof Vettore) value = new Boolean(false);

			else if (value instanceof Oggetto) value = new Boolean(false);

			else if (value instanceof Null) value = new Boolean(true);

			else if (value instanceof Undefined) value = new Boolean(false);

			else throw new VisitorException("Errore operando non valido per il confronto");

		}//fine caso Undefined
		else throw new VisitorException("Errore operando non valido per il confronto");

	}//fine visit EqualExp

	/**
	 * Questo metodo compie la valutazione dell'operatore condizionale: cond ?  true : false
	 * @param e rappresenta la categoria sintattica espressione condizionale.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( ExpCond e ) throws VisitorException{

		e.getOper1().accept(this);//valuta la condizione

		if (valutaCond()) e.getOper2().accept(this);//se la condizione � vera
		else e.getOper3().accept(this);//se la condizione � false

	}//fine visit ExpCond

	/**
	 * Questo metodo compie la valutazione di una sequenza di espressioni, tutte separate da una virgola;
	 * @param e rappresenta la categoria sintattica espressione sequenza di espressioni.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( ExpSeq e ) throws VisitorException{

		e.getOper1().accept(this);//valuta la parte a sinistra della virgola

		e.getOper2().accept(this);//valuta la parte a destra della virgola

	}//fine visit ExpSeq

	/**
	 * Questo metodo compie la valutazione del ciclo for/in di javascript;
	 * @param e rappresenta la categoria sintattica ciclo 'for/in'.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( ForInIstr e ) throws VisitorException{

		boolean nbf = noBreakFlag, ncf = noContinueFlag, fag = funAnnFlag;

		noBreakFlag = noContinueFlag = funAnnFlag = false;

		String varCiclo = new String();//inizializzo una stringa nulla come variabile di ciclo, conterr� i nomi delle propriet� dell'oggetto

		if (e.getVar() instanceof DefVarExplIstr){

			if(e.getVar().getListaDef().getSucc() == null) {
				//valuto la variabile di ciclo
				visit(e.getVar().getListaDef());

				visit(e.getVar().getListaDef().getNomeVar());

				environment.addVar((String) value, varCiclo ,ambitoFlag);

				varCiclo = (String) value;

			}
			else throw new VisitorException("Errore di sintassi nel ciclo for/in");

		}
		else throw new VisitorException("Errore di sintassi ciclo for/in");

		e.getObj().accept(this);//valuto l'oggetto       
		if (value instanceof Oggetto) {

			String prop = new String();

			Oggetto obj = (Oggetto) value;              
			while ( (prop = (String) obj.getNomeProp()) != null) {                    
				environment.addVar( varCiclo, prop ,ambitoFlag);

				e.getCorpo().accept(this);

				if ((breakFlag) || (returnFlag)) break;

				if (continueFlag) continueFlag = false;

			}

		}
		else throw new VisitorException("Errore la variabile nel ciclo for/in non � un'oggetto");

		if (breakFlag) breakFlag = false;//resetto il flag

		noBreakFlag = nbf;

		noContinueFlag = ncf;

		funAnnFlag = fag;

	}//fine visit ForInIstr

	/**
	 * Questo metodo compie la valutazione del ciclo 'for' di javascript.
	 * @param e rappresenta la categoria sintattica ciclo 'for'.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( ForIstr e ) throws VisitorException{                         

		boolean nbf = noBreakFlag, ncf = noContinueFlag, fag = funAnnFlag;

		noBreakFlag = noContinueFlag = funAnnFlag = false;

		if (e.getIniz() != null) e.getIniz().accept(this);//inizializzo la variabile di ciclo              

		if (e.getCond() != null) e.getCond().accept(this);//valuto la condizione del ciclo
		else value = new Boolean(true);

		while (valutaCond()) {

			e.getCorpo().accept(this);//eseguo il corpo del ciclo

			if ((breakFlag) || (returnFlag)) break;

			if (continueFlag) continueFlag = false;

			if (e.getInc() != null) e.getInc().accept(this);//incremento la variabile di ciclo

			if (e.getCond() != null) e.getCond().accept(this);//ritorno a valutare la condizione del ciclo
			else value = new Boolean(true);

		}//fine while

		if (breakFlag) breakFlag = false;//resetto breakFlag in quanto sono uscito dal ciclo

		noBreakFlag = nbf;

		noContinueFlag = ncf;

		funAnnFlag = fag;

	}//fine visit ForIstr

	/**
	 * Questo metodo compie la valutazione della chiamata di funzioni come l-value di javascript.
	 * @param e rappresenta la categoria sintattica chiamata di funzione come l-value.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( FunctionCallExp e ) throws VisitorException{

		int ambito = ambitoFlag;

		boolean nrf = noReturnFlag;

		noReturnFlag = false;//si pu� utilizzare l'istruzione return

		funCallFlag = true;//indico che sto invocando una funzione

		e.getNomeFun().accept(this);//valuto il nome della funzione

		funCallFlag = false;

		if (value instanceof String) {

			if ((value = environment.getVar((String) value)) != null) throw new VisitorException("Errore: l\'identificatore della funzione non esiste");

		}
		else if (value instanceof IComp){

			value = ((IComp) value).getVal();

		}
		else if (value instanceof Prop){

			value = ((Prop) value).getVal();

		}

		if (value instanceof Funzione) {

			Funzione fun = (Funzione) value;

			environment.creaRdA(ambitoFlag);

			ambitoFlag = LOCAL;//con la chiamata di funzione l'ambito diventa locale			

			if (e.getListaArg() != null) e.getListaArg().accept(this);//valuto i parametri della funzione			

			visit(fun.getCorpo());//valuto il corpo della funzione

			environment.eliminaRdA();

		}
		else throw new VisitorException("Errore "+ value.toString()+" non � una funzione");

		if (returnFlag) returnFlag = false;

		ambitoFlag = ambito;//ristabilisco l'ambito precedente

		noReturnFlag = nrf;

	}//fine visit FunctionCallExp

	/**
	 * Questo metodo compie la valutazione della chiamata di funzioni come r-value di javascript.
	 * @param e rappresenta la categoria sintattica chiamata di funzione come r-value.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( FunctionCallValExp e ) throws VisitorException{

		int ambito = ambitoFlag;

		boolean nrf = noReturnFlag;

		noReturnFlag = false;

		funCallFlag = true;//indico che sto invocando una funzione

		e.getNomeFun().accept(this);//valuto il nome della funzione

		funCallFlag = false;

		if (value instanceof Funzione) {

			Funzione fun = (Funzione) value;

			environment.creaRdA(ambitoFlag);

			ambitoFlag = LOCAL;//con la chiamata di funzione l'ambito diventa locale

			if (e.getListaArg() != null) e.getListaArg().accept(this);//valuto i parametri della funzione			

			visit(fun.getCorpo());//valuto il corpo della funzione

			environment.eliminaRdA();

		}
		else throw new VisitorException("Errore "+ value.toString()+" non � una funzione");

		if (returnFlag) returnFlag = false;

		ambitoFlag = ambito;//ristabilisco l'ambito precedente

		noReturnFlag = nrf;

	}//fine visit FunctionCallValExp

	/**
	 * Questo metodo compie la valutazione dell'espressione di maggiore uguale '>=' di javascript.
	 * @param e rappresenta la categoria sintattica espressione di maggiore uguale.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( GreatEqExp e ) throws VisitorException {

		e.getOper1().accept(this);//valuto il primo operando

		if (value instanceof Double){

			double op1 = ((Double) value).doubleValue();

			e.getOper2().accept(this);

			if (value instanceof Double){

				double op2 = ((Double) value).doubleValue();

				if (op1 >= op2) value = new Boolean(true);
				else value = new Boolean(false);

			}
			else if (value instanceof Boolean){

				double op2;

				if (((Boolean) value).booleanValue() == true) op2 = 1;
				else op2 = 0;
				if (op1 >= op2) value = new Boolean(true);
				else value = new Boolean(false);

			}
			else if (value instanceof String){

				try{ double op2 = Double.parseDouble((String) value);

				if (op1 >= op2) value = new Boolean(true);
				else value = new Boolean(false);

				}
				catch (NumberFormatException e1){

					value = new Boolean(false);

				}

			}
			else if (value instanceof Vettore) value = new Boolean(false);

			else if (value instanceof Oggetto) value = new Boolean(false);

			else if (value instanceof Null) value = new Boolean(false);

			else if (value instanceof Undefined) value = new Boolean(false);
			else throw new VisitorException("Errore operando non valido per il confronto");

		}//fine caso Double
		else if (value instanceof Boolean){

			double op1;

			if (((Boolean) value).booleanValue()) op1 = 1;
			else op1 = 0;

			e.getOper2().accept(this);

			if (value instanceof Double){

				if (op1 >= ((Double) value).doubleValue()) value = new Boolean(true);
				else value = new Boolean(false);

			}
			else if (value instanceof Boolean){

				double op2;

				if (((Boolean) value).booleanValue()) op2 = 1;
				else op2 = 0;

				if (op1 >= op2) value = new Boolean(true);
				else value = new Boolean (false);

			}
			else if (value instanceof String) value = new Boolean(false);

			else if (value instanceof Vettore) value = new Boolean(false);

			else if (value instanceof Oggetto) value = new Boolean(false);

			else if (value instanceof Null) value = new Boolean(false);

			else if (value instanceof Undefined) value = new Boolean(false);

			else throw new VisitorException("Errore operando non valido per il confronto");

		}//fine caso Boolean
		else if (value instanceof String){

			String op1 = (String) value;

			e.getOper2().accept(this);

			if (value instanceof Double){

				try{ double dop1 = Double.parseDouble((String) op1);

				if (dop1 >= ((Double) value).doubleValue()) value = new Boolean(true);
				else value = new Boolean(false);

				}
				catch (NumberFormatException e1){

					value = new Boolean(false);

				}


			}
			else if (value instanceof Boolean) value = new Boolean(false);

			else if (value instanceof String){

				String op2 = (String) value;

				if (op1.compareTo(op2) == 0) value = new Boolean(true);
				else if (op1.compareTo(op2)>0) value = new Boolean(true);
				else value = new Boolean(false);

			}

			else if (value instanceof Vettore) value = new Boolean(false);

			else if (value instanceof Oggetto) value = new Boolean(false);

			else if (value instanceof Null) value = new Boolean(false);

			else if (value instanceof Undefined) value = new Boolean(false);

			else throw new VisitorException("Errore operando non valido per il confronto");

		}//fine caso String
		else if (value instanceof Vettore) throw new VisitorException("Errore operando per il confronto non valido");

		else if (value instanceof Oggetto) throw new VisitorException("Errore operando per il confronto non valido");

		else if (value instanceof Null) throw new VisitorException("Errore operando per il confronto non valido");

		else if (value instanceof Undefined) throw new VisitorException("Errore operando per il confronto non valido: valore di variabile indefinito");

		else throw new VisitorException("Errore operando non valido per il confronto");

	}//fine visit GreatEqExp

	/**
	 * Questo metodo compie la valutazione dell'espressione di maggiore '>' di javascript.
	 * @param e rappresenta la categoria sintattica espressione di maggiore.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( GreaterExp e ) throws VisitorException{

		e.getOper1().accept(this);//valuto il primo operando

		if (value instanceof Double){

			double op1 = ((Double) value).doubleValue();

			e.getOper2().accept(this);

			if (value instanceof Double){

				double op2 = ((Double) value).doubleValue();

				if (op1 > op2) value = new Boolean(true);
				else value = new Boolean(false);

			}
			else if (value instanceof Boolean){

				double op2;

				if (((Boolean) value).booleanValue() == true) op2 = 1;
				else op2 = 0;
				if (op1 > op2) value = new Boolean(true);
				else value = new Boolean(false);

			}
			else if (value instanceof String){

				try{ double op2 = Double.parseDouble((String) value);

				if (op1 > op2) value = new Boolean(true);
				else value = new Boolean(false);

				}
				catch (NumberFormatException e1){

					value = new Boolean(false);

				}

			}
			else if (value instanceof Vettore) value = new Boolean(false);

			else if (value instanceof Oggetto) value = new Boolean(false);

			else if (value instanceof Null) value = new Boolean(false);

			else if (value instanceof Undefined) value = new Boolean(false);
			else throw new VisitorException("Errore operando non valido per il confronto");

		}//fine caso Double
		else if (value instanceof Boolean){

			double op1;

			if (((Boolean) value).booleanValue()) op1 = 1;
			else op1 = 0;

			e.getOper2().accept(this);

			if (value instanceof Double){

				if (op1 > ((Double) value).doubleValue()) value = new Boolean(true);
				else value = new Boolean(false);

			}
			else if (value instanceof Boolean){

				double op2;

				if (((Boolean) value).booleanValue()) op2 = 1;
				else op2 = 0;

				if (op1 > op2) value = new Boolean(true);
				else value = new Boolean (false);

			}
			else if (value instanceof String) value = new Boolean(false);

			else if (value instanceof Vettore) value = new Boolean(false);

			else if (value instanceof Oggetto) value = new Boolean(false);

			else if (value instanceof Null) value = new Boolean(false);

			else if (value instanceof Undefined) value = new Boolean(false);

			else throw new VisitorException("Errore operando non valido per il confronto");

		}//fine caso Boolean
		else if (value instanceof String){

			String op1 = (String) value;

			e.getOper2().accept(this);

			if (value instanceof Double){

				try{ double dop1 = Double.parseDouble((String) op1);

				if (dop1 > ((Double) value).doubleValue()) value = new Boolean(true);
				else value = new Boolean(false);

				}
				catch (NumberFormatException e1){

					value = new Boolean(false);

				}

			}
			else if (value instanceof Boolean) value = new Boolean(false);

			else if (value instanceof String){

				String op2 = (String) value;

				if (op1.compareTo(op2)>0) value = new Boolean(true);
				else value = new Boolean(false);
			}

			else if (value instanceof Vettore) value = new Boolean(false);

			else if (value instanceof Oggetto) value = new Boolean(false);

			else if (value instanceof Null) value = new Boolean(false);

			else if (value instanceof Undefined) value = new Boolean(false);

			else throw new VisitorException("Errore operando non valido per il confronto");

		}//fine caso String
		else if (value instanceof Vettore) throw new VisitorException("Errore operando per il confronto non valido");

		else if (value instanceof Oggetto) throw new VisitorException("Errore operando per il confronto non valido");

		else if (value instanceof Null) throw new VisitorException("Errore operando per il confronto non valido");

		else if (value instanceof Undefined) throw new VisitorException("Errore operando per il confronto non valido: valore di variabile indefinito");

		else throw new VisitorException("Errore operando non valido per il confronto");

	}//fine visit GreatExp

	/**
	 * Questo metodo compie la valutazione dell'identificatore come l-value di javascript.
	 * @param e rappresenta la categoria sintattica indentificatore come l-value.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit (IdentExp e) throws VisitorException{

		String id = e.getIdName();//prende il nome del costruttore

		value = id;// restituisco il nome della variabile

		if (funCallFlag) {

			if (environment.searchVar(id)) ambitoFlag = environment.getAmbitoFun(id);
			else throw new VisitorException("Errore si sta cercando di accedere ad una variabile che non � stata definita");

		}

	}// fine visit IdentExp

	/**
	 * Questo metodo compie la valutazione dell'identificatore come r-value di javascript.
	 * @param e rappresenta la categoria sintattica indentificatore come r-value.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( IdentValExp e ) throws VisitorException{                       

		if (paramFlag) {

			String id = e.getIdName();

			if (environment.searchParam(id)) value = environment.getParam(id);
			else throw new VisitorException("Errore si sta cercando di accedere ad una variabile non definita");

		}
		else{

			String id = e.getIdName();

			if (environment.searchVar(id)) value = environment.getVar(id);
			else throw new VisitorException("Errore si sta cercando di accedere ad una variabile che non � stata definita");               

			if (funCallFlag) ambitoFlag = environment.getAmbitoFun(id);                

		}

	}//fine visit IdentValExp

	/**
	 * Questo metodo compie la valutazione dell'istruzione di selezione 'if' di javascript.
	 * @param e rappresenta la categoria sintattica istruzione di selezione.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( IfIstr e ) throws VisitorException{

		boolean faf = funAnnFlag;

		funAnnFlag = false;

		e.getCond().accept(this);

		if (valutaCond()) e.getCorpo().accept(this);
		else if (e.getElse() != null) e.getElse().accept(this);//se esiste il ramo else viene valutato

		funAnnFlag = faf;

	}//fine visit IfIstr

	/**
	 * Questo metodo compie la valutazione della lista degli argomenti di una chiamata di una
	 * funzione o di un costruttore;
	 * @param e rappresenta la categoria sintattica lista argomenti di chiamata di una funzione.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( ListaArgExp e ) throws VisitorException{

		boolean pf = paramFlag;

		paramFlag = true;

		if (value instanceof Funzione){

			Funzione fun = (Funzione) value;

			e.getArgVal().accept(this);//valuta il valore dell'argomento

			String idParam;//stringa che conterr� i nomi dei parametri

			if ((idParam = fun.getParam()) != null) {

				environment.addVar(idParam,value, ambitoFlag);//aggiungo il parametro alla lista delle variabili locali

				value = fun;

				if (e.getSucc() != null) e.getSucc().accept(this);
				else fun.resetIndice();//i parametri sono finiti riporto indice a 0

			}// se si va all'else significa che i parametri della funzione sono finiti e gli altri verranno scartati
			else fun.resetIndice();

			paramFlag = pf;

		}
		else if (value instanceof Costruttore){

			Costruttore costr = (Costruttore) value;

			e.getArgVal().accept(this);//valuta il valore dell'argomento

			String idParam;

			if ((idParam = costr.getParam()) != null) {

				environment.addVar(idParam,value,ambitoFlag);//aggiungo il parametro alla lista delle variabili locali

				value = costr;

				if (e.getSucc() != null) e.getSucc().accept(this);
				else costr.resetIndice();//i parametri sono finiti riporto indice a 0

			}// se si va all'else significa che i parametri della funzione sono finiti e gli altri verranno scartati
			else costr.resetIndice();

			paramFlag = pf;

		}
		else throw new VisitorException("Errore nella valutazione degli argomenti di chiamata di funzione");

	}//fine visit ListaArgExp

	/**
	 * Questo metodo compie la valutazione della lista di definizioni di variabili esplicite, cio� che
	 * seguono la parola chiave 'var';
	 * @param e rappresenta la categoria sintattica lista di definizione di variabili.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( ListaDefIstr e ) throws VisitorException {

		e.getNomeVar().accept(this);//valuto il nome della variabile

		String nomeVar = (String) value;

		if(e.getVal() != null) e.getVal().accept(this);//valuto il valore che assumer� la funzione
		else value = new Undefined();

		environment.addVar(nomeVar,value,ambitoFlag);//aggiungo la variabile all'ambiente

		if (e.getSucc() != null) e.getSucc().accept(this);

		value = nomeVar;//alla fine ho il nome della variabile in var

	}//fine visit ListaDefIstr

	/**
	 * Questo metodo compie la valutazione della lista di elementi di un array di javascript.
	 * @param e rappresenta la categoria sintattica lista elementi array.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( ListaElExp e ) throws VisitorException{

		if (value instanceof Vettore){

			Vettore v = (Vettore) value;

			e.getElem().accept(this);//valuto il valore dell'elemento

			v.addComp(value);//aggiungo la componente al vettore appendendola in coda al vettore

			value = v;

			if (e.getSucc() != null) e.getSucc().accept(this);

		}
		else throw new VisitorException("Errore nella valutazione della lista degli elementi dell\'array");

	}//fine visit ListaElExp

	/**
	 * Questo metodo compie la valutazione di una lista di istruzioni contenute in un blocco.
	 * @param e rappresenta la categoria sintattica lista delle istruzioni.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( ListaIstr e ) throws VisitorException{

		if (!(continueFlag || returnFlag || breakFlag)){

			if (switchExp != null){//siamo in un costrutto switch

				if ((caseFlag) || (e.getIstr() instanceof DefaultIstr)) {//il case � gi� stato trovato e valuto tutto

					e.getIstr().accept(this);

					if (e.getSucc() != null) e.getSucc().accept(this);

				}
				else if (e.getIstr() instanceof CaseIstr) {

					e.getIstr().accept(this);

					if (e.getSucc() != null) e.getSucc().accept(this);

				}
				else if (e.getSucc() != null) e.getSucc().accept(this);

			}
			else{

				e.getIstr().accept(this);//valuto l'istruzione

				if (e.getSucc() != null) e.getSucc().accept(this);//valuto l'istruzione successiva

			}

		}

	}//fine visit ListaIstr

	/**
	 *  Questo metodo compie la valutazione della lista di parametri nella definizione della funzione.
	 * @param e rappresenta la categoria sintattica lista parametri di una funzione.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( ListaParamIstr e ) throws VisitorException{

		if (value instanceof Funzione){//sto analizzando i parametri di una funzione

			Funzione fun = (Funzione) value;//assegno la funzione a una variabile di comodo

			e.getIdent().accept(this);//valuto il nome parametro		

			try {
				fun.addParam((String) value);//aggiungo il parametro a quelli della funzione
			}
			catch (FunzioneException f){

				throw new VisitorException(f.getMessage());

			}		

			value = fun;

			//verifico se la lista di parametri � finita
			if (e.getSuccParam() != null) e.getSuccParam().accept(this);		

		}
		else if (value instanceof Costruttore){//sto analizzando i parametri di un costruttore

			Costruttore costr = (Costruttore) value;//assegno il costruttore a una variabile di comodo

			e.getIdent().accept(this);//valuto il parametro

			try {
				costr.addParam((String) value);//aggiungo il parametro a quelli della funzione
			}
			catch (CostruttoreException f){

				throw new VisitorException(f.getMessage());

			}

			value = costr;

			//verifico se la lista di parametri � finita
			if (e.getSuccParam() != null) e.getSuccParam().accept(this);

		}

	}//fine visit ListaParamIstr

	/**
	 * Questo metodo compie la valutazione della lista delle propriet� di un oggetto creato come espressione di javascript.
	 * @param e rappresenta la categoria sintattica lista propriet� oggetto creato come espressione.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( ListaPropExp e ) throws VisitorException{

		if (value instanceof Oggetto) {

			Oggetto obj = (Oggetto) value;

			e.getNomeProp().accept(this);//valuto il nome della propriet�

			String nomeProp = (String) value;

			e.getPropVal().accept(this);//valuto il valore della propriet�

			obj.addProp(nomeProp,value);//aggiungo la propriet� all'oggetto

			value = obj;

			if (e.getNextProp() != null) e.getNextProp().accept(this);

		}
		else throw new VisitorException("Errore nell'interpretazione della lista delle propriet�");

	}//fine visit ListaPropExp

	/**
	 * Questo metodo compie la valutazione dell'espressione di minore uguagale '<=' di javascript.
	 * @param e rappresenta la categoria sintattica espressione di minore uguale.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( LowEqExp e ) throws VisitorException{

		e.getOper1().accept(this);//valuto il primo operando

		if (value instanceof Double){

			double op1 = ((Double) value).doubleValue();

			e.getOper2().accept(this);

			if (value instanceof Double){

				double op2 = ((Double) value).doubleValue();

				if (op1 <= op2) value = new Boolean(true);
				else value = new Boolean(false);

			}
			else if (value instanceof Boolean){

				double op2;

				if (((Boolean) value).booleanValue() == true) op2 = 1;
				else op2 = 0;
				if (op1 <= op2) value = new Boolean(true);
				else value = new Boolean(false);

			}
			else if (value instanceof String){

				try{ double op2 = Double.parseDouble((String) value);

				if (op1 <= op2) value = new Boolean(true);
				else value = new Boolean(false);

				}
				catch (NumberFormatException e1){

					value = new Boolean(false);

				}

			}
			else if (value instanceof Vettore) value = new Boolean(false);

			else if (value instanceof Oggetto) value = new Boolean(false);

			else if (value instanceof Null) value = new Boolean(false);

			else if (value instanceof Undefined) value = new Boolean(false);
			else throw new VisitorException("Errore operando non valido per il confronto");

		}//fine caso Double
		else if (value instanceof Boolean){

			double op1;

			if (((Boolean) value).booleanValue()) op1 = 1;
			else op1 = 0;

			e.getOper2().accept(this);

			if (value instanceof Double){

				if (op1 <= ((Double) value).doubleValue()) value = new Boolean(true);
				else value = new Boolean(false);

			}
			else if (value instanceof Boolean){

				double op2;

				if (((Boolean) value).booleanValue()) op2 = 1;
				else op2 = 0;

				if (op1 <= op2) value = new Boolean(true);
				else value = new Boolean (false);

			}
			else if (value instanceof String) value = new Boolean(false);

			else if (value instanceof Vettore) value = new Boolean(false);

			else if (value instanceof Oggetto) value = new Boolean(false);

			else if (value instanceof Null) value = new Boolean(false);

			else if (value instanceof Undefined) value = new Boolean(false);

			else throw new VisitorException("Errore operando non valido per il confronto");

		}//fine caso Boolean
		else if (value instanceof String){

			String op1 = (String) value;

			e.getOper2().accept(this);

			if (value instanceof Double){

				try{ double dop1 = Double.parseDouble((String) op1);

				if (dop1 <= ((Double) value).doubleValue()) value = new Boolean(true);
				else value = new Boolean(false);

				}
				catch (NumberFormatException e1){

					value = new Boolean(false);

				}


			}
			else if (value instanceof Boolean) value = new Boolean(false);

			else if (value instanceof String){

				String op2 = (String) value;

				if (op1.compareTo(op2) == 0) value = new Boolean(true);
				else if (op1.compareTo(op2)<0) value = new Boolean(true);
				else value = new Boolean(false);
			}

			else if (value instanceof Vettore) value = new Boolean(false);

			else if (value instanceof Oggetto) value = new Boolean(false);

			else if (value instanceof Null) value = new Boolean(false);

			else if (value instanceof Undefined) value = new Boolean(false);

			else throw new VisitorException("Errore operando non valido per il confronto");

		}//fine caso String
		else if (value instanceof Vettore) throw new VisitorException("Errore operando per il confronto non valido");

		else if (value instanceof Oggetto) throw new VisitorException("Errore operando per il confronto non valido");

		else if (value instanceof Null) throw new VisitorException("Errore operando per il confronto non valido");

		else if (value instanceof Undefined) throw new VisitorException("Errore operando per il confronto non valido: valore di variabile indefinito");

		else throw new VisitorException("Errore operando non valido per il confronto");

	}//fine visit LowEqExp

	/**
	 * Questo metodo compie la valutazione dell'espressione di minore  '<' di javascript.
	 * @param e rappresenta la categoria sintattica espressione di minore.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( LowerExp e ) throws VisitorException{

		e.getOper1().accept(this);//valuto il primo operando

		if (value instanceof Double){

			double op1 = ((Double) value).doubleValue();

			e.getOper2().accept(this);

			if (value instanceof Double){

				double op2 = ((Double) value).doubleValue();

				if (op1 < op2) value = new Boolean(true);
				else value = new Boolean(false);

			}
			else if (value instanceof Boolean){

				double op2;

				if (((Boolean) value).booleanValue() == true) op2 = 1;
				else op2 = 0;
				if (op1 < op2) value = new Boolean(true);
				else value = new Boolean(false);

			}
			else if (value instanceof String){

				try{ double op2 = Double.parseDouble((String) value);

				if (op1 < op2) value = new Boolean(true);
				else value = new Boolean(false);

				}
				catch (NumberFormatException e1){

					value = new Boolean(false);

				}

			}
			else if (value instanceof Vettore) value = new Boolean(false);

			else if (value instanceof Oggetto) value = new Boolean(false);

			else if (value instanceof Null) value = new Boolean(false);

			else if (value instanceof Undefined) value = new Boolean(false);
			else throw new VisitorException("Errore operando non valido per il confronto");

		}//fine caso Double
		else if (value instanceof Boolean){

			double op1;

			if (((Boolean) value).booleanValue()) op1 = 1;
			else op1 = 0;

			e.getOper2().accept(this);

			if (value instanceof Double){

				if (op1 < ((Double) value).doubleValue()) value = new Boolean(true);
				else value = new Boolean(false);

			}
			else if (value instanceof Boolean){

				double op2;

				if (((Boolean) value).booleanValue()) op2 = 1;
				else op2 = 0;

				if (op1 < op2) value = new Boolean(true);
				else value = new Boolean (false);

			}
			else if (value instanceof String) value = new Boolean(false);

			else if (value instanceof Vettore) value = new Boolean(false);

			else if (value instanceof Oggetto) value = new Boolean(false);

			else if (value instanceof Null) value = new Boolean(false);

			else if (value instanceof Undefined) value = new Boolean(false);

			else throw new VisitorException("Errore operando non valido per il confronto");

		}//fine caso Boolean
		else if (value instanceof String){

			String op1 = (String) value;

			e.getOper2().accept(this);

			if (value instanceof Double){

				try{ double dop1 = Double.parseDouble((String) op1);

				if (dop1 < ((Double) value).doubleValue()) value = new Boolean(true);
				else value = new Boolean(false);

				}
				catch (NumberFormatException e1){

					value = new Boolean(false);

				}


			}
			else if (value instanceof Boolean) value = new Boolean(false);

			else if (value instanceof String){

				String op2 = (String) value;

				if (op1.compareTo(op2)<0) value = new Boolean(true);
				else value = new Boolean(false);

			}

			else if (value instanceof Vettore) value = new Boolean(false);

			else if (value instanceof Oggetto) value = new Boolean(false);

			else if (value instanceof Null) value = new Boolean(false);

			else if (value instanceof Undefined) value = new Boolean(false);

			else throw new VisitorException("Errore operando non valido per il confronto");

		}//fine caso String
		else if (value instanceof Vettore) throw new VisitorException("Errore operando per il confronto non valido");

		else if (value instanceof Oggetto) throw new VisitorException("Errore operando per il confronto non valido");

		else if (value instanceof Null) throw new VisitorException("Errore operando per il confronto non valido");

		else if (value instanceof Undefined) throw new VisitorException("Errore operando per il confronto non valido: valore di variabile indefinito");

		else throw new VisitorException("Errore operando non valido per il confronto");

	}//fine visit LowerExp

	/**
	 * Questo metodo compie la valutazione dell'operazione sottrazione di javascript.
	 * @param e rappresenta la categoria sintattica operazione di sottrazione.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( MinusExp e ) throws VisitorException{

		e.getOper1().accept(this);//valuto il primo operando

		if (value instanceof Double){//il primo operando � un numero

			double op1 = ((Double) value).doubleValue();

			e.getOper2().accept(this);

			if (value instanceof Double){

				double op2 = ((Double) value).doubleValue();

				value = new Double(op1-op2);

			}
			else throw new VisitorException("Errore: in - il secondo operando non � un numero");

		}
		else throw new VisitorException("Errore: in - il primo operando non � un numero");

	}//fine visit MinusExp

	/**
	 * Questo metodo compie la valutazione dell'operazione resto della divisione fra interi di javascript.
	 * @param e rappresenta la categoria sintattica operazione di resto della divisione fra interi.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( ModExp e ) throws VisitorException{

		e.getOper1().accept(this);//valuto il primo operando

		if (value instanceof Double){//il primo operando � un numero

			int op1 = ((Double) value).intValue();

			double o = ((Double) value).doubleValue();

			if ((o-op1) == 0) {

				e.getOper2().accept(this);

				if (value instanceof Double){

					int op2 = ((Double) value).intValue();

					o = ((Double) value).doubleValue();

					if ((o-op2) == 0) {

						if (op2 == 0) throw new VisitorException("Errore: divisione per 0");
						else value = new Double(op1%op2);

					}
					else throw new VisitorException("Errore: in % il secondo operando non � un numero intero");
				}
				else throw new VisitorException("Errore: in % il secondo operando non � un numero");
			}
			else throw new VisitorException("Errore: in % il primo operando non � un numero intero");
		}
		else throw new VisitorException("Errore: in % il primo operando non � un numero");

	}//fine visit ModExp


	/**
	 * Questo metodo compie la valutazione dell'operazione moltiplicazione di javascript.
	 * @param e rappresenta la categoria sintattica operazione di moltiplicazione.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( MulExp e ) throws VisitorException{

		e.getOper1().accept(this);//valuto il primo operando

		if (value instanceof Double){//il primo operando � un numero

			double op1 = ((Double) value).doubleValue();

			e.getOper2().accept(this);

			if (value instanceof Double){

				double op2 = ((Double) value).doubleValue();

				value = new Double(op1*op2);

			}
			else throw new VisitorException("Errore: in * il secondo operando non � un numero");

		}
		else throw new VisitorException("Errore: in * il primo operando non � un numero");

	}//fine visit MulExp

	/**
	 * Questo metodo compie la valutazione dell'operatore di creazione di oggetti 'new' di javascript.
	 * @param e rappresenta la categoria sintattica operazione di creazione oggetti.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( NewExp e ) throws VisitorException{

		int ambito = ambitoFlag;

		funCallFlag = true;//indico che sto invocando una funzione

		e.getNomeCostr().accept(this);//valuto il nome del costruttore

		funCallFlag = false;

		if (value instanceof Costruttore){

			Costruttore costr = (Costruttore) value;

			environment.creaRdA(ambitoFlag);

			ambitoFlag = LOCAL;//con la chiamata di funzione l'ambito diventa locale

			if (e.getListaArg() != null) e.getListaArg().accept(this);//valuto i parametri del costruttore

			Oggetto obj = new Oggetto();//creo un nuovo oggetto vuoto che il corpo del costr inizializzer�

			value = obj;

			costr.getCorpo().accept(this);;//valuto il corpo del costruttore

			environment.eliminaRdA();

			value = obj;

		}
		else throw new VisitorException("Errore: "+value.toString()+" non � un costruttore");

		ambitoFlag = ambito;//ristabilisco l'ambito precedente

	}//fine visit NewExp

	/**
	 * Questo metodo compie la valutazione dell'espressione di disuguglianza '!=' di javascript.
	 * @param e rappresenta la categoria sintattica espressione di disuguglianza.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( NotEqualExp e ) throws VisitorException{

		e.getOper1().accept(this);//valuto il primo operando

		if (value instanceof Double){

			double op1 = ((Double) value).doubleValue();

			e.getOper2().accept(this);

			if (value instanceof Double){

				double op2 = ((Double) value).doubleValue();

				if (op1 != op2) value = new Boolean(true);
				else value = new Boolean(false);

			}
			else if (value instanceof Boolean){

				double op2;

				if (((Boolean) value).booleanValue() == true) op2 = 1;
				else op2 = 0;
				if (op1 != op2) value = new Boolean(true);
				else value = new Boolean(false);

			}
			else if (value instanceof String){

				try{ double op2 = Double.parseDouble((String) value);

				if (op1 != op2) value = new Boolean(true);
				else value = new Boolean(false);

				}
				catch (NumberFormatException e1){

					value = new Boolean(false);

				}

			}
			else if (value instanceof Vettore) value = new Boolean(false);

			else if (value instanceof Oggetto) value = new Boolean(false);

			else if (value instanceof Null) value = new Boolean(false);

			else if (value instanceof Undefined) value = new Boolean(false);
			else throw new VisitorException("Errore operando non valido per il confronto");

		}//fine caso Double
		else if (value instanceof Boolean){

			boolean op1 = ((Boolean) value).booleanValue();

			e.getOper2().accept(this);

			if (value instanceof Double){

				double dop1;

				if (op1 == true) dop1 = 1;
				else dop1 = 0;
				if (dop1 != ((Double) value).doubleValue()) value = new Boolean(true);
				else value = new Boolean(false);

			}
			else if (value instanceof Boolean){

				if (op1 != ((Boolean) value).booleanValue()) value = new Boolean(true);
				else value = new Boolean (false);

			}
			else if (value instanceof String) value = new Boolean(false);

			else if (value instanceof Vettore) value = new Boolean(false);

			else if (value instanceof Oggetto) value = new Boolean(false);

			else if (value instanceof Null) value = new Boolean(false);

			else if (value instanceof Undefined) value = new Boolean(false);

			else throw new VisitorException("Errore operando non valido per il confronto");

		}//fine caso Boolean
		else if (value instanceof String){

			String op1 = (String) value;

			e.getOper2().accept(this);

			if (value instanceof Double){

				try{ double dop1 = Double.parseDouble((String) op1);

				if (dop1 != ((Double) value).doubleValue()) value = new Boolean(true);
				else value = new Boolean(false);

				}
				catch (NumberFormatException e1){

					value = new Boolean(false);

				}


			}
			else if (value instanceof Boolean) value = new Boolean(false);

			else if (value instanceof String){

				String op2 = (String) value;

				if (op1.compareTo(op2) != 0) value = new Boolean(true);
				else value = new Boolean(false);

			}

			else if (value instanceof Vettore) value = new Boolean(false);

			else if (value instanceof Oggetto) value = new Boolean(false);

			else if (value instanceof Null) value = new Boolean(false);

			else if (value instanceof Undefined) value = new Boolean(false);

			else throw new VisitorException("Errore operando non valido per il confronto");

		}//fine caso String
		else if (value instanceof Vettore){

			Vettore op1 = (Vettore) value;

			e.getOper2().accept(this);

			if (value instanceof Double) value = new Boolean(false);

			else if (value instanceof Boolean) value = new Boolean(false);

			else if (value instanceof String){

				if (op1.toString().compareTo((String) value) != 0) value = new Boolean(true);
				else value = new Boolean(false);

			}
			else if (value instanceof Vettore){

				//qui confronto le istanze dell'oggetto e vedo se sono uguali

				if (op1.toString().compareTo(((Vettore)value).toString()) != 0) value = new Boolean(true);
				else value = new Boolean(false);

			}
			else if (value instanceof Oggetto) value = new Boolean(false);

			else if (value instanceof Null) value = new Boolean(false);

			else if (value instanceof Undefined) value = new Boolean(false);

			else throw new VisitorException("Errore operando non valido per il confronto");

		}//fine caso Vettore
		else if (value instanceof Oggetto){

			Oggetto op1 = (Oggetto) value;

			e.getOper2().accept(this);

			if (value instanceof Double) value = new Boolean(false);

			else if (value instanceof Boolean) value = new Boolean(false);

			else if (value instanceof String){

				if (op1.toString().compareTo((String) value) != 0) value = new Boolean(true);
				else value = new Boolean(false);

			}
			else if (value instanceof Vettore) value = new Boolean(false);

			else if (value instanceof Oggetto){

				//qui confronto le due istanze per vedere se sono uguali
				if (op1.toString().compareTo(((Oggetto)value).toString()) != 0) value = new Boolean(true);
				else value = new Boolean(false);

			}
			else if (value instanceof Null) value = new Boolean(false);

			else if (value instanceof Undefined) value = new Boolean(false);

			else throw new VisitorException("Errore operando non valido per il confronto");

		}//fine caso Oggetto
		else if (value instanceof Null){

			e.getOper2().accept(this);

			if (value instanceof Double) value = new Boolean(false);

			else if (value instanceof Boolean) value = new Boolean(false);

			else if (value instanceof String) value = new Boolean(false);

			else if (value instanceof Vettore) value = new Boolean(false);

			else if (value instanceof Oggetto) value = new Boolean(false);

			else if (value instanceof Null) value = new Boolean(true);

			else if (value instanceof Undefined) value = new Boolean(true);

			else throw new VisitorException("Errore operando non valido per il confronto");

		}//fine caso Null
		else if (value instanceof Undefined){

			e.getOper2().accept(this);

			if (value instanceof Double) value = new Boolean(false);

			else if (value instanceof Boolean) value = new Boolean(false);

			else if (value instanceof String) value = new Boolean(false);

			else if (value instanceof Vettore) value = new Boolean(false);

			else if (value instanceof Oggetto) value = new Boolean(false);

			else if (value instanceof Null) value = new Boolean(true);

			else if (value instanceof Undefined) value = new Boolean(false);

			else throw new VisitorException("Errore operando non valido per il confronto");

		}//fine caso Undefined
		else throw new VisitorException("Errore operando non valido per il confronto");

	}//fine visit NotEqualExp

	/**
	 * Questo metodo compie la valutazione dell'espressione di negazione '!' di javascript.
	 * @param e rappresenta la categoria sintattica espressione di negazione.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( NotExp e ) throws VisitorException{

		e.getOper1().accept(this);//valuto il primo operando

		if (value instanceof Double) {

			if (((Double) value).doubleValue() > 0) value = new Boolean(false);
			else if (((Double) value).doubleValue() == 0) value = new Boolean(true);
			else throw new VisitorException("l'operando del ! non � convertibile in un valore booleano");

		}
		else if (value instanceof Boolean){

			if (((Boolean) value).booleanValue()) value = new Boolean(false);
			else value = new Boolean(true);

		}
		else if (value instanceof String) {

			if (((String) value).compareTo("") == 0) value = new Boolean(true);
			else value = new Boolean(false);

		}
		else if ((value instanceof Undefined) || (value instanceof Null)) value = new Boolean(true);
		else throw new VisitorException("Errore operatore non valido per !");


	}//fine visit NotExp

	/**
	 * Questo metodo compie la valutazione del valore 'null' di javascript.
	 * @param e rappresenta la categoria sintattica valore 'null'.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( NullExp e ){

		value = new Null();

	}//fine visit NullExp

	/**
	 * Questo metodo compie la valutazione dei numeri di javascript.
	 * @param e rappresenta la categoria sintattica numero.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( NumExp e ){

		value = new Double(e.getVal());

	}//fine visit NumExp

	/**
	 * Questo metodo compie la valutazione dell'espressione relazionale or logico '||' di javascript.
	 * @param e rappresenta la categoria sintattica espressione relazionale or logico.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( OrExp e ) throws VisitorException{

		e.getOper1().accept(this);

		if (value instanceof Boolean){//la valutazione del primo operando ha restituito un Boolean

			boolean op = ((Boolean) value).booleanValue();

			if (op == true) value = new Boolean(true);
			else // il primo operando non � false allora restituisco il secondo
				e.getOper2().accept(this);

		}//fine caso Boolean

		else if (value instanceof String){

			String val = (String) value;

			if (!(val.compareTo("") == 0)) e.getOper2().accept(this);
			//altrimenti in value rimane quello che c'era gi�

		}//fine caso String

		else if (value instanceof Double){

			if (((Double) value).intValue() == 0) e.getOper2().accept(this);

		}//fine caso Double

		else if ((value instanceof Null) || (value instanceof Undefined)){

			e.getOper2().accept(this);

		}// nel caso in value vi sia o Null o Undefined rimarr� tale valore

		else throw new VisitorException("Errore: operando non valido per l'operatore ||");


	}//fine visit OrExp

	/**
	 * Questo metodo compie la valutazione dell'operazione somma di javascript.
	 * @param e rappresenta la categoria sintattica operazione di somma.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( PlusExp e ) throws VisitorException{

		e.getOper1().accept(this);//valuto il primo operando

		if (value instanceof Double){//il primo operando � un numero

			double op1 = ((Double) value).doubleValue();

			e.getOper2().accept(this);

			if (value instanceof Double){

				double op2 = ((Double) value).doubleValue();

				value = new Double(op1+op2);

			}
			else if (value instanceof String){//il secondo operando � una stringa

				String op2 = (String) value;

				value = new String(op1+op2);

			}
			else throw new VisitorException("Errore operendo non valido nell\'operatore +");
		}
		else if (value instanceof String){//concatenazione di stringhe

			String op1 = (String) value;

			e.getOper2().accept(this);//valuto il secondo operando

			value = new String(op1 + value);

		}
		else throw new VisitorException("Errore: il primo operando di + non � valido");

	}//fine visit PlusExp

	/**
	 * Questo metodo compie la valutazione dell'operazione post-decremento di javascript.
	 * @param e rappresenta la categoria sintattica operazione post-decremento.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( PostDecExp e ) throws VisitorException{

		e.getOper1().accept(this);//valuto il primo operando che deve essere un l-value valido

		if (value instanceof String){

			String nomeVar = (String) value;

			if (environment.searchVar(nomeVar)) value = environment.getVar(nomeVar);

			if (value instanceof Double){

				environment.addVar(nomeVar,new Double(((Double) value).doubleValue()-1), ambitoFlag);

			}
			else throw new VisitorException("Errore: l'operando di -- post-decremento non � valido");

		}
		else if (value instanceof IProp){

			IProp p = (IProp) value;

			value = p.getVal();

			if (value instanceof Double){

				p.setVal(new Double(((Double) value).doubleValue()-1));

			}
			else throw new VisitorException("Errore: l'operando di -- post-decremento non � valido");

		}
		else if (value instanceof IComp){

			IComp c = (IComp) value;

			value = c.getVal();

			if (value instanceof Double){

				c.setVal(new Double(((Double) value).doubleValue()-1));

			}
			else throw new VisitorException("Errore: l'operando di -- post-decremento non � valido");

		}
		else throw new VisitorException("Errore: l'operando di -- post-decremento non � valido");

	}//fine visit PostDecExp

	/**
	 * Questo metodo compie la valutazione dell'operazione post-incremento di javascript.
	 * @param e rappresenta la categoria sintattica operazione post-incremento.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( PostIncExp e ) throws VisitorException{                         

		e.getOper1().accept(this);//valuto il primo operando che deve essere un l-value valido

		if (value instanceof String){

			String nomeVar = (String) value;

			if (environment.searchVar(nomeVar)) value = environment.getVar(nomeVar);

			if (value instanceof Double){

				environment.addVar(nomeVar,new Double(((Double) value).doubleValue()+1), ambitoFlag);

			}
			else throw new VisitorException("Errore: l'operando di ++ post-incremento non � valido");

		}
		else if (value instanceof IProp){

			IProp p = (IProp) value;

			value = p.getVal();

			if (value instanceof Double){

				p.setVal(new Double(((Double) value).doubleValue()+1));

			}
			else throw new VisitorException("Errore: l'operando di ++ post-incremento non � valido");

		}
		else if (value instanceof IComp){

			IComp c = (IComp) value;

			value = c.getVal();

			if (value instanceof Double){

				c.setVal(new Double(((Double) value).doubleValue()+1));

			}
			else throw new VisitorException("Errore: l'operando di ++ post-incremento non � valido");

		}
		else throw new VisitorException("Errore l'operando di ++ post-incremento non � valido");

	}//fine visit PostIncExp

	/**
	 * Questo metodo compie la valutazione dell'operazione pre-decremento di javascript.
	 * @param e rappresenta la categoria sintattica operazione pre-decremento.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( PreDecExp e ) throws VisitorException{

		e.getOper1().accept(this);//valuto il primo operando che deve essere un l-value valido

		if (value instanceof String){

			String nomeVar = (String) value;

			if (environment.searchVar(nomeVar)) value = environment.getVar(nomeVar);

			if (value instanceof Double){

				value = new Double(((Double) value).doubleValue()-1);

				environment.addVar(nomeVar, value, ambitoFlag);

			}
			else throw new VisitorException("Errore l'operando di -- pre-decremento non � valido");

		}
		else if (value instanceof IProp){

			IProp p = (IProp) value;

			value = p.getVal();

			if (value instanceof Double){

				value = new Double(((Double) value).doubleValue()-1);

				p.setVal(value);

			}
			else throw new VisitorException("Errore l'operando di -- pre-decremento non � valido");

		}
		else if (value instanceof IComp){

			IComp c = (IComp) value;

			value = c.getVal();

			if (value instanceof Double){

				value = new Double(((Double) value).doubleValue()-1);

				c.setVal(value);

			}
			else throw new VisitorException("Errore l'operando di -- pre-decremento non � valido");

		}
		else throw new VisitorException("Errore l'operando di -- pre-decremento non � valido");

	}//fine visit PreDecExp

	/**
	 * Questo metodo compie la valutazione dell'operazione pre-incremento di javascript.
	 * @param e rappresenta la categoria sintattica operazione pre-incremento.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( PreIncExp e ) throws VisitorException{

		e.getOper1().accept(this);//valuto il primo operando che deve essere un l-value valido

		if (value instanceof String){

			String nomeVar = (String) value;

			if (environment.searchVar(nomeVar)) value = environment.getVar(nomeVar);

			if (value instanceof Double){

				value = new Double(((Double) value).doubleValue()+1);

				environment.addVar(nomeVar, value, ambitoFlag);

			}
			else throw new VisitorException("Errore l'operando di ++ pre-decremento non � valido");

		}
		else if (value instanceof IProp){

			IProp p = (IProp) value;

			value = p.getVal();

			if (value instanceof Double){

				value = new Double(((Double) value).doubleValue()+1);

				p.setVal(value);

			}
			else throw new VisitorException("Errore l'operando di ++ pre-decremento non � valido");

		}
		else if (value instanceof IComp){

			IComp c = (IComp) value;

			value = c.getVal();

			if (value instanceof Double){

				value = new Double(((Double) value).doubleValue()+1);

				c.setVal(value);

			}
			else throw new VisitorException("Errore l'operando di ++ pre-decremento non � valido");

		}
		else throw new VisitorException("Errore l'operando di ++ pre-decremento non � valido");

	}//fine visit PreIncExp

	/**
	 * Questo metodo compie la valutazione di una propriet� di un oggetto come l-value di javascript.
	 * @param e rappresenta la categoria sintattica propriet� oggetto come l-value.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit (PropExp e){

		String id = e.getIdName();

		value = id;//restituisce il nome della propriet�

	}//fine visit PropExp e

	/**
	 * Questo metodo compie la valutazione di una propriet� di un oggetto come r-value di javascript.
	 * @param e rappresenta la categoria sintattica propriet� oggetto come r-value.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit (PropValExp e){

		String id = e.getIdName();

		value = id;//restituisce il nome della propriet�

	}//fine visit PropValExp

	/**
	 * Questo metodo compie la valutazione dell'istruzione 'return' di javascript.
	 * @param e rappresenta la categoria sintattica dell'istruzione 'return'.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( ReturnIstr e ) throws VisitorException{

		if (noReturnFlag) throw new VisitorException("Errore: non si pu� utilizzare l'istruzione return in questo ambito");
		else{

			if (e.getRetVal() != null) e.getRetVal().accept(this);//valuto l'exp in return se c'�
			else value = null;

			returnFlag = true;

		}

	}//fine visit ReturnIstr

	/**
	 * Questo metodo compie la valutazione delle stringhe di javascript.
	 * @param e rappresenta la categoria sintattica stringa.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( StringaExp e ){

		value = new String(e.getVal());

	}//fine visit StringaExp

	/**
	 * Questo metodo compie la valutazione dell'istruzione 'switch' di javascript.
	 * @param e rappresenta la categoria sintattica dell'istruzione 'switch'.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( SwitchIstr e ) throws VisitorException{		

		e.getCond().accept(this);//valuto la condizione dello switch

		switchExp = value;//memorizzo il risultato della valutazione dell'espressione dello switch

		boolean fun = funAnnFlag, nbf = noBreakFlag;

		funAnnFlag = noBreakFlag = false;

		e.getCorpo().accept(this);//valuto il corpo dello switch

		if (breakFlag) breakFlag = false;//resetto il breakFlag;

		funAnnFlag = fun;

		noBreakFlag = nbf;

	}//fine visit SwitchIstr

	/**
	 * Questo metodo compie la valutazione della lista di istruzioni del corpo di un costruttore di javascript.
	 * @param e rappresenta la categoria sintattica lista istruzioni corpo di un costruttore.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( ThisIstr e ) throws VisitorException{

		if (value instanceof Oggetto){

			Oggetto obj = (Oggetto) value;

			e.getNomeProp().accept(this);//valuto il nome della proposizione

			String id = (String) value;

			e.getPropVal().accept(this);//valuta il valore della propriet�

			obj.addProp(id,value);

			value = obj;

		}
		else throw new VisitorException("Errore: nella costruzione dell'oggetto");

	}//fine visit ThisIstr

	/**
	 * Questo metodo compie la valutazione dell'operazione meno unario di javascript.
	 * @param e rappresenta la categoria sintattica operazione meno unario.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( UnMinusExp e ) throws VisitorException{

		e.getOper1().accept(this);//valuto l'operando

		if (value instanceof Double){

			value = new Double(-1*((Double) value).doubleValue());

		}
		else throw new VisitorException("Errore: l\'operando di - unario non � valido");

	}//fine visit UnMinusExp

	/**
	 * Questo metodo compie la valutazione dell'operazione pi� unario di javascript.
	 * @param e rappresenta la categoria sintattica operazione pi� unario.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( UnPlusExp e ) throws VisitorException{

		e.getOper1().accept(this);

		if (!(value instanceof Double)) throw new VisitorException("Errore: l\'operando di + unario non � valido");

	}//fine visit UnPlusExp

	/**
	 * Questo metodo compie la valutazione del ciclo 'while' di javascript.
	 * @param e rappresenta la categoria sintattica ciclo 'while'.
	 * @throws VisitorException segnalazione di errore durante la valutazione
	 */
	public void visit ( WhileIstr e ) throws VisitorException{		

		boolean nbf = noBreakFlag, ncf = noContinueFlag, fag = funAnnFlag;

		noBreakFlag = noContinueFlag = funAnnFlag = false;

		e.getCond().accept(this);

		while (valutaCond()) {

			e.getCorpo().accept(this);

			if (continueFlag) continueFlag = false;//resetto e faccio compiere un'altra iterazione

			if ((breakFlag) || (returnFlag)) break;//esco dal ciclo

			e.getCond().accept(this);

		}

		if (breakFlag) breakFlag = false;//resetto breakFlag

		noBreakFlag = nbf;

		noContinueFlag = ncf;

		funAnnFlag = fag;

	}//fine visit WhileIstr


	/*
	 * =====================================================================
	 *              ALTRI METODI PUBBLICI
	 *======================================================================
	 */


	/**
	 * Questo metodo consente di azzerare lo stato dell'interprete per eseguire
	 * una nuova interpretazione.
	 */
	public void init(){

		// creo un nuovo environment globale
		environment = new AmbienteDef();
		//resetto la variabile dei risultati parziali            
		value = null;
		switchExp = null;//non contiene nulla inizialmente
		caseFlag = false;//inizialmente falsa
		ambitoFlag = GLOBAL;//inizializzo ad ambito globale
		funAnnFlag = true;//inizializzo in modo che si possano definire funzioni annidate
		breakFlag = false;//inizializzo la variabile a false, cio� non si � ancora incontrato nessuna istruzione break
		continueFlag = false;//inizialmente falsa
		returnFlag = false;//inizialmente falsa
		// i prossimi tre flag sono impostati a true in quanto non ci possono essere le istruzioni break, continue e return
		//all'inizio dell'interpretazione di un programma, cio� nel main
		noBreakFlag = true;
		noContinueFlag = true;
		noReturnFlag = true;
		funCallFlag = false;//inizialmente non si sta chiamando nessuna funzione
		paramFlag = false;

	}//init

	/**
	 * Fornisce una rappresentazione esterna del risultato della valutazione sotto 
	 * forma di stringa, il risultato consiste nel contenuto della tabella delle variabili
	 * globali dell'ambiente di esecuzione.
	 * @return   String     Rappresentazione esterna sotto forma di stringa.
	 */
	public String getResAsString(){ 
		return environment.toString();
	}


 }//fine InterpreterVisitor