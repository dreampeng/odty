package club.odty.odty.project.dao.user;

import club.odty.odty.base.dao.BaseDao;
import club.odty.odty.project.entity.user.BaseUser;
import org.springframework.stereotype.Repository;


@Repository
public interface BaseUserDao extends BaseDao<BaseUser, String> {
}
