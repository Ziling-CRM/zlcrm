package net.ziling.crm.common.util;

/**
 * Description:
 * 响应结果基础视图对象 如需扩充自己的视图对象可以从该类继承
 *
 * @author huaxin
 * @create 2018/01/09 15:45
 */
public class ResultVo {

    public Integer code;
    public String msg;
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
