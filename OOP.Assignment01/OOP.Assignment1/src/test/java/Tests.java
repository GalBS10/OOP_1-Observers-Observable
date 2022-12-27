import observer.ConcreteMember;
import observer.GroupAdmin;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    // stub method to check external dependencies compatibility
    @Test
    public void test(){
        String s1 = "Alice";
        String s2 = "Bob";

        logger.info(()->JvmUtilities.objectFootprint(s1));

        logger.info(()->JvmUtilities.objectFootprint(s1,s2));

        logger.info(()->JvmUtilities.objectTotalSize(s1));

        logger.info(() -> JvmUtilities.jvmInfo());
    }
    /**
     * this func is checking whether a customer is updated while he is
     * registered (if he doesn't it's wrong).
     * Furthermore, the func is checking whether the State is being initialized.
     * (Checking the register, Notify and insert)
     */
    @Test
    public void test1(){
        GroupAdmin shop = new GroupAdmin();
        ConcreteMember c1 = new ConcreteMember();//Customer
        ConcreteMember c2 = new ConcreteMember();//Customer
        ConcreteMember c3 = new ConcreteMember();//Customer
        ConcreteMember c4 = new ConcreteMember();//Customer
//registering all the customers.
        shop.register(c1);
        shop.register(c2);
        shop.register(c3);
        shop.register(c4);

        shop.insert(0,"new Rolex just dropped in");//Notify is happening in insert func'

        assertEquals(shop.State.toString(),c1.State.toString());//comparing between the Strings in order to not compare
        assertEquals(shop.State.toString(),c1.State.toString());//between their pointers.
        assertEquals(shop.State.toString(),c1.State.toString());
        assertEquals(shop.State.toString(),c1.State.toString());
    }
    /**
     * this test is checking whether a customer is updated while he is not
     * registered (if he does it's wrong)
     * Furthermore, the func is checking whether the State is changing after
     * initializing.
     */
    @Test
    public void Test2(){
        GroupAdmin shop = new GroupAdmin();
        ConcreteMember c1 = new ConcreteMember();//Customer
        ConcreteMember c2 = new ConcreteMember();//Customer
        ConcreteMember c3 = new ConcreteMember();//Customer
        ConcreteMember c4 = new ConcreteMember();//Customer

        shop.register(c1);
        shop.register(c2);
        shop.register(c3);

         shop.insert(0,"new Rolex just dropped in");

        assertEquals(shop.State.toString(),c1.State.toString());
        assertEquals(shop.State.toString(),c1.State.toString());
        assertEquals(shop.State.toString(),c1.State.toString());
        assertEquals("",c4.State.toString());//Because c4 didn't register yet his State is the default - "".

        shop.register(c4);

        shop.insert(0,"The Rolex is on discount");

        assertEquals(shop.State.toString(),c1.State.toString());
        assertEquals(shop.State.toString(),c1.State.toString());
        assertEquals(shop.State.toString(),c1.State.toString());
        assertEquals(shop.State.toString(),c1.State.toString());
    }
    /**
     * This test checks whether the "unregister" is working also checks if state of the observer is changed
     * after he unregistered.
     */
    @Test
    public void Test_unregister() {
        GroupAdmin shop = new GroupAdmin();
        ConcreteMember c1 = new ConcreteMember();//Customer

        shop.register(c1);
        shop.insert(0,"New Boss watch is on sale!");//Right now c1.State=shop.state.
        shop.unregister(c1);//c1.State supposed to be null
        assertEquals(null,c1.State);
    }
    /**
     *This test checks the "append" func.
     */
    @Test
    public void Test_append(){
        GroupAdmin shop = new GroupAdmin();
        ConcreteMember c1 = new ConcreteMember();//Customer

        shop.register(c1);
        shop.insert(0,"New Boss watch is ");//Right now c1.State.toString = "New Boss watch is".
        shop.append("on sale!");

        assertEquals(shop.State.toString(),c1.State.toString());//shop.State.toString()="New Boss watch ison sale!".

    }
    /**
     *This test checks the "delete" func.
     */
    @Test
    public void Test_delete(){
        GroupAdmin shop = new GroupAdmin();
        ConcreteMember c1 = new ConcreteMember();//Customer

        shop.register(c1);
        shop.insert(0,"New Boss watch is");//Right now c1.State.toString = "New Boss watch is".
        shop.delete(0,5);

        assertEquals(shop.State.toString(),c1.State.toString());//shop.State.toString()="oss watch is".
    }
    /**
     **This test checks the "undo" func.
     * the problem is that the undo func always works twice.
     */
    @Test
    public void Test_undo(){
        GroupAdmin shop = new GroupAdmin();
        ConcreteMember c1 = new ConcreteMember();//Customer

        shop.register(c1);
        shop.insert(0,"New Boss watch is");//Right now c1.State.toString = "New Boss watch is".
        shop.delete(0,5);
        shop.append("on sale!");

        shop.undo();

        shop.delete(0,18);
        shop.append("hello and welcome everybody");
        shop.append("GAL");
        shop.append("GAL");

        shop.undo();

        assertEquals(shop.State.toString(),c1.State.toString());//shop.State.toString()="hello and welcome everybodyGAL"
    }
    /**
     **This test checks the "undo" func, but this time we check what happens when we do "undo" when the stack is empty
     * (answer: we do nothing,no change)
     * the problem is that the undo func always works twice.
     */
    @Test
    public void Test_undo2(){
        GroupAdmin shop = new GroupAdmin();
        ConcreteMember c1 = new ConcreteMember();//Customer

        shop.register(c1);
        shop.insert(0,"New Boss watch is");//Right now c1.State.toString = "New Boss watch is".
        shop.delete(0,5);
        shop.append("on sale!");

        shop.undo();
        shop.undo();
        shop.undo();
        shop.undo();
        shop.undo();
        shop.undo();

        assertEquals(shop.State.toString(),c1.State.toString());//shop.State.toString() = the first thing we initialize it ("").
    }

}
