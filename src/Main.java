import exception.NoDataException;
import service.Management;

public class Main {
    public static void main(String[] args) throws Exception {
        try{
            Management management = new Management();
            management.initData();
            management.mainMenu();
        }catch (NoDataException no){
            System.out.println(no.getMessage());
        }

    }
}