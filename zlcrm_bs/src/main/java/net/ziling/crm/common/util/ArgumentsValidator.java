package net.ziling.crm.common.util;

import net.ziling.crm.common.wrap.UserPermision;

/**
 * Description:
 * 检查字段不为空
 *
 * @author huaxin
 * @create 2018/01/09 16:45
 */
public class ArgumentsValidator {

    /**
     * 用来检测用户和密码不能为空
     * @param username
     * @param password
     * @return  判断结果的布尔值
     */
    public static boolean checkUsernameAndPasswordNotNull(String username, String password){
        if (username != null && password != null) {
            return true;
        }
        return false;
    }

    public static boolean checkPermissionExist(String permimission) {
        return true;
    }


}
