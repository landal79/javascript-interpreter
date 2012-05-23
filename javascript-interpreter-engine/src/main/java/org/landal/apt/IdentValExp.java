package org.landal.apt;

import org.landal.visitor.VisitorException;

/**
 * Questa classe rappresenta la categoria sintattica identificatore come
 * r-value.
 * 
 * @author Landini Alex
 * @version 1.0
 */
public class IdentValExp extends IdentExp {

	/**
	 * Costruttore di IdentValExp.
	 * 
	 * @param name
	 *            nome dell'identificatore.
	 */
	public IdentValExp(String name) {
		super(name);
	}

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
	public void accept(org.landal.visitor.IVisitor v)
			throws org.landal.visitor.VisitorException {
		v.visit(this);
	}

}