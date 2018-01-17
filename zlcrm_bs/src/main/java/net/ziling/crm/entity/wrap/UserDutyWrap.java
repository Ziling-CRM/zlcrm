package net.ziling.crm.entity.wrap;

/**
 * Description:
 * 将User和Duty进行封装
 *
 * @author huaxin
 * @create 2018/01/17 15:55
 */
public class UserDutyWrap {

    private String userId;
    private String realname;
    private String post;
    private String capacityRate;
    private String creditRate;

    public UserDutyWrap(String userId, String realname, String post, String capacityRate, String creditRate) {
        this.userId = userId;
        this.realname = realname;
        this.post = post;
        this.capacityRate = capacityRate;
        this.creditRate = creditRate;
    }

    public UserDutyWrap() {
        super();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getCapacityRate() {
        return capacityRate;
    }

    public void setCapacityRate(String capacityRate) {
        this.capacityRate = capacityRate;
    }

    public String getCreditRate() {
        return creditRate;
    }

    public void setCreditRate(String creditRate) {
        this.creditRate = creditRate;
    }
}
