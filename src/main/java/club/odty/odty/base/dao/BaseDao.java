package club.odty.odty.base.dao;

import club.odty.odty.base.Entity.BaseEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 基础Dao,所有Dao必须继承此接口
 *
 * @param <Entity>
 * @param <ID>
 */
public interface BaseDao<Entity extends BaseEntity, ID> {

    /**
     * 保存
     *
     * @param t
     * @return
     */
    Entity saveOrUpdate(@NotNull Entity t);

    /**
     * 批量保存
     *
     * @param ts
     * @return
     */
    List<Entity> saveOrUpdate(@NotEmpty List<Entity> ts);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    Entity findByUuid(@NotEmpty ID id);

    /**
     * 根据条件查询
     *
     * @param param
     * @return
     */
    List<Entity> findByParam(@NotEmpty Map<String, Object> param);

    /**
     * 根据条件更新
     *
     * @param param     更新的参数
     * @param condition 更新的条件
     * @return
     */
    Boolean updateByParam(@NotEmpty Map<String, Object> param, @NotEmpty Map<String, Object> condition);

}
