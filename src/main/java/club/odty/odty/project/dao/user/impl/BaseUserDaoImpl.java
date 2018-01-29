package club.odty.odty.project.dao.user.impl;

import club.odty.odty.base.dao.impl.BaseDaoImpl;
import club.odty.odty.project.dao.user.BaseUserDao;
import club.odty.odty.project.entity.user.BaseUser;
import org.springframework.stereotype.Repository;

@Repository
public class BaseUserDaoImpl extends BaseDaoImpl<BaseUser, String> implements BaseUserDao {
}
