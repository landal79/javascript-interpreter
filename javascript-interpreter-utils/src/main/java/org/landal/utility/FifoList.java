/*
 * FifoList.java
 *
 * Created on 11 dicembre 2004, 21.09
 */

package org.landal.utility;

/**
 * Questa classe implementa l'ADT Lista che può essere usata anche come una
 * Fifo, in quanto eredita da entrambi i tipi di dati;
 * 
 * @author Alex Landini
 */
public class FifoList<E> extends List<E> implements IFifoList<E> {

	/**
	 * Costruttore di una fifolist senza argomenti.
	 */
	public FifoList() {

		super();
	}

	/**
	 * Costruttore nel caso venga inserito il primo oggetto.
	 * 
	 * @param o
	 *            primo oggetto da inserire.
	 */
	public FifoList(E o) {

		super(o);

	}

	/**
	 * Questo metodo restuisce il primo elemento in testa alla fifo eleminandolo
	 * dalla fifo. Se la Fifo è vuota ritorna Null.
	 * 
	 * @return Object elemento in testa alla lista
	 */
	public E getVal() {

		E o = null;
		// reperisco il dato se esiste
		if (first != null) {
			o = first.getVal();
			// al primo nodo metto il successivo del primo
			first = first.getNext();
			// ritorno il dato letto
			return o;
		}// la fifo è vuota
		else
			return null;

	}

	/**
	 * Questo metodo inserisce un oggetto in coda alla fifo.
	 * 
	 * @return o oggetto da inserire.
	 */
	public void insertVal(E o) {
		// verifico se la lista non è vuota
		if (first != null) {
			super.append(o);
		} else {
			first = act = new Nodo<E>(o);
		}

	}

}
