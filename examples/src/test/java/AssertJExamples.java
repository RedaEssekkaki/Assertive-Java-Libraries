import org.assertj.core.api.*;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.assertj.core.description.Description;

import static org.assertj.core.util.Lists.list;
import static org.assertj.core.util.Sets.newLinkedHashSet;


import static java.lang.String.format;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assumptions.assumeThat;


import org.junit.Rule;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.*;
import java.util.function.Consumer;



//@ExtendWith(SoftAssertionsExtension.class)

public class AssertJExamples {
   // @InjectSoftAssertions
    //private SoftAssertions softly;

    final StringBuilder descriptionReportBuilder = new StringBuilder(format("Assertions:%n"));;
    Consumer<Description> descriptionConsumer;

    @BeforeEach
    void setup(){
        // initialize the description consumer
        descriptionConsumer = desc -> descriptionReportBuilder.append(format("-- %s%n", desc));

        // use the description consumer for any following assertions descriptions.
        Assertions.setDescriptionConsumer(descriptionConsumer);
    }

    @AfterEach
    void after(){
        String descriptionReport = descriptionReportBuilder.toString();
        System.out.println(descriptionReport);
    }

    @Test
    void a_few_simple_assertions() {
        assertThat("The Lord of the Rings").as("raté").isNotNull()
                .startsWith("The")
                .contains("Lord")
                .endsWith("Rings");




        // execute some assertions
        List<String> frodo = new ArrayList<String>();
        frodo.add("Frodo");
        frodo.add("33");
        frodo.add("HOBBIT");
        assertThat(frodo.get(0)).as("check name").withFailMessage("is %s should be Frodo", frodo.get(0)).isEqualTo("Frodo");
        assertThat(frodo.get(1)).as("check age").overridingErrorMessage(() -> "Mauvais age : %s").isEqualTo("33");

        // get the report

    }

    @Test
    void reccursiveComparraion(){

        class Address {
            int number;
            String street;
        }

        class Home {
            Address address = new Address();
            Date ownedSince;
        }
        class Person {
            String name;
            double height;
            Home home = new Home();

            Person(String s, double h)
            {
                this.name = s;
                this.height = h;
            }
        }

        Person sherlock = new Person("Sherlock", 1.80);
        sherlock.home.ownedSince = new Date(123);
        sherlock.home.address.street = "Baker Street";
        sherlock.home.address.number = 221;

        Person sherlock2 = new Person("Sherlock", 1.80);
        sherlock2.home.ownedSince = new Date(123);
        sherlock2.home.address.street = "Baker Street";
        sherlock2.home.address.number = 221;

        // assertion succeeds as the data of both objects are the same.
        assertThat(sherlock).usingRecursiveComparison().as("check %s", sherlock).isEqualTo(sherlock2);

        // assertion fails as Person equals only compares references.
        //assertThat(sherlock).isEqualTo(sherlock2);
    }

    @Test
    void reccursiveIgnoringNull(){

        class Address {
            int number;
            String street;
        }

        class Home {
            Address address = new Address();
        }

        class Person {
            String name;
            double height;
            Home home = new Home();

            Person(String s, double h)
            {
                this.name = s;
                this.height = h;
            }
        }


        Person sherlock = new Person("Sherlock", 1.80);
        sherlock.home.address.street = "Baker Street";
        sherlock.home.address.number = 221;

        Person noName = new Person(null, 1.80);
        noName.home.address.street = null;
        noName.home.address.number = 221;

        // assertion succeeds as name and home.address.street fields are ignored in the comparison
        assertThat(sherlock).usingRecursiveComparison().ignoringExpectedNullFields()
                .as("check sherlock - noName")
                .withErrorMessageForFields("home differ", "sherlock.home", "noName.home")
                .isEqualTo(noName);

        // assertion fails as name and home.address.street fields are populated for sherlock but not for noName.
        assertThat(noName).usingRecursiveComparison().ignoringExpectedNullFields()
                .as("check noName - sherlock")
                .withErrorMessageForFields("home differ", "sherlock.home", "noName.home")
                .isEqualTo(sherlock);
    }


    @Rule
    public final JUnitSoftAssertions softly3 = new JUnitSoftAssertions();


    @Test
    void basic_soft_assertions_example() {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat("George Martin").as("great authors").isEqualTo("JRR Tolkien");
        softly.assertThat(42).as("response to Everything").isGreaterThan(0);
        softly.assertThat("Gandalf").as("Wizzard ?").isEqualTo("Sauron");

        // Don't forget to call assertAll() otherwise no assertion errors are reported!
        softly.assertAll();
    }

