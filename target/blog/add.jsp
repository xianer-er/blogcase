<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Timestamp" %><%--
  Created by IntelliJ IDEA.
  User: 仙儿
  Date: 2020/5/15
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>写博客</title>
    <meta charset="utf-8">
    <%--    <link rel="stylesheet" type="text/css" href="../../../target/blog/css/index.css">--%>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
<%--        退出按钮的跳转--%>
        function quit() {
            location.href="${pageContext.request.contextPath}/findBlogByPageServlet";
        }

    //    获取当前时间
            var a=(new Date()).toLocaleString();
                a.replace(/\//g,'-');
            var nowdate= (new Date(a))/1000;
            alert(a);
        function getLocalTime(nS) {
            return new Date(parseInt(nS)*1000).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
        }
    </script>
</head>
<body>
<div class="container">
   <h3>编辑新的博客</h3>
    <form action="${pageContext.request.contextPath}/addBlogServlet" method="post">
        <%--      隐藏域 提交id--%>
        <%
            Date d =new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String now = format.format(d);
        %>
            <%=now%>
        <input type="hidden" name="updatetime" value=<%=now%>>
        <div class="form-group">
            <label for="exampleInputTitle">输入博客标题</label>
            <input type="text" class="form-control" required="required" name="title" id="exampleInputTitle">
        </div>
        <div class="form-group">
        <label>选择博客类别</label>
            <div class="dropdown">
                <select  name="category">
                    <c:forEach items="${cg}" var="cg">
                        ${cg.idcategory}
                        <option value="${cg.idcategory}" >${cg.categorycol}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label for="exampleInputContent">输入博客内容</label>
            <input type="text" class="form-control" required="required" name="content" id="exampleInputContent">
        </div>

        <div class="form-group">

    <input id="submit" class="btn btn-primary" type="submit" value="提交">
    <button type="button" onclick="quit()" class="btn btn-default">退出</button>
        </div>
    </form>
</div>
</body>
</html>
