package org.landal.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.landal.lexer.*;
import org.landal.parser.*;
import org.landal.token.*;
import org.landal.utility.*;

import java.io.*;

/**
 * Questa classe serve per testare la classe LexerLL
 * 
 * @author Alex Landini
 */
public class LexerLLTest {

	/**
	 * lexer.
	 */
	private ILexer lex;

	/**
	 * Interfaccia delle operazioni di analisi LL
	 */
	private LLAnalisys ll;

	/**
	 * Operazione di set up per impostare le classi di testing
	 */
	@Before
	public void setUp() {
		// creo il lexer
		lex = new LexerLL(new Lexer(new StringReader(
				"for (i=0;i<10;i++) { break;}"), new TokenFactory()));
		ll = (LLAnalisys) lex;
	}

	/**
	 * Testo i metodi per l'analisiLL.
	 */
	@Test
	public void testLL() {
		// variabili per i risultati parziali
		String res = null, resExp = null;
		IToken tok = null;

		try {
			// leggo il primo token dal lexer e verifico che sia quello giusto
			tok = lex.nextToken();
			// il primo token � il for
			res = tok.toString();
			resExp = "for";
			assertTrue("estrazione primo token:", res.equals(resExp));

			// ora avvio l'analisi LL e rimetto dentro il primo token
			ll.initAnalisysLL(tok);
			// estraggo il token per verificare se � stato inserito, dovrebbe
			// essere sempre for
			tok = ll.readCoda();
			res = tok.toString();
			resExp = "for";
			assertTrue("lettura token da coda:", res.equals(resExp));

			// vado avanti fino alla fine della coda
			ITokenFifoList tl = new TokenFifoList();
			while ((tok = ll.readCoda()) != null) {
				tl.insertFifoToken(tok);
			}
			res = tl.toString();
			resExp = "(  |  i  |  =  |  0  |  ;  |  i  |  <  |  10  |  ;  |  i  |  ++  |  )  |  {  |  break  |  ;  |  }  ";
			assertTrue(" lettura coda:", res.equals(resExp));

			// ora comincio a togliere i token dalla fifo.
			res = "";
			while ((tok = tl.getFifoToken()) != null)
				res = res + tok.toString();
			resExp = "(i=0;i<10;i++){break;}";
			assertTrue("svuotata coda:", res.equals(resExp));

		} catch (LexerException e) {
			fail(e.getMessage());
		}
	}// testLL

}
