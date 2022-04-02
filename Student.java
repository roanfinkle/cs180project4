import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Student {
    private String quizFileName;
    private String quizProgressFileName;

    //Student object constructor for a student with a quiz in progress
    public Student(String quizFileName, String quizProgressFileName) {
        this.quizFileName = quizFileName;
        this.quizProgressFileName = quizProgressFileName;
    }

    //Student method for taking the Quiz that is not in progress
    public void takeQuiz() {
        //Reads the quizFile
        Scanner scanner = new Scanner(System.in);
        File quizFile = new File(quizFileName);
        ArrayList<String> list = new ArrayList<>();
        try {
            //First checks if the quiz is in progress by checking the length of the progress file
            File quizProgress = new File(quizProgressFileName);
            quizProgress.createNewFile();
            FileReader fr = new FileReader(quizProgressFileName);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            ArrayList<String> progressFileLength = new ArrayList<>();
            boolean inProgress;
            while (line != null) {
                progressFileLength.add(line);
                line = bfr.readLine();
            }
            bfr.close();
            if (progressFileLength.size() <= 1) {
                inProgress = false;
            } else {
                inProgress = true;
            }


            fr = new FileReader(quizFile);
            bfr = new BufferedReader(fr);
            line = bfr.readLine();
            while (line != null) {
                list.add(line);
                line = bfr.readLine();
            }
            bfr.close();

            //Gets length of quizProgressFile to for later to know what question to start on
            int progressFileLengthInt = progressFileLength.size(); 

            //If the list is in progress
            if (inProgress) {
                //First line of quizFile is quiz name. Gets it to print to student but NOT to write to file
                String quizName = list.get(0);
            
                
                System.out.println(progressFileLengthInt);

                //Starts output by printing out quiz name and if progress has been made
                System.out.println(quizName);
                System.out.println("You have already made progress on this quiz.");

                //Loops through the rest of the file and stores quiz questions and their respective answers into variables
                //Then prints them out and allows the student to take the test
                for (int i = progressFileLengthInt; i < list.size(); i++) {
                    //Gets the substring of the question and stores it into the question variable
                    String question = list.get(i).substring(4, list.get(i).indexOf("@", 4));

                    //Gets the whole question line and stores it in String l
                    String l = list.get(i);
                
                    //Finds the correct answer and stores it in correctAnswer by looking for the key @AR: @BR: @CR: or @DR: R stands for right, then deletes the R from the substring
                    String correctAnswer;
                    if (l.indexOf("@AR:") != -1) {
                        correctAnswer = "A";
                        l = l.substring(0, l.indexOf("@AR:") + 2) + l.substring(l.indexOf("@AR:") + 3);
                    } else if (l.indexOf("@BR:") != -1) {
                        correctAnswer = "B";
                        l = l.substring(0, l.indexOf("@BR:") + 2) + l.substring(l.indexOf("@BR:") + 3);
                    } else if (l.indexOf("@CR:") != -1) {
                        correctAnswer = "C";
                        l = l.substring(0, l.indexOf("@CR:") + 2) + l.substring(l.indexOf("@CR:") + 3);
                    } else {
                        correctAnswer = "D";
                        l = l.substring(0, l.indexOf("@DR:") + 2) + l.substring(l.indexOf("@DR:") + 3);
                    }

                    //Stores the substrings of each answer inside of their respective answerLetter variable
                    String answerA = l.substring(l.indexOf("@A:") + 3, l.indexOf("@B:"));
                    String answerB = l.substring(l.indexOf("@B:") + 3, l.indexOf("@C:"));
                    String answerC = l.substring(l.indexOf("@C:") + 3, l.indexOf("@D:"));
                    String answerD = l.substring(l.indexOf("@D:") + 3);

                    //Prints out question number, the question, and answers
                    System.out.println("Question number: " + i);
                    System.out.println(question);
                    System.out.println("A: " + answerA);
                    System.out.println("B: " + answerB);
                    System.out.println("C: " + answerC);
                    System.out.println("D: " + answerD);

                    //Asks user if they would like to answer by entering a number or a file and checks to see if the input is valid
                    int choice = 0;
                    do {
                        System.out.println("Would you like to answer normally or by attaching a file?");
                        System.out.println("1: Normally");
                        System.out.println("2: File");
                        String response = scanner.nextLine();
                        if (response.equals("1")) {
                            choice = 1;
                        } else if (response.equals("2")) {
                            choice = 2;
                        } else {
                            choice = 0;
                            System.out.println("Incorrect file format!");
                        }
                    } while(choice != 1 && choice != 2);

                    //If choice is normal, prompts user to enter A, B, C, or D as a String and stores in variable answerChoice
                    //If choice is file, asks the user for the fileName and reads the first letter of the file. If it is A, B, C, or D, stores answer in variable answerChoice
                    //Both choices are doWhile loops that wont end until a valid input is recieved
                    String answerChoice;
                    if (choice == 1) {
                        do {
                            System.out.println("Enter either A, B, C, or D: ");
                            answerChoice = scanner.nextLine().toUpperCase();
                            if (!answerChoice.equals("A") && !answerChoice.equals("B") && !answerChoice.equals("C") && !answerChoice.equals("D")) {
                                System.out.println("You must enter your answer in the correct format!");
                            }
                        } while(!answerChoice.equals("A") && !answerChoice.equals("B") && !answerChoice.equals("C") && !answerChoice.equals("D")); 
                    } else {
                        do {
                            ArrayList<String> listAns = new ArrayList<>();
                            System.out.println("Enter file name with the letter of your answer at the front of the first line:");
                            String fileInput = scanner.nextLine();
                            File answerFile = new File(fileInput);
                            answerFile.createNewFile();
                            FileReader frAns = new FileReader(fileInput);
                            BufferedReader bfrAns = new BufferedReader(frAns);
                            String lineAns = bfrAns.readLine();
                            while (lineAns != null) {
                                listAns.add(lineAns);
                                lineAns = bfrAns.readLine();
                            }
                            bfrAns.close();
                            if (listAns.size() == 0) {
                                answerChoice = "Z";
                            } else {
                                answerChoice = listAns.get(0).substring(0, 1).toUpperCase();
                            }
                            if (!answerChoice.equals("A") && !answerChoice.equals("B") && !answerChoice.equals("C") && !answerChoice.equals("D")) {
                                System.out.println("Either your file does not exist or it is in the wrong format!");
                            }
                        } while(!answerChoice.equals("A") && !answerChoice.equals("B") && !answerChoice.equals("C") && !answerChoice.equals("D")); 
                    }

                    //Checks if answerChoice is the same as correctAnswer
                    boolean correct;
                    if (answerChoice.equals(correctAnswer)) {
                        correct = true;
                    } else {
                        correct = false;
                    }

                    //Writes to quizProgress file
                    FileOutputStream fos = new FileOutputStream(quizProgressFileName, true);
                    PrintWriter pw = new PrintWriter(fos);
                    if (correct) {
                        pw.println(list.get(i).substring(0, 4) + question + "@Choice:" + answerChoice + "@" + "Correct");
                        pw.close();
                    } else {
                        pw.println(list.get(i).substring(0, 4) + question + "@Choice:" + answerChoice + "@" + "Incorrect");
                        pw.close();
                    }       
                }
            } else {
                //First line of quizFile is quiz name. Gets it and writes quiz name to progressFile
                String quizName = list.get(0);
                if (progressFileLengthInt == 0) {
                    FileOutputStream fos = new FileOutputStream(quizProgressFileName, true);
                    PrintWriter pw = new PrintWriter(fos);
                    pw.println(quizName);
                    pw.close();
                }
                

                //Starts output by printing out quiz name
                System.out.println(quizName);

                //Loops through the rest of the file and stores quiz questions and their respective answers into variables
                //Then prints them out and allows the student to take the test
                for (int i = 1; i < list.size(); i++) {
                    //Gets the substring of the question and stores it into the question variable
                    String question = list.get(i).substring(4, list.get(i).indexOf("@", 4));

                    //Gets the whole question line and stores it in String l
                    String l = list.get(i);
                
                    //Finds the correct answer and stores it in correctAnswer by looking for the key @AR: @BR: @CR: or @DR: R stands for right, then deletes the R from the substring
                    String correctAnswer;
                    if (l.indexOf("@AR:") != -1) {
                        correctAnswer = "A";
                        l = l.substring(0, l.indexOf("@AR:") + 2) + l.substring(l.indexOf("@AR:") + 3);
                    } else if (l.indexOf("@BR:") != -1) {
                        correctAnswer = "B";
                        l = l.substring(0, l.indexOf("@BR:") + 2) + l.substring(l.indexOf("@BR:") + 3);
                    } else if (l.indexOf("@CR:") != -1) {
                        correctAnswer = "C";
                        l = l.substring(0, l.indexOf("@CR:") + 2) + l.substring(l.indexOf("@CR:") + 3);
                    } else {
                        correctAnswer = "D";
                        l = l.substring(0, l.indexOf("@DR:") + 2) + l.substring(l.indexOf("@DR:") + 3);
                    }

                    //Stores the substrings of each answer inside of their respective answerLetter variable
                    String answerA = l.substring(l.indexOf("@A:") + 3, l.indexOf("@B:"));
                    String answerB = l.substring(l.indexOf("@B:") + 3, l.indexOf("@C:"));
                    String answerC = l.substring(l.indexOf("@C:") + 3, l.indexOf("@D:"));
                    String answerD = l.substring(l.indexOf("@D:") + 3);

                    //Prints out question and answers
                    System.out.println("Question number: " + i);
                    System.out.println(question);
                    System.out.println("A: " + answerA);
                    System.out.println("B: " + answerB);
                    System.out.println("C: " + answerC);
                    System.out.println("D: " + answerD);

                    //Asks user if they would like to answer by entering a number or a file and checks to see if the input is valid
                    int choice = 0;
                    do {
                        System.out.println("Would you like to answer normally or by attaching a file?");
                        System.out.println("1: Normally");
                        System.out.println("2: File");
                        String response = scanner.nextLine();
                        if (response.equals("1")) {
                            choice = 1;
                        } else if (response.equals("2")) {
                            choice = 2;
                        } else {
                            choice = 0;
                            System.out.println("Incorrect file format!");
                        }
                    } while(choice != 1 && choice != 2);

                    //If choice is normal, prompts user to enter A, B, C, or D as a String and stores in variable answerChoice
                    //If choice is file, asks the user for the fileName and reads the first letter of the file. If it is A, B, C, or D, stores answer in variable answerChoice
                    //Both choices are doWhile loops that wont end until a valid input is recieved
                    String answerChoice;
                    if (choice == 1) {
                        do {
                            System.out.println("Enter either A, B, C, or D: ");
                            answerChoice = scanner.nextLine().toUpperCase();
                            if (!answerChoice.equals("A") && !answerChoice.equals("B") && !answerChoice.equals("C") && !answerChoice.equals("D")) {
                                System.out.println("You must enter your answer in the correct format!");
                            }
                        } while(!answerChoice.equals("A") && !answerChoice.equals("B") && !answerChoice.equals("C") && !answerChoice.equals("D")); 
                    } else {
                        do {
                            ArrayList<String> listAns = new ArrayList<>();
                            System.out.println("Enter file name with the letter of your answer at the front of the first line:");
                            String fileInput = scanner.nextLine();
                            File answerFile = new File(fileInput);
                            answerFile.createNewFile();
                            FileReader frAns = new FileReader(answerFile);
                            BufferedReader bfrAns = new BufferedReader(frAns);
                            String lineAns = bfrAns.readLine();
                            while (lineAns != null) {
                                listAns.add(lineAns);
                                lineAns = bfrAns.readLine();
                            }
                            bfrAns.close();
                            System.out.println(listAns.size());
                            if (listAns.size() == 0) {
                                answerChoice = "Z";
                            } else {
                                answerChoice = listAns.get(0).substring(0, 1).toUpperCase();
                            }
                            if (!answerChoice.equals("A") && !answerChoice.equals("B") && !answerChoice.equals("C") && !answerChoice.equals("D")) {
                                System.out.println("Either your file does not exist or it is in the wrong format!");
                            }
                        } while(!answerChoice.equals("A") && !answerChoice.equals("B") && !answerChoice.equals("C") && !answerChoice.equals("D")); 
                    }

                    //Checks if answerChoice is the same as correctAnswer
                    boolean correct;
                    if (answerChoice.equals(correctAnswer)) {
                        correct = true;
                    } else {
                        correct = false;
                    }

                    //Writes to quizProgress file
                    FileOutputStream fos = new FileOutputStream(quizProgressFileName, true);
                    PrintWriter pw = new PrintWriter(fos);
                    if (correct) {
                        pw.println(list.get(i).substring(0, 4) + question + "@Choice:" + answerChoice + "@" + "Correct");
                        pw.close();
                    } else {
                        pw.println(list.get(i).substring(0, 4) + question + "@Choice:" + answerChoice + "@" + "Incorrect");
                        pw.close();
                    }       
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //System.out.println("File not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}