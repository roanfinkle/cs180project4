import java.io.*;
import java.util.ArrayList;

/**
 * Teacher class to create new Teachers and 
 * enabke them to create and enter courses
 * Purdue University -- CS18000 -- Spring 2022 -- Project
 * 4
 *
 * @author Ritwik Jain
 * @version April 04, 2022
 * Lab sec 03
 */

public class Teacher {
    private String userName;
    private String password;
    private String name;
    private ArrayList<Courses> listCourses = new ArrayList<>();

    //Constructor for existing teacher
    //Constructs a teacher object with userName, password, name
    public Teacher(String userName, String password, String name) {
        this.userName = userName;
        this.password = password;
        this.name = name;
    }

    //Constructor for new teacher
    //Constructs a teacher object with userName, password, name
    //Adds teacher name, userName, and password to teacherListFile
    public Teacher(String userName, String password,
                   String name, String teacherListFileName) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        try {
            File f = new File(teacherListFileName);
            f.createNewFile();
            BufferedReader br = new BufferedReader(new FileReader(f));
            String str = br.readLine();
            String s = "";
            while (str != null) {
                s += str + "\n";
                str = br.readLine();
            }
            s += name + "$$" + userName + "$$" + password + "\n";
            PrintWriter pw = new PrintWriter(new FileWriter(f), true);
            pw.print(s);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUserName() {
        return this.userName;
    }

    public ArrayList<Courses> getListCourses() {
        return this.listCourses;
    }

    //Method that adds a course object to the courseList array
    public boolean createCourse(String courseName, String courseListFileName)
            throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(courseListFileName));
        String str = br.readLine();
        while (str != null) {
            if (str.equals(courseName)) {
                return false;
            }
            str = br.readLine();
        }
        this.listCourses.add(new Courses(courseName, courseListFileName));
        return true;
    }

    //Checks if courseName is inside of the courseListFile
    //Returns true if courseName is inside of the courseListFile
    //Returns false if courseName is not inside of the courseListFile
    public boolean enterCourse(String courseName, String courseListFileName)
            throws IOException {
        File f = new File(courseListFileName);
        BufferedReader br = new BufferedReader(new FileReader(f));
        String er = br.readLine();
        if (er == null || er.equals("") || er.equals(" ")) {
            return false;
        } else {
            int c = 0;
            while (er != null) {
                if (er.equals(courseName)) {
                    c++;
                }
                er = br.readLine();
            }
            if (c == 0) {
                return false;
            } else {
                this.listCourses.add(new Courses(courseName));
                return true;
            }
        }
    }
}
