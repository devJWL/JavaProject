package service;

import resources.Student;
import resources.Subject;
import java.util.ArrayList;
import java.util.Arrays;
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
        Subject designPattern = new Subject("디자인패턴", false);
        Subject springSecurity = new Subject("SpringSecurity", false);
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
            System.out.printf("현재 수강생 : %d 명\n",student.size());
            System.out.println("수강생 관리 메뉴 입니다.");
            System.out.println("아래 메뉴 중 하나를 선택해주세요.");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 수정");
            System.out.println("3. 수강생 삭제");
            System.out.println("4. 수강생 목록 조회");
            System.out.println("5. 메인 화면 이동");
            System.out.println("==================================");
            int input = sc.nextInt();
            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> changeStudent(); // 수강생 수정
                case 3 -> deleteStudent(); // 수강생 삭제
                case 4 -> inquireStudent(); // 수강생 목록 조회
                case 5 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    public void createStudent(){
        int idInput;
        int yesno;
        Boolean flag;
        String nameInput;
        String subjectInput;
        String statusInput;

        do {
            printHeader();
            flag = true;
            System.out.println("수강생 정보 등록 메뉴 입니다.\n");
            System.out.println("등록할 수강생의 고유번호를 입력해주세요");
            idInput = sc.nextInt();
            for (Student students : student)
                if (students.getId() == idInput) {
                    flag = false;
                    System.out.println("고유번호가 중복되었습니다. 다시 입력해주세요.");
                    break;
                }
        }while(!flag);
        sc.nextLine();
            System.out.println("\n등록할 수강생의 이름을 입력해주세요");
            nameInput = sc.nextLine();
            System.out.println("\n등록할 수강생의 상태를 입력해주세요 (GREEN RED YELLOW)");
            statusInput = sc.nextLine();
            do {
                System.out.println("\n필수 과목 목록 : Java 객체지향 Spring JPA MySQL");
                System.out.println("등록할 수강생의 필수 과목 목록을 입력해주세요 ( 띄어쓰기로 구분해주세요 )");
                subjectInput = sc.nextLine();
                if(subjectInput.split(" ").length<3) {
                    System.out.println("필수과목이 3개 미만입니다. 다시 입력해주세요.");
                    continue;
                }
                for (String sub : subjectInput.split(" ")){
                    flag = false;
                    for (Subject subjects : subject) {
                        if(!subjects.isMandatory())
                            continue;
                        if (sub.equals(subjects.getName()))
                            flag = true;
                    }
                }
                if(!flag)
                    System.out.println("불가능한 과목명이 포함되어있습니다. 다시 입력해주세요.");
            }while(!(subjectInput.split(" ").length>=3&&flag));
            String[] mandatory = subjectInput.split(" ");

        do {
            System.out.println("\n선택 과목 목록 : 디자인패턴 SpringSecurity Redis MongoDB");
            System.out.println("등록할 수강생의 선택 과목 목록을 입력해주세요 ( 띄어쓰기로 구분해주세요)");
            subjectInput = sc.nextLine();
            if(subjectInput.split(" ").length<2) {
                System.out.println("필수과목이 2개 미만입니다. 다시 입력해주세요.");
                continue;
            }
            for (String sub : subjectInput.split(" ")){
                flag = false;
                for (Subject subjects : subject) {
                    if(subjects.isMandatory())
                        continue;
                    if (sub.equals(subjects.getName()))
                        flag = true;
                }
            }
            if(!flag)
                System.out.println("불가능한 과목명이 포함되어있습니다. 다시 입력해주세요.");
        }while(subjectInput.split(" ").length>=3&&flag);
        String[] choice = subjectInput.split(" ");
        System.out.println("해당 수강생 정보를 계속 등록하시겠습니까?\n");
        System.out.printf("%-5s 학생 | %-10d | 필수 과목 : %-20s | 선택 과목 : %-20s | 상태 : %-5s\n",nameInput,idInput, Arrays.toString(mandatory), Arrays.toString(choice),statusInput);
        System.out.println("1. 확인");
        System.out.println("2. 다시 입력");
        yesno = sc.nextInt();
        if(yesno==1){
            Student studentInput = new Student(nameInput,idInput,statusInput);
            for(String sub : mandatory) {
                for (Subject subjects : subject)
                    if (sub.equals(subjects.getName()))
                        studentInput.addSubject(subjects);
            }
            for(String sub : choice) {
                for (Subject subjects : subject)
                    if (sub.equals(subjects.getName()))
                        studentInput.addSubject(subjects);
            }
            student.add(studentInput);
            System.out.println("수강생 정보가 등록되었습니다.");
        }
    }
    public void deleteStudent(){
        int idInput;
        int yesno;
        boolean flag = false;
            printHeader();
            System.out.println("수강생 삭제 메뉴 입니다.\n");
            System.out.println("삭제할 수강생의 고유번호를 입력해주세요");
            idInput = sc.nextInt();
            for (Student students : student)
                if (students.getId() == idInput)
                    flag = true;
            if(!flag)
                System.out.println("잘못된 고유번호입니다.");
            else{
                for (Student students : student)
                    if (students.getId() == idInput){
                        students.show();
                        System.out.println("해당 수강생 정보를 삭제하시겠습니까?\n");
                        System.out.println("1. 네");
                        System.out.println("2. 아니오");
                        yesno = sc.nextInt();
                        if(yesno==1){
                            student.remove(students);
                            System.out.println("수강생 정보가 삭제되었습니다.");
                        }
                        break;
                    }
            }


    }
    public void changeStudent(){
        int idInput;
        int yesno;
        String nameInput;
        String statusInput;
        boolean flag = false;
        printHeader();
        System.out.println("수강생 수정 메뉴 입니다.\n");
        System.out.println("수정할 수강생의 고유번호를 입력해주세요");
        idInput = sc.nextInt();
        for (Student students : student)
            if (students.getId() == idInput)
                flag = true;
        if(!flag)
            System.out.println("잘못된 고유번호입니다.");
        else{
            for (Student students : student)
                if (students.getId() == idInput){
                    students.show();
                    sc.nextLine();
                    System.out.println("\n수강생의 새로운 이름을 입력해주세요");
                    nameInput = sc.nextLine();
                    System.out.println("\n수강생의 새로운 상태를 입력해주세요 (GREEN RED YELLOW)");
                    statusInput = sc.nextLine();
                    System.out.printf("변경전 : %-5s 학생 | %-5s 상태\n", students.getName(), students.getStatus());
                    System.out.printf("변경후 : %-5s 학생 | %-5s 상태\n", nameInput, statusInput);
                    System.out.println("해당 수강생 정보를 변경하시겠습니까?\n");
                    System.out.println("1. 네");
                    System.out.println("2. 아니오");
                    yesno = sc.nextInt();
                    if(yesno==1){
                        students.setName(nameInput);
                        students.setStatus(statusInput);
                        System.out.println("변경되었습니다.");
                    }
                }
        }
    }

    public void inquireStudent() {
        int idInput;
        int yesno;
        String status;
        boolean flag = false;
        do {
            printHeader();
            System.out.println("\n수강생 목록 조회 메뉴 입니다.");
            System.out.println("아래 메뉴 중 하나를 선택해주세요.\n");
            System.out.println("1. 수강생 조회 (고유번호)");
            System.out.println("2. 수강생 조회 (상태)");
            System.out.println("3. 뒤로가기");
            yesno = sc.nextInt();
        } while (!(yesno > 0 && yesno < 4));
        if (yesno == 1) {
            System.out.println("조회할 수강생의 고유번호를 입력해주세요.");
            idInput = sc.nextInt();
            for (Student students : student)
                if (students.getId() == idInput)
                    flag = true;
            if (!flag)
                System.out.println("잘못된 고유번호입니다.");
            else
                for (Student students : student)
                    if (students.getId() == idInput)
                        students.show();
        }
        else if (yesno == 2) {
            System.out.println("조회할 상태를 입력해주세요. (GREEN RED YELLOW)");
            sc.nextLine();
            status = sc.nextLine();
            System.out.printf("%-5s 상태인 수강생 목록\n",status);
            for(Student stu : student)
                if(stu.getStatus().equals(status))
                    stu.show();
        }
        else {
        }
    }


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
    public void updateScore(){}
    public void inquireScore(){}

    public void printHeader(){
        System.out.println("==================================");
        System.out.println("내일배움캠프 스프링 수강생 관리 프로그램");
        System.out.println("==================================\n");
    }
}
