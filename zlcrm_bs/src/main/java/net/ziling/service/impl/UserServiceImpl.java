package net.ziling.service.impl;

import net.ziling.entity.User;
import net.ziling.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Description:
 * 用户服务接口UserService的实现部分
 *
 * @author huaxin
 * @create 2018/01/09 16:20
 */
@Service
public class UserServiceImpl implements UserService {

    private final static String DEFAULT_USERNAME = "sadmin";
    private final static String DEFAULT_PASSWORD = "sadmin";

    @Override
    public User loginByUsernameAndPassword(String username, String password) {
        User user = null;
        if (username.equals(DEFAULT_USERNAME) && password.equals(DEFAULT_PASSWORD)) {
            System.out.println("true");
            user = new User();
            user.setUsername(DEFAULT_USERNAME);
            user.setPassword(DEFAULT_PASSWORD);
        }
        return user;
    }
}
