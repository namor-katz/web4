package service;

import DAO.CarDao;
import model.Car;
import model.DailyReport;
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
        //принять данные машины, если этого бренда менее 10, добавить, вернуть число. если 10 и более вернуть 0
        long result;
        int CountCarsOneBrand = getCountCarEqualBrand(brand);
        if(CountCarsOneBrand < 10) {
            result = carDao.addCar(new Car(brand, model, licensePlate, price));
            return result;
        }
        else {
            System.out.println("машин этого бренда более 10. не добавляю.");
            return 0L;
        }
    }

    public int getCountCarEqualBrand(String brand) {
        int countCar = carDao.getCountCarEqualBrand(brand);
        return countCar;
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
            System.out.println("цена купленного авто = " + price);

            try {
                DailyReportService dailyReportService = DailyReportService.getInstance();
                boolean isReportFirst = dailyReportService.itFirstSale();//если первая - вставляем первую строку.
                if(isReportFirst == true) {
                    dailyReportService.addValueInTable(price);//количество проданных тут константа.
                }
                else {
                    dailyReportService.editValueTable(price);//обновляем сумму продаж и количество проданных
                }
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
