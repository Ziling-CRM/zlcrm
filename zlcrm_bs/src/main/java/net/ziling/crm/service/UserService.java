package net.ziling.crm.service;

import javafx.util.Pair;
import net.ziling.crm.entity.*;
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
     * @return
     */
    public int updateUserInf(BaseUser user);
    public int updateUserDuty(Duty duty, String userId);
    public int updateUserProject(Project project, String userId);

   /**
     * 添加用户的职责信息，其中包括用户和职责的关联表，和职责的信息表
     * @param userDuty
     * @param duty
     * @return 返回为0则表示成功，非零均为有误，1表示UserDuty的插入有误，2表示Duty的插入有误 , 3表示数据为空
     */
    public int addUserDuty(UserDuty userDuty, Duty duty);

    /**
     * 添加用户的项目信息，其中包括用户的项目信息，和用户项目的关联表的信息
     * @param userProject
     * @param project
     * @return  返回为0则表示成功，非零均为有误，1表示UserProject的插入有误，2表示Project的插入有误 , 3表示数据为空
     */
    public int addUserProject(UserProject userProject, Project project);

    /**
     * 通过搜索条件进行用户的信息检索
     * @param limits 限制的条件的Map集合
     * @return 所有满足条件的用户的集合
     */
    public List<BaseUser> getAllSelectedUser(Map<String, String> limits);

    /**
     * 通过userId判断用户是否存在
     * @param userId
     * @return
     */
    public BaseUser judgeUserExist(String userId);

}
