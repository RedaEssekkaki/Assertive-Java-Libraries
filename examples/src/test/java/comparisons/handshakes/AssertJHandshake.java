package comparisons.handshakes;


import org.assertj.core.*;

import org.examples.BiDirectionalAssociation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertJHandshake {
    private BiDirectionalAssociation<Integer> BDA_0;
    private BiDirectionalAssociation<Integer> BDA_1;
    private BiDirectionalAssociation<Integer> BDA_2;
    private BiDirectionalAssociation<Integer> BDA_3;

    @BeforeEach
    public void setup()
    {
        BDA_0 = new BiDirectionalAssociation<>();
        BDA_1 = new BiDirectionalAssociation<>();
        BDA_2 = new BiDirectionalAssociation<>();
        BDA_3 = new BiDirectionalAssociation<>();

    }

    @Test
    public void testSet()
    {
        //test all get() is null

        assertThat(BDA_0.get()).isNull();
        assertThat(BDA_1.get()).isNull();
        assertThat(BDA_2.get()).isNull();
        assertThat(BDA_3.get()).isNull();


        BDA_0.set(BDA_1);

        //test bda_0 & bda_1 associated
        assertThat(BDA_0.get()).isEqualTo(BDA_1);
        assertThat(BDA_1.get()).isEqualTo(BDA_0);


        BDA_1.set(BDA_2);

        //test bda_2 & bda_1 linked
        assertThat(BDA_1.get()).isEqualTo(BDA_2);
        assertThat(BDA_2.get()).isEqualTo(BDA_1);


        //test bda_0 is null
        assertThat(BDA_0.get()).isNull();


        //test bda_3 & bda_0 linked
        BDA_3.set(BDA_0);
        assertThat(BDA_0.get()).isEqualTo(BDA_3);
        assertThat(BDA_3.get()).isEqualTo(BDA_0);

    }

    @Test
    public void testUnSet()
    {
        BDA_0.set(BDA_1);
        BDA_2.set(BDA_3);

        //test all is not get() null
        assertThat(BDA_0.get()).isNotNull();
        assertThat(BDA_1.get()).isNotNull();
        assertThat(BDA_2.get()).isNotNull();
        assertThat(BDA_3.get()).isNotNull();


        BDA_3.unSet();

        //test BDA_3 & BDA_2 get() null
        assertThat(BDA_3.get()).isNull();
        assertThat(BDA_2.get()).isNull();

        BDA_0.unSet();

        //test bda_0 & bda_1 get() null
        assertThat(BDA_1.get()).isNull();
        assertThat(BDA_0.get()).isNull();


    }
}
