package service;

import DAO.CarDao;
import DAO.DailyReportDao;
import model.DailyReport;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

public class DailyReportService {

    private static DailyReportService dailyReportService;

//    private SessionFactory sessionFactory;

    private DailyReportDao dailyReportDao;

    private DailyReportService() {
        dailyReportDao = new DailyReportDao();
    }

    public static DailyReportService getInstance() {
        if (dailyReportService == null) {
            dailyReportService = new DailyReportService();
        }
        return dailyReportService;
    }

    public List<DailyReport> getAllDailyReports() {
        return dailyReportDao.getAllDailyReport();
    }

    public void addSoldData(long price) {
        //количество_машин++ сумма_продаж = сумма_продаж + price
    }

    public void addValueInTable(long price) {
        //добавляет после первой продажи, видимо, оно же и создаёт таблицу.
//        long result;
        DailyReport dailyReport = new DailyReport(1L, price);
        dailyReportDao.addValueInTable(price);
    }

    public void editValueTable(long price) {
        //это продажи последующих машин с модификацией единственной строки в бд
        dailyReportDao.editValueTable(price);
    }

    public DailyReport getLastReport() {
        List<DailyReport> dailyReports;
        DailyReport dailyReport;
        dailyReports = dailyReportDao.getAllDailyReport();
        dailyReport = dailyReports.get(0);
        return dailyReport;
    }
}
