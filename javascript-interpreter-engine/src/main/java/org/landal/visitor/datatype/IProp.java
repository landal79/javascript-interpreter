package org.landal.visitor.datatype;

/**
 * Questa interfaccia definisce il tipo propriet�, come per esempio propriet�
 * di un oggetto definita da un nome e dal relativo valore.
 * @author  Alex
 */
public interface IProp {
    
    /**
     * Restituisce il nome della propriet�.
     * @return  String  nome della propriet�.
     */
    public String getNome();

    /**
     * Imposta il nome della propriet�.
     * @param   name  nome della propriet�.
     */
    public void setNome(String name);

    /**
     * Restituisce il valore della propriet�.
     * @return Object   valore della propriet�.
     */
    public Object getVal();

    /**
     * Imposta il valore della propriet�.
     * @param   o   oggetto da inserire come valore della propriet�.
     */
    public void setVal(Object o);

    /**
     * Rappresentazione esterna della propriet�.
     * @return  String  rappresentazione esterna.
     */
    public String toString();
    
}
