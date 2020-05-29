package action;

import action.base.EmpBaseAction;
import com.opensymphony.xwork2.ActionContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PunchAction extends EmpBaseAction {
    private static final long serialVersionUID = 7473955582470241302L;
    // 封装处理结果的punchIsValid成员变量
    private int punchIsValid;

    public int getPunchIsValid() {
        return punchIsValid;
    }
    public void setPunchIsValid(int punchIsValid) {
        this.punchIsValid = punchIsValid;
    }

    @Override
    public String execute() throws Exception {
        //创建实例
        ActionContext actionContext = ActionContext.getContext();
        // 获取HttpSession中的user属性
        String user = (String) actionContext.getSession().get(WebConstant.USER);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //格式化当前时间
        String dutyDay = sdf.format(new Date());
        //调用业务逻辑方法处理请求
        int result = empManager.validPunch(user , dutyDay);
        setPunchIsValid(result);
        return SUCCESS;
    }
}
