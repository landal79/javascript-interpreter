package org.landal.visitor.datatype;

import org.landal.utility.IList;

/**
 * Questa interfaccia rappresenta una lista di propriet� di un oggetto
 * @author  Alex Landini
 */
public interface IPropList extends IList{
    
    /**
     * Reperisce una propriet� dalla posizione attuale.
     * @return Prop   propriet�.
     */
    public IProp getProp();
    
    /**
     * Appende una propriet� in fondo alla lista.
     * @param   p   propriet� da inserire.
     */
    public void addProp (IProp p);
    
}
