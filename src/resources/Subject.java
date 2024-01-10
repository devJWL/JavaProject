package resources;

public class Subject {
    private String subjectName;
    private int[] subjectScore = new int[10];
    private int subjectAverage;
    private char[] subjectGrade = new char[10];
    private boolean isMandatory;

    public Subject(String name, boolean isMandatory){
        this.subjectName = name;
        this.isMandatory = isMandatory;
    }

    // Getter
    public String getSubjectName(){
        return subjectName;
    }
    public int getSubjectScore(int index){
        return subjectScore[index];
    }
    public char getSubjectGrade(int index){
        return subjectGrade[index];
    }
    public boolean getIsMandatory(){
        return isMandatory;
    }
    //public int getSubjectAverage(){}

}
