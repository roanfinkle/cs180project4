import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Quiz class to create Quizzes 
 * Purdue University -- CS18000 -- Spring 2022 -- Project
 * 4
 *
 * @author Ritwik Jain
 * @version April 04, 2022
 * Lab sec 03
 */
public class Quiz {
    private int quizNumber;
    private int numOfQuestions;

    public Quiz(int quizNumber, String quizListFileName, int numOfQuestions,
                String type, String poolOrExact, String fileOrManually,
                boolean randomize) throws IOException {
        this.quizNumber = quizNumber;
        this.numOfQuestions = numOfQuestions;
        File f = new File(quizListFileName);
        BufferedReader br = new BufferedReader(new FileReader(f));
        String str = br.readLine();
        String s = "";
        while (str != null) {
            s += str + "\n";
            str = br.readLine();
        }
        s += "Quiz" + Integer.toString(quizNumber) + "(";
        s += Integer.toString(numOfQuestions) + ")" + "(" + type + ")";
        s += "(" + poolOrExact + ")" + "(" + fileOrManually + ")";
        s += String.format("(%s)", Boolean.toString(randomize)) + "\n";
        PrintWriter pw = new PrintWriter(new FileWriter(f), true);
        pw.print(s);
        pw.flush();
        pw.close();
    }

    public Quiz(int quizNumber) {
        this.quizNumber = quizNumber;
    }

