package net.ziling.entity;

/**
 * Description:
 * 用户表的实体类
 *
 * @author huaxin
 * @create 2018/01/09 16:18
 */
public class User {

    private String username;
    private String telephone;
    private String password;

    //==getter  setter

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelphone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", telphone='" + telephone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
