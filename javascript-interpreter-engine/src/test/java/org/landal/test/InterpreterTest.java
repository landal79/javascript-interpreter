package org.landal.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.landal.apt.IApt;
import org.landal.lexer.ILexer;
import org.landal.lexer.Lexer;
import org.landal.parser.IParser;
import org.landal.parser.JavascriptParser;
import org.landal.token.TokenFactory;
import org.landal.visitor.InterpreterVisitor;

import java.io.StringReader;

/**
 * Classe di Test per il parser
 * 
 * @author Alex Landini
 */
public class InterpreterTest {

	/**
	 * lexer per i test.
	 */
	private ILexer lex;

	/**
	 * parser da testare.
	 */
	private IParser par;

	/**
	 * visitor interprete.�
	 */
	private InterpreterVisitor vis;

	/**
	 * albero di cui compiere l'interpretazione.
	 */
	private IApt apt = null;

	/**
	 * Inizializzo le classi per l'esecuzione del test.
	 */
	@Before
	public void setUp() {
		lex = new Lexer(new TokenFactory());
		par = new JavascriptParser(lex);
		vis = new InterpreterVisitor();

	}// setUp

	/*
	 * Test delle operazioni aritmetiche, relazionali.
	 */
	@Test
	public void testOperazioni() {

		// stringa per i risultati delle valutazioni
		String res = null;
		// Stringa per i risultati attesi
		String resExpected = null;

		try {
			// test somma
			resExpected = "x = 5.0";
			lex.init(new StringReader("x = 2+3;"));
			par.setLexer(lex);
			vis.init();
			par.parse();
			par.getApt().accept(vis);
			res = vis.getResAsString();
			assertEquals("somma: ", resExpected, res.trim());

			// test sottrazione
			resExpected = "x = -1.0";
			lex.init(new StringReader("x = 2-3;"));
			par.setLexer(lex);
			vis.init();
			par.parse();
			par.getApt().accept(vis);
			res = vis.getResAsString();
			assertEquals("sottrazione: ", resExpected, res.trim());

			// test moltiplicazione
			resExpected = "x = 6.0";
			lex.init(new StringReader("x=2*3;"));
			par.setLexer(lex);
			vis.init();
			par.parse();
			par.getApt().accept(vis);
			res = vis.getResAsString();
			assertEquals("moltiplicazione: ", resExpected, res.trim());

			// test divisione
			resExpected = "x = 0.6666666666666666";
			lex.init(new StringReader("x=2/3;"));
			par.setLexer(lex);
			vis.init();
			par.parse();
			par.getApt().accept(vis);
			res = vis.getResAsString();
			assertEquals("divisione: ", resExpected, res.trim());

			// test modulo
			resExpected = "x = 2.0";
			lex.init(new StringReader("x=2%3;"));
			par.setLexer(lex);
			vis.init();
			par.parse();
			par.getApt().accept(vis);
			res = vis.getResAsString();
			assertEquals("modulo: ", resExpected, res.trim());

			// espressioni aritmetiche
			resExpected = "x = -1.0";
			lex.init(new StringReader("x = (2%3) + (6-9);"));
			par.setLexer(lex);
			vis.init();
			par.parse();
			par.getApt().accept(vis);
			res = vis.getResAsString();
			assertEquals("exp_1: ", resExpected, res.trim());

			// test and logico
			resExpected = "x = 3.0";
			lex.init(new StringReader("x=2&&3;"));
			par.setLexer(lex);
			vis.init();
			par.parse();
			par.getApt().accept(vis);
			res = vis.getResAsString();
			assertEquals("and_1: ", resExpected, res.trim());

			resExpected = "c = false";
			lex.init(new StringReader("c = false&&3;"));
			par.setLexer(lex);
			vis.init();
			par.parse();
			par.getApt().accept(vis);
			res = vis.getResAsString();
			assertEquals("and_2: ", resExpected, res.trim());

			resExpected = "val = false";
			lex.init(new StringReader("val = false&&true;"));
			par.setLexer(lex);
			vis.init();
			par.parse();
			par.getApt().accept(vis);
			res = vis.getResAsString();
			assertEquals("and_2: ", resExpected, res.trim());

			// test or

			resExpected = "val = 2.0";
			lex.init(new StringReader("val = 2||3;"));
			par.setLexer(lex);
			vis.init();
			par.parse();
			par.getApt().accept(vis);
			res = vis.getResAsString();
			assertEquals("or_1: ", resExpected, res.trim());

			resExpected = "val = 3.0";
			lex.init(new StringReader("val = false||3;"));
			par.setLexer(lex);
			vis.init();
			par.parse();
			par.getApt().accept(vis);
			res = vis.getResAsString();
			assertEquals("or_2: ", resExpected, res.trim());

			// test <
			resExpected = "val = true";
			lex.init(new StringReader("val = 2<3;"));
			par.setLexer(lex);
			vis.init();
			par.parse();
			par.getApt().accept(vis);
			res = vis.getResAsString();
			assertEquals("low_1: ", resExpected, res.trim());

			resExpected = "val = false";
			lex.init(new StringReader("val = 65 > 87;"));
			par.setLexer(lex);
			vis.init();
			par.parse();
			par.getApt().accept(vis);
			res = vis.getResAsString();
			assertEquals("gre_3: ", resExpected, res.trim());

			// test ==
			resExpected = "val = false";
			lex.init(new StringReader("val = 2==3;"));
			par.setLexer(lex);
			vis.init();
			par.parse();
			par.getApt().accept(vis);
			res = vis.getResAsString();
			assertEquals("low_1: ", resExpected, res.trim());

			resExpected = "val = true";
			lex.init(new StringReader("val = 3==3;"));
			par.setLexer(lex);
			vis.init();
			par.parse();
			par.getApt().accept(vis);
			res = vis.getResAsString();
			assertEquals("eq_2: ", resExpected, res.trim());

			// not equal !=
			resExpected = "val = true";
			lex.init(new StringReader("val = 8!=9;"));
			par.setLexer(lex);
			vis.init();
			par.parse();
			par.getApt().accept(vis);
			res = vis.getResAsString();
			assertEquals("neq_1: ", resExpected, res.trim());

			// concatenazione fra stringhe
			resExpected = "val = ciaoworld";
			lex.init(new StringReader("val = 'ciao'+'world';"));
			par.setLexer(lex);
			vis.init();
			par.parse();
			par.getApt().accept(vis);
			res = vis.getResAsString();
			assertEquals("eq_3: ", resExpected, res.trim());

		} catch (Exception e) {
			fail("operazioni: " + e.getMessage());
		}

	}// testOperazioni

	/**
	 * test di costruzione degli oggetti composti;
	 */
	@Test
	public void testObj() {

		// stringa per i risultati delle valutazioni
		String res = null;
		// Stringa per i risultati attesi
		String resExpected = null;
		try {
			// test oggetto letterale
			resExpected = "x = { n:10.0 , m:0.0 }";
			lex.init(new StringReader("x={n:10,m:0};"));
			par.setLexer(lex);
			vis.init();
			par.parse();
			par.getApt().accept(vis);
			res = vis.getResAsString();
			assertEquals("obj_1: ", resExpected, res.trim());

			// test array
			resExpected = "x = [ 0:9.0 , 1:9.0 , 2:9.0 , 3:{ m:65.0 , l:65.0 } ]";
			lex.init(new StringReader("x=[9,9,9,{m:65,l:65}];"));
			par.setLexer(lex);
			vis.init();
			par.parse();
			par.getApt().accept(vis);
			res = vis.getResAsString();
			assertEquals("vet_1: ", resExpected, res.trim());

		} catch (Exception e) {
			fail(e.getMessage());
		}

	}// testObj

}
