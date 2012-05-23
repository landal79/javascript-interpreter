package org.landal.visitor.datatype;

import org.landal.utility.IIterator;

/**
 * Questa classe rappresenta una implementazione del tipo vettore di javascript.
 * Il vettore in javascript � un'entit� dinamica che pu� contenere qualsiasi cosa.
 * @author  Landini Alex
 * @version 1.0
 */
public class Vettore extends ComplexType{

	/**
	* Questo campo contiene un lista che indica le componenti del vettore, esso
	* conterr� tanti oggetti Comp: costituiti da un campo indice e da uno valore.
	*/
	private ICompList listaComp;

	/**
         * Costruttore di Vettore.
         */
        public Vettore(){

		listaComp = new CompList();
	}

	/**
	* Questo metodo riceve in ingresso un valore, che sar� il valore della componente che
	* verr� appesa in coda al vettore; Il metodo quindi assegna l'indice alla componente.
        * @param    val   valore della componente.
	*/
	public void addComp(Object val){
            
             System.out.println("Aggiungo una componente al vettore: "+val);
            
            IIterator it = listaComp.createIterator();
            
            int i = 0;//mi serve per sapere l'indice
            //conto di quanti elementi � composta la lista
            while (it.hasNext()){
                it.next();
                i++;
            }   
             System.out.println("inserisco una componnte in fondo al vettore: "+val);            
             listaComp.addComp(new Comp(i, val));//appende in coda al vettore

             System.out.println("vettore dopo aver aggiunto componenti: "+listaComp.toString());
	}

	/**
         * Questo metodo inserisce una componente che viene ricevuta in ingresso
         * @param   comp   componente da inserire
         */
        public void addIComp(IComp comp){
            System.out.println(" aggiungo componente al vettore");               
		listaComp.addComp(comp);//appende in coda al vettore
                System.out.println("la componente � stata aggiunta");

	}

	/**
         * Questo metodo mi restiuisce una componente del vettore di cui ricevo in ingresso
         * l'indice.
         * @param   n   indice della componente.
         * @return  IComp   componente restituita.
         */
        public IComp getComp(int n){                       

            IComp c;

            IIterator it = listaComp.createIterator();
            
            while (it.hasNext()){
                c = (IComp) it.next();
               if (c.getIndice() == n ) return c;
            }//while
 
            return null;// la componente non esiste non esiste		

	}//fine getProp

	/**
	* Questo metodo modifica le componente di un array, se la componente non esiste
	* la aggiungo alla lista, mantenendole ordinate rispetto al campo indice del componente.
        * @param    n   indice della componente
        * @param    val   valore da assegnare alla componente.
	*/
	public void modifyComp (int n, Object val){
            
            IComp c;
            //creo un iterator per scorrere la lista
            IIterator it = listaComp.createIterator();
            //scandisco la lista per vedere se l'elemento esiste
            while (it.hasNext()){
                c = (IComp) it.next();
                if (c.getIndice() == n){
                    //ho trovato la componente di indice n
                    c.setVal(val);
                    return;
                }
                else if (c.getIndice() > n){
                    //la componente ha indice maggiore di n
                    //allora la inserisco nella posizione corrente
                    listaComp.insert(new Comp(n,val));
                    return;
                }

            }//while
            //il vettore � finito, allora creo una nuova componente in coda al vettore
            listaComp.addComp(new Comp(n,val));
                               		
	}//fine modifyComp

	/**
	* Questo metodo verifica se esiste la componente il cui indice � passato come parametro.
        * @param    n   indice della componente da cercare
        * @return   boolean     valore che mi indica se la componente esiste.
	*/
	public boolean verifyIndice(int n){

            IComp c;

            IIterator it = listaComp.createIterator();
            
            while (it.hasNext()){
                c = (IComp) it.next();
               if (c.getIndice() == n ) return true;
            }//while
 
            return false;// la componente non esiste non esiste	

	}//fine verifyIndice
                
	/**
         * Fornisce una rappresentazione esterna del vettore.
         * @return  String  rappresentazione esterna del vettore.
         */
        public String toString() {
		return listaComp.toString();
	}


}
