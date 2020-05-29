package action;

import action.base.MgrBaseAction;
import com.opensymphony.xwork2.ActionContext;

public class CheckAppAction extends MgrBaseAction {
    private static final long serialVersionUID = 6077806255288878557L;
    // 需要被处理的申请ID
    private int appid;
    // 封装处理结果
    private String result;

    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String execute() throws Exception {
        // 创建ActionContext实例
        ActionContext ctx = ActionContext.getContext();
        // 获取HttpSession中的user属性
        String mgrName = (String)ctx.getSession()
                .get(WebConstant.USER);
        // 通过申请
        if (result.equals("pass"))
        {
            mgrManager.check(appid, mgrName, true);
        }
        // 拒绝申请
        else if (result.equals("deny"))
        {
            mgrManager.check(appid, mgrName, false);
        }
        else
        {
            throw new Exception("参数丢失");
        }
        return SUCCESS;
    }
}
