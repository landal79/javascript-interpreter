package org.landal.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.landal.utility.*;

/**
 * Questa classe serve per testare le liste.
 * 
 * @author Alex Landini
 */
public class ListTest {

	/**
	 * Con questo metodo eseguo il test per le liste
	 */
	@Test
	public void testList_Integer() {
		// variabili per i risultati
		String res = null;
		String resExp = null;
		// creo una lista
		IList<Integer> l = new List<Integer>();
		// riempo la lista
		for (int i = 0; i < 10; i++) {
			l.insert(i);
		}

		// verfico se l'inserimento è avvenuto correttamente
		resExp = "9  |  8  |  7  |  6  |  5  |  4  |  3  |  2  |  1  |  0  ";
		res = l.toString();
		assertTrue("inserimento:", res.equals(resExp));

		// mi sposto di una posizione e rimuovo un elemento
		l.goNext();
		l.remove();
		res = l.toString();
		resExp = "9  |  7  |  6  |  5  |  4  |  3  |  2  |  1  |  0  ";
		assertTrue("remove:", res.equals(resExp));

		// reperisco l'elemento nella posizione attuale che dovrebbe essere 9
		Object o = l.get();
		res = o.toString();
		assertTrue("get:", res.equals("" + 9));

		// eseguo un inserimento in coda con append
		l.append(new Integer(10));
		res = l.toString();
		resExp = "9  |  7  |  6  |  5  |  4  |  3  |  2  |  1  |  0  |  10  ";
		assertTrue("append:", res.equals(resExp));

		// vado in fondo alla coda e inserisco un elemento
		while (l.get() != null)
			l.goNext();
		l.insert(99999);
		res = l.toString();
		resExp = "9  |  7  |  6  |  5  |  4  |  3  |  2  |  1  |  0  |  10  |  99999  ";
		assertTrue("insert at end:", res.equals(resExp));

		// vado avanti in fondo alla lista e rimuovo, la lista non dovrebbe
		// subire cambiamenti
		l.goNext();
		l.remove();
		res = l.toString();
		assertTrue("remove from end:", res.equals(resExp));

		// torno a inizio lista e prelevo un elemento
		l.goFirst();
		l.insert(11111);
		res = l.toString();
		resExp = "11111  |  9  |  7  |  6  |  5  |  4  |  3  |  2  |  1  |  0  |  10  |  99999  ";
		assertTrue("remove from end:", res.equals(resExp));

	}

	/**
	 * Eseguo il test per le fifolist.
	 */
	@Test
	public void testFifoList() {
		IFifoList<Integer> fl = new FifoList<Integer>();
		// variabili per i risultati
		String res = null;
		String resExp = null;

		// riempo la fifolist
		for (int i = 0; i < 10; i++)
			fl.insertVal(i);

		res = fl.toString();
		resExp = "0  |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  ";
		assertTrue("insertVal:", res.equals(resExp));

		// rimuovo un elemento dalla fifolist
		fl.getVal();
		res = fl.toString();
		resExp = "1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  ";
		assertTrue("insertVal:", res.equals(resExp));

		// la svuoto finchè non è vuota per verificare eventuali errori
		while (fl.getVal() != null)
			;

		res = fl.toString();
		resExp = "Empty";
		assertTrue("insertVal:", res.equals(resExp));

	}// testFifoList

}
