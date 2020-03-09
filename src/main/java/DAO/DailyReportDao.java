package DAO;

import model.DailyReport;
import org.hibernate.*;
import org.hibernate.loader.plan.spi.QuerySpaceUidNotRegisteredException;
import util.DBHelper;
import java.awt.*;

import java.util.List;

public class DailyReportDao {

    private SessionFactory sessionFactory;

    public DailyReportDao() {
        this.sessionFactory = DBHelper.getSessionFactory();
    }

    public long addValueInTable(long price) throws HibernateException {
        long count = 1;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        DailyReport dailyReport = new DailyReport(price, count);
        Long result = (long) session.save(dailyReport);
        transaction.commit();
        session.close();
        return result;
    }

    public int editValueTable(long price) {
        long before_count = getCountSaleCars();
        before_count++;
        long before_money = getMoneySum();
        long after_sum = before_money + price;
//        System.out.println("АФТЕР сум = " + after_sum);
        String hql = "UPDATE DailyReport SET earnings = :earnings , soldCars = :soldCars";
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setParameter("earnings", after_sum);
        query.setParameter("soldCars", before_count);

        int result = query.executeUpdate();
        transaction.commit();
        session.close();
        return result;
    }

    public void StartNewDay() {
        DailyReport dailyReport = new DailyReport(0L, 0L);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(dailyReport);
        transaction.commit();
        session.close();
    }

    public int getCountRows() {
        //получаем количество строк. если 0, строк тут НЕТ. table is empty.
        List countRows;
        int result;
        String hql = "FROM DailyReport";
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        countRows = query.list();
        session.close();
        result = countRows.size();
        return result;
    }

    public long getCountSaleCars() {
        String hql = "FROM DailyReport";
        long result;
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        try {
            DailyReport dailyReport = (DailyReport) query.list().get(0);
            result = dailyReport.getSoldCars();
        }
        catch (IndexOutOfBoundsException e) {
            result = 0;
        }
        session.close();
        return result;
    }

    public long getMoneySum() {
        long result;
        String hql = "From DailyReport"; //!! неверный селект (был)
        Session session = sessionFactory.openSession();
        DailyReport dailyReport = (DailyReport) session.createQuery(hql).list().get(0);
        result = dailyReport.getEarnings();
        session.close();
        return result;
    }

    public long getMaxIdDay() {
       //tmp, возможно, нинужон
        return 0;
    }

    public List<DailyReport> getAllDailyReport() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<DailyReport> dailyReports = session.createQuery("FROM DailyReport").list();
        System.out.println("ЭТО дайлиРЕпортЛист" + dailyReports.size());
        transaction.commit();
        session.close();
        return dailyReports;
    }
}
