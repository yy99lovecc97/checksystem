package action.base;

import com.opensymphony.xwork2.ActionSupport;
import service.EmpManager;

public class EmpBaseAction extends ActionSupport {
    private static final long serialVersionUID = 1403316729643231721L;
    //依赖的业务逻辑组件
    protected EmpManager empManager;

    public void setEmpManager(EmpManager empManager) {
        this.empManager = empManager;
    }
}
