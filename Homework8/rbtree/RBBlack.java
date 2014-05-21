package rbtree;


/** An class representing a Black color 
 *  @author Edwin Cowart
 *  @version 2013.11.14 */
public class RBBlack implements IRBColor {
    
    /** Is the given object equivalent equivalent to this Black
     * @param obj : Any Object in Java
     * @return : A boolean of whether the given 
     *  Object equivalent to this class */
    public boolean equals(Object obj) {
        return (obj instanceof RBBlack);
    }
    
    /** Get the hashCode of the Black
     * @return : int of the hashCode for Black */
    public int hashCode() {
        return this.toString().hashCode();
    }
    
    /** Is this Black Color Black 
     * @return : true */
    public boolean isBlack() {
        return true;
    }
    
    /** Is this Black Color Red Color 
     * @return : false */
    public boolean isRed() {
        return false;
    }
    
    /** Convert this class to String form
     * @return : A String of this class name */
    public String toString() {
        return "BLACK";
    }
}