package resources;

public class Student {
    private String studentName;
    private int studentId;
    private String studentStatus;
    public Student(String name, int id, String status){
        this.studentName = name;
        this.studentId = id;
        this.studentStatus = status;
    }

    // Getter
    public String getStudentName(){
        return studentName;
    }

    public int getStudentId(){
        return studentId;
    }

    public String getStudentStatus(){
        return studentStatus;
    }
    public void addSubject(Subject subject){

    }
}
