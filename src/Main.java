import exception.BadInputException;
import exception.NoDataException;
import service.Management;

import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) throws Exception {
        try{
            Management management = new Management();
            management.initData();
            management.mainMenu();
        }catch (InputMismatchException input){
            System.out.println("Error : 숫자값을 입력해주세요.");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}