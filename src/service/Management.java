package service;

import resources.Student;
import resources.Subject;
import java.util.ArrayList;
import java.util.Scanner;

public class Management {
    private  ArrayList<Student> student = new ArrayList<Student>();
    private  ArrayList<Subject> subject = new ArrayList<Subject>();
    private static Scanner sc = new Scanner(System.in);

    public void initData(){
        Subject java = new Subject("Java", true);
        Subject oop = new Subject("객체지향", true);
        Subject spring = new Subject("Spring", true);
        Subject jpa = new Subject("JPA", true);
        Subject mysql = new Subject("MySQL", true);
        Subject designPattern = new Subject("디자인 패턴", false);
        Subject springSecurity = new Subject("Spring Security", false);
        Subject redis = new Subject("Redis", false);
        Subject mongodb = new Subject("MongoDB", false);

        subject.add(java);
        subject.add(oop);
        subject.add(spring);
        subject.add(jpa);
        subject.add(mysql);
        subject.add(designPattern);
        subject.add(springSecurity);
        subject.add(redis);
        subject.add(mongodb);
    }

    public void mainMenu(){
        boolean flag = true;
        while (flag) {
            printHeader();
            System.out.println("관리자 메뉴에 오신 것을 환영합니다.\n");
            System.out.println("아래 메뉴 중 하나를 선택해주세요.");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.println("==================================");
            int input = sc.nextInt();
            switch (input) {
                case 1 -> studentMenu(); // 수강생 관리
                case 2 -> scoreMenu(); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                }
            }
        }
    }

    public void studentMenu(){
        boolean flag = true;
        while (flag) {
            printHeader();
            System.out.println("수강생 관리 메뉴 입니다.");
            System.out.println("아래 메뉴 중 하나를 선택해주세요.");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 삭제");
            System.out.println("3. 수강생 목록 조회");
            System.out.println("4. 메인 화면 이동");
            System.out.println("==================================");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> deleteStudent(); // 수강생 삭제
                case 3 -> inquireStudent(); // 수강생 목록 조회
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    public void createStudent(){}
    public void deleteStudent(){}
    public void inquireStudent(){}


    public void scoreMenu(){
        boolean flag = true;
        while (flag) {
            printHeader();
            System.out.println("점수 관리 메뉴 입니다.");
            System.out.println("1. 수강생 점수 등록");
            System.out.println("2. 수강생 점수 수정");
            System.out.println("3. 수강생 성적 조회");
            System.out.println("4. 메인 화면 이동");
            System.out.println("==================================");
            int input = sc.nextInt();


            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateScore(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireScore(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    public void createScore(){}
    public void updateScore(){
        while(true) {
            printHeader();
            System.out.println("수강생 점수 수정 페이지입니다.");
            System.out.println("수강생의 고유 번호를 입력해주세요.");

            // 학생 찾기
            Student foundStudent;
            while (true) {
                foundStudent = null;
                int id = sc.nextInt();
                for (Student s : student) {
                    if (s.getId() == id) {
                        foundStudent = s;
                    }
                }
                if (foundStudent != null) break;
                System.out.println("존재하지 않는 고유번호입니다. 다시 입력해주세요.");
            }
            // 학생 찾기 완료


            // 과목 찾기
            Subject foundSubject = null;
            int type;  // 선택, 필수 여부
            System.out.println("과목을 입력해주세요");

            while (true) {
                type = 0;
                String subjectName = sc.next();
                for (Subject sub : foundStudent.getMandatorySubject()) {
                    if (subjectName.equals(sub.getName())) {
                        foundSubject = sub;
                        type = 1;
                    }
                }
                if (type != 0) break;

                for (Subject sub : foundStudent.getChoiceSubject()) {
                    if (subjectName.equals(sub.getName())) {
                        foundSubject = sub;
                        type = 2;
                    }
                }
                if (type != 0) break;
                System.out.println("존재하지 않는 과목입니다. 다시 입력해주세요.");
            }
            // 과목 찾기 완료


            // 회차 찾기
            int foundRound;
            System.out.println("회차를 입력해주세요");

            while (true) {
                String input = sc.next();
                // 숫자 아닌 경우 예외처리
                if (isDigit(input)) {
                    System.out.println("숫자값을 입력해주세요");
                    continue;
                }
                int round = Integer.parseInt(input);
                if (round < 1 || round > 10) {
                    System.out.println("회차는 1 ~ 10회차 사이입니다. 다시 입력해주세요.");
                    continue;
                }
                int[] scores = foundSubject.getScores();
                if (scores[round] == -1) {
                    System.out.println("아직 점수가 등록되지 않은 회차입니다.");
                    continue;
                }
                foundRound = round;
                break;
            }
            // 회차 찾기 완료

            // 점수 입력
            int score;
            System.out.println("점수를 입력해주세요.");
            while (true) {
                String input = sc.next();
                if (isDigit(input)) {
                    System.out.println("숫자값을 입력해주세요");
                    continue;
                }
                score = Integer.parseInt(input);
                if (score < 0 || score > 100) {
                    System.out.println("점수는 0 ~ 100점 사이입니다. 다시 입력해주세요.");
                    continue;
                }
                break;
            }
            // 점수 입력 완료
            System.out.println("해당 점수 정보를 계속 수정하시겠습니까?");
            System.out.printf("기존 정보 : %s 학생 | %d | %s | %d | %d\n", foundStudent.getName(), foundStudent.getId(), foundSubject.getName(),
                                                                    foundRound, foundSubject.getScores()[foundRound]);
            System.out.printf("기존 정보 : %s 학생 | %d | %s | %d | %d\n", foundStudent.getName(), foundStudent.getId(), foundSubject.getName(), foundRound, score);
            int sel = sc.nextInt();
            if (sel == 1) {
                foundSubject.getScores()[foundRound] = score;
                foundSubject.getGrades()[foundRound] = calcGrade(score, foundSubject.isMandatory());
                break;
            }
            sel = sc.nextInt();
            if (sel == 1) {
                continue;
            }
            break;
        }
    }

    public void inquireScore() {
        while (true) {
            printHeader();
            System.out.println("수강생 점수 수정 페이지입니다.");
            System.out.println("수강생의 고유 번호를 입력해주세요.");

            // 학생 찾기
            Student foundStudent;
            while (true) {
                foundStudent = null;
                int id = sc.nextInt();
                for (Student s : student) {
                    if (s.getId() == id) {
                        foundStudent = s;
                    }
                }
                if (foundStudent != null) break;
                System.out.println("존재하지 않는 고유번호입니다. 다시 입력해주세요.");
            }
            // 학생 찾기 완료


            // 과목 찾기
            Subject foundSubject = null;
            int type;  // 선택, 필수 여부
            System.out.println("과목을 입력해주세요");

            while (true) {
                type = 0;
                String subjectName = sc.next();
                for (Subject sub : foundStudent.getMandatorySubject()) {
                    if (subjectName.equals(sub.getName())) {
                        foundSubject = sub;
                        type = 1;
                    }
                }
                if (type != 0) break;

                for (Subject sub : foundStudent.getChoiceSubject()) {
                    if (subjectName.equals(sub.getName())) {
                        foundSubject = sub;
                        type = 2;
                    }
                }
                if (type != 0) break;
                System.out.println("존재하지 않는 과목입니다. 다시 입력해주세요.");
            }
            // 과목 찾기 완료

            System.out.printf("%s 학생의 %s 과목 회차별 등급\n", foundStudent.getName(), foundSubject.getName());
            int[] scores = foundSubject.getScores();
            char[] grades = foundSubject.getGrades();
            System.out.print("회차 ");
            for (int i = 0; i < 10; ++i) {
                if (scores[i] == -1) break;
                System.out.printf("| %2d회차", i + 1);
            }
            System.out.println();

            System.out.print("점수 ");
            for (int i = 0; i < 10; ++i) {
                if (scores[i] == -1) break;
                System.out.printf("| %4d", scores[i]);
            }
            System.out.println();

            System.out.print("등급 ");
            for (int i = 0; i < 10; ++i) {
                if (scores[i] == -1) break;
                System.out.printf("| %4c", grades[i]);
            }
        }
    }

    private char calcGrade(int score, boolean mandatory) {
        if (mandatory) {
            if (score >= 95) return 'A';
            else if (score >= 90) return 'B';
            else if (score >= 80) return 'C';
            else if (score >= 70) return 'D';
            else if (score >= 60) return 'F';
            else return 'N';
        }
        if (score >= 90) return 'A';
        else if (score >= 80) return 'B';
        else if (score >= 70) return 'C';
        else if (score >= 60) return 'D';
        else if (score >= 50) return 'F';
        else return 'N';
    }

    private boolean isDigit(String number) {
        char[] chars = number.toCharArray();
        for (char ch : chars) {
            if (ch < '0' || ch > '9') return true;
        }
        return false;
    }

    public void printHeader(){
        System.out.println("==================================");
        System.out.println("내일배움캠프 스프링 수강생 관리 프로그램");
        System.out.println("==================================\n");
    }
}
