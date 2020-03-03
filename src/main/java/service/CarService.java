package service;

import DAO.CarDao;
import model.Car;
import model.DailyReport;
import org.hibernate.SessionFactory;
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

    public List<Car> getAllCars() {
        List allCarList = new LinkedList();
//        CarDao dao = getCarClientDAO();
        return allCarList;
    }
/*
    private static CarDao getCarClientDAO() {
        return new CarDao(getMysqlConnection());
    }
*/

}
