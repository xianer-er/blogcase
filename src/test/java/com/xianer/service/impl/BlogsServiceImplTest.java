package com.xianer.service.impl;

import com.xianer.controller.Blogs;
import com.xianer.dao.BlogsDao;
import com.xianer.dao.impl.BlogsDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class BlogsServiceImplTest {
    BlogsDao dao = new BlogsDaoImpl();
    @Test
    public void findAll() {

        System.out.println(dao.findAll());
    }
    @Test
    public Blogs findBlog(String id) {
        System.out.println(dao.findBlog(Integer.parseInt(id)));
        return dao.findBlog(Integer.parseInt(id));
    }
}