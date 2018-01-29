package club.odty.odty.base.dao.impl;

import club.odty.odty.base.Entity.BaseEntity;
import club.odty.odty.base.Util.TimeUtil;
import club.odty.odty.base.Util.UUIDUtil;
import club.odty.odty.base.dao.BaseDao;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class BaseDaoImpl<Entity extends BaseEntity, ID> implements BaseDao<Entity, ID> {
    @Autowired
    EntityManager entityManager;
    private Class<Entity> entityClass;
    private String entityClassName;

    public BaseDaoImpl() {
        Type type = getClass().getGenericSuperclass();
        Type[] generics = ((ParameterizedType) type).getActualTypeArguments();
        this.entityClass = (Class<Entity>) generics[0];
        this.entityClassName = entityClass.getName();
    }

    @Override
    public Entity saveOrUpdate(@NotNull Entity t) {
        if (t.getUuid() == null) {
            t.setUuid(UUIDUtil.createUuid());
            t.setCreateTime(TimeUtil.getTimestamp());
        } else {
            t.setUpdateTime(TimeUtil.getTimestamp());
        }
        return entityManager.merge(t);
    }

    @Override
    public List<Entity> saveOrUpdate(@NotEmpty List<Entity> ts) {
        // 每100条往数据库里写入一次,相对提升性能，此值可改变
        int batchSize = 100;
        int i = 0;
        for (Entity t : ts) {
            if (t.getUuid() == null) {
                t.setUuid(UUIDUtil.createUuid());
                t.setCreateTime(TimeUtil.getTimestamp());
            } else {
                t.setUpdateTime(TimeUtil.getTimestamp());
            }
            t = entityManager.merge(t);
            i++;
            if (i % batchSize == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        return ts;
    }

    @Override
    public Entity findByUuid(@NotEmpty ID id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public List<Entity> findByParam(@NotEmpty Map<String, Object> param) {
        String hql = "FROM " + entityClassName + "WHERE 1 = 1 ";
        for (String keySet : param.keySet()) {
            hql += "and " + keySet + " = :" + keySet + " ";
        }
        Query query = entityManager.createQuery(hql);
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

    @Override
    public int updateByParam(@NotEmpty Map<String, Object> param, @NotEmpty Map<String, Object> condition) {
        String hql = "update " + entityClassName + " set 1 = 1 ";
        //设置修改值
        for (String keySet : param.keySet()) {
            hql += ", " + keySet + " = :" + keySet + " ";
        }
        hql += " where 1=1 ";
        //设置条件
        for (String keySet : condition.keySet()) {
            hql += "and " + keySet + " = :" + keySet + " ";
        }
        Query query = entityManager.createQuery(hql);
        //此处是修改的参数
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        //此处是条件的参数
        for (Map.Entry<String, Object> entry : condition.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.executeUpdate();
    }

    @Override
    public void delete(@NotNull Entity... ts) {
        int batchSize = 100;
        int i = 0;
        for (Entity t : ts) {
            entityManager.remove(t);
            i++;
            if (i % batchSize == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
    }

    @Override
    public int delete(@NotEmpty Map<String, Object> param) {
        String hql = "DELETE from " + entityClassName + "WHERE 1 = 1 ";
        for (String keySet : param.keySet()) {
            hql += "and " + keySet + " = :" + keySet + " ";
        }
        Query query = entityManager.createQuery(hql);
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.executeUpdate();
    }

}
