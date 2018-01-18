package net.ziling.crm.common.wrap;

/**
 * Description:
 * 用户的权限
 *
 * @author huaxin
 * @create 2018/01/10 16:43
 */
public enum UserPermision {

    SADMIN("*","超级管理员"),
    ALL("0", "所有权限"),
    NOTDELETE("1", "不能删除"),
    NOTDELETEORUPDATE("2", "不能删除或者修改"),
    ONLYSELECT("3", "只能查看");

    private String value;
    private String msg;
    public String[] permissions = {"*", "0", "1", "2", "3"};

    private UserPermision(String value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public String getValue() {
        return value;
    }

    public String getMsg() {
        return msg;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }
}
