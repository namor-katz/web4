import model.Car;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.h2.message.DbException;
import service.CarService;
import servlet.CustomerServlet;
import util.DBHelper;

import java.sql.SQLDataException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception{
        CustomerServlet customerServlet = new CustomerServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(customerServlet), "/customer");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        server.join();
/*
        System.out.println("старт Майн");
        CarService carService = CarService.getInstance();
//        db.printConnectInfo();
        try {
            long id = carService.addCar("Автотаз", "ОКА", "Ч777ЦУ", 20000);
            long id2 = carService.addCar("ЗАЗ", "ЗАЗ968М", "Р777РР", 5000);
            List<Car> fpt = carService.getAllCars();
            System.out.println("сейчас я распечатаю таблицу!");
            for (Car i: fpt) {
                System.out.println(i.getBrand() + " " +  i.getModel());
            }

        } catch (DbException e) {
            System.out.println("что то пошло не так");
        }*/
    }
}
