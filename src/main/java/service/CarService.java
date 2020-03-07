package service;

import DAO.CarDao;
import model.Car;
import model.DailyReport;
//import org.h2.message.DbException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import util.DBHelper;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CarService {

    private static CarService carService;

    private CarDao carDao;

    private CarService() {
        carDao = new CarDao();
    }

    public static CarService getInstance() {
        if (carService == null) {
            carService = new CarService();
        }
        return carService;
    }

    public long addCar(String brand, String model, String licensePlate, long price) throws SQLException {
        long result;
        //если больше 10 то не добавлять!
        Car car = new Car(brand, model,licensePlate, price);
        result = carDao.addCar(car);
        return result;
    }

    public Car getCarById(Long id) {
        Car car = carDao.getCarById(id);
        return car;
    }

    public long ifCarExist(String brand, String model, String licensePlate) {
        //может, получать id?
        List ifCar = carDao.ifCarExist(brand, model, licensePlate);
        if (ifCar.size() > 0) {
            Car car = (Car)ifCar.get(0);
            long index = car.getId();
            return index;
        }
        else return 0;
    }

    public void deleteCar(long id) throws SQLException {
        carDao.deleteCarById(id);
    }

    public boolean BuyCar(String brand, String model, String licensePlate) throws SQLException {
        //вот тут я вижу, можно вообще ничего не возвращать. ибо оно просто проверяет потом getAllCars
        long index;
        index = ifCarExist(brand, model, licensePlate);
        if(index == 0) {
            return false;
        }
        else {
            Car car = getCarById(index);
            Long price = car.getPrice();
            try {
                DailyReportService dailyReportService = DailyReportService.getInstance();
            dailyReportService.addValueInTable(price);
            } catch (Exception e) {
                System.out.println("сорян, не работает");
            }
            deleteCar(index);
            return true;
        }
    }

    public List<Car> getAllCars() {
        List allCarList = new LinkedList();
        allCarList = carDao.getAllCars();
        return allCarList;
    }
}
