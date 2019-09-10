package dao.impl;

import dao.ApplicationDao;
import domain.Application;
import domain.Employee;
import utils.impl.BaseDaoImpl;

import java.util.List;

public class ApplicationDaoImpl extends BaseDaoImpl<Application> implements ApplicationDao{
    /**
     * 根据员工查询未处理的异动申请
     * @param emp 需要查询的员工
     * @return 该员工对应的未处理的异动申请
     */
    @Override
    public List<Application> findByEmp(Employee emp) {
        return find("select a from Application as a where "
                + "a.attend.employee=?0" , emp);
    }
}
