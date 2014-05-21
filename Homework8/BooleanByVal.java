

import java.util.Comparator;

/** A Class Representing a Comparator that compares Booleans by their value
*  @author Edwin Cowart
*  @version 2013.11.14 */
public class BooleanByVal implements Comparator<Boolean> {

    /** Compare the values of the two Booleans
     * @param b0 : Any Boolean 
     * @param b1 : Any Boolean
     * @return : 
     * - 0 If both Booleans are equal
     * - -int if the first is false and second is true 
     * - +int if the first is true and second is false  */
    public int compare(Boolean b0, Boolean b1) {
        return b0.compareTo(b1);
    }
    
    /** Is the given object equal to the BooleanByVal Comparator
     * @param obj : Any Object
     * @return : boolean of whether the given object is a BooleanByVal */
    public boolean equal(Object obj) {
        return obj instanceof BooleanByVal;
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
        return "BooleanByVal";
    }
}