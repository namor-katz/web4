package service;

import DAO.CarDao;
import DAO.DailyReportDao;
import model.DailyReport;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

public class DailyReportService {

    private static DailyReportService dailyReportService;

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

    public boolean itFirstSale() {
        int result = dailyReportDao.getCountRows();
        if (result > 0) {
            return false; //не первая
        }
        else return true; //первая
    }

    public void deleteAllData(String hqlFromDelete) {
        dailyReportDao.deleteAllData(hqlFromDelete);
    }

    public void addValueInTable(long price) {
            dailyReportDao.addValueInTable(price);
    }

    public void editValueTable(long price) {
        //это продажи последующих машин с модификацией единственной строки в бд
        dailyReportDao.editValueTable(price);
    }

    public void StartNewDay() {
        dailyReportDao.StartNewDay();
    }

    public DailyReport getLastReport() {
        List<DailyReport> dailyReports;
        DailyReport dailyReport;
        dailyReports = dailyReportDao.getAllDailyReport();
        int MaxId = dailyReportDao.getCountRows() - 2;
        dailyReport = dailyReports.get(MaxId);
        return dailyReport;
    }
}
