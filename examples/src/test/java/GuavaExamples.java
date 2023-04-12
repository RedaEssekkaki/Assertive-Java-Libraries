
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


}
