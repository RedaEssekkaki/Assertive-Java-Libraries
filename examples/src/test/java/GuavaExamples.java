
import junit.framework.AssertionFailedError;
import org.junit.Test;
import com.google.common.base.Preconditions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class GuavaExamples {


    /**
     * Testing the checkArgument Guard in Guava
     * */
    @Test
    public void testCheckArgument() {
        try {
            Preconditions.checkArgument(false);
            fail("no exception thrown, IllegalArgumentException expected");
        } catch (IllegalArgumentException expected) {
        }
    }
    @Test
    public void testTrueExpression(){
        Preconditions.checkArgument(true);
    }

    /**
     * Testing the checkNotNull Guard in Guava
     * */
    @Test
    public void testNotNullArgument(){
        try{
            Preconditions.checkNotNull(null, "An argument to the function should be given");
            fail("A NullPointerException was expected");
        }
        catch (NullPointerException expected){
        }
    }
    @Test
    public void testNotNullArgument_successful(){
        Preconditions.checkNotNull("A message", "An argument to the function should be given");
    }

    @Test
    public void testCheckState() {
        Preconditions.checkState(true, "%s", IGNORE_ME);
    }

    private static final Object IGNORE_ME =
            new Object() {
                @Override
                public String toString() {
                    throw new AssertionFailedError();
                }
            };

    private static class Message {
        boolean invoked;

        @Override
        public String toString() {
            assertFalse(invoked);
            invoked = true;
            return "A message";
        }
    }
}
