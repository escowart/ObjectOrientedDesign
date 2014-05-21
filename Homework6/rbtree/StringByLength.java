package rbtree;



import java.util.Comparator;

/** A Class Representing a Comparator that compares Strings by their lengths
*  @author Edwin Cowart
*  @version 2013.10.19 */
class StringByLength implements Comparator<String> {

    /** Compare the lengths of the two given strings
     * @param s0 : Any String 
     * @param s1 : Any String
     * @return : 
     * - 0 if their lengths are equal
     * - -int if the first String's, s0's, length is shorter
     * - +int if the first String's, s0's, length is longer */
    public int compare(String s0, String s1) {
        return s0.length() - s1.length();
    }
    
    /** Is the given object equal to the StringByLength Comparator
     * @param obj : Any Object
     * @return : boolean of whether the given object is a StringByLength */
    public boolean equal(Object obj) {
        return obj instanceof StringByLength;
    }
    

    /**
     * There is only one instance of this class so its hashCode is constant
     * @return : the hash code is the same for all instances
     */
    public int hashCode() {
        return (this.toString().hashCode());
    }

    /**
     * Convert this class into a String form
     * @return : String name of Comparator
     */
    public String toString() {
        return "StringByLength";
    }
    
}