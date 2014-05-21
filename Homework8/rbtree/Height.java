package rbtree;


import java.util.Comparator;

/** A Visitor that calculates the Height of Red-Black Trees
 * @author Edwin Cowart
 * @version 2013.11.14
 * @param <T>
 */
public class Height<T> implements RBTreeVisitor<T, Integer> {
    /**
     * Get the Height of the Empty Tree of the given Comparator 
     *  and String for Color
     * @param comp the Comparator for the whole tree
     * @param color the color of the node, which should be "RED"
     *        or "BLACK"
     * @return 0
     */
    public Integer visitEmpty(Comparator<T> comp, String color) {
        return new Integer(0);
    }
    
    /**
     * Get the Height of the Tree of the given Comparator 
     *  and String for Color, Data, and left and right branches
     * @param comp the Comparator for the whole tree
     * @param color the color of the node, which should be "RED"
     *        or "BLACK"
     * @param data the label/value for the node
     * @param left the left subtree of the node
     * @param right the right subtree of the node
     * @return An Integer of the height
     */
    public Integer visitNode(Comparator<T> comp, String color, T data, 
                     RBTree<T> left, RBTree<T> right) {
        if (left != null && right != null) {
            return new Integer(1 + Math.max(left.accept(this),
                    right.accept(this)));
        }
        else if (left != null) {
            return 1 + left.accept(this);
        }
        else if (right != null) {
            return 1 + right.accept(this);
        }
        else {
            return 1;
        }
    }
}
