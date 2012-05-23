package org.landal.visitor.datatype;

import org.landal.utility.IIterator;
import org.landal.utility.List;

/**
 * Questa classe rappresenta il tipo lista di componenti
 * @author  Alex Landini
 */
public class CompList extends List implements ICompList{
    
    /**
     * Costruttore della lista di componenti.
     */
    public CompList() {
        super();
    }
    
    /**
     * Reperisce una componente dalla posizione attuale.
     * @return Comp   propriet�.
     */
    public IComp getComp(){      
        return (IComp) super.get();
    }
    
    /**
     * Appende una componente in fondo alla lista.
     * @param   c   propriet� da inserire.
     */
    public void addComp (IComp c){         
        super.append(c);        
    }
    
    /**
     * Una rappresetazione esterna.
     * Racchiude le componenti del vettore fra '{' , '}'
     * @return  String  rappresentazione esterna.
     */
    public String toString(){

        IIterator it = this.createIterator();
        String str = new String();
        String s =  new String();

        while (it.hasNext()) {
            s = it.next().toString();
            if (str.compareTo("") == 0) str =  s;
                    else str = str + " , "+s;
            }//while

        return "[ "+str+" ]";

    }//fine toString
    
}
