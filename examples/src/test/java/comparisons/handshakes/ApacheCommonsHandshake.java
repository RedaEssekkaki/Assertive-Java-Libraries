package comparisons.handshakes;

import org.apache.commons.lang3.Validate;
import org.examples.BiDirectionalAssociation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


public class ApacheCommonsHandshake {

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
        Validate.isTrue(BDA_0.get() == null);
        Validate.isTrue(BDA_1.get() == null);
        Validate.isTrue(BDA_2.get() == null);
        Validate.isTrue(BDA_3.get() == null);


        BDA_0.set(BDA_1);

        //test bda_0 & bda_1 associated
        Validate.isTrue(BDA_0.get() == BDA_1);
        Validate.isTrue(BDA_1.get() == BDA_0);


        BDA_1.set(BDA_2);

        //test bda_0 is null
        Validate.isTrue(BDA_0.get() == null);

        //test bda_2 & bda_1 linked
        Validate.isTrue(BDA_1.get() == BDA_2);
        Validate.isTrue(BDA_2.get() == BDA_1);

        BDA_3.set(BDA_0);

        //test bda_3 & bda_0 linked
        Validate.isTrue(BDA_0.get() == BDA_3);
        Validate.isTrue(BDA_3.get() == BDA_0);
    }

    @Test
    public void testUnSet()
    {
        BDA_0.set(BDA_1);
        BDA_2.set(BDA_3);

        //test all is not get() null
        Validate.notNull(BDA_0.get());
        Validate.notNull(BDA_1.get());
        Validate.notNull(BDA_2.get());
        Validate.notNull(BDA_3.get());


        BDA_3.unSet();

        //test BDA-3 & BDA_2 get() null
        Validate.isTrue(BDA_2.get() == null);
        Validate.isTrue(BDA_3.get() == null);

        BDA_0.unSet();

        //test bda_0 & bda_1 get() null
        Validate.isTrue(BDA_0.get() == null);
        Validate.isTrue(BDA_1.get() == null);



    }




}
