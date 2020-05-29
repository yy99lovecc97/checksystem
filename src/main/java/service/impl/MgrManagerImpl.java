package service.impl;

import dao.*;
import domain.*;
import exception.HrExecption;
import service.MgrManager;
import vo.AppBean;
import vo.EmpBean;
import vo.SalaryBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class MgrManagerImpl implements MgrManager {
    private ApplicationDao applicationDao;
    private AttendDao attendDao;
    private AttendTypeDao attendTypeDao;
    private CheckBackDao checkBackDao;
    private EmployeeDao employeeDao;
    private ManagerDao managerDao;
    private PaymentDao paymentDao;
    @Override
    public void addEmp(Employee emp, String mgr) throws HrExecption {
        Manager manager = managerDao.findByName(mgr);
        if (manager == null){
            throw new HrExecption("您是经理吗？或你还未登录");
        }
        emp.setManager(manager);
        employeeDao.save(emp);
    }

    @Override
    public void delEmp(int id, String mgr) throws HrExecption {
        Manager manager = managerDao.findByName(mgr);
        if (manager == null){
            throw new HrExecption("您是经理吗？或你还未登录");
        }
        employeeDao.delete(Employee.class , id);
    }

    @Override
    public List<SalaryBean> getSalaryByMgr(String mgr) throws HrExecption{
        Manager manager = managerDao.findByName(mgr);
        if (manager == null){
            throw new HrExecption("您是经理吗？或你还未登录");
        }
        //查询经理对应的员工
        Set<Employee> employees = manager.getEmployees();
        if (employees == null || employees.size() < 1){
            throw new HrExecption("您的部门没有员工");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH , -1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String payMonth = simpleDateFormat.format(calendar.getTime());
        List<SalaryBean> salaryBeans = new ArrayList<>();

        for (Employee employee : employees){
            Payment payment = paymentDao.findByMonthAndEmp(payMonth , employee);
            if (payment != null){
                salaryBeans.add(new SalaryBean(employee.getName() , payment.getAmount()));
            }
        }
        return salaryBeans;
    }

    @Override
    public List<EmpBean> getEmpsByMgr(String mgr) throws HrExecption{
        Manager manager = managerDao.findByName(mgr);
        if (manager == null){
            throw new HrExecption("您是经理吗？或你还未登录");
        }
        //查询经理对应的员工
        Set<Employee> employees = manager.getEmployees();
        if (employees == null || employees.size() < 1){
            throw new HrExecption("您的部门没有员工");
        }
        //封装
        List<EmpBean> empBeans = new ArrayList<>();
        for (Employee employee : employees){
            empBeans.add(new EmpBean(employee.getId(),employee.getName() , employee.getPass() , employee.getSalary()));
        }
        return empBeans;
    }

    @Override
    public List<AppBean> getAppsByMgr(String mgr) throws HrExecption{
        Manager manager = managerDao.findByName(mgr);
        if (manager == null){
            throw new HrExecption("您是经理吗？或你还未登录");
        }
        //查询经理对应的员工
        Set<Employee> employees = manager.getEmployees();
        if (employees == null || employees.size() < 1){
            throw new HrExecption("您的部门没有员工");
        }
        List<AppBean>appBeans = new ArrayList<>();
        for (Employee employee : employees){
            //查看该员工的全部申请
            List<Application>applications = applicationDao.findByEmp(employee);
            if (applications != null && applications.size() > 0){
                for (Application application : applications){
                    //选择未处理的申请
                    if (application.isResult() == false){
                        Attend attend = application.getAttend();
                        appBeans.add(new AppBean(application.getId() , employee.getName() , attend.getAttendType().getName()
                        , application.getAttendType().getName() , application.getReason()));
                    }
                }
            }
        }
        return appBeans;
    }

    @Override
    public void check(int appId, String mgrName, boolean result) {
        Application application = applicationDao.get(Application.class , appId);
        CheckBack checkBack = new CheckBack();
        checkBack.setApp(application);
        Manager manager = managerDao.findByName(mgrName);
        if (manager == null)
        {
            throw new HrExecption("您是经理吗？或你还未登录？");
        }
        checkBack.setManager(manager);
        //同意通过申请
        if (result){
            //设置通过申请
            checkBack.setResult(true);

            //修改申请为已批复
            application.setResult(true);
            applicationDao.update(application);
            //为真时还需要修改出勤类型
            Attend attend = application.getAttend();
            attend.setAttendType(application.getAttendType());
            attendDao.update(attend);
        }
        else {
            //没有通过申请
            checkBack.setResult(false);
            application.setResult(true);
            applicationDao.update(application);
        }
        checkBackDao.save(checkBack);
    }

    public void setApplicationDao(ApplicationDao applicationDao) {
        this.applicationDao = applicationDao;
    }

    public void setAttendDao(AttendDao attendDao) {
        this.attendDao = attendDao;
    }

    public void setAttendTypeDao(AttendTypeDao attendTypeDao) {
        this.attendTypeDao = attendTypeDao;
    }

    public void setCheckBackDao(CheckBackDao checkBackDao) {
        this.checkBackDao = checkBackDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void setManagerDao(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    public void setPaymentDao(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }
}
