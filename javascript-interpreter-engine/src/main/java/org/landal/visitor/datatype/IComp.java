package org.landal.visitor.datatype;

/**
 * Rappresenta una genrica componente con un indice che indica la posizione
 * e un campo valore.
 * @author  Alex Landini
 */
public interface IComp {
    
    /**
     * Restituisce l'indice della componente.
     * @return  int     indice della componente.
     */
    public int getIndice();

    /**
     * imposta l'indice della componente.
     * @param   i   indice del componente.
     */
    public void setIndice(int i);

    /**
     * Reperisce il valore della componente.
     * @return  Object   valore della componente.
     */
    public Object getVal();

    /**
     * Imposta il valore della componente.
     * @param   o   oggetto da inserire come valore della componente.
     */
    public void setVal(Object o);

    /**
     * Fornisce una rappresentazione esterna della componente.
     * @return  String  rappresentazione esterna della componente.
     */
    public String toString();
}
