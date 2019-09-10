package utils.impl;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import utils.BaseDao;

import java.io.Serializable;
import java.util.List;

public class BaseDaoImpl<T> implements BaseDao<T> {
    private SessionFactory sessionFactory;
    @Override
    public T get(Class<T> entityClazz, Serializable id) {
        return (T) getSessionFactory().getCurrentSession().get(entityClazz,id);
    }

    @Override
    public Serializable save(T entity) {
        return getSessionFactory().getCurrentSession().save(entity);
    }

    @Override
    public void update(T entity) {
        getSessionFactory().getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(T entity) {
        getSessionFactory().getCurrentSession().delete(entity);
    }

    @Override
    public void delete(Class<T> entityClazz, Serializable id) {
        getSessionFactory().getCurrentSession()
                .createQuery("delete " + entityClazz.getSimpleName()
                        + " en where en.id like ?")
                .setParameter(1 , id)
                .executeUpdate();
    }

    @Override
    public List<T> findAll(Class<T> entityClazz) {
        return find("select en from "
                + entityClazz.getSimpleName() + " en");
    }

    @Override
    public long findCount(Class<T> entityClazz) {
        List<?> l = find("select count(*) from "
                + entityClazz.getSimpleName());
        // ���ز�ѯ�õ���ʵ������
        if (l != null && l.size() == 1 )
        {
            return (Long) l.get(0);
        }
        return 0;
    }
    protected List<T> find(String hql)
    {
        return (List<T>)getSessionFactory().getCurrentSession()
                .createQuery(hql)
                .getResultList();
    }
    @SuppressWarnings("unchecked")
    protected List<T> find(String hql , Object... params)
    {
        // ������ѯ
        Query query = getSessionFactory().getCurrentSession()
                .createQuery(hql);
        // Ϊ����ռλ����HQL������ò���
        for(int i = 0 , len = params.length ; i < len ; i++)
        {
            query.setParameter(i + "" , params[i]);
        }
        return (List<T>)query.getResultList();
    }

    @SuppressWarnings("unchecked")
    protected List<Object> findByPage(String hql, int pageNo, int pageSize)
    {
        return getSessionFactory().getCurrentSession()
                .createQuery(hql)
                .setFirstResult((pageNo - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }
    @SuppressWarnings("unchecked")
    protected List<Object> findByPage(String hql , int pageNo, int pageSize
            , Object... params)
    {
        Query query = getSessionFactory().getCurrentSession()
                .createQuery(hql);
        for(int i = 0 , len = params.length ; i < len ; i++)
        {
            query.setParameter(i + "" , params[i]);
        }
        // ִ�з�ҳ�������ز�ѯ���
        return query.setFirstResult((pageNo - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
