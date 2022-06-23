package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: X2148
 * Date: 2022-06-15
 * Time: 21:47
 */
public class BlogDao {
    // 1. insert  -> blog_list
    public void insert(Blog blog){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "insert into blog values(null,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,blog.getTitle());
            statement.setString(2,blog.getContent());
            statement.setTimestamp(3,blog.getPostTime());
            statement.setInt(4,blog.getUserId());
            int ret = statement.executeUpdate();
            if(ret == 1){
                System.out.println("博客新增成功");
            }else{
                System.out.println("博客新增失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,null);
        }
    }
    // 2. selectAll -> blog_list
    public List<Blog> selectAll(){
        List<Blog> blogs = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from blog";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                Blog blog = new Blog();
                blog.setBlogId(resultSet.getInt("blogId"));
                blog.setTitle(resultSet.getString("title"));
                String content = resultSet.getString("content");
                if (content.length() > 100) {
                    content = content.substring(0, 100) + "...";
                }
                blog.setContent(content);
                blog.setPostTime(resultSet.getTimestamp("postTime"));
                blog.setUserId(resultSet.getInt("userId"));
                blogs.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBUtil.close(connection,statement,resultSet);
        }
        return blogs;
    }

    // 3. selectOne  -> blog_detail
    public Blog selectOne(int blogId){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from blog where blogId = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,blogId);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                Blog blog = new Blog();
                blog.setBlogId(blogId);
                blog.setTitle(resultSet.getString("title"));
                blog.setContent(resultSet.getString("content"));
                blog.setPostTime(resultSet.getTimestamp("postTime"));
                blog.setUserId(resultSet.getInt("userId"));
                return blog;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }
    // 4. delete  -> blog_detail delete
    public void delete(int blogId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "delete from blog where blogId = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,blogId);
            int ret = statement.executeUpdate();
            if (ret == 1) {
                System.out.println("删除博客成功");
            } else {
                System.out.println("删除博客失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, null);
        }
    }

    /*************************  单元测试   *********************************/
    public static void main(String[] args) {
        BlogDao blogDao = new BlogDao();
        // 1. insert
//        Blog blog = new Blog();
//        blog.setTitle("博客列表页");
//        blog.setContent("* blogDao类：blogId、title、content、postTime、userId\n" +
//                "* User类：userId、username、password、flag\n" +
//                "* BlogDao类：insert()、selectAll、selectOne、delete\n" +
//                "* UserDao类；selectById()、selectByName()\n" +
//                "* DBUtil工具类：封装数据库操作\n");
//        blog.setPostTime(new Timestamp(System.currentTimeMillis()));
//        blog.setUserId(1);
//        blogDao.insert(blog);

        // 2. selectAll
//        List<Blog> blogs = blogDao.selectAll();
//        System.out.println(blogs);

        // 3. selectOne
//        Blog blog = blogDao.selectOne(1);
//        System.out.println(blog);

        // 4. delete
//        blogDao.delete(1);
    }
}
