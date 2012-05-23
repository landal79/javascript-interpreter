package org.landal.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.landal.lexer.*;
import org.landal.token.*;

import java.io.StringReader;

/**
 * Questa classe serve per testare il lexer
 * 
 * @author Alex Landini
 */
public class TokenIDTest {

	/**
	 * Lexer da testare.
	 */
	private ILexer lex;

	/**
	 * Operazione di set up per impostare le classi di testing
	 */
	@Before
	public void setUp() {
		// creo il lexer
		lex = new Lexer(new TokenFactory());
	}

	/**
	 * metodo di test per i lettarali
	 */
	@Test
	public void testLetterali() {
		// stringa per i risultati delle valutazioni
		String res = null;
		// Stringa per i risultati attesi
		String resExpected = null;

		// eseguo i test dei lettarali
		try {
			// test dei numeri
			resExpected = "num";
			lex.init(new StringReader("123"));
			res = lex.nextToken().getID();
			assertTrue("lexer numeri_1: ", resExpected.equals(res));

			// test delle stringhe
			resExpected = new String("stringa");
			lex.init(new StringReader("\"ciao\""));
			res = lex.nextToken().getID();
			assertTrue("lexer stringhe_1: ", resExpected.equals(res));

			// test Boolean
			resExpected = "boolean";
			lex.init(new StringReader("true"));
			res = lex.nextToken().getID();
			assertTrue("lexer stringhe_1: ", resExpected.equals(res));

			resExpected = "boolean";
			lex.init(new StringReader("false"));
			res = lex.nextToken().getID();
			assertTrue("lexer stringhe_2: ", resExpected.equals(res));

			// test identificatori
			resExpected = "ident";
			lex.init(new StringReader("identifier"));
			res = lex.nextToken().getID();
			assertTrue("lexer identificatori_1: ", resExpected.equals(res));

			resExpected = "ident";
			lex.init(new StringReader("_identifier"));
			res = lex.nextToken().getID();
			assertTrue("lexer identificatori_2: ", resExpected.equals(res));

			resExpected = "ident";
			lex.init(new StringReader("$identifier"));
			res = lex.nextToken().getID();
			assertTrue("lexer identificatori_3: ", resExpected.equals(res));

		} catch (LexerException e) {
			fail("errore nella lettura dei token");
		}

	}// testLetterali

	/**
	 * Questo metodo serve per testare il riconoscimento degli operatori
	 */
	@Test
	public void testOperatori() {
		// stringa per i risultati delle valutazioni
		String res = null;
		// Stringa per i risultati attesi
		String resExpected = null;

		try {
			resExpected = "=";
			lex.init(new StringReader("="));
			res = lex.nextToken().getID();
			assertTrue("lexer operatori_1: ", resExpected.equals(res));

			resExpected = "&&";
			lex.init(new StringReader("&&"));
			res = lex.nextToken().getID();
			assertTrue("lexer operatori_2: ", resExpected.equals(res));

			resExpected = "||";
			lex.init(new StringReader("||"));
			res = lex.nextToken().getID();
			assertTrue("lexer operatori_3: ", resExpected.equals(res));

			resExpected = "!=";
			lex.init(new StringReader("!="));
			res = lex.nextToken().getID();
			assertTrue("lexer operatori_4: ", resExpected.equals(res));

			resExpected = "==";
			lex.init(new StringReader("=="));
			res = lex.nextToken().getID();
			assertTrue("lexer operatori_5: ", resExpected.equals(res));

			resExpected = "!";
			lex.init(new StringReader("!"));
			res = lex.nextToken().getID();
			assertTrue("lexer operatori_6: ", resExpected.equals(res));

			resExpected = "?";
			lex.init(new StringReader("?"));
			res = lex.nextToken().getID();
			assertTrue("lexer operatori_7: ", resExpected.equals(res));

			resExpected = "++";
			lex.init(new StringReader("++"));
			res = lex.nextToken().getID();
			assertTrue("lexer operatori_8: ", resExpected.equals(res));

			resExpected = "--";
			lex.init(new StringReader("--"));
			res = lex.nextToken().getID();
			assertTrue("lexer operatori_9: ", resExpected.equals(res));

			resExpected = "<=";
			lex.init(new StringReader("<="));
			res = lex.nextToken().getID();
			assertTrue("lexer operatori_10: ", resExpected.equals(res));

			resExpected = ">=";
			lex.init(new StringReader(">="));
			res = lex.nextToken().getID();
			assertTrue("lexer operatori_11: ", resExpected.equals(res));

			resExpected = "<";
			lex.init(new StringReader("<"));
			res = lex.nextToken().getID();
			assertTrue("lexer operatori_12: ", resExpected.equals(res));

			resExpected = ">";
			lex.init(new StringReader(">"));
			res = lex.nextToken().getID();
			assertTrue("lexer operatori_13: ", resExpected.equals(res));

			resExpected = "%";
			lex.init(new StringReader("%"));
			res = lex.nextToken().getID();
			assertTrue("lexer operatori_14: ", resExpected.equals(res));

			resExpected = "/";
			lex.init(new StringReader("/"));
			res = lex.nextToken().getID();
			assertTrue("lexer operatori_15: ", resExpected.equals(res));

			resExpected = "*";
			lex.init(new StringReader("*"));
			res = lex.nextToken().getID();
			assertTrue("lexer operatori_16: ", resExpected.equals(res));

			resExpected = "-";
			lex.init(new StringReader("-"));
			res = lex.nextToken().getID();
			assertTrue("lexer operatori_17: ", resExpected.equals(res));

			resExpected = "+";
			lex.init(new StringReader("+"));
			res = lex.nextToken().getID();
			assertTrue("lexer operatori_18: ", resExpected.equals(res));

		} catch (LexerException e) {
			fail("Errore durante la lettura dei token");
		}

	}// testOperatori

