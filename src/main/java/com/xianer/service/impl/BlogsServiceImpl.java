package com.xianer.service.impl;

import com.xianer.controller.Category;
import com.xianer.controller.PageBean;
import com.xianer.dao.BlogsDao;
import com.xianer.dao.impl.BlogsDaoImpl;
import com.xianer.service.BlogsService;

import com.xianer.controller.Blogs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlogsServiceImpl implements BlogsService {

        BlogsDao dao = new BlogsDaoImpl();
    @Override
    public List<Blogs> findAll() {
//        调用dao
        System.out.println(dao.findAll());
        return dao.findAll();
    }

    @Override
    public void addBlog(Blogs blog) {
        dao.add(blog);
    }

    @Override
    public void deleteBlog(String id) {
        dao.delete(Integer.parseInt(id));
    }

    @Override
    public void unpdate(Blogs blog) {
        dao.update(blog);
    }

    @Override
    public Blogs findBlog(String id) {
//        Blogs blogs = new Blogs();
        return dao.findBlog(Integer.parseInt(id));
    }

    @Override
    public void delSelected(String[] ids) {
        if(ids!=null && ids.length>0){

//        1.遍历ids数组
        for (String id : ids) {
//            2.调用删除方法
            dao.delete(Integer.parseInt(id));
        }
        }
    }

    @Override
    public PageBean<Blogs> findBlogByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        if(currentPage<=0){
            currentPage=1;
        }

        PageBean<Blogs> pb = new PageBean<Blogs>();
//        1.存入currentPage
        pb.setCurrentPage(currentPage);
//        2.存入rows
        pb.setRows(rows);
//        3.调用dao查询总记录数
        int totalCount = dao.findTotalCount(condition);
        System.out.println(totalCount);
        pb.setTotalCount(totalCount);
//         4.调用dao查询相应数据
        int start = (currentPage-1)*rows;
        List<Blogs> list = dao.findBlogByPage(start,rows,condition);

        pb.setList(list);
//        5.存入总页数
        if(totalCount<rows){
            int totalPage =1;
            pb.setTotalPage(totalPage);
        }else{
        int totalPage = (totalCount%rows) == 0? (totalCount/rows):(totalCount/rows+1);
        pb.setTotalPage(totalPage);
        }
//        6.存入类别中文
       List<String> categorycol = new ArrayList<>();
        for (int i = 0;i<list.size();i++){
            String categoryid= list.get(i).getCategory();
            String s_category = dao.findCategoryById(Integer.parseInt(categoryid));
            categorycol.add(s_category);
        }
        pb.setCategorycol(categorycol);
        return pb;
    }

    @Override
    public List<Category> findCategory() {

        return dao.findCategory();
    }

    @Override
    public String findCategoryById(int id) {

        return dao.findCategoryById(id);
    }
}
