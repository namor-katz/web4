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
        //где то тут надо отдавать 403. если машина не принята
        //вообще тут мы добавляем машины. в 1 тесте 10 штук.
        String brand = req.getParameter("brand");
        String licensePlate = req.getParameter("licensePlate");
        String model = req.getParameter("model");
        Long price = Long.parseLong(req.getParameter("price"));

        CarService carService =  CarService.getInstance();
        try {
            carService.addCar(brand, model, licensePlate, price);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.setContentType("text/html; charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
