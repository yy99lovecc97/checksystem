package dao.impl;

import dao.PaymentDao;
import domain.Employee;
import domain.Payment;
import utils.impl.BaseDaoImpl;

import java.util.List;

public class PaymentDaoImpl extends BaseDaoImpl<Payment> implements PaymentDao {
    @Override
    public List<Payment> findByEmp(Employee emp) {
        return find("select p from Payment as p where p.employee=?0" , emp);
    }

    @Override
    public Payment findByMonthAndEmp(String payMonth, Employee emp) {
        List<Payment> pays = find("select p from Payment as p where"
                + " p.employee=?0 and p.payMonth=?1" , emp , payMonth);
        if (pays != null && pays.size() > 0)
        {
            return pays.get(0);
        }
        return null;
    }
}
