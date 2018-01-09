package net.ziling.service;

import net.ziling.entity.User;

/**
 * Description:
 * 用户相关的服务
 *
 * @author huaxin
 * @create 2018/01/09 16:03
 */
public interface UserService {

    /**
     * 通过用户名和密码进行登录，后期可以扩展为通过其他方式进行登录
     * @param username  用户名
     * @param password  用户密码
     * @return  如果登录成功，则返回登录成功的用户的用户信息
     */
    public User loginByUsernameAndPassword(String username, String password);

}
