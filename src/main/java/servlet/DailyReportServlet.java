package servlet;

import com.google.gson.Gson;
import model.DailyReport;
import service.DailyReportService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/report")
public class DailyReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo().contains("all")) {
            DailyReportService.getInstance().getAllDailyReports();
        }
        else if (req.getPathInfo().contains("last")) {
            String json = new Gson().toJson(DailyReportService.getInstance().getLastReport());
            System.out.println(json + " я ЕСТЬ жейсон!");

            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print(json);
            out.flush();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doDelete(req, resp); //это всё же удаляет
        DailyReportService.getInstance().deleteAllData("DELETE FROM Car");
        DailyReportService.getInstance().deleteAllData("DELETE FROM DailyReport");
        resp.setContentType("text/html; charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
