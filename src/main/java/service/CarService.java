package service;

import DAO.CarDao;
import model.Car;
import model.DailyReport;
import org.h2.message.DbException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import util.DBHelper;

import java.util.LinkedList;
import java.util.List;

public class CarService {

    private static CarService carService;

    private SessionFactory sessionFactory;

    private CarService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static CarService getInstance() {
        if (carService == null) {
            carService = new CarService(DBHelper.getSessionFactory());
        }
        return carService;
    }
/*
    public Car getCarByID(int id) {
        CarDao dao = new CarDao.sess;
    }
*/
    public long addCar(String brand, String model, String licenseplat, long price) throws DbException {
//        Car car = new Car(brand, model, licenseplat, price);
        long result;
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            CarDao dao = new CarDao(session);
            Car car = new Car(brand, model,licenseplat, price);
            result = dao.addCar(car);
            transaction.commit();
            session.close();
            return result;
    }
/*
    public List<Car> getAllCars() {
        List allCarList = new LinkedList();
        CarDao dao = getCarClientDAO();
        return allCarList;
    }

    private static CarDao getCarClientDAO() {
        return new CarDao(getMysqlConnection());
    }
*/
}
