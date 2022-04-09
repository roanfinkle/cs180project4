import java.io.*;
import java.util.ArrayList;

public class Courses {
    private String courseName;
    private ArrayList<Quiz> quizList = new ArrayList<>();

    //Constructor for course that has not already been created
    //Constructs a courses object with arguments courseName and courseListFileName.
    //CourseListFileName written to with the name of the course
    public Courses(String courseName, String courseListFileName) throws IOException {
        this.courseName = courseName;
        File f = new File(courseListFileName);
        BufferedReader br = new BufferedReader(new FileReader(f));
        String str = br.readLine();
        String s = "";
        while (str != null) {
            s += str + "\n";
            str = br.readLine();
        }
        br.close();
        s += courseName + "\n";
        PrintWriter pw = new PrintWriter(new FileWriter(f), true);
        pw.print(s);
        pw.close();
    }

    //Constructor for a course that has already been created
    public Courses(String courseName) {
        this.courseName = courseName;
    }

    //Getter for courseName
    public String getCourseName() {
        return this.courseName;
    }

    //Method that creates a new Quiz object and adds it to an Array that stores them called quizList
    public boolean createQuiz(int quizNumber, String quizListFileName, int numOfQuestions, 
        String type, String poolOrExact, String fileOrManually, boolean randomize) throws IOException {
        this.quizList.add(new Quiz(quizNumber, quizListFileName, numOfQuestions, type, poolOrExact, fileOrManually, randomize));
        return true;
    }

    //Method that checks the quiz type
    //Returns not contained for a null quiz
    //Returns filepool, fileexact, manualpoolMCQ, or manualexactMCQ
    //Adds quiz objects to ArrayList quizList
    public String enterQuiz(int quizNumber, String quizListFileName) throws IOException {
        File f = new File(quizListFileName);
        BufferedReader br = new BufferedReader(new FileReader(f));
        String str = br.readLine();
        String returnValue = "";
        if (str == null) {
            br.close();
            return "not contained";
        } else {
            int c = 0;
            while (str != null) {
                if (str.substring(0, str.indexOf('(')).equals("Quiz" + Integer.toString(quizNumber))) {
                    c++;
                    if (str.contains("file")) {
                        if (str.contains("pool")) {
                            returnValue = "filepool";
                        } else {
                            returnValue = "fileexact";
                        }
                    } else {
                        if (str.contains("pool")) {
                            returnValue = "manualpoolMCQ";
                        } else {
                            returnValue = "manualexactMCQ";
                        }
                    }
                }
                str = br.readLine();
            }
            if (c == 0) {
                br.close();
                return "not contained";
            } else {
                br.close();
                this.quizList.add(new Quiz(quizNumber));
                return returnValue;
            }
        }
    }

    //Returns quizList Array
    public ArrayList<Quiz> getListQuizzes() {
        return this.quizList;
    }
}