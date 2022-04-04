import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Teacher {
    /*
    in main method:
    Welcome to Brightspace! Would you like to:
    1. Create a Course.
    2. Edit a Course.
    3. Remove a Course.
    4. Access a Course to view contents.
    5. Exit.
     */

    public String quizName;
    public String course;
    public String fileName;


    // if choice == 1
    public void createCourse() {

    }

    // if choice == 2
    public void editCourse() {

    }

    // if choice == 3
    public void removeCourse() {

    }

    // if choice == 4
    // accesses a course to add and delete quizzes
    public void accessCourse() throws IOException {
        Scanner scan = new Scanner(System.in);

        // prompts user to select a course to modify
        System.out.println("Please select a course to access:");
        ArrayList<String> courses = new ArrayList<>();
        try (BufferedReader bfr1 = new BufferedReader(new FileReader("src/Courses.txt"))) {
            String str;
            int count = 1;
            while ((str = bfr1.readLine()) != null) {
                courses.add(str);
                System.out.println(count + ". " + str);
                count += 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int courseNum = scan.nextInt();
        scan.nextLine();
        course = courses.get(courseNum - 1);

        System.out.println("Would you like to:\n1. Add a Quiz.\n2. Edit a Quiz.\n3. Remove a Quiz.\n4. View Quiz Grades.\n5. Exit.");
        int teacherCommand = scan.nextInt();
        scan.nextLine();

        File f1 = new File("src/Quizzes.txt");

        // creates a quiz in the given course
        if (teacherCommand == 1) {
            System.out.println("Enter the filename to write the quiz to:");
            fileName = scan.nextLine();
            System.out.println("Enter the name of the quiz:");
            quizName = scan.nextLine();

            PrintWriter pw1 = new PrintWriter(fileName);
            pw1.write(quizName + ", " + course);
            pw1.println();
            pw1.flush();

            // writes the quiz contents to overarching quizzes file in this format
            //      quizName, courseName, quizFileName
            PrintWriter pw2 = new PrintWriter(f1);
            pw2.write(quizName + ", " + course + ", " + fileName);
            pw2.println();
            pw2.flush();

            // prompts user for quiz questions and answers
            System.out.println("Enter the amount of questions on the quiz:");
            int questionAmount = scan.nextInt();
            scan.nextLine();
            String[] questions = new String[questionAmount];
            String[] options = new String[4];
            String[] answers = new String[questionAmount];
            for (int i = 0; i < questionAmount; i++) {
                int questionNumber = i + 1;
                System.out.println("Enter Question " + questionNumber + ":");
                questions[i] = scan.nextLine();
                System.out.println("Enter Question " + questionNumber + "'s answer options on separate lines:");
                int j = 0;
                for (j = 0; j < 4; j++) {
                    options[j] = scan.nextLine();
                }
                j = 0;
                System.out.println("Enter Question " + questionNumber + "'s answer:");
                answers[i] = scan.nextLine();

                // creates a single quiz file written in this format
                //      quizName, courseName
                //      MCQ@question@A:option@B:option@C:option@D:option
                if (answers[i].equalsIgnoreCase("a")) {
                    pw1.write("MCQ@" + questions[i] + "@AR:" + options[0] + "@B:" + options[1] + "@C:" + options[2] + "@D:" + options[3]);
                    pw1.println();
                    pw1.flush();
                } else if (answers[i].equalsIgnoreCase("b")) {
                    pw1.write("MCQ@" + questions[i] + "@A:" + options[0] + "@BR:" + options[1] + "@C:" + options[2] + "@D:" + options[3]);
                    pw1.println();
                    pw1.flush();
                } else if (answers[i].equalsIgnoreCase("c")) {
                    pw1.write("MCQ@" + questions[i] + "@A:" + options[0] + "@B:" + options[1] + "@CR:" + options[2] + "@D:" + options[3]);
                    pw1.println();
                    pw1.flush();
                } else if (answers[i].equalsIgnoreCase("d")) {
                    pw1.write("MCQ@" + questions[i] + "@A:" + options[0] + "@B:" + options[1] + "@C:" + options[2] + "@DR:" + options[3]);
                    pw1.println();
                    pw1.flush();
                }

            }
            pw1.close();
            pw2.close();

        // edits a quiz in the given course
        } else if (teacherCommand == 2) {
            System.out.println("Enter the name of the quiz:");
            quizName = scan.nextLine();
            System.out.println("Enter the filename of the quiz:");
            fileName = scan.nextLine();

            File f2 = new File(fileName);
            FileReader fr1 = new FileReader(f2);
            BufferedReader bfr2 = new BufferedReader(fr1);

            PrintWriter pw3 = new PrintWriter(fileName);

            ArrayList<String> quiz = new ArrayList<String>();
            String line = bfr2.readLine();
            while (line != null) {
                quiz.add(line);
                line = bfr2.readLine();
            }

            String[] questions = new String[quiz.size()];
            String[] options = new String[4];
            String[] answers = new String[quiz.size()];
            for (int i = 1; i < quiz.size(); i ++) {
                System.out.println("Enter a new Question" + i + ":");
                questions[i] = scan.nextLine();
                System.out.println("Enter Question " + i + "'s new answer options on separate lines:");
                for (int j = 0; j < 4; j++) {
                    options[j] = scan.nextLine();
                }
                System.out.println("Enter Question " + i + "'s new answer:");
                answers[i] = scan.nextLine();

                String newQuestion = "";

                // rewrites the chosen quiz file written in this format
                //      quizName, courseName
                //      MCQ@question@A:option@B:option@C:option@D:option
                if (answers[i].equalsIgnoreCase("a")) {
                    newQuestion = "MCQ@" + questions[i] + "@AR:" + options[0] + "@B:" + options[1] + "@C:" + options[2] + "@D:" + options[3];
                } else if (answers[i].equalsIgnoreCase("b")) {
                    newQuestion = "MCQ@" + questions[i] + "@A:" + options[0] + "@BR:" + options[1] + "@C:" + options[2] + "@D:" + options[3];
                } else if (answers[i].equalsIgnoreCase("c")) {
                    newQuestion = "MCQ@" + questions[i] + "@A:" + options[0] + "@B:" + options[1] + "@CR:" + options[2] + "@D:" + options[3];
                } else if (answers[i].equalsIgnoreCase("d")) {
                    newQuestion = "MCQ@" + questions[i] + "@A:" + options[0] + "@B:" + options[1] + "@C:" + options[2] + "@DR:" + options[3];
                }

                // replaces the line containing the old question with the new question
                String output = quiz.get(i);
                output = output.replaceAll(output, newQuestion);
                pw3.write(output);
                pw3.flush();
            }


        // removes a quiz in the given course
        } else if (teacherCommand == 3) {
            System.out.println("Enter the name of the quiz:");
            quizName = scan.nextLine();
            System.out.println("Enter the filename of the quiz:");
            fileName = scan.nextLine();

            // removes the String on overarching quizzes file with the same contents
            PrintWriter pw2 = new PrintWriter(f1);
            String search = quizName + ", " + course + ", " + fileName;
            search = search.replaceAll(search, " ");
            pw2.append(search);
            pw2.flush();
            pw2.close();
        }
    }
}
