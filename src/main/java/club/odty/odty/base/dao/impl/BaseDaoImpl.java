package club.odty.odty.base.dao.impl;

import club.odty.odty.base.Entity.BaseEntity;
import club.odty.odty.base.Util.TimeUtil;
import club.odty.odty.base.Util.UUIDUtil;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class BaseDaoImpl<Entity extends BaseEntity, ID> {
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

    Entity saveOrUpdate(@NotNull Entity t) {
        if (t.getUuid() == null) {
            t.setUuid(UUIDUtil.createUuid());
            t.setCreateTime(TimeUtil.getTimestamp());
        } else {
            t.setUpdateTime(TimeUtil.getTimestamp());
        }
        return entityManager.merge(t);
    }

    List<Entity> saveOrUpdate(@NotEmpty List<Entity> ts) {
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
            entityManager.persist(t);
            i++;
            if (i % batchSize == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        return ts;
    }

    Entity findByUuid(@NotEmpty ID id) {
        return entityManager.find(entityClass, id);
    }

    List<Entity> findByParam(@NotEmpty Map<String, Object> param) {
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

    void updateByParam(@NotEmpty Map<String, Object> param, @NotEmpty Map<String, Object> condition) {
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
        query.getResultList();
    }

    void delete(@NotNull Entity t) {
    }

    /**
     * 根据参数删除
     *
     * @param param
     * @return
     */
    void delete(@NotEmpty Map<String, Object> param) {
        return null;
    }

}
