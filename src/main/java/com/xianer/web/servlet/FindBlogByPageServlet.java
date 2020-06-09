package com.xianer.web.servlet;

import com.xianer.controller.Blogs;
import com.xianer.controller.Category;
import com.xianer.controller.PageBean;
import com.xianer.service.BlogsService;
import com.xianer.service.impl.BlogsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/findBlogByPageServlet")
public class FindBlogByPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //1.获取参数
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");

        if(currentPage==null||"".equals(currentPage)){
            currentPage="1";
        }
        if(rows==null||"".equals(rows)){
            rows="5";
        }

//        获取条件查询参数
    Map<String,String[]> condition= request.getParameterMap();

//        2.调用service查询数据
        BlogsService service = new BlogsServiceImpl();
      PageBean<Blogs> pb= service.findBlogByPage(currentPage,rows,condition);
       List<Category>  cg = service.findCategory();
//      3.存入request域中
        request.setAttribute("pb",pb);
//        查询的条件情况
        request.setAttribute("condition",condition);
        request.setAttribute("cg",cg);
//        4.转发到index
        request.getRequestDispatcher("/index.jsp").forward(request,response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
