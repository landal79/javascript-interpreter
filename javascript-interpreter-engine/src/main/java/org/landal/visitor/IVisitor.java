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

/**
 * Questa interfaccia contiene un metodo unico <i>visit</i>, che � per�
 * overloaded, infatti ne esiste uno per ogni classe concreta appartenente al
 * componente apt (componente che contiene tutte le categorie sintattiche), se
 * una classe vuole compiere la valutazione di una frase scritta in Javascript �
 * necessario che implementi tutti i metodi contenuti in qusta interfaccia
 * 
 * @author Alex Landini
 * @version 1.0
 */
public interface IVisitor {

	/**
	 * Questo metodo compie la valutazione di accesso all'array come l-value,
	 * cio� come componente dell'array a cui assegnare un valore.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica accesso alla componente
	 *            dell'array come l-value
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(AccessArrayExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione di accesso all'array come r-value,
	 * cio� si considera il valore della variabile contenuto nella componente
	 * dell'array.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica accesso alla componente
	 *            dell'array come r-value
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(AccessArrayValExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione di accesso a una propriet� di un
	 * oggetto come l-value, cio� come propriet� dell'oggetto a cui assegnare un
	 * valore.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica accesso alla propriet�
	 *            dell'oggetto come l-value
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(AccessPropertyExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione di accesso a una propriet� di un
	 * oggetto come r-value, cio� viene considerato il valore contenuto della
	 * propriet� dell'oggetto.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica accesso alla propriet�
	 *            dell'oggetto come r-value
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(AccessPropertyValExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'operazione '||', 'and' logico.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica 'and' logico
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(AndExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'operazione di assegnamento.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica 'assegnamento'.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(AssignExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione di un blocco di istruzioni delimitato
	 * da '{' , '}'.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica blocco di istruzioni.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(BloccoIstr e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione di un valore booleano.
	 * 
	 * @param e
	 *            rappresenta un valore booleano
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(BooleanoExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'istruzione 'break' di
	 * javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica dell'istruzione 'break'.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(BreakIstr e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'istruzione 'case' di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica dell'istruzione 'case'.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(CaseIstr e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'istruzione 'continue' di
	 * javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica dell'istruzione
	 *            'continue'.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(ContinueIstr e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione del corpo di un costruttore di
	 * javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica corpo di un costruttore.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(CostrIstr e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'espressione di definizione un
	 * array di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica definizione array.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(DefArrayElExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'istruzione 'default' di
	 * javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica dell'istruzione 'default'.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(DefaultIstr e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione della definizione di un costruttore
	 * di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica difinizione di un
	 *            costruttore.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(DefCostrIstr e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione della definizione di una funzione
	 * come espressione di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica difinizione di una
	 *            funzione come espressione.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(DefFunExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione della definizione di una funzione
	 * come istruzione di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica difinizione di una
	 *            funzione come istruzione.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(DefFunIstr e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione della definizione delle propriet� di
	 * un oggetto come espressione di javascript. Esempio: b = {m : 65, l :
	 * '56'};
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica difinizione di un oggetto
	 *            come espressione.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(DefPropObjectExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione della definizione esplicita di
	 * variabili attraverso la keyword 'var' di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica difinizione esplicita di
	 *            variabili.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(DefVarExplIstr e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'operazione divisione di
	 * javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica operazione di divisione.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(DivExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione del ciclo 'do-while' di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica ciclo 'do-while'.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(DoIstr e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'istruzione vuota di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica istruzione vuota.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(EmptyIstr e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'espressione di uguglianza '=='
	 * di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica espressione di uguglianza.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(EqualExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'espressione condizionale ' ? : '
	 * di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica espressione condizionale.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(ExpCond e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'espressione sequenza di
	 * espressioni ' , ' di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica espressione sequenza di
	 *            espressioni.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(ExpSeq e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione del ciclo 'for/in' di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica ciclo 'for/in'.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(ForInIstr e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione del ciclo 'for' di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica ciclo 'for'.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(ForIstr e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione della chiamata di funzioni come
	 * l-value di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica chiamata di funzione come
	 *            l-value.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(FunctionCallExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione della chiamata di funzioni come
	 * r-value di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica chiamata di funzione come
	 *            r-value.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(FunctionCallValExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'espressione di maggiore uguale
	 * '>=' di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica espressione di maggiore
	 *            uguale.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(GreatEqExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'espressione di maggiore '>' di
	 * javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica espressione di maggiore.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(GreaterExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'identificatore come l-value di
	 * javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica indentificatore come
	 *            l-value.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(IdentExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'identificatore come r-value di
	 * javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica indentificatore come
	 *            r-value.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(IdentValExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'istruzione di selezione 'if' di
	 * javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica istruzione di selezione.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(IfIstr e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione della lista di argomenti di chiamata
	 * di una funzione di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica lista argomenti di
	 *            chiamata di una funzione.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(ListaArgExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione della lista di definizione di
	 * variabili di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica lista di definizione di
	 *            variabili.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(ListaDefIstr e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione della lista di elementi di un array
	 * di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica lista elementi array.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(ListaElExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione della lista delle istruzioni di
	 * javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica lista delle istruzioni.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(ListaIstr e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione della lista dei parametri di una
	 * funzione di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica lista parametri di una
	 *            funzione.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(ListaParamIstr e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione della lista delle propriet� di un
	 * oggetto creato come espressione di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica lista propriet� oggetto
	 *            creato come espressione.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(ListaPropExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'espressione di minore uguagale
	 * '<=' di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica espressione di minore
	 *            uguale.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(LowEqExp e) throws VisitorException;

/**
     * Questo metodo compie la valutazione dell'espressione di minore  '<' di javascript.
     * @param e rappresenta la categoria sintattica espressione di minore.
     * @throws VisitorException segnalazione di errore durante la valutazione
     */
	public void visit(LowerExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'operazione sottrazione di
	 * javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica operazione di sottrazione.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(MinusExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'operazione resto della divisione
	 * fra interi di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica operazione di resto della
	 *            divisione fra interi.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(ModExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'operazione moltiplicazione di
	 * javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica operazione di
	 *            moltiplicazione.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(MulExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'operatore di creazione di
	 * oggetti 'new' di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica operazione di creazione
	 *            oggetti.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(NewExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'espressione di disuguglianza
	 * '!=' di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica espressione di
	 *            disuguglianza.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(NotEqualExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'espressione di negazione '!' di
	 * javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica espressione di negazione.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(NotExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione del valore 'null' di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica valore 'null'.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(NullExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dei numeri di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica numero.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(NumExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'espressione relazionale or
	 * logico '||' di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica espressione relazionale or
	 *            logico.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(OrExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'operazione somma di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica operazione di somma.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(PlusExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'operazione post-decremento di
	 * javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica operazione
	 *            post-decremento.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(PostDecExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'operazione post-incremento di
	 * javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica operazione
	 *            post-incremento.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(PostIncExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'operazione pre-decremento di
	 * javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica operazione pre-decremento.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(PreDecExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'operazione pre-incremento di
	 * javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica operazione pre-incremento.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(PreIncExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione di una propriet� di un oggetto come
	 * l-value di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica propriet� oggetto come
	 *            l-value.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(PropExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione di una propriet� di un oggetto come
	 * r-value di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica propriet� oggetto come
	 *            r-value.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(PropValExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'istruzione 'return' di
	 * javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica dell'istruzione 'return'.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(ReturnIstr e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione delle stringhe di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica stringa.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(StringaExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'istruzione 'switch' di
	 * javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica dell'istruzione 'switch'.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(SwitchIstr e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione della lista di istruzioni del corpo
	 * di un costruttore di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica lista istruzioni corpo di
	 *            un costruttore.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(ThisIstr e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'operazione meno unario di
	 * javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica operazione meno unario.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(UnMinusExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione dell'operazione pi� unario di
	 * javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica operazione pi� unario.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(UnPlusExp e) throws VisitorException;

	/**
	 * Questo metodo compie la valutazione del ciclo 'while' di javascript.
	 * 
	 * @param e
	 *            rappresenta la categoria sintattica ciclo 'while'.
	 * @throws VisitorException
	 *             segnalazione di errore durante la valutazione
	 */
	public void visit(WhileIstr e) throws VisitorException;

}
