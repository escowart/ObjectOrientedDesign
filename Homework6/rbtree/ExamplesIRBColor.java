package rbtree;



import tester.Tester;

/** A class testing Red-Black Color implementation 
 * @author Edwin Cowart
 * @version 2013.11.1 */
public class ExamplesIRBColor {
        
    private IRBColor red = new RBRed();
    private IRBColor black = new RBBlack();
    
    /** Test the Red-Black colors
     *  @param t : The Tester class*/ 
    public void testRedBlack(Tester t) {
        // Red-Black equals check
        t.checkExpect(this.red.equals(this.red));
        t.checkExpect(!this.black.equals(this.red));
        t.checkExpect(!this.red.equals(this.black));
        t.checkExpect(this.black.equals(this.black));
        
        // Red-Black hashCode check
        t.checkExpect(this.black.hashCode(), 0);
        t.checkExpect(this.red.hashCode(), 1);
        
        // Red-Black isBlack check
        t.checkExpect(this.black.isBlack());
        t.checkExpect(!this.red.isBlack());
        
        // Red-Black isRed check
        t.checkExpect(!this.black.isRed());
        t.checkExpect(this.red.isRed());
        
        // Red-Black toString check
        t.checkExpect(this.black.toString(), "Black");
        t.checkExpect(this.red.toString(), "Red");
    }
    
}
