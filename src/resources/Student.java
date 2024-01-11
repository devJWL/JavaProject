package resources;

import exception.NoDataException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Student {
    private String name;
    private int id;
    private String status;
    private final List<Subject> allSubject = new ArrayList<>();
    private final List<Subject> mandatorySubject = new ArrayList<>();
    private final List<Subject> choiceSubject = new ArrayList<>();

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        this.status = "Green";
    }
    public Student(String name, int id, String status) {
        this(name,id);
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

    public List<Subject> getAllSubject() {
        return Stream.concat(getMandatorySubject().stream(), getChoiceSubject().stream())
                .collect(Collectors.toList());
    }

    public List<Subject> getMandatorySubject() {
        return mandatorySubject;
    }

    public List<Subject> getChoiceSubject() {
        return choiceSubject;
    }

    public void setScorePerRound(Subject subject, int round, int score) throws NoDataException {
        Subject sub = getAllSubject().stream().filter(s-> Objects.equals(s.getName(), subject.getName())).toList().get(0);
        boolean isContains = getAllSubject().stream().anyMatch(s-> Objects.equals(s.getName(), subject.getName()));
        if(isContains){
            sub.setScores(round-1,score);
        }else{
            throw new NoDataException("해당하는 과목이 없습니다.");
        }
    }
}
