package service;

import exception.NoDataException;
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

    public void mainMenu() throws Exception{
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

    public void createStudent(){}  // 저
    public void deleteStudent(){}  // 상엽
    public void inquireStudent(){} // 유라


    public void scoreMenu() throws Exception {
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

    public void createScore() throws Exception {
        printHeader();
        System.out.println("수강생 점수 등록 페이지 입니다.");
        System.out.println("수강생의 고유 번호는 입력해주세요.");
        int id = sc.nextInt();

        System.out.println("과목을 입력해주세요.");
        String subject = sc.next();
        System.out.println("회차를 입력해주세요.");
        int round = sc.nextInt();
        System.out.println("점수를 입력해주세요.");
        int score = sc.nextInt();

        Student std = getStudentById(id);
        std.getMandatorySubject().add(new Subject("Java",true)); //Test
        Subject sbj = getSubjectByName(subject);

        System.out.println("해당 점수 정보를 계속 등록하시겠습니까?\n");
        showStudentInfo(std.getName(),id,subject,round,score);

        System.out.println("1. 확인");
        System.out.println("2. 다시 입력");
        int choice = sc.nextInt();

        if(choice == 1){
            std.setScorePerRound(sbj,round,score);
            System.out.println("점수 정보가 등록되었습니다. 다음 점수 정보를 입력하시겠습니까?\n ");
            System.out.println("1. 네");
            System.out.println("2. 아니오");
            int next = sc.nextInt();
            if(next == 1) createScore();
            else if(next==2) mainMenu();
        }
        else if(choice == 2){
            createScore();
        }
    }

    public Student getStudentById(int id){
//        return student.stream().filter(s -> s.getId() == id).toList().get(0);
        return new Student("YURA",100);  // Test
    }
    public Subject getSubjectByName(String sub){
        return subject.stream().filter(s -> s.getName().equals(sub)).toList().get(0);
    }
    public void showStudentInfo(String name,int id,String subject,int round,int score) {
        System.out.printf("%10s",name);
        System.out.printf("%10s"," | ");
        System.out.printf("%10s",id);
        System.out.printf("%10s"," | ");
        System.out.printf("%10s",subject);
        System.out.printf("%10s"," | ");
        System.out.printf("%3s",round);
        System.out.printf("%5s"," | ");
        System.out.printf("%5s%n",score);
    }
    public void updateScore(){}
    public void inquireScore(){}

    public void printHeader(){
        System.out.println("==================================");
        System.out.println("내일배움캠프 스프링 수강생 관리 프로그램");
        System.out.println("==================================\n");
    }
}
