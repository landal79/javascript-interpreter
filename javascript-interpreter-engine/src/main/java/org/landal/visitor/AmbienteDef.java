package org.landal.visitor;

import java.util.Map;

import org.landal.utility.MyHashtable;

/**
 * Questa classe rappresenta l'ambiente di definizione a cui il visitor si
 * appoggia per compiere la valutazione del linguaggio in esso sono presenti la
 * tabella delle variabili, e uno stack per il supporto alla chiamata di
 * funzioni. L'ambiente di definizione si occupa di ricercare le variabili sia
 * in ambito globale che in ambito locale, restiuendo o modificando il loro
 * valore a seconda delle necessità.
 * 
 * @author Landini Alex
 * @version 1.0
 */
public class AmbienteDef {

	/**
	 * Queste costanti definiscono i possibili ambiti possibili.
	 */
	public final int GLOBAL = 0, LOCAL = 1;

	/**
	 * Tabella che contiene le varibili globali del programma.
	 */
	protected Map<String, Object> var;

	/**
	 * Stack di chiamata delle funzioni
	 */
	private StackAttivazione sAtt;

	/**
	 * Questo campo contiene un riferimento al record attualmente in cima allo
	 * stack. Serve per la ricerca delle variabili globali.
	 */
	private RecordAttivazione top;

	/**
	 * Costruttore di AmbienteDef che crea la tabella delle variabili e lo stack
	 * di attivazione delle funzioni.
	 */
	public AmbienteDef() {
		var = new MyHashtable<String, Object>();
		sAtt = new StackAttivazione();
		top = null;
	}

	// ====================================================================
	// FUNZIONI GESTIONE TABELLA VARIABILI
	// ====================================================================

	/**
	 * Questo metodo controlla se esiste una determinata variabile o funzione o
	 * costruttore, verificando se esiste prima nell'ambiente locale sullo stack
	 * e poi nella tabella delle variabili globali.
	 * 
	 * @param s
	 *            stringa che rappresenta il nome della variabile.
	 * @return boolean indica se la variabile esiste o no.
	 */
	public boolean searchVar(String s) {

		if (sAtt.isEmpty()) {// stack vuoto

			// verifico se esiste fra le variabili globali
			if (var.containsKey(s))
				return true;
			else
				return false;
		}// stack pieno
			// verifico se esiste fra le variabili locali
		else {

			if (searchLocalVar(s))
				return true;
			// verifico se esiste fra le variabili globali
			else if (var.containsKey(s))
				return true;
			else
				return false;
		}

	}// fine searchVar

	/**
	 * Questo metodo restituisce il valore della variabile di cui viene passato
	 * il nome.
	 * 
	 * @param s
	 *            stringa che contiene il nome della variabile.
	 * @return Object valore della variabile
	 */
	public Object getVar(String s) throws AmbienteDefException {
		// controllo se lo stack � vuoto
		if (sAtt.isEmpty()) {// stack vuoto
			if (var.containsKey(s))
				return var.get(s);
			else
				throw new AmbienteDefException(
						"Errore si sta cercando di prendere un valore di una variabile che non esiste");
		}// stack pieno
		else if (searchLocalVar(s))
			return getLocalVar(s);
		else if (var.containsKey(s))
			return var.get(s);
		else
			throw new AmbienteDefException(
					"Errore si sta cercando di prendere un valore di una variabile che non esiste");

	}// fine retrieveVar

	/**
	 * Questo metodo serve per aggiungere una nuova variabile nell'ambito
	 * indicato dal relativo parametro.
	 * 
	 * @param s
	 *            stringa che rappresenta il nome della variabile.
	 * @param val
	 *            valore da assegnare alla variabile
	 * @param ambito
	 *            indica l'ambito in cui inserire la variabile.
	 */
	public void addVar(String s, Object val, final int ambito)
			throws AmbienteDefException {

		switch (ambito) {
		// inserisco la variabile nell'ambito globale
		case GLOBAL:
			var.put(s, val);
			break;
		// inserisco la variabile nell'ambito locale
		case LOCAL:
			addLocalVar(s, val);
			break;
		}// fine switch

	}// fine addVar

	/**
	 * Questo metodo mi restituise l'ambito in cui � definita la variabile di
	 * cui mi viene passato il nome come parametro.
	 * 
	 * @param s
	 *            stringa che rappresenta il nome della variabile.
	 * @return int ambito della variabile
	 */
	public int getAmbitoFun(String s) throws AmbienteDefException {

		if (sAtt.isEmpty()) {// stack vuoto
			if (var.containsKey(s))
				return GLOBAL;
			else
				return LOCAL;
		}// stack pieno
		else if (searchLocalVar(s))
			return LOCAL;
		else if (var.containsKey(s))
			return GLOBAL;
		else
			throw new AmbienteDefException("Errore: funzione non definita");

	}// fine searchVar

	// ===================================================
	// METODI PER GESTIRE I PARAMETRI DELLE FUNZIONI
	// ===================================================

