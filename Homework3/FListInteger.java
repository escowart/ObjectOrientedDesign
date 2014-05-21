
/** A Class Representing an F-List of Integers
 * The outer most element in the list is at 0th index and 
 *  each element inward has an index of 1 + previous index 
 * @author Edwin Cowart
 * @version 2013.9.24 */
abstract class FListInteger {
    
    /** Create an Empty List
     * @return : The EmptyFLI */
    public static FListInteger emptyList() {
        return new EmptyFLI();
    }
    
    /** Add the given Integer to the given FListInteger 
     * @param f : Any FListInteger
     * @param x : Any Integer
     * @return : An AddFLI with f and x as its parameters */
    public static FListInteger add(FListInteger f, Integer x) {
        return new AddFLI(f, x);
    }
    
    /** Is the given FListInteger an Empty List? 
     * @param f : Any FListInteger
     * @return : boolean of whether the given FListInteger is Empty */
    public static boolean isEmpty(FListInteger f) {
        return f.isEmpty();
    }
    
    /** Get the element at the given index of the given FListInteger
     * @param f : Any FListInteger
     * @param n : The index of the element that will be returned
     * @return : An Integer which is the at the given index */
    public static Integer get(FListInteger f, int n) {
        return f.get(n);
    }
    
    /** Set the element given index of the FListInteger to the given Integer.
     * If the FListInteger does not have n elements an exception will be thrown
     * @param f : Any FListInteger
     * @param n : The index of FListInteger that will be set
     * @param y : THe Integer that will be set at the index
     * @return : The modified FListInteger */
    public static FListInteger set(FListInteger f, int n, Integer y) {
        return f.set(n, y);
    }
    
    /** Return the size of the given list
     * @param f : Any FSetInteger
     * @return : An int the size of the FSetInteger */
    public static int size(FListInteger f) {
        return f.size();
    }

    /** Is this FListInteger an Empty List?
     * @return : boolean of whether the list is empty*/
    protected abstract boolean isEmpty();
    
    /** Get the Integer at the given index of the FListInteger
     * @param n : Any int
     * @return : The Integer at the given index */
    protected abstract Integer get(int n);
    
    /** Set the given the element at the given index to the given Integer or
     * Throw an Exception
     * @param n : The index of the element that will be reset
     * @param y : The new Integer that will be set
     * @return : The new FListInteger with the modification */
    protected abstract FListInteger set(int n, Integer y);
    
    /** Return the the size of the list
     * @return : An int which displays the size of FListInteger */
    protected abstract int size();
    
}

/** A Class Representing The Empty FListInteger 
 * @author Edwin Cowart
 * @version 2013.9.24 */
class EmptyFLI extends FListInteger {
    
    /** Is this EmptyFLS an Empty List?
     * @return : boolean of whether the list is empty*/
    protected boolean isEmpty() {
        return true;
    }
    
    /** Throw an exception because there are no indexes in the EmptyFLS
     * @param n : The index
     * @return : throw RuntimeException */
    protected Integer get(int n) {
        throw new RuntimeException("get : The index is not in the List");
    }
    
    /** Throw an exception because there are no indexes in the EmptyFLS
     * @param n : The index of the element that will be reset
     * @param y : The new Integer that will be set
     * @return : throw RuntimeException */
    protected FListInteger set(int n, Integer y) {
        throw new RuntimeException("set : The index is not in the List");
    }
    
    /** Return the the size of the EmptyFLS (0)
     * @return : 0 */
    protected int size() {
        return 0;
    }
    
    /** Convert the EmptyFLI into String Format
     * @return : A String of the EmptyFLI */
    public String toString() {
        return "[]";
    }
    
    /** Is the given Object is the EmptyFLS
     * @param o : Any Object
     * @return : boolean of whether the Objects are equal */
    public boolean equals(Object o) {
        return o instanceof EmptyFLI;
    }
    
    /** Return the hashCode of the EmptyFLS
     * @return : 0 (The size of the list) */
    public int hashCode() {
        return 0;
    }
}

/** A Class Representing The Add FListInteger 
 * @author Edwin Cowart
 * @version 2013.9.24 */
class AddFLI extends FListInteger {
    
    private FListInteger f;
    private Integer x;
    
    /** Create a new AddLI with a FListInteger and an Integer put in the fields
     * @param f : Any FListInteger
     * @param x : Any Integer
     */
    protected AddFLI(FListInteger f, Integer x) {
        this.f = f;
        this.x = x;
    }
    
    /** Is this AddFLS an Empty List 
     * @return : boolean of whether the list is empty*/
    protected boolean isEmpty() {
        return false; 
    }
    
    /** Get the Integer at the given index of the AddFLI
     * @param n : Any int
     * @return : The Integer at the given index */
    protected Integer get(int n) {
        if (n == 0) {
            return this.x;
        }
        else {
            return f.get(n - 1);
        }
    }
    
    /** Set the given the element at the given index to the given Integer
     * @param n : The index of the element that will be reset
     * @param y : The new Integer that will be set
     * @return : The new AddFLI with the modification */
    protected FListInteger set(int n, Integer y) {
        if (n == 0) {
            return new AddFLI(this.f, y);
        }
        else {
            return new AddFLI(this.f.set(n - 1, y), this.x);
        }
    }
    
    /** Return the the size of the list
     * @return : An int which displays the size of AddFLI */
    protected int size() {
        return 1 + f.size();
    }
    
    /** The recursive case for toString
     * @return : A piece of the toString formula */
    protected String toStringRec() {
        if (this.size() == 1) {
            return this.x + "]";
        }
        else {
            AddFLI a = (AddFLI) this.f;
            return this.x.toString() + ", " + a.toStringRec();
        }
    }
    
    /** Convert the AddFLI into String Format
     * @return : A String of the EmptyFLI */
    public String toString() {
        return "[" + this.toStringRec();
    }
    
    /** Is the given Object is this AddFLI?
     * @param o : Any Object
     * @return : boolean of whether the Objects are equal */
    public boolean equals(Object o) {
        if (o instanceof AddFLI) {
            AddFLI a = (AddFLI) o;
            return this.x.equals(a.x) && this.f.equals(a.f);
        }
        else {
            return false;
        }
    }
    
    /** Return the hashCode of the AddFLI
     * @return : 0 (The size of the list) */
    public int hashCode() {
        return this.x.hashCode() + this.f.hashCode();
    }
}