package resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subject {
    private String name;
    private final int[] scores = new int[10];
    private final char[] grades = new char[10];
    private boolean isMandatory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getScores() {
        return scores;
    }

    public char[] getGrades() {
        return grades;
    }

    public void setScores(int pos, int score){
        scores[pos] = score;
        if(isMandatory){
            if(score>=95) grades[pos] = 'A';
            else if(score>=90) grades[pos] = 'B';
            else if(score>=80) grades[pos] = 'C';
            else if(score>=70) grades[pos] = 'D';
            else if(score>=60) grades[pos] = 'F';
            else grades[pos] = 'N';
        }else{
            if(score>=90) grades[pos] = 'A';
            else if(score>=80) grades[pos] = 'B';
            else if(score>=70) grades[pos] = 'C';
            else if(score>=60) grades[pos] = 'D';
            else if(score>=50) grades[pos] = 'F';
            else grades[pos] = 'N';
        }
    }

    public boolean isMandatory() {
        return isMandatory;
    }

    public void setMandatory(boolean mandatory) {
        isMandatory = mandatory;
    }

    public Subject(String name, boolean isMandatory) {
        this.name = name;
        this.isMandatory = isMandatory;
        Arrays.fill(scores, -1);
    }
}