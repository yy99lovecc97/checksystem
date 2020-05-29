package action;

import action.base.MgrBaseAction;
import com.opensymphony.xwork2.ActionContext;
import domain.Employee;

public class AddEmpAction extends MgrBaseAction {
    private static final long serialVersionUID = -541944663686364440L;
    //代表新增员工的成员变量
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String execute() throws Exception {
        ActionContext actionContext = ActionContext.getContext();
        // 获取HttpSession中的user属性
        String mgrName = (String)actionContext.getSession()
                .get(WebConstant.USER);
        //添加新用户
        mgrManager.addEmp(employee,mgrName);
        clearMessages();
        addActionMessage("新增员工成功");
        return SUCCESS;
    }
}
