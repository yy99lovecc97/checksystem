package dao;

import domain.Employee;
import utils.BaseDao;

import java.util.List;

public interface EmployeeDao extends BaseDao<Employee> {
    /**
     * 根据用户名和密码查询员工
     * @param employee
     * @return 符合指定用户名密码的员工的集合
     */
    List<Employee> findByNameAndPass(Employee employee);

    /**
     * 根据用户名查询员工
     * @param name
     * @return 符合用户名的员工
     */
    Employee findByName(String name);
}
