import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;



public class TruthExamples {

    @Test
    public void intTest()
    {
        Integer i = 20;
        Integer j = 30;

        assertThat(i).isInstanceOf(Integer.class);

        assertThat(i).isEqualTo(20);

        assertThat(j).isGreaterThan(i);

        assertThat(i).isEqualTo(10);
    }

    @Test
    public void floatTests(){
        float f1 = 1.0f;
        float f2 = 1.1f;


        assertThat(f1).isWithin(0.11f).of(f2);

        assertThat(f1).isEqualTo(f2);

    }


}
