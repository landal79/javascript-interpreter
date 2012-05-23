package org.landal.utility;

/**
 * Questa interccia rappresenta il generico nodo di un qualsiasi tipo di lista.
 * 
 * @author Alex Landini
 */
public interface INodo<E> {

	/**
	 * Restuisce il valore contenuto nel nodo.
	 * 
	 * @return Object oggetto presente nel nodo.
	 */
	public E getVal();

	/**
	 * Imposta il valore del nodo.
	 * 
	 * @param o
	 *            oggetto che diventarà il valore contenuto nel nodo.
	 */
	public void setVal(E o);

	/**
	 * Restituisce un riferimento al nodo successivo.
	 * 
	 * @return INodo riferimento al prossimo nodo.
	 */
	public INodo<E> getNext();

	/**
	 * Imposta il riferimento al prossimo nodo.
	 * 
	 * @param n
	 *            riferimeto al prossimo nodo.
	 */
	public void setNext(INodo<E> n);

}
