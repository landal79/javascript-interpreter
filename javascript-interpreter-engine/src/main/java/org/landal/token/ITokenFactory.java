package org.landal.token;

/**
 * Questa interffiaccia definisce il costruttore dell'insieme di token.
 * Con questa interfaccia si vuole realizzare il pattern Abstract Factory
 * in modo da rendere un componente indipendente dalla creazione di token
 * particolari.
 * @author  Alex Landini
 * @version 1.0
 */
public interface ITokenFactory {
    
    /**
     * Crea il token And.
     * @return IToken    rappresenta il tipo token And.
     */
    public IToken createAndToken();
    
    /**
     * Crea il token Boolean.
     * @param   val valore specifico del token.
     * @return IToken    rappresenta il tipo token Boolean.
     */
    public IToken createBooleanToken(String val);
    
    /**
     * Crea il token Break.
     * @return IToken    rappresenta il tipo token Break.
     */
    public IToken createBreakToken();
    
    /**
     * Crea il token Case.
     * @return IToken    rappresenta il tipo token Case.
     */
    public IToken createCaseToken();
    
    /**
     * Crea il token CloseParG.
     * @return IToken    rappresenta il tipo token '}'.
     */        
    public IToken createCloseParGToken();
    
    /**
     * Crea il token CloseParQ.
     * @return IToken    rappresenta il tipo token ']'.
     */
    public IToken createCloseParQToken();
    
    /**
     * Crea il token CloseParT.
     * @return IToken    rappresenta il tipo token ')'.
     */
    public IToken createCloseParTToken();
    /**
     * Crea il token Colon.
     * @return IToken    rappresenta il tipo token ':'.
     */
    public IToken createColonToken();
    
    /**
     * Crea il token Comma.
     * @return IToken    rappresenta il tipo token ','.
     */
    public IToken createCommaToken();
    
    /**
     * Crea il token Continue.
     * @return IToken    rappresenta il tipo token 'continue'.
     */
    public IToken createContinueToken();
	
    /**
     * Crea il token Dec.
     * @return IToken    rappresenta il tipo token '--'.
     */
    public IToken createDecToken();
   
    /**
     * Crea il token Default.
     * @return IToken    rappresenta il tipo token 'default'.
     */
    public IToken createDefaultToken();
    
    /**
     * Crea il token Div.
     * @return IToken    rappresenta il tipo token '/'.
     */
    public IToken createDivToken();
    
    /**
     * Crea il token Do.
     * @return IToken    rappresenta il tipo token 'do'.
     */
    public IToken createDoToken();
    
    /**
     * Crea il token Dot.
     * @return IToken    rappresenta il tipo token '.'.
     */
    public IToken createDotToken();
    
    /**
     * Crea il token EMark.
     * @return IToken    rappresenta il tipo token '!'.
     */
    public IToken createEMarkToken();
    
    /**
     * Crea il token Else.
     * @return IToken    rappresenta il tipo token 'else'.
     */
    public IToken createElseToken();
    
    /**
     * Crea il token Equal.
     * @return IToken    rappresenta il tipo token '=='.
     */
    public IToken createEqualToken();
    
    /**
     * Crea il token For.
     * @return IToken    rappresenta il tipo token 'for'.
     */
    public IToken createForToken();
    
    /**
     * Crea il token Function.
     * @return IToken    rappresenta il tipo token 'function'.
     */
    public IToken createFunctionToken();
    
    /**
     * Crea il token GreatEq.
     * @return IToken    rappresenta il tipo token '>='.
     */
    public IToken createGreatEqToken();
    /**
     * Crea il token Greater.
     * @return IToken    rappresenta il tipo token '>'.
     */
    public IToken createGreaterToken();
    
    /**
     * Crea il token Ident.
     * @param  name   stringa che indica il nome dell'identificatore.
     * @return IToken    rappresenta il tipo token identificatore.
     */
    public IToken createIdentToken(String name);
    
