import java.io.*;

/**
 * testingQuiz class to run test cases on each method 
 * of Quiz.java, please note that for testing purposes
 * this class should be run with testedQuiz.java(not Quiz.java)
 * for the testing to be successful.
 * Purdue University -- CS18000 -- Spring 2022 -- Project
 * 4
 *
 * @author Ritwik Jain
 * @version April 10, 2022
 * Lab sec 03
 */

public class testingQuiz {
    public static void main(String[] args) throws IOException {
        String input1 = "Arithmetics\n" + "1\n" + "What is 1+1?\n"
                + "1\n" + "2\n" + "3\n" + "4\n" + "B\n" + "1\n" +
                "What is 2+1?\n" + "1\n" + "2\n" + "3\n" + "4\n" +
                "C\n" + "1\n" + "What is 3+1?\n" + "1\n" + "2\n" +
                "3\n" + "4\n" + "D\n" + "1\n" + "What is 1*1?\n"
                + "1\n" + "2\n" + "3\n" + "4\n" + "A\n" + "1\n" +
                "What is 2+2?\n" + "1\n" + "2\n" + "3\n" + "4\n" + "D\n"
                + "1\n" + "What is 3-2?\n" + "1\n" + "2\n" + "3\n" + "4\n" +
                "A\n" + "1\n" + "What is 4-1?\n" + "1\n" + "2\n" + "3\n" +
                "4\n" + "C\n" + "2\n";
        testedQuiz qz = new testedQuiz(1);
        qz.makeQuizManual("Alex$$Mathematics$$1.txt", "MCQ", input1);
        BufferedReader br1 = new BufferedReader(new FileReader("Alex$$Mathematics$$1.txt"));
        String reader1 = br1.readLine();
        String counter1 = "";
        String expected1 = "Arithmetics\n" +
                "1. What is 1+1?\n" +
                "A) 1\n" +
                "B) 2\n" +
                "C) 3\n" +
                "D) 4\n" +
                "2. What is 2+1?\n" +
                "A) 1\n" +
                "B) 2\n" +
                "C) 3\n" +
                "D) 4\n" +
                "3. What is 3+1?\n" +
                "A) 1\n" +
                "B) 2\n" +
                "C) 3\n" +
                "D) 4\n" +
                "4. What is 1*1?\n" +
                "A) 1\n" +
                "B) 2\n" +
                "C) 3\n" +
                "D) 4\n" +
                "5. What is 2+2?\n" +
                "A) 1\n" +
                "B) 2\n" +
                "C) 3\n" +
                "D) 4\n" +
                "6. What is 3-2?\n" +
                "A) 1\n" +
                "B) 2\n" +
                "C) 3\n" +
                "D) 4\n" +
                "7. What is 4-1?\n" +
                "A) 1\n" +
                "B) 2\n" +
                "C) 3\n" +
                "D) 4\n";
        while (reader1 != null) {
            counter1 += reader1 + '\n';
            reader1 = br1.readLine();
        }
        if (counter1.equals(expected1)) {
            System.out.println("test passed");
        }
        br1.close();
        br1 = new BufferedReader(new FileReader("Alex$$Mathematics$$1$$Answers.txt"));
        reader1 = br1.readLine();
        counter1 = "";
        String expected2 = "B\n" +
                "C\n" +
                "D\n" +
                "A\n" +
                "D\n" +
                "A\n" +
                "C\n";
        while (reader1 != null) {
            counter1 += reader1 + '\n';
            reader1 = br1.readLine();
        }
        if (counter1.equals(expected2)) {
            System.out.println("test passed");
        }
        br1.close();
        String input2 = "Arithmetics\n" + "1\n" + "What is 1+1?\n"
                + "1\n" + "2\n" + "3\n" + "4\n" + "E\n" + "B\n" + "1\n" +
                "What is 2+1?\n" + "1\n" + "2\n" + "3\n" + "4\n" +
                "C\n" + "1\n" + "What is 3+1?\n" + "1\n" + "2\n" +
                "3\n" + "4\n" + "D\n" + "1\n" + "What is 1*1?\n"
                + "1\n" + "2\n" + "3\n" + "4\n" + "A\n" + "1\n" +
                "What is 2+2?\n" + "1\n" + "2\n" + "3\n" + "4\n" + "D\n"
                + "1\n" + "What is 3-2?\n" + "1\n" + "2\n" + "3\n" + "4\n" +
                "A\n" + "1\n" + "What is 4-1?\n" + "1\n" + "2\n" + "3\n" +
                "4\n" + "C\n" + "2\n";
        qz.makeQuizManual("Miranda$$Mathematics$$1.txt", "MCQ", input2);
        br1 = new BufferedReader(new FileReader("Miranda$$Mathematics$$1.txt"));
        reader1 = br1.readLine();
        counter1 = "";
        String expected3 = expected1;
        while (reader1 != null) {
            counter1 += reader1 + '\n';
            reader1 = br1.readLine();
        }
        if (counter1.equals(expected3)) {
            System.out.println("test passed");
        }
        br1.close();
        br1 = new BufferedReader(new FileReader("Miranda$$Mathematics$$1$$Answers.txt"));
        reader1 = br1.readLine();
        counter1 = "";
        String expected0 = "B\n" +
                "C\n" +
                "D\n" +
                "A\n" +
                "D\n" +
                "A\n" +
                "C\n";
        ;
        while (reader1 != null) {
            counter1 += reader1 + '\n';
            reader1 = br1.readLine();
        }
        if (counter1.equals(expected0)) {
            System.out.println("test passed");
        }
        br1.close();
        String input3 = "Arithmetics\n" + "2\n";
        qz.makeQuizManual("Aryan$$Mathematics$$1.txt", "MCQ", input3);
        br1 = new BufferedReader(new FileReader("Aryan$$Mathematics$$1.txt"));
        reader1 = br1.readLine();
        counter1 = "";
        while (reader1 != null) {
            counter1 += reader1 + '\n';
            reader1 = br1.readLine();
        }
        String expected29 = "Arithmetics\n";
        if (counter1.equals(expected29)) {
            System.out.println("test passed");
        }
        br1.close();
        String input4 = "Arithmetics\n" + "jk\n" + "2\n";
        qz.makeQuizManual("Peter$$Mathematics$$1.txt", "MCQ", input4);
        br1 = new BufferedReader(new FileReader("Peter$$Mathematics$$1.txt"));
        reader1 = br1.readLine();
        counter1 = "";
        while (reader1 != null) {
            counter1 += reader1 + '\n';
            reader1 = br1.readLine();
        }
        String expected30 = "Arithmetics\n";
        if (counter1.equals(expected30)) {
            System.out.println("test passed");
        }
        br1.close();
        String input5 = "1\n" + "Modular Arithmetic\n" + "2\n" +
                "What is 12 % 5?\n" + "1\n" + "2\n" + "3\n" + "4\n" +
                "B\n" + "89\n" + "3\n" + "2\n" + "6\n" + "4\n" + "we\n" +
                "2\n" + "What is my age?\n" + "23\n" + "19\n" + "45\n" +
                "15\n" + "B\n" + "5\n";
        qz.makeQuizManual("Alex$$Mathematics$$1.txt", "MCQ", input5);
        String expected5 = "Modular Arithmetic\n" +
                "1. What is 1+1?\n" +
                "A) 1\n" +
                "B) 2\n" +
                "C) 3\n" +
                "D) 4\n" +
                "2. What is my age?\n" +
                "A) 23\n" +
                "B) 19\n" +
                "C) 45\n" +
                "D) 15\n" +
                "3. What is 1*1?\n" +
                "A) 1\n" +
                "B) 2\n" +
                "C) 3\n" +
                "D) 4\n" +
                "4. What is 2+2?\n" +
                "A) 1\n" +
                "B) 2\n" +
                "C) 3\n" +
                "D) 4\n" +
                "5. What is 3-2?\n" +
                "A) 1\n" +
                "B) 2\n" +
                "C) 3\n" +
                "D) 4\n" +
                "6. What is 4-1?\n" +
                "A) 1\n" +
                "B) 2\n" +
                "C) 3\n" +
                "D) 4\n" +
                "7. What is 12 % 5?\n" +
                "A) 1\n" +
                "B) 2\n" +
                "C) 3\n" +
                "D) 4\n";
        BufferedReader br5 = new BufferedReader(new FileReader("Alex$$Mathematics$$1.txt"));
        String reader = br5.readLine();
        String counter = "";
        while (reader != null) {
            counter += reader + '\n';
            reader = br5.readLine();
        }
        if (counter.equals(expected5)) {
            System.out.println("test passed");
        }
        BufferedReader br4 = new BufferedReader(new FileReader("Alex$$Mathematics$$1$$Answers.txt"));
        String reader4 = br4.readLine();
        String counter4 = "";
        String expected4 = "B\n" +
                "B\n" +
                "A\n" +
                "D\n" +
                "A\n" +
                "C\n" +
                "B\n";
        while (reader4 != null) {
            counter4 += reader4 + '\n';
            reader4 = br4.readLine();
        }
        if (counter4.equals(expected4)) {
            System.out.println("test passed");
        }
        qz.makeExactQuizFile("Alex$$Mathematics$$1.txt", "Monica$$Mathematics$$1.txt",
                "Alex$$Mathematics$$1$$Answers.txt");
        BufferedReader br6 = new BufferedReader(new FileReader("Monica$$Mathematics$$1.txt"));
        String reader6 = br6.readLine();
        String counter6 = "";
        String expected6 = expected5;
        while (reader6 != null) {
            counter6 += reader6 + '\n';
            reader6 = br6.readLine();
        }
        if (counter6.equals(expected6)) {
            System.out.println("test passed");
        }
        br6.close();
        BufferedReader br7 = new BufferedReader(new FileReader("Monica$$Mathematics$$1$$Answers.txt"));
        String reader7 = br7.readLine();
        String counter7 = "";
        String expected7 = expected4;
        while (reader7 != null) {
            counter7 += reader7 + '\n';
            reader7 = br7.readLine();
        }
        if (counter7.equals(expected7)) {
            System.out.println("test passed");
        }
        br7.close();
        qz.makePoolQuizFile("Alex$$Mathematics$$1.txt", "Sarah$$Mathematics$$1.txt",
                "Alex$$Mathematics$$1$$Answers.txt");
        br6 = new BufferedReader(new FileReader("Sarah$$Mathematics$$1.txt"));
        reader6 = br6.readLine();
        counter6 = "";
        expected6 = expected5;
        while (reader6 != null) {
            counter6 += reader6 + '\n';
            reader6 = br6.readLine();
        }
        if (counter6.equals(expected6)) {
            System.out.println("test passed");
        }
        br6.close();
        br7 = new BufferedReader(new FileReader("Sarah$$Mathematics$$1$$Answers.txt"));
        reader7 = br7.readLine();
        counter7 = "";
        expected7 = expected4;
        while (reader7 != null) {
            counter7 += reader7 + '\n';
            reader7 = br7.readLine();
        }
        if (counter7.equals(expected7)) {
            System.out.println("test passed");
        }
        br7.close();
    }
}
