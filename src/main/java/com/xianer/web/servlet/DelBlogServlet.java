package com.xianer.web.servlet;

import com.xianer.service.BlogsService;
import com.xianer.service.impl.BlogsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/delBlogServlet")
public class DelBlogServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//1.获取id

        String id = request.getParameter("id");

//        2.调用service删除
        BlogsService service = new BlogsServiceImpl();
        service.deleteBlog(id);
//        3.跳转到blogsListServlet
       response.sendRedirect(request.getContextPath()+"/findBlogByPageServlet");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
