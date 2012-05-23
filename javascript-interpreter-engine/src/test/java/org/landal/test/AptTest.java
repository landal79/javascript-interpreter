package org.landal.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.landal.apt.*;

/**
 * Questa classe serve per testare le classi del package apt.
 * @author Alex Landini
 */
public class AptTest {
    
    @Test
    public void testExp(){
        //variabili per risultati parziali
        String res = null, resExp = null;
        
        //StringaExp
        StringaExp s = new StringaExp("ciao");
        res = s.toString();
        resExp=new String("ciao");
        assertTrue("StringaExp: ",res.equals(resExp));
        
        
        //postDec
        PostDecExp e = new PostDecExp(new IdentExp("i"));
        res = e.toString();
        resExp="i --";
        assertTrue("StringaExp: ",res.equals(resExp));
        
    }//testExp

    
}
