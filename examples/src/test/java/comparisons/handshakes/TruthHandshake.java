package comparisons.handshakes;

import org.examples.BiDirectionalAssociation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TruthHandshake {



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

        BDA_0.set(BDA_1);

        //test bda_0 & bda_1 associated

        BDA_1.set(BDA_2);

        //test bda_0 is null

        //test bda_2 & bda_1 linked

        BDA_3.set(BDA_0);

        //test bda_3 & bda_0 linked

    }

    @Test
    public void testUnSet()
    {
        BDA_0.set(BDA_1);
        BDA_2.set(BDA_3);

        //test all is not get() null

        BDA_3.unSet();

        //test BDA-3 & BDA_2 get() null

        BDA_0.unSet();

        //test bda_0 & bda_1 get() null



    }
}
