package dao.impl;

import dao.EmployeeDao;
import domain.Employee;
import utils.impl.BaseDaoImpl;

import java.util.List;

public class EmployeeDaoImpl extends BaseDaoImpl<Employee> implements EmployeeDao {
    @Override
    public List<Employee> findByNameAndPass(Employee employee) {
        return find("select p from Employee as p where p.name = ?0 and p.pass = ?1"
        ,employee.getName() , employee.getPass());
    }

    @Override
    public Employee findByName(String name) {
        List<Employee> emps = find("select e from Employee e where e.name = ?0"
                , name);
        if (emps!= null && emps.size() >= 1)
        {
            return emps.get(0);
        }
        return null;
    }
}
