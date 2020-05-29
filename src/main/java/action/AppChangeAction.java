package action;

import action.base.EmpBaseAction;

import java.util.List;

public class AppChangeAction extends EmpBaseAction {
    private static final long serialVersionUID = 4577552215156767583L;
    //封装所有异动的列表
    private List types;

    public List getTypes() {
        return types;
    }

    public void setTypes(List types) {
        this.types = types;
    }

    @Override
    public String execute() throws Exception {
        setTypes(empManager.getAllType());
        return SUCCESS;
    }
}
