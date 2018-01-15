package net.ziling.crm.service.impl;

import net.ziling.crm.common.util.UUIDTools;
import net.ziling.crm.common.wrap.GetUserResult;
import net.ziling.crm.common.wrap.UserStatus;
import net.ziling.crm.dao.*;
import net.ziling.crm.entity.*;
import net.ziling.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private DutyMapper dutyMapper;
    @Autowired
    private UserDutyMapper userDutyMapper;
    @Autowired
    private UserProjectMapper userProjectMapper;

    @Override
    public BaseUser loginByUsernameAndPassword(String username, String password) {
        BaseUser user;

        user = baseUserMapper.selectByUsername(username);

        //没有找到该用户的信息
        if (user == null || (user.getUsername() == null) || (user.getUsername().length() <= 0)) {
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
        System.out.println("userId:" + userId);
        UserRole userRole = userRoleMapper.getUserRoleByUserId(userId);
        return roleMapper.selectByPrimaryKey(userRole.getRoleId());
    }

    @Override
    public List<BaseUser> getAllAdmin() {
        return baseUserMapper.getAllAdmin();
    }

    @Override
    public BaseUser getUserByUsername(String username) {
        if (username == null || username.trim().length() <= 0) {
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
    public int updateByUserId(BaseUser user, String permission) {
        int res = baseUserMapper.updateUserByUserIdSimple(user);
        System.out.println(res);
        if (res == 0) {
            return res;
        }

        res = userRoleMapper.updateUserRoleByUserId(user.getUserId(), permission);
        System.out.println(res);
        if (res == 0) {
            return -1;
        } else {
            return res;
        }
    }

    @Override
    public int deleteByUserId(String userId) {
        return baseUserMapper.deleteByUserId(userId);
    }

    @Override
    public Map<String, Object> getUserByUserId(String userId) {
        Map<String, Object> resultUser = new HashMap<>();

        //获取基本信息
        BaseUser user = baseUserMapper.selectByUserId(userId);
        if (user == null) {
            resultUser.put("Error", GetUserResult.USER_NOT_EXIST.getValue());
            resultUser.put("ErrorMsg", GetUserResult.USER_NOT_EXIST.getMsg());
            return resultUser;
        }

        //获取dutyId
        List<String> dutyIds = userDutyMapper.selectByUserId(userId);
        if (dutyIds.size() == 0) {
            resultUser.put("Error", GetUserResult.DUTY_NOT_EXIST.getValue());
            resultUser.put("ErrorMsg", GetUserResult.DUTY_NOT_EXIST.getMsg());
            return resultUser;
        }

        //获取proId
        List<String> proIds = userProjectMapper.selectProIdByUserId(userId);
        if (proIds.size() == 0) {
            resultUser.put("Error", GetUserResult.PROJECT_NOT_EXIST.getValue());
            resultUser.put("ErrorMsg", GetUserResult.PROJECT_NOT_EXIST.getMsg());
            return resultUser;
        }

        List<Duty> duties = new ArrayList<Duty>();
        duties.clear();
        //获取duty信息
        for (String id : dutyIds) {
            Duty t = dutyMapper.selectDutyByDutyId(id);
            if (t != null) {
                duties.add(t);
            } else {
                resultUser.put("Error", GetUserResult.DUTY_ID_NOT_EXIST.getValue());
                resultUser.put("ErrorMsg", GetUserResult.DUTY_ID_NOT_EXIST.getMsg());
                return resultUser;
            }
        }

        List<Project> projects = new ArrayList<Project>();
        projects.clear();
        //获取project信息
        for (String id : proIds) {
            Project t = projectMapper.selectProjectByProId(id);
            if (t != null) {
                projects.add(t);
            } else {
                resultUser.put("Error", GetUserResult.PROJECT_ID_NOT_EXIST.getValue());
                resultUser.put("ErrorMsg", GetUserResult.PROJECT_ID_NOT_EXIST.getMsg());
                return resultUser;
            }
        }

        resultUser.put("Error", GetUserResult.SUCCESS.getValue());
        resultUser.put("ErrorMsg", GetUserResult.SUCCESS.getMsg());

        resultUser.put("User", user);
        resultUser.put("Duties", duties);
        resultUser.put("Projects", projects);

        return resultUser;
    }

    @Override
    public int addBaseUser(BaseUser baseUser) throws Exception {
        try {
            baseUser.setStatus(UserStatus.ON.toString());
            return baseUserMapper.insertSelective(baseUser);
        } catch (Exception e) {
            throw e;
        } finally {
            return 0;
        }
    }

    @Override
    public int addUserDuty(UserDuty userDuty, Duty duty) {
        if (userDuty.getUserId() == null || userDuty.getDutyId() == null ||
                userDuty.getDutyId().trim().length() <= 0 || userDuty.getUserId().trim().length() <= 0) {
            return 3;
        }

        try {
            dutyMapper.insertSelective(duty);
        }catch (Exception e) {
            e.printStackTrace();
            return 2;
        }

        try {
            userDutyMapper.insertSelective(userDuty);
        }catch (Exception e) {
            e.printStackTrace();
            return 1;
        }

        return 0;
    }

    @Override
    public int addUserProject(UserProject userProject, Project project) {
        if (userProject.getUserId() == null || userProject.getProId() == null ||
                userProject.getProId().trim().length() <= 0 || userProject.getUserId().trim().length() <= 0) {
            return 3;
        }

        try {
            projectMapper.insertSelective(project);
        }catch (Exception e) {
            e.printStackTrace();
            return 2;
        }

        try {
            userProjectMapper.insertSelective(userProject);
        }catch (Exception e) {
            e.printStackTrace();
            return 1;
        }

        return 0;
    }

    @Override
    public List<BaseUser> getAllSelectedUser(Map<String, String> limits) {
        return baseUserMapper.getAllUser();
    }

    @Override
    public BaseUser judgeUserExist(String userId) {
        return baseUserMapper.selectByUserId(userId);
    }
}
