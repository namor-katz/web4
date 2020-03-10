package servlet;

import model.Car;
import service.CarService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/producer")
public class ProducerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String brand = req.getParameter("brand");
        String licensePlate = req.getParameter("licensePlate");
        String model = req.getParameter("model");
        Long price = Long.parseLong(req.getParameter("price"));

        CarService carService =  CarService.getInstance();
        try {
            long successAdd = carService.addCar(brand, model, licensePlate, price);
            if(successAdd != 0) {
                resp.setStatus(HttpServletResponse.SC_OK);
            }
            else {
                resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        }
        catch (SQLException e) {
            System.out.println("в ПРОДУЦЕРсервлет произошло ебучее исключение!");
            e.printStackTrace();
        }

        resp.setContentType("text/html; charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
