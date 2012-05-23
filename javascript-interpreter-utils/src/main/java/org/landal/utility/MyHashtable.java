package org.landal.utility;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Questa classe eredita da hashtable tutti i comportamenti, si essa specializza
 * solo la stampa.
 * 
 * @author Landini Alex
 * @version 1.0
 */
public class MyHashtable<K, V> extends Hashtable<K, V> {

	private static final long serialVersionUID = 1L;

	private static final String NEW_LINE = "\n";
	private static final String EQUAL_SYMBOL = " = ";

	/**
	 * Costruttore di MyHashtable
	 */
	public MyHashtable() {
		super();
	}

	/**
	 * Rappresentazione esterna della hashtable diversa da quella di Hashtable.
	 * 
	 * @return String rappresentazione esterna.
	 */
	public String toString() {

		StringBuilder sb = new StringBuilder();

		Enumeration<V> en1 = this.elements();
		Enumeration<K> en2 = this.keys();

		while (en1.hasMoreElements()) {
			sb.append(en2.nextElement().toString()).append(EQUAL_SYMBOL)
					.append(en1.nextElement().toString()).append(NEW_LINE);
		}

		return sb.toString();

	}

}