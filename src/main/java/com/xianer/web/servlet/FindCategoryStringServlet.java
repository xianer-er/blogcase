package com.xianer.web.servlet;

import com.xianer.service.BlogsService;
import com.xianer.service.impl.BlogsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findCategoryStringServlet")
public class FindCategoryStringServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    1.获取参数
        String cgid = request.getParameter("cgid");
//        2.调用service查询数据
        BlogsService service = new BlogsServiceImpl();

        String s_cg = service.findCategoryById(Integer.parseInt(cgid));
//        3.将数据存入request域
        request.setAttribute("s_cg",s_cg);

//        4.转发到index
        request.getRequestDispatcher("/index.jsp").forward(request,response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
