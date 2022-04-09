import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) throws IOException {
        do {
            File f34 = new File("teacherList.txt");
            f34.createNewFile();
            File erty = new File("studentList.txt");
            erty.createNewFile();
            Scanner sc = new Scanner(System.in);
            String rol;
            boolean alreadyRegistered = false;
            boolean looper = false;
            String category;
            String name;
            String userName;
            String password;
            do {
                boolean hoho;
                do {
                    System.out.println("Enter 1 or 2 from the following options:");
                    System.out.println("1. Register\n2. Login");
                    rol = sc.nextLine();
                    if (rol.equals("1") || rol.equals("2")) {
                        hoho = true;
                    } else {
                        System.out.println("Invalid input!");
                        hoho = false;
                    }
                } while (!hoho);
                if (rol.equals("1")) {
                    boolean hjhj;
                    do {
                        System.out.println("Are you a student or a teacher?");
                        System.out.println("Enter the word \"Student\" or \"Teacher\"");
                        category = sc.nextLine();
                        if (category.toUpperCase().equals("STUDENT") || category.toUpperCase().equals("TEACHER")) {
                            hjhj = true;
                        } else {
                            System.out.println("Invalid input!");
                            hjhj = false;
                        }
                    } while (!hjhj);
                    System.out.println("Enter your name:");
                    name = sc.nextLine();
                    System.out.println("Enter your username:");
                    userName = sc.nextLine();
                    System.out.println("Enter your password:");
                    password = sc.nextLine();
                    if (category.toUpperCase().equals("STUDENT")) {
                        BufferedReader read = new BufferedReader(new FileReader(erty));
                        String logins = read.readLine();
                        String adder = "";
                        adder += logins + "\n";
                        while (logins != null) {
                            if (logins.equals(name + "$$" + userName + "$$" + password)) {
                                alreadyRegistered = true;
                                looper = true;
                            }
                            logins = read.readLine();
                            adder += logins + "\n";
                        }
                        if (alreadyRegistered) {
                            System.out.println("You are already a registered user and have been directly logged in.");
                            PrintWriter pen = new PrintWriter(new FileWriter(erty));
                            pen.print(adder);
                            pen.flush();
                        } else {
                            adder += name + "$$" + userName + "$$" + password + "\n";
                            PrintWriter pen = new PrintWriter(new FileWriter(erty));
                            pen.print(adder);
                            pen.flush();
                            System.out.println("You have been successfully registered.");
                            looper = true;
                        }
                    } else {
                        BufferedReader read = new BufferedReader(new FileReader(f34));
                        String logins = read.readLine();
                        String adder = "";
                        adder += logins + "\n";
                        while (logins != null) {
                            if (logins.equals(name + "$$" + userName + "$$" + password)) {
                                alreadyRegistered = true;
                                looper = true;
                            }
                            logins = read.readLine();
                            adder += logins + "\n";
                        }
                        if (!alreadyRegistered) {
                            adder += name + "$$" + userName + "$$" + password + "\n";
                            PrintWriter pen = new PrintWriter(new FileWriter(f34));
                            pen.print(adder);
                            pen.flush();
                            System.out.println("You have been successfully registered.");
                            looper = true;
                        } else {
                            System.out.println("You are already a registered user and have been directly logged in.");
                        }
                    }
                } else {
                    do {
                        System.out.println("Are you a student or a teacher?");
                        System.out.println("Enter the word \"Student\" or \"Teacher\"");
                        category = sc.nextLine();
                        if (category.toUpperCase().equals("STUDENT") || category.toUpperCase().equals("TEACHER")) {
                            break;
                        } else {
                            System.out.println("Invalid input!");
                        }
                    } while (true);
                    System.out.println("Enter your name:");
                    name = sc.nextLine();
                    System.out.println("Enter your username:");
                    userName = sc.nextLine();
                    System.out.println("Enter your password:");
                    password = sc.nextLine();
                    if (category.toUpperCase().equals("STUDENT")) {
                        BufferedReader read = new BufferedReader(new FileReader(erty));
                        String logins = read.readLine();
                        String adder = "";
                        adder += logins + "\n";
                        while (logins != null) {
                            if (logins.equals(name + "$$" + userName + "$$" + password)) {
                                alreadyRegistered = true;
                                looper = true;
                            }
                            logins = read.readLine();
                            adder += logins + "\n";
                        }
                        if (!alreadyRegistered) {
                            System.out.println("Name, Username, and Password do not match any entries!");
                            looper = false;
                        } else {
                            System.out.println("Logged in.");
                        }
                    } else {
                        BufferedReader read = new BufferedReader(new FileReader(f34));
                        String logins = read.readLine();
                        String adder = "";
                        adder += logins + "\n";
                        while (logins != null) {
                            if (logins.equals(name + "$$" + userName + "$$" + password)) {
                                alreadyRegistered = true;
                                looper = true;
                            }
                            logins = read.readLine();
                            adder += logins + "\n";
                        }
                        if (!alreadyRegistered) {
                            System.out.println("Name, Username, and Password do not match any entries!");
                            looper = false;
                        } else {
                            System.out.println("Logged in.");
                        }
                    }
                }
            } while (!looper);

            if (category.toUpperCase().equals("TEACHER")) {
                Teacher t = null;
                if (alreadyRegistered) {
                    t = new Teacher(userName, password, name);
                } else {
                    t = new Teacher(userName, password, name, "teacherList.txt");
                }
                String courseListFileName = name + "$$" + userName + "$$" + password;
                courseListFileName += "$$Courses.txt";
                File f = new File(courseListFileName);
                f.createNewFile();
                boolean a;
                do {
                    int teacherChoice = 0;
                    do {
                        System.out.println("Choose from the following options. Enter 1, 2, 3, or 4.");
                        System.out.println("1. Create a new course");
                        System.out.println("2. Enter and edit a course");
                        System.out.println("3. Delete an existing course");
                        System.out.println("4. Log out");
                        String teacherChoiceString = sc.nextLine();
                        if (teacherChoiceString.equals("1")) {
                            teacherChoice = 1;
                        } else if (teacherChoiceString.equals("2")) {
                            teacherChoice = 2;
                        } else if (teacherChoiceString.equals("3")) {
                            teacherChoice = 3;
                        } else if (teacherChoiceString.equals("4")) {
                            teacherChoice = 4;
                        } else {
                            System.out.println("Invalid Input!");
                            teacherChoice = 0;
                        }
                    } while (teacherChoice == 0);
                    switch (teacherChoice) {
                        case 1: {
                            System.out.println("Enter the course name:");
                            String courseName = sc.nextLine();
                            try {
                                if (t.createCourse(courseName, courseListFileName)) {
                                    File f1 = new File(name + "$$" + courseName + "$$quizList.txt");
                                    f1.createNewFile();
                                    System.out.println("Course successfully created.");
                                }
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            a = false;
                            break;
                        }
                        case 2: {
                            System.out.println("Enter the course name:");
                            String courseName = sc.nextLine();
                            boolean fgh = true;
                            try {
                                fgh = t.enterCourse(courseName, courseListFileName);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (fgh) {
                                System.out.println("You are now inside the specified course.");
                                boolean jkl = false;
                                do {
                                    int choiceAgain = 0;
                                    do {
                                        System.out.println("Choose from the following options.");
                                        System.out.println("1. Create a new quiz");
                                        System.out.println("2. Enter and edit an existing quiz");
                                        System.out.println("3. View student submissions for a quiz");
                                        System.out.println("4. Delete an existing quiz");
                                        System.out.println("5. Exit to Courses");
                                        String choiceAgainString = sc.nextLine();
                                        if (choiceAgainString.equals("1")) {
                                            choiceAgain = 1;
                                        } else if (choiceAgainString.equals("2")) {
                                            choiceAgain = 2;
                                        } else if (choiceAgainString.equals("3")) {
                                            choiceAgain = 3;
                                        } else if (choiceAgainString.equals("4")) {
                                            choiceAgain = 4;
                                        } else if (choiceAgainString.equals("5")) {
                                            choiceAgain = 5; 
                                        } else {
                                            System.out.println("Invalid Input!");
                                            choiceAgain = 0;
                                        }
                                    } while (choiceAgain == 0);
                                    switch (choiceAgain) {
                                        case 1: {
                                            String quizListFileName = name + "$$" + courseName + "$$quizList.txt";
                                            System.out.println("Enter the quiz number:");
                                            int quizNumber = sc.nextInt();
                                            sc.nextLine();
                                            System.out.println("Enter the number of questions in the quiz");
                                            int numOfQuestions = sc.nextInt();
                                            sc.nextLine();
                                            boolean b;
                                            int choice2;
                                            String type = "";
                                            do {
                                                System.out.println(
                                                        "Do you want to add MCQs, fill im the blanks or true/false?");
                                                System.out.println("1. MCQs\n2. fill in the blanks\n3. true/false");
                                                choice2 = sc.nextInt();
                                                sc.nextLine();
                                                if (choice2 == 1 || choice2 == 2 || choice2 == 3) {
                                                    b = false;
                                                    if (choice2 == 1) {
                                                        type = "MCQ";
                                                    } else if (choice2 == 2) {
                                                        type = "fill in the blanks";
                                                    } else {
                                                        type = "true/false";
                                                    }
                                                } else {
                                                    System.out.println("Enter a valid choice");
                                                    b = true;
                                                }
                                            } while (b);
                                            int choice90;
                                            boolean bn;
                                            String poolOrExact = "";
                                            do {
                                                System.out.print(
                                                        "Do you want to add a pool of questions to randomly choose from or ");
                                                System.out.println("the exact questions for the quiz?");
                                                System.out.println("1. pool of questions\n2. exact questions");
                                                choice90 = sc.nextInt();
                                                sc.nextLine();
                                                if (choice90 == 1 || choice90 == 2) {
                                                    bn = false;
                                                    if (choice90 == 1) {
                                                        poolOrExact = "pool";
                                                    } else {
                                                        poolOrExact = "exact";
                                                    }
                                                } else {
                                                    System.out.println("Enter a valid choice");
                                                    bn = true;
                                                }
                                            } while (bn);
                                            int choice89;
                                            String fileOrManually = "";
                                            do {
                                                System.out.println("Do you want to attach a file or add manually?");
                                                System.out.println("1. attach a file\n2. add manually");
                                                choice89 = sc.nextInt();
                                                sc.nextLine();
                                                if (choice89 == 1 || choice89 == 2) {
                                                    bn = false;
                                                    if (choice89 == 1) {
                                                        fileOrManually = "file";
                                                    } else {
                                                        fileOrManually = "manually";
                                                    }
                                                } else {
                                                    System.out.println("Enter a valid choice");
                                                    bn = true;
                                                }
                                            } while (bn);
                                            int choice88;
                                            boolean randomize = true;
                                            do {
                                                System.out.println("Do you want to randomize this quiz or not?");
                                                System.out.println("1. randomize\n2. don't randomize");
                                                choice88 = sc.nextInt();
                                                sc.nextLine();
                                                if (choice88 == 1 || choice88 == 2) {
                                                    bn = false;
                                                    if (choice89 == 1) {
                                                        randomize = true;
                                                    } else {
                                                        randomize = false;
                                                    }
                                                } else {
                                                    System.out.println("Enter a valid choice");
                                                    bn = true;
                                                }
                                            } while (bn);
                                            boolean tyu = true;
                                            try {
                                                File attempters = new File(name + "$$" + courseName + "$$" + quizNumber
                                                        + "$$Attempters.txt");
                                                attempters.createNewFile();
                                                tyu = t.getListCourses().get(t.getListCourses().size() - 1).createQuiz(
                                                        quizNumber, quizListFileName, numOfQuestions, type, poolOrExact,
                                                        fileOrManually, randomize);
                                            } catch (FileNotFoundException e) {
                                                e.printStackTrace();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                            if (tyu) {
                                                if (choice90 == 1 && choice89 == 1) {
                                                    boolean d;
                                                    do {
                                                        System.out.println("Enter the file path of the quiz");
                                                        String filePath = sc.nextLine();
                                                        System.out.println(
                                                                "Enter the file path of the answers to the quiz");
                                                        String answerFilePath = sc.nextLine();
                                                        int n = t.getListCourses().size();
                                                        int size = t.getListCourses().get(n - 1).getListQuizzes()
                                                                .size();
                                                        String quizFileName = name + "$$" + courseName + "$$"
                                                                + Integer.toString(quizNumber) + ".txt";
                                                        d = t.getListCourses().get(n - 1).getListQuizzes().get(size - 1)
                                                                .makePoolQuizFile(filePath, quizFileName,
                                                                        answerFilePath);
                                                        if (d) {
                                                            System.out.println("The quiz was made successfully");
                                                        } else {
                                                            System.out.println(
                                                                    "Either the file was empty or the path was invalid. Please enter a valid path.");
                                                        }
                                                    } while (!d);
                                                } else if (choice90 == 2 && choice89 == 1) {
                                                    boolean d;
                                                    do {
                                                        System.out.println("Enter the file path");
                                                        String filePath = sc.nextLine();
                                                        System.out.println(
                                                                "Enter the file path of the answers to the quiz");
                                                        String answerFilePath = sc.nextLine();
                                                        int n = t.getListCourses().size();
                                                        int size = t.getListCourses().get(n - 1).getListQuizzes()
                                                                .size();
                                                        String quizFileName = name + "$$" + courseName + "$$"
                                                                + Integer.toString(quizNumber) + ".txt";
                                                        d = t.getListCourses().get(n - 1).getListQuizzes().get(size - 1)
                                                                .makeExactQuizFile(filePath, quizFileName,
                                                                        answerFilePath);
                                                        if (d) {
                                                            System.out.println("The quiz was made successfully");
                                                        } else {
                                                            System.out.println(
                                                                    "Either the file was empty or the path was invalid. Please enter a valid path.");
                                                        }
                                                    } while (!d);
                                                } else {
                                                    String quizFileName = name + "$$" + courseName + "$$"
                                                            + Integer.toString(quizNumber) + ".txt";
                                                    int n = t.getListCourses().size();
                                                    int size = t.getListCourses().get(n - 1).getListQuizzes().size();
                                                    t.getListCourses().get(n - 1).getListQuizzes().get(size - 1)
                                                            .makeQuizManual(quizFileName, type);
                                                }
                                            }
                                            jkl = false;
                                            break;
                                        }
                                        case 2: {
                                            String quizListFileName = name + "$$" + courseName + "$$quizList.txt";
                                            System.out.println("Enter the quiz number");
                                            int quizNumber = sc.nextInt();
                                            sc.nextLine();
                                            if (t.getListCourses().get(t.getListCourses().size() - 1)
                                                    .enterQuiz(quizNumber, quizListFileName).equals("filepool")) {
                                                boolean d;
                                                do {
                                                    System.out.println("Enter the file path");
                                                    String filePath = sc.nextLine();
                                                    System.out
                                                            .println("Enter the file path of the answers to the quiz");
                                                    String answerFilePath = sc.nextLine();
                                                    int n = t.getListCourses().size();
                                                    int size = t.getListCourses().get(n - 1).getListQuizzes().size();
                                                    String quizFileName = name + "$$" + courseName + "$$"
                                                            + Integer.toString(quizNumber) + ".txt";
                                                    d = t.getListCourses().get(n - 1).getListQuizzes().get(size - 1)
                                                            .makePoolQuizFile(filePath, quizFileName, answerFilePath);
                                                    if (d) {
                                                        System.out.println("The quiz was made successfully");
                                                    } else {
                                                        System.out.println(
                                                                "Either the file was empty or the path was invalid. Please enter a valid path.");
                                                    }
                                                } while (!d);
                                            } else if (t.getListCourses().get(t.getListCourses().size() - 1)
                                                    .enterQuiz(quizNumber, quizListFileName).equals("fileexact")) {
                                                boolean d;
                                                do {
                                                    System.out.println("Enter the file path");
                                                    String filePath = sc.nextLine();
                                                    System.out
                                                            .println("Enter the file path of the answers to the quiz");
                                                    String answerFilePath = sc.nextLine();
                                                    int n = t.getListCourses().size();
                                                    int size = t.getListCourses().get(n - 1).getListQuizzes().size();
                                                    String quizFileName = name + "$$" + courseName + "$$"
                                                            + Integer.toString(quizNumber) + ".txt";
                                                    d = t.getListCourses().get(n - 1).getListQuizzes().get(size - 1)
                                                            .makeExactQuizFile(filePath, quizFileName, answerFilePath);
                                                    if (d) {
                                                        System.out.println("The quiz was made successfully");
                                                    } else {
                                                        System.out.println(
                                                                "Either the file was empty or the path was invalid. Please enter a valid path.");
                                                    }
                                                } while (!d);
                                            } else if (t.getListCourses().get(t.getListCourses().size() - 1)
                                                    .enterQuiz(quizNumber, quizListFileName).contains("manualpool")) {
                                                if (t.getListCourses().get(t.getListCourses().size() - 1)
                                                        .enterQuiz(quizNumber, quizListFileName).contains("MCQ")) {
                                                    String quizFileName = name + "$$" + courseName + "$$"
                                                            + Integer.toString(quizNumber) + ".txt";
                                                    int n = t.getListCourses().size();
                                                    int size = t.getListCourses().get(n - 1).getListQuizzes().size();
                                                    t.getListCourses().get(n - 1).getListQuizzes().get(size - 1)
                                                            .makeQuizManual(quizFileName, "MCQ");
                                                } else if (t.getListCourses().get(t.getListCourses().size() - 1)
                                                        .enterQuiz(quizNumber, quizListFileName)
                                                        .contains("fillintheblanks")) {
                                                    String quizFileName = name + "$$" + courseName + "$$"
                                                            + Integer.toString(quizNumber) + ".txt";
                                                    int n = t.getListCourses().size();
                                                    int size = t.getListCourses().get(n - 1).getListQuizzes().size();
                                                    t.getListCourses().get(n - 1).getListQuizzes().get(size - 1)
                                                            .makeQuizManual(quizFileName, "fill in the blanks");
                                                } else {
                                                    String quizFileName = name + "$$" + courseName + "$$"
                                                            + Integer.toString(quizNumber) + ".txt";
                                                    int n = t.getListCourses().size();
                                                    int size = t.getListCourses().get(n - 1).getListQuizzes().size();
                                                    t.getListCourses().get(n - 1).getListQuizzes().get(size - 1)
                                                            .makeQuizManual(quizFileName, "true/false");
                                                }
                                            } else if (t.getListCourses().get(t.getListCourses().size() - 1)
                                                    .enterQuiz(quizNumber, quizListFileName).contains("manualexact")) {
                                                if (t.getListCourses().get(t.getListCourses().size() - 1)
                                                        .enterQuiz(quizNumber, quizListFileName).contains("MCQ")) {
                                                    String quizFileName = name + "$$" + courseName + "$$"
                                                            + Integer.toString(quizNumber) + ".txt";
                                                    int n = t.getListCourses().size();
                                                    int size = t.getListCourses().get(n - 1).getListQuizzes().size();
                                                    t.getListCourses().get(n - 1).getListQuizzes().get(size - 1)
                                                            .makeQuizManual(quizFileName, "MCQ");
                                                } else if (t.getListCourses().get(t.getListCourses().size() - 1)
                                                        .enterQuiz(quizNumber, quizListFileName)
                                                        .contains("fillintheblanks")) {
                                                    String quizFileName = name + "$$" + courseName + "$$"
                                                            + Integer.toString(quizNumber) + ".txt";
                                                    int n = t.getListCourses().size();
                                                    int size = t.getListCourses().get(n - 1).getListQuizzes().size();
                                                    t.getListCourses().get(n - 1).getListQuizzes().get(size - 1)
                                                            .makeQuizManual(quizFileName, "fill in the blanks");
                                                } else {
                                                    String quizFileName = name + "$$" + courseName + "$$"
                                                            + Integer.toString(quizNumber) + ".txt";
                                                    int n = t.getListCourses().size();
                                                    int size = t.getListCourses().get(n - 1).getListQuizzes().size();
                                                    t.getListCourses().get(n - 1).getListQuizzes().get(size - 1)
                                                            .makeQuizManual(quizFileName, "true/false");
                                                }
                                            } else {
                                                System.out.println("This quiz has not been created yet");
                                            }
                                            jkl = false;
                                            break;
                                        }
                                        case 3: {
                                            System.out.println("Enter the name of the student");
                                            String studentName = sc.nextLine();
                                            System.out.println("Enter the quiz number");
                                            int quizNumber = sc.nextInt();
                                            System.out.println("Enter the attempt number");
                                            int attemptNumber = sc.nextInt();
                                            int choice1;
                                            do {
                                                System.out.println(
                                                        "Do you want to :-\n1. View the grades\n2. view submission");
                                                choice1 = sc.nextInt();
                                                if (choice1 == 1 || choice1 == 2) {
                                                    break;
                                                } else {
                                                    System.out.println("Invalid choice");
                                                }
                                            } while (true);
                                            if (choice1 == 1) {
                                                File ert = new File(studentName + "$$" + name + "$$" + courseName + "$$"
                                                        + Integer.toString(quizNumber) + "$$"
                                                        + Integer.toString(attemptNumber) + "$$gradingReport.txt");
                                                if (ert.exists()) {
                                                    BufferedReader bf = new BufferedReader(new FileReader(ert));
                                                    String printings = bf.readLine();
                                                    while (printings != null) {
                                                        System.out.println(printings);
                                                        printings = bf.readLine();
                                                    }
                                                } else {
                                                    System.out.println(
                                                            "This attempt for this quiz has not been completed yet");
                                                }
                                            } else {
                                                File ert = new File(studentName + "$$" + name + "$$" + courseName + "$$"
                                                        + Integer.toString(quizNumber) + "$$"
                                                        + Integer.toString(attemptNumber) + "$$submission.txt");
                                                if (ert.exists()) {
                                                    BufferedReader bf = new BufferedReader(new FileReader(ert));
                                                    String printings = bf.readLine();
                                                    while (printings != null) {
                                                        System.out.println(printings);
                                                        printings = bf.readLine();
                                                    }
                                                } else {
                                                    System.out.println(
                                                            "This attempt for this quiz has not been completed yet");
                                                }
                                            }
                                            jkl = false;
                                            break;
                                        }
                                        case 4: {
                                            System.out.println("Enter the quiz number to be deleted");
                                            int quizNumber = sc.nextInt();
                                            File gh = new File(name + "$$" + courseName + "$$quizList.txt");
                                            System.out.println(name + "$$" + courseName + "$$quizList.txt");
                                            gh.createNewFile();
                                            BufferedReader bf = new BufferedReader(new FileReader(gh));
                                            String str = bf.readLine();
                                            System.out.println(str);
                                            int c = 0;
                                            StringBuilder appender = new StringBuilder();
                                            while (str != null) {
                                                if (str.contains("Quiz" + Integer.toString(quizNumber))) {
                                                    File fr = new File(
                                                            name + "$$" + courseName + "$$" + quizNumber + ".txt");
                                                    fr.delete();
                                                    File wer = new File(name + "$$" + courseName + "$$" + quizNumber
                                                            + "$$Answers.txt");
                                                    wer.delete();
                                                    c++;
                                                } else {
                                                    appender.append(str).append("\n");
                                                }
                                                str = bf.readLine();
                                            }
                                            if (appender.toString().equals("")) {
                                                PrintWriter pwer = new PrintWriter(new FileWriter(gh), true);
                                                pwer.println("");
                                                pwer.flush();
                                            } else {
                                                appender = new StringBuilder(
                                                        appender.substring(0, appender.length() - 1));
                                                PrintWriter pwer = new PrintWriter(new FileWriter(gh), true);
                                                pwer.println(appender);
                                                pwer.flush();
                                            }
                                            if (c > 0) {
                                                System.out.println("The quiz was deleted successfully.");
                                            } else {
                                                System.out.println("The quiz does not already exist.");
                                            }
                                            jkl = false;
                                            break;
                                        }
                                        case 5: {
                                            jkl = true;
                                            break;
                                        }
                                        default: {
                                            System.out.println("This is not a valid choice!");
                                            jkl = false;
                                            break;
                                        }
                                    }
                                } while (!jkl);
                            } else {
                                System.out.println("This course has not yet been created");
                            }
                            a = false;
                            break;
                        }
                        case 3: {
                            System.out.println("Enter the Course name");
                            String str = sc.nextLine();
                            File fr = new File(name + "$$" + userName + "$$" + password + "$$Courses.txt");
                            fr.createNewFile();
                            BufferedReader rt = new BufferedReader(new FileReader(fr));
                            String ch = rt.readLine();
                            int c = 0;
                            StringBuilder appender = new StringBuilder();
                            while (ch != null) {
                                if (ch.equals(str)) {
                                    c++;
                                    File gh = new File(name + "$$" + ch + "$$quizList.txt");
                                    gh.createNewFile();
                                    BufferedReader bf = new BufferedReader(new FileReader(gh));
                                    String str1 = bf.readLine();
                                    while (str1 != null) {
                                        int quizNumber = Integer.parseInt(str1.substring(4, str1.indexOf("(")));
                                        File fr1 = new File(
                                                name + "$$" + str + "$$" + Integer.toString(quizNumber) + ".txt");
                                        fr1.delete();
                                        File wer = new File(name + "$$" + str + "$$" + Integer.toString(quizNumber)
                                                + "$$Answers.txt");
                                        wer.delete();
                                        str1 = bf.readLine();
                                    }
                                    gh.delete();
                                } else {
                                    appender.append(ch).append("\n");
                                }
                                ch = rt.readLine();
                            }
                            if (appender.toString().equals("")) {
                                PrintWriter pwsd = new PrintWriter(new FileWriter(fr), true);
                                pwsd.println("");
                                pwsd.flush();
                            } else {
                                appender = new StringBuilder(appender.substring(0, appender.length() - 1));
                                PrintWriter pwsd = new PrintWriter(new FileWriter(fr), true);
                                pwsd.println(appender);
                                pwsd.flush();
                            }
                            if (c > 0) {
                                System.out.println("Course deleted successfully");
                            } else {
                                System.out.println("Course was already not present");
                            }
                            a = false;
                            break;
                        }
                        case 4: {
                            a = true;
                            break;
                        }
                        default: {
                            System.out.println("This is not a valid choice!");
                            a = false;
                            break;
                        }
                    }
                } while (!a);
            } else {
                boolean lm = false;
                do {
                    File ft = new File(name + "$$processQuiz.txt");
                    ft.createNewFile();
                    BufferedReader bred = new BufferedReader(new FileReader(ft));
                    String contents = bred.readLine();
                    if (!(contents == null || contents.equals(""))) {
                        int attemptNumber = 0;
                        int startingQuestionNumber = 0;
                        int counteraction = 0;
                        String teacherName = "";
                        String courseName = "";
                        int quizNumber = 0;
                        while (contents != null) {
                            counteraction++;
                            if (counteraction == 1) {
                                int n = contents.lastIndexOf("-", contents.indexOf("qtsf"));
                                int n1 = contents.lastIndexOf("@", contents.indexOf("qtsf"));
                                attemptNumber = Integer.parseInt(contents.substring(n + 1, n1));
                                int n2 = contents.lastIndexOf("-");
                                startingQuestionNumber = Integer.parseInt(contents.substring(n2 + 1));
                                teacherName = contents.substring(0, contents.indexOf("@"));
                                int h = contents.indexOf("@", contents.indexOf("@") + 1);
                                courseName = contents.substring(contents.indexOf("@") + 1, h);
                                int y = contents.indexOf("@", h + 1);
                                quizNumber = Integer.parseInt(contents.substring(h + 4, y));
                            } else {
                                System.out.println(contents);
                            }
                            contents = bred.readLine();
                        }
                        attemptNumber--;
                        File sft = new File(
                                name + "$$" + teacherName + "$$" + courseName + "$$" + Integer.toString(quizNumber)
                                        + "$$" + Integer.toString(attemptNumber + 1) + "$$submission.txt");
                        sft.createNewFile();
                        File studentAnswersOnly = new File(
                                name + "$$" + teacherName + "$$" + courseName + "$$" + Integer.toString(quizNumber)
                                        + "$$" + Integer.toString(attemptNumber + 1) + "$$submittedAnswers.txt");
                        studentAnswersOnly.createNewFile();
                        BufferedReader readStudentAnswersOnly = new BufferedReader(new FileReader(studentAnswersOnly));
                        BufferedReader readsft = new BufferedReader(new FileReader(sft));
                        String temporaries = readsft.readLine();
                        StringBuilder a1 = new StringBuilder();
                        while (temporaries != null) {
                            a1.append(temporaries).append("\n");
                            temporaries = readsft.readLine();
                        }
                        a1 = new StringBuilder(a1.substring(0, a1.length() - 1));
                        PrintWriter pwsft = new PrintWriter(new FileWriter(sft), true);
                        pwsft.println(a1);
                        pwsft.flush();
                        StringBuilder a2 = new StringBuilder();
                        String temporaries1 = readStudentAnswersOnly.readLine();
                        while (temporaries1 != null) {
                            a2.append(temporaries1).append("\n");
                            temporaries1 = readStudentAnswersOnly.readLine();
                        }
                        a2 = new StringBuilder(a2.substring(0, a2.length() - 1));
                        PrintWriter pwStudentAnswersOnly = new PrintWriter(new FileWriter(studentAnswersOnly), true);
                        pwStudentAnswersOnly.println(a2);
                        pwStudentAnswersOnly.flush();
                        File f457 = new File(
                                name + "$$" + teacherName + "$$" + courseName + "$$" + Integer.toString(quizNumber)
                                        + "$$" + Integer.toString(attemptNumber + 1) + "formQuiz.txt");
                        f457.createNewFile();
                        BufferedReader read = new BufferedReader(new FileReader(f457));
                        String quizStarting = read.readLine();
                        int counterAgain = -1;
                        while (quizStarting != null) {
                            counterAgain++;
                            if (counterAgain >= startingQuestionNumber) {
                                System.out.print(quizStarting.replaceAll("@", "\n"));
                                int choic;
                                do {
                                    System.out.println("1. Enter the response\n2. attach a file with the response");
                                    choic = sc.nextInt();
                                    sc.nextLine();
                                    if (choic == 1 || choic == 2) {
                                        break;
                                    } else {
                                        System.out.println("invalid response");
                                    }
                                } while (true);
                                do {
                                    if (choic == 2) {
                                        System.out.println("Enter the file path");
                                        String responseFilePath = sc.nextLine();
                                        File fresponse = new File(responseFilePath);
                                        if (fresponse.length() == 0) {
                                            System.out.println(
                                                    "The file does not exist or is empty. Please enter a valid file path");
                                        } else {
                                            BufferedReader re = new BufferedReader(new FileReader(fresponse));
                                            String answer = re.readLine();
                                            pwStudentAnswersOnly.println(answer);
                                            BufferedReader bread = new BufferedReader(new FileReader(ft));
                                            String readings = bread.readLine();
                                            String appendingString = "";
                                            int cf = 0;
                                            while (readings != null) {
                                                cf++;
                                                if (cf == 1) {
                                                    appendingString += String.format("%s@%s@qn-%d@attemptno-%d@qtsf-%d",
                                                            teacherName, courseName, quizNumber, attemptNumber + 1,
                                                            counterAgain) + "\n";

                                                } else {
                                                    appendingString += readings + "\n";
                                                }
                                                readings = bread.readLine();
                                            }
                                            appendingString += quizStarting.replaceAll("@", "\n");
                                            appendingString += answer + "\n";
                                            PrintWriter pw35 = new PrintWriter(new FileWriter(ft), true);
                                            pw35.println(appendingString);
                                            pwsft.print(quizStarting.replaceAll("@", "\n"));
                                            pwsft.print(answer + "\n");
                                            pwsft.flush();
                                            break;
                                        }
                                    } else {
                                        System.out.println("Enter the answer");
                                        String answer = sc.nextLine();
                                        pwStudentAnswersOnly.println(answer);
                                        BufferedReader bread = new BufferedReader(new FileReader(ft));
                                        String readings = bread.readLine();
                                        String appendingString = "";
                                        int cf = 0;
                                        while (readings != null) {
                                            cf++;
                                            if (cf == 1) {
                                                appendingString += String.format("%s@%s@qn-%d@attemptno-%d@qtsf-%d",
                                                        teacherName, courseName, quizNumber, attemptNumber + 1,
                                                        counterAgain) + "\n";
                                            } else {
                                                appendingString += readings + "\n";
                                            }
                                            readings = bread.readLine();
                                        }
                                        appendingString += quizStarting.replaceAll("@", "\n");
                                        appendingString += answer + "\n";
                                        PrintWriter pw35 = new PrintWriter(new FileWriter(ft), true);
                                        pw35.println(appendingString);
                                        pwsft.print(quizStarting.replaceAll("@", "\n"));
                                        pwsft.print(answer + "\n");
                                        pwsft.flush();
                                        break;
                                    }
                                } while (true);
                            }
                            quizStarting = read.readLine();
                        }
                        PrintWriter bread789 = new PrintWriter(new FileWriter(ft), true);
                        bread789.println("");
                        Date date = new Date();
                        Timestamp ts = new Timestamp(date.getTime());
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        pwsft.println(formatter.format(ts));
                        System.out.println("This quiz is over and is being submitted");
                        File quizGradingReport = new File(
                                name + "$$" + teacherName + "$$" + courseName + "$$" + Integer.toString(quizNumber)
                                        + "$$" + Integer.toString(attemptNumber + 1) + "$$gradingReport.txt");
                        quizGradingReport.createNewFile();
                        BufferedReader brGrading = new BufferedReader(new FileReader(quizGradingReport));
                        PrintWriter pwGrading = new PrintWriter(new FileWriter(quizGradingReport), true);
                        // File rt = new File(studentName + "$$" + teacherName + "$$" + courseName +
                        // "$$" + Integer.toString(quizNumber) + "$$" + Integer.toString(attemptNumber +
                        // 1) + "Solutions.txt") ;
                        File f468 = new File(
                                name + "$$" + teacherName + "$$" + courseName + "$$" + Integer.toString(quizNumber)
                                        + "$$" + Integer.toString(attemptNumber + 1) + "Solutions.txt");
                        f468.createNewFile();
                        BufferedReader brGradingFrom = new BufferedReader(new FileReader(f468));
                        BufferedReader brGraded = new BufferedReader(new FileReader(studentAnswersOnly));
                        String checked = brGraded.readLine();
                        String checker = brGradingFrom.readLine();
                        int d = 0;
                        int totalPoints = 0;
                        while (checked != null) {
                            d++;
                            if (checked.equals(checker.substring(checker.indexOf(" ") + 1))) {
                                pwGrading.println(String.format("Question %d - correct - %d points", d, 2));
                                totalPoints += 2;
                            } else {
                                pwGrading.println(String.format("Question %d - wrong - %d points", d, 0));
                            }
                            checked = brGraded.readLine();
                            checker = brGradingFrom.readLine();
                        }
                        pwStudentAnswersOnly.println(formatter.format(ts));
                        pwGrading.println("total points - " + totalPoints);
                        File f1 = new File(name + "$$" + teacherName + "$$" + courseName + "$$"
                                + Integer.toString(quizNumber) + "$$AttemptTracker.txt");
                        PrintWriter pw901 = new PrintWriter(new FileWriter(f1), true);
                        pw901.println(Integer.toString(attemptNumber + 1));
                        pw901.flush();
                    } else {
                        System.out.println("Choose from the following options.");
                        System.out.println("1. Enter a course");
                        System.out.println("2. Log out");
                        int studentChoice = sc.nextInt();
                        sc.nextLine();
                        switch (studentChoice) {
                            case 1: {
                                System.out.println("Enter the name of the teacher");
                                String teacherName = sc.nextLine();
                                BufferedReader bg = new BufferedReader(new FileReader(new File("teacherList.txt")));
                                String findTeacher = bg.readLine();
                                if (findTeacher == null) {
                                    System.out.println("No teacher is registered yet. Please Log out.");
                                } else {
                                    int c = 0;
                                    while (findTeacher != null) {
                                        if (!findTeacher.equals("null")) {
                                            if (findTeacher.substring(0, findTeacher.indexOf("$")).toUpperCase()
                                                    .equals(teacherName.toUpperCase())) {
                                                c++;
                                            }
                                        }
                                        findTeacher = bg.readLine();
                                    }
                                    if (c == 0) {
                                        System.out.println("This teacher is not in the system");
                                    } else {
                                        System.out.println("Enter the course name");
                                        String courseName = sc.nextLine();
                                        File f56 = new File(teacherName + "$$" + courseName + "$$quizList.txt");
                                        if (!f56.exists()) {
                                            System.out.println("No such course exists for this teacher");
                                        } else {
                                            boolean ki;
                                            do {
                                                System.out.println("Select from the following options");
                                                System.out.println("1. Attempt a quiz");
                                                System.out.println("2. View submission");
                                                System.out.println("3. View grades.");
                                                System.out.println("4. Exit");
                                                int choice57 = sc.nextInt();
                                                sc.nextLine();
                                                switch (choice57) {
                                                    case 1: {
                                                        String quizIdentifier = "";
                                                        System.out.println("Enter the quiz number");
                                                        int choice90 = sc.nextInt();
                                                        sc.nextLine();
                                                        BufferedReader br45 = new BufferedReader(new FileReader(f56));
                                                        int counter = 0;
                                                        String searching = br45.readLine();
                                                        while (searching != null) {
                                                            if (searching
                                                                    .contains("Quiz" + Integer.toString(choice90))) {
                                                                c++;
                                                                quizIdentifier = searching;
                                                            }
                                                            searching = br45.readLine();
                                                        }
                                                        if (c == 0) {
                                                            System.out.println("This quiz does not exist");
                                                        } else {
                                                            BufferedReader br739 = new BufferedReader(
                                                                    (new FileReader(teacherName + "$$" + courseName
                                                                            + "$$" + choice90 + ".txt")));
                                                            String copier = br739.readLine();
                                                            int noOfQuestionsRequired = Integer.parseInt(quizIdentifier
                                                                    .substring(quizIdentifier.indexOf('(') + 1,
                                                                            quizIdentifier.indexOf(')',
                                                                                    quizIdentifier.indexOf('(') + 1)));
                                                            int c34 = -1;
                                                            while (copier != null) {
                                                                c34++;
                                                                copier = br739.readLine();
                                                            }
                                                            if (c34 % 5 == 0) {
                                                                if (quizIdentifier.contains("pool")) {
                                                                    if ((c34 / 5) >= 5) {
                                                                        String s = quizIdentifier
                                                                                .substring(quizIdentifier.indexOf(')',
                                                                                        quizIdentifier.indexOf('(')
                                                                                                + 1));
                                                                        quizIdentifier = quizIdentifier.substring(0,
                                                                                quizIdentifier.indexOf('(') + 1)
                                                                                + Integer.toString(c34 / 5) + s;
                                                                        Student student = new Student();
                                                                        student.takeQuiz(name, teacherName, courseName,
                                                                                choice90, quizIdentifier);
                                                                    } else {
                                                                        System.out.println(
                                                                                "The quiz is yet to be completed by the teacher");
                                                                    }
                                                                } else if (quizIdentifier.contains("exact")) {
                                                                    if ((c34 / 5) >= noOfQuestionsRequired) {
                                                                        String s = quizIdentifier
                                                                                .substring(quizIdentifier.indexOf(')',
                                                                                        quizIdentifier.indexOf('(')
                                                                                                + 1));
                                                                        quizIdentifier = quizIdentifier.substring(0,
                                                                                quizIdentifier.indexOf('(') + 1)
                                                                                + Integer.toString(c34 / 5) + s;
                                                                        Student student = new Student();
                                                                        student.takeQuiz(name, teacherName, courseName,
                                                                                choice90, quizIdentifier);
                                                                    } else {
                                                                        System.out.println(
                                                                                "The quiz is yet to be completed by the teacher");
                                                                    }
                                                                }
                                                            } else {
                                                                System.out.println(
                                                                        "The quiz is yet to be completed by the teacher");
                                                            }
                                                        }
                                                        ki = false;
                                                        break;
                                                    }
                                                    case 2: {
                                                        System.out.println("Enter the quiz number.");
                                                        int quizNumber = sc.nextInt();
                                                        System.out.println("Enter the attempt number.");
                                                        int attemptNumber = sc.nextInt();
                                                        File ert = new File(name + "$$" + teacherName + "$$"
                                                                + courseName + "$$" + Integer.toString(quizNumber)
                                                                + "$$" + Integer.toString(attemptNumber)
                                                                + "$$submission.txt");
                                                        if (ert.exists()) {
                                                            BufferedReader bf = new BufferedReader(new FileReader(ert));
                                                            String printings = bf.readLine();
                                                            while (printings != null) {
                                                                System.out.println(printings);
                                                                printings = bf.readLine();
                                                            }
                                                        } else {
                                                            System.out.println(
                                                                    "This attempt for this quiz has not been completed yet or the quiz has not been attempted at all.");
                                                        }
                                                        ki = false;
                                                        break;
                                                    }
                                                    case 3: {
                                                        System.out.println("Enter the quiz number.");
                                                        int quizNumber = sc.nextInt();
                                                        System.out.println("Enter the attempt number.");
                                                        int attemptNumber = sc.nextInt();
                                                        File ert = new File(name + "$$" + teacherName + "$$"
                                                                + courseName + "$$" + Integer.toString(quizNumber)
                                                                + "$$" + Integer.toString(attemptNumber)
                                                                + "$$gradingReport.txt");
                                                        if (ert.exists()) {
                                                            BufferedReader bf = new BufferedReader(new FileReader(ert));
                                                            String printings = bf.readLine();
                                                            while (printings != null) {
                                                                System.out.println(printings);
                                                                printings = bf.readLine();
                                                            }
                                                        } else {
                                                            System.out.println(
                                                                    "This attempt for this quiz has not been completed yet or the quiz has not been attempted at all.");
                                                        }
                                                        ki = false;
                                                        break;
                                                    }
                                                    case 4: {
                                                        ki = true;
                                                        break;
                                                    }
                                                    default: {
                                                        System.out.println("This is not a valid choice");
                                                        ki = false;
                                                        break;
                                                    }
                                                }
                                            } while (!ki);
                                        }
                                    }
                                }
                                lm = false;
                                break;
                            }
                            case 2: {
                                lm = true;
                                break;
                            }
                            default: {
                                System.out.println("This is not a valid option");
                                lm = false;
                                break;
                            }
                        }
                    }
                } while (!lm);
            }
        } while (true);
    }
}