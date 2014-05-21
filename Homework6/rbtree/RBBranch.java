package rbtree;


import java.util.Comparator;

/** A Class representing a Red-Black Branch Node
 *  @author Edwin Cowart
 *  @version 2013.10.31
 */
public class RBBranch extends RBTree {
    /** Constructor for the Red-Black Tree given both a Comparator for Strings 
     *  and a String
     *  This is the constructor for anything other than the root
     * @param comp : Any Comparator<String>
     * @param str : Any String
     */
    protected RBBranch(Comparator<String> comp, String str) {
        super(comp, str, new RBRed());
    }
    
    /** Constructor for the Red-Black Tree given both a Comparator for Strings 
     *  and a String
     *  This is the constructor for anything other than the root
     * @param comp : Any Comparator<String>
     * @param str : Any String
     * @param left : Any RBTree
     * @param right : Any RBTree
     */
    protected RBBranch(Comparator<String> comp, String str, RBBranch left,
            RBBranch right) {
        super(comp, str, new RBBlack(), left, right);
    }
    
    /** If this Red-Black Branch is a Red-Black Root 
     * and its Red-Black color is Red set its color to Black */
    protected void ifRootToBlack() {
        // This does nothing because it is not a Root Node
    }
    
    /** Is this Red-Black Branch a Red-Black Branch
     * @return : A boolean of whether this Red-Black Branch is Red-Black Branch
     */
    protected boolean isBranch() {
        return true;
    }
    
    /** Is this Red-Black Branch an empty tree?
     * @return : false because a Red-Black Branch can never be empty */
    protected boolean isEmpty() {
        return false;
    }
    
    /** Is this Red-Black Branch a Red-Black Root
     * @return : A boolean of whether this Red-Black Branch is Red-Black Root
     */
    protected boolean isRoot() {
        return false;
    }
}
