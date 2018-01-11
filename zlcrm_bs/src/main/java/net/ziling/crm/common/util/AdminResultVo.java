package net.ziling.crm.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * 管理员的返回视图
 *
 * @author huaxin
 * @create 2018/01/10 17:19
 */
public class AdminResultVo {

    public Integer code;
    public String msg;
    public Map<String, Object> data;

    public AdminResultVo(){
        data = new HashMap<>();
    }

    public void setAdminDatas(String dataName, Object data) {
        this.data.put(dataName, data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
