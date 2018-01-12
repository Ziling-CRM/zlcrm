package net.ziling.crm.controller;

import net.ziling.crm.common.util.AdminResultVo;
import net.ziling.crm.entity.BaseUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Description:
 * 用户相关的前端控制器
 *
 * @author huaxin
 * @create 2018/01/12 15:27
 */
@Controller
@RequestMapping("/admin")
public class UserController {

    @RequestMapping("/addBaseUser")
    @ResponseBody
    public AdminResultVo addBaseUser(String username, String password, HttpSession session, HttpServletRequest request) {
        AdminResultVo resultVo = new AdminResultVo();
        BaseUser user = null;


        return resultVo;
    }

}
