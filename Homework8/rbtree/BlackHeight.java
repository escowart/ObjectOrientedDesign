package rbtree;


import java.util.Comparator;

/** A Visitor that calculates the Black Height of Red-Black Trees
 * @author Edwin Cowart
 * @version 2013.11.14
 * @param <T>
 */
public class BlackHeight<T> implements RBTreeVisitor<T, Integer> {
    /**
     * Get the Black Height of the Empty Tree of the given Comparator 
     *  and String for Color
     * @param comp the Comparator for the whole tree
     * @param color the color of the node, which should be "RED"
     *        or "BLACK"
     * @return 0
     */
    public Integer visitEmpty(Comparator<T> comp, String color) {
        return 0;
    }
    
    /**
     * Get the Black Height of the Red-Black Tree of the given Comparator 
     *  and String for Color, Data, and left and right branches
     * @param comp the Comparator for the whole tree
     * @param color the color of the node, which should be "RED"
     *        or "BLACK"
     * @param data the label/value for the node
     * @param left the left subtree of the node
     * @param right the right subtree of the node
     * @return An Integer of the height of the Red-Black Tree
     */
    public Integer visitNode(Comparator<T> comp, String color, T data,
            RBTree<T> left, RBTree<T> right) {
        int i = 0;
        if (color.equals("BLACK")) {
            i = 1;
        }
        if (left != null) {
            return i + left.accept(this);
        }
        return i;
    }
}
