package org.landal.utility;

/**
 * Questa interfaccia rappresenta l'astrazione della struttura dati Lifo.
 * 
 * @author Alex Landini
 */
public interface ILifo<E> {

	/**
	 * Controlla se la lifo è vuota.
	 * 
	 * @return boolean valore booleano che indica se la lifo è piena o vuota
	 */
	public boolean isEmpty();

	/**
	 * Inserisce un oggetto al top della lifo.
	 * 
	 * @param o
	 *            oggetto da inserire.
	 */
	public void push(E o);

	/**
	 * Prende il primo oggetto in cima alla lifo.
	 * 
	 * @return Il primo oggetto in cima alla lifo.
	 */
	public E pop();

}
