package org.landal.apt;

import org.landal.visitor.IVisitor;
import org.landal.visitor.VisitorException;

/**
 * Questa interfaccia rappresenta l'albero di derivazione di una frase, essa
 * andr� specializzata dalle classi concrete che rappresentano le varie
 * categorie sintattiche concrete che possono comparire in una frase di un
 * linguaggio.
 * 
 * @author Alex Landini
 * @version 1.0
 */
public interface IApt {

	/**
	 * Questo metodo permette a un visitor di valutare la categoria sintattica
	 * attraverso la tecnica del double-dispatch.
	 * 
	 * @param v
	 *            rappresenta il visitor che eseguir� la valutazione della
	 *            classe
	 * @throws VisitorException
	 *             nel caso durante la valutazione dell'albero si generi qualche
	 *             errore.
	 */
	public void accept(IVisitor v) throws VisitorException;

	/**
	 * Questo metodo consente di ottenere una rappresentazione esterna della
	 * categoria sintattica.
	 * 
	 * @return String viene restituita la rappresetazione esterna sotto forma di
	 *         stringa.
	 */
	public String toString();
}
