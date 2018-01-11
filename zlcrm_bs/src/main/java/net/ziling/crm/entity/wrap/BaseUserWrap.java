package net.ziling.crm.entity.wrap;

import net.ziling.crm.entity.BaseUser;

/**
 * Description:
 * 对基础类的封装
 *
 * @author huaxin
 * @create 2018/01/11 16:43
 */
public class BaseUserWrap extends BaseUser {

    private String permission;

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }


    public String printLog() {
        return "BaseUserWrap{" +super.toString()+
                "permission='" + permission + '\'' +
                '}';
    }
}
