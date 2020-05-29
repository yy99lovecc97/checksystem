package action.authority;

import action.WebConstant;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class MgrAuthorityInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = 7302491377479488393L;

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        ActionContext actionContext = ActionContext.getContext();
        //获取httpsession的level属性
        String level = (String) actionContext.getSession().get(WebConstant.LEVEL);
        // 如果level不为null，且level为mgr
        if ( level != null && level.equals(WebConstant.MGR_LEVEL))
        {
            return actionInvocation.invoke();
        }
        return Action.LOGIN;
    }
}
