package comp3350.group6.homiez.business;

import junit.framework.TestCase;

import comp3350.group6.homiez.application.Main;
import comp3350.group6.homiez.objects.Posting;
import comp3350.group6.homiez.objects.User;

public class AgeCompatibilityTest extends TestCase {

    Compatibility c;
    Posting p;
    User u;
    AccessPostings accessPostings;

    public void setUp() {
        Main.startUp();

        c = new AgeCompatibility();
        u = new User("postinglist_text", "postinglist_text",20, "postinglist_text");
        p = new Posting("postinglist_text", "postinglist_text", new User("test2","postinglist_text",24, "postinglist_text"), 2, "postinglist_text", "postinglist_text", "postinglist_text");
        accessPostings = new AccessPostings();
        accessPostings.insertPosting(p);
    }

    public void testNullValues() {
        System.out.println("\nStarting testNullValues");

        assertEquals(-1.0, c.calculateCompatibility(null, new Posting("p")));
        assertEquals(-1.0, c.calculateCompatibility(null, new User("u")));
        assertEquals(-1.0, c.calculateCompatibility(null, (Posting) null));
        assertEquals(-1.0, c.calculateCompatibility(null, (User) null));

        System.out.println("Finished testNullValues");

    }

    public void testOneAttachedUserList() {
        System.out.println("\nStarting testOneAttachedUserList");
        assertEquals(73, Math.round(c.calculateCompatibility(u, p)));
        System.out.println("Finished testOneAttachedUserList");
    }

    public void testNullAttachedUser() {
        System.out.println("\nStarting testNullAttachedUser");
        p.addAttachedUser(null);
        assertEquals(73, Math.round(c.calculateCompatibility(u, p)));
        System.out.println("Finished testNullAttachedUser");
    }

    public void testUserWithInvalidAge() {
        System.out.println("\nStarting testUserWithInvalidAge");
        // attach one more user
        User u = new User("test3");
        assertEquals(-1.0, c.calculateCompatibility(u, p));
        System.out.println("Finished testUserWithInvalidAge");
    }

    public void testValidInput() {
        System.out.println("\nStarting testValidInput");

        User test = new User("test4", "test4",20, "test4");
        p.addAttachedUser(test);

        assertEquals(100.00, c.calculateCompatibility(u,p ));
        assertEquals(100.00, c.calculateCompatibility(u, test));

        System.out.println("Finished testValidInput");
    }

    public void testMultipleValidInputs() {

        System.out.println("\nStarting testMultipleValidInputs");

        User test = new User("test4", "test4",20, "test4");
        p.addAttachedUser(test);

        User test5 = new User("test5", "test4",10, "test4");
        p.addAttachedUser(test);

        User test6 = new User("test6", "test4",13, "test4");
        p.addAttachedUser(test);

        assertEquals(100, Math.round(c.calculateCompatibility(u,p )));
        assertEquals(100, Math.round(c.calculateCompatibility(u, test)));
        assertEquals(33, Math.round(c.calculateCompatibility(u, test5)));
        assertEquals(53, Math.round(c.calculateCompatibility(u, test6)));
        System.out.println("Finished testValidInput");
    }

    public void testEdgeCases() {

        System.out.println("\nStarting testEdgeCases");

        User test = new User("test4", "test4",20, "test4");
        p.addAttachedUser(test);

        User test5 = new User("test5", "test4",-99, "test4");
        p.addAttachedUser(test);

        User test6 = new User("test6", "test4",4, "test4");
        p.addAttachedUser(test);

        assertEquals(100, Math.round(c.calculateCompatibility(u,p )));
        assertEquals(100, Math.round(c.calculateCompatibility(u, test)));
        assertEquals(-1, Math.round(c.calculateCompatibility(u, test5)));
        assertEquals(0, Math.round(c.calculateCompatibility(u, test6)));
        System.out.println("Finished testEdgeCases");

    }

}
