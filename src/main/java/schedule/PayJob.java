package schedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import service.EmpManager;

public class    PayJob extends QuartzJobBean {
    //判断作业是否执行的旗标
    private boolean isRunning = false;

    private EmpManager empManager;

    public void setEmpManager(EmpManager empManager) {
        this.empManager = empManager;
    }
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if (!isRunning){
            System.out.println("开始调度自动结算工资");
            isRunning = true;
            empManager.autoPay();
            isRunning = false;
        }
    }
}
