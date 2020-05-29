package action;

import action.base.EmpBaseAction;
import com.opensymphony.xwork2.ActionContext;
import domain.Manager;

import static service.EmpManager.LOGIN_EMP;
import static service.EmpManager.LOGIN_MGR;

public class LoginAction extends EmpBaseAction {
    private static final long serialVersionUID = 7922979648150320921L;
    //定义一个常量作为员工登陆成功的返回值
    private final String EMP_RESULT = "emp";
    //定义一个常量作为经理登陆成功的返回值
    private final String MGR_RESULT = "mgr";
    //封装请求参数
    private Manager manager;
    //登录的验证码
    private String vercode;

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public String getVercode() {
        return vercode;
    }

    public void setVercode(String vercode) {
        this.vercode = vercode;
    }

    @Override
    public String execute() throws Exception {
        //创建actioncontext实例
        ActionContext actionContext = ActionContext.getContext();
        //获取httpsession的rand属性
        String ver2 = (String) actionContext.getSession().get("rand");
        if (vercode.equalsIgnoreCase(ver2)){
            //调用业务逻辑方法处理登录
            int result = empManager.validLogin(getManager());
            //登录结果为员工
            if (result == LOGIN_EMP){
                actionContext.getSession().put(WebConstant.USER , manager.getName());
                actionContext.getSession().put(WebConstant.LEVEL , WebConstant.EMP_LEVEL);
                addActionMessage("您已经成功登录系统");
                return EMP_RESULT;
            }
            //登录结果为经理
            else if (result == LOGIN_MGR){
                actionContext.getSession().put(WebConstant.USER , manager.getName());
                actionContext.getSession().put(WebConstant.LEVEL , WebConstant.MGR_LEVEL);
                addActionMessage("您已经成功登录系统");
                return MGR_RESULT;
            }
            //用户名和密码不匹配
            else {
                addActionMessage("用户名/密码不匹配");
                return ERROR;
            }
        }
        // 验证码不匹配
        addActionMessage("验证码不匹配,请重新输入");
        return ERROR;
    }
}
