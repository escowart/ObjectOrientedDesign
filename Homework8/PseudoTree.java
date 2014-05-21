

import java.util.Comparator;

/** A Class for implementing BTree which only acts as a shell class
 * @author Edwin Cowart
 * @version 2013.11.14
 * @param <T> : The Type the Tree contains
 */
public class PseudoTree<T> extends BTree<T> {

    /** Constructor for the PseudoTree given a Comparator for Strings
     * @param comp : Any Comparator<String>
     */
    protected PseudoTree(Comparator<T> comp) {
        super(comp);
    }
}
