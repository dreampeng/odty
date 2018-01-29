package club.odty.odty.project.controller.user;

import club.odty.odty.project.entity.user.BaseUser;
import club.odty.odty.project.service.user.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@RestController
public class BaseUserController {
    @Autowired
    BaseUserService baseUserService;

    @PostMapping(value = "/user/registe")
    public BaseUser sayHello(@RequestParam(required = true) String userName, @RequestParam(required = true) String passWord, @RequestParam(required = true) String nickName) {
        BaseUser user = new BaseUser();
        user.setUserName(userName);
        user.setPassWord(passWord);
        user.setNickName(nickName);
        return baseUserService.saveUser(user);
    }
}
