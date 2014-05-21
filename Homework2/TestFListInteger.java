import junit.framework.TestCase;

/*
 * Edwin Cowart
 * escowart@ccs.neu.edu
 */

/**
 * A Class that test FListInteger public static methods
 * @author Edwin Cowart
 * @version 2013.9.24
 */
public class TestFListInteger extends TestCase {

    /**
     * Run the main method which will black box test FListInteger
     * @param args : Any Array of Strings
     */
    public static void main(String[] args) {
        TestFListInteger test = new TestFListInteger();
        test.init();
        test.testFListInteger();
        test.testOveride();
        test.testFListInteger();
        test.testOveride();
    }

    private FListInteger e;
    private FListInteger a0;
    private FListInteger a1;
    private FListInteger a2;

    private FListInteger a30;

    private FListInteger a40;
    private FListInteger a41;

    private FListInteger a50;
    private FListInteger a51;
    private FListInteger a52;

    /** Create instances of FListInteger */
    private void init() {
        try {
            this.e = FListInteger.emptyList();
            this.a0 = FListInteger.add(this.e, new Integer(0));
            this.a1 = FListInteger.add(this.a0, new Integer(1));
            this.a2 = FListInteger.add(this.a1, new Integer(2));

            this.a30 = FListInteger.add(this.e, new Integer(3));

            this.a40 = FListInteger.add(this.e, new Integer(0));
            this.a41 = FListInteger.add(this.a40, new Integer(3));

            this.a50 = FListInteger.add(this.e, new Integer(0));
            this.a51 = FListInteger.add(this.a50, new Integer(3));
            this.a52 = FListInteger.add(this.a51, new Integer(2));
        } 
        catch (Exception ex) {
            System.out.println("An Exception was thrown"
                    + " during the creation method");
            System.out.println(ex);
        }
    }

    /** Test all methods */
    public void testFListInteger() {
        try {
            assertTrue("empty 0", FListInteger.isEmpty(this.e));
            assertFalse("nonempty 0", FListInteger.isEmpty(this.a0));
            assertFalse("nonempty 1", FListInteger.isEmpty(this.a1));
            assertFalse("nonempty 2", FListInteger.isEmpty(this.a2));
            assertTrue("get 0",
                    FListInteger.get(this.a0, 0).equals(new Integer(0)));
            assertTrue("get 1",
                    new Integer(1).equals(FListInteger.get(this.a1, 0)));
            assertTrue("get 2",
                    new Integer(0).equals(FListInteger.get(this.a1, 1)));
            assertTrue("get 3",
                    new Integer(2).equals(FListInteger.get(this.a2, 0)));
            assertTrue("get 4",
                    new Integer(1).equals(FListInteger.get(this.a2, 1)));
            assertTrue("get 5",
                    new Integer(0).equals(FListInteger.get(this.a2, 2)));
            assertTrue("set 0", this.a30.equals(FListInteger
                    .set(this.a0, 0, new Integer(3))));
            assertTrue("set 1", this.a41.equals(FListInteger
                    .set(this.a1, 0, new Integer(3))));
            assertTrue("set 2", this.a52.equals(FListInteger
                    .set(this.a2, 1, new Integer(3))));
            assertEquals("size 0", 0, FListInteger.size(this.e));
            assertEquals("size 1", 1, FListInteger.size(this.a0));
            assertEquals("size 2", 2, FListInteger.size(this.a1));
            assertEquals("size 3", 3, FListInteger.size(this.a2));
        } 
        catch (Exception ex) {
            System.out.println("An Exception was thrown "
                    + "during the testA method");
            System.out.println(ex);
        }
    }
    
    public void testOveride() {
        try {
            assertEquals("hashCode 0", this.e.hashCode(), this.e.hashCode());
            assertEquals("hashCode 1", this.a0.hashCode(), this.a0.hashCode());
            assertEquals("hashCode 2", this.a1.hashCode(), this.a1.hashCode());
            assertEquals("hashCode 3", this.a2.hashCode(), this.a2.hashCode());
            assertTrue("equal 0", this.e.equals(this.e));
            assertTrue("equal 1", this.a0.equals(this.a0));
            assertTrue("equal 2", this.a1.equals(this.a1));
            assertTrue("equal 3", this.a2.equals(this.a2));
            assertFalse("equal 4", this.e.equals(this.a2));
            assertFalse("equal 5", this.a2.equals(this.e));            
        }
        catch (Exception ex) {
            System.out.println("An Exception was thrown "
                    + "during the testOveride method");
            System.out.println(ex);
        }
    }
}
