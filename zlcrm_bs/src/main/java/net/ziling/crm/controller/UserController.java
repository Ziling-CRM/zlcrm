package net.ziling.crm.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import net.ziling.crm.common.util.AdminResultVo;
import net.ziling.crm.common.util.CheckNotNull;
import net.ziling.crm.common.util.ResultVo;
import net.ziling.crm.common.wrap.LoginResult;
import net.ziling.crm.common.wrap.UserPermision;
import net.ziling.crm.entity.BaseUser;
import net.ziling.crm.entity.Role;
import net.ziling.crm.entity.wrap.AdminUserWrap;
import net.ziling.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Description:
 * 前端控制器
 *
 * @author huaxin
 * @create 2018/01/08 19:54
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public AdminResultVo login(String userName, String password, HttpSession session, HttpServletRequest request) {
        AdminResultVo resultVo = new AdminResultVo();
        BaseUser user = null;

        System.out.println(userName+"  "+ password);

        //验证登录参数不能为空
        if(CheckNotNull.checkUsernameAndPasswordNotNull(userName, password)) {
            user = userService.loginByUsernameAndPassword(userName, password);
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
        resultVo.setAdminDatas("permission",role.getRoleId());

        List<BaseUser> adminLists;
        //如果是超级管理员，获取所有的一般管理员信息
        if (role.getRoleId().equals(UserPermision.SADMIN.getValue())) {
            adminLists = userService.getAllAdmin();
            resultVo.setAdminDatas("adminLists", adminLists);
        }

        //设置登录状态信息
        session.setAttribute("user",user);

        //登录成功之后的封装的参数
        resultVo.setCode(LoginResult.SUCCESS.getValue());
        resultVo.setMsg(LoginResult.SUCCESS.getMsg());
        resultVo.setAdminDatas("userId", user.getUserId());
        resultVo.setAdminDatas("username", user.getUsername());

        return resultVo;
    }

    @RequestMapping("/addAdmin")
    @ResponseBody
    public ResultVo addAdmin(String userName, String password, HttpSession session, HttpServletRequest request){
        return null;
    }

}
