package net.ziling.crm.service;

import com.sun.xml.internal.rngom.parse.host.Base;
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
     * 根据userId更新用户信息
     * @param user,permission
     * @return success number 0 / 1
     */
    public int updateByUserId(BaseUser user, String permission);

    /**
     * 根据userId删除所有user
     * @param baseUserId
     * @return success number 0 / 1
     */
    public int deleteByUserId(String baseUserId);
}
