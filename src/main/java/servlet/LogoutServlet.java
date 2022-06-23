package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description: 实现注销登录功能
 * User: X2148
 * Date: 2022-06-20
 * Time: 23:21
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");

        // 1. 判断登录状态
        HttpSession session = req.getSession(false);
        if(session == null){
            //未登录
            String html = "<h3>当前未登录，无法注销</h3>";
            resp.getWriter().write(html);
            return;
        }
        // 2. 将session 中保存的 user删掉
        session.removeAttribute("user");
        // 3. 重定向到 blog_login.hmtl
        resp.sendRedirect("blog_login.html");
    }
}
