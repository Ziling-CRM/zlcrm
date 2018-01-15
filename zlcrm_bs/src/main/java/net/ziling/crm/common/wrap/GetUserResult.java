package net.ziling.crm.common.wrap;
/**
 * Description:
 * 获取User信息的时候的结果提示信息
 *
 * @author wzy
 * @create 2018/01/15 9:28
 */
public enum GetUserResult {
    SUCCESS(0, "操作成功"),
    LACK_OF_PARAMETERS(1, "缺少参数"),
    USER_NOT_EXIST(2, "用户不存在"),
    DUTY_NOT_EXIST(3, "用户职责不存在"),
    PROJECT_NOT_EXIST(4, "用户项目不存在"),
    DUTY_ID_NOT_EXIST(5, "职责Id对应职责不存在"),
    PROJECT_ID_NOT_EXIST(6, "项目Id对应项目不存在");

    private int value;
    private String msg;

    /**
     * 自动实例化，不允许外部进行实例化
     */
    private GetUserResult(int value, String msg) {
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
