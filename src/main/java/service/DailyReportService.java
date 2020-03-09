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

    public boolean itFirstSale() {
        int result = dailyReportDao.getCountRows();
        if (result > 0) {
            return false; //не первая
        }
        else return true; //первая
    }

    public void addValueInTable(long price) {
//        long result;
//        long TableContent = dailyReportDao.getCountSaleCars();
//        if(TableContent == 0) { //если пуста вызываем эдд
//            DailyReport dailyReport = new DailyReport(price, 1L);
            dailyReportDao.addValueInTable(price);
//        }
//        else {  //если нет Едит
//            dailyReportDao.editValueTable(price);
//        }
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
