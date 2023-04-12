
import junit.framework.AssertionFailedError;
import org.junit.Test;
import com.google.common.base.Preconditions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class GuavaExamples {

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

    /**
     * Testing the checkArgument Guard in Guava
     *
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

    @Test
    public void testNotNullArgument(){
        String test = "Hello world";
        Preconditions.checkNotNull(test, "An argument tot the function should be given");
    }

    @Test
    public void testCheckState() {
        Preconditions.checkState(true, "%s", IGNORE_ME);
    }

}
