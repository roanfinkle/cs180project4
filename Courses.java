import java.io.*;
import java.util.ArrayList;

/**
 * Courses class to manage Courses created 
 * Purdue University -- CS18000 -- Spring 2022 -- Project
 * 4
 *
 * @author Ritwik Jain
 * @version April 04, 2022
 * Lab sec 03
 */

public class Courses {
    private String courseName;
    private ArrayList<Quiz> quizList = new ArrayList<>();

    //Constructor for course that has not already been created
    //CourseListFileName written to with the name of the course
    public Courses(String courseName, String courseListFileName)
            throws IOException {
        this.courseName = courseName;
        File f = new File(courseListFileName);
        BufferedReader br = new BufferedReader(new FileReader(f));
        String str = br.readLine();
        String s = "";
        while (str != null) {
            s += str + "\n";
            str = br.readLine();
        }
        s += courseName + "\n";
        PrintWriter pw = new PrintWriter(new FileWriter(f), true);
        pw.print(s);
        pw.close();
    }

    public Courses(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return this.courseName;
    }

    /**Method that creates a new Quiz object and adds it
      to an Array that stores them called quizList**/
    public boolean createQuiz(int quizNumber, String quizListFileName, int numOfQuestions,
                              String type, String poolOrExact, String fileOrManually,
                              boolean randomize) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(quizListFileName));
        String str = br.readLine();
        while (str != null) {
            if (str.contains("Quiz" + Integer.toString(quizNumber))) {
                return false;
            }
            str = br.readLine();
        }
        this.quizList.add(new Quiz(quizNumber, quizListFileName, numOfQuestions,
                type, poolOrExact, fileOrManually, randomize));
        return true;
    }

    //Method that checks the quiz type
    //Returns not contained for a null quiz
    //Returns filepool, fileexact, manualpoolMCQ, or manualexactMCQ
    //Adds quiz objects to ArrayList quizList
    public String enterQuiz(int quizNumber, String quizListFileName)
            throws IOException {
        File f = new File(quizListFileName);
        BufferedReader br = new BufferedReader(new FileReader(f));
        String er = br.readLine();
        String returnValue = "";
        if (er == null || er.equals("") || er.equals(" ")) {
            return "not contained";
        } else {
            int c = 0;
            while (er != null) {
                if (er.substring(0, er.indexOf('(')).equals("Quiz" +
                        Integer.toString(quizNumber))) {
                    c++;
                    if (er.contains("file")) {
                        if (er.contains("pool")) {
                            returnValue = "filepool";
                        } else {
                            returnValue = "fileexact";
                        }
                    } else {
                        if (er.contains("pool")) {
                            if (er.contains("MCQ")) {
                                returnValue = "manualpoolMCQ";
                            } else if (er.contains("fill in the blanks")) {
                                returnValue = "manualpoolfillintheblanks";
                            } else {
                                returnValue = "manualpooltrue/false";
                            }
                        } else {
                            if (er.contains("MCQ")) {
                                returnValue = "manualexactMCQ";
                            } else if (er.contains("fill in the blanks")) {
                                returnValue = "manualexactfillintheblanks";
                            } else {
                                returnValue = "manualexacttrue/false";
                            }
                        }
                    }
                }
                er = br.readLine();
            }
            if (c == 0) {
                return "not contained";
            } else {
                this.quizList.add(new Quiz(quizNumber));
                return returnValue;
            }
        }
    }

    public ArrayList<Quiz> getListQuizzes() {
        return this.quizList;
    }
}
