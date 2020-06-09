package com.xianer.web.servlet;

import com.xianer.controller.Blogs;
import com.xianer.service.BlogsService;
import com.xianer.service.impl.BlogsServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/updateBlogServlet")
public class UpdateBlogServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//1.设置编码
        request.setCharacterEncoding("utf-8");
//        2.获取map
        Map<String,String[]> map = request.getParameterMap();
//        3.封装对象
        Blogs blog = new Blogs();
        try {
            BeanUtils.populate(blog,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
//        4.调用service修改
        BlogsService service = new BlogsServiceImpl();
        service.unpdate(blog);
//        5.转发到blogsListServlet
        response.sendRedirect(request.getContextPath()+"/findBlogByPageServlet");



    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
