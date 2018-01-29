package club.odty.odty.project.service.user;

import club.odty.odty.project.entity.user.BaseUser;

public interface BaseUserService {
    /**
     * 保存用户(注册修改)
     *
     * @param e
     * @return
     */
    BaseUser saveUser(BaseUser e);
}