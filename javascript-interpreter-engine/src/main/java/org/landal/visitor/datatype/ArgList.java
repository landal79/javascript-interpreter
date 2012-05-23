package org.landal.visitor.datatype;

import org.landal.utility.IIterator;
import org.landal.utility.List;

/**
 * Questa classe rappresenta l'astrazione lista di argomenti
 * @author  Alex Landini
 */
public class ArgList extends List{
    
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

        return " "+str+" ";

    }//fine toString
    
}
