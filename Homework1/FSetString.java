import java.util.ArrayList;
/** Edwin Cowart
 * escowart@css.neu.edu
 */

/**
 * An abstract class representing a set of strings
 * 
 * @author Edwin Cowart
 * @version 2013.9.15
 */
public abstract class FSetString {

    /** Make a new empty set 
     * @return : the EmptyFSS */
    public static FSetString emptySet() {
        return new EmptyFSS();
    }

    /** Insert the String into the FSetString 
     * @param fss : Any FSeString
     * @param str : Any String
     * @return : A new InsertFSS with str inserted
     */
    public static FSetString insert(FSetString fss, String str) {
        if (fss.containsI(str)) {
            return fss;
        } 
        else {
            return fss.insertI(str);
        }
    }

    /** Add the String to the FSetString if it is not already present
     * @param fss : Any FSeString
     * @param str : Any String
     * @return : fss with str added if not already present in set
     */
    public static FSetString add(FSetString fss, String str) {
        if (fss.containsI(str)) {
            return fss;
        } 
        else {
            return fss.insertI(str);
        }
    }

    /** Find the size of the FSetString
     * @param fss : Any FSetString
     * @return : The number of elements in fss
     */
    public static int size(FSetString fss) {
        return fss.sizeI();
    }

    /** Is the FSetString empty? 
     * @param fss : Any FSetString
     * @return : Whether the FSetString is the EmptyFSS
     */
    public static boolean isEmpty(FSetString fss) {
        return fss.isEmptyI();
    }

    /** Does the FSetString contain the String?  
     * @param fss : Any FSetString
     * @param str : Any String
     * @return : Whether the FSetString is the EmptyFSS
     */
    public static boolean contains(FSetString fss, String str) {
        return fss.containsI(str);
    }

    /** Is the given FSetString a subset of this FSetString? 
     * @param a : Any FSetString
     * @param b : Any FSEtString
     * @return : Is b a subset of a?
     */
    public static boolean isSubset(FSetString a, FSetString b) {
        return a.isSubsetI(b);
    }

    /** Is the String absent from the FSetString? 
     * @param fss : Any FSetString
     * @param str : Any String
     * @return : return the FSetString without str
     */
    public static FSetString absent(FSetString fss, String str) {
        return fss.absentI(str);
    }

    /** Find the Union the two FSetString 
     * @param a : Any FSetString
     * @param b : Any FSetString
     * @return : A FSetString which is the union of the two
     */
    public static FSetString union(FSetString a, FSetString b) {
        return a.unionI(b);
    }

    /** Find the Intersect of the two FSetString 
     * @param a : Any FSetString
     * @param b : Any FSetString
     * @return : A FSetString which is the intersect of the two
     */
    public static FSetString intersect(FSetString a, FSetString b) {
        return a.intersectI(b);
    }

    /** Return the FSetString in String format 
     * @return : The FSetString in String Form
     */
    public abstract String toString();

    /** Is the given object equal to the FSetString 
     *@param o : Any Object
     *@return : Whether o is equal to the FSetString
     */
    public abstract boolean equals(Object o);

    /** The HashCode of the FSetString 
     * @return : The hashCode of the FSetString
     */
    public abstract int hashCode();

    /** The size of the FSetString 
     * @param str : Any String
     * @return : A new FSetString with str inserted as an element
     */
    protected abstract FSetString insertI(String str);

    /** The size of the FSetString
     * @param str : Any String
     * @return : Whether this FSetString
     */
    protected abstract boolean containsI(String str);

    /** The size of the FSetString
     * @return : The number of elements in the FSetString
     */
    protected abstract int sizeI();

    /** Is the FSetString empty? 
     * @return : Whether the FSetString is the EmptyFSS
     */
    protected abstract boolean isEmptyI();

    /** Is the FSetString a subset of this one? 
     * @param fss : Any FSetString
     * @return : Is fss a subset of this FSetString
     */
    protected abstract boolean isSubsetI(FSetString fss);

    /** The union of this FSetString and the given 
     * @param fss : Any FSetString
     * @return : A FSetString which is the union of this FSetString and fss
     */
    protected abstract FSetString unionI(FSetString fss);

    /** The intersect of this FSetString and the given 
     * @param fss : Any FSetString
     * @return : the intersection of fss and this FSetString
     */
    protected abstract FSetString intersectI(FSetString fss);
    
    /** Remove the given string from the FSetString
     * @param str : Any String
     * @return : The FSetString with the string removed
     */
    protected abstract FSetString absentI(String str);
}

/**
 * A class representing the empty creator which is the Empty set part of the
 *   Implementation of the FSetString ADT class
 * @author Edwin Cowart
 * @version 2013.9.17
 */
class EmptyFSS extends FSetString {

    /**
     * Does the EmptyFSS contain the given String
     * @param str : Any String
     * @return : false
     */
    protected boolean containsI(String str) {
        return false;
    }

    /**
     * Does the EmptyFSS contain the given String i.e. false
     * @param fss : Any FSetString
     * @return : true
     */
    protected boolean isSubsetI(FSetString fss) {
        return true;
    }

    /**
     * The Union of the EmptyFSS and the given FSetString
     * @param fss : Any FSetString
     * @return : fss
     */
    protected FSetString unionI(FSetString fss) {
        return fss;
    }

    /**
     * The Intersect of the EmptyFSS and the given FSetString
     * @param fss : Any FSetString
     * @return : this
     */
    protected FSetString intersectI(FSetString fss) {
        return this;
    }

    /** The EmptyFSS in String format 
     * @return : Class in String Form 
     */
    public String toString() {
        return "{...(0 elements)...}";
    }

