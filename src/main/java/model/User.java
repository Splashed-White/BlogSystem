package model;

/**
 * Created with IntelliJ IDEA.
 * Description: 用于封装用户对象
 * User: X2148
 * Date: 2022-06-15
 * Time: 21:46
 */
public class User {
    private int userId;
    private String username;
    private String password;
    private int flag; //表示当前博客的作者是否为登录用户

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", flag=" + flag +
                '}';
    }
}
