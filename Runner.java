import java.io.*;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Student or teacher?");
        String category = sc.nextLine();
        System.out.println("name");
        String name = sc.nextLine();
        System.out.println("username");
        String userName = sc.nextLine();
        System.out.println("password");
        String password = sc.nextLine();
        if (category.toUpperCase().equals("TEACHER")) {
            Teacher t = null;
            t = new Teacher(userName, password, name, "teacherList.txt");
            String courseListFileName = name + "$$" + userName + "$$" + password;
            courseListFileName += "$$Courses.txt";
            File f = new File(courseListFileName);
            f.createNewFile();
            boolean a;
            do {
                System.out.println("Choose from the following options.");
                System.out.println("1. Create a new course");
                System.out.println("2. Enter and edit a course");
                System.out.println("3. Delete an existing course");
                System.out.println("4. Log out");
                int teacherChoice = sc.nextInt();
                sc.nextLine();
                switch (teacherChoice) {
                    case 1: {
                        System.out.println("Enter the course name");
                        String courseName = sc.nextLine();
                        try {
                            if (t.createCourse(courseName, courseListFileName)) {
                                File f1 = new File(name + "$$" + courseName + "$$quizList.txt");
                                f1.createNewFile();
                                System.out.println("course successfully created");
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
                        System.out.println("Enter the course name");
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
                            System.out.println("you are now into the specified course");
                            boolean jkl = false;
                            do {
                                System.out.println("Choose from the following options.");
                                System.out.println("1. Create a new quiz");
                                System.out.println("2. Enter and edit an existing quiz");
                                System.out.println("3. View student submissions for a quiz");
                                System.out.println("4. Delete an existing quiz");
                                System.out.println("5. Exit to Courses");
                                int choiceAgain = sc.nextInt();
                                sc.nextLine();
                                switch (choiceAgain) {
                                    case 1: {
                                        String quizListFileName = name + "$$" + courseName + "$$quizList.txt";
                                        System.out.println("Enter the quiz number");
                                        int quizNumber = sc.nextInt();
                                        sc.nextLine();
                                        System.out.println("Enter the number of questions in the quiz");
                                        int numOfQuestions = sc.nextInt();
                                        sc.nextLine();
                                        boolean b;
                                        int choice2;
                                        String type = "";
                                        do {
                                            System.out.println("Do you want to add MCQs, fill im the blanks or true/false?");
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
                                            System.out.print("Do you want to add a pool of questions to randomly choose from or ");
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
                                            tyu = t.getListCourses().get(t.getListCourses().size() - 1).createQuiz(quizNumber, quizListFileName, numOfQuestions, type, poolOrExact, fileOrManually, randomize);
                                        } catch (FileNotFoundException e) {
                                            e.printStackTrace();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        if (tyu) {
                                            if (choice90 == 1 && choice89 == 1) {
                                                boolean d;
                                                do {
                                                    System.out.println("Enter the file path");
                                                    String filePath = sc.nextLine();
                                                    int n = t.getListCourses().size();
                                                    int size = t.getListCourses().get(n - 1).getListQuizzes().size();
                                                    String quizFileName = name + "$$" + courseName + "$$" + Integer.toString(quizNumber) + ".txt";
                                                    d = t.getListCourses().get(n - 1).getListQuizzes().get(size - 1).makePoolQuizFile(filePath, quizFileName);
                                                    if (d) {
                                                        System.out.println("The quiz was made successfully");
                                                    } else {
                                                        System.out.println("Either the file was empty or the path was invalid. Please enter a valid path.");
                                                    }
                                                } while (!d);
                                            } else if (choice90 == 2 && choice89 == 1) {
                                                boolean d;
                                                do {
                                                    System.out.println("Enter the file path");
                                                    String filePath = sc.nextLine();
                                                    int n = t.getListCourses().size();
                                                    int size = t.getListCourses().get(n - 1).getListQuizzes().size();
                                                    String quizFileName = name + "$$" + courseName + "$$" + Integer.toString(quizNumber) + ".txt";
                                                    d = t.getListCourses().get(n - 1).getListQuizzes().get(size - 1).makeExactQuizFile(filePath, quizFileName);
                                                    if (d) {
                                                        System.out.println("The quiz was made successfully");
                                                    } else {
                                                        System.out.println("Either the file was empty or the path was invalid. Please enter a valid path.");
                                                    }
                                                } while (!d);
                                            } else {
                                                String quizFileName = name + "$$" + courseName + "$$" + Integer.toString(quizNumber) + ".txt";
                                                int n = t.getListCourses().size();
                                                int size = t.getListCourses().get(n - 1).getListQuizzes().size();
                                                t.getListCourses().get(n - 1).getListQuizzes().get(size - 1).makeQuizManual(quizFileName, type);
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
                                        if (t.getListCourses().get(t.getListCourses().size() - 1).enterQuiz(quizNumber, quizListFileName).equals("filepool")) {
                                            boolean d;
                                            do {
                                                System.out.println("Enter the file path");
                                                String filePath = sc.nextLine();
                                                int n = t.getListCourses().size();
                                                int size = t.getListCourses().get(n - 1).getListQuizzes().size();
                                                String quizFileName = name + "$$" + courseName + "$$" + Integer.toString(quizNumber) + ".txt";
                                                d = t.getListCourses().get(n - 1).getListQuizzes().get(size - 1).makePoolQuizFile(filePath, quizFileName);
                                                if (d) {
                                                    System.out.println("The quiz was made successfully");
                                                } else {
                                                    System.out.println("Either the file was empty or the path was invalid. Please enter a valid path.");
                                                }
                                            } while (!d);
                                        } else if (t.getListCourses().get(t.getListCourses().size() - 1).enterQuiz(quizNumber, quizListFileName).equals("fileexact")) {
                                            boolean d;
                                            do {
                                                System.out.println("Enter the file path");
                                                String filePath = sc.nextLine();
                                                int n = t.getListCourses().size();
                                                int size = t.getListCourses().get(n - 1).getListQuizzes().size();
                                                String quizFileName = name + "$$" + courseName + "$$" + Integer.toString(quizNumber) + ".txt";
                                                d = t.getListCourses().get(n - 1).getListQuizzes().get(size - 1).makeExactQuizFile(filePath, quizFileName);
                                                if (d) {
                                                    System.out.println("The quiz was made successfully");
                                                } else {
                                                    System.out.println("Either the file was empty or the path was invalid. Please enter a valid path.");
                                                }
                                            } while (!d);
                                        } else if (t.getListCourses().get(t.getListCourses().size() - 1).enterQuiz(quizNumber, quizListFileName).contains("manualpool")) {
                                            if (t.getListCourses().get(t.getListCourses().size() - 1).enterQuiz(quizNumber, quizListFileName).contains("MCQ")) {
                                                String quizFileName = name + "$$" + courseName + "$$" + Integer.toString(quizNumber) + ".txt";
                                                int n = t.getListCourses().size();
                                                int size = t.getListCourses().get(n - 1).getListQuizzes().size();
                                                t.getListCourses().get(n - 1).getListQuizzes().get(size - 1).makeQuizManual(quizFileName, "MCQ");
                                            } else if (t.getListCourses().get(t.getListCourses().size() - 1).enterQuiz(quizNumber, quizListFileName).contains("fillintheblanks")) {
                                                String quizFileName = name + "$$" + courseName + "$$" + Integer.toString(quizNumber) + ".txt";
                                                int n = t.getListCourses().size();
                                                int size = t.getListCourses().get(n - 1).getListQuizzes().size();
                                                t.getListCourses().get(n - 1).getListQuizzes().get(size - 1).makeQuizManual(quizFileName, "fill in the blanks");
                                            } else {
                                                String quizFileName = name + "$$" + courseName + "$$" + Integer.toString(quizNumber) + ".txt";
                                                int n = t.getListCourses().size();
                                                int size = t.getListCourses().get(n - 1).getListQuizzes().size();
                                                t.getListCourses().get(n - 1).getListQuizzes().get(size - 1).makeQuizManual(quizFileName, "true/false");
                                            }
                                        } else if (t.getListCourses().get(t.getListCourses().size() - 1).enterQuiz(quizNumber, quizListFileName).contains("manualexact")) {
                                            if (t.getListCourses().get(t.getListCourses().size() - 1).enterQuiz(quizNumber, quizListFileName).contains("MCQ")) {
                                                String quizFileName = name + "$$" + courseName + "$$" + Integer.toString(quizNumber) + ".txt";
                                                int n = t.getListCourses().size();
                                                int size = t.getListCourses().get(n - 1).getListQuizzes().size();
                                                t.getListCourses().get(n - 1).getListQuizzes().get(size - 1).makeQuizManual(quizFileName, "MCQ");
                                            } else if (t.getListCourses().get(t.getListCourses().size() - 1).enterQuiz(quizNumber, quizListFileName).contains("fillintheblanks")) {
                                                String quizFileName = name + "$$" + courseName + "$$" + Integer.toString(quizNumber) + ".txt";
                                                int n = t.getListCourses().size();
                                                int size = t.getListCourses().get(n - 1).getListQuizzes().size();
                                                t.getListCourses().get(n - 1).getListQuizzes().get(size - 1).makeQuizManual(quizFileName, "fill in the blanks");
                                            } else {
                                                String quizFileName = name + "$$" + courseName + "$$" + Integer.toString(quizNumber) + ".txt";
                                                int n = t.getListCourses().size();
                                                int size = t.getListCourses().get(n - 1).getListQuizzes().size();
                                                t.getListCourses().get(n - 1).getListQuizzes().get(size - 1).makeQuizManual(quizFileName, "true/false");
                                            }
                                        } else {
                                            System.out.println("This quiz has not been created yet");
                                        }
                                        jkl = false;
                                        break;
                                    }
                                    case 3: {
                                        jkl = false;
                                        break;
                                    }
                                    case 4: {
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
        }
    }
}
