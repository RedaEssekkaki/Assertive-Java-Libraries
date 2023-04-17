package comparisons.handshakes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.examples.BiDirectionalAssociation;
import org.atlanmod.commons.Guards;

public class AtlanmodHandshake {
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
        Guards.checkEqualTo(BDA_0.get(), null);
        Guards.checkEqualTo(BDA_1.get(), null);
        Guards.checkEqualTo(BDA_2.get(), null);
        Guards.checkEqualTo(BDA_3.get(), null);


        BDA_0.set(BDA_1);

        //test bda_0 & bda_1 associated
        Guards.checkEqualTo(BDA_0.get(), BDA_1);
        Guards.checkEqualTo(BDA_1.get(), BDA_0);


        BDA_1.set(BDA_2);

        //test bda_0 is null
        Guards.checkEqualTo(BDA_0.get(), null);

        //test bda_2 & bda_1 linked
        Guards.checkEqualTo(BDA_1.get(), BDA_2);
        Guards.checkEqualTo(BDA_2.get(), BDA_1);

        BDA_3.set(BDA_0);

        //test bda_3 & bda_0 linked
        Guards.checkEqualTo(BDA_0.get(), BDA_3);
        Guards.checkEqualTo(BDA_3.get(), BDA_0);
    }

    @Test
    public void testUnSet()
    {
        BDA_0.set(BDA_1);
        BDA_2.set(BDA_3);

        //test all is not get() null
        Guards.checkNotNull(BDA_0.get());
        Guards.checkNotNull(BDA_1.get());
        Guards.checkNotNull(BDA_2.get());
        Guards.checkNotNull(BDA_3.get());


        BDA_3.unSet();

        //test BDA-3 & BDA_2 get() null
        Guards.checkEqualTo(BDA_2.get(), null);
        Guards.checkEqualTo(BDA_3.get(), null);

        BDA_0.unSet();

        //test bda_0 & bda_1 get() null
        Guards.checkEqualTo(BDA_0.get(), null);
        Guards.checkEqualTo(BDA_1.get(), null);



    }
}
