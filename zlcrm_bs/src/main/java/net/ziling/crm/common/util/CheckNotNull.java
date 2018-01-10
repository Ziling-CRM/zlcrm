package net.ziling.crm.common.util;

/**
 * Description:
 * 检查字段不为空
 *
 * @author huaxin
 * @create 2018/01/09 16:45
 */
public class CheckNotNull {

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
}
