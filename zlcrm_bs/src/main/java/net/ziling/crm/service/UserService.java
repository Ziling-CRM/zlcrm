package net.ziling.crm.service;

import javafx.util.Pair;
import net.ziling.crm.entity.BaseUser;
import net.ziling.crm.entity.Duty;
import net.ziling.crm.entity.Project;
import net.ziling.crm.entity.Role;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据userId更新用户信息
     * @param user,permission
     * @return success 0 / 1
     */
    public int updateByUserId(BaseUser user, String permission);

    /**
     * 根据userId删除所有user
     * @param baseUserId
     * @return success 0 / 1
     */
    public int deleteByUserId(String baseUserId);

    /**
     * 根据用户的userId获取用户的信息
     * @param userId
     * @return
     */
    public Map<String, Object> getUserByUserId(String userId);

    /**
     * 添加客户信息
     * @param baseUser
     * @return
     */
    public int addBaseUser(BaseUser baseUser) throws Exception;

    /**
     * 修改客户信息，可选user，project以及duty
     * @param user
     * @param project
     * @param duty
     * @return
     */
    public Map<String, Object> updateUserInf(BaseUser user, Project project, Duty duty);
}
