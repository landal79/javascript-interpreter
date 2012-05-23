package org.landal.utility;

/**
 * Questa classe rappresenta un Iterator per la lista
 * 
 * @author Alex Landini
 */
public class ListIterator<E> implements IIterator<E> {

	/**
	 * Riferimento alla lista.
	 */
	protected IList<E> list;

	/**
	 * Costruttore di ListIterator che riceve la lista come parametro.
	 * 
	 * @param list
	 *            lista di cui costruire l'iterator
	 */
	public ListIterator(IList<E> list) {
		this.list = list;
		// quando creo l'iterator mi pongo all'inizio della lista
		list.goFirst();
	}

	/**
	 * Reperisce il prossimo l'elemento.
	 * 
	 * @return Object prossimo elemento della lista.
	 */
	public E next() {
		// recupero l'oggetto dalla posizione corrente
		E o = list.get();
		// avanzo nella lista
		list.goNext();
		// ritorno l'oggetto corrente
		return o;

	}// next

	/**
	 * Query per verificare se la lista ha ancora degli elementi.
	 * 
	 * @return boolena valore che indica se la lista ha ancora degli elementi.
	 */
	public boolean hasNext() {
		// verifico se la lista non è vuota
		if (list.get() != null)
			return true;
		else
			return false;
	}// hasNext

	/**
	 * Rimuove l'elemento nellla posizione corrente.
	 */
	public void remove() {
		list.remove();
	}

}
