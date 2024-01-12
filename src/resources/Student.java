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

    // 필수과목 + 선택과목 합한 모든 과목 리스트로 가져오기
    public List<Subject> getAllSubject() {
        return Stream.concat(getMandatorySubject().stream(), getChoiceSubject().stream())
                .collect(Collectors.toList());
    }

    // 필수과목 리스트로 가져오기
    public List<Subject> getMandatorySubject() {
        return mandatorySubject;
    }

    // 선택과목 리스트로 가져오기
    public List<Subject> getChoiceSubject() {
        return choiceSubject;
    }

    // 해당 과목 회차당 점수 설정 (수강 과목이 아닐 경우 예외 처리 완료)
    public void setScorePerRound(Subject subject, int round, int score) throws NoDataException {
        if(containSubject(subject.getName())){
            Subject sub = getAllSubject().stream().filter(s-> Objects.equals(s.getName(), subject.getName())).toList().get(0);
            sub.setScores(round-1,score);
        }else{
            throw new NoDataException("해당하는 과목이 없습니다.");
        }
    }

    // 해당 학생의 수강 과목이 맞는지 여부 반환
    public boolean containSubject(String subName){
        return getAllSubject().stream().anyMatch(s-> Objects.equals(s.getName(), subName));
    }

    // 해당 과목 회차당 점수가 이미 있는지 여부 반환
    public boolean existScorePerRound(String subName, int round) throws NoDataException {
        if(containSubject(subName)){
            Subject sub = getAllSubject().stream().filter(s-> Objects.equals(s.getName(), subName)).toList().get(0);
            return sub.getScores()[round - 1] != -1;
        }
        else{
            throw new NoDataException("해당하는 과목이 없습니다.");
        }
    }
}
