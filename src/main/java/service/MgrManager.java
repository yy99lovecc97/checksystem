package service;

import domain.Employee;
import exception.HrExecption;
import vo.AppBean;
import vo.EmpBean;
import vo.SalaryBean;

import java.util.List;

public interface MgrManager {
    /**
     * 新增员工
     * @param emp 新增员工
     * @param mgr 员工所属的经理
     * @throws HrExecption
     */
    void addEmp(Employee emp , String mgr) throws HrExecption;

    void delEmp(int id , String mgr) throws HrExecption;
    /**
     * 根据经理返回工资
     * @param mgr 经理姓名
     * @return 工资
     */
    List<SalaryBean> getSalaryByMgr(String mgr) throws HrExecption;

    /**
     * 根据经理返回部门的全部员工
     * @param mgr 经理名
     * @return 经理的全部下属
     */
    List<EmpBean> getEmpsByMgr(String mgr) throws HrExecption;

    /**
     *
     * @param mgr 经理名
     * @return 部门的全部申请
     */
    List<AppBean> getAppsByMgr(String mgr) throws HrExecption;

    /**
     * 处理申请
     * @param appId 申请ID
     * @param mgrName 经理名
     * @param result 申请结果
     */
    void check(int appId , String mgrName , boolean result);
}
