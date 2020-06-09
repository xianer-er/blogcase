package com.xianer.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.xianer.service.BlogsService;
import com.xianer.service.impl.BlogsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.xianer.controller.Blogs;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/blogsListServlet")
public class BlogsListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1.调用BlogsService查询数据
        BlogsService service = new BlogsServiceImpl();
        List<Blogs> blogs =  service.findAll();
//        list存入request域中
        request.setAttribute("blogs",blogs);

//        3.转发到index。jsp
        request.getRequestDispatcher("/index.jsp").forward(request,response);

//        PrintWriter out = response.getWriter();
//        Gson gson=new Gson();
//        String jsonComments = gson.toJson(blogs);//查找总的评论并且转化为数组
//        out.write(jsonComments);
//        out.flush();
//        System.out.println("我过这了");
//        out.close();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
