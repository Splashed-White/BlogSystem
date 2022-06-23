package servlet;

import model.User;
import model.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description: 实现登录页面
 * User: X2148
 * Date: 2022-06-20
 * Time: 13:23
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        req.setCharacterEncoding("utf-8");

        // 1. 读取用户名和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(username == null || "".equals(username) || password == null || "".equals(password)){
            String html = "<h3>用户名或密码缺失</h3>";
            resp.getWriter().write(html);
            return;
        }
        // 2. 在数据库中验证用户名密码
        UserDao userDao = new UserDao();
        User user = userDao.selectByName(username);
        if(user == null){
            //用户不存在
            String html = "<h3>用户名或密码错误</h3>";
            resp.getWriter().write(html);
            return;
        }
        if(!user.getPassword().equals(password)){
            //密码错误
            String html = "<h3>用户名或密码错误</h3>";
            resp.getWriter().write(html);
            return;
        }
        // 3. 登录成功，设置session
        HttpSession session = req.getSession(true);
        session.setAttribute("user",user);
        // 4. 重定向到 blog_list.html
        resp.sendRedirect("blog_list.html");
    }
}












