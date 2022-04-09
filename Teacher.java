import java.io.*;
import java.util.ArrayList;

public class Teacher {
    private String userName ;
    private String password ;
    private String name ;
    private ArrayList<Courses> listCourses= new ArrayList<>() ;
    public Teacher(String userName , String password , String name) {
        this.userName = userName ;
        this.password = password ;
        this.name = name ;
    }
    public Teacher(String userName , String password , String name , String teacherListFileName) {
        this.userName = userName ;
        this.password = password ;
        this.name = name ;
        try {
            File f = new File(teacherListFileName) ;
            f.createNewFile() ;
            BufferedReader br = new BufferedReader(new FileReader(f)) ;
            String str = br.readLine() ;
            String s = "" ;
            while(str != null) {
                s += str + "\n" ;
                str = br.readLine() ;
            }
            s += name + "$$" + userName + "$$" + password + "\n";
            PrintWriter pw = new PrintWriter(new FileWriter(f), true) ;
            pw.print(s) ;
            pw.close() ;
        } catch (FileNotFoundException e) {
            e.printStackTrace() ;
        } catch (IOException e) {
            e.printStackTrace() ;
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
        return this.listCourses ;
    }
    public boolean createCourse(String courseName , String courseListFileName) throws IOException {
        this.listCourses.add(new Courses(courseName , courseListFileName)) ;
        return true ;
    }
    public boolean enterCourse(String courseName , String courseListFileName) throws IOException {
        File f = new File(courseListFileName) ;
        BufferedReader br = new BufferedReader(new FileReader(f)) ;
        String er = br.readLine() ;
        if (er == null) {
            return false ;
        } else {
            int c = 0 ;
            while(er != null) {
                if(er.equals(courseName)) {
                    c++ ;
                }
                er = br.readLine() ;
            }
            if (c == 0) {
                return false ;
            } else {
                this.listCourses.add(new Courses(courseName)) ;
                return true ;
            }
        }
    }
}
