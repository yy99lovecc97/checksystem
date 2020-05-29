package action;

import action.base.EmpBaseAction;
import com.opensymphony.xwork2.ActionContext;
import vo.AttendBean;

import java.util.List;

public class ViewUnAttendAction extends EmpBaseAction {
    private static final long serialVersionUID = -2318239763722073549L;
    private List<AttendBean> unAttend;

    public List<AttendBean> getUnAttend() {
        return unAttend;
    }

    public void setUnAttend(List<AttendBean> unAttend) {
        this.unAttend = unAttend;
    }

    @Override
    public String execute() throws Exception {
        ActionContext actionContext = ActionContext.getContext();
        String user = (String)actionContext.getSession()
                .get(WebConstant.USER);
        List<AttendBean> result = empManager.unAttend(user);
        setUnAttend(result);
        return SUCCESS;
    }
}
