package DAO;

import org.hibernate.*;
import model.Car;
import util.DBHelper;

import java.awt.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CarDao {

    private SessionFactory sessionFactory;

    public CarDao() {
        this.sessionFactory = DBHelper.getSessionFactory();
    }

    public Car getCarById(long id) throws HibernateException {
        Session session = sessionFactory.openSession();
        return (Car) session.get(Car.class, id);
    }

    public int deleteCarById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query q = session.createQuery("delete Car where id = :id");
        q.setParameter("id", id);
        int i = q.executeUpdate();
        transaction.commit();
        session.close();
        return i;
    }

    public List<Car> ifCarExist(String brand, String model, String licensePlate) throws HibernateException {
        List ifCar = new ArrayList();
        String hql = "FROM Car WHERE brand = :brand AND model = :model AND licensePlate = :licensePlate";
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        query.setParameter("brand", brand);
        query.setParameter("model", model);
        query.setParameter("licensePlate", licensePlate);
        ifCar = query.list();
        return ifCar;
    }
/*
    public int buyCar(String brand, String model, String licensePlate) {

        query.l


    }
*/
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
        transaction.commit();
        session.close();
        return AllCars;
    }

}
