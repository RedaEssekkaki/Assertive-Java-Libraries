import com.google.common.collect.ImmutableList;
import com.google.common.truth.Expect;
import org.examples.BiDirectionalAssociation;
import org.examples.UniDirectionalAssociation;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;



public class TruthExamples {

    @Test
    /**
     * Truth can make writing equality and comparative assertions quite easy
     */
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
    /**
     * Truth has a suite of assertions made specifically for floats, that allow to
     * account for the inherent margin of error Floating Point Operations create
     */
    public void floatTests()
    {
        float f1 = 1.0f;
        float f2 = 1.1f;


        assertThat(f1).isWithin(0.11f).of(f2);

        assertWithMessage("When not using tolerances, using floats can be difficult \n" +
                "Here, we check that 0.1f+0.1f+0.1 is equal to 0.3f, with no tolerance").that(0.1f+0.1f+0.1).isEqualTo(0.3f);

    }

    @Test
    /**
     * Truth has a number of tools for strings, such as regular expressions, seeing if a string ends , starts or contains
     * a string of characters. This can make testing that data is formatted correctly easy
     */
    public void StringTests()
    {

        String s1 = "naomod";
        String s2 = "nao";

        assertThat(s1).startsWith("nao");
        assertThat(s1).contains("mo");
        assertWithMessage("missing 'mod' at end of string").that(s1).endsWith("mod");
        assertWithMessage("missing 'mod' at end of string").that(s2).endsWith("mod");

    }

    @Test
    /**
     * Truth has a number of methods to make assertions on Iterable types.
     */
    public void IterableTests()
    {
        ArrayList<Integer> list1 = new ArrayList<Integer>();

        for(int i = 0; i<10; i++)
        {
            list1.add(i);
        }

        assertThat(list1).contains(5);
        assertThat(list1).containsAtLeast(1,3,4);
        assertThat(list1).isInOrder();
        assertThat(list1).isNotEmpty();

        assertWithMessage("List does not contain the number 16").that(list1).contains(16);

    }

    @Test
    public void UniDirectionalTests()
    {
        UniDirectionalAssociation<Integer> UDA = new UniDirectionalAssociation<>();
        Integer Int1 = 10;
        Integer Int2 = 20;


        assertThat(UDA.isSet()).isFalse();

        UDA.setAssociatedValue(Int1);

        assertThat(UDA.getAssociatedValue()).isEqualTo(10);
        assertThat(UDA.isSet()).isTrue();

        UDA.setAssociatedValue(Int2);

        assertThat(UDA.getAssociatedValue()).isEqualTo(Int2);

        assertWithMessage("THis test is supposed to fail").that(UDA.getAssociatedValue()).isSameInstanceAs(Int1);


    }

    @Test
    public void BiDirectionalTest()
    {
        BiDirectionalAssociation<Integer> BDA1 = new BiDirectionalAssociation<>();
        BiDirectionalAssociation<Integer> BDA2 = new BiDirectionalAssociation<>();
        BiDirectionalAssociation<Integer> BDA3 = new BiDirectionalAssociation<>();


        BDA1.set(BDA2);

        assertThat(BDA2.get()).isSameInstanceAs(BDA1);
        assertThat(BDA1.get()).isSameInstanceAs(BDA2);

        BDA1.set(BDA3);

        assertThat(BDA2.get()).isNull();
        assertThat(BDA3.get()).isSameInstanceAs(BDA1);


        assertWithMessage("This test is supposed to fail").that(BDA1.get()).isSameInstanceAs(BDA2);

    }

    @Rule
    public final Expect expect = Expect.create();
    @Test
    /**
     * using expect instead of assert allows more than one test to fail,
     * same as with soft assertions in AssertJ
     */
    public void softAssertions()
    {
        expect.withMessage("using expect instead of assert allows more than one test to fail, \n" +
                " same as with soft assertions in AssertJ").that(10).isEqualTo(20);
        expect.withMessage("as you can see by multiple tests failing instead of only showing the first one that fails \n" +
                "and then ending the test without running the other assertions").that(ImmutableList.of(10,20,30,40)).contains(55);
        expect.that(25).isNotNull();
        expect.that(20.789456).isWithin(1).of(20);
        expect.that(0.1f+0.1f+0.1).isEqualTo(0.3f);


    }

    @Test
    /**
     * Truth also allows assertions on classes and exceptions
     */
    public void miscellaneousTests()
    {
        assertThat(ArrayList.class).isAssignableTo(Iterable.class);

        assertThat(new IllegalArgumentException("test exception")).hasMessageThat().isEqualTo("test exception");

        assertWithMessage("this test is supposed to fail").that(Integer.class).isAssignableTo(HashMap.class);
    }

}
