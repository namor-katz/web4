package DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.Car;

import java.awt.*;
import java.sql.PreparedStatement;
import java.util.List;

public class CarDao {

    private Session session;

    public CarDao(Session session) {
        this.session = session;
    }

    public Car getCarById(int id) throws HibernateException {
        return (Car) session.get(Car.class, id);
    }

    public long addCar(Car car) throws HeadlessException {
            return (Long) session.save(car);
    }
/*
    public List<Car> getAllCars() {
        String sql = "SELECT * FROM car";
//        PreparedStatement preparedStatement =
    }
*/

}
