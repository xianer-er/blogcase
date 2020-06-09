package com.xianer.service;

import com.xianer.controller.Blogs;
import com.xianer.controller.Category;
import com.xianer.controller.PageBean;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的业务接口
 */
public interface BlogsService {
    /**
     * 查询所有用户信息
     */
    public List<Blogs> findAll();

    /**
     * 保存新的博客
     * @param blog
     */
    void addBlog(Blogs blog);

    /**
     * 根据id删除博客
     * @param id
     */
    void deleteBlog(String id);

    /**
     * 根据id修改数据
     * @param blog
     */
    void unpdate(Blogs blog);

    /**
     * 根据id查找对应博客内容
     * @param id
     * @return
     */
    Blogs findBlog(String id);

    /**
     * 批量删除选中博客
     * @param ids
     */
    void delSelected(String[] ids);

    /**
     * 分页条件查询
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    PageBean<Blogs> findBlogByPage(String currentPage, String rows, Map<String, String[]> condition);

    /**
     * 查询所有类别string
     * @return
     */
   List<Category>  findCategory();

    /**
     * 根据id查询对应的类别string
     * @param id
     * @return
     */
    String findCategoryById(int id);
}
