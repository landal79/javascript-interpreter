package org.landal.token;

/**
 * Classe che implementa l'interfaccia ITokenFactory, quindi crea concretamente
 * le istanze di token.
 * @author  Alex Landini
 * @version 1.0
 */
public class TokenFactory implements ITokenFactory{
    
    /** 
     * Costruttore di TokenFactory
     */
    public TokenFactory() {
    }    
    
    /**
     * Crea il token And.
     * @return IToken    rappresenta il tipo token And.
     */
    public IToken createAndToken(){
        return new AndToken();
    }
    
    /**
     * Crea il token Boolean.
     * @param   val valore specifico del token.
     * @return IToken    rappresenta il tipo token Boolean.
     */
    public IToken createBooleanToken(String val){
        return new BooleanToken(val);
    }
    
    /**
     * Crea il token Break.
     * @return IToken    rappresenta il tipo token Break.
     */
    public IToken createBreakToken(){
        return new BreakToken();
    }
    
    /**
     * Crea il token Case.
     * @return IToken    rappresenta il tipo token Case.
     */
    public IToken createCaseToken(){
        return new CaseToken();
    }
    
    /**
     * Crea il token CloseParG.
     * @return IToken    rappresenta il tipo token '}'.
     */        
    public IToken createCloseParGToken(){
        return new CloseParGToken();
    }
    
    /**
     * Crea il token CloseParQ.
     * @return IToken    rappresenta il tipo token ']'.
     */
    public IToken createCloseParQToken(){
        return new CloseParQToken();
    }
    
    /**
     * Crea il token CloseParT.
     * @return IToken    rappresenta il tipo token ')'.
     */
    public IToken createCloseParTToken(){
        return new CloseParTToken();
    }
    
    /**
     * Crea il token Colon.
     * @return IToken    rappresenta il tipo token ':'.
     */
    public IToken createColonToken(){
        return new ColonToken();
    }
    
    /**
     * Crea il token Comma.
     * @return IToken    rappresenta il tipo token ','.
     */
    public IToken createCommaToken(){
        return new CommaToken();
    }
    
    /**
     * Crea il token Continue.
     * @return IToken    rappresenta il tipo token 'continue'.
     */
    public IToken createContinueToken(){
        return new ContinueToken();
    }
	
    /**
     * Crea il token Dec.
     * @return IToken    rappresenta il tipo token '--'.
     */
    public IToken createDecToken(){
        return new DecToken();
    }
   
    /**
     * Crea il token Default.
     * @return IToken    rappresenta il tipo token 'default'.
     */
    public IToken createDefaultToken(){
        return new DefaultToken();
    }
    
    /**
     * Crea il token Div.
     * @return IToken    rappresenta il tipo token '/'.
     */
    public IToken createDivToken(){
        return new DivToken();
    }
    
    /**
     * Crea il token Do.
     * @return IToken    rappresenta il tipo token 'do'.
     */
    public IToken createDoToken(){
        return new DoToken();
    }
    
    /**
     * Crea il token Dot.
     * @return IToken    rappresenta il tipo token '.'.
     */
    public IToken createDotToken(){
        return new DotToken();
    }
    
    /**
     * Crea il token EMark.
     * @return IToken    rappresenta il tipo token '!'.
     */
    public IToken createEMarkToken(){
        return new EMarkToken();
    }
    
    /**
     * Crea il token Else.
     * @return IToken    rappresenta il tipo token 'else'.
     */
    public IToken createElseToken(){
        return new ElseToken();
    }
    
    /**
     * Crea il token Equal.
     * @return IToken    rappresenta il tipo token '=='.
     */
    public IToken createEqualToken(){
        return new EqualToken();
    }
    
    /**
     * Crea il token For.
     * @return IToken    rappresenta il tipo token 'for'.
     */
    public IToken createForToken(){
        return new ForToken();
    }
    
    /**
     * Crea il token Function.
     * @return IToken    rappresenta il tipo token 'function'.
     */
    public IToken createFunctionToken(){
        return new FunctionToken();
    }
    
    /**
     * Crea il token GreatEq.
     * @return IToken    rappresenta il tipo token '>='.
     */
    public IToken createGreatEqToken(){
        return new GreatEqToken();
    }
    /**
     * Crea il token Greater.
     * @return IToken    rappresenta il tipo token '>'.
     */
    public IToken createGreaterToken(){
        return new GreaterToken();
    }
    
    /**
     * Crea il token Ident.
     * @param  name   stringa che indica il nome dell'identificatore.
     * @return IToken    rappresenta il tipo token identificatore.
     */
    public IToken createIdentToken(String name){
        return new IdentToken(name);
    }
    
    /**
     * Crea il token If.
     * @return IToken    rappresenta il tipo token 'if'.
     */
    public IToken createIfToken(){
        return new IfToken();
    }
    
