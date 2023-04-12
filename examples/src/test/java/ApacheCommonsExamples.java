
import org.apache.commons.lang3.Validate;

import java.util.ArrayList;

/**
 * The purpose of this class is to tinker with apache.commons.lang3.Validate 's
 * guard system, in order to draw a comparison with a few other similar systems,
 * such as Guava.
 */
public class ApacheCommonsExamples {

    private class StudentGrade {
        private ArrayList<Float> grades = new ArrayList<>(8);

        public void set(ArrayList<Float> newGrades) {
            Validate.isTrue(newGrades.size() == 8, "You can't have more or less grades than you have courses: current %d ; required 8", newGrades.size());
            this.grades = newGrades;
        }

        public ArrayList<Float> get() {
            return grades;
        }

        // Sets individual course's grade
        public void setMath(int courseNumber, Float newGrade) {
            Validate.inclusiveBetween(1, 8, courseNumber, "There is only 8 courses in this school.");
            Validate.notNull(newGrade, "You can't have a null grade.");
            this.grades.set(courseNumber - 1, newGrade);
        }
    }
}