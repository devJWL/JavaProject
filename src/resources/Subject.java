package resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subject {
    private String name;
    private final int[] scores = new int[10];
    private final char[] grades = new char[10];
    private boolean isMandatory;

    public Subject(String name, boolean isMandatory) {
        this.name = name;
        this.isMandatory = isMandatory;
        Arrays.fill(scores, -1);
    }

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

    public boolean isMandatory() {
        return isMandatory;
    }

    public void setMandatory(boolean mandatory) {
        isMandatory = mandatory;
    }

    public char getAverage() {
        int count = 0;
        int sum = 0;
        for (int score : scores) {
            if (score == -1){
                continue;
            }
            sum += score;
            ++count;
        }
        return calcGrade(sum / count);
    }

    private char calcGrade(int score) {
        //// 처리
        return 'A';
    }
}
