package dao.impl;

import dao.AttendDao;
import domain.Attend;
import domain.AttendType;
import domain.Employee;
import utils.impl.BaseDaoImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class AttendDaoImpl extends BaseDaoImpl<Attend> implements AttendDao {

    @Override
    public List<Attend> findByEmpAndMonth(Employee employee, String month) {
        return find("from Attend as a where a.employee = ?0" +
                "and substring (a.dutyDay , 0 , 7)=?1" , employee , month);
    }

    @Override
    public List<Attend> findByEmpAndDutyDay(Employee employee, String dutyDay) {
        return find("from Attend as a where a.employee=?0 and "
                + "a.dutyDay=?1" , employee , dutyDay);
    }

    @Override
    public Attend findByEmpAndDutyDayAndCome(Employee employee, String dutyDay, boolean isCome) {

        List<Attend> al = findByEmpAndDutyDay(employee , dutyDay);
        if (al != null && al.size() >= 1)
        {
            for (Attend attend : al)
            {
                if (attend.isCome() == isCome )
                {
                    return attend;
                }
            }
        }
        return null;
    }

    @Override
    public List<Attend> findByEmpUnAttend(Employee employee, AttendType type) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        String end = simpleDateFormat.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH , -3);
        String start = simpleDateFormat.format(calendar.getTime());

        return find("from Attend as a where a.employee=?0 and a.attendType != ?1 and a.dutyDay between ?2 and ?3"
        ,employee,type,start,end);
    }
}
