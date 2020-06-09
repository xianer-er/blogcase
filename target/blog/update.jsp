
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>编辑博客</title>
    <meta charset="utf-8">
    <%--    <link rel="stylesheet" type="text/css" href="../../../target/blog/css/index.css">--%>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function quit() {
            location.href="${pageContext.request.contextPath}/findBlogByPageServlet";
        }
    </script>

    <style>
        #selected
        {
            border-bottom:1px solid red;
        }
    </style>
</head>
<body>
<div class="container">
    <h3>编辑博客</h3>
    <form action="${pageContext.request.contextPath}/updateBlogServlet" method="post">
<%--      隐藏域 提交id--%>
        <input type="hidden" name="id" value="${blog.id}">
        <div class="form-group">
            <label for="exampleInputTitle">输入博客标题</label>
            <input type="text" class="form-control" required="required" name="title"value="${blog.title}" id="exampleInputTitle">
        </div>

        <label>选择博客类别</label>
        <div class="form-group">
            <div class="dropdown">
                <select  name="category">
                    <c:forEach items="${cg}" var="cg">
                    <c:if test="${cg.idcategory==blog.category}">
                        <option id="selected" value="${cg.idcategory}" selected="selected" >${cg.categorycol}</option>
                    </c:if>
                        <c:if test="${cg.idcategory!=blog.category}">
                        <option value="${cg.idcategory}" >${cg.categorycol}</option>

                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label for="exampleInputContent">输入博客内容</label>
            <input type="text" value="${blog.content}" required="required" class="form-control" name="content" id="exampleInputContent">
        </div>

        <div class="form-group">

            <input class="btn btn-primary" type="submit" value="提交">
            <button type="button"  onclick="quit()" class="btn btn-default">返回</button>
        </div>
    </form>
</div>
</body>
</html>
