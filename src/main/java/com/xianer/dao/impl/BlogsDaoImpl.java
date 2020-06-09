package com.xianer.dao.impl;

import com.xianer.controller.Category;
import com.xianer.dao.BlogsDao;
import com.xianer.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.xianer.controller.Blogs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BlogsDaoImpl implements BlogsDao {
    private JdbcTemplate template= new  JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Blogs> findAll() {
//        使用jdbc操作数据库
        String sql = "SELECT * FROM blogs";
//        它可自动将一行数据映射到指定类的实例中 它首先将这个类实例化，然后通过名称匹配的方式，映射到属性中去。
        List<Blogs> blogs = template.query(sql,new BeanPropertyRowMapper<Blogs>(Blogs.class));
        return blogs;
    }

    @Override
    public void add(Blogs blog) {
//1.定义sql
        String sql = "insert into blogs values(null,?,?,?,?)";
//        2.执行sql
        template.update(sql,blog.getTitle(),blog.getCategory(),blog.getUpdatetime(),blog.getContent());
    }


    @Override
    public void delete(int id) {
//        1.定义sql
        String sql = "delete from blogs where id = ?";
//        2.执行sql
        template.update(sql,id);
    }

    @Override
    public void update(Blogs blog) {
//       1.定义sql
        String sql = "update blogs set title=? ,category=? ,content=? where id = ?";
//        2.执行sql
        template.update(sql,blog.getTitle(),blog.getCategory(),blog.getContent(),blog.getId());
    }

    @Override
    public Blogs findBlog(int id) {
//        1.定义sql
        String sql ="select * from blogs where id=?";
//        2.执行sql

        return template.queryForObject(sql,new BeanPropertyRowMapper<Blogs>(Blogs.class),id);
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        String sql = "select count(*) from blogs where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
//        遍历map
        Set<String> keySet = condition.keySet();
        List<Object> params = new ArrayList<Object>();

        for (String key : keySet) {
//            排除分页条件参数
            if("currentPage".equals(key)||"rows".equals(key)){
                continue;
            }
            String value =condition.get(key)[0];
            if(value!=null  && !"".equals(value)){
                sb.append(" and "+key+" like ?");
                params.add("%"+value+"%"); //？的值
            }
        }
//        System.out.println(template.queryForObject(sb.toString(),Integer.class,params.toArray()));
        return template.queryForObject(sb.toString(),Integer.class,params.toArray());
    }

    @Override
    public List<Blogs> findBlogByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from blogs where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
//        遍历map
        Set<String> keySet = condition.keySet();
        List<Object> params = new ArrayList<Object>();

        for (String key : keySet) {
//            排除分页条件参数
            if("currentPage".equals(key)||"rows".equals(key)){
                continue;
            }
            String value =condition.get(key)[0];
            if(value!=null && !"".equals(value)){
                sb.append(" and "+key+" like ?");
                params.add("%"+value+"%"); //？的值
            }
        }
//        添加分页查询
        sb.append(" limit  ? , ? ");
//        添加分页查询参数值
        params.add(start);
        params.add(rows);

        System.out.println(sb.toString());
        System.out.println(params);
//        System.out.println(pb);
        return template.query(sb.toString(),new BeanPropertyRowMapper<Blogs>(Blogs.class),params.toArray());
    }


    @Override
    public List<Category> findCategory() {
        String sql = "SELECT * FROM category";
        List<Category> categories = template.query(sql,new BeanPropertyRowMapper<Category>(Category.class));
        return categories;
    }
    @Override
    public String findCategoryById(int id){

        String sql = "select categorycol from category where idcategory = ?";
        return template.queryForObject(sql,java.lang.String.class,id);
    }

}
