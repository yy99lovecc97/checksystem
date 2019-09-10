package dao;

import domain.Application;
import domain.Employee;
import utils.BaseDao;

import java.util.List;

public interface ApplicationDao extends BaseDao<Application> {
    /**
     * 根据员工查询未处理的异动申请
     * @param emp
     * @return  该员工对应的未处理的异动申请
     */
    List<Application> findByEmp(Employee emp);
}
