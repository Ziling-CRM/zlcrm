package net.ziling.controller;

import net.ziling.common.util.CheckNotNull;
import net.ziling.common.util.ResultVo;
import net.ziling.common.wrap.LoginResult;
import net.ziling.entity.User;
import net.ziling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public ResultVo login(String userName, String password, HttpSession session, HttpServletRequest request) {
        ResultVo resultVo = new ResultVo();
        User user = null;

        //验证登录参数不能为空
        if(CheckNotNull.checkUsernameAndPasswordNotNull(userName, password)) {
            user = userService.loginByUsernameAndPassword(userName, password);
        }
        //如果登录失败，设置状态码，然后返回
        if (user == null) {
            resultVo.setStatus_code(LoginResult.USERNAMEORPASSWORD_ERROR.getValue());
            resultVo.setMsg(LoginResult.USERNAMEORPASSWORD_ERROR.getMsg());
            return resultVo;
        }

        //登录成功之后的封装的参数
        resultVo.setStatus_code(LoginResult.SUCCESS.getValue());
        resultVo.setMsg(LoginResult.SUCCESS.getMsg());
        resultVo.setData(user);

        return resultVo;
    }

}
