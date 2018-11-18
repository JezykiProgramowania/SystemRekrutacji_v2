import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class School {

    private boolean isEmpty = true;
    private String name = "";
    private Map<String, Integer> schools = new HashMap<>(); // the first argument is the school name , 2nd is the points
    //private Map<String,Integer> schoolStu = new HashMap<>(); // the first argument is the school name , 2nd is the student name
    private Map<String,String> schoolStudent = new HashMap<>();

    public Map<String, String> getSchoolStudent() {
        return schoolStudent;
    }

    public void setSchoolStudent(Map<String, String> schoolStudent) {
        this.schoolStudent = schoolStudent;
    }

    public Map<String, Integer> getSchools() { // the first argument is the school, second points
        return schools;
    }

    public void setSchools(Map<String, Integer> schools) {
        this.schools = schools;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