    /**
     * Crea il token In.
     * @return IToken    rappresenta il tipo token 'In'.
     */
    public IToken createInToken(){
        return new InToken();
    }
    
    /**
     * Crea il token Inc.
     * @return IToken    rappresenta il tipo token '++'.
     */
    public IToken createIncToken(){
        return new IncToken();
    }
    
    /**
     * Crea il token Instanceof.
     * @return IToken    rappresenta il tipo token 'instanceof'.
     */
    public IToken createInstanceofToken(){
        return new InstanceofToken();
    }
    
    /**
     * Crea il token LowEq.
     * @return IToken    rappresenta il tipo token '<='.
     */
    public IToken createLowEqToken(){
        return new LowEqToken();
    }
    
    /**
     * Crea il token Lower.
     * @return IToken    rappresenta il tipo token '<'.
     */
    public IToken createLowerToken(){
        return new LowerToken();
    }
    /**
     * Crea il token Minus.
     * @return IToken    rappresenta il tipo token '-'.
     */
    public IToken createMinusToken(){
        return new MinusToken();
    }
    
    /**
     * Crea il token Mod.
     * @return IToken    rappresenta il tipo token '%'.
     */
    public IToken createModToken(){
        return new ModToken();
    }
    
    /**
     * Crea il token Mul.
     * @return IToken    rappresenta il tipo token '*'.
     */
    public IToken createMulToken(){
        return new MulToken();
    }
    
    /**
     * Crea il token New.
     * @return IToken    rappresenta il tipo token 'new'.
     */
    public IToken createNewToken(){
        return new NewToken();
    }
    
    /**
     * Crea il token NotEqual.
     * @return IToken    rappresenta il tipo token '!='.
     */
    public IToken createNotEqualToken(){
      return new NotEqualToken();
    }
    
    /**
     * Crea il token Null.
     * @return IToken    rappresenta il tipo token 'null'.
     */
    public IToken createNullToken(){
        return new NullToken();
    }
    
    /**
     * Crea il token Num.
     * @param   val      valore del numero
     * @return IToken    rappresenta il tipo token numero.
     */
    public IToken createNumToken(String val){
        return new NumToken(val);
    }
    
    /**
     * Crea il token OperParG.
     * @return IToken    rappresenta il tipo token '{'.
     */
    public IToken createOpenParGToken(){
        return new OpenParGToken();
    }
    
    /**
     * Crea il token OperParQ.
     * @return IToken    rappresenta il tipo token '['.
     */
    public IToken createOpenParQToken(){
        return new OpenParQToken();
    }
    
    /**
     * Crea il token OperParT.
     * @return IToken    rappresenta il tipo token '('.
     */
    public IToken createOpenParTToken(){
        return new OpenParTToken();
    }
    
    /**
     * Crea il token Or.
     * @return IToken    rappresenta il tipo token '||'.
     */
    public IToken createOrToken(){
        return new OrToken();
    }
    
    /**
     * Crea il token Plus.
     * @return IToken    rappresenta il tipo token '+'.
     */
    public IToken createPlusToken(){
        return new PlusToken();
    }
    
     /**
     * Crea il token QMark.
     * @return IToken    rappresenta il tipo token '?'.
     */
    public IToken createQMarkToken(){
        return new QMarkToken();
    }
    
     /**
     * Crea il token Return.
     * @return IToken    rappresenta il tipo token 'return'.
     */
    public IToken createReturnToken(){
        return new ReturnToken();
    }
    
     /**
     * Crea il token Semicolon.
     * @return IToken    rappresenta il tipo token ';'.
     */
    public IToken createSemicolonToken(){
        return new SemicolonToken();
    }
    
     /**
     * Crea il token Stringa.
     * @param   val     valore della stringa      
     * @return IToken    rappresenta il tipo token stringa.
     */
    public IToken createStringaToken(String val){
        return new StringaToken(val);
    }
    
     /**
     * Crea il token Switch.
     * @return IToken    rappresenta il tipo token 'switch'.
     */
    public IToken createSwitchToken(){
        return new SwitchToken();
    }
    
     /**
     * Crea il token This.
     * @return IToken    rappresenta il tipo token 'this'.
     */
    public IToken createThisToken(){
        return new ThisToken();
    }
    
     /**
     * Crea il token Uguale.
     * @return IToken    rappresenta il tipo token '='.
     */
    public IToken createUgualeToken(){
        return new UgualeToken();
    }
    
     /**
     * Crea il token Var.
     * @return IToken    rappresenta il tipo token 'var'.
     */
    public IToken createVarToken(){
        return new VarToken();
    }
    
     /**
     * Crea il token While.
     * @return IToken    rappresenta il tipo token 'while'.
     */
    public IToken createWhileToken(){
        return new WhileToken();
    }
    
}//TokenFactory
