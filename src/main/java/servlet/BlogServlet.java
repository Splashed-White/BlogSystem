package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Blog;
import model.BlogDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 处理博客列表和博客详情
 * User: X2148
 * Date: 2022-06-16
 * Time: 19:03
 */
@WebServlet("/blog")
public class BlogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf8");
        ObjectMapper mapper = new ObjectMapper();
        BlogDao blogDao = new BlogDao();

        //检查登录状态
        User user = Util.checkLogin(req);
        if(user == null){
            //未登录,重定向
            // ajax默认是不支持重定向的，它是局部刷新，不重新加载页面,所以不能使用resp.sendRedirect重定向
            // 此处使用 403 表示登录失败，通过js操作来实现页面跳转
            resp.setStatus(403);
            return;
        }

        String blogId = req.getParameter("blogId");
        if(blogId == null || "".equals(blogId)){
            //博客列表
            List<Blog> blogs = blogDao.selectAll();
            String jsonString = mapper.writeValueAsString(blogs);
            resp.getWriter().write(jsonString);
        }else{
            //博客详情
            Blog blog = blogDao.selectOne(Integer.parseInt(blogId));
            String jsonString = mapper.writeValueAsString(blog);
            resp.getWriter().write(jsonString);
        }
    }
    //提交博客
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        req.setCharacterEncoding("utf-8");

        // 1. 检测用户登陆状态
        User user = Util.checkLogin(req);
        if(user  == null){
            //未登录，跳转到登陆页面
            resp.sendRedirect("blog_list.html"); //form表单触发请求，可以返回302
            return;
        }
        // 2. 读取请求中的数据(title content)
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        if(title == null || "".equals(title) || content == null || "".equals(content)){
            String html = "<h3>当前提交的博客标题或正文为空，无法提交</h3>";
            resp.getWriter().write(html);
            return;
        }
        // 3. 构造blog对象，插入到数据库中
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setUserId(user.getUserId());
        blog.setPostTime(new Timestamp(System.currentTimeMillis()));

        BlogDao blogDao = new BlogDao();
        blogDao.insert(blog);
        // 4. 从定向到 blog_list.html
        resp.sendRedirect("blog_list.html");
    }
}
