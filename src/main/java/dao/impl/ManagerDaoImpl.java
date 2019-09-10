package dao.impl;

import dao.ManagerDao;
import domain.Manager;
import utils.impl.BaseDaoImpl;

import java.util.List;

public class ManagerDaoImpl extends BaseDaoImpl<Manager> implements ManagerDao {
    @Override
    public List<Manager> findByNameAndPass(Manager mgr) {
        return find("select m from Manager m where m.name = ?0 and m.pass=?1"
                , mgr.getName() , mgr.getPass());
    }

    @Override
    public Manager findByName(String name) {
        List<Manager> ml = find("select m from Manager m where m.name=?0"
                , name);
        if (ml != null && ml.size() > 0)
        {
            return ml.get(0);
        }
        return null;
    }
}
