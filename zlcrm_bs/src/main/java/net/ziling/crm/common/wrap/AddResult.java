package net.ziling.crm.common.wrap;

/**
 * Description:
 * 添加信息的时候的结果提示信息
 *
 * @author huaxin
 * @create 2018/01/10 20:47
 */
public enum AddResult {

    SUCCESS(0, "操作成功"),
    FIELD_NULL(1, "存在非法空字段"),
    DOUBLE_USERNAME(2, "用户名重复了"),
    FAILED_IN_INSERT(3, "插入数据到数据库出错"),
    NOT_PERMISSION(4, "没有权限"),
    USERID_NOT_NUMBER(5, "工号userId只能是数字");


    private int value;
    private String msg;

    /**
     * 自动实例化，不允许外部进行实例化
     */
    private AddResult(int value, String msg) {
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
