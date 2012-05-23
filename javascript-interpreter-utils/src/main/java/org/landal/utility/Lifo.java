package org.landal.utility;

/**
 * Questa classe rappresenta una possibile implementazione del tipo lifo, ovvero
 * di uno stack.
 * 
 * @author Alex Landini
 */
public class Lifo<E> extends List<E> implements ILifo<E> {

	/**
	 * Costruttore della lifo.
	 */
	public Lifo() {
		super();
	}

	/**
	 * Costruttore nel caso venga passato il primo oggetto da inserire.
	 * 
	 * @param o
	 *            primo oggetto da inserire.
	 */
	public Lifo(E o) {
		super(o);
	}

	/**
	 * Inserisce un oggetto al top della lifo.
	 * 
	 * @param o
	 *            oggetto da inserire.
	 */
	public void push(E o) {
		// mi posiziono all'inizio della lista
		super.goFirst();
		// lo inserisco come primo elemento
		super.insert(o);
	}

	/**
	 * Prende il primo oggetto in cima alla lifo.
	 * 
	 * @return Object primo oggetto in cima alla lifo.
	 */
	public E pop() {
		// mi posiziono all'inizio della lista
		super.goFirst();
		// reperisco l'oggetto in cima alla lista
		E o = super.get();
		// elimino l'oggetto in cima alla lista
		super.remove();
		// resituisco l'oggetto
		return o;
	}

}// Lifo
