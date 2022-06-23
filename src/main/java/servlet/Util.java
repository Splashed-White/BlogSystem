package servlet;

import model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * Description: 实现强制登录的工具类，用于检查用户的登录状态
 * User: X2148
 * Date: 2022-06-20
 * Time: 15:25
 */
public class Util {
    public static User checkLogin(HttpServletRequest req){
        HttpSession session = req.getSession(false); //如果没找到合法的sessionId,并自动不创建
        if(session == null){
            return null; //未登录
        }
        //返回登录用户的信息user
        User user = (User) session.getAttribute("user");
        return user;
    }
}
