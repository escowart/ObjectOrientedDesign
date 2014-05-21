

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import tester.Tester;

/** A Class for testing the BTree class 
 * @author Edwin
 * @version 2013.11.1
 */
public class ExamplesBTree {
    
    
    private StringByLex strbylex = new StringByLex();
    
    private BTree emptybt = BTree.binTree(this.strbylex);
    private BTree bt0 = BTree.binTree(this.strbylex);
    private BTree bt1 = BTree.binTree(this.strbylex);
    private BTree bt2 = BTree.binTree(this.strbylex);
    
    private ArrayList<String> a0 = new ArrayList<String>();
    
    /** Initialize the data */
    private void initData() {
        this.a0.add("four");
        this.a0.add("zebra");
        this.a0.add("normal");
        this.a0.add("yellow");
        this.a0.add("nine");
        this.a0.add("ten");
        this.a0.add("add");
        this.a0.add("current");
        this.a0.add("eight");
        this.a0.add("eight");
        
        this.bt0.build(a0);
        this.bt1.build(a0, -1);
        this.bt2.build(a0, 3);
    }
    
    /** Test the BTree implementation
     *   Red-Black Tree class 
     *   @param t : The Tester class*/ 
    public void testBTree(Tester t) {
        this.initData();

        // Test toString
        t.checkExpect(this.emptybt.toString(), "");
        t.checkExpect(this.bt0.toString(), "add, current, eight, four, nine, "
                + "normal, ten, yellow, zebra");
        t.checkExpect(this.bt2.toString(), "four, normal, zebra");

        // Test equals
        t.checkExpect(this.emptybt.equals(this.emptybt));
        t.checkExpect(!this.bt0.equals(this.emptybt));
        t.checkExpect(!this.emptybt.equals(this.bt0));
        t.checkExpect(this.bt0.equals(this.bt0));
        t.checkExpect(this.bt0.equals(this.bt1));
        t.checkExpect(this.bt1.equals(this.bt0));
        t.checkExpect(!this.bt1.equals(this.bt2));
        t.checkExpect(!this.bt1.equals(""));

        // Test contains
        t.checkExpect(!this.emptybt.contains("four"));
        t.checkExpect(this.bt0.contains("four"));
        t.checkExpect(this.bt0.contains("eight"));
        t.checkExpect(!this.bt0.contains("five"));

        // Test hashCode
        t.checkExpect(this.emptybt.hashCode(), this.emptybt.hashCode());
        t.checkExpect(this.bt0.hashCode(), this.bt0.hashCode());

        // Test RepOK
        t.checkExpect(this.bt0.repOK());
        t.checkExpect(this.bt1.repOK());
        t.checkExpect(this.bt2.repOK());
    }
        
        /**
         * Test part 2 of BTree Red-Black Tree class
         * @param t : The Tester class
         */
    public void testBTree2(Tester t) {
        // Test Iterator
        
        Iterator<String> ibt0 = this.bt0.iterator();
        t.checkExpect(ibt0.next(), "add");
        t.checkExpect(ibt0.next(), "current");
        t.checkExpect(ibt0.next(), "eight");
        t.checkExpect(ibt0.next(), "four");
        t.checkExpect(ibt0.next(), "nine");
        t.checkExpect(ibt0.next(), "normal");
        t.checkExpect(ibt0.next(), "ten");
        t.checkExpect(ibt0.next(), "yellow");
        t.checkExpect(ibt0.next(), "zebra");
        
        // checkException was not working
        try {
            ibt0.next();
        } 
        catch (NoSuchElementException e) {
            t.checkExpect(e, new NoSuchElementException(
                    "next: The is no next element in the "
                            + "Binary Search Tree Iterator"));
        }
        
        try {
            ibt0.next();
        } 
        catch (RuntimeException e) {
            t.checkExpect(e, new RuntimeException(
                    "An exception has already been thrown"
                            + ", cannot continue"));
        }
        try {
            ibt0.hasNext();
        } 
        catch (RuntimeException e) {
            t.checkExpect(e, new RuntimeException(
                    "An exception has already been thrown"
                            + ", cannot continue"));
        }
        
        try {
            ibt0.remove();
        } 
        catch (UnsupportedOperationException e) {
            t.checkExpect(e, new UnsupportedOperationException(
                    "remove: This method is not avalible"));
        }
        
        // Test Build Exceptions
        Iterator<String> ibt1 = this.bt1.iterator();
        ibt1.next();
        try {
            this.bt1.build(this.a0);
        } 
        catch (RuntimeException e) {
            t.checkExpect(e, new ConcurrentModificationException(
                    "build: Cannot build while an Iterator "
                            + "is running over this Binary Search Tree"));
        }
        
        try {
            this.bt1.build(this.a0, -1);
        } 
        catch (RuntimeException e) {
            t.checkExpect(e, new ConcurrentModificationException(
                    "build: Cannot build while an Iterator "
                            + "is running over this Binary Search Tree"));
        }
        
    }
}
