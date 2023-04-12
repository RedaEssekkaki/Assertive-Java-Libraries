import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;



public class TruthExamples {

    @Test
    public void test()
    {
        Integer i = 20;
        Integer j = 30;

        assertThat(i).isEqualTo(20);
        assertThat(i).isEqualTo(10);

        assertThat(j).isGreaterThan(i);
    }



}
