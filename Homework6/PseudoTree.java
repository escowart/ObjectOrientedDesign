

import java.util.Comparator;

/** A Class for implementing BTree which only acts as a shell class
 * @author Edwin Cowart
 * @version 2013.11.1
 */
public class PseudoTree extends BTree {

    /** Constructor for the PseudoTree given a Comparator for Strings
     * @param comp : Any Comparator<String>
     */
    protected PseudoTree(Comparator<String> comp) {
        super(comp);
    }
}
