package org.landal.utility;

/**
 * Questa interfaccia definisce il tipo iterator per scorrere le liste.
 * 
 * @author Alex Landini
 */
public interface IIterator<E> {

	/**
	 * Reperisce il prossimo l'elemento.
	 * 
	 * @return Object prossimo elemento della lista.
	 */
	public E next();

	/**
	 * Query per verificare se la lista ha ancora degli elementi.
	 * 
	 * @return boolena valore che indica se la lista ha ancora degli elementi.
	 */
	public boolean hasNext();

	/**
	 * Rimuove l'elemento nellla posizione corrente.
	 */
	public void remove();

}
