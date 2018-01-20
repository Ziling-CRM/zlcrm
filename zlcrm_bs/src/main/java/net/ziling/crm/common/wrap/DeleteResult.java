package net.ziling.crm.common.wrap;

public enum DeleteResult {
    SUCCESS(0, "删除客户项目！"),
    LACK_OF_ID(1, "缺少必要参数！"),
    USER_NOT_EXIST(2, "用户不存在！"),
    USER_PROJECT_NOT_EXIST(3, "要删除的项目不属于用户！"),
    ID_PROJECT_NOT_EXIST(4, "项目不存在！");

    private int value;
    private String msg;

    /**
     * 自动实例化，不允许外部进行实例化
     */
    private DeleteResult(int value, String msg) {
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
