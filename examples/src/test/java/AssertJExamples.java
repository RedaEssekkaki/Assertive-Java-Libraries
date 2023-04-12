import org.assertj.core.api.Assertions;
import org.assertj.core.description.Description;

import static org.assertj.core.api.Assertions.*;


import org.junit.jupiter.api.*;

import java.util.*;
import java.util.function.Consumer;


public class AssertJExamples {

    final StringBuilder descriptionReportBuilder = new StringBuilder(String.format("Assertions:%n"));;
    Consumer<Description> descriptionConsumer;

    @BeforeEach
    void setup(){
        // initialize the description consumer
        descriptionConsumer = desc -> descriptionReportBuilder.append(String.format("-- %s%n", desc));

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
        assertThat(sherlock).usingRecursiveComparison().ignoringExpectedNullFields().as("check sherlock - noName").withFailMessage("probleme de nullitée", sherlock, noName).isEqualTo(noName);

        // assertion fails as name and home.address.street fields are populated for sherlock but not for noName.
        //assertThat(noName).usingRecursiveComparison().ignoringExpectedNullFields().as("check noName - sherlock").withFailMessage("probleme de nullitée", sherlock, noName).isEqualTo(sherlock);
    }
}