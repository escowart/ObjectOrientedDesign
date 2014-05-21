package rbtree;


import java.util.Comparator;

/** A Class Representing a Comparator that compares Strings 
 *   by their lexicographical order
*  @author Edwin Cowart
*  @version 2013.11.14 */
public class StringByLex implements Comparator<String> {

    /** Compare the lexicographical order of the two given strings
     * @param s0 : Any String 
     * @param s1 : Any String
     * @return : 
     * - 0 if their the same String 
     * - -int if the first String, s0, comes first 
     * - +int if the first String, s0, comes second */
    public int compare(String s0, String s1) {
        return s0.compareTo(s1);
    }
    
    /** Is the given object equal to the StringByLex Comparator
     * @param obj : Any Object
     * @return : boolean of whether the given object is a StringByLex */
    public boolean equal(Object obj) {
        return obj instanceof StringByLex;
    }
    

    /**
     * There is only one instance of this class so its hashCode is constant
     * @return : the hash code is the same for all instances
     */
    public int hashCode() {
        return this.toString().hashCode();
    }

    /**
     * Convert this class into a String form
     * @return : String name of Comparator
     */
    public String toString() {
        return "StringByLex";
    }
}