package net.ziling.common.wrap;

/**
 * Description:
 * 记录用户的登录状态的类型
 *
 * @author huaxin
 * @create 2018/01/09 16:51
 */
public enum LoginResult {

    SUCCESS(0, "登录成功"),
    USERNAMEORPASSWORD_ERROR(1, "用户名或密码错误"),
    USER_LOCKED(2, "用户被锁定");

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
