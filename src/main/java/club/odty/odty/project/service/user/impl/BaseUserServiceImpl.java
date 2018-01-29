package club.odty.odty.project.service.user.impl;

import club.odty.odty.project.dao.user.BaseUserDao;
import club.odty.odty.project.entity.user.BaseUser;
import club.odty.odty.project.service.user.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseUserServiceImpl implements BaseUserService {
    @Autowired
    BaseUserDao baseUserDao;

    @Override
    public BaseUser saveUser(BaseUser e) {
        e = baseUserDao.saveOrUpdate(e);
        return e;
    }
}
