import org.h2.message.DbException;
import service.CarService;
import util.DBHelper;

import java.sql.SQLDataException;

public class Main {

    public static void main(String[] args) {
        System.out.println("старт Майн");
        CarService carService = CarService.getInstance();
//        db.printConnectInfo();

        try {
            long id = carService.addCar("Автотаз", "ОКА", "Ч777ЦУ", 20000);
        } catch (DbException e) {
            System.out.println("что то пошло не так");
        }
    }
}
