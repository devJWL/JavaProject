package service;

import exception.BadInputException;
import exception.ExistDataException;
import exception.NoDataException;
import resources.Student;
import resources.Subject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Management {
    private  ArrayList<Student> studentList = new ArrayList<Student>();
    private  ArrayList<Subject> subjectList = new ArrayList<Subject>();
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
        Collections.addAll(subjectList,java,oop,spring,jpa,mysql,designPattern,springSecurity,redis,mongodb);
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

    // 수강생의 과목별 시험 회차별 점수 등록
    public void createScore() throws Exception {
        printHeader();
//        studentList.add(new Student("yura",1)); //Test

        System.out.println("수강생 점수 등록 페이지 입니다.");
        System.out.println("수강생의 고유 번호는 입력해주세요.");
        int id = sc.nextInt();
        // 고유 번호가 존재하지 않는 경우 : 존재하지 않는 고유번호입니다. 다시 입력해주세요.
        Student std = getStudentById(id);
//        std.getMandatorySubject().add(new Subject("Java",true)); //Test

        System.out.println("과목을 입력해주세요.");
        String sub = sc.next();
        // 과목이 존재하지 않는 경우 : 존재하지 않는 과목입니다. 다시 입력해주세요.
        if(!std.containSubject(sub)) throw new NoDataException("과목");

        System.out.println("회차를 입력해주세요.");
        int round = sc.nextInt();
        // 입력이 10초과 및 1 미만인 경우 : 회차는 1~10회차 사이입니다. 다시 입력해주세요.
        if(round>10 || round<1) throw new BadInputException("round range");
        // 회차 점수가 이미 등록되어 있을 경우 : 해당 회차 점수는 이미 등록되어 있습니다. 다시 입력해주세요.
        if(std.existScorePerRound(sub,round)) throw new ExistDataException("해당 회차 점수");

        System.out.println("점수를 입력해주세요.");
        int score = sc.nextInt();
        // 입력이 100 초과 및 음수인 경우 : 점수는  1~100점 사이입니다. 다시 입력해주세요.
        if(score>100 || score<0) throw new BadInputException("score range");



        Subject sbj = getSubjectByName(sub);

        System.out.println("해당 점수 정보를 계속 등록하시겠습니까?\n");
        showStudentInfo(std.getName(),id,sub,round,score);

        System.out.println("1. 확인");
        System.out.println("2. 다시 입력");
        int choice = sc.nextInt();

        if(choice == 1){
            std.setScorePerRound(sbj,round,score);
            System.out.println("점수 정보가 등록되었습니다. 다음 점수 정보를 입력하시겠습니까?\n ");

            showStudentInfo(std);

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

    // Studnet 아이디로 Studnet 객체 가져오기
    // (고유번호 없을 경우 예외 처리 완료)
    public Student getStudentById(int id) throws NoDataException {
        if(!studentList.stream().filter(s -> s.getId() == id).toList().isEmpty()){
            return studentList.stream().filter(s -> s.getId() == id).toList().get(0);
        }
        throw new NoDataException("고유번호");
    }

    // Subject 이름으로 Subject 객체 가져오기
    public Subject getSubjectByName(String sub){
        return subjectList.stream().filter(s -> s.getName().equals(sub)).toList().get(0);
    }

    // Student 내에 있는 과목별 점수 정보 출력 (Test용으로 유용)
    public void showStudentInfo(Student std){
        for(int i=0;i<std.getAllSubject().size();i++){
            for(int j=0;j<10;j++){
                showStudentInfo(std.getName(),std.getId(),std.getAllSubject().get(i).getName(),j+1,std.getAllSubject().get(i).getScores()[j]);
            }

        }
    }

    // Student 내에 정보 넣기 전에 확인용으로 출력
    public void showStudentInfo(String name,int id,String subject,int round,int score) {
        System.out.printf("%5s",name);
        System.out.printf("%5s"," | ");
        System.out.printf("%5s",id);
        System.out.printf("%5s"," | ");
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
