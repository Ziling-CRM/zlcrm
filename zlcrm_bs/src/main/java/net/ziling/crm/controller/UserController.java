package net.ziling.crm.controller;

import net.ziling.crm.common.util.AdminResultVo;
import net.ziling.crm.common.util.UUIDTools;
import net.ziling.crm.common.wrap.*;
import net.ziling.crm.entity.*;
import net.ziling.crm.service.UserService;
import net.ziling.crm.common.util.ResultVo;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.annotations.Delete;
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

        // 验证当前登录的管理员的权限
        BaseUser adminUser = (BaseUser)session.getAttribute("loginAdminUser");
        if (adminUser == null) {
            resultVo.setCode(LoginResult.USER_NOT_LOGIN.getValue());
            resultVo.setMsg(LoginResult.USER_NOT_LOGIN.getMsg());
            return resultVo;
        }
        Role role = (Role)session.getAttribute("role");
        if (Integer.parseInt(role.getRoleId()) > Integer.parseInt(UserPermision.NOTDELETEORUPDATE.getValue())) {
            resultVo.setCode(AddResult.NOT_PERMISSION.getValue());
            resultVo.setMsg(AddResult.NOT_PERMISSION.getMsg());
            return resultVo;
        }
        // end 验证当前登录的管理员的权限

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

        // 验证当前登录的管理员的权限
        BaseUser adminUser = (BaseUser)session.getAttribute("loginAdminUser");
        if (adminUser == null) {
            resultVo.setCode(LoginResult.USER_NOT_LOGIN.getValue());
            resultVo.setMsg(LoginResult.USER_NOT_LOGIN.getMsg());
            return resultVo;
        }
        Role role = (Role)session.getAttribute("role");
        if (Integer.parseInt(role.getRoleId()) > Integer.parseInt(UserPermision.NOTDELETEORUPDATE.getValue())) {
            resultVo.setCode(AddResult.NOT_PERMISSION.getValue());
            resultVo.setMsg(AddResult.NOT_PERMISSION.getMsg());
            return resultVo;
        }
        // end 验证当前登录的管理员的权限

        //验证userId的有效性
        //1、userId不能为空
        if (userId == null || userId.trim().length() <= 0) {
            resultVo.setCode(AddResult.FIELD_NULL.getValue());
            resultVo.setMsg(AddResult.FIELD_NULL.getMsg()+":userId不能为空");
            return resultVo;
        }
        //2、新增加的userId即表示为工号在数据库中存在
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

        // 验证当前登录的管理员的权限
        BaseUser adminUser = (BaseUser)session.getAttribute("loginAdminUser");
        if (adminUser == null) {
            resultVo.setCode(LoginResult.USER_NOT_LOGIN.getValue());
            resultVo.setMsg(LoginResult.USER_NOT_LOGIN.getMsg());
            return resultVo;
        }
        Role role = (Role)session.getAttribute("role");
        if (Integer.parseInt(role.getRoleId()) > Integer.parseInt(UserPermision.NOTDELETEORUPDATE.getValue())) {
            resultVo.setCode(AddResult.NOT_PERMISSION.getValue());
            resultVo.setMsg(AddResult.NOT_PERMISSION.getMsg());
            return resultVo;
        }
        // end 验证当前登录的管理员的权限

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
    public ResultVo listAllUser(String userId, String duty, String realname, HttpSession session, HttpServletRequest request) {
        ResultVo resultVo = new ResultVo();
        Map<String , Object> limits = new HashMap<>();
        Map<String , List<BaseUser>> resultDate = new HashMap<>();
        List<BaseUser> userList;

        // 验证当前登录的管理员的权限
        BaseUser loginAdminUser = (BaseUser)session.getAttribute("loginAdminUser");
        if (loginAdminUser == null) {
            resultVo.setCode(LoginResult.USER_NOT_LOGIN.getValue());
            resultVo.setMsg(LoginResult.USER_NOT_LOGIN.getMsg());
            return resultVo;
        }
        Role loginAdminUserRole = (Role)session.getAttribute("role");
        if (Integer.parseInt(loginAdminUserRole.getRoleId()) > Integer.parseInt(UserPermision.ONLYSELECT.getValue())) {
            resultVo.setCode(AddResult.NOT_PERMISSION.getValue());
            resultVo.setMsg(AddResult.NOT_PERMISSION.getMsg());
            return resultVo;
        }
        // end 验证当前登录的管理员的权限

        limits.put("userId",userId);
        limits.put("duty",duty);
        limits.put("realname",realname);


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

        // 验证当前登录的管理员的权限
        BaseUser loginAdminUser = (BaseUser)session.getAttribute("loginAdminUser");
        if (loginAdminUser == null) {
            resultVo.setCode(LoginResult.USER_NOT_LOGIN.getValue());
            resultVo.setMsg(LoginResult.USER_NOT_LOGIN.getMsg());
            return resultVo;
        }
        Role loginAdminUserRole = (Role)session.getAttribute("role");
        if (Integer.parseInt(loginAdminUserRole.getRoleId()) > Integer.parseInt(UserPermision.ONLYSELECT.getValue())) {
            resultVo.setCode(AddResult.NOT_PERMISSION.getValue());
            resultVo.setMsg(AddResult.NOT_PERMISSION.getMsg());
            return resultVo;
        }
        // end 验证当前登录的管理员的权限

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

        resultVo.setAdminDatas("baseUser", user);
        resultVo.setAdminDatas("duty", duties);

        resultVo.setAdminDatas("projectsList",projects);
        return resultVo;
    }

    @RequestMapping("/updateUserBase")
    @ResponseBody
    public AdminResultVo updateUserBase(HttpSession session, HttpServletRequest request) throws Exception{
        AdminResultVo resultVo = new AdminResultVo();
        BaseUser user = new BaseUser();
        Map params = request.getParameterMap();

        // 验证当前登录的管理员的权限
        BaseUser loginAdminUser = (BaseUser)session.getAttribute("loginAdminUser");
        if (loginAdminUser == null) {
            resultVo.setCode(LoginResult.USER_NOT_LOGIN.getValue());
            resultVo.setMsg(LoginResult.USER_NOT_LOGIN.getMsg());
            return resultVo;
        }
        Role loginAdminUserRole = (Role)session.getAttribute("role");
        if (Integer.parseInt(loginAdminUserRole.getRoleId()) > Integer.parseInt(UserPermision.NOTDELETE.getValue())) {
            resultVo.setCode(AddResult.NOT_PERMISSION.getValue());
            resultVo.setMsg(AddResult.NOT_PERMISSION.getMsg());
            return resultVo;
        }
        // end 验证当前登录的管理员的权限

        //验证用户Id是否合法
        if(params.get("userId") == null){
            resultVo.setCode(UpdateUserResult.LACK_OF_USERID.getValue());
            resultVo.setMsg(UpdateUserResult.LACK_OF_USERID.getMsg());
            return resultVo;
        }
        BeanUtils.populate(user, params);
        if(user.isUpdatable()){
            if(userService.updateUserInf(user)!=0) {
                resultVo.setCode(UpdateUserResult.USER_NOT_EXIST.getValue());
                resultVo.setMsg(UpdateUserResult.USER_NOT_EXIST.getMsg());
            }else{
                resultVo.setCode(UpdateUserResult.SUCCESS.getValue());
                resultVo.setMsg(UpdateUserResult.SUCCESS.getMsg());
            }
        }else{
            resultVo.setCode(UpdateUserResult.PARAMETER_ALL_NULL.getValue());
            resultVo.setMsg(UpdateUserResult.PARAMETER_ALL_NULL.getMsg());
        }
        return resultVo;
    }

    @RequestMapping("/updateUserDuty")
    @ResponseBody
    public AdminResultVo updateUserDuty(String userId, String monthIncome, String userIncome,
                                    String checkNum, String proceedNum, HttpSession session,
                                    HttpServletRequest request)throws Exception{
        AdminResultVo resultVo = new AdminResultVo();
        Duty duty = new Duty();
        Map params = request.getParameterMap();
        BeanUtils.populate(duty, params);
        int res = 0;

        // 验证当前登录的管理员的权限
        BaseUser loginAdminUser = (BaseUser)session.getAttribute("loginAdminUser");
        if (loginAdminUser == null) {
            resultVo.setCode(LoginResult.USER_NOT_LOGIN.getValue());
            resultVo.setMsg(LoginResult.USER_NOT_LOGIN.getMsg());
            return resultVo;
        }
        Role loginAdminUserRole = (Role)session.getAttribute("role");
        if (Integer.parseInt(loginAdminUserRole.getRoleId()) > Integer.parseInt(UserPermision.NOTDELETE.getValue())) {
            resultVo.setCode(AddResult.NOT_PERMISSION.getValue());
            resultVo.setMsg(AddResult.NOT_PERMISSION.getMsg());
            return resultVo;
        }
        // end 验证当前登录的管理员的权限

        //验证用户Id是否合法
        if(params.get("userId") == null || params.get("dutyId") == null){
            resultVo.setCode(UpdateUserResult.LACK_OF_USERID.getValue());
            resultVo.setMsg(UpdateUserResult.LACK_OF_USERID.getMsg());
            return resultVo;
        }

        //验证数字
        try {
            if (params.get("monthIncome") != null) {
                if(Float.parseFloat(monthIncome) < 0){
                    throw new Exception();
                }
            }
            if (params.get("userIncome") != null){
                if (Float.parseFloat(userIncome) < 0) {
                    throw new Exception();
                }
            }
            if(params.get("proceedNum") != null){
                if (Integer.parseInt(proceedNum) < 0) {
                    throw new Exception();
                }
            }
            if(params.get("checkNum") != null){
                if(Integer.parseInt(checkNum) < 0){
                    throw new Exception();
                }
            }
        }catch (Exception e){
            resultVo.setCode(UpdateUserResult.PARAMETER_NOT_VALID.getValue());
            resultVo.setMsg(UpdateUserResult.PARAMETER_NOT_VALID.getMsg());
            return resultVo;
        }

        res = userService.updateUserDuty(duty, userId);
        if(duty.isUpdatable()){
            if(res == -1){
                resultVo.setCode(UpdateUserResult.DUTY_ID_NOT_EXIST.getValue());
                resultVo.setMsg(UpdateUserResult.DUTY_ID_NOT_EXIST.getMsg());
            }else if(res == -2) {
                resultVo.setCode(UpdateUserResult.DUTY_NOT_EXIST.getValue());
                resultVo.setMsg(UpdateUserResult.DUTY_NOT_EXIST.getMsg());
            }else{
                resultVo.setCode(UpdateUserResult.SUCCESS.getValue());
                resultVo.setMsg(UpdateUserResult.SUCCESS.getMsg());
            }
        }else{
            resultVo.setCode(UpdateUserResult.PARAMETER_ALL_NULL.getValue());
            resultVo.setMsg(UpdateUserResult.PARAMETER_ALL_NULL.getMsg());
        }

        return resultVo;
    }

    @RequestMapping("/updateUserProject")
    @ResponseBody
    public AdminResultVo updateUserProject(String userId, HttpSession session, HttpServletRequest request)throws Exception{
        AdminResultVo resultVo = new AdminResultVo();
        Project project = new Project();
        Map params = request.getParameterMap();
        BeanUtils.populate(project, params);
        int res = 0;

        // 验证当前登录的管理员的权限
        BaseUser loginAdminUser = (BaseUser)session.getAttribute("loginAdminUser");
        if (loginAdminUser == null) {
            resultVo.setCode(LoginResult.USER_NOT_LOGIN.getValue());
            resultVo.setMsg(LoginResult.USER_NOT_LOGIN.getMsg());
            return resultVo;
        }
        Role loginAdminUserRole = (Role)session.getAttribute("role");
        if (Integer.parseInt(loginAdminUserRole.getRoleId()) > Integer.parseInt(UserPermision.NOTDELETE.getValue())) {
            resultVo.setCode(AddResult.NOT_PERMISSION.getValue());
            resultVo.setMsg(AddResult.NOT_PERMISSION.getMsg());
            return resultVo;
        }
        // end 验证当前登录的管理员的权限

        //验证用户Id是否合法
        if(userId == null || params.get("proId") == null){
            resultVo.setCode(UpdateUserResult.LACK_OF_USERID.getValue());
            resultVo.setMsg(UpdateUserResult.LACK_OF_USERID.getMsg());
            return resultVo;
        }

        if(project.isUpdatable()){
            res = userService.updateUserProject(project, userId);
            if(res == -1) {
                resultVo.setCode(UpdateUserResult.PROJECT_ID_NOT_EXIST.getValue());
                resultVo.setMsg(UpdateUserResult.PROJECT_ID_NOT_EXIST.getMsg());
            }else if(res == -2){
                resultVo.setCode(UpdateUserResult.PROJECT_NOT_EXIST.getValue());
                resultVo.setMsg(UpdateUserResult.PROJECT_NOT_EXIST.getMsg());
            }else{
                resultVo.setCode(UpdateUserResult.SUCCESS.getValue());
                resultVo.setMsg(UpdateUserResult.SUCCESS.getMsg());
            }
        }else{
            resultVo.setCode(UpdateUserResult.PARAMETER_ALL_NULL.getValue());
            resultVo.setMsg(UpdateUserResult.PARAMETER_ALL_NULL.getMsg());
        }

        return resultVo;
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public AdminResultVo deleteUser(String userId, HttpSession session, HttpServletRequest request){
        AdminResultVo resultVo = new AdminResultVo();

        // 验证当前登录的管理员的权限
        BaseUser loginAdminUser = (BaseUser)session.getAttribute("loginAdminUser");
        if (loginAdminUser == null) {
            resultVo.setCode(LoginResult.USER_NOT_LOGIN.getValue());
            resultVo.setMsg(LoginResult.USER_NOT_LOGIN.getMsg());
            return resultVo;
        }
        Role loginAdminUserRole = (Role)session.getAttribute("role");
        if (Integer.parseInt(loginAdminUserRole.getRoleId()) > Integer.parseInt(UserPermision.ALL.getValue())) {
            resultVo.setCode(AddResult.NOT_PERMISSION.getValue());
            resultVo.setMsg(AddResult.NOT_PERMISSION.getMsg());
            return resultVo;
        }
        // end 验证当前登录的管理员的权限

        if(userService.deleteByUserId(userId) == 0){
            resultVo.setCode(1);
            resultVo.setMsg("用户不存在");
        }else{
            resultVo.setCode(0);
            resultVo.setMsg("删除成功");
        }

        return resultVo;
    }

    @RequestMapping("/deleteUserProject")
    @ResponseBody
    public AdminResultVo deleteProject(String userId, String proId,  HttpSession session, HttpServletRequest request){
        AdminResultVo resultVo = new AdminResultVo();
        DeleteResult res;

        /*// 验证当前登录的管理员的权限
        BaseUser loginAdminUser = (BaseUser)session.getAttribute("loginAdminUser");
        if (loginAdminUser == null) {
            resultVo.setCode(LoginResult.USER_NOT_LOGIN.getValue());
            resultVo.setMsg(LoginResult.USER_NOT_LOGIN.getMsg());
            return resultVo;
        }
        Role loginAdminUserRole = (Role)session.getAttribute("role");
        if (Integer.parseInt(loginAdminUserRole.getRoleId()) > Integer.parseInt(UserPermision.ALL.getValue())) {
            resultVo.setCode(AddResult.NOT_PERMISSION.getValue());
            resultVo.setMsg(AddResult.NOT_PERMISSION.getMsg());
            return resultVo;
        }
        // end 验证当前登录的管理员的权限*/

        res = userService.deleteProject(userId, proId);
        resultVo.setCode(res.getValue());
        resultVo.setMsg(res.getMsg());
        return resultVo;
    }
}
