import tester.Tester;


public class ExamplesFSetString {

    public ExamplesFSetString() {
        // TODO Auto-generated constructor stub
    }
    
    private final String alice   = "Alice";
    private final String bob   = "Bob";
    private final String carol = "Carol";
    private final String dave  = "Dave";

    // FSetString objects to be created and then tested.

    private FSetString f0;    // { }
    private FSetString f1;    // { alice }
    private FSetString f2;    // { bob, alice }
    private FSetString f3;    // { carol, bob, alice }
    private FSetString f4;    // { dave, carol, bob, alice }
    private FSetString f5;    // { alice, bob, alice }
    private FSetString f6;    // { carol, dave, bob, alice }
    private FSetString f7;    // { alice, bob, bob, alice }

    private FSetString s1;    // { alice }
    private FSetString s2;    // { bob }
    private FSetString s3;    // { carol }
    private FSetString s4;    // { dave }

    private FSetString s12;    // { alice, bob }
    private FSetString s13;    // { alice, carol }
    private FSetString s14;    // { alice, dave }
    private FSetString s23;    // { bob, carol }
    private FSetString s34;    // { carol, dave }

    private FSetString s123;   // { alice, bob, carol }
    private FSetString s124;   // { alice, bob, dave }
    private FSetString s134;   // { alice, carol, dave }
    private FSetString s234;   // { bob, carol, dave }
    
    public void init(){
        f0 = FSetString.emptySet();
        f1 = FSetString.insert(f0, alice);
        f2 = FSetString.insert(f1, bob);
        f3 = FSetString.insert(f2, carol);
        f4 = FSetString.insert(f3, dave);
        f5 = FSetString.insert(f2, alice);
        f6 = FSetString.insert(FSetString.insert(f2, dave), carol);

        f7 = FSetString.insert(f0, alice);
        f7 = FSetString.insert(f7, bob);
        f7 = FSetString.insert(f7, bob);
        f7 = FSetString.insert(f7, alice);

        s1 = FSetString.insert(f0, alice);
        s2 = FSetString.insert(f0, bob);
        s3 = FSetString.insert(f0, carol);
        s4 = FSetString.insert(f0, dave);

        s12 = FSetString.insert(f1, "Bob");
        s13 = FSetString.insert(f1, "Carol");
        s14 = FSetString.insert(f1, "Dave");
        s23 = FSetString.insert(s2, "Carol");
        s34 = FSetString.insert(s3, "Dave");

        s123 = FSetString.add(s12, carol);
        s124 = FSetString.add(s12, dave);
        s134 = FSetString.add(s13, dave);
        s234 = FSetString.add(s23, dave);

        s134 = FSetString.add(s134, dave);
        s234 = FSetString.add(s234, dave);
    }
    
    public void testAll(Tester t) {
        init();
        t.checkExpect(s14,0);
        t.checkExpect(FSetString.union(s1, s4), 0);
    }

}
