import java.io.*;
import java.util.ArrayList;

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
    public Teacher(String userName, String password, String name, String teacherListFileName) {
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
            br.close();
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

    //Getter for name
    public String getName() {
        return this.name;
    }

    //Getter for password
    public String getPassword() {
        return this.password;
    }

    //Getter for userName
    public String getUserName() {
        return this.userName;
    }

    //Getter for ArrayList that stores all of the courses objects
    public ArrayList<Courses> getListCourses() {
        return this.listCourses;
    }

    //Method that adds a course object to the courseList array
    public boolean createCourse(String courseName, String courseListFileName) throws IOException {
        this.listCourses.add(new Courses(courseName, courseListFileName));
        return true;
    }

    //Checks if courseName is inside of the courseListFile
    //Returns true if courseName is inside of the courseListFile
    //Returns false if courseName is not inside of the courseListFile
    public boolean enterCourse(String courseName, String courseListFileName) throws IOException {
        File f = new File(courseListFileName);
        BufferedReader br = new BufferedReader(new FileReader(f));
        String str = br.readLine();
        if (str == null || str.equals("") || str.equals(" ")) {
            br.close();
            return false;
        } else {
            int c = 0;
            while (str != null) {
                if (str.equals(courseName)) {
                    c++;
                }
                str = br.readLine();
            }
            if (c == 0) {
                br.close();
                return false;
            } else {
                br.close();
                this.listCourses.add(new Courses(courseName));
                return true;
            }
        }
    }
}
