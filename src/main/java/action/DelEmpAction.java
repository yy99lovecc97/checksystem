package action;

import action.base.MgrBaseAction;
import com.opensymphony.xwork2.ActionContext;

public class DelEmpAction extends MgrBaseAction {
    private static final long serialVersionUID = -499846213507768816L;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    @Override
    public String execute() throws Exception {
        ActionContext actionContext = ActionContext.getContext();
        // 获取HttpSession中的user属性
        String mgrName = (String)actionContext.getSession()
                .get(WebConstant.USER);
        mgrManager.delEmp(id,mgrName);
        addActionMessage("删除员工成功");
        return SUCCESS;
    }
}
