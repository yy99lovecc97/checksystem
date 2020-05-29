package service.impl;

import dao.*;
import domain.*;
import service.EmpManager;
import vo.AttendBean;
import vo.PaymentBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EmpManagerImpl implements EmpManager {
    private ApplicationDao applicationDao;
    private AttendDao attendDao;
    private AttendTypeDao attendTypeDao;
    private CheckBackDao checkBackDao;
    private EmployeeDao employeeDao;
    private ManagerDao managerDao;
    private PaymentDao paymentDao;
    /**
     * 以经理身份来验证登录
     * @param mgr 登录的经理身份
     * @return 登录后的身份确认:0为登录失败，1为登录emp 2为登录mgr
     */
    @Override
    public int validLogin(Manager mgr) {
        //如果找到经理，就以经理登录
        if (managerDao.findByNameAndPass(mgr).size()>=1){
            return LOGIN_MGR;
        }
        //如果找到普通员工就以普通员工登录
        else if (employeeDao.findByNameAndPass(mgr).size()>=1){
            return LOGIN_EMP;
        }
        else {
            return LOGIN_FAIL;
        }
    }

    /**
     * 自动打卡，每周1-5，早上七点为每个员工插入旷工记录
     */
    @Override
    public void autoPunch() {
        System.out.println("自动插入旷工记录");
        List<Employee> employees = employeeDao.findAll(Employee.class);
        //获取当前时间
        String dutyDay = new java.sql.Date(System.currentTimeMillis()).toString();
        for (Employee employee : employees){
            //获取旷工对应的出勤类型
            AttendType attendType = attendTypeDao.get(AttendType.class , 6);
            Attend attend = new Attend();
            attend.setDutyDay(dutyDay);
            attend.setAttendType(attendType);
            if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < AM_LIMIT){
                attend.setCome(true);
            }
            else {
                attend.setCome(false);
            }
            attend.setEmployee(employee);
            attendDao.save(attend);
        }
    }

    /**
     * 每月一号结算上个月工资
     */
    @Override
    public void autoPay() {
        System.out.println("自动插入工资结算");
        List<Employee> employees = employeeDao.findAll(Employee.class);
        //获取上个月时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH , -15);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String payMonth = simpleDateFormat.format(calendar.getTime());
        for (Employee employee : employees){
            Payment payment = new Payment();
            //获取该员工工资
            double amount = employee.getSalary();
            //获取该员工上个月 出勤记录
            List<Attend> attends = attendDao.findByEmpAndMonth(employee , payMonth);
            // 用工资累积其出勤记录的工资
            for (Attend attend : attends){
                amount += attend.getAttendType().getAmerce();
            }
            //添加工资结算
            payment.setPayMonth(payMonth);
            payment.setEmployee(employee);
            payment.setAmount(amount);
            paymentDao.save(payment);
        }
    }

    /**
     * 验证某个员工是否可打卡
     * @param user 员工名
     * @param dutyDay 日期
     * @return 可打卡的类别
     */
    @Override
    public int validPunch(String user, String dutyDay) {
        Employee employee = employeeDao.findByName(user);
        if (employee == null){
            return NO_PUNCH;
        }
        //找出员工当前的出勤记录
        List<Attend> attends = attendDao.findByEmpAndDutyDay(employee , dutyDay);
        //系统没有为用户在当天插入空打卡记录，无法打卡
        if (attends == null || attends.size() <= 0){
            return NO_PUNCH;
        }
        //开始上班打卡
        else if (attends.size() == 1 && attends.get(0).isCome() && attends.get(0).getPunchTime() == null){
            return COME_PUNCH;
        }
        else if (attends.size() == 1 && attends.get(0).getPunchTime() == null){
            return LEAVE_PUNCH;
        }
        else if (attends.size() == 2){
            //可以上下班打卡
            if (attends.get(0).getPunchTime() == null && attends.get(1).getPunchTime() == null){
                return BOTH_PUNCH;
            }
            // 可以下班打卡
            else if (attends.get(1).getPunchTime() == null)
            {
                return LEAVE_PUNCH;
            }
            else
            {
                return NO_PUNCH;
            }
        }
        return NO_PUNCH;
    }

    /**
     * 打卡
     * @param user 员工名
     * @param dutyDay 打卡日期
     * @param isCome 是否是上班打卡
     * @return 打卡结果
     */
    @Override
    public int punch(String user, String dutyDay, boolean isCome) {
        Employee employee = employeeDao.findByName(user);
        if (employee == null){
            return PUNCH_FAIL;
        }
        //找到员工本次打卡对应的出勤记录
        Attend attend = attendDao.findByEmpAndDutyDayAndCome(employee , dutyDay , isCome);
        if (attend == null){
            return PUNCH_FAIL;
        }
        if (attend.getPunchTime() != null){
            return PUNCHED;
        }
        System.out.println("===========打卡==========");
        //获取打卡时间
        int punchHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        attend.setPunchTime(new Date());
        //上班打卡
        if (isCome){
            //九点前 算正常
            if (punchHour < COME_LIMIT){
                attend.setAttendType(attendTypeDao.get(AttendType.class , 1));
            }
            //9-11点之间算迟到
            else if (punchHour < LATE_LIMIT){
                attend.setAttendType(attendTypeDao.get(AttendType.class , 4));
            }
            //十一点之后算旷工
        }
        //下班打卡
        else {
            // 18点之后算正常
            if (punchHour >= LEAVE_LIMIT)
            {
                attend.setAttendType(attendTypeDao.get(AttendType.class , 1));
            }
            // 16~18点之间算早退
            else if (punchHour >= EARLY_LIMIT)
            {
                attend.setAttendType(attendTypeDao.get(AttendType.class , 5));
            }
        }
        attendDao.update(attend);
        return PUNCH_SUCC;
    }
    /**
     * 根据员工浏览自己的工资
     * @param empName 员工名
     * @return 该员工的工资列表
     */
    @Override
    public List<PaymentBean> empSalary(String empName) {
        Employee employee = employeeDao.findByName(empName);
        //获取该员工的全部工资列表
        List<Payment> payments = paymentDao.findByEmp(employee);
        List<PaymentBean> paymentBeans = new ArrayList<>();
        //封装vo集合
        for (Payment payment : payments){
            paymentBeans.add(new PaymentBean(payment.getPayMonth() , payment.getAmount()));
        }
        return paymentBeans;
    }

    /**
     * 员工查看自己的最近三天非正常打卡
     * @param empName 员工名
     * @return 该员工的最近三天的非正常打卡
     */
    @Override
    public List<AttendBean> unAttend(String empName) {
        //找出正常上班的出勤类型
        AttendType attendType = attendTypeDao.get(AttendType.class , 1);
        Employee employee = employeeDao.findByName(empName);
        //找出非正常上班的出勤记录
        List<Attend> attends = attendDao.findByEmpUnAttend(employee , attendType);
        List<AttendBean> attendBeans = new ArrayList<>();
        //封装vo集合
        for (Attend attend : attends){
            attendBeans.add(new AttendBean(attend.getId() , attend.getDutyDay()
                    , attend.getAttendType().getName() , attend.getPunchTime()));
        }
        return attendBeans;
    }

    /**
     * 返回全部的出勤类别
     * @return 全部的出勤类别
     */
    @Override
    public List<AttendType> getAllType() {
        return attendTypeDao.findAll(AttendType.class);
    }

    /**
     * 添加申请
     * @param attId 申请的出勤ID
     * @param typeId 申请的类型ID
     * @param reason 申请的理由
     * @return 添加的结果
     */
    @Override
    public boolean addApplication(int attId, int typeId, String reason) {
        System.out.println("------------"+ attId);
        System.out.println("~~~~~~" + typeId);
        System.out.println("~~~~~~" + reason);
        //创建一个申请
        Application application = new Application();
        //获取申请需要改变的出勤记录
        Attend attend = attendDao.get(Attend.class , attId);
        AttendType attendType = attendTypeDao.get(AttendType.class , typeId);
        application.setAttend(attend);
        application.setAttendType(attendType);
        if (reason != null){
            application.setReason(reason);
        }
        System.out.println("====aaaaaaaaa====");
        applicationDao.save(application);
        return true;
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
