package schedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import service.EmpManager;
//作业bean
public class PunchJob extends QuartzJobBean {
    //判断作业是否执行的旗标
    private boolean isRunning = false;

    private EmpManager empManager;

    public void setEmpManager(EmpManager empManager) {
        this.empManager = empManager;
    }
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if (!isRunning){
            System.out.println("开始调度自动打卡");
            isRunning = true;
            empManager.autoPunch();
            isRunning = false;
        }
    }
}
