package com.xianer.dao;

import com.xianer.controller.Blogs;
import com.xianer.controller.Category;

import java.util.List;
import java.util.Map;

//用户操作的dao
public interface BlogsDao {
    public List<Blogs> findAll();

    void add(Blogs blog);

    void delete(int id);

    void update(Blogs blog);

    Blogs findBlog(int id);

    int findTotalCount(Map<String, String[]> condition);

    List<Blogs> findBlogByPage(int start, int rows, Map<String, String[]> condition);

    List<Category> findCategory();

    String findCategoryById(int id);
}
