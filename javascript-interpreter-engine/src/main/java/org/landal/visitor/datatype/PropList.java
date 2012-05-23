package org.landal.visitor.datatype;

import org.landal.utility.IIterator;
import org.landal.utility.List;

/**
 * Questa classe implementa il tipo lista di propriet�.
 * @author  Alex Landini
 */
public class PropList extends List implements IPropList{
    
    
    /**
     * Costruttore di PropList.
     */
    public PropList() {
        super();
    }
    
    /**
     * Reperisce una propriet� dalla posizione attuale.
     * @return Prop   propriet�.
     */
    public IProp getProp(){
        return (IProp) super.get();
    }
    
    /**
     * Appende una propriet� in fondo alla lista.
     * @param   p   propriet� da inserire.
     */
    public void addProp (IProp p){
         super.append(p);       
    }
    
    
    /**
     * Una rappresetazione esterna.
     * Racchiude le componenti del vettore fra '[' , ']'
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

        return "{ "+str+" }";

    }//fine toString
}
