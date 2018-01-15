package net.ziling.crm.controller;

import net.ziling.crm.common.util.AdminResultVo;
import net.ziling.crm.common.wrap.GetUserResult;
import net.ziling.crm.entity.BaseUser;
import net.ziling.crm.entity.Duty;
import net.ziling.crm.entity.Project;
import net.ziling.crm.service.UserService;
import org.apache.taglibs.standard.tag.common.fmt.RequestEncodingSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.DimensionUIResource;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/addBaseUser")
    @ResponseBody
    public AdminResultVo addBaseUser(String username, String password, HttpSession session, HttpServletRequest request) {
        AdminResultVo resultVo = new AdminResultVo();
        BaseUser user = null;


        return resultVo;
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public  AdminResultVo getUser(String userId, HttpSession session, HttpServletRequest request){
        AdminResultVo resultVo = new AdminResultVo();
        //TODO:权限

        if(userId == null){
            resultVo.setCode(GetUserResult.LACK_OF_PARAMETERS.getValue());
            resultVo.setMsg("缺少参数");
            return resultVo;
        }

        Map<String, Object> userResult = userService.getUserByUserId(userId);
        resultVo.setCode(Integer.parseInt(userResult.get("Error").toString()));
        resultVo.setMsg(userResult.get("ErrorMsg").toString());

        if(Integer.parseInt(userResult.get("Error").toString()) != GetUserResult.SUCCESS.getValue()){
            return resultVo;
        }

        BaseUser user = (BaseUser)(userResult.get("User"));
        List<Duty> duties = (List<Duty>)(userResult.get("Duties"));
        List<Project> projects = (List<Project>)(userResult.get("Projects"));

        resultVo.setAdminDatas("userId", userId);
        resultVo.setAdminDatas("realname",user.getRealname());
        resultVo.setAdminDatas("gender",user.getGender());
        resultVo.setAdminDatas("telephone",user.getTelephone());
        resultVo.setAdminDatas("qq",user.getQq());
        resultVo.setAdminDatas("email",user.getEmail());
        resultVo.setAdminDatas("creditCard",user.getCreditcard());
        resultVo.setAdminDatas("creditAddress",user.getCreditaddress());
        resultVo.setAdminDatas("alipay",user.getAlipay());
        resultVo.setAdminDatas("address",user.getAddress());
        resultVo.setAdminDatas("skill", duties.get(0).getSkill());
        resultVo.setAdminDatas("workYears",duties.get(0).getWorkYears());
        resultVo.setAdminDatas("post", duties.get(0).getPost());
        resultVo.setAdminDatas("company", duties.get(0).getCompany());
        resultVo.setAdminDatas("capacityRate", duties.get(0).getCapacityRate());
        resultVo.setAdminDatas("creditRate", duties.get(0).getCreditRate());
        resultVo.setAdminDatas("hireRate", duties.get(0).getRehireRate());
        resultVo.setAdminDatas("checkRate", duties.get(0).getCheckRate());
        resultVo.setAdminDatas("checkNum", duties.get(0).getCheckNum());
        resultVo.setAdminDatas("proceedNum", duties.get(0).getProceedNum());
        resultVo.setAdminDatas("userIncome", duties.get(0).getUserIncome());
        resultVo.setAdminDatas("monthIncome", duties.get(0).getMonthIncome());
        resultVo.setAdminDatas("projectslist",projects);
        return resultVo;
    }
}
