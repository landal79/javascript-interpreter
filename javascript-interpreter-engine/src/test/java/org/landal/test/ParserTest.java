package org.landal.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.landal.lexer.*;
import org.landal.parser.*;
import org.landal.token.*;

import java.io.StringReader;

/**
 * Classe di Test per il parser
 * 
 * @author Alex Landini
 */
public class ParserTest {

	/**
	 * lexer per i test.
	 */
	private ILexer lex;

	/**
	 * parser da testare.
	 */
	private IParser par;

	/**
	 * Inizializzo le classi per l'esecuzione del test.
	 */
	@Before
	public void setUp() {
		lex = new Lexer(new TokenFactory());
		par = new JavascriptParser(lex);
	}// setUp

	/*
	 * Test dei numeri.
	 */
	@Test
	public void testNum() {

		// stringa per i risultati delle valutazioni
		String res = null;
		// Stringa per i risultati attesi
		String resExpected = null;

		try {
			// test dei numeri
			resExpected = "2134.0";
			lex.init(new StringReader("2134;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("numero_1: ", resExpected.equals(res));

			resExpected = "2134.5665";
			lex.init(new StringReader("2134.5665;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("numero_2: ", resExpected.equals(res));

		} catch (ParserException e) {
			fail("Errore numero: " + e.getMessage());
		}

	}// testNum

	/**
	 * Test dei boolaen.
	 */
	public void testBoolean() {

		// stringa per i risultati delle valutazioni
		String res = null;
		// Stringa per i risultati attesi
		String resExpected = null;

		try {
			resExpected = "true";
			lex.init(new StringReader("true;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("vero: ", resExpected.equals(res));

			resExpected = "false";
			lex.init(new StringReader("false;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("falso: ", resExpected.equals(res));

		} catch (ParserException e) {
			fail("Errore boolean: " + e.getMessage());
		}

	}// testBoolean

	/**
	 * test per le stringhe.
	 */
	public void testString() {

		// stringa per i risultati delle valutazioni
		String res = null;
		// Stringa per i risultati attesi
		String resExpected = null;

		try {
			resExpected = "true";
			lex.init(new StringReader("'true';"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("stringa_1: ", resExpected.equals(res));

			resExpected = "tanti saluti";
			lex.init(new StringReader("\"tanti saluti\";"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("falso: ", resExpected.equals(res));

		} catch (ParserException e) {
			fail("Errore boolean: " + e.getMessage());
		}

	}// testString

	/**
	 * Test degli identificatori.
	 */
	public void testIdent() {

		// stringa per i risultati delle valutazioni
		String res = null;
		// Stringa per i risultati attesi
		String resExpected = null;

		try {
			resExpected = "a";
			lex.init(new StringReader("a;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("ident_1: ", resExpected.equals(res));

			resExpected = "a.m";
			lex.init(new StringReader("a.m;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("ident_2: ", resExpected.equals(res));

			resExpected = "a[ i ]";
			lex.init(new StringReader("a[i];"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("ident_3: ", resExpected.equals(res));

			resExpected = "a( i )";
			lex.init(new StringReader("a(i);"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("ident_4: ", resExpected.equals(res));

		} catch (ParserException e) {
			fail("Errore identificatore: " + e.getMessage());
		}

	}// testIdent

	/*
	 * Test delle operazioni aritmetiche, relazionali.
	 */
	public void testOperazioni() {

		// stringa per i risultati delle valutazioni
		String res = null;
		// Stringa per i risultati attesi
		String resExpected = null;

		try {
			// test somma
			resExpected = "2.0 + 3.0";
			lex.init(new StringReader("2+3;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("somma: ", resExpected.equals(res));

			// test sottrazione
			resExpected = "2.0 - 3.0";
			lex.init(new StringReader("2-3;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("sottrazione: ", resExpected.equals(res));

			// test moltiplicazione
			resExpected = "2.0 * 3.0";
			lex.init(new StringReader("2*3;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("moltiplicazione: ", resExpected.equals(res));

			// test divisione
			resExpected = "2.0 / 3.0";
			lex.init(new StringReader("2/3;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("divisione: ", resExpected.equals(res));

			// test modulo
			resExpected = "2.0 % 3.0";
			lex.init(new StringReader("2%3;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("modulo: ", resExpected.equals(res));

			// espressioni aritmetiche
			resExpected = "2.0 % 3.0 + 6.0 - 9.0";
			lex.init(new StringReader("(2%3) + (6-9);"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("exp_1: ", resExpected.equals(res));

			// test and logico
			resExpected = "2.0 && 3.0";
			lex.init(new StringReader("2&&3;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("and_1: ", resExpected.equals(res));

			resExpected = "false && 3.0";
			lex.init(new StringReader("false&&3;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("and_2: ", resExpected.equals(res));

			resExpected = "false && true";
			lex.init(new StringReader("false&&true;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("and_2: ", resExpected.equals(res));

			// test or

			resExpected = "2.0 || 3.0";
			lex.init(new StringReader("2||3;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("or_1: ", resExpected.equals(res));

			resExpected = "false || 3.0";
			lex.init(new StringReader("false||3;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("or_2: ", resExpected.equals(res));

			resExpected = "false || true";
			lex.init(new StringReader("false||true;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("and_2: ", resExpected.equals(res));

			// test <
			resExpected = "2.0 < 3.0";
			lex.init(new StringReader("2<3;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("low_1: ", resExpected.equals(res));

			resExpected = "false < 3.0";
			lex.init(new StringReader("false<3;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("low_2: ", resExpected.equals(res));

			resExpected = "8.0 < o";
			lex.init(new StringReader("8<o;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("low_3: ", resExpected.equals(res));

			// test >
			resExpected = "8.0 < o";
			lex.init(new StringReader("8<o;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("gre_1: ", resExpected.equals(res));

			resExpected = "false > 3.0";
			lex.init(new StringReader("false>3;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("gre_2: ", resExpected.equals(res));

			resExpected = "o < 0.8";
			lex.init(new StringReader("o<0.8;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("gre_3: ", resExpected.equals(res));

			// operatore di decremento
			resExpected = "i --";
			lex.init(new StringReader("i--;"));
			par.setLexer(lex);
			par.parse();
			if (par.getApt() != null)
				res = par.getApt().toString();
			assertTrue("post dec: ", resExpected.equals(res));

			// test ==
			resExpected = "2.0 == 3.0";
			lex.init(new StringReader("2==3;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("low_1: ", resExpected.equals(res));

			resExpected = "false == 3.0";
			lex.init(new StringReader("false==3;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("eq_2: ", resExpected.equals(res));

			resExpected = "8.0 == o";
			lex.init(new StringReader("8==o;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("eq_3: ", resExpected.equals(res));

			// not equal !=
			resExpected = "8.0 != o";
			lex.init(new StringReader("8!=o;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("neq_1: ", resExpected.equals(res));

			// concatenazione fra stringhe
			resExpected = "ciao + world";
			lex.init(new StringReader("'ciao'+'world';"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("eq_3: ", resExpected.equals(res));

		} catch (Exception e) {
			fail("operazioni: " + e.getMessage());
		}

	}// testEmptyIstr

	/**
	 * test per le operazioni di assegnamento
	 */
	public void testAssign() {

		// stringa per i risultati delle valutazioni
		String res = null;
		// Stringa per i risultati attesi
		String resExpected = null;
		try {
			// test Assegnamento
			resExpected = "x = 10.0";
			lex.init(new StringReader("x=10;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("ass_1: ", resExpected.equals(res));

			resExpected = "x = b";
			lex.init(new StringReader("x=b;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("ass_2: ", resExpected.equals(res));

			resExpected = "x[ o ] = b";
			lex.init(new StringReader("x['o']=b;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("ass_3: ", resExpected.equals(res));

		} catch (Exception e) {
			fail(e.getMessage());
		}

	}// testAssign

	/**
	 * test di costruzione degli oggetti composti;
	 */
	public void testObj() {

		// stringa per i risultati delle valutazioni
		String res = null;
		// Stringa per i risultati attesi
		String resExpected = null;
		try {
			// test oggetto letterale
			resExpected = "x = { n : 10.0 , m : g }";
			lex.init(new StringReader("x={n:10,m:g};"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("obj_1: ", resExpected.equals(res));

			// test array
			resExpected = "x = [ 9.0 , 9.0 , 9.0 , { m : 65.0 , l : 65.0 } ]";
			lex.init(new StringReader("x=[9,9,9,{m:65,l:65}];"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("vet_1: ", resExpected.equals(res));

			resExpected = "array[ 0.0 ] = { a : 54.0 , b : 56.0 , c : 54.0 }";
			lex.init(new StringReader("array[0] = {a:54, b:56, c: 54};"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("vet_3: ", resExpected.equals(res));

			resExpected = "x.m = { n : 10.0 , m : g }";
			lex.init(new StringReader("x.m={n:10,m:g};"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("obj_2: ", resExpected.equals(res));

		} catch (Exception e) {
			fail(e.getMessage());
		}

	}// testObj

	/*
	 * Test dell'istruzione vuota.
	 */
	public void testEmptyIstr() {

		// stringa per i risultati delle valutazioni
		String res = null;
		// Stringa per i risultati attesi
		String resExpected = null;

		try {
			// test dei numeri
			resExpected = ";";
			lex.init(new StringReader(";"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("istruzione vuota: ", resExpected.equals(res));
		} catch (ParserException e) {
			fail("Errore istruzione vuota: " + e.getMessage());
		}

	}// testEmptyIstr

	/*
	 * Test dell'istruzione break.
	 */
	public void testBreak() {

		// stringa per i risultati delle valutazioni
		String res = null;
		// Stringa per i risultati attesi
		String resExpected = null;

		try {
			resExpected = "break;";
			lex.init(new StringReader("break;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("case: ", resExpected.equals(res));
		} catch (ParserException e) {
			fail("Errore case: " + e.getMessage());
		}

	}// testBreak

	/*
	 * Test dell'istruzione case.
	 */
	public void testCase() {

		// stringa per i risultati delle valutazioni
		String res = null;
		// Stringa per i risultati attesi
		String resExpected = null;

		try {
			resExpected = "case 1.0 : break;";
			lex.init(new StringReader("case 1: break;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("case: ", resExpected.equals(res));
		} catch (ParserException e) {
			fail("Errore case: " + e.getMessage());
		}

	}// testCase

	/*
	 * Test del blocco.
	 */
	public void testBlocco() {

		// stringa per i risultati delle valutazioni
		String res = null;
		// Stringa per i risultati attesi
		String resExpected = null;

		try {
			resExpected = "{\ncase 1.0 : break;\n}\n";
			lex.init(new StringReader("{ case 1 : break; }"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("blocco: ", resExpected.equals(res));
		} catch (ParserException e) {
			fail("Errore blocco: " + e.getMessage());
		}

	}// testBlocco

	/**
	 * test per l'if.
	 */
	public void testIf() {

		// stringa per i risultati delle valutazioni
		String res = null;
		// Stringa per i risultati attesi
		String resExpected = null;

		try {
			resExpected = "if ( x > 0.0 ) m = 9.0 else x = 9.0";
			lex.init(new StringReader("if (x >0) m = 9; else x = 9;"));
			par.setLexer(lex);
			par.parse();
			res = par.getApt().toString();
			assertTrue("blocco: ", resExpected.equals(res));
		} catch (ParserException e) {
			fail("Errore blocco: " + e.getMessage());
		}

	}

}
