package net.ziling.crm.controller;

import net.ziling.crm.common.util.ResultVo;
import net.ziling.crm.common.wrap.AddResult;
import net.ziling.crm.entity.BaseUser;
import net.ziling.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/addUserBase")
    @ResponseBody
    public ResultVo addUserBase(String userId, String realname, String gender, String telephone,
                                String qq, String email, String creditCard, String creditAddress,
                                String alipay, String address, HttpSession session, HttpServletRequest request) {
        ResultVo resultVo = new ResultVo();
        BaseUser user = new BaseUser();

        //验证userId的有效性
        //1、userId不能为空
        if (userId == null || userId.trim().length() <= 0) {
            resultVo.setStatus_code(AddResult.FIELD_NULL.getValue());
            resultVo.setMsg(AddResult.FIELD_NULL.getMsg()+":userId不能为空");
            return resultVo;
        }
        //2、新增加的userId即表示为工号不能在数据库中存在
        //验证用户名不能重复
        if (userService.getUserByUserId(userId) != null) {
            resultVo.setStatus_code(AddResult.DOUBLE_USERNAME.getValue());
            resultVo.setMsg(AddResult.DOUBLE_USERNAME.getMsg());
            return resultVo;
        }

        //此处需要验证当前session中的登录状态的权限

        //end

        //封装用户信息
        user.setUserId(userId);
        user.setRealname(realname);
        user.setGender(gender);
        user.setTelephone(telephone);
        user.setEmail(email);
        user.setQq(qq);
        user.setCreditcard(creditCard);
        user.setCreditaddress(creditAddress);
        user.setAlipay(alipay);
        user.setAddress(address);
        try {
            userService.addBaseUser(user);
        }catch (Exception e) {
            e.printStackTrace();
            resultVo.setStatus_code(AddResult.FAILED_IN_INSERT.getValue());
            resultVo.setMsg(AddResult.FAILED_IN_INSERT.getMsg());
            return resultVo;
        }
        resultVo.setStatus_code(AddResult.SUCCESS.getValue());
        resultVo.setMsg(AddResult.SUCCESS.getMsg());
        return resultVo;
    }

}
