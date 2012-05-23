package org.landal.parser;

import org.landal.apt.IApt;
import org.landal.lexer.ILexer;

/**
 *  Questa interfaccia definisce le operazione che servono per compiere 
 *  l'analisi sintattica di una frase di un linguaggio, il parser che la implementa
 *  deve essere un parser che costruisce un albero di derivazione .
 *  
 * @author  Alex Landini
 * @version 1.0
 */
public interface IParser {
    
    /**
     * Questo metodo effettua l'analisi sintattica della frase. 
     * di ingresso.
     * @param lex    rappresenta un riferimento a un analizzatore lessicale.
     * @throws ParserException  rappresenta il generico errore che si pu� verificare durante l'analisi sintattica
     */ 
    public void parse ();
    
    /**
     * Questo metodo restituisce un oggetto interfaccia IApt che rappresenta l'albero di derivazione
     * costruito dal parser, se l'analisi sintattica � andata a buon fine altrimenti genera un eccezzione
     * che indica il tipo di errore che si � verificato.     
     * @return  IApt    restituisce l'albero di derivazione costruito dal parser. 
     * @throws  ParserException     indicazione di errore durante l'analisi sintattica  
     */ 
    public IApt getApt() throws ParserException; 
    
    /**
     * Questo metodo ritorna lo stato del parser e contiene eventuali indicazioni di errore nel caso 
     * si sia verificato qualche inconveniente durante l'analisi sintattica.
     * @return  String  messaggio che rappresenta lo stato del Parser.
     */
    public String getStato();
    
    /**
     * Questo metodo resituisce il risultato sotto forma di stringa, cio� una rappresentazione esterna dell'albero
     * di derivazione costruito dal parser.
     */ 
    public String getResultAsString();
    
    /**
     * Questo metodo permette di impostare il lexer per la scansione, oppure di cambiare il lexer da
     * cui il parser riceve i token.
     * @param lex    rappresenta un riferimento a un analizzatore lessicale.
     */
    public void setLexer(ILexer lex); 
        
       
}
