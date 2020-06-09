package com.xianer.web.servlet;

import com.xianer.service.BlogsService;
import com.xianer.service.impl.BlogsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delSelectedServlet")
public class DelSelectedServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    1.获取ids
        String[] ids = request.getParameterValues("bid");
//        2.调用service删除
        BlogsService service = new BlogsServiceImpl();
        service.delSelected(ids);
//        3.转发到blogsListServlet
        response.sendRedirect(request.getContextPath()+"/findBlogByPageServlet");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