	/**
	 * Questo metodo esegue il test dei simboli.
	 */
	@Test
	public void testSimboli() {
		// stringa per i risultati delle valutazioni
		String res = null;
		// Stringa per i risultati attesi
		String resExpected = null;

		try {
			resExpected = ",";
			lex.init(new StringReader(","));
			res = lex.nextToken().getID();
			assertTrue("lexer simboli_1: ", resExpected.equals(res));

			resExpected = ";";
			lex.init(new StringReader(";"));
			res = lex.nextToken().getID();
			assertTrue("lexer simboli_2: ", resExpected.equals(res));

			resExpected = ".";
			lex.init(new StringReader("."));
			res = lex.nextToken().getID();
			assertTrue("lexer simboli_3: ", resExpected.equals(res));

			resExpected = ":";
			lex.init(new StringReader(":"));
			res = lex.nextToken().getID();
			assertTrue("lexer simboli_4: ", resExpected.equals(res));

			resExpected = "(";
			lex.init(new StringReader("("));
			res = lex.nextToken().getID();
			assertTrue("lexer simboli_5: ", resExpected.equals(res));

			resExpected = ")";
			lex.init(new StringReader(")"));
			res = lex.nextToken().getID();
			assertTrue("lexer simboli_6: ", resExpected.equals(res));

			resExpected = "[";
			lex.init(new StringReader("["));
			res = lex.nextToken().getID();
			assertTrue("lexer simboli_7: ", resExpected.equals(res));

			resExpected = "]";
			lex.init(new StringReader("]"));
			res = lex.nextToken().getID();
			assertTrue("lexer simboli_8: ", resExpected.equals(res));

			resExpected = "{";
			lex.init(new StringReader("{"));
			res = lex.nextToken().getID();
			assertTrue("lexer simboli_9: ", resExpected.equals(res));

			resExpected = "}";
			lex.init(new StringReader("}"));
			res = lex.nextToken().getID();
			assertTrue("lexer simboli_10: ", resExpected.equals(res));

		} catch (LexerException e) {
			fail("Errore durante la lettura dei token");
		}

	}// testSimboli

	@Test
	public void testErroreStringa() {

		// stringa per i risultati delle valutazioni
		String res = null;
		// Stringa per i risultati attesi
		String resExpected = null;

		// errori nel riconoscimento di stringhe
		try {
			resExpected = new String("ciao");
			lex.init(new StringReader("\"ciao"));
			res = lex.nextToken().getID();

		} catch (LexerException e) {

			assertTrue(
					"Errore Stringa: ",
					e.getMessage()
							.equals("Errore: riga 1 :Manca il carattere terminale della stringa: \""));
			return;
		}// catch
		fail("si doveva avere un'eccezione");

	}// test Errore

