package net.ziling.crm.service.impl;

import net.ziling.crm.common.util.UUIDTools;
import net.ziling.crm.common.wrap.UserStatus;
import net.ziling.crm.dao.BaseUserMapper;
import net.ziling.crm.dao.RoleMapper;
import net.ziling.crm.dao.UserRoleMapper;
import net.ziling.crm.entity.BaseUser;
import net.ziling.crm.entity.Role;
import net.ziling.crm.entity.UserRole;
import net.ziling.crm.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private BaseUserMapper baseUserMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public BaseUser loginByUsernameAndPassword(String username, String password) {
        BaseUser user;

        user = baseUserMapper.selectByUsername(username);

        //没有找到该用户的信息
        if (user == null || (user.getUsername()==null) || (user.getUsername().length()<=0)) {
            return null;
        }

        //如果用户找到了，匹配用户的密码
        if (!user.getPassword().equals(password)) {
            user.setPassword(null);
            return user;
        }

        return user;
    }

    @Override
    public Role getUserRole(String userId) {
        System.out.println("userId:"+userId);
        UserRole userRole = userRoleMapper.getUserRoleByUserId(userId);
        return roleMapper.selectByPrimaryKey(userRole.getRoleId());
    }

    @Override
    public List<BaseUser> getAllAdmin() {
        return  baseUserMapper.getAllAdmin();
    }

    @Override
    public BaseUser getUserByUsername(String username) {
        if (username == null || username.trim().length()<=0) {
            return null;
        }
        return baseUserMapper.selectByUsername(username);
    }

    @Override
    public int addAdminUserAndRole(BaseUser user, Role role) {
        if (user == null) {
            return -1;
        }
        try {
            user.setStatus(UserStatus.ON.toString());
            baseUserMapper.insertSelective(user);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

        if (role == null) {
            return -2;
        }
        try {
            UserRole userRole = new UserRole();
            userRole.setId(UUIDTools.getUUIDId());
            userRole.setRoleId(role.getRoleId());
            userRole.setUserId(user.getUserId());
            userRoleMapper.insertSelective(userRole);
        } catch (Exception e) {
            e.printStackTrace();
            return -2;
        }
        return 0;
    }

    @Override
    public int updateByUserId(BaseUser user, String permission){
        int res = baseUserMapper.updateUserByUserIdSimple(user);
        if(res == 0) {
            return res;
        }

        res = userRoleMapper.updateUserRoleByUserId(user.getUserId(), permission);
        System.out.println(res);

        return res;
    }

    @Override
    public int deleteByUserId(String userId){
        return baseUserMapper.deleteByUserId(userId);
    }
}
