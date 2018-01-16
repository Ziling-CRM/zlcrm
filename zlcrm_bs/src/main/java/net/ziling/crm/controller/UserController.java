package net.ziling.crm.controller;

import net.ziling.crm.common.util.AdminResultVo;
import net.ziling.crm.common.util.UUIDTools;
import net.ziling.crm.common.wrap.GetUserResult;
import net.ziling.crm.common.wrap.UpdateUserResult;
import net.ziling.crm.common.wrap.LoginResult;
import net.ziling.crm.entity.*;
import net.ziling.crm.service.UserService;
import net.ziling.crm.common.util.ResultVo;
import net.ziling.crm.common.wrap.AddResult;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
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

    @RequestMapping("/addUserBase")
    @ResponseBody
    public ResultVo addUserBase(String userId, String realname, String gender, String telephone,
                                String qq, String email, String creditCard, String creditAddress,
                                String alipay, String address, HttpSession session, HttpServletRequest request) {
        ResultVo resultVo = new ResultVo();
        BaseUser user = new BaseUser();

        System.out.println("userId:"+userId+"  realname:" + realname);

        //此处需要验证当前session中的登录状态的权限

        //end

        //验证userId的有效性
        //1、userId不能为空
        if (userId == null || userId.trim().length() <= 0) {
            resultVo.setCode(AddResult.FIELD_NULL.getValue());
            resultVo.setMsg(AddResult.FIELD_NULL.getMsg()+":userId不能为空");
            return resultVo;
        }
        //2、新增加的userId即表示为工号不能在数据库中存在
        //验证用户名不能重复
        if (userService.judgeUserExist(userId) != null) {
            resultVo.setCode(AddResult.DOUBLE_USERNAME.getValue());
            resultVo.setMsg(AddResult.DOUBLE_USERNAME.getMsg());
            return resultVo;
        }

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
            resultVo.setCode(AddResult.FAILED_IN_INSERT.getValue());
            resultVo.setMsg(AddResult.FAILED_IN_INSERT.getMsg());
            return resultVo;
        }
        resultVo.setCode(AddResult.SUCCESS.getValue());
        resultVo.setMsg(AddResult.SUCCESS.getMsg());
        return resultVo;
    }

    @RequestMapping("/addUserDuty")
    @ResponseBody
    public ResultVo addUserDuty(String userId, String skill, String workYears, String post,
                                String company, String capacityRate, String creditRate, String rehireRate,
                                String checkRate, String checkNum, String proceedNum, String userIncome,
                                String monthIncome, HttpSession session, HttpServletRequest request) {
        ResultVo resultVo = new ResultVo();
        UserDuty userDuty = new UserDuty();
        Duty duty = new Duty();

        //首先进行当前管理员用户权限的检验
        // end 管理员权限的检验

        //验证userId的有效性
        //1、userId不能为空
        if (userId == null || userId.trim().length() <= 0) {
            resultVo.setCode(AddResult.FIELD_NULL.getValue());
            resultVo.setMsg(AddResult.FIELD_NULL.getMsg()+":userId不能为空");
            return resultVo;
        }
        //2、新增加的userId即表示为工号不能在数据库中存在
        //验证用户名必须先存在
        if (userService.judgeUserExist(userId) == null) {
            resultVo.setCode(AddResult.USER_NOT_FIND.getValue());
            resultVo.setMsg(AddResult.USER_NOT_FIND.getMsg());
            return resultVo;
        }

        //封装用户的职责信息
        duty.setDutyId(UUIDTools.getUUIDByTime_M());
        duty.setSkill(skill);
        duty.setWorkYears(workYears);
        duty.setPost(post);
        duty.setCompany(company);
        duty.setCapacityRate(capacityRate);
        duty.setCreditRate(creditRate);
        duty.setRehireRate(rehireRate);
        duty.setCheckRate(checkRate);
        //验证数字参数的正确性
        int cn;  int pn; float ui; float mi;
        try {
            cn = Integer.parseInt(checkNum);
            pn = Integer.parseInt(proceedNum);
            ui = Float.parseFloat(userIncome);
            mi = Float.parseFloat(monthIncome);
        }catch (Exception e) {
            e.printStackTrace();
            resultVo.setCode(AddResult.FIELD_NULL.getValue());
            resultVo.setMsg(AddResult.FIELD_NULL.getMsg()+", 只能为数字的字段中包含了非数字值");
            return resultVo;
        }
        duty.setCheckNum(cn);
        duty.setProceedNum(pn);
        duty.setUserIncome(ui);
        duty.setMonthIncome(mi);
        //封装用户职责表中的数据
        userDuty.setUserId(userId);
        userDuty.setDutyId(duty.getDutyId());
        // end 封装用户的职责信息

        //写入数据到职责表,写入数据到用户职责表进行用户和职责的关联操作
        switch (userService.addUserDuty(userDuty, duty)) {
            case 0: {
                resultVo.setCode(AddResult.SUCCESS.getValue());
                resultVo.setMsg(AddResult.SUCCESS.getMsg());
            }
                break;
            case 1: {
                resultVo.setCode(AddResult.FAILED_IN_INSERT.getValue());
                resultVo.setMsg(AddResult.SUCCESS.getMsg()+"，UserDuty的插入有误");
            }
                break;
            case 2: {
                resultVo.setCode(AddResult.FAILED_IN_INSERT.getValue());
                resultVo.setMsg(AddResult.SUCCESS.getMsg()+"，Duty的插入有误");
            }
                break;
            case 3: {
                resultVo.setCode(AddResult.FIELD_NULL.getValue());
                resultVo.setMsg(AddResult.FIELD_NULL.getMsg());
            }
                break;
            default: {
                resultVo.setCode(-1);
                resultVo.setMsg("不明错误，非法操作");
            }

        }
        return resultVo;
    }

    @RequestMapping("/addUserProject")
    @ResponseBody
    public ResultVo addUserProject(String userId, String projectName, String projectType, String projectPrice,
                                String projectIncome, String projectState, HttpSession session, HttpServletRequest request) {
        ResultVo resultVo = new ResultVo();
        UserProject userProject = new UserProject();
        Project project = new Project();

        //首先进行当前管理员用户权限的检验
        // end 管理员权限的检验

        //验证userId的有效性
        //1、userId不能为空
        if (userId == null || userId.trim().length() <= 0) {
            resultVo.setCode(AddResult.FIELD_NULL.getValue());
            resultVo.setMsg(AddResult.FIELD_NULL.getMsg()+":userId不能为空");
            return resultVo;
        }
        //2、新增加的userId即表示为工号不能在数据库中存在
        //验证用户名必须先存在
        if (userService.judgeUserExist(userId) == null) {
            resultVo.setCode(AddResult.USER_NOT_FIND.getValue());
            resultVo.setMsg(AddResult.USER_NOT_FIND.getMsg());
            return resultVo;
        }

        //开始封装用户的项目信息
        project.setProId(UUIDTools.getUUIDByTime_M());
        project.setProjectName(projectName);
        project.setProjectType(projectType);
        project.setProjectPrice(projectPrice);
        project.setProjectIncome(projectIncome);
        project.setProjectState(projectState);
        userProject.setUserId(userId);
        userProject.setProId(project.getProId());
        // end 封装用户的项目信息

        //写入数据到项目表,写入数据到用户项目表进行用户和职责的关联操作
        switch (userService.addUserProject(userProject, project)) {
            case 0: {
                resultVo.setCode(AddResult.SUCCESS.getValue());
                resultVo.setMsg(AddResult.SUCCESS.getMsg());
            }
            break;
            case 1: {
                resultVo.setCode(AddResult.FAILED_IN_INSERT.getValue());
                resultVo.setMsg(AddResult.SUCCESS.getMsg()+"，UserProject的插入有误");
            }
            break;
            case 2: {
                resultVo.setCode(AddResult.FAILED_IN_INSERT.getValue());
                resultVo.setMsg(AddResult.SUCCESS.getMsg()+"，Project的插入有误");
            }
            break;
            case 3: {
                resultVo.setCode(AddResult.FIELD_NULL.getValue());
                resultVo.setMsg(AddResult.FIELD_NULL.getMsg());
            }
            break;
            default: {
                resultVo.setCode(-1);
                resultVo.setMsg("不明错误，非法操作");
            }
        }

        return resultVo;
    }

    @RequestMapping("/listAllUser")
    @ResponseBody
    public ResultVo listAllUser(String userId, String duty, String username, HttpSession session, HttpServletRequest request) {
        ResultVo resultVo = new ResultVo();
        Map<String , String> limits = new HashMap<>();
        Map<String , List<BaseUser>> resultDate = new HashMap<>();
        List<BaseUser> userList;

        //首先进行当前管理员用户权限的检验
        // end 管理员权限的检验

        //验证userId的有效性
        //1、userId不能为空
        if (userId == null || userId.trim().length() <= 0) {
            resultVo.setCode(AddResult.FIELD_NULL.getValue());
            resultVo.setMsg(AddResult.FIELD_NULL.getMsg()+":userId不能为空");
            return resultVo;
        }
        //2、新增加的userId即表示为工号不能在数据库中存在

        // 验证参数username的可用性并封装参数

        // end 结束username的验证

        // 验证参数duty的可用性并封装参数

        // end 结束duty的验证

        userList = userService.getAllSelectedUser(limits);
        resultVo.setCode(AddResult.SUCCESS.getValue());
        resultVo.setMsg(AddResult.SUCCESS.getMsg());
        resultDate.put("userList", userList);
        resultVo.setData(resultDate);

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
        if(duties.size() != 0) {
            resultVo.setAdminDatas("skill", duties.get(0).getSkill());
            resultVo.setAdminDatas("workYears", duties.get(0).getWorkYears());
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
        }else{
            resultVo.setAdminDatas("skill", "");
            resultVo.setAdminDatas("workYears", "");
            resultVo.setAdminDatas("post", "");
            resultVo.setAdminDatas("company", "");
            resultVo.setAdminDatas("capacityRate", "");
            resultVo.setAdminDatas("creditRate", "");
            resultVo.setAdminDatas("hireRate", "");
            resultVo.setAdminDatas("checkRate", "");
            resultVo.setAdminDatas("checkNum", "");
            resultVo.setAdminDatas("proceedNum", "");
            resultVo.setAdminDatas("userIncome", "");
            resultVo.setAdminDatas("monthIncome", "");
        }
        resultVo.setAdminDatas("projectslist",projects);
        return resultVo;
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public AdminResultVo updateUser(HttpSession session, HttpServletRequest request) throws Exception{
        AdminResultVo resultVo = new AdminResultVo();
        BaseUser user = new BaseUser();
        Duty duty = new Duty();
        Project project = new Project();
        Map params = request.getParameterMap();

        //验证用户Id是否合法
        if(params.get("userId") == null){
            resultVo.setCode(UpdateUserResult.LACK_OF_USERID.getValue());
            resultVo.setMsg(UpdateUserResult.LACK_OF_USERID.getMsg());
            return resultVo;
        }
        //验证数字
        try {
            if (params.get("monthIncome") != null) {
                Float.parseFloat(params.get("monthIncome").toString());
            }
            if (params.get("userIncome") != null){
                Float.parseFloat(params.get("userIncome").toString());
            }
            if(params.get("proceedNum") != null){
                Integer.parseInt(params.get("proceedNum").toString());
            }
            if(params.get("checkNum") != null){
                Integer.parseInt(params.get("checkNum").toString());
            }
        }catch (Exception e){
            resultVo.setCode(UpdateUserResult.PARAMETER_NOT_VALID.getValue());
            resultVo.setMsg(UpdateUserResult.PARAMETER_NOT_VALID.getMsg());
            return resultVo;
        }
        BeanUtils.populate(user, params);
        BeanUtils.populate(duty, params);
        BeanUtils.populate(project, params);

        Map<String, Object> res = userService.updateUserInf(user,project,duty);
        resultVo.setCode(Integer.parseInt(res.get("Error").toString()));
        resultVo.setMsg(res.get("ErrorMsg").toString());
        return resultVo;
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public AdminResultVo deleteUser(String userId, HttpSession session, HttpServletRequest request){
        AdminResultVo resultVo = new AdminResultVo();

        if(userService.deleteByUserId(userId) == 0){
            resultVo.setCode(1);
            resultVo.setMsg("用户不存在");
        }else{
            resultVo.setCode(0);
            resultVo.setMsg("删除成功");
        }

        return resultVo;
    }
}
