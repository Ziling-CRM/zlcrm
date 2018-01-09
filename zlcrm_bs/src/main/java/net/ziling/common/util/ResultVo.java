package net.ziling.common.util;

/**
 * Description:
 * 响应结果基础视图对象 如需扩充自己的视图对象可以从该类继承
 *
 * @author huaxin
 * @create 2018/01/09 15:45
 */
public class ResultVo {

    private Integer status_code;
    private String msg;
    private Object data;

    public Integer getStatus_code() {
        return status_code;
    }

    public void setStatus_code(Integer status_code) {
        this.status_code = status_code;
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
