
import org.junit.Test;
import com.google.common.base.Preconditions;

public class GuavaExamples {


    @Test
    public void testNullArgument(){
        String test = null;
        Preconditions.checkNotNull(test, "An argument tot the function should be given");
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
