package observer;

/**
 * This interface makes all of his implementers to implements his functions.
 */

public interface Member {
        //Updating the State of an observer.
        public void update(UndoableStringBuilder usb);

}
