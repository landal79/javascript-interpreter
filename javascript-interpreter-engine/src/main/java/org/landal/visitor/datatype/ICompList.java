package org.landal.visitor.datatype;

import org.landal.utility.IList;

/**
 * Questa interfaccia definisce il tipo lista di componenti con indice.
 * @author  Alex Landini
 */
public interface ICompList extends IList{
    
     /**
     * Reperisce una componente dalla posizione attuale.
     * @return Comp   propriet�.
     */
    public IComp getComp();
    
    /**
     * Appende una componente in fondo alla lista.
     * @param   c   propriet� da inserire.
     */
    public void addComp (IComp c);
    
}
