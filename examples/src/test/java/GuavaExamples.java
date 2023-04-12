
import org.junit.Test;
import com.google.common.base.Preconditions;

import static org.junit.Assert.fail;

public class GuavaExamples {

    /**
     * Testing the checkArgument Guard in Guava
     *
     * */
    @Test
    public void testCheckArgument() {
        try {
            Preconditions.checkArgument(false);
            fail("no exception thrown");
        } catch (IllegalArgumentException expected) {
        }
    }

    @Test
    public void testNotNullArgument(){
        String test = "Hello world";
        Preconditions.checkNotNull(test, "An argument tot the function should be given");
    }


    /**
     * This test is going to fail because a */
    @Test
    public void testFalseExpression(){
        Boolean test = false;
        Preconditions.checkArgument(test , "The expression is false");
    }

    @Test
    public void testTrueExpression(){
        Boolean test = true;
        Preconditions.checkArgument(test , "The expression is false");
    }
}