    /**
     * Crea il token If.
     * @return IToken    rappresenta il tipo token 'if'.
     */
    public IToken createIfToken();
    
    /**
     * Crea il token In.
     * @return IToken    rappresenta il tipo token 'In'.
     */
    public IToken createInToken();
    
    /**
     * Crea il token Inc.
     * @return IToken    rappresenta il tipo token '++'.
     */
    public IToken createIncToken();
    
    /**
     * Crea il token Instanceof.
     * @return IToken    rappresenta il tipo token 'instanceof'.
     */
    public IToken createInstanceofToken();
    
    /**
     * Crea il token LowEq.
     * @return IToken    rappresenta il tipo token '<='.
     */
    public IToken createLowEqToken();
    
    /**
     * Crea il token Lower.
     * @return IToken    rappresenta il tipo token '<'.
     */
    public IToken createLowerToken();
    /**
     * Crea il token Minus.
     * @return IToken    rappresenta il tipo token '-'.
     */
    public IToken createMinusToken();
    
    /**
     * Crea il token Mod.
     * @return IToken    rappresenta il tipo token '%'.
     */
    public IToken createModToken();
    
    /**
     * Crea il token Mul.
     * @return IToken    rappresenta il tipo token '*'.
     */
    public IToken createMulToken();
    
    /**
     * Crea il token New.
     * @return IToken    rappresenta il tipo token 'new'.
     */
    public IToken createNewToken();
    
    /**
     * Crea il token NotEqual.
     * @return IToken    rappresenta il tipo token '!='.
     */
    public IToken createNotEqualToken();
    
    /**
     * Crea il token Null.
     * @return IToken    rappresenta il tipo token 'null'.
     */
    public IToken createNullToken();
    
    /**
     * Crea il token Num.
     * @param   val      valore del numero
     * @return IToken    rappresenta il tipo token numero.
     */
    public IToken createNumToken(String val);
    
    /**
     * Crea il token OperParG.
     * @return IToken    rappresenta il tipo token '{'.
     */
    public IToken createOpenParGToken();
    
    /**
     * Crea il token OperParQ.
     * @return IToken    rappresenta il tipo token '['.
     */
    public IToken createOpenParQToken();
    
    /**
     * Crea il token OperParT.
     * @return IToken    rappresenta il tipo token '('.
     */
    public IToken createOpenParTToken();
    
    /**
     * Crea il token Or.
     * @return IToken    rappresenta il tipo token '||'.
     */
    public IToken createOrToken();
    
    /**
     * Crea il token Plus.
     * @return IToken    rappresenta il tipo token '+'.
     */
    public IToken createPlusToken();
    
     /**
     * Crea il token QMark.
     * @return IToken    rappresenta il tipo token '?'.
     */
    public IToken createQMarkToken();
    
     /**
     * Crea il token Return.
     * @return IToken    rappresenta il tipo token 'return'.
     */
    public IToken createReturnToken();
    
     /**
     * Crea il token Semicolon.
     * @return IToken    rappresenta il tipo token ';'.
     */
    public IToken createSemicolonToken();
    
     /**
     * Crea il token Stringa.
     * @param   val     valore della stringa      
     * @return IToken    rappresenta il tipo token stringa.
     */
    public IToken createStringaToken(String val);
    
     /**
     * Crea il token Switch.
     * @return IToken    rappresenta il tipo token 'switch'.
     */
    public IToken createSwitchToken();
    
     /**
     * Crea il token This.
     * @return IToken    rappresenta il tipo token 'this'.
     */
    public IToken createThisToken();
    
     /**
     * Crea il token Uguale.
     * @return IToken    rappresenta il tipo token '='.
     */
    public IToken createUgualeToken();
    
     /**
     * Crea il token Var.
     * @return IToken    rappresenta il tipo token 'var'.
     */
    public IToken createVarToken();
    
     /**
     * Crea il token While.
     * @return IToken    rappresenta il tipo token 'while'.
     */
    public IToken createWhileToken();
    
    
}
