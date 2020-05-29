package action;

import action.base.EmpBaseAction;
import com.opensymphony.xwork2.ActionContext;
import vo.PaymentBean;

import java.util.List;

public class ViewSalaryAction extends EmpBaseAction {
    private static final long serialVersionUID = -3326637282686960955L;
    // 封装所有发薪信息的List
    private List<PaymentBean> salarys;

    public List<PaymentBean> getSalarys() {
        return salarys;
    }

    public void setSalarys(List<PaymentBean> salarys) {
        this.salarys = salarys;
    }

    @Override
    public String execute() throws Exception {
        // 创建ActionContext实例
        ActionContext ctx = ActionContext.getContext();
        // 获取HttpSession中的user属性
        String user = (String)ctx.getSession()
                .get(WebConstant.USER);
        List<PaymentBean> salarys =  empManager.empSalary(user);
        setSalarys(salarys);
        return SUCCESS;
    }
}
