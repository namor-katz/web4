import util.DBHelper;

public class Main {

    public static void main(String[] args) {
        System.out.println("старт Майн");
        DBHelper db = new DBHelper();
        db.printConnectInfo();
    }
}
