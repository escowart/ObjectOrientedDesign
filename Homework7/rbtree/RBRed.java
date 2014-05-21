package rbtree;



/** An class representing a Red color 
 *  @author Edwin Cowart
 *  @version 2013.11.9 */
public class RBRed implements IRBColor {
    /** Is the given object equivalent to this Red
     * @param obj : Any Object in Java
     * @return : A boolean of whether the given 
     *  Object equivalent to this class */
    public boolean equals(Object obj) {
        return (obj instanceof RBRed);
    }
    
    /** Get the hashCode of the Red
     * @return : int 1 for Red */
    public int hashCode() {
        return this.toString().hashCode();
    }
    
    /** Is this Red Color Black 
     * @return : false */
    public boolean isBlack() {
        return false;
    }
    
    /** Is this Red Color Red Color 
     * @return : true */
    public boolean isRed() {
        return true;
    }
    
    /** Convert this class to String form
     * @return : A String of this class name */
    public String toString() {
        return "RED";
    }
}
