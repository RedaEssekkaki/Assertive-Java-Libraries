
import org.junit.Test;
import com.google.common.base.Preconditions;

public class GuavaExamples {

    public void checkArgumentNotNull(String arg){
        Preconditions.checkNotNull(arg, "An argument tot the function should be given");
    }

    public void checkBooleanArgument(boolean expression){
        Preconditions.checkArgument(expression, "expression is false");
    }

    @Test
    public void testNullArgument(){
        String test = null;
        checkArgumentNotNull(test);
    }


    @Test
    public void testNotNullArgument(){
        String test = "Hello world";
        checkArgumentNotNull(test);
    }

    @Test
    public void testFalseExpression(){
        Boolean test = false;
        checkBooleanArgument(test);
    }

    @Test
    public void testTrueExpression(){
        Boolean test = true;
        checkBooleanArgument(test);
    }
}