    @Test
    void auto_closeable_soft_assertions_example() {
        try (AutoCloseableSoftAssertions softly2 = new AutoCloseableSoftAssertions()) {
            softly2.assertThat("George Martin").as("great authors").isEqualTo("JRR Tolkien");
            softly2.assertThat(42).as("response to Everything").isGreaterThan(10);
            softly2.assertThat("Gandalf").isEqualTo("Gandalf");
            // no need to call assertAll, this is done when softly is closed.
        }
    }

    @Test
    void assertSoftly_example() {
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat("George Martin").as("great authors").isEqualTo("JRR Tolkien");
            softly.assertThat(42).as("response to Everything").isGreaterThan(0);
            softly.assertThat("Sauron").isEqualTo("Sauron");
            // no need to call assertAll(), assertSoftly does it for us.
        });
    }


    @Test
    void testCollectorAssertion() {
        SoftAssertions softlyy = new SoftAssertions();
        StringBuilder reportBuilder = new StringBuilder(format("Assertions report:%n"));

        // register our callback
        softlyy.setAfterAssertionErrorCollected(error -> reportBuilder.append(format("------------------%n%s%n", error.getMessage())));
        // the AssertionError corresponding to the failing assertions are registered in the report
        softlyy.assertThat("The Beatles").isEqualTo("The Rolling Stones");
        softlyy.assertThat(123).isEqualTo(123).isEqualTo(456);
        softlyy.assertAll();
    }

    @Test
    public void assumptionsTest() {
        List<String> frodo = new ArrayList<String>();
        frodo.add("Frodo");
        frodo.add("33");
        frodo.add("HOBBIT");
        // since this assumption is true ...
        assumeThat(frodo.get(2)).isEqualTo("HOBBIT");
        // ... this assertion is performed
        assertThat(frodo).doesNotContain("Sam");
    }

    @Test
    public void conditionalAssertTests() {
        Set<String> JEDIS = newLinkedHashSet("Luke", "Yoda", "Obiwan");

        Condition<String> jediPower = new Condition<>(JEDIS::contains, "jedi power");
        Condition<String> jedi = new Condition<String>(JEDIS::contains, "jedi");

        assertThat("Yoda").is(jedi);
        assertThat("Vader").isNot(jedi);

        assertThat("Yoda").has(jediPower);
        assertThat("Solo").doesNotHave(jediPower);

        assertThat("Vader").is(jedi);
    }
    @Test
    public void condtionnalWithCollections() {
        Set<String> JEDIS = newLinkedHashSet("Luke", "Yoda", "Obiwan");
        Condition<String> jediPower = new Condition<>(JEDIS::contains, "jedi power");
        Condition<String> jedi = new Condition<String>(JEDIS::contains, "jedi");

        assertThat(list("Luke", "Yoda")).are(jedi);
        assertThat(list("Leia", "Solo")).areNot(jedi);

        assertThat(list("Luke", "Yoda")).have(jediPower);
        assertThat(list("Leia", "Solo")).doNotHave(jediPower);

        assertThat(list("Luke", "Yoda", "Leia")).areAtLeast(2, jedi);
        assertThat(list("Luke", "Yoda", "Leia")).haveAtLeast(2, jediPower);

        assertThat(list("Luke", "Yoda", "Leia")).areAtMost(2, jedi);
        assertThat(list("Luke", "Yoda", "Leia")).haveAtMost(2, jediPower);

        assertThat(list("Luke", "Yoda", "Leia")).areExactly(2, jedi);
        assertThat(list("Luke", "Yoda", "Leia")).haveExactly(2, jediPower);
    }
    @Test
    public void allOfCondition(){
        Set<String> JEDIS = newLinkedHashSet("Luke", "Yoda", "Obiwan");
        Condition<String> jediPower = new Condition<>(JEDIS::contains, "jedi power");
        Condition<String> jedi = new Condition<String>(JEDIS::contains, "jedi");
        List<String> SITHS = list("Sidious", "Vader", "Plagueis");
        Condition<String> sith = new Condition<>(SITHS::contains, "sith");

        assertThat("Vader").is(anyOf(jedi, sith));
        assertThat("Solo").is(allOf(not(jedi), not(sith)));
    }
}


