package net.ziling.crm.common.wrap;

/**
 * Description:
 * 记录用户的登录状态的类型
 *
 * @author huaxin
 * @create 2018/01/09 16:51
 */
public enum LoginResult {

    SUCCESS(0, "登录成功"),
    USER_NOT_EXIST(1, "找不到该用户"),
    PASSWORD_ERROR(2, "密码错误"),
    USER_LOCKED(3, "用户被锁定");

    private int value;
    private String msg;

    private LoginResult(int value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public int getValue() {
        return value;
    }

    public String getMsg() {
        return msg;
    }

}
