package observer;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the "Sender" interface inorder to create an observable.
 * @author Gal & Lidor.
 * @version 1.0
 */

public class GroupAdmin implements Sender {
    public UndoableStringBuilder State;//The state of the observable.
    private List<Member> Observers;//a List of members that need to be notified.
    /**
     * Constructor
     * We initialize all the fields to default.
     */
    public GroupAdmin(){
        State = new UndoableStringBuilder();
        Observers = new ArrayList<>();
    }
    /**
     * Register an observer to the observable by adding him to "Observers" list
     * (checking if the observer is already registered)
     * @param obj - The observer we want to register.
     */
    @Override
    public void register(Member obj) {
        if(((ConcreteMember)obj).Counter == 0) {//Checking if obj is already registered to the observable
            Observers.add(obj);
            ((ConcreteMember) obj).Counter++;
            obj.update(State);
        }
    }
    /**
     * Removing an observer from the "Observers" list
     * @param obj - The observer we want to remove.
     */
    @Override
    public void unregister(Member obj) {
        obj.update(null);//We want to change the pointer of obj.State to be null in order to stop obj from being updated.
        Observers.remove(obj);
        ((ConcreteMember) obj).Counter--;//if we unregistered obj we want him to be able to register again.
    }
    /**
     * Changing the State by adding a substring from a specific index.
     * @param offset - The index in the string where we want to add new string.
     * @param obj - The substring that we want to add.
     */
    @Override
    public void insert(int offset, String obj) {
        State.insert(offset,obj);
        Notify();
    }
    /**
     * Adding a String to the end of "State".
     * @param obj - The String we want to add.
     */
    @Override
    public void append(String obj) {
        State.append(obj);
        Notify();
    }
    /**
     * Deleting a substring from "State" that start and end by the next parameters.
     * @param start - The start index of the substring.
     * @param end - The end index of the substring.
     */
    @Override
    public void delete(int start, int end) {
        State.delete(start,end);
        Notify();
    }
    /**
     * undoing the last command(function).
     */
    @Override
    public void undo() {
        State.undo();
        Notify();
    }
    /**
     * notifying all the Observers in "Observers" and updating them with the new State.
     */
    public void Notify(){
        for( Member m : Observers){
            m.update(State);
        }
    }
}
