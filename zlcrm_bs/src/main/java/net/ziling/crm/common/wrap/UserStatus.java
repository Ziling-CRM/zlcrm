package net.ziling.crm.common.wrap;

/**
 * Description:
 * 表述用户的状态信息
 *
 * @author huaxin
 * @create 2018/01/10 15:21
 */
public enum UserStatus {

    UP(0, "用户处于上线状态"),
    ON(1, "用户存在于平台中"),
    OFF(2, "用户被删除"),
    LOCK(3, "用户被锁定");

    private int value;
    private String msg;

    private UserStatus(int value, String msg) {
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
