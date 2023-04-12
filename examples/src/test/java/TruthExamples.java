import org.examples.BiDirectionalAssociation;
import org.examples.UniDirectionalAssociation;
import org.junit.Test;

import java.util.ArrayList;

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
    public void floatTests()
    {
        float f1 = 1.0f;
        float f2 = 1.1f;


        assertThat(f1).isWithin(0.11f).of(f2);

        assertThat(f1).isEqualTo(f2);

    }

    @Test
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

}
