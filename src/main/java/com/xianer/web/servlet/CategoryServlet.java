package com.xianer.web.servlet;

import com.xianer.controller.Category;
import com.xianer.service.BlogsService;
import com.xianer.service.impl.BlogsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/categoryServlet")
public class CategoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //        1.调用service查询数据
        BlogsService service = new BlogsServiceImpl();
        List<Category> cg = service.findCategory();
//        2.将数据存入request域
        request.setAttribute("cg",cg);
//        3.转发到add。jsp
        request.getRequestDispatcher("/add.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