    public boolean makePoolQuizFile(String filePath, String quizFileName,
                                    String answerFilePath) {
        try {
            File f1 = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(f1));
            String str = br.readLine();
            File f7 = new File(answerFilePath);
            BufferedReader br7 = new BufferedReader(new FileReader(f7));
            String str7 = br7.readLine();
            if (str == null || str7 == null) {
                return false;
            } else {
                String s = "";
                while (str != null) {
                    s += str + "\n";
                    str = br.readLine();
                }
                String temp = "";
                while (str7 != null) {
                    temp += str7 + "\n";
                    str7 = br7.readLine();
                }
                File f2 = new File(quizFileName);
                f2.createNewFile();
                File f67 = new File(quizFileName.substring(0, quizFileName.indexOf('.')) +
                        "$$Answers.txt");
                f67.createNewFile();
                PrintWriter pw456 = new PrintWriter(new FileWriter(f67), true);
                pw456.print(temp);
                pw456.close();
                PrintWriter pw = new PrintWriter(new FileWriter(f2), true);
                pw.print(s);
                pw.flush();
                pw.close();
                return true;
            }
        } catch (IOException e) {
            return false;
        }
    }

    public boolean makeExactQuizFile(String filePath, String quizFileName,
                                     String answerFilePath) {
        try {
            File f1 = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(f1));
            String str = br.readLine();
            File f7 = new File(answerFilePath);
            BufferedReader br7 = new BufferedReader(new FileReader(f7));
            String str7 = br7.readLine();
            String returnValue;
            if (str == null || str7 == null) {
                return false;
            } else {
                String s = "";
                while (str != null) {
                    s += str + "\n";
                    str = br.readLine();
                }
                String temp = "";
                while (str7 != null) {
                    temp += str7 + "\n";
                    str7 = br7.readLine();
                }
                File f2 = new File(quizFileName);
                f2.createNewFile();
                File f67 = new File(quizFileName.substring(0, quizFileName.indexOf('.')) +
                        "$$Answers.txt");
                f67.createNewFile();
                PrintWriter pw456 = new PrintWriter(new FileWriter(f67), true);
                pw456.print(temp);
                pw456.close();
                PrintWriter pw = new PrintWriter(new FileWriter(f2), true);
                pw.println(s);
                pw.close();
                return true;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    public void makeQuizManual(String quizFileName,
                               String type) throws IOException {
        File f2 = new File(quizFileName);
        f2.createNewFile();
        File f3 = new File(quizFileName.substring(0, quizFileName.indexOf('.')) +
                "$$Answers.txt");
        f3.createNewFile();
        BufferedReader br = new BufferedReader(new FileReader(f2));
        String str = br.readLine();
        String s = "";
        while (str != null) {
            s += str + "\n";
            str = br.readLine();
        }
        BufferedReader br5 = new BufferedReader(new FileReader(f3));
        String line = br5.readLine();
        String l = "";
        while (line != null) {
            l += line + "\n";
            line = br5.readLine();
        }

        PrintWriter pw3 = new PrintWriter(new FileWriter(f3), true);
        Scanner sc = new Scanner(System.in);
        if (type.equals("MCQ")) {
            if (s.equals("") || s == null) {
                int questionNumber = 0;
                System.out.println("Enter the title of the quiz");
                String title = sc.nextLine();
                s += title + '\n';
                PrintWriter pw = new PrintWriter(new FileWriter(f2), true);
                pw.println(title);
                boolean g;
                do {
                    String option;
                    boolean h;
                    do {
                        System.out.println("Do you want to enter a question statement?\n1. yes\n2. no");
                        option = sc.nextLine();
                        if (!(option.equals("1") || option.equals("2"))) {
                            System.out.println("Enter a valid option");
                            h = false;
                        } else {
                            h = true;
                        }
                    } while (!h);
                    if (option.equals("1")) {
                        System.out.println("Enter the question statement");
                        String qs = sc.nextLine();
                        questionNumber++;
                        String answerChoices;
                        s += Integer.toString(questionNumber) + ". " + qs + '\n';
                        pw.println(Integer.toString(questionNumber) + ". " + qs);
                        for (int cn = 1; cn <= 4; cn++) {
                            if (cn == 1) {
                                System.out.println("Enter the first choice");
                                answerChoices = sc.nextLine();
                                s += "A) " + answerChoices + '\n';
                                PrintWriter pw1 = new PrintWriter(new FileWriter(f2), true);
                                pw1.print(s);
                                pw1.close();
                            } else if (cn == 2) {
                                System.out.println("Enter the second option");
                                answerChoices = sc.nextLine();
                                s += "B) " + answerChoices + '\n';
                                PrintWriter pw1 = new PrintWriter(new FileWriter(f2), true);
                                pw1.print(s);
                                pw1.close();
                            } else if (cn == 3) {
                                System.out.println("Enter the third option");
                                answerChoices = sc.nextLine();
                                s += "C) " + answerChoices + '\n';
                                PrintWriter pw1 = new PrintWriter(new FileWriter(f2), true);
                                pw1.print(s);
                                pw1.close();
                            } else {
                                System.out.println("Enter the fourth option and then the answer to this question");
                                System.out.println("Enter the fourth option");
                                answerChoices = sc.nextLine();
                                System.out.println("Enter the answer as either A, B, C or D. " +
                                        "The option will be added to the quiz only after the the " +
                                        "answer is entered.");
                                String answer;
                                do {
                                    answer = sc.nextLine();
                                    if (answer.equals("A") || answer.equals("B") || answer.equals("C")
                                            || answer.equals("D")) {
                                        break;
                                    } else {
                                        System.out.println("Enter a valid answer.");
                                    }
                                } while (true);
                                s += "D) " + answerChoices + '\n';
                                l += answer.toUpperCase() + "\n";
                                PrintWriter pw1 = new PrintWriter(new FileWriter(f2), true);
                                pw1.print(s);
                                pw1.close();
                                PrintWriter pw45 = new PrintWriter(new FileWriter(f3), true);
                                pw45.print(l);
                                pw45.close();
                            }
                        }
                        g = false;
                    } else {
                        g = true;
                    }
                } while (!g);
            } else {
                boolean df;
                do {
                    System.out.println("Select from the following options.");
                    System.out.println("1. Change the title");
                    System.out.println("2. Continue adding questions and respective options");
                    System.out.println("3. Delete a question");
                    System.out.println("4. Replace a question");
                    System.out.println("5. Exit the quiz without editing");
                    String option6 = sc.nextLine();
                    switch (option6) {
                        case "1": {
                            System.out.println("Enter the new title");
                            String newTitle = sc.nextLine();
                            s = s.substring(s.indexOf('\n'));
                            s = newTitle + s;
                            PrintWriter pw1 = new PrintWriter(new FileWriter(f2), true);
                            pw1.println(s);
                            pw1.close();
                            System.out.println("Title successfully changed.");
                            df = false;
                            break;
                        }
                        case "2": {
                            BufferedReader frgt = new BufferedReader(new FileReader(f2));
                            int numLines = -1;
                            String linesCounter = frgt.readLine();
                            while (linesCounter != null) {
                                numLines++;
                                linesCounter = frgt.readLine();
                            }
                            if (numLines % 5 == 1) {
                                System.out.println("Enter the four options for question number " +
                                        Integer.toString(numLines / 5 + 1));
                                for (int cn = 1; cn <= 4; cn++) {
                                    String answerChoices;
                                    if (cn == 1) {
                                        System.out.println("Enter the first choice");
                                        answerChoices = sc.nextLine();
                                        s += "A) " + answerChoices + '\n';
                                        PrintWriter pw1 = new PrintWriter(new FileWriter(f2), true);
                                        pw1.print(s);
                                        pw1.close();
                                    } else if (cn == 2) {
                                        System.out.println("Enter the second option");
                                        answerChoices = sc.nextLine();
                                        s += "B) " + answerChoices + '\n';
                                        PrintWriter pw1 = new PrintWriter(new FileWriter(f2), true);
                                        pw1.print(s);
                                        pw1.close();
                                    } else if (cn == 3) {
                                        System.out.println("Enter the third option");
                                        answerChoices = sc.nextLine();
                                        s += "C) " + answerChoices + '\n';
                                        PrintWriter pw1 = new PrintWriter(new FileWriter(f2), true);
                                        pw1.print(s);
                                        pw1.close();
                                    } else {
                                        System.out.println("Enter the fourth option and then the " +
                                                "answer to this question");
                                        System.out.println("Enter the fourth option");
                                        answerChoices = sc.nextLine();
                                        System.out.println("Enter the answer. The option will be added to the quiz " +
                                                "only after the the answer is entered.");
                                        String answer;
                                        do {
                                            answer = sc.nextLine();
                                            if (answer.equals("A") || answer.equals("B") || answer.equals("C")
                                                    || answer.equals("D")) {
                                                break;
                                            } else {
                                                System.out.println("Enter a valid answer.");
                                            }
                                        } while (true);
                                        s += "D) " + answerChoices + '\n';
                                        l += answer.toUpperCase() + "\n";
                                        PrintWriter pw1 = new PrintWriter(new FileWriter(f2), true);
                                        pw1.print(s);
                                        pw1.close();
                                        PrintWriter pw45 = new PrintWriter(new FileWriter(f3), true);
                                        pw45.print(l);
                                        pw45.close();
                                    }
                                }
                            } else if (numLines % 5 == 0) {
                                System.out.println("Enter the question and four options for question number " +
                                        Integer.toString(numLines / 5 + 1));
                                System.out.println("Enter the question statement");
                                String qs = sc.nextLine();
                                s += Integer.toString(numLines / 5 + 1) + ". " + qs + '\n';
                                PrintWriter pw = new PrintWriter(new FileWriter(f2), true);
                                pw.println(Integer.toString(numLines / 5 + 1) + ". " + qs);
                                for (int cn = 1; cn <= 4; cn++) {
                                    String answerChoices;
                                    if (cn == 1) {
                                        System.out.println("Enter the first choice");
                                        answerChoices = sc.nextLine();
                                        s += "A) " + answerChoices + '\n';
                                        PrintWriter pw1 = new PrintWriter(new FileWriter(f2), true);
                                        pw1.print(s);
                                        pw1.close();
                                    } else if (cn == 2) {
                                        System.out.println("Enter the second option");
                                        answerChoices = sc.nextLine();
                                        s += "B) " + answerChoices + '\n';
                                        PrintWriter pw1 = new PrintWriter(new FileWriter(f2), true);
                                        pw1.print(s);
                                        pw1.close();
                                    } else if (cn == 3) {
                                        System.out.println("Enter the third option");
                                        answerChoices = sc.nextLine();
                                        s += "C) " + answerChoices + '\n';
                                        PrintWriter pw1 = new PrintWriter(new FileWriter(f2), true);
                                        pw1.print(s);
                                        pw1.close();
                                    } else {
                                        System.out.println("Enter the fourth option and then the " +
                                                "answer to this question");
                                        System.out.println("Enter the fourth option");
                                        answerChoices = sc.nextLine();
                                        System.out.println("Enter the answer. The option will be added to " +
                                                "the quiz only after the the answer is entered.");
                                        String answer;
                                        do {
                                            answer = sc.nextLine();
                                            if (answer.equals("A") || answer.equals("B") || answer.equals("C")
                                                    || answer.equals("D")) {
                                                break;
                                            } else {
                                                System.out.println("Enter a valid answer.");
                                            }
                                        } while (true);
                                        s += "D) " + answerChoices + '\n';
                                        l += answer.toUpperCase() + "\n";
                                        PrintWriter pw1 = new PrintWriter(new FileWriter(f2), true);
                                        pw1.print(s);
                                        pw1.close();
                                        PrintWriter pw45 = new PrintWriter(new FileWriter(f3), true);
                                        pw45.print(l);
                                        pw45.close();
                                    }
                                }
                            } else {
                                System.out.println("Enter the options for question number " +
                                        Integer.toString(numLines / 5 + 1));
                                for (int cn = numLines % 5; cn <= 4; cn++) {
                                    String answerChoices;
                                    if (cn == 1) {
                                        System.out.println("Enter the first choice");
                                        answerChoices = sc.nextLine();
                                        s += "A) " + answerChoices + '\n';
                                        PrintWriter pw1 = new PrintWriter(new FileWriter(f2), true);
                                        pw1.print(s);
                                        pw1.close();
                                    } else if (cn == 2) {
                                        System.out.println("Enter the second option");
                                        answerChoices = sc.nextLine();
                                        s += "B) " + answerChoices + '\n';
                                        PrintWriter pw1 = new PrintWriter(new FileWriter(f2), true);
                                        pw1.print(s);
                                        pw1.close();
                                    } else if (cn == 3) {
                                        System.out.println("Enter the third option");
                                        answerChoices = sc.nextLine();
                                        s += "C) " + answerChoices + '\n';
                                        PrintWriter pw1 = new PrintWriter(new FileWriter(f2), true);
                                        pw1.print(s);
                                        pw1.close();
                                    } else {
                                        System.out.println("Enter the fourth option and then the answer to " +
                                                "this question");
                                        System.out.println("Enter the fourth option");
                                        answerChoices = sc.nextLine();
                                        System.out.println("Enter the answer. The option will be added to the " +
                                                "quiz only after the the answer is entered.");
                                        String answer;
                                        do {
                                            answer = sc.nextLine();
                                            if (answer.equals("A") || answer.equals("B") || answer.equals("C")
                                                    || answer.equals("D")) {
                                                break;
                                            } else {
                                                System.out.println("Enter a valid answer.");
                                            }
                                        } while (true);
                                        s += "D) " + answerChoices + '\n';
                                        l += answer.toUpperCase() + "\n";
                                        PrintWriter pw1 = new PrintWriter(new FileWriter(f2), true);
                                        pw1.print(s);
                                        pw1.close();
                                        PrintWriter pw45 = new PrintWriter(new FileWriter(f3), true);
                                        pw45.print(l);
                                        pw45.close();
                                    }
                                }
                            }
                            df = false;
                            break;
                        }
                        case "3": {
                            BufferedReader bf = new BufferedReader(new FileReader(quizFileName));
                            String quizLines = bf.readLine();
                            while (quizLines != null) {
                                System.out.println(quizLines);
                                quizLines = bf.readLine();
                            }
                            bf.close();
                            int dqn;
                            do {
                                System.out.println("Enter the question number to be deleted.");
                                try {
                                    dqn = sc.nextInt();
                                    sc.nextLine();
                                    break;
                                } catch (InputMismatchException e) {
                                    System.out.println("Enter an integer.");
                                    sc.nextLine();
                                }
                            } while (true);
                            String str1 = String.format("%d. ", dqn);
                            String str2 = String.format("%d. ", dqn + 1);
                            if (!s.contains(str1)) {
                                System.out.println("This question is not yet added to the quiz.");
                            } else {
                                if (!s.contains(str2)) {
                                    s = s.substring(0, s.indexOf(str1));
                                    PrintWriter pw1 = new PrintWriter(new FileWriter(f2), true);
                                    pw1.print(s);
                                    pw1.close();
                                } else {
                                    s = s.substring(0, s.indexOf(str1)) + s.substring(s.indexOf(str2));
                                    String s1 = "";
                                    String s2 = "";
                                    PrintWriter pw1 = new PrintWriter(new FileWriter(f2), true);
                                    pw1.print(s);
                                    pw1.flush();
                                    pw1.close();
                                    BufferedReader vf = new BufferedReader(new FileReader(f2));
                                    s1 = vf.readLine();
                                    int ct = -1;
                                    while (s1 != null) {
                                        ct++;
                                        if (ct % 5 == 1) {
                                            int bn = Integer.parseInt(s1.substring(0, s1.indexOf(".")));
                                            if (bn >= dqn) {
                                                s2 += Integer.toString(bn - 1) + s1.substring(s1.indexOf(".")) + "\n";
                                            } else {
                                                s2 += s1 + "\n";
                                            }
                                        } else {
                                            s2 += s1 + "\n";
                                        }
                                        s1 = vf.readLine();
                                    }
                                    PrintWriter pw3456 = new PrintWriter(new FileWriter(f2), true);
                                    pw3456.print(s2);
                                    pw3456.flush();
                                    pw3456.close();
                                }
                                int c = 0;
                                for (int i = 0; i < l.length(); i++) {
                                    if (l.charAt(i) == 'A' || l.charAt(i) == 'B' || l.charAt(i) == 'C'
                                            || l.charAt(i) == 'D') {
                                        c++;
                                    }
                                    if (c == dqn) {
                                        if (i == l.length() - 2) {
                                            l = l.substring(0, i);
                                        } else {
                                            String pg = l.substring(i + 2);
                                            l = l.substring(0, i) + pg;
                                        }
                                        break;
                                    }
                                }
                                PrintWriter pw45 = new PrintWriter(new FileWriter(f3), true);
                                pw45.print(l);
                                pw45.close();
                                System.out.println("Question deleted successfully");
                            }
                            df = false;
                            break;
                        }
                        case "4": {
                            BufferedReader bf = new BufferedReader(new FileReader(quizFileName));
                            String quizLines = bf.readLine();
                            while (quizLines != null) {
                                System.out.println(quizLines);
                                quizLines = bf.readLine();
                            }
                            bf.close();
                            int rqn;
                            do {
                                System.out.println("Enter the question number of the question to be replaced");
                                try {
                                    rqn = sc.nextInt();
                                    sc.nextLine();
                                    break;
                                } catch (InputMismatchException e) {
                                    System.out.println("Enter an integer.");
                                    sc.nextLine();
                                }
                            } while (true);
                            String str1 = String.format("%d. ", rqn);
                            String str2 = String.format("%d. ", rqn + 1);
                            if (!(s.contains(str1))) {
                                System.out.println("This question is not yet added to the quiz.");
                            } else {
                                System.out.println("Enter the new question statement.");
                                String nqs = sc.nextLine();
                                System.out.println("Enter the first option");
                                String firstOption = sc.nextLine();
                                firstOption = "A) " + firstOption + "\n";
                                System.out.println("Enter the second option");
                                String secondOption = sc.nextLine();
                                secondOption = "B) " + secondOption + "\n";
                                System.out.println("Enter the third option");
                                String thirdOption = sc.nextLine();
                                thirdOption = "C) " + thirdOption + "\n";
                                System.out.println("Enter the fourth option and then the answer to this question");
                                System.out.println("Enter the fourth option");
                                String fourthOption = sc.nextLine();
                                System.out.println("Enter the answer. The option will be added to the quiz only " +
                                        "after the the answer is entered.");
                                String answer;
                                do {
                                    answer = sc.nextLine();
                                    if (answer.equals("A") || answer.equals("B") || answer.equals("C")
                                            || answer.equals("D")) {
                                        break;
                                    } else {
                                        System.out.println("Enter a valid answer.");
                                    }
                                } while (true);
                                fourthOption = "D) " + fourthOption + "\n";
                                String str3 = str1 + nqs + "\n" + firstOption + secondOption +
                                        thirdOption + fourthOption;
                                if (!s.contains(str2)) {
                                    s = s.substring(0, s.indexOf(str1));
                                    s += str3;
                                    PrintWriter pw1 = new PrintWriter(new FileWriter(f2), true);
                                    pw1.print(s);
                                    pw1.close();
                                } else {
                                    String local = s.substring(s.indexOf(str2));
                                    s = s.substring(0, s.indexOf(str1));
                                    s += str3;
                                    s += local;
                                    PrintWriter pw1 = new PrintWriter(new FileWriter(f2), true);
                                    pw1.print(s);
                                    pw1.close();
                                }
                                int c = 0;
                                for (int i = 0; i < l.length(); i++) {
                                    if (l.charAt(i) == 'A' || l.charAt(i) == 'B' || l.charAt(i) == 'C'
                                            || l.charAt(i) == 'D') {
                                        c++;
                                    }
                                    if (c == rqn) {
                                        if (i == l.length() - 2) {
                                            l = l.substring(0, i) + answer + "\n";
                                        } else {
                                            String pg = l.substring(i + 2);
                                            l = l.substring(0, i) + answer + "\n" + pg;
                                        }
                                        break;
                                    }
                                }
                                PrintWriter pw45 = new PrintWriter(new FileWriter(f3), true);
                                pw45.print(l);
                                pw45.close();
                                System.out.println("Question replaced successfully");
                            }
                            df = false;
                            break;
                        }
                        case "5": {
                            df = true;
                            break;
                        }
                        default: {
                            System.out.println("This is not a valid choice");
                            df = false;
                            break;
                        }
                    }
                } while (!df);
            }
        }
    }
}

