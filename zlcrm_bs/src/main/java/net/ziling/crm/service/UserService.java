package net.ziling.crm.service;

import net.ziling.crm.entity.BaseUser;
import net.ziling.crm.entity.Domain;
import net.ziling.crm.entity.Role;

import java.util.List;

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
    public BaseUser loginByUsernameAndPassword(String username, String password);

    /**
     * 获取当前管理员的权限信息
     * @param userId
     * @return 权限列表
     */
    public Role getUserRole(String userId);

    /**
     * 获取所有管理员信息
     * @return
     */
    public List<BaseUser> getAllAdmin();

    /**
     * 根据用户的用户名获取用户的信息
     * @param username
     * @return
     */
    public BaseUser getUserByUsername(String username);

    /**
     * 添加管理员用户
     * @param user
     * @return
     */
    public int addAdminUserAndRole(BaseUser user, Role role);

}