    /**
     * Is the given Object equal to the EmptyFSS
     * @param o : Any Object in Java
     * @return : true if o is the EmptyFSS
     */
    public boolean equals(Object o) {
        return o instanceof EmptyFSS;
    }

    /** The hashCode of the EmptyFSS 
     * @return : 0
     */
    public int hashCode() {
        return 0;
    }

    /**
     * Insert the given String into the set by creating a Insert
     * @param str : Any String
     * @return : A new InsertFSS which gets str passed into it
     */
    protected FSetString insertI(String str) {
        return new InsertFSS(str);
    }

    /** The Size of the EmptyFSS 
     * @return : 0 (it has no elements)
     */
    protected int sizeI() {
        return 0;
    }

    /** Is the EmptyFSS a EmptyFSS 
     * @return : true
     */
    protected boolean isEmptyI() {
        return true;
    }
    
    /** Remove the given string from the EmptyFSS
     * @param str : Any String
     * @return : this
     */
    protected FSetString absentI(String str) {
        return this;
    }
}


/**
 * A class representing the insert creator which is the non-Empty set part of
 * the Implementation of the FSetString ADT class
 * 
 * @author Edwin Cowart
 * @version 2013.9.17
 * */
class InsertFSS extends FSetString {

    private ArrayList<String> set = new ArrayList<String>();

    /**
     * The Constructor for creating an InsertFSS with one element
     * @param str : Any String
     */
    protected InsertFSS(String str) {
        this.set = new ArrayList<String>();
        this.set.add(str);
    }
    
    /**
     * The Constructor for creating an InsertFSS with the give ArrayList
     * @param slist : Any ArrayList of String
     */
    protected InsertFSS(ArrayList<String> slist) {
        this.set = slist;
    }
    
    /**
     * Clone this class so mutation does not effect it
     * @return : A clone of this class 
     */
    protected InsertFSS iClone() {
        ArrayList<String> alist = new ArrayList<String>();
        for (String s : this.set) {
            alist.add(s);
        }
        return new InsertFSS(alist);
    }

    /**
     * Does the InsertFSS contain the given String
     * @param str : Any String
     * @return : whether the InsertFSS contains str
     */
    protected boolean containsI(String str) {
        return this.set.contains(str);
    }

    /**
     * Is the given FSetString a subset of this InsertFSS
     * @param fss : Any FSetString
     * @return : Is fss a subset of this InsertFSS
     */
    protected boolean isSubsetI(FSetString fss) {
        if (fss.isEmptyI()) {
            return false;
        }
        InsertFSS iset = (InsertFSS)fss;
        for (String s : this.set) {
            if (!iset.set.contains(s)) {
                return false;
            } 
        }
        return true;
    }

    /**
     * Take the Union of the InsertFSS and the given FSetString
     * @param fss : Any FSetString
     * @return : A FSetString which is the union of this FSetString and fss
     */
    protected FSetString unionI(FSetString fss) {
        InsertFSS c = iClone();
        if (fss.isEmptyI()) {
            return c;
        } 
        else {
            InsertFSS p = ((InsertFSS) fss).iClone();
            for (String s : c.set) {
                if (p.containsI(s)) {
                    p.set.remove(s);
                }
            }
            this.set.addAll(p.set);
            return this;
        }
    }

    /**
     * Take the Intersect of the InsertFSS and the given FSetString
     * @param fss : Any FSetString
     * @return : A FSetString of the intersect between this InsertFSS and fss
     */
    protected FSetString intersectI(FSetString fss) {
        InsertFSS c = iClone();
        if (fss.isEmptyI()) {
            return fss;
        } 
        else {
            InsertFSS p = ((InsertFSS) fss).iClone();
            for (String s : c.set) {
                if (p.containsI(s)) {
                    p.set.remove(s);
                } 
                else {
                    c.set.remove(s);
                }
            }
            return c;
        }
    }

    /** Convert the NotEmpFSS in String format 
     * @return : The class in String form
     */
    public String toString() {
        String str = "{...(" + this.sizeI() + " elements" + ")...}";
        return str;
        
    }

    /** Is the given object equal to this InsertFSS 
     * @param o : Any Object
     * @return : Is o the same object as this InsertFSS 
     */
    public boolean equals(Object o) {
        if (o instanceof InsertFSS) {
            InsertFSS fss = (InsertFSS) o;
            return this.isSubsetI(fss) && fss.isSubsetI(this);
        }
        return false;
    }

    /** The hashCode of the InsertFSS 
     * @return : The hashCode of this InsertFSS 
     */
    public int hashCode() {
        return this.set.hashCode();
    }

    /** Insert a new String into the set 
     * @param str : Any String
     * @return : insert the str in this InsertFSS
     */
    protected FSetString insertI(String str) {
        InsertFSS c = iClone();
        c.set.add(str);
        return c;
    }

    /** Return the size of the set 
     * @return : The number of elements in this InsertFSS 
     */
    protected int sizeI() {
        return this.set.size();
    }

    /** Is this the EmptyFSS? 
     * @return : false
     */
    protected boolean isEmptyI() {
        return false;
    }
    
    /** Remove the given string from the FSetString
     * @param str : Any String
     * @return : This InsertFSS if their are still elements 
     *    left after removing str otherwise the EmptyFSS is returned
     */
    protected FSetString absentI(String str) {
        InsertFSS c = iClone();
        c.set.remove(str);
        if (c.sizeI() == 0) {
            return new EmptyFSS();
        } 
        else {
            return c;
        }
    }

}
