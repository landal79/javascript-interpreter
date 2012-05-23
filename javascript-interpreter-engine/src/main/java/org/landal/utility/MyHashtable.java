package org.landal.utility;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Questa classe eredita da hashtable tutti i comportamenti, si essa specializza solo la stampa.
 * @author  Landini Alex
 * @version 1.0
 */
 public class MyHashtable extends Hashtable{

        /**
         * Costruttore di MyHashtable
         */
 	public MyHashtable(){
            super();
        }

 	/**
         * Rappresentazione esterna della hashtable diversa da quella di Hashtable.
         * @return  String  rappresentazione esterna.
         */
        public String toString(){

            Enumeration en1=this.elements();
            Enumeration en2=this.keys();
            Object o;
            String s = new String();
            String str = new String();
            while (en1.hasMoreElements()){
                    o = en1.nextElement();
                    s = (String) en2.nextElement();
                    str= str + s +" = "+o.toString()+"\n";
                    }

            return str;

        }//fine toString

 }