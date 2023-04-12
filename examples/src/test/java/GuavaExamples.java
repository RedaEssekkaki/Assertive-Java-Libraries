
import org.junit.Test;
import com.google.common.base.Preconditions;

public class GuavaExamples {

    public void doSomething(String arg){
        Preconditions.checkNotNull(arg, "An argument should be given");
    }
    @Test
    public void testNullArgument(){
        String test = null;
        doSomething(test);
    }


    @Test
    public void testNotNullArgument(){
        String test = "Hello world";
        doSomething(test);
    }


}
