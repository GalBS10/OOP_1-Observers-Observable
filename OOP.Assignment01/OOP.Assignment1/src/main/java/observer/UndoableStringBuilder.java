package observer;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * this class executes StringBuilder methods and in addition you can undo all the methods
 * @author Gal Ben Shitrit & Lidor Keren Yeshuah
 * @version 1.0
 */
public class UndoableStringBuilder {
    private StringBuilder str = new StringBuilder("");
    private Stack<StringBuilder> st;

    /**
     * Constructor
     * @param s - we use this String to determine the String of StringBuilder str
     */
    public UndoableStringBuilder(String s) {
        this.str = str.append(s);
        st = new Stack<StringBuilder>();
        st.push(new StringBuilder(this.str));
    }

    /**
     * Constructor
     * @param s - we use this String to determine the Stringbuilder of StringBuilder str.
     */
    public UndoableStringBuilder(StringBuilder s) {
        this.str = str.append(s);
        st = new Stack<StringBuilder>();
        st.push(new StringBuilder(this.str));
    }
    /**
     * Default constructor
     */

    public UndoableStringBuilder() {
        this.str = new StringBuilder("");
        st = new Stack<StringBuilder>();
        st.push(new StringBuilder(this.str));
    }

    /**
     * the function gets string and adds it to the end of the string that already exists
     * @param str - the string we add to the old String
     * @return the method returns a new UndoableStringBuilder with the old string+str
     */
    public UndoableStringBuilder append(String str) {
        st.push(this.str);
        this.str = new StringBuilder(this.str.append(str).toString());
        return new UndoableStringBuilder(this.str);
    }

    /**
     * the function gets start index and end index then takes the substring of the exist string
     * and deletes this substring
     * @param start - the start index of the substring that we want to delete from the existing string
     * @param end - the end index of the substring that we want to delete from the existing string
     * @exception throws StringIndexOutOfBoundsException
     * @return the String we get after deleting the substring we made
     */
    public UndoableStringBuilder delete(int start, int end)
    {
        try
        {
            if((start<0)||(start>this.str.length())||(start>end)||(end<0))
            {
                throw new StringIndexOutOfBoundsException("you have entered wrong indexes");

            }
            st.push(this.str);
            this.str = new StringBuilder(this.str.delete(start, end).toString());
            if(start==end){
                st.pop();
            }
            return new UndoableStringBuilder(this.str);
        }
        catch(StringIndexOutOfBoundsException ex){
            System.err.println(ex);
            ex.printStackTrace();
        }
        return new UndoableStringBuilder(this.str);
    }

    /**
     * this function adds a string to the existing string in any place we choose
     * @param offset - the index where the new string will be added to
     * @param str - the string that will be added to the existing string
     * @exception -throws StringIndexOutOfBoundsException
     * @return some chars of the old String plus + the new string + the remaining chars from  the old string
     */
    public UndoableStringBuilder insert(int offset, String str)
    {
        try
        {
            if((offset<0)||(offset>this.str.length())){
                throw new StringIndexOutOfBoundsException("you have entered wrong index");
            }
            st.push(this.str);
            this.str = new StringBuilder(this.str.insert(offset, str).toString());
            return new UndoableStringBuilder(this.str);
        }
        catch(StringIndexOutOfBoundsException ex)
        {
            System.err.println(ex);
            ex.printStackTrace();
        }
        return new UndoableStringBuilder(this.str);
    }

    /**
     * the function gets new string and replaces it in the substring in the exists string
     * @param start - the start index of the substring that we want to replace in the existing string
     * @param end - the end index of the substring that we want to replace in the existing string
     * @param str - the new string that we want to replace in the exists string
     * @exception -throws StringIndexOutOfBoundsException
     * @return The new String we get after we replace str with substring between start and end
     */
    public UndoableStringBuilder replace(int start, int end, String str) {

        try {
            if ((start < 0)||(start>this.str.length())||(start > end)){
                throw new StringIndexOutOfBoundsException("you have entered wrong indexes");
            }
            st.push(this.str);
            this.str = new StringBuilder(this.str.replace(start, end, str));
            return this;
        } catch (StringIndexOutOfBoundsException ex) {
            System.err.println(ex);
            ex.printStackTrace();
        }
        return new UndoableStringBuilder(this.str);
    }
    /**
     * the function will reverse the string
     * example: "hello world!" --> "!dlrow olleh"
     * @return reversed String
     */
    public UndoableStringBuilder reverse(){
        st.push(this.str);
        this.str = new StringBuilder(this.str.reverse().toString());
        return new UndoableStringBuilder(this.str);
    }

    /**
     * the function will undo the last action that has been taken
     * @exception throws EmptyStackException
     * @return the previous String before the last change
     */
    public void undo () {
        try {
            st.pop();
            this.str =new StringBuilder(st.peek().toString());
        } catch (EmptyStackException e) {

        }
    }

    /**
     * the function will represent a string and simplify the way to print
     * what we want on the screen
     * @return
     */
    public String toString(){
        return this.str.toString();
    }
}