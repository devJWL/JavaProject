package resources;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private int id;
    private String status;
    private final List<Subject> mandatorySubject = new ArrayList<>();
    private final List<Subject> choiceSubject = new ArrayList<>();

    public Student(String name, int id, String status){
        this.name = name;
        this.id = id;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Subject> getMandatorySubject() {
        return mandatorySubject;
    }

    public List<Subject> getChoiceSubject() {
        return choiceSubject;
    }

    public void addSubject(Subject subject){
        if(subject.isMandatory())
            mandatorySubject.add(subject);
        else
            choiceSubject.add(subject);
    }

    public void show(){
        String mandatory = "";
        String choice = "";
        for(Subject mansub : mandatorySubject ) {
            mandatory += mansub.getName();
            mandatory += " ";
        }
        for(Subject chosub : mandatorySubject ) {
            choice += chosub.getName();
            choice += " ";
        }
        System.out.printf("%s 학생 | %-10d | 필수 과목 : %-20s | 선택 과목 : %-20s | 상태 : %-5s\n", name, id, mandatory, choice, status);
    }
}
