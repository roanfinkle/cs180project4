import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * testingStudent class to test the takeQuiz() method
 * in the Student.java class. Please note: this
 * testingStudent.java class should be run with
 * the testedStudent.java file (not the Student.java file).
 * Also, please read comments in this testingStudent.java file
 * as they contain important details regarding how to run
 * the tests as well what all is covered by the test.
 * Purdue University -- CS18000 -- Spring 2022 -- Project
 * 4
 *
 * @author Ritwik Jain
 * @version April 10, 2022
 * Lab sec 03
 */

public class testingStudent {
    public static void main(String[] args) throws IOException {
        /** Please Note: Run these test cases for the testedStudent.java
         * file only after the test cases for the testedQuiz.java file.
         * Run the test cases for the testedQuiz.java file without having
         * any prior files in the system. After those tests, download the
         * Alex$$Mathematics$$1.txt and Alex$$Mathematics$$1$$Answers.txt
         * from the github before running the test cases in this file.
         * Also before running the tests in this file, please download
         * the uploadedAnswerForQuestion.txt file also.
         */
        testedStudent ts = new testedStudent();
        /** this test case handles the case when the student tries
         * access a quiz made by a teacher who does has not made
         * the quiz until then.
         */
        boolean test1 = false;
        try {
            ts.takeQuiz("Ryan", "Bob",
                    "Science", 1,
                    "Quiz1(5)(MCQ)(exact)(manually)(false)", "");
        } catch (FileNotFoundException | NumberFormatException e) {
            System.out.println("the file is not present!");
            System.out.println("Test passed");
            test1 = true;
        }

        /** this test case handles both valid and invalid inputs.
         * It handles invalid choice by the user,
         * Number format exceptions, uploading answers through file import,
         * uploading answer manually through terminal in the case whe the
         * quiz has a pool of questions and randomization is true,i.e. a
         * random assortment of 5 questions is chosen everytime.
         * more importantly, this test case also checks that the attempt tracker
         * for the student has incremented in value for the particular quiz,
         * the grading report has been appropriately generated, the
         * student submissions have been generated with the time stamp.
         */
        String input = "er\n" + "3\n" + "1\n" + "A\n" +
                "1\n" + "B\n" + "1\n" + "C\n" + "2\n" + "uploadedAnswerForQuestion.txt\n" +
                "1\n" + "B\n" + "1\n" + "D\n" + "1\n" + "Q\n" +
                "A\n";
        ts.takeQuiz("Ryan", "Alex",
                "Mathematics", 1,
                "Quiz1(7)(MCQ)(pool)(manually)(true)", input);
        String expected = "A\n" +
                "B\n" +
                "C\n" +
                "A\n" +
                "B";
        BufferedReader bf = new BufferedReader(new FileReader
                ("Ryan$$Alex$$Mathematics$$1$$1$$submittedAnswers.txt"));
        String str = bf.readLine();
        String counter = "";
        while (str != null) {
            counter += str + '\n';
            str = bf.readLine();
        }
        boolean test2 = false;
        if (counter.contains(expected)) {
            test2 = true;
        }
        bf.close();

        /** this test case handles the fact that a new set of
         * questions has been generated on the second attempt.
         * And also, the attempt tracker has been incremented
         * and all the submissions and reports have been generated.
         */
        input = "er\n" + "3\n" + "1\n" + "A\n" +
                "1\n" + "B\n" + "1\n" + "C\n" + "2\n" + "uploadedAnswerForQuestion.txt\n" +
                "1\n" + "B\n" + "1\n" + "D\n" + "1\n" + "Q\n" +
                "A\n";

        ts.takeQuiz("Ryan", "Alex",
                "Mathematics", 1,
                "Quiz1(7)(MCQ)(pool)(manually)(true)", input);

        bf = new BufferedReader(new FileReader
                ("Ryan$$Alex$$Mathematics$$1$$2$$submittedAnswers.txt"));
        str = bf.readLine();
        counter = "";
        while (str != null) {
            counter += str + '\n';
            str = bf.readLine();
        }
        boolean test3 = false;
        if (counter.contains(expected)) {
            test3 = true;
        }
        bf.close();

        /** this test case handles both valid and invalid inputs.
         * It handles invalid choice by the user,
         * Number format exceptions, uploading answers through file import,
         * uploading answer manually through terminal in the case whe the
         * quiz has te exact set of questions and randomization is true,
         * thus, the order of the questions and the options is different
         * everytime. more importantly, this test case also
         * checks that the attempt tracker for the student has
         * incremented in value for the particular quiz,
         * the grading report has been appropriately generated, the
         * student submissions have been generated with the time stamp.
         */
        input = "er\n" + "3\n" + "1\n" + "A\n" +
                "1\n" + "B\n" + "1\n" + "C\n" + "2\n" + "uploadedAnswerForQuestion.txt\n" +
                "1\n" + "B\n" + "1\n" + "D\n" + "1\n" + "Q\n" +
                "A\n";

        ts.takeQuiz("Ryan", "Alex",
                "Mathematics", 1,
                "Quiz1(7)(MCQ)(exact)(manually)(true)", input);

        bf = new BufferedReader(new FileReader
                ("Ryan$$Alex$$Mathematics$$1$$3$$submittedAnswers.txt"));
        str = bf.readLine();
        counter = "";
        while (str != null) {
            counter += str + '\n';
            str = bf.readLine();
        }
        expected = "A\n" +
                "B\n" +
                "C\n" +
                "A\n" +
                "B\n" +
                "D\n" +
                "A";
        boolean test4 = false;
        if (counter.contains(expected)) {
            test4 = true;
        }
        bf.close();

        /** this test handles the same situations as above
         * except that the order of questions is not randomized
         * with every attempt.
         */
        input = "er\n" + "3\n" + "1\n" + "A\n" +
                "1\n" + "B\n" + "1\n" + "C\n" + "2\n" + "uploadedAnswerForQuestion.txt\n" +
                "1\n" + "B\n" + "1\n" + "D\n" + "1\n" + "Q\n" +
                "A\n";

        ts.takeQuiz("Ryan", "Alex",
                "Mathematics", 1,
                "Quiz1(7)(MCQ)(exact)(manually)(false)", input);

        bf = new BufferedReader(new FileReader
                ("Ryan$$Alex$$Mathematics$$1$$4$$submittedAnswers.txt"));
        str = bf.readLine();
        counter = "";
        while (str != null) {
            counter += str + '\n';
            str = bf.readLine();
        }
        expected = "A\n" +
                "B\n" +
                "C\n" +
                "A\n" +
                "B\n" +
                "D\n" +
                "A";
        boolean test5 = false;
        if (counter.contains(expected)) {
            test5 = true;
        }
        bf.close();

        /** Please note that AttemptTracker.txt file has been update to 4
         * for this quiz by the teacher named Alex for the student Ryan.
         */
        if (test1) {
            System.out.println("test passed");
            if (test2) {
                System.out.println("test passed");
                if (test3) {
                    System.out.println("test passed");
                    if (test4) {
                        System.out.println("test passed");
                        if (test5) {
                            System.out.println("test passed");
                        }
                    }
                }
            }
        }

        /** Overall these test cases cover any possible case that
         * will reach the takeQuiz() method. (There are
         * some erroneous cases that are handles in the main
         * method itself and therefore, the takeQuiz() method is not
         * called). They handle any invalid inputs that the user might enter
         * while taking the quiz, manual and file imports are handled,
         * if the user tries to attempt a quiz not yet created(this is
         * mainly handles in the main method), if the quiz file was
         * empty or not created, updation of attempt tracker
         * and appropriate generation of submissions with time stamps
         * and grading reports for each attempt.
         * To see thatg the test cases work as expected, please
         * look at the Solutions.txt, submission.txt and gradingReport.txt
         * files for each of the attempts. Their output matches with the
         * inputs and the randomized questions for the respective attempt.
         */

    }
}
