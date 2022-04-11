import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Student class to let the student attempt 
 * the quiz created in a particular course
 * Purdue University -- CS18000 -- Spring 2022 -- Project
 * 4
 *
 * @author Ritwik Jain
 * @version April 04, 2022
 * Lab sec 03
 */

public class Student {
    public void takeQuiz(String studentName, String teacherName,
                         String courseName, int quizNumber,
                         String quizIdentifier) throws IOException {
        ArrayList<String> arr = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        File f1 = new File(studentName + "$$" + teacherName + "$$" +
                courseName + "$$" + Integer.toString(quizNumber) + "$$AttemptTracker.txt");
        f1.createNewFile();
        BufferedReader br29 = new BufferedReader(new FileReader(f1));
        String gl = br29.readLine();
        if (gl == null) {
            PrintWriter pw90 = new PrintWriter(new FileWriter(f1), true);
            pw90.println("0");
        }
        BufferedReader br92 = new BufferedReader(new FileReader(f1));
        int attemptNumber = Integer.parseInt(br92.readLine());
        int noOfQuestions = Integer.parseInt(quizIdentifier.substring(quizIdentifier.indexOf('(') + 1,
                quizIdentifier.indexOf(')', quizIdentifier.indexOf('(') + 1)));
        String[][] questionArray = new String[noOfQuestions][6];
        String[][] questionArray1 = new String[noOfQuestions][6];
        String[][] questionArray2 = new String[noOfQuestions][6];
        ArrayList<Integer> optionRandomize = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            optionRandomize.add(i);
        }
        int[] optionOrder = new int[4];
        for (int i = 1; i <= 4; i++) {
            int h = (int) (((Math.random() * ((4 - i + 1) - 1 + 1) + 1)) - 1);
            optionOrder[i - 1] = optionRandomize.get(h);
            optionRandomize.remove(h);
        }
        BufferedReader br56 = new BufferedReader((new FileReader(teacherName +
                "$$" + courseName + "$$" + quizNumber + ".txt")));
        String copier = br56.readLine();
        BufferedReader br57 = new BufferedReader((new FileReader(teacherName +
                "$$" + courseName + "$$" + quizNumber + "$$Answers.txt")));
        String answerCopier;
        String quizTitle = "";
        if (copier == null) {
            System.out.println("This quiz is currently empty. Cannot be attempted");
        } else {
            int counter = -1;
            while (copier != null) {
                if (!(copier.equals("") || copier.equals(" "))) {
                    counter++;
                    if (!(counter == 0)) {
                        if (counter % 6 != 0) {
                            String addition = copier.substring(copier.indexOf(" ") + 1);
                            questionArray[counter / 6][(counter % 6) - 1] = addition;
                        } else {
                            answerCopier = br57.readLine();
                            questionArray[(counter / 6) - 1][5] = answerCopier;
                        }
                    } else {
                        quizTitle = copier;
                    }
                }
                if (!(counter % 6 == 5)) {
                    copier = br56.readLine();
                }
            }

            for (int k = 0; k < noOfQuestions; k++) {
                for (int g = 0; g < 6; g++) {
                    if (g == 0) {
                        questionArray1[k][g] = questionArray[k][g];
                    } else {
                        if (g == 5) {
                            int no;
                            if (questionArray[k][g].equals("A")) {
                                no = 1;
                            } else if (questionArray[k][g].equals("B")) {
                                no = 2;
                            } else if (questionArray[k][g].equals("C")) {
                                no = 3;
                            } else {
                                no = 4;
                            }
                            int newIndex = 0;
                            for (int l = 0; l < 4; l++) {
                                if (optionOrder[l] == no) {
                                    newIndex = l + 1;
                                }
                            }
                            if (newIndex == 1) {
                                questionArray1[k][g] = "A";
                            } else if (newIndex == 2) {
                                questionArray1[k][g] = "B";
                            } else if (newIndex == 3) {
                                questionArray1[k][g] = "C";
                            } else {
                                questionArray1[k][g] = "D";
                            }
                        } else {
                            questionArray1[k][g] = questionArray[k][optionOrder[g - 1]];
                        }
                    }
                }
            }
            if (quizIdentifier.contains("MCQ")) {
                if (quizIdentifier.contains("true")) {
                    if (quizIdentifier.contains("pool")) {
                        File f457 = new File(studentName + "$$" + teacherName + "$$" +
                                courseName + "$$" + Integer.toString(quizNumber) + "$$" +
                                Integer.toString(attemptNumber + 1) + "formQuiz.txt");
                        f457.createNewFile();
                        ArrayList<Integer> questionRandomize = new ArrayList<>();
                        for (int i = 1; i <= noOfQuestions; i++) {
                            questionRandomize.add(i);
                        }
                        int[] questionOrder = new int[5];
                        for (int i = 1; i <= 5; i++) {
                            int h = (int) (((Math.random() * ((noOfQuestions - i + 1) - 1 + 1) + 1)) - 1);
                            questionOrder[i - 1] = questionRandomize.get(h);
                            questionRandomize.remove(h);
                        }
                        BufferedReader read = new BufferedReader(new FileReader(f457));
                        PrintWriter pw98 = new PrintWriter(new FileWriter(f457), true);
                        pw98.println(quizTitle);
                        File f468 = new File(studentName + "$$" + teacherName + "$$" +
                                courseName + "$$" + Integer.toString(quizNumber) + "$$" +
                                Integer.toString(attemptNumber + 1) + "Solutions.txt");
                        f468.createNewFile();
                        BufferedReader read468 = new BufferedReader(new FileReader(f468));
                        PrintWriter pw468 = new PrintWriter(new FileWriter(f468), true);
                        for (int y = 0; y < questionOrder.length; y++) {
                            String inLine = "";
                            for (int lp = 0; lp < 6; lp++) {
                                if (lp % 6 == 0) {
                                    inLine += Integer.toString(y + 1) + ". " +
                                            questionArray1[questionOrder[y] - 1][0] + "@";
                                } else if (lp % 6 == 5) {
                                    pw468.println(Integer.toString(y + 1) + ". " +
                                            questionArray1[questionOrder[y] - 1][5]);
                                } else {
                                    if (lp == 1) {
                                        inLine += "A) " + questionArray1[questionOrder[y] - 1][lp] + "@";
                                    } else if (lp == 2) {
                                        inLine += "B) " + questionArray1[questionOrder[y] - 1][lp] + "@";
                                    } else if (lp == 3) {
                                        inLine += "C) " + questionArray1[questionOrder[y] - 1][lp] + "@";
                                    } else {
                                        inLine += "D) " + questionArray1[questionOrder[y] - 1][lp] + "@";
                                    }
                                }
                            }
                            pw98.println(inLine);
                        }
                        File ft = new File(studentName + "$$processQuiz.txt");
                        ft.createNewFile();
                        BufferedReader readft = new BufferedReader(new FileReader(ft));
                        PrintWriter pwft = new PrintWriter(new FileWriter(ft), true);
                        pwft.println(String.format("%s@%s@qn-%d@attemptno-%d@qtsf-%d", teacherName,
                                courseName, quizNumber, attemptNumber + 1, 1));
                        pwft.println(quizTitle);
                        File sft = new File(studentName + "$$" + teacherName + "$$" +
                                courseName + "$$" + Integer.toString(quizNumber) + "$$" +
                                Integer.toString(attemptNumber + 1) + "$$submission.txt");
                        sft.createNewFile();
                        File studentAnswersOnly = new File(studentName + "$$" +
                                teacherName + "$$" + courseName + "$$" + Integer.toString(quizNumber) +
                                "$$" + Integer.toString(attemptNumber + 1) + "$$submittedAnswers.txt");
                        studentAnswersOnly.createNewFile();
                        BufferedReader readStudentAnswersOnly = new BufferedReader(new FileReader(studentAnswersOnly));
                        PrintWriter pwStudentAnswersOnly = new PrintWriter(new FileWriter(studentAnswersOnly),
                                true);
                        BufferedReader readsft = new BufferedReader(new FileReader(sft));
                        PrintWriter pwsft = new PrintWriter(new FileWriter(sft), true);
                        pwsft.println(quizTitle);
                        String quizStarting = read.readLine();
                        int counterAgain = 0;
                        while (quizStarting != null) {
                            counterAgain++;
                            if (counterAgain == 1) {
                                System.out.println(quizStarting);
                            } else {
                                System.out.print(quizStarting.replaceAll("@", "\n"));
                                String choic;
                                do {
                                    System.out.println("1. Enter the response\n2. attach a file with the response");
                                    choic = sc.nextLine();
                                    if (choic.equals("1") || choic.equals("2")) {
                                        break;
                                    } else {
                                        System.out.println("invalid response");
                                    }
                                } while (true);
                                do {
                                    if (choic.equals("2")) {
                                        System.out.println("Enter the file path");
                                        String responseFilePath = sc.nextLine();
                                        File fresponse = new File(responseFilePath);
                                        if (fresponse.length() == 0) {
                                            System.out.println("The file does not exist or is empty. " +
                                                    "Please enter a valid file path");
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
                                                    appendingString += String.format("%s@%s@qn-%d@attemptno-%d@qtsf-%d", teacherName,
                                                            courseName, quizNumber, attemptNumber + 1, counterAgain) + "\n";

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
                                        String answer;
                                        do {
                                            System.out.println("Enter the answer");
                                            answer = sc.nextLine();
                                            if (answer.equals("A") || answer.equals("B") || answer.equals("C") || answer.equals("D")) {
                                                break;
                                            } else {
                                                System.out.println("The answer should be either A, B, C or D");
                                            }
                                        } while (true);
                                        pwStudentAnswersOnly.println(answer);
                                        BufferedReader bread = new BufferedReader(new FileReader(ft));
                                        String readings = bread.readLine();
                                        String appendingString = "";
                                        int cf = 0;
                                        while (readings != null) {
                                            cf++;
                                            if (cf == 1) {
                                                appendingString += String.format("%s@%s@qn-%d@attemptno-%d@qtsf-%d", teacherName,
                                                        courseName, quizNumber, attemptNumber + 1, counterAgain) + "\n";
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
                        File quizGradingReport = new File(studentName + "$$" + teacherName + "$$" +
                                courseName + "$$" + Integer.toString(quizNumber) + "$$" +
                                Integer.toString(attemptNumber + 1) + "$$gradingReport.txt");
                        quizGradingReport.createNewFile();
                        BufferedReader brGrading = new BufferedReader(new FileReader(quizGradingReport));
                        PrintWriter pwGrading = new PrintWriter(new FileWriter(quizGradingReport), true);
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
                        PrintWriter pw901 = new PrintWriter(new FileWriter(f1), true);
                        pw901.println(Integer.toString(attemptNumber + 1));
                        pw901.flush();
                    } else {
                        File f457 = new File(studentName + "$$" + teacherName + "$$" +
                                courseName + "$$" + Integer.toString(quizNumber) + "$$" +
                                Integer.toString(attemptNumber + 1) + "formQuiz.txt");
                        f457.createNewFile();
                        ArrayList<Integer> questionRandomize = new ArrayList<>();
                        for (int i = 1; i <= noOfQuestions; i++) {
                            questionRandomize.add(i);
                        }
                        int[] questionOrder = new int[noOfQuestions];
                        for (int i = 1; i <= noOfQuestions; i++) {
                            int h = (int) (((Math.random() * ((noOfQuestions - i + 1) - 1 + 1) + 1)) - 1);
                            questionOrder[i - 1] = questionRandomize.get(h);
                            questionRandomize.remove(h);
                        }
                        BufferedReader read = new BufferedReader(new FileReader(f457));
                        PrintWriter pw98 = new PrintWriter(new FileWriter(f457), true);
                        pw98.println(quizTitle);
                        File f468 = new File(studentName + "$$" + teacherName + "$$" +
                                courseName + "$$" + Integer.toString(quizNumber) + "$$" +
                                Integer.toString(attemptNumber + 1) + "Solutions.txt");
                        f468.createNewFile();
                        BufferedReader read468 = new BufferedReader(new FileReader(f468));
                        PrintWriter pw468 = new PrintWriter(new FileWriter(f468), true);
                        for (int y = 0; y < questionOrder.length; y++) {
                            String inLine = "";
                            for (int lp = 0; lp < 6; lp++) {
                                if (lp % 6 == 0) {
                                    inLine += Integer.toString(y + 1) + ". " +
                                            questionArray1[questionOrder[y] - 1][0] + "@";
                                } else if (lp % 6 == 5) {
                                    pw468.println(Integer.toString(y + 1) + ". " +
                                            questionArray1[questionOrder[y] - 1][5]);
                                } else {
                                    if (lp == 1) {
                                        inLine += "A) " + questionArray1[questionOrder[y] - 1][lp] + "@";
                                    } else if (lp == 2) {
                                        inLine += "B) " + questionArray1[questionOrder[y] - 1][lp] + "@";
                                    } else if (lp == 3) {
                                        inLine += "C) " + questionArray1[questionOrder[y] - 1][lp] + "@";
                                    } else {
                                        inLine += "D) " + questionArray1[questionOrder[y] - 1][lp] + "@";
                                    }
                                }
                            }
                            pw98.println(inLine);
                        }
                        File ft = new File(studentName + "$$processQuiz.txt");
                        ft.createNewFile();
                        BufferedReader readft = new BufferedReader(new FileReader(ft));
                        PrintWriter pwft = new PrintWriter(new FileWriter(ft), true);
                        pwft.println(String.format("%s@%s@qn-%d@attemptno-%d@qtsf-%d", teacherName,
                                courseName, quizNumber, attemptNumber + 1, 1));
                        pwft.println(quizTitle);
                        File sft = new File(studentName + "$$" + teacherName + "$$" +
                                courseName + "$$" + Integer.toString(quizNumber) + "$$" +
                                Integer.toString(attemptNumber + 1) + "$$submission.txt");
                        sft.createNewFile();
                        File studentAnswersOnly = new File(studentName + "$$" +
                                teacherName + "$$" + courseName + "$$" + Integer.toString(quizNumber) +
                                "$$" + Integer.toString(attemptNumber + 1) + "$$submittedAnswers.txt");
                        studentAnswersOnly.createNewFile();
                        BufferedReader readStudentAnswersOnly = new BufferedReader(new FileReader(studentAnswersOnly));
                        PrintWriter pwStudentAnswersOnly = new PrintWriter(new FileWriter(studentAnswersOnly),
                                true);
                        BufferedReader readsft = new BufferedReader(new FileReader(sft));
                        PrintWriter pwsft = new PrintWriter(new FileWriter(sft), true);
                        pwsft.println(quizTitle);
                        String quizStarting = read.readLine();
                        int counterAgain = 0;
                        while (quizStarting != null) {
                            counterAgain++;
                            if (counterAgain == 1) {
                                System.out.println(quizStarting);
                            } else {
                                System.out.print(quizStarting.replaceAll("@", "\n"));
                                String choic;
                                do {
                                    System.out.println("1. Enter the response\n2. attach a file with the response");
                                    choic = sc.nextLine();
                                    if (choic.equals("1") || choic.equals("2")) {
                                        break;
                                    } else {
                                        System.out.println("invalid response");
                                    }
                                } while (true);
                                do {
                                    if (choic.equals("2")) {
                                        System.out.println("Enter the file path");
                                        String responseFilePath = sc.nextLine();
                                        File fresponse = new File(responseFilePath);
                                        BufferedReader mz = new BufferedReader(new FileReader(fresponse));
                                        String temp = mz.readLine();
                                        if (fresponse.createNewFile() || temp == null) {
                                            System.out.println("The file does not exist or is empty. " +
                                                    "Please enter a valid file path");
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
                                                    appendingString += String.format("%s@%s@qn-%d@attemptno-%d@qtsf-%d", teacherName,
                                                            courseName, quizNumber, attemptNumber + 1, counterAgain) + "\n";

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
                                        String answer;
                                        do {
                                            System.out.println("Enter the answer");
                                            answer = sc.nextLine();
                                            if (answer.equals("A") || answer.equals("B") || answer.equals("C") || answer.equals("D")) {
                                                break;
                                            } else {
                                                System.out.println("The answer should be either A, B, C or D");
                                            }
                                        } while (true);
                                        pwStudentAnswersOnly.println(answer);
                                        BufferedReader bread = new BufferedReader(new FileReader(ft));
                                        String readings = bread.readLine();
                                        String appendingString = "";
                                        int cf = 0;
                                        while (readings != null) {
                                            cf++;
                                            if (cf == 1) {
                                                appendingString += String.format("%s@%s@qn-%d@attemptno-%d@qtsf-%d", teacherName,
                                                        courseName, quizNumber, attemptNumber + 1, counterAgain) + "\n";
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
                        File quizGradingReport = new File(studentName + "$$" + teacherName + "$$" +
                                courseName + "$$" + Integer.toString(quizNumber) + "$$" +
                                Integer.toString(attemptNumber + 1) + "$$gradingReport.txt");
                        quizGradingReport.createNewFile();
                        BufferedReader brGrading = new BufferedReader(new FileReader(quizGradingReport));
                        PrintWriter pwGrading = new PrintWriter(new FileWriter(quizGradingReport), true);
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
                        PrintWriter pw901 = new PrintWriter(new FileWriter(f1), true);
                        pw901.println(Integer.toString(attemptNumber + 1));
                        pw901.flush();
                    }
                } else {
                    if (quizIdentifier.contains("pool")) {
                        File f457 = new File(studentName + "$$" + teacherName + "$$" +
                                courseName + "$$" + Integer.toString(quizNumber) + "$$" +
                                Integer.toString(attemptNumber + 1) + "formQuiz.txt");
                        f457.createNewFile();
                        ArrayList<Integer> questionRandomize = new ArrayList<>();
                        for (int i = 1; i <= noOfQuestions; i++) {
                            questionRandomize.add(i);
                        }
                        int[] questionOrder = new int[5];
                        for (int i = 1; i <= 5; i++) {
                            int h = (int) (((Math.random() * ((noOfQuestions - i + 1) - 1 + 1) + 1)) - 1);
                            questionOrder[i - 1] = questionRandomize.get(h);
                            questionRandomize.remove(h);
                        }
                        BufferedReader read = new BufferedReader(new FileReader(f457));
                        PrintWriter pw98 = new PrintWriter(new FileWriter(f457), true);
                        pw98.println(quizTitle);
                        File f468 = new File(studentName + "$$" + teacherName + "$$" +
                                courseName + "$$" + Integer.toString(quizNumber) + "$$" +
                                Integer.toString(attemptNumber + 1) + "Solutions.txt");
                        f468.createNewFile();
                        BufferedReader read468 = new BufferedReader(new FileReader(f468));
                        PrintWriter pw468 = new PrintWriter(new FileWriter(f468), true);
                        for (int y = 0; y < questionOrder.length; y++) {
                            String inLine = "";
                            for (int lp = 0; lp < 6; lp++) {
                                if (lp % 6 == 0) {
                                    inLine += Integer.toString(y + 1) + ". " + questionArray1[questionOrder[y] - 1][0] + "@";
                                } else if (lp % 6 == 5) {
                                    pw468.println(Integer.toString(y + 1) + ". " + questionArray1[questionOrder[y] - 1][5]);
                                } else {
                                    if (lp == 1) {
                                        inLine += "A) " + questionArray1[questionOrder[y] - 1][lp] + "@";
                                    } else if (lp == 2) {
                                        inLine += "B) " + questionArray1[questionOrder[y] - 1][lp] + "@";
                                    } else if (lp == 3) {
                                        inLine += "C) " + questionArray1[questionOrder[y] - 1][lp] + "@";
                                    } else {
                                        inLine += "D) " + questionArray1[questionOrder[y] - 1][lp] + "@";
                                    }
                                }
                            }
                            pw98.println(inLine);
                        }
                        File ft = new File(studentName + "$$processQuiz.txt");
                        ft.createNewFile();
                        BufferedReader readft = new BufferedReader(new FileReader(ft));
                        PrintWriter pwft = new PrintWriter(new FileWriter(ft), true);
                        pwft.println(String.format("%s@%s@qn-%d@attemptno-%d@qtsf-%d", teacherName,
                                courseName, quizNumber, attemptNumber + 1, 1));
                        pwft.println(quizTitle);
                        File sft = new File(studentName + "$$" + teacherName + "$$" +
                                courseName + "$$" + Integer.toString(quizNumber) + "$$" +
                                Integer.toString(attemptNumber + 1) + "$$submission.txt");
                        sft.createNewFile();
                        File studentAnswersOnly = new File(studentName + "$$" +
                                teacherName + "$$" + courseName + "$$" + Integer.toString(quizNumber) +
                                "$$" + Integer.toString(attemptNumber + 1) + "$$submittedAnswers.txt");
                        studentAnswersOnly.createNewFile();
                        BufferedReader readStudentAnswersOnly = new BufferedReader(new FileReader(studentAnswersOnly));
                        PrintWriter pwStudentAnswersOnly = new PrintWriter(new FileWriter(studentAnswersOnly),
                                true);
                        BufferedReader readsft = new BufferedReader(new FileReader(sft));
                        PrintWriter pwsft = new PrintWriter(new FileWriter(sft), true);
                        pwsft.println(quizTitle);
                        String quizStarting = read.readLine();
                        int counterAgain = 0;
                        while (quizStarting != null) {
                            counterAgain++;
                            if (counterAgain == 1) {
                                System.out.println(quizStarting);
                            } else {
                                System.out.print(quizStarting.replaceAll("@", "\n"));
                                String choic;
                                do {
                                    System.out.println("1. Enter the response\n2. attach a file with the response");
                                    choic = sc.nextLine();
                                    if (choic.equals("1") || choic.equals("2")) {
                                        break;
                                    } else {
                                        System.out.println("invalid response");
                                    }
                                } while (true);
                                do {
                                    if (choic.equals("2")) {
                                        System.out.println("Enter the file path");
                                        String responseFilePath = sc.nextLine();
                                        File fresponse = new File(responseFilePath);
                                        if (fresponse.length() == 0) {
                                            System.out.println("The file does not exist or is empty. Please enter a valid file path");
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
                                                    appendingString += String.format("%s@%s@qn-%d@attemptno-%d@qtsf-%d", teacherName,
                                                            courseName, quizNumber, attemptNumber + 1, counterAgain) + "\n";

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
                                        String answer;
                                        do {
                                            System.out.println("Enter the answer");
                                            answer = sc.nextLine();
                                            if (answer.equals("A") || answer.equals("B") || answer.equals("C") || answer.equals("D")) {
                                                break;
                                            } else {
                                                System.out.println("The answer should be either A, B, C or D");
                                            }
                                        } while (true);
                                        pwStudentAnswersOnly.println(answer);
                                        BufferedReader bread = new BufferedReader(new FileReader(ft));
                                        String readings = bread.readLine();
                                        String appendingString = "";
                                        int cf = 0;
                                        while (readings != null) {
                                            cf++;
                                            if (cf == 1) {
                                                appendingString += String.format("%s@%s@qn-%d@attemptno-%d@qtsf-%d", teacherName,
                                                        courseName, quizNumber, attemptNumber + 1, counterAgain) + "\n";
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
                        File quizGradingReport = new File(studentName + "$$" + teacherName + "$$" +
                                courseName + "$$" + Integer.toString(quizNumber) + "$$" +
                                Integer.toString(attemptNumber + 1) + "$$gradingReport.txt");
                        quizGradingReport.createNewFile();
                        BufferedReader brGrading = new BufferedReader(new FileReader(quizGradingReport));
                        PrintWriter pwGrading = new PrintWriter(new FileWriter(quizGradingReport),
                                true);
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
                        PrintWriter pw901 = new PrintWriter(new FileWriter(f1), true);
                        pw901.println(Integer.toString(attemptNumber + 1));
                        pw901.flush();
                    } else {
                        File f457 = new File(studentName + "$$" + teacherName + "$$" + courseName +
                                "$$" + Integer.toString(quizNumber) + "$$" +
                                Integer.toString(attemptNumber + 1) + "formQuiz.txt");
                        f457.createNewFile();
                        ArrayList<Integer> questionRandomize = new ArrayList<>();
                        for (int i = 1; i <= noOfQuestions; i++) {
                            questionRandomize.add(i);
                        }
                        int[] questionOrder = new int[noOfQuestions];
                        for (int i = 1; i <= noOfQuestions; i++) {
                            questionOrder[i - 1] = i;
                        }
                        BufferedReader read = new BufferedReader(new FileReader(f457));
                        PrintWriter pw98 = new PrintWriter(new FileWriter(f457), true);
                        pw98.println(quizTitle);
                        File f468 = new File(studentName + "$$" + teacherName + "$$" +
                                courseName + "$$" + Integer.toString(quizNumber) + "$$" +
                                Integer.toString(attemptNumber + 1) + "Solutions.txt");
                        f468.createNewFile();
                        BufferedReader read468 = new BufferedReader(new FileReader(f468));
                        PrintWriter pw468 = new PrintWriter(new FileWriter(f468), true);
                        for (int y = 0; y < questionOrder.length; y++) {
                            String inLine = "";
                            for (int lp = 0; lp < 6; lp++) {
                                if (lp % 6 == 0) {
                                    inLine += Integer.toString(y + 1) + ". " + questionArray1[questionOrder[y] - 1][0] + "@";
                                } else if (lp % 6 == 5) {
                                    pw468.println(Integer.toString(y + 1) + ". " + questionArray1[questionOrder[y] - 1][5]);
                                } else {
                                    if (lp == 1) {
                                        inLine += "A) " + questionArray1[questionOrder[y] - 1][lp] + "@";
                                    } else if (lp == 2) {
                                        inLine += "B) " + questionArray1[questionOrder[y] - 1][lp] + "@";
                                    } else if (lp == 3) {
                                        inLine += "C) " + questionArray1[questionOrder[y] - 1][lp] + "@";
                                    } else {
                                        inLine += "D) " + questionArray1[questionOrder[y] - 1][lp] + "@";
                                    }
                                }
                            }
                            pw98.println(inLine);
                        }
                        File ft = new File(studentName + "$$processQuiz.txt");
                        ft.createNewFile();
                        BufferedReader readft = new BufferedReader(new FileReader(ft));
                        PrintWriter pwft = new PrintWriter(new FileWriter(ft), true);
                        pwft.println(String.format("%s@%s@qn-%d@attemptno-%d@qtsf-%d", teacherName,
                                courseName, quizNumber, attemptNumber + 1, 1));
                        pwft.println(quizTitle);
                        File sft = new File(studentName + "$$" + teacherName + "$$" +
                                courseName + "$$" + Integer.toString(quizNumber) + "$$" +
                                Integer.toString(attemptNumber + 1) + "$$submission.txt");
                        sft.createNewFile();
                        File studentAnswersOnly = new File(studentName + "$$" + teacherName +
                                "$$" + courseName + "$$" + Integer.toString(quizNumber) + "$$" +
                                Integer.toString(attemptNumber + 1) + "$$submittedAnswers.txt");
                        studentAnswersOnly.createNewFile();
                        BufferedReader readStudentAnswersOnly = new BufferedReader(new FileReader(studentAnswersOnly));
                        PrintWriter pwStudentAnswersOnly = new PrintWriter(new FileWriter(studentAnswersOnly),
                                true);
                        BufferedReader readsft = new BufferedReader(new FileReader(sft));
                        PrintWriter pwsft = new PrintWriter(new FileWriter(sft), true);
                        pwsft.println(quizTitle);
                        String quizStarting = read.readLine();
                        int counterAgain = 0;
                        while (quizStarting != null) {
                            counterAgain++;
                            if (counterAgain == 1) {
                                System.out.println(quizStarting);
                            } else {
                                System.out.print(quizStarting.replaceAll("@", "\n"));
                                String choic;
                                do {
                                    System.out.println("1. Enter the response\n2. attach a file with the response");
                                    choic = sc.nextLine();
                                    if (choic.equals("1") || choic.equals("2")) {
                                        break;
                                    } else {
                                        System.out.println("invalid response");
                                    }
                                } while (true);
                                do {
                                    if (choic.equals("2")) {
                                        System.out.println("Enter the file path");
                                        String responseFilePath = sc.nextLine();
                                        File fresponse = new File(responseFilePath);
                                        BufferedReader mz = new BufferedReader(new FileReader(fresponse));
                                        String temp = mz.readLine();
                                        if (fresponse.createNewFile() || temp == null) {
                                            System.out.println("The file does not exist or is empty. Please enter a valid file path");
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
                                                    appendingString += String.format("%s@%s@qn-%d@attemptno-%d@qtsf-%d", teacherName,
                                                            courseName, quizNumber, attemptNumber + 1, counterAgain) + "\n";

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
                                        String answer;
                                        do {
                                            System.out.println("Enter the answer");
                                            answer = sc.nextLine();
                                            if (answer.equals("A") || answer.equals("B") || answer.equals("C") || answer.equals("D")) {
                                                break;
                                            } else {
                                                System.out.println("The answer should be either A, B, C or D");
                                            }
                                        } while (true);
                                        pwStudentAnswersOnly.println(answer);
                                        BufferedReader bread = new BufferedReader(new FileReader(ft));
                                        String readings = bread.readLine();
                                        String appendingString = "";
                                        int cf = 0;
                                        while (readings != null) {
                                            cf++;
                                            if (cf == 1) {
                                                appendingString += String.format("%s@%s@qn-%d@attemptno-%d@qtsf-%d", teacherName,
                                                        courseName, quizNumber, attemptNumber + 1, counterAgain) + "\n";
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
                        File quizGradingReport = new File(studentName + "$$" + teacherName + "$$" +
                                courseName + "$$" + Integer.toString(quizNumber) + "$$" +
                                Integer.toString(attemptNumber + 1) + "$$gradingReport.txt");
                        quizGradingReport.createNewFile();
                        BufferedReader brGrading = new BufferedReader(new FileReader(quizGradingReport));
                        PrintWriter pwGrading = new PrintWriter(new FileWriter(quizGradingReport),
                                true);
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
                        PrintWriter pw901 = new PrintWriter(new FileWriter(f1), true);
                        pw901.println(Integer.toString(attemptNumber + 1));
                        pw901.flush();
                    }
                }
            } else if (quizIdentifier.contains("fill in the blanks")) {

            } else {

            }
        }
    }
}
