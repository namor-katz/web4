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

//    private SessionFactory sessionFactory;

    private CarDao carDao;


    private CarService() {
//        this.sessionFactory = sessionFactory;
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
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        CarDao dao = new CarDao(session);
        Car car = new Car(brand, model,licensePlate, price);
        result = carDao.addCar(car);
  //      transaction.commit();
   //     session.close();
        return result;
    }

    public List<Car> getAllCars() {
        List allCarList = new LinkedList();
 //       Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        CarDao dao = new CarDao(ses);
        allCarList = carDao.getAllCars();

        return allCarList;
    }
/*
    private static CarDao getCarClientDAO() {
        return new CarDao(getMysqlConnection());
    }
*/
}
