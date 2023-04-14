package comparisons.handshakes;

import org.examples.BiDirectionalAssociation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.common.base.Preconditions;
import com.google.common.base.Objects;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.junit.Assert.fail;

public class GuavaHandshake {



    private BiDirectionalAssociation<Integer> BDA_0;
    private BiDirectionalAssociation<Integer> BDA_1;
    private BiDirectionalAssociation<Integer> BDA_2;
    private BiDirectionalAssociation<Integer> BDA_3;

    private ArrayList<BiDirectionalAssociation<Integer>> assoList;
    private boolean test;

    @BeforeEach
    public void setup()
    {
        BDA_0 = new BiDirectionalAssociation<>();
        BDA_1 = new BiDirectionalAssociation<>();
        BDA_2 = new BiDirectionalAssociation<>();
        BDA_3 = new BiDirectionalAssociation<>();
        assoList = new ArrayList<>();
        assoList.add(BDA_0);
        assoList.add(BDA_1);
        assoList.add(BDA_2);
        assoList.add(BDA_3);

    }

    @Test
    public void testSet()
    {
        //test all get() is null
        for (BiDirectionalAssociation asso : assoList) {
            try{
                checkNotNull(asso.get(), "Fiels must be null");
                fail("A NullPointerException was expected");
            }
            catch (NullPointerException expected){
            }
     }

        BDA_0.set(BDA_1);
        //test bda_0 & bda_1 associated
        test = Objects.equal(BDA_0.get(), BDA_1);
        test = Objects.equal(BDA_1.get(), BDA_0) && test;
        checkArgument(test, "BD0 et BD1 doivent etre liés");

        BDA_1.set(BDA_2);
        //test bda_0 is null
        try{
            checkNotNull(BDA_0.get(), "Fiels must be null");
            fail("A NullPointerException was expected");
        }
        catch (NullPointerException expected){
        }

        //test bda_2 & bda_1 linked
        test = Objects.equal(BDA_2.get(), BDA_1);
        test = Objects.equal(BDA_1.get(), BDA_2) && test;
        checkArgument(test, "BD2 et BD1 doivent etre liés");


        BDA_3.set(BDA_0);
        //test bda_3 & bda_0 linked
        test = Objects.equal(BDA_3.get(), BDA_0);
        test = Objects.equal(BDA_0.get(), BDA_3) && test;
        checkArgument(test, "BD3 et BD0 doivent etre liés");



    }

    @Test
    public void testUnSet()
    {
        BDA_0.set(BDA_1);
        BDA_2.set(BDA_3);

        //test all get() is not null
        for (BiDirectionalAssociation asso : assoList) {
            checkNotNull(asso.get(), "Fiels must be not null");
        }

        BDA_3.unSet();
        //test BDA-3 & BDA_2 get() null
        try{
            checkNotNull(BDA_2.get(), "Fiels must be null");
            fail("A NullPointerException was expected");
        }
        catch (NullPointerException expected){
        }
        try{
            checkNotNull(BDA_3.get(), "Fiels must be null");
            fail("A NullPointerException was expected");
        }
        catch (NullPointerException expected){
        }



        BDA_0.unSet();
        //test bda_0 & bda_1 get() null
        try{
            checkNotNull(BDA_0.get(), "Fiels must be null");
            fail("A NullPointerException was expected");
        }
        catch (NullPointerException expected){
        }
        try{
            checkNotNull(BDA_1.get(), "Fiels must be null");
            fail("A NullPointerException was expected");
        }
        catch (NullPointerException expected){
        }

    }
}
