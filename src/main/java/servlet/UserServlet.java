package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Blog;
import model.BlogDao;
import model.User;
import model.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: X2148
 * Date: 2022-06-20
 * Time: 16:12
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf8");
        ObjectMapper mapper = new ObjectMapper();

        // 1. 判断当前用户是否登录
        User user = Util.checkLogin(req);
        if(user == null){
            resp.setStatus(403);
            return;
        }
        // 2. 读取请求中的blogId参数
        String blogId = req.getParameter("blogId");
        if(blogId == null){
            System.out.println("未获取到blogId");
            // blog_list.html 获取登录用户信息
            String jsonString = mapper.writeValueAsString(user);
            resp.getWriter().write(jsonString);
            return;
        }else{
            // 获取文章作者信息
            System.out.println("获取到了blogId:" + blogId);
            // blogId -> blog -> userId -> user
            BlogDao blogDao = new BlogDao();
            Blog blog = blogDao.selectOne(Integer.parseInt(blogId));
            int userId = blog.getUserId();
            UserDao userDao = new UserDao();
            User author = userDao.selectById(userId);
            //判断该博客的作者是否为登陆用户
            author.setFlag(author.getUserId() == user.getUserId() ? 1 : 0);
            System.out.println("当前查询到的作者信息: " + author);
            String jsonString = mapper.writeValueAsString(author);
            resp.getWriter().write(jsonString);
        }
    }
}