package action;

import action.base.MgrBaseAction;
import com.opensymphony.xwork2.ActionContext;
import vo.EmpBean;

import java.util.List;

public class ViewEmpAction extends MgrBaseAction {
    private static final long serialVersionUID = 8492036327123639595L;
    //封装当前经理所有员工
    private List<EmpBean> emps;

    public List<EmpBean> getEmps() {
        return emps;
    }

    public void setEmps(List<EmpBean> emps) {
        this.emps = emps;
    }

    @Override
    public String execute() throws Exception {
        // 创建ActionContext实例
        ActionContext ctx = ActionContext.getContext();
        // 获取HttpSession中的user属性
        String mgrName = (String)ctx.getSession()
                .get(WebConstant.USER);
        setEmps(mgrManager.getEmpsByMgr(mgrName));
        return SUCCESS;
    }
}
