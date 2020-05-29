package action;

import action.base.MgrBaseAction;
import com.opensymphony.xwork2.ActionContext;
import vo.AppBean;

import java.util.List;

public class ViewAppAction extends MgrBaseAction {
    private static final long serialVersionUID = 4440740613993089490L;
    //封装申请
    List<AppBean> apps;

    public List<AppBean> getApps() {
        return apps;
    }

    public void setApps(List<AppBean> apps) {
        this.apps = apps;
    }

    @Override
    public String execute() throws Exception {
        // 创建ActionContext实例
        ActionContext ctx = ActionContext.getContext();
        // 获取HttpSession中的user属性
        String mgrName = (String)ctx.getSession()
                .get(WebConstant.USER);
        List<AppBean> appBeans = mgrManager.getAppsByMgr(mgrName);
        setApps(appBeans);
        return SUCCESS;
    }
}
