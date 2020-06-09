package com.xianer.web.servlet;

import com.xianer.controller.Blogs;
import com.xianer.controller.Category;
import com.xianer.service.BlogsService;
import com.xianer.service.impl.BlogsServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/addBlogServlet")
public class AddBlogServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//1.设置编码
        request.setCharacterEncoding("utf-8");
//        2.获取参数
        Map<String ,String[]> map = request.getParameterMap();
//        3.封装对象
        Blogs blog = new Blogs();

        try {
//            其中Map中的key必须与目标对象中的属性名相同
            BeanUtils.populate(blog,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
//        4.调用service保存
        BlogsService service = new BlogsServiceImpl();

        service.addBlog(blog);


//5.跳转到blogsListServlet
        response.sendRedirect(request.getContextPath()+"/findBlogByPageServlet");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
