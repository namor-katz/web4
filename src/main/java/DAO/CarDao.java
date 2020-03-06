package DAO;

import org.hibernate.*;
import model.Car;
import util.DBHelper;

import java.awt.*;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.List;

public class CarDao {

    private SessionFactory sessionFactory;

    public CarDao() {
        this.sessionFactory = DBHelper.getSessionFactory();
    }

    public Car getCarById(int id) throws HibernateException {
        Session session = sessionFactory.openSession();
        return (Car) session.get(Car.class, id);
    }

    public long addCar(Car car) throws HeadlessException {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Long result = (Long) session.save(car);
            transaction.commit();
            session.close();
            return result;
    }

    public List<Car> getAllCars() {
        String hql = "FROM Car";
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Car> AllCars = session.createQuery(hql).list();
        System.out.println("длина лист оф кар = " + AllCars.size());
        transaction.commit();
        session.close();
        return AllCars;
    }

}
