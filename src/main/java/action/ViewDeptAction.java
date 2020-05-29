package action;

import action.base.MgrBaseAction;
import com.opensymphony.xwork2.ActionContext;
import vo.SalaryBean;

import java.util.List;

public class ViewDeptAction extends MgrBaseAction {
    private static final long serialVersionUID = -7396310598200795234L;
    //封装发薪列表的list成员变量
    private List<SalaryBean> salarys;

    public List<SalaryBean> getSalarys() {
        return salarys;
    }

    public void setSalarys(List<SalaryBean> salarys) {
        this.salarys = salarys;
    }

    @Override
    public String execute() throws Exception {
        // 创建ActionContext实例
        ActionContext ctx = ActionContext.getContext();
        // 获取HttpSession中的user属性
        String managerName = (String)ctx.getSession()
                .get(WebConstant.USER);
        //调用业务逻辑方法取得员工的全部发薪列表
        List<SalaryBean> salaryBeans = mgrManager.getSalaryByMgr(managerName);
        setSalarys(salaryBeans);
        return SUCCESS;
    }
}