	/**
	 * Questo metodo verifica se esiste un parametro conoscendo il nome.
	 * 
	 * @param s
	 *            stringa che rappresenta il nome del parametro
	 */
	public boolean searchParam(String s) {

		if (sAtt.isEmpty()) {// stack vuoto
			if (var.containsKey(s))
				return true;
			else
				return false;
		}// stack pieno
		else if (searchLocalParam(s))
			return true;
		else if (var.containsKey(s))
			return true;
		else
			return false;

	}// fine searchParam

	/**
	 * Questo metodo restiuisce il valore della variabile di cui viene passato
	 * il nome.
	 * 
	 * @param s
	 *            stringa che rappresenta il nome del paramtro da cercare.
	 * @return Object valore del parametro.
	 * @throws AmbienteDefException
	 *             si � verificato un errore nella ricerca dei parametri.
	 */
	public Object getParam(String s) throws AmbienteDefException {

		if (sAtt.isEmpty()) {// stack vuoto
			if (var.containsKey(s))
				return var.get(s);
			else
				throw new AmbienteDefException(
						"Errore si sta cercando di prendere un valore di una variabile che non esiste");
		}// stack pieno
		else if (searchLocalParam(s))
			return getLocalParam(s);
		else if (var.containsKey(s))
			return var.get(s);
		else
			throw new AmbienteDefException(
					"Errore si sta cercando di prendere un valore di una variabile che non esiste");

	}// fine getParam

	// ==========================================
	// METODI PER LA GESTIONE DELLO STACK
	// ==========================================

	/**
	 * Questo metodo inserisce in cima allo stack un nuovo record di
	 * attivazione.
	 * 
	 * @param r
	 *            record do attivazione da inserire.
	 */
	protected void pushRecord(RecordAttivazione r) {
		sAtt.push(r);
		top = r;
	}// fine pushRecord

	/**
	 * Questo metodo estrae il primo record di attivazione dalla cima dello
	 * stack.
	 * 
	 * @throws AmbienteDefException
	 *             nel caso lo stack sia vuoto si verifica un errore.
	 */
	protected void popRecord() throws AmbienteDefException {

		try {
			// elimino il primo record in cima allo stack
			sAtt.pop();
			// prendo il riferimento al prossimo
			top = (RecordAttivazione) sAtt.getTop();
		} catch (EmptyStackException e) {
			throw new AmbienteDefException("Errore: " + e.getMessage());
		}
	}// fine popRecord

	/**
	 * Questo metodo crea un record di attivazione inserendolo poi im cima allo
	 * stack.
	 * 
	 * @param ambito
	 *            rappresenta l'ambito in cui deve essere inserito il record di
	 *            attivazione.
	 * @throws AmbienteDefException
	 *             si � verificato un errore durante la crazione di un nuovo
	 *             record di attivazione.
	 */
	public void creaRdA(final int ambito) throws AmbienteDefException {
		// verifico la validit� dell'ambito
		if ((ambito == GLOBAL) || (ambito == LOCAL)) {
			// creo il nuovo record di attivazione
			RecordAttivazione r = new RecordAttivazione(top, ambito);
			pushRecord(r);
		}// ambito non valido
		else
			throw new AmbienteDefException(
					"Errore: ambito di definizione della funzione non valido");
	}// fine creaRdA

	/**
	 * Questo metodo serve a eliminare un record di attivazione dallo stack
	 * 
	 * @throws AmbienteDefException
	 *             si � verificato un errore durante l'eliminazione del record.
	 */
	public void eliminaRdA() throws AmbienteDefException {

		popRecord();

	}// fine eliminaRda

	// ================================================================
	// GESTIONE DEL RECORD DI ATTIVAZIONE
	// ================================================================

	/**
	 * Questo metodo serve per cercare le variabili locali
	 */
	protected boolean searchLocalVar(String id) {

		return top.searchVar(id);

	}// fine searchLocalVar

	/**
	 * Questo metodo serve per aggiungere una variabile locale
	 */
	protected void addLocalVar(String id, Object val) {

		top.addVar(id, val);

	}// fine addLocalVar

	/**
	 * Reperisce il valore di una variabile locale
	 */
	private Object getLocalVar(String s) {

		return top.getVar(s);

	}// fine getLocalVar

	// ==============================================================
	// METODI PER GESTIRE I PARAMETRI LOCALI
	// ==============================================================

	/**
	 * Questo metodo serve a verificare l'esistenza di parametri locali
	 */
	protected boolean searchLocalParam(String id) {

		return top.searchParam(id);

	}// fine searchLocalparam

	/**
	 * Questo metodo serve per reperire il valore di un variabile locale
	 */
	protected Object getLocalParam(String s) {

		return top.getParam(s);

	}// fine getLocalParam

	// ==========================================================
	// rappresentazione esterna della tabella delle variabili
	// =========================================================

	/**
	 * Fornisce la rappresentazione esterna della tabella delle variabili
	 * gloabali
	 * 
	 * @return String rappresentazione esterna della tabella delle variabili
	 *         globali
	 */
	public String toString() {
		return var.toString();
	}

}