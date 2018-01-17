package net.ziling.crm.common.wrap;

public enum UpdateUserResult {
    SUCCESS(0, "操作成功"),
    LACK_OF_USERID(1, "缺少用户Id"),
    USER_NOT_EXIST(2, "用户不存在"),
    HAS_WARNINGS(3, "警告"),
    DUTY_ID_NOT_EXIST(4, "警告：职责Id对应职责不存在；"),
    PROJECT_ID_NOT_EXIST(5, "警告：项目Id对应项目不存在；"),
    PARAMETER_ALL_NULL(6, "缺少可修改参数"),
    PARAMETER_NOT_VALID(7,"数字参数错误");

    private int value;
    private String msg;

    /**
     * 自动实例化，不允许外部进行实例化
     */
    private UpdateUserResult(int value, String msg) {
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
