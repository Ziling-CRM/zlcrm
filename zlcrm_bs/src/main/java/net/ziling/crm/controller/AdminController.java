package net.ziling.crm.controller;

import net.ziling.crm.common.util.AdminResultVo;
import net.ziling.crm.common.util.ArgumentsValidator;
import net.ziling.crm.common.util.ResultVo;
import net.ziling.crm.common.util.UUIDTools;
import net.ziling.crm.common.wrap.AddResult;
import net.ziling.crm.common.wrap.LoginResult;
import net.ziling.crm.common.wrap.UserPermision;
import net.ziling.crm.entity.BaseUser;
import net.ziling.crm.entity.Role;
import net.ziling.crm.entity.wrap.BaseUserWrap;
import net.ziling.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * 管理员相关的前端控制器
 *
 * @author huaxin
 * @create 2018/01/08 19:54
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final static String PRE_STR_OF_USERID = "a";

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public AdminResultVo login(String username, String password, HttpSession session, HttpServletRequest request) {
        AdminResultVo resultVo = new AdminResultVo();
        BaseUser user = null;

        //验证登录参数不能为空
        if (ArgumentsValidator.checkUsernameAndPasswordNotNull(username, password)) {
            user = userService.loginByUsernameAndPassword(username, password);
        }
        //如果登录失败，设置状态码，然后返回
        if (user == null) {
            resultVo.setCode(LoginResult.USER_NOT_EXIST.getValue());
            resultVo.setMsg(LoginResult.USER_NOT_EXIST.getMsg());
            return resultVo;
        }

        //用户的密码错误
        if (user.getPassword() == null) {
            resultVo.setCode(LoginResult.PASSWORD_ERROR.getValue());
            resultVo.setMsg(LoginResult.PASSWORD_ERROR.getMsg());
            return resultVo;
        }

        //用户状态判断
        //end 用户状态判断

        //获取当前登录的管理员的权限
        Role role = userService.getUserRole(user.getUserId());
        resultVo.setAdminDatas("permission", role.getRoleId());

        System.out.println("login: loginAdminUser:"+user+ "  role:"+role);

        session.setAttribute("loginAdminUser", user);
        session.setAttribute("role", role);

        //登录成功之后的封装的参数
        resultVo.setCode(LoginResult.SUCCESS.getValue());
        resultVo.setMsg(LoginResult.SUCCESS.getMsg());
        resultVo.setAdminDatas("userId", user.getUserId());
        resultVo.setAdminDatas("username", user.getUsername());

        return resultVo;
    }

    @RequestMapping("/addAdmin")
    @ResponseBody
    public ResultVo addAdmin(String userId, String username, String password, String permission, HttpSession session, HttpServletRequest request) {
        ResultVo resultVo = new ResultVo();
        BaseUser user ;

        //验证登录参数不能为空
        if (!ArgumentsValidator.checkUsernameAndPasswordNotNull(username, password)) {
            resultVo.setCode(AddResult.FIELD_NULL.getValue());
            resultVo.setMsg(AddResult.FIELD_NULL.getMsg());
            return resultVo;
        }

        //验证用户名不能重复
        if (userService.getUserByUsername(username) != null) {
            resultVo.setCode(AddResult.DOUBLE_USERNAME.getValue());
            resultVo.setMsg(AddResult.DOUBLE_USERNAME.getMsg());
            return resultVo;
        }

        // 验证当前登录的管理员的权限
//        BaseUser adminUser = (BaseUser)session.getAttribute("loginAdminUser");
//        if (adminUser == null) {
//            resultVo.setCode(LoginResult.USER_NOT_LOGIN.getValue());
//            resultVo.setMsg(LoginResult.USER_NOT_LOGIN.getMsg());
//            return resultVo;
//        }
//        Role role = (Role)session.getAttribute("role");
//        if (Integer.parseInt(role.getRoleId()) > Integer.parseInt(UserPermision.NOTDELETEORUPDATE.getValue())) {
//            resultVo.setCode(AddResult.NOT_PERMISSION.getValue());
//            resultVo.setMsg(AddResult.NOT_PERMISSION.getMsg());
//            return resultVo;
//        }
        // end 验证当前登录的管理员的权限

        // 如果没有输入userId则使用系统生成的userId
        if (userId == null) {
            userId = UUIDTools.getUUIDByTime_M();
        }

        // 判断userId只能为普通的数字，里面不能包含字符
        try {
            Integer.parseInt(userId);
        }catch (Exception e) {
            e.printStackTrace();
            resultVo.setCode(AddResult.USERID_NOT_NUMBER.getValue());
            resultVo.setMsg(AddResult.USERID_NOT_NUMBER.getMsg());
            return resultVo;
        }

        // 加上前缀
        userId = PRE_STR_OF_USERID + userId;

        //添加管理员用户信息
        user = new BaseUser(userId, username, password);

        Role userRole = new Role();
        userRole.setRoleId(permission);
        if (userService.addAdminUserAndRole(user, userRole) < 0) {
            resultVo.setCode(AddResult.FAILED_IN_INSERT.getValue());
            resultVo.setMsg(AddResult.FAILED_IN_INSERT.getMsg());
            return resultVo;
        }

        resultVo.setCode(AddResult.SUCCESS.getValue());
        resultVo.setMsg(AddResult.SUCCESS.getMsg());
        resultVo.setData(user);
        return resultVo;
    }

    @RequestMapping("/getAdmin")
    @ResponseBody
    public AdminResultVo getAdmin(String username, HttpSession session, HttpServletRequest request) {
        AdminResultVo resultVo = new AdminResultVo();
        BaseUser adminUser = null;

        //验证参数的信息是否不为空
        if (username == null || username.trim().length() <= 0) {
            resultVo.setCode(AddResult.FIELD_NULL.getValue());
            resultVo.setMsg(AddResult.FIELD_NULL.getMsg());
            return  resultVo;
        }

        // 验证当前登录的管理员的权限
//        BaseUser loginAdminUser = (BaseUser)session.getAttribute("loginAdminUser");
//        if (loginAdminUser == null) {
//            resultVo.setCode(LoginResult.USER_NOT_LOGIN.getValue());
//            resultVo.setMsg(LoginResult.USER_NOT_LOGIN.getMsg());
//            return resultVo;
//        }
//        Role loginAdminUserRole = (Role)session.getAttribute("role");
//        if (Integer.parseInt(loginAdminUserRole.getRoleId()) > Integer.parseInt(UserPermision.ONLYSELECT.getValue())) {
//            resultVo.setCode(AddResult.NOT_PERMISSION.getValue());
//            resultVo.setMsg(AddResult.NOT_PERMISSION.getMsg());
//            return resultVo;
//        }
        // end 验证当前登录的管理员的权限

        //这个地方的带有状态筛选，OFF为用户被删除状态， ON为用户正常在线的状态
        adminUser = userService.getUserByUsername(username);

        //如果没有找到该管理员用户的话
        if (adminUser == null || adminUser.getUsername() ==null || adminUser.getUsername().trim().length() <=0) {
            resultVo.setCode(LoginResult.USER_NOT_EXIST.getValue());
            resultVo.setMsg(LoginResult.USER_NOT_EXIST.getMsg());
            return  resultVo;
        }

        //获取该管理员用户的权限信息
        Role role = userService.getUserRole(adminUser.getUserId());

        resultVo.setCode(AddResult.SUCCESS.getValue());
        resultVo.setMsg(AddResult.SUCCESS.getMsg());
        resultVo.setAdminDatas("userId", adminUser.getUserId());
        resultVo.setAdminDatas("username", adminUser.getUsername());
        resultVo.setAdminDatas("password", adminUser.getPassword());
        resultVo.setAdminDatas("permission", role.getRoleId());

        return resultVo;
    }

    @RequestMapping("/listAllAdmin")
    @ResponseBody
    public AdminResultVo listAllAdmin(String username, int curPage, int pageNum, HttpSession session, HttpServletRequest request) {
        AdminResultVo resultVo = new AdminResultVo();
        BaseUser user = null;

        //验证参数
        if (username == null || username.trim().length() <=0 ) {
            resultVo.setCode(AddResult.FIELD_NULL.getValue());
            resultVo.setMsg(AddResult.FIELD_NULL.getMsg());
            return resultVo;
        }

        //获取username对应的管理员用户的信息
        user = userService.getUserByUsername(username);
        if (user == null) {
            resultVo.setMsg(LoginResult.USER_NOT_EXIST.getMsg()+"，找不到该管理员用户");
            resultVo.setCode(LoginResult.USER_NOT_EXIST.getValue());
            return resultVo;
        }

        //获取当前登录的管理员的权限
        Role role = userService.getUserRole(user.getUserId());
        if (!role.getRoleId().equals(UserPermision.SADMIN.getValue())) {
            resultVo.setCode(AddResult.NOT_PERMISSION.getValue());
            resultVo.setMsg(AddResult.NOT_PERMISSION.getMsg());
            return resultVo;
        }

        List<BaseUserWrap> adminListsWrap = new ArrayList<>();
        List<BaseUser> adminLists;
        //如果是超级管理员，获取所有的一般管理员信息
        adminLists = userService.getAllAdmin((curPage - 1)*pageNum, pageNum);
        BaseUserWrap baseUserWrap = new BaseUserWrap();
        Role adminRole;
        //获取每个管理员信息的权限
        for (BaseUser baseUser : adminLists) {
            System.out.println(baseUser);
            baseUserWrap.setUserId(baseUser.getUserId());
            baseUserWrap.setPassword(baseUser.getPassword());
            baseUserWrap.setStatus(baseUser.getStatus());
            baseUserWrap.setUsername(baseUser.getUsername());
            baseUserWrap.setAddress(baseUser.getAddress());
            baseUserWrap.setEmail(baseUser.getEmail());
            baseUserWrap.setRealname(baseUser.getRealname());
            baseUserWrap.setTelephone(baseUser.getTelephone());

            adminRole = userService.getUserRole(baseUser.getUserId());

            baseUserWrap.setPermission(adminRole.getRoleId());

            System.out.println("baseUserWrap:"+baseUserWrap);
            adminListsWrap.add(baseUserWrap);
            baseUserWrap = new BaseUserWrap();
        }

        resultVo.setAdminDatas("adminLists", adminListsWrap);
        resultVo.setCode(AddResult.SUCCESS.getValue());
        resultVo.setMsg(AddResult.SUCCESS.getMsg());

        return resultVo;
    }

    /**
     * 管理员信息修改
     * @author wzy
     */
    @RequestMapping("/updateAdmin")
    @ResponseBody
    public AdminResultVo updateAdmin(String userId, String username, String password, String permission,
                                     HttpSession session, HttpServletRequest request) {
        AdminResultVo resultVo = new AdminResultVo();
        BaseUser user = null;
        int permissionI = 0;

        //Todo:用户状态与权限判断

        //参数完整性
        if(userId == null || username == null || password == null || permission == null){
            resultVo.setCode(1);
            resultVo.setMsg("缺失必要参数");
            return resultVo;
        }

        //Todo:字符转义?
        //permission整数
        try{
            permissionI = Integer.valueOf(permission);
        }catch(Exception e){
            resultVo.setCode(2);
            resultVo.setMsg("非法的权限设置，必须为整数");
            return resultVo;
        }
        //permission check
        if(permissionI > 3 || permissionI < 0){
            resultVo.setCode(3);
            resultVo.setMsg("非法的权限设置，超出范围");
            return resultVo;
        }

        // 验证当前登录的管理员的权限
//        BaseUser loginAdminUser = (BaseUser)session.getAttribute("loginAdminUser");
//        if (loginAdminUser == null) {
//            resultVo.setCode(LoginResult.USER_NOT_LOGIN.getValue());
//            resultVo.setMsg(LoginResult.USER_NOT_LOGIN.getMsg());
//            return resultVo;
//        }
//        Role loginAdminUserRole = (Role)session.getAttribute("role");
//        if (Integer.parseInt(loginAdminUserRole.getRoleId()) > Integer.parseInt(UserPermision.NOTDELETE.getValue())) {
//            resultVo.setCode(AddResult.NOT_PERMISSION.getValue());
//            resultVo.setMsg(AddResult.NOT_PERMISSION.getMsg());
//            return resultVo;
//        }
        // end 验证当前登录的管理员的权限

        //按管理员Id更新管理员的userName,password,permission
        user = new BaseUser();
        user.setUserId(userId);
        user.setPassword(password);
        user.setUsername(username);

        int successNum = userService.updateByUserId(user, permission);

        if(successNum > 0){
            resultVo.setCode(0);
            resultVo.setMsg("修改成功");
            resultVo.setAdminDatas("username",username);
            resultVo.setAdminDatas("password",password);
            resultVo.setAdminDatas("permission", permission);
        }else if(successNum == -1){
            resultVo.setCode(4);
            resultVo.setMsg("角色用户关联不存在");
        }else if(successNum == 0){
            resultVo.setCode(5);
            resultVo.setMsg("用户不存在");
        }else{
            resultVo.setCode(6);
            resultVo.setMsg("未知错误");
        }
        return resultVo;
    }

    /**
     * 管理员信息删除
     * @author wzy
     */
    @RequestMapping("/deleteAdmin")
    @ResponseBody
    public AdminResultVo deleteAdmin(String userId, HttpSession session, HttpServletRequest request){
        //Todo:用户状态与权限判断
        AdminResultVo resultVo = new AdminResultVo();

        // 验证当前登录的管理员的权限
//        BaseUser loginAdminUser = (BaseUser)session.getAttribute("loginAdminUser");
//        if (loginAdminUser == null) {
//            resultVo.setCode(LoginResult.USER_NOT_LOGIN.getValue());
//            resultVo.setMsg(LoginResult.USER_NOT_LOGIN.getMsg());
//            return resultVo;
//        }
//        Role loginAdminUserRole = (Role)session.getAttribute("role");
//        if (Integer.parseInt(loginAdminUserRole.getRoleId()) > Integer.parseInt(UserPermision.ALL.getValue())) {
//            resultVo.setCode(AddResult.NOT_PERMISSION.getValue());
//            resultVo.setMsg(AddResult.NOT_PERMISSION.getMsg());
//            return resultVo;
//        }
        // end 验证当前登录的管理员的权限


        int successNum = userService.deleteByUserId(userId);
        if(successNum == 0){
            resultVo.setCode(1);
            resultVo.setMsg("用户不存在");
        }else{
            resultVo.setCode(0);
            resultVo.setMsg("删除成功");
        }
        return resultVo;
    }
}
