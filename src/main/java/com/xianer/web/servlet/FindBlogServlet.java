package com.xianer.web.servlet;

import com.xianer.controller.Blogs;
import com.xianer.controller.Category;
import com.xianer.service.BlogsService;
import com.xianer.service.impl.BlogsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/findBlogServlet")
public class FindBlogServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取id
        String id = request.getParameter("id");
//        2.调用service查找
        BlogsService service = new BlogsServiceImpl();
        Blogs blog = service.findBlog(id);
        List<Category> cg = service.findCategory();
//        3.将blog存到request
        request.setAttribute("blog",blog);
        request.setAttribute("cg",cg);
//        4.转发到update.jsp
        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