	/**
	 * Questo metodo serve per testare il riconoscimento delle parole chiave.
	 */
	@Test
	public void testKeyword() {
		// stringa per i risultati delle valutazioni
		String res = null;
		// Stringa per i risultati attesi
		String resExpected = null;

		try {
			resExpected = "break";
			lex.init(new StringReader("break"));
			res = lex.nextToken().getID();
			assertTrue("lexer key_1: ", resExpected.equals(res));

			resExpected = "case";
			lex.init(new StringReader("case"));
			res = lex.nextToken().getID();
			assertTrue("lexer key_2: ", resExpected.equals(res));

			resExpected = "continue";
			lex.init(new StringReader("continue"));
			res = lex.nextToken().getID();
			assertTrue("lexer key_3: ", resExpected.equals(res));

			resExpected = "default";
			lex.init(new StringReader("default"));
			res = lex.nextToken().getID();
			assertTrue("lexer key_4: ", resExpected.equals(res));

			resExpected = "else";
			lex.init(new StringReader("else"));
			res = lex.nextToken().getID();
			assertTrue("lexer key_5: ", resExpected.equals(res));

			resExpected = "do";
			lex.init(new StringReader("do"));
			res = lex.nextToken().getID();
			assertTrue("lexer key_6: ", resExpected.equals(res));

			resExpected = "for";
			lex.init(new StringReader("for"));
			res = lex.nextToken().getID();
			assertTrue("lexer key_7: ", resExpected.equals(res));

			resExpected = "function";
			lex.init(new StringReader("function"));
			res = lex.nextToken().getID();
			assertTrue("lexer key_8: ", resExpected.equals(res));

			resExpected = "if";
			lex.init(new StringReader("if"));
			res = lex.nextToken().getID();
			assertTrue("lexer key_9: ", resExpected.equals(res));

			resExpected = "new";
			lex.init(new StringReader("new"));
			res = lex.nextToken().getID();
			assertTrue("lexer key_10: ", resExpected.equals(res));

			resExpected = "null";
			lex.init(new StringReader("null"));
			res = lex.nextToken().getID();
			assertTrue("lexer key_11: ", resExpected.equals(res));

			resExpected = "return";
			lex.init(new StringReader("return"));
			res = lex.nextToken().getID();
			assertTrue("lexer key_12: ", resExpected.equals(res));

			resExpected = "switch";
			lex.init(new StringReader("switch"));
			res = lex.nextToken().getID();
			assertTrue("lexer key_13: ", resExpected.equals(res));

			resExpected = "this";
			lex.init(new StringReader("this"));
			res = lex.nextToken().getID();
			assertTrue("lexer key_14: ", resExpected.equals(res));

			resExpected = "var";
			lex.init(new StringReader("var"));
			res = lex.nextToken().getID();
			assertTrue("lexer key_15: ", resExpected.equals(res));

			resExpected = "while";
			lex.init(new StringReader("while"));
			res = lex.nextToken().getID();
			assertTrue("lexer key_16: ", resExpected.equals(res));

		} catch (LexerException e) {
			fail("Errore durante la lettura dei token");
		}

	}// testOperatori

	@Test
	public void testErroreNumero() {
		String res = null;
		// errori nel riconoscimento di numeri
		try {
			lex.init(new StringReader("123."));
			res = lex.nextToken().getID();

		} catch (LexerException e) {

			assertTrue(
					"Errore numero: ",
					e.getMessage()
							.equals("Errore: riga 1 :Errore durante la lettura di numero reale;"));
			return;
		}// catch
		fail("si doveva avere un'eccezione");

	}// test Errore

	@Test
	public void testErroreOperatore() {
		String res = null;
		// errori nel riconoscimento di operatori
		try {
			lex.init(new StringReader("^"));
			res = lex.nextToken().getID();
		} catch (LexerException e) {
			assertTrue(
					"Errore simbolo: ",
					e.getMessage()
							.equals("Errore: riga 1 :In ingresso c'� un oggetto non riconosciuto"));
			return;
		}// catch
		fail("si doveva avere un'eccezione");

	}// testErroreOperatore

}
