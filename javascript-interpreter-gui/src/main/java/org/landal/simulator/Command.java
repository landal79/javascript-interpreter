package org.landal.simulator;


import java.io.StringReader;

import org.landal.iodevice.ICommand;
import org.landal.lexer.ILexer;
import org.landal.lexer.Lexer;
import org.landal.parser.IParser;
import org.landal.parser.JavascriptParser;
import org.landal.token.TokenFactory;

/**
 * Questa classe rappresenta il generico comando per 
 * la particolare applicazione che si vuole creare cio� di analisi delle frasi
 * di un linguaggio. Questa classe � astratta in quanto imposta solamente le
 * operazioni comuni ai comandi per l'applicazione. 
 * Si � applicato il pattern <i>Template method</i>, infatti le sottoclassi
 * dovranno definire i metodi astratti, che specializzano il comportamento 
 * del metodo pubblico <i>execute</i> che � il metodo che viene chiamato
 * dai pulsanti per eseguire l'azione.
 * @author  Alex
 * @version 1.0
 */
public abstract class Command implements ICommand{
    
    /**
     * Lexer per eseguire l'analisi lessicale.
     */
    protected ILexer lex;
    
    /**
     * Parser per eseguire l'analisi sintattica.
     */
    protected IParser par;
    
    /**
     * Stringa che serve per contenere il risultato dell'esecuzione del comando
     */
    protected String res;
    
    /**
     * Costruttore di command. 
     */
    protected Command() {
        //creo il lexer
        lex = new Lexer(new TokenFactory());
        //creo il parser
        par = new JavascriptParser(lex);
        res = null;        
    }
    
     /**
     * Questo metodo riceve in ingresso una stringa e pone il risultato 
     * dell'elaborazione in una stringa.
     * @param input  stringa di input.
     * @return  risultato dell'elaborazione.
     */
    public String execute (String input){
        //inizializzo il lexer;
        lex.init(new StringReader(input));
        //inizializzo il parser
        par.setLexer(lex);
        //resetto il risultato 
        res = null;
        //invoco il metodo astratto
        action();
        return res;
    }//execute
    
    /**
     * metodo astratto che le sottoclassi devono implementare per specializzare
     * il comportamento di <i>execute</i>.
     */
    protected abstract void action();
    
}
