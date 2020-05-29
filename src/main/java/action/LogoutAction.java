package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutAction extends ActionSupport implements ServletRequestAware {
    private static final long serialVersionUID = -6287382505083456088L;
    // 定义一个HttpServletRequest对象
    private HttpServletRequest request;

    //形参为request
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws Exception {
        // 获取HttpSession
        HttpSession session = request.getSession();
        // 使Session失效
        session.invalidate();
        return SUCCESS;
    }
}
