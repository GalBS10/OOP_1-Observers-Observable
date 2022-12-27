package observer;

/**
 * This class implements the "Member" interface inorder to create an observer
 * @author Gal & Lidor.
 * @version 1.0
 */

public class ConcreteMember implements Member {
    //fields//
    public UndoableStringBuilder State;
    public int Counter;

    /**
     * Constructor
     * We initialize all the fields to default.
     */
    public ConcreteMember(){
        State = new UndoableStringBuilder();
        Counter = 0;
    }

    /**
     * This func is updating the State of an observer.
     * @param usb - we use this UndoableStringBuilder to set a new address to point at with State.(Shallow copying)
     */
    @Override
    public void update(UndoableStringBuilder usb) {
        State = usb;
    }
}
