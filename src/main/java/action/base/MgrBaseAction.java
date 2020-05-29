package action.base;

import com.opensymphony.xwork2.ActionSupport;
import service.MgrManager;

public class MgrBaseAction extends ActionSupport {
    private static final long serialVersionUID = 6536104449598149298L;
    protected MgrManager mgrManager;

    public void setMgrManager(MgrManager mgrManager) {
        this.mgrManager = mgrManager;
    }
}
