

import java.util.Comparator;

/** A Class Representing a Comparator that compares Integers by their values
*  @author Edwin Cowart
*  @version 2013.11.14 */
public class IntByVal implements Comparator<Integer> {

    /** Compare the values of the two Integers
     * @param i0 : Any Integer 
     * @param i1 : Any Integer
     * @return : 
     * - 0 if their values are equal
     * - -int if the second Integer's is greater 
     * - +int if the second Integer's is greater */
    public int compare(Integer i0, Integer i1) {
        return i0.compareTo(i1);
    }
    
    /** Is the given object equal to the IntByVal Comparator
     * @param obj : Any Object
     * @return : boolean of whether the given object is a IntByVal */
    public boolean equal(Object obj) {
        return obj instanceof IntByVal;
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
        return "IntByVal";
    }
}