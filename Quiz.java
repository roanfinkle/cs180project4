import java.io.*;
import java.util.Scanner;

public class Quiz {
    private int quizNumber ;
    private int numOfQuestions ;
    public Quiz(int quizNumber , String quizListFileName , int numOfQuestions , String type , String poolOrExact , String fileOrManually , boolean randomize) throws IOException {
        this.quizNumber = quizNumber ;
        this.numOfQuestions = numOfQuestions ;
        File f = new File(quizListFileName) ;
        BufferedReader br = new BufferedReader(new FileReader(f)) ;
        String str = br.readLine() ;
        String s = "" ;
        while(str != null) {
            s += str + "\n" ;
            str = br.readLine() ;
        }
        s += "Quiz" + Integer.toString(quizNumber) + "(" ;
        s += Integer.toString(numOfQuestions) + ")" + "(" + type + ")" ;
        s += "(" + poolOrExact + ")" + "(" + fileOrManually + ")" ;
        s += String.format("(%s)" , Boolean.toString(randomize)) + "\n" ;
        PrintWriter pw = new PrintWriter(new FileWriter(f), true) ;
        pw.print(s) ;
        pw.close() ;
    }
    public Quiz(int quizNumber) {
        this.quizNumber = quizNumber ;
    }
    public boolean makePoolQuizFile(String filePath , String quizFileName) {
        try {
            File f1 = new File(filePath) ;
            BufferedReader br = new BufferedReader(new FileReader(f1)) ;
            String str = br.readLine() ;
            if (str == null) {
                return false ;
            } else {
                String s = "" ;
                while(str != null) {
                    s += str ;
                    str = br.readLine() ;
                }
                File f2 = new File(quizFileName) ;
                f2.createNewFile() ;
                PrintWriter pw = new PrintWriter(new FileWriter(f2), true) ;
                pw.println(s) ;
                pw.close() ;
                return true ;
            }
        } catch (IOException e) {
            return false ;
        }
    }
    public boolean makeExactQuizFile(String filePath , String quizFileName) {
        try {
            File f1 = new File(filePath) ;
            BufferedReader br = new BufferedReader(new FileReader(f1)) ;
            String str = br.readLine() ;
            String returnValue ;
            if (str == null) {
                return false ;
            } else {
                String s = "" ;
                while(str != null) {
                    s += str ;
                    str = br.readLine() ;
                }
                File f2 = new File(quizFileName) ;
                f2.createNewFile() ;
                PrintWriter pw = new PrintWriter(new FileWriter(f2), true) ;
                pw.println(s) ;
                pw.close() ;
                return true ;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace() ;
            return false ;
        } catch (IOException e) {
            return false ;
        }
    }
    public void makeQuizManual(String quizFileName , String type) throws IOException {
        File f2 = new File(quizFileName) ;
        f2.createNewFile() ;
        BufferedReader br = new BufferedReader(new FileReader(f2)) ;
        String str = br.readLine() ;
        String s = "" ;
        while(str != null) {
            s += str ;
            str = br.readLine() ;
        }
        PrintWriter pw = new PrintWriter(new FileWriter(f2), true) ;
        Scanner sc = new Scanner(System.in) ;
        if(type.equals("MCQ")) {
            if (s.equals("")) {
                System.out.println("Enter the title of the quiz");
                String title = sc.nextLine();
                s += title +'\n' ;
                pw.println(title);
                boolean g ;
                do {
                    int option ;
                    boolean h ;
                    do {
                        System.out.println("Do you want to enter a question statement?\n1. yes\n2. no");
                        option = sc.nextInt();
                        sc.nextLine();
                        if(!(option == 1 || option == 2)) {
                            System.out.println("Enter a valid option") ;
                            h = false ;
                        } else {
                            h = true ;
                        }
                    } while(!h) ;
                    if (option == 1) {
                        System.out.println("Enter the question number") ;
                        int qn = sc.nextInt() ;
                        sc.nextLine() ;
                        System.out.println("Enter the question statement") ;
                        String qs = sc.nextLine() ;
                        s += Integer.toString(qn) + ". " + qs + '\n' ;
                        pw.println(Integer.toString(qn) + ". " + qs) ;
                        g = false ;
                    } else {
                        boolean jk ;
                        int option1 ;
                        do {
                            System.out.println("Do you want to enter the four choices for the question?\n1. yes\n2. no") ;
                            option1 = sc.nextInt() ;
                            sc.nextLine() ;
                            if(!(option1 == 1 || option1 == 2)) {
                                System.out.println("Enter a valid option") ;
                                jk = false ;
                            } else {
                                jk = true ;
                            }
                        } while(!jk) ;
                        if (option1 == 1) {
                            int cn ;
                            do {
                                String answerChoices ;
                                System.out.println("Enter the choice number") ;
                                cn = sc.nextInt() ;
                                sc.nextLine() ;
                                if (cn == 1) {
                                    System.out.println("Enter the first choice") ;
                                    answerChoices = sc.nextLine() ;
                                    s += "A) " + answerChoices + '\n' ;
                                    pw.println("A) " + answerChoices) ;
                                } else if (cn == 2) {
                                    System.out.println("Enter the second option") ;
                                    answerChoices = sc.nextLine() ;
                                    s += "B) " + answerChoices + '\n';
                                    pw.println("B) " + answerChoices) ;
                                } else if (cn == 3) {
                                    System.out.println("Enter the third option") ;
                                    answerChoices = sc.nextLine() ;
                                    s += "C) " + answerChoices + '\n';
                                    pw.println("C) " + answerChoices) ;
                                } else if (cn == 4) {
                                    System.out.println("Enter the fourth option") ;
                                    answerChoices = sc.nextLine() ;
                                    s += "D) " + answerChoices + '\n' ;
                                    pw.println("D) " + answerChoices) ;
                                } else {
                                    System.out.println("This is not a valid choice number") ;
                                }
                            } while(cn != 4) ;
                            g = false ;
                            } else {
                            g = true ;
                        }
                        }
                    } while(!g) ;
            } else {
                boolean df ;
                do {
                    System.out.println("Select from the following options.") ;
                    System.out.println("1. Change the title") ;
                    System.out.println("2. Add a question statement") ;
                    System.out.println("3. Add choices to a question") ;
                    System.out.println("4. Delete a question") ;
                    System.out.println("5. Replace a question") ;
                    System.out.println("6. Exit the quiz without editing") ;
                    int option6 = sc.nextInt() ;
                    sc.nextLine() ;
                    switch (option6) {
                        case 1: {
                            System.out.println("Enter the new title") ;
                            String newTitle = sc.nextLine() ;
                            s = s.substring(s.indexOf('\n')) ;
                            s = newTitle + s ;
                            PrintWriter pw1 = new PrintWriter(new FileWriter(f2), true) ;
                            pw1.println(s) ;
                            pw1.close() ;
                            System.out.println("Title successfully changed.") ;
                            df = false ;
                            break ;
                        } case 2: {
                            System.out.println("Enter the question number") ;
                            int qn = sc.nextInt() ;
                            sc.nextLine() ;
                            System.out.println("Enter the question statement") ;
                            String qs = sc.nextLine() ;
                            s += Integer.toString(qn) + ". " + qs + '\n';
                            PrintWriter pw1 = new PrintWriter(new FileWriter(f2), true) ;
                            pw1.print(s) ;
                            pw1.close() ;
                            System.out.println("Question statement added successfully.") ;
                            df = false ;
                            break ;
                        } case 3: {
                            int cn ;
                            do {
                                String answerChoices ;
                                System.out.println("Enter the choice number") ;
                                cn = sc.nextInt() ;
                                sc.nextLine() ;
                                if (cn == 1) {
                                    System.out.println("Enter the first choice") ;
                                    answerChoices = sc.nextLine() ;
                                    s += "A) " + answerChoices + '\n';
                                    PrintWriter pw1 = new PrintWriter(new FileWriter(f2), true) ;
                                    pw1.print(s) ;
                                    pw1.close() ;
                                } else if (cn == 2) {
                                    System.out.println("Enter the second option") ;
                                    answerChoices = sc.nextLine() ;
                                    s += "B) " + answerChoices + '\n';
                                    PrintWriter pw1 = new PrintWriter(new FileWriter(f2), true) ;
                                    pw1.print(s) ;
                                    pw1.close() ;
                                } else if (cn == 3) {
                                    System.out.println("Enter the third option") ;
                                    answerChoices = sc.nextLine() ;
                                    s += "C) " + answerChoices + '\n';
                                    PrintWriter pw1 = new PrintWriter(new FileWriter(f2), true) ;
                                    pw1.print(s) ;
                                    pw1.close() ;
                                } else if (cn == 4) {
                                    System.out.println("Enter the fourth option") ;
                                    answerChoices = sc.nextLine() ;
                                    s += "D) " + answerChoices + '\n';
                                    PrintWriter pw1 = new PrintWriter(new FileWriter(f2), true) ;
                                    pw1.print(s) ;
                                    pw1.close() ;
                                } else {
                                    System.out.println("This is not a valid choice number") ;
                                }
                            } while(cn != 4) ;
                            df = false ;
                            break ;
                        } case 4: {
                            System.out.println("Enter the question number to be deleted.") ;
                            int dqn = sc.nextInt() ;
                            sc.nextLine() ;
                            String str1 = String.format("%d. " , dqn) ;
                            String str2 = String.format("%d. " , dqn + 1) ;
                            if (!s.contains(str1)) {
                                System.out.println("This question is not yet added to the quiz.") ;
                            } else {
                                if (!s.contains(str2)) {
                                    s = s.substring(0, s.indexOf(str1)) ;
                                    PrintWriter pw1 = new PrintWriter(new FileWriter(f2), true) ;
                                    pw1.print(s) ;
                                    pw1.close() ;
                                } else {
                                    s = s.substring(0, s.indexOf(str1)) + s.substring(s.indexOf(str2));
                                    PrintWriter pw1 = new PrintWriter(new FileWriter(f2), true) ;
                                    pw1.print(s) ;
                                    pw1.close() ;
                                }
                            }
                            df = false ;
                            break ;
                        } case 5: {
                            System.out.println("Enter the question number of the question to be replaced") ;
                            int rqn = sc.nextInt() ;
                            sc.nextLine() ;
                            String str1 = String.format("%d. " , rqn) ;
                            String str2 = String.format("%d. " , rqn + 1) ;
                            if (!s.contains(str1)) {
                                System.out.println("This question is not yet added to the quiz.") ;
                            } else {
                                System.out.println("Enter the new question statement.") ;
                                String nqs = sc.nextLine() ;
                                System.out.println("Enter the first option") ;
                                String firstOption = sc.nextLine() ;
                                firstOption = "A) " + firstOption + "\n" ;
                                System.out.println("Enter the second option") ;
                                String secondOption = sc.nextLine() ;
                                secondOption = "B) " + secondOption + "\n" ;
                                System.out.println("Enter the third option") ;
                                String thirdOption = sc.nextLine() ;
                                thirdOption = "C) " + thirdOption + "\n" ;
                                System.out.println("Enter the fourth option") ;
                                String fourthOption = sc.nextLine() ;
                                fourthOption = "D) " + fourthOption + "\n";
                                String str3 = str1 + nqs + "\n" + firstOption + secondOption + thirdOption + fourthOption;
                                if (!s.contains(str2)) {
                                    s = s.substring(0, s.indexOf(str1)) ;
                                    s += str3;
                                    PrintWriter pw1 = new PrintWriter(new FileWriter(f2), true) ;
                                    pw1.print(s) ;
                                    pw1.close() ;
                                } else {
                                    s = s.substring(0, s.indexOf(str1)) ;
                                    s += str3;
                                    s += s.substring(s.indexOf(str2)) ;
                                    PrintWriter pw1 = new PrintWriter(new FileWriter(f2), true) ;
                                    pw1.print(s) ;
                                    pw1.close() ;
                                }
                            }
                            df = false ;
                            break ;
                        } case 6: {
                            df = true ;
                            break ;
                        } default: {
                            System.out.println("This is not a valid choice") ;
                            df = false ;
                            break ;
                        }
                    }
                } while(!df) ;
                    }
                }
            }
    }

